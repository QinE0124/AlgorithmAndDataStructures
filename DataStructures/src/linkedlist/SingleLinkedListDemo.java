package linkedlist;

import java.util.Stack;

/**
 * @author QinE
 * @create 2021-11-08 21:07
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        SingleLinkedList sl = new SingleLinkedList();
        sl.add(new Node(1, 45));
        sl.add(new Node(2, 88));
        sl.addByOrder(new Node(5, 22));
        sl.addByOrder(new Node(4, 99));
        sl.addByOrder(new Node(3, 33));


    //    sl.update(new Node(3,78));
        sl.list();
        sl.del(6);
        sl.list();

//        Node lastIndexNode = SingleLinkedList.findLastIndexNode(2, sl.root);
//        System.out.println(lastIndexNode);

//        SingleLinkedList.reverseList(sl.root);
//        sl.list();
//        SingleLinkedList.reversePrint(sl.root);

    }
}

class SingleLinkedList {

    public Node root = new Node();//初始化根节点，不存放数据

    //获取单链表有效节点数量(如果为带头节点链表，则不需统计头节点)
    public static int getLength(Node head) {
        if (head.getNext() == null) return 0;
        Node temp = head.getNext();
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.getNext();
        }

        return count;
    }

    //查找单链表倒数第k个节点
    public static Node findLastIndexNode(int index, Node head) {
        if (head.getNext() == null) {
            System.out.println("当前链表为空，无法查找");
            return null;
        }
        int length = getLength(head);
        if (index < 0 || index > length) return null;//index校验
        Node temp = head.getNext();
        //利用for循环辅助遍历到倒数第k个位置
        for (int i = 0; i < length - index; i++) {
            temp = temp.getNext();
        }

        return temp;
    }

    //单链表反转
    public static void reverseList(Node head) {
       //如过单链表为空或者只有一个节点，直接返回
        if (head.getNext() == null || head.getNext().getNext() == null) return;
        Node reverseHead = new Node();
        Node cur = head.getNext();//辅助节点，方便遍历
        Node next = null;
        //每次遍历将链表上的节点放在反转链表最前端
        while (cur != null) {
            next = cur.getNext();//保存节点，方便遍历
            cur.setNext(reverseHead.getNext());//将下一节点指向反转链表的最前端
            reverseHead.setNext(cur);//将cur挂到反转链表上
            cur = next;//指向后一节点
        }
        head.setNext(reverseHead.getNext());//将反转后的链表挂在原先单链表上，致此，反转完成
    }

    //单链表逆序打印（栈）
    public static void reversePrint(Node head) {
        if (head.getNext() == null) return;
        Stack<Node> stack = new Stack<>();
        Node temp = head.getNext();
        while (temp != null) {
            stack.push(temp);
            temp = temp.getNext();
        }

        //打印
         while(stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
    /**
     * 将节点存放在单链表最后
     *
     * @param node
     */
    public void add(Node node) {

        //因为头节点不能动，所以需要辅助节点帮忙遍历
        Node temp = root;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(node);
    }

    /**
     * 按照id排序插入单链表
     * @param node
     */
    public void addByOrder(Node node) {

        Node temp = root;
        boolean flag = false;//标志，默认不存在
/*        while (temp.getNext() != null) {
//            if (temp.getNext().getId() > node.getId()) { //找到插入位置，temp后节点
////                node.setNext(temp.getNext());
////                temp.setNext(node);
//                flag = true;
//            } //else if (temp.getNext().getId() == node.getId()){//该节点已存在，无法插入
////                System.out.println("该节点已存在，无法插入");
////            }
//            temp = temp.getNext();
//        }*/
        while (true) {
            if (temp.getNext() == null) break;
            if (temp.getNext().getId() > node.getId()) {//找到插入位置
                break;
            }
            flag = true;
            temp = temp.getNext();
        }

        if (flag) {
            node.setNext(temp.getNext());
            temp.setNext(node);
        } else {
            System.out.println("该节点已存在，无法插入");
        }

    }

    /**
     * 更新节点
     * @param node
     */
    public void update(Node node) {
        if (root.getNext() == null) System.out.println("链表为空，请直接添加");
        Node temp = root.getNext();
        while (temp.getNext() != null) {
            if (temp.getId() == node.getId()) {
                temp.setValue(node.getValue());
                System.out.println("成功更新节点");
                break;
            }
            temp = temp.getNext();
        }

    }
    /**
     * 删除节点
     * @param id
     */
    public void del(int id) {
        if (root.getNext() == null) System.out.println("链表为空，不能删除");
        Node temp = root;
        while (temp.getNext() != null) {
            if (temp.getNext().getId() == id) {
                temp.setNext(temp.getNext().getNext());
                System.out.println("删除成功");
                break;
            }
            temp = temp.getNext();

            if (temp.getNext() == null && temp.getId() != id) {
                System.out.println("当前链表没有该节点，无法删除");
            }
        }



    }

    /**
     * 遍历链表
     */
    public void list() {
        if (root == null) System.out.println("链表为空，不能遍历");

        //temp辅助遍历
        Node temp = root;
        while (temp.getNext() != null) {
            temp = temp.getNext();
            System.out.println(temp);
        }
    }
}

class Node {

    private int id;
    private int value;
    private Node next;

    public Node() {}

    public Node(int id, int value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value + "," + "id=" + id +
                '}';
    }
}