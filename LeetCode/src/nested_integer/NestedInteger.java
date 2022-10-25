package nested_integer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 数据结构：NestedInteger
 * 其中存的数据可能是一个Integer整数，也可能是一个NestedInteger列表
 *
 * 算法会被输入一个NestedInteger列表，需要做的就是写一个迭代器，
 * 将这个带有嵌套结构的NestedInteger的列表拍平
 * @author QinE
 * @create 2022-06-14 15:19
 */
public class NestedInteger implements Iterator<Integer> {

    private Iterator<Integer> iterator;

    //构造器输入一个NestedInteger列表
    public NestedInteger(List<NestedIntegerImplementation> nestedList) {
        //存放将nestedList打平的结果
        LinkedList<Integer> result = new LinkedList<>();
        for (NestedIntegerImplementation node : nestedList) {
            //以每个节点为根节点遍历
            traverse(node, result);
        }
    }

    /*遍历以root为根节点的多叉树，将叶子节点的值存入result*/
    private void traverse(NestedIntegerImplementation root, LinkedList<Integer> result) {
        if (root.isInteger()) {
            //到达叶子结点
            result.add(root.getInteger());
            return;
        }

        //遍历框架
        for (NestedIntegerImplementation child : root.getList())
            traverse(child, result);

    }

    //返回下个整数
    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    //判断下个元素是否存在
    @Override
    public Integer next() {
        return iterator.next();
    }
}
