package queue;

import java.util.Scanner;

/**
 * 使用数组模拟环形队列
 *
 * @author QinE
 * @create 2021-11-08 17:23
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        System.out.println("测试数据模拟环形队列的案例");

        //创建一个环形队列
        CircleArrayQueue queue = new CircleArrayQueue(4);
        //设置4，但其队列的有效数据最大是3
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收1个字符
            switch (key) {
                case 'a':
                    queue.enQueue(scanner.nextInt());
                    break;
                case 'g':
                    try {
                        int value = queue.edQueue();
                        System.out.println("成功从队列取出数据" + value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    loop = false;
                    scanner.close();
                    break;
                case 's':
                    queue.showQueue();
                    break;
                case 'h':
                    try {
                        int value = queue.getHead();
                        System.out.println(value);
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;

            }
    }
}


}

class CircleArrayQueue {

    private int front;//指向队列第一个元素
    private int rear;//指向队列最后一个元素的后一个位置，留出一个空间作为约束
    private int[] arr;

    public CircleArrayQueue(int maxSize) {
        arr = new int[maxSize];
    }

    private boolean isEmpty() {
        return front == rear;
    }

    private boolean isFull() {
        return (rear + 1) % arr.length == front;
    }

    /**
     * 入队列
     * @param value
     */
    public void enQueue(int value) {
        if (isFull()) {
            System.out.println("队列已满，无法添加");
        } else{
            arr[rear] = value;
            rear = (rear + 1) % arr.length;
        }

    }

    /**
     * 出队列
     * @return
     */
    public int edQueue() {
       if (isEmpty()) {
           throw new RuntimeException("队列空，不能出队列");
       } else {
           //保存值
           int value = arr[front];
           front = (front + 1) % arr.length;
           return value;
       }
    }

    /**
     * 显示队列所有数据
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("当前队列为空，无法遍历");
        } else {
            for (int i = front; i < front + validDataQuantity(); i++) {
                System.out.println(arr[i % arr.length]);
            }
        }
    }

    /**
     * 获取首位
     * @return
     */
    public int getHead() {
        if (isEmpty()) {
            throw new RuntimeException("当前队列为空，无法获取首位");
        }
        return arr[front % arr.length];
    }

    /**
     * 获取有效数据数量
     * @return
     */
    public int validDataQuantity() {
        return (rear + arr.length - front) % arr.length;
    }
}
