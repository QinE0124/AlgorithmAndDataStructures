package segment_tree;

import java.util.Scanner;

/**
 * @author QinE
 * @create 2021-11-18 17:04
 */
public class SegmentTree {

    public int[] data;
    public Node[] tree;
    public static final int Max_N = 10000;


    class Node {
        int l;
        int r;
        int sum;
        int max;

        Node() {}

        Node(int l, int r, int sum, int max) {
            this.l = l;
            this.r = r;
            this.sum = sum;
            this.max = max;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "l=" + l +
                    ", r=" + r +
                    ", sum=" + sum +
                    ", max=" + max +
                    '}';
        }
    }

    private int getNodesCount(int length) {
        int count = 1;
        while (true) {
            if (count >= length) {
                count = count * 2 - 1;
                break;
            }
            count <<= 1;
        }
        return count;
    }

    public void init() {
        data = new int[]{1, 3, 5, 7, 9, 11};
        tree = new Node[getNodesCount(data.length)];
    }

    /**
     * 构造线段树
     * @param l
     * @param r
     * @param id 当前结点在tree中索引
     */
    public void build(int l, int r, int id) {
        tree[id] = new Node(l, r, 0, 0);
        if (l== r) {
            tree[id].sum = tree[id].max = data[l];
            return;
        }
        int mid = (l + r) >> 1;
        int leftNode = id * 2 + 1;
        int rightNode = id * 2 + 2;
        build(l, mid, leftNode);
        build(mid + 1, r, rightNode);
        tree[id].sum = tree[leftNode].sum + tree[rightNode].sum;
        tree[id].max = Math.max(tree[leftNode].max, tree[rightNode].max);
    }

    /**
     * 单点修改
     * @param id
     * @param index
     * @param value
     */
    public void update(int id, int index, int value) {
        if (index < 0 || index >= data.length) {
            System.out.println("不存在此索引");
            return;
        }

        if (tree[id].l == tree[id].r) {
            tree[id].max = tree[id].sum = data[index] = value;
            return;
        }

        int mid = (tree[id].l + tree[id].r) >> 1;
        int leftNode = id * 2 + 1;
        int rightNde = id * 2 + 2;

        if (index <= mid && index >= tree[id].l) {
            update(leftNode, index, value);
        } else {
            update(rightNde, index, value);
        }

        tree[id].sum = tree[leftNode].sum + tree[rightNde].sum;
        tree[id].max = Math.max(tree[leftNode].max , tree[rightNde].max);
    }

    /**
     * 区域求和
     * @param l,r 需要求和的区间
     * @return
     */
    public int getSum(int id, int l, int r) {
        //如果当前区间与目标区间完全不重合，直接返回
        if (tree[id].l > r || tree[id].r < l) {
            return 0;
        }

        //如果当前区间完全包含与目标区间，则直接返回当前结点的sum
        if (tree[id].l >= l && tree[id].r <= r) {
            return tree[id].sum;
        }

        int mid = (tree[id].l + tree[id].r) >> 1;
        int leftNode = id * 2 + 1;
        int rightNode = id * 2 + 2;

        if (r <= mid) {
            return getSum(leftNode, l, r);
        } else if (l > mid) {
            return getSum(rightNode, l, r);
        } else {
            return getSum(leftNode, l, mid) + getSum(rightNode, mid + 1, r);
        }
    }

    /**
     * 求区间最大值
     * @param id
     * @param l,r 目标区间
     * @return
     */
    public int getMax(int id, int l, int r) {
        //如果当前区间与目标区间完全不重合，直接返回
        if (tree[id].l > r || tree[id].r < l) {
            return 0;
        }

        //如果当前区间包含于目标区间，则返回当前结点所存储的最大值
        if (l <= tree[id].l && r >= tree[id].r) {
            return tree[id].max;
        }

        int mid = (tree[id].l + tree[id].r) >> 1;
        int leftNode = id * 2 + 1;
        int rightNode = id * 2 + 2;
        if (r <= mid) {
            return getMax(leftNode, l, r);
        } else if (l > mid) {
            return getMax(rightNode, l, r);
        } else {
            return Math.max(getMax(leftNode, l, mid), getMax(rightNode, mid + 1, r));
        }
    }

    public void print() {
        for (int i = 0; i < tree.length; i++) {
            System.out.println(tree[i]);
        }
    }
    public static void main(String[] args) {
        SegmentTree se = new SegmentTree();
        Scanner sc = new Scanner(System.in);
        se.init();
//        int n = sc.nextInt();
//        for (int i = 0; i < n; i++) {
//            se.data[i] = sc.nextInt();
//        }
//        sc.close();
        System.out.println(se.data.length);
        System.out.println(se.tree.length);
        se.build(0, se.data.length - 1, 0);
        se.print();
        System.out.println();
        se.update(0, 3, 89);
        se.print();
        int sum = se.getSum(0, 0, 1);
        System.err.println(sum);
        int max = se.getMax(0, 0, 1);
        System.err.println(max);

    }
}
