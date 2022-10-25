/**
 * 字典树（或者前缀树）实现
 * @author QinE
 * @create 2021-10-20 8:21
 */
public class TrieTest {

    public static void main(String[] args) {
        TrieNode trieNode = new TrieNode();
        trieNode.insert("str");

    }
}

class TrieNode {
    private TrieNode[] children;
    private boolean isEnd;

    public TrieNode() {
        children = new TrieNode[26];
        isEnd = false;
    }

    //插入
    public void insert(String word) {
        TrieNode node = this;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }

        node.isEnd = true;
    }

    //查找前缀
    public TrieNode searchPrefix(String prefix) {
        TrieNode node = this;

        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }

            node = node.children[index];
        }

        return node;
    }

    //查找
    public boolean search(String word) {
        return searchPrefix(word) != null && this.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }
}