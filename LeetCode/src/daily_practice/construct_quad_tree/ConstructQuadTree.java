package daily_practice.construct_quad_tree;

import org.junit.Test;

/**
 * 建立四叉树
 * @author QinE
 * @create 2022-04-29 22:04
 */
public class ConstructQuadTree {

    @Test
   public void test() {
        int[][] grid = {{0,1},{1,0}};
   }

    public static Node construct(int[][] grid) {
        return dfs(0, 0, grid.length, grid[0].length, grid);
    }



    public static Node dfs(int r0, int c0, int r1, int c1, int[][] grid) {
        boolean same = true;
        for (int i = r0; i < r1; i++) {
            for (int j = c0; j < c1; j++) {
                if (grid[i][j] != grid[r0][c0]) {
                    same = false;
                    break;
                }
            }
            if (!same)
                break;

        }

        if (same)
            return new Node(grid[r0][c0] == 1, true);

        Node ret = new Node(
                true, false,
                dfs(r0, c0, r0 + r1 >> 1, c0 + c1 >> 1, grid),
                dfs(r0, c0 + c1 >> 1, r0 + r1 >> 1, c1, grid ),
                dfs(r0 + r1 >> 1, c0, r1, c0 + c1 >> 1, grid),
                dfs(r0 + r1 >> 1, c0 + c1 >> 1, r1, c1, grid)
        );

        return ret;
    }


}

class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    Node (boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }


    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", isLeaf=" + isLeaf +
                '}';
    }
}