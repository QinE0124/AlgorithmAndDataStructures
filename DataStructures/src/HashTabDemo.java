import java.util.Scanner;

/**
 * @author Qine
 * @create 2021-07-20 18:38
 */
public class HashTabDemo {
    public static void main(String[] args) {

        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("exit:退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
            }

        }
    }

}

//创建HashTab管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedArray;
    private int size;

    //构造器
    public HashTab(int size) {
        this.size = size;
        empLinkedArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp) {
        int empLinkedListNO = hashFun(emp.id);
        empLinkedArray[empLinkedListNO].add(emp);
    }

    //遍历所有的链表，遍历HashTab
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedArray[i].list(i);
        }
    }

    //根据输入的id，查找雇员
    public void findEmpById(int id) {
        //使用散列函数确定到哪条链上查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n", (empLinkedListNO + 1), id);
        } else {
            System.out.println("在哈希表中没有找到该雇员");
        }
    }

    //编写一个散列函数
    public int hashFun(int id) {
        return id % size;
    }
}
//创建EmpLinkedList，表示链表
class EmpLinkedList {
    private Emp head;

    //添加雇员到链表
    //假定，当添加雇员时，id是自增长
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {
            System.out.println("第 "+(no+1)+" 链表为空");
            return;
        }
        System.out.println("第 "+(no+1)+" 链表的信息为");
        Emp curEmp = head;
        while (true) {
            System.out.printf(" => id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //根据id查找雇员
    //如果查找到，就返回Emp，如果没有找到，就返回null
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;
            }
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}
//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;
    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}