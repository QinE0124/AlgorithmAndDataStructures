package avl;

/**
 * @author Qine
 * @create 2021-07-27 19:36
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        //创建一个 AVLTree对象
        AVLTree avlTree = new AVLTree();
        //添加结点
        for(int i=0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        avlTree.infixOrder();

        System.out.println("在平衡处理~~");
        System.out.println("树的高度=" + avlTree.getRoot().height()); //3
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight()); // 2
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight()); // 2
        System.out.println("当前的根结点=" + avlTree.getRoot());//8
    }
}
class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    //添加结点
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("树空，不能遍历");
        }
    }

    //查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找要删除结点的父结点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //返回以node为根结点的二叉排序树的最小结点的值,并删除
    public int delRightTreeMin(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }

    //删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            Node parent = searchParent(value);
            if (targetNode.left == null && targetNode.right == null) {
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value){
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null){
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {
                if (targetNode.left != null) {
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }
}
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //返回左子树的高度
    public int leftHeight() {
        if (this.left == null) {
            return 0;
        }
        return this.left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (this.right == null) {
            return 0;
        }
        return this.right.height();
    }

    //返回以该结点为根结点的树的高度
    public int height() {
        return Math.max(this.left == null ? 0 : this.leftHeight(), this.right == null ? 0 : this.rightHeight()) + 1;
    }

    //左旋转
    private void leftRotate() {
        //以当前结点的值创建新的结点
        Node newNode = new Node(this.value);
        //把新的结点的左子树设置为当前结点的左子树
        newNode.left = this.left;
        //把新的结点的右子树设置为当前结点的右子树的左子树
        newNode.right = this.right.left;
        //把当前结点的值替换成右子节点的值
        this.value = this.right.value;
        //把当前结点的右子树设置为当前结点右子树的右子树
        this.right = this.right.right;
        //把当前结点的左子树设置成新的结点
        this.left = newNode;

    }

    //右旋转
    private void rightRotate() {
        Node newNode = new Node(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = newNode;
    }
    //添加结点
    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (node.value < this.value) {
           if (this.left == null) {
               this.left = node;
           } else {
               this.left.add(node);
           }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        //当添加一个结点后，如果（右子树的高度 - 左子树的高度）> 1，左旋转
        if (this.rightHeight() - this.leftHeight() > 1) {
            //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if (this.right != null && right.leftHeight() > right.rightHeight()) {
                //先对右子结点进行右旋转
                this.right.rightRotate();
                //然后对当前结点进行左旋转
                this.leftRotate();
            }
            return;
        }

        //当添加一个结点后，如果（左子树的高度 - 右子树的高度）> 1，右旋转
        if (this.leftHeight() - this.rightHeight() > 1) {
            //如果它的左子树的右子树的高度大于它的左子树的左子树的高度
            if (this.left != null && left.rightHeight() > left.leftHeight()) {
                //先对左子左子结点进行左旋转
                this.left.leftRotate();
            }
            this.rightRotate();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //查找要删除的结点
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除的父结点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value)
            || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value > this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}