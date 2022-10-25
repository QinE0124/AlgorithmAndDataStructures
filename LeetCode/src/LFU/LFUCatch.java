package LFU;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * @author QinE
 * @create 2022-06-10 20:24
 */
public class LFUCatch {

    //key 到 val 的映射，KV表
    HashMap<Integer, Integer> keyToVal;
    //key 到 freq的映射, KF表
    HashMap<Integer, Integer> keyToFreq;
    //freq 到 key 列表的映射， FK表
    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    //记录最小的频次
    int minFreq;
    //记录LFU缓存的最大容量
    int cap;

    public LFUCatch(int capacity) {
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        this.cap = capacity;
        this.minFreq = 0;
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key))
            return -1;
        //增加key对应的freq
        increaseFreq(key);
        return keyToVal.get(key);
    }


    public void put(int key, int val) {
        if (this.cap <= 0)
            return;
        //若key已存在，修改对应的val即可
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, val);
            //key对应的freq加一
            increaseFreq(key);
            return;
        }

        //key不存在，需要重新插入
        //容量已满的化需要淘汰一个freq最小的key
        if (this.cap <= keyToVal.size()) {
            removeMinFreqKey();
        }

        //插入key和val,对应的freq为一
        keyToVal.put(key, val);
        keyToFreq.put(key, 1);
        freqToKeys.putIfAbsent(key, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);
        //插入新key后最小的freq肯定是1
        this.minFreq = 1;
    }

    private void removeMinFreqKey() {

        //freq最小的key列表
        LinkedHashSet<Integer> keyList = freqToKeys.get(this.minFreq);
        //其中最先插入的那个key就是该被淘汰的key
        int deletedKey = keyList.iterator().next();
        //更新FK表
        keyList.remove(deletedKey);
        if (keyList.isEmpty())
            freqToKeys.remove(this.minFreq);

        //更新KV表
        keyToVal.remove(deletedKey);
        //更新KF表
        keyToFreq.remove(deletedKey);

    }

    private void increaseFreq(int key) {
        int freq = keyToFreq.get(key);
        //更新KF表
        keyToFreq.put(key, freq + 1);
        //更新Fk表
        freqToKeys.get(freq).remove(key);
        freqToKeys.putIfAbsent(key, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);

        //如果freq对应的列表空了，移除这个freq
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);
            //如果这个freq恰好是minFreq，更新minFreq
            if (freq == this.minFreq)
                this.minFreq++;
        }
    }
}
