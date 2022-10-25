package union_find;

/**
 * @author QinE
 * @create 2022-06-16 15:11
 */
public class UnionFind {

    //记录连通分量
    private int count;
    //节点x的父节点是parent[x]
    private int[] parent;
    //新增一个数组记录数的“重量“
    private int[] size;

    //构造函数，n为图的节点总数
    public UnionFind(int n) {
        //一开始互不相连
        this.count = n;
        //父节点指针指向自己
        //最初每个树只有一个节点
        //重量应该初始化为1
        size = new int[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }


    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ)
            return;
        //将两棵树合为一棵，小树接到大树上，较平衡
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += rootQ;
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }

        //两个分量合二为一
        count--;
    }

    //返回某个节点x的根节点
    private int find(int x) {
        //根节点的parent[x] == [x]
        while (parent[x] != x) {
            //路径压缩
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    //返回当前的连通分量的个数
    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rooQ = find(q);
        return rootP == rooQ;
    }
}
