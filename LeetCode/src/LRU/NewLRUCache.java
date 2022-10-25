package LRU;

import java.util.LinkedHashMap;

/**
 * @author QinE
 * @create 2022-06-09 20:58
 */
public class NewLRUCache {
    int cap;
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    public NewLRUCache(int capacity) {
        this.cap = capacity;
    }

    public int get(int  key) {
        if (!cache.containsKey(key))
            return -1;
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int val) {
        if (cache.containsKey(key)) {
            cache.put(key, val);
            makeRecently(key);
            return;
        }

        if (cache.size() >= cap) {
            //链表头部就上是长时间没用的
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }

        cache.put(key, val);
    }
    private void makeRecently(int key) {
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);
    }

}
