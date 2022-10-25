package linkedlist;

/**
 * 双链表的实现
 *
 * @author QinE
 * @create 2021-11-10 17:17
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        DoubleLinkedList dl = new DoubleLinkedList();
        dl.add(new NewNode(1, 22));
        dl.add(new NewNode(2, 11));
//        dl.list();
//        dl.del(2);
//        dl.update(new NewNode(1,44));
        dl.addByOrder(new NewNode(4, 44));
        dl.addByOrder(new NewNode(3, 9));
        dl.addByOrder(new NewNode(5, 2));
        dl.list();
    }
}

class DoubleLinkedList {

    public NewNode root = new NewNode();

    public void add(NewNode node) {
        NewNode temp = root;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        //到达链表尾部
        temp.setNext(node);
        node.setPre(temp);
    }

    public void addByOrder(NewNode node) {
        NewNode temp = root;
        boolean falg = false;
        while (true) {
            if (temp.getNext() == null) break;
            if (temp.getNext() != null && temp.getNext().getId() > node.getId()) {
                break;
            } else if (temp.getNext() != null && temp.getNext().getId() == node.getId()) {
                falg = true;
                break;
            }
            temp = temp.getNext();
        }

        if (!falg) {
            //为防止空指针，对temp位置进行判断
            if (temp.getNext() != null) {//尚未达到尾部
                node.setNext(temp.getNext());
                temp.getNext().setPre(node);
//                temp.setNext(node);
            }
            temp.setNext(node);
            node.setPre(temp);
        } else {
            System.out.println("该节点已存在，无法添加");
        }
    }

    public void list() {
        if (root.getNext() == null) System.out.println("单链表为空，请添加节点后遍历");
        NewNode temp = root.getNext();
        while (temp != null) {
            System.out.println(temp);
            temp = temp.getNext();
        }
    }

    public void del(int id) {
        if (root.getNext() == null) return;
        NewNode temp = root.getNext();
        while (temp != null) {
            if (id == temp.getId()) {
                temp.getPre().setNext(temp.getNext());
                if (temp.getNext() != null)
                    temp.getNext().setPre(temp.getPre());//如果此节点为最后一个节点，则不需执行次操作，否则会空指针异常
                System.out.println("删除成功");
                break;
            }

            temp = temp.getNext();

        }

        if (temp == null)
            System.out.println("该节点不存在，无法删除");

    }

    public void update(NewNode node) {
        if (root.getNext() == null) return;
        NewNode temp = root.getNext();
        while (temp != null) {
            if (node.getId() == temp.getId()) {
                temp.setValue(node.getValue());
                System.out.println("修改成功");
                break;
            }
            temp = temp.getNext();
        }
    }
}

class NewNode {
    private NewNode pre;//前一节点
    private NewNode next;//后一节点
    private int id;
    private int value;

    public NewNode(){}

    public NewNode(int id, int value) {
        this.id = id;
        this.value = value;
    }

    public NewNode getPre() {
        return pre;
    }

    public void setPre(NewNode pre) {
        this.pre = pre;
    }

    public NewNode getNext() {
        return next;
    }

    public void setNext(NewNode next) {
        this.next = next;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "NewNode{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}