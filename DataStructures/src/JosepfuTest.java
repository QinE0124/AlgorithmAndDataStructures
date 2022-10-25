/**
 * @author Qine
 * @create 2021-07-12 21:58
 */
public class JosepfuTest {
    public static void main(String[] args) {
        //测试
        CircleSingleList circleSingleList = new CircleSingleList();
        circleSingleList.addBoy(5);
        circleSingleList.showBoy();
        circleSingleList.countBoy(1,2,5);
    }
}

//创建一个环形的单向链表
class CircleSingleList {
    //创建一个first节点，目前没有编号
    private Boy first;

    //添加一个小孩节点，构建成一个环形的链表
    public void addBoy(int nums) {
        //num做数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针，帮助构建环形链表
        //使用for创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy() {
        //判断链表是否为空
        if (first == null) {
            System.out.println("当前没有小孩");
            return;
        }
        //因为first不能动，使用辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.println("小孩的编号为" + curBoy.getNo());
            if (curBoy.getNext() == first) {//说明已经遍历完成
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    //根据用户的输入，计算出小孩出圈的顺序

    /**
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建一个辅助指针，帮助小孩出圈
        Boy helper = first;
        //需求helper事先指向环形链表的最后节点
        while (true) {
            if (helper.getNext() == first) {
                //说明helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，想让first和helper移动 k - 1 次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同时移动m - 1次，然后出圈
        //这是一个循环操作，直到圈中只有一个节点
        while (helper != first) {

            //让first和helper指针同时的移动countNum - 1
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号为%d\n",first.getNo());

    }
}
//创建一个Boy类，表示一个节点
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
