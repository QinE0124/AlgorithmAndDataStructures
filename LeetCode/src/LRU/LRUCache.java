package LRU;

import java.util.HashMap;

/**
 * LRU算法实现
 * @author QinE
 * @create 2022-06-09 20:38
 */
public class LRUCache {

    private HashMap<Integer, Node> map;
    private DoubleList cache;
    //最大容量
    private int cap;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        cache = new DoubleList();
        this.cap = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        //将该数据提升为最近使用的
        makeRecently(key);
        return map.get(key).val;
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) {
            deleteKey(key);
            addRecently(key, val);
            return;
        }

        if (cap == cache.getSize()) {
            removeLeastRecently();
        }

        addRecently(key, val);
    }
    //将某个key提升为最近使用的
    private void makeRecently(int key) {
        Node x = map.get(key);
        cache.remove(x);
        cache.addLast(x);
    }

    //添加最近使用的key
    private void addRecently(int key, int val) {
        Node x = new Node(key, val);
        cache.addLast(x);
        map.put(key, x);
    }

    //删除某一个key
    private void deleteKey(int key) {
        Node x = map.remove(key);
        cache.remove(x);
    }

    //删除最久未使用的元素
    private void removeLeastRecently() {
        Node deleteNode = cache.removeFirst();
        int deleteKey = deleteNode.key;
        map.remove(deleteKey);
    }


}
