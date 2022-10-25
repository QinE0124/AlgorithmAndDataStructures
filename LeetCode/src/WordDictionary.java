/**
 * Design a data structure that supports adding new words and finding
 * if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure,
 * it can be matched later.
 * bool search(word) Returns true if there is any string
 * in the data structure that matches word or false otherwise.
 * word may contain dots '.' where dots can be matched with any letter.
 *
 * @author QinE
 * @create 2021-10-20 9:33
 */

/*
解答错误！待改进
 */
public class WordDictionary {

    private Trie root;

    public WordDictionary() {
        this.root = new Trie();
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        String str = "strs";
        wordDictionary.addWord(str);
        System.out.println(wordDictionary.search(str));
    }

    //将 word 添加到数据结构中，之后可以对它进行匹配
    public void addWord(String word) {
        root.insert(word);
    }

    //如果数据结构中存在字符串与 word 匹配，则返回 true ；
    // 否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    //深度搜索遍历
    public boolean dfs(String word, int index, Trie node) {
        if (index == word.length()) {
            return node.isEnd();
        }

        char ch = word.charAt(index);
        //使用递归处理
        if (Character.isLetter(ch)) {//判断下个字符是否为符号
            int childrenIndex = ch - 'a';

            if (node.getChildren()[childrenIndex] == null || node.isEnd() == true) {
               return true;
            } else if (node.getChildren()[childrenIndex] != null && node.isEnd() == false) {
                dfs(word, index + 1, node.getChildren()[childrenIndex]);
            }

        } else {
            for (int i = 0; i < 26; i++) {
                if (node.getChildren()[i] != null && node.isEnd() == false) {
                    dfs(word, index + 1, node.getChildren()[i]);
                } else if (node.getChildren()[i] == null || node.isEnd() == true) {
                    return true;
                }
            }
        }
        return false;
    }
}

//前缀树
class Trie {
    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    //插入
    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch- 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }

        //添加完成后将isEnd设置为true
        node.isEnd = true;
        

    }

    //遍历
    /*
    未完成
     */
    public void show() {
       for (int i = 0; i < children.length; i++) {
           children[i].show();
       }
    }
    public Trie[] getChildren() {
        return children;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
