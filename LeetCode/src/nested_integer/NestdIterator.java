package nested_integer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author QinE
 * @create 2022-06-14 15:57
 */
public class NestdIterator implements Iterator<Integer> {

    private LinkedList<NestedIntegerImplementation> list;

    public NestdIterator(List<NestedIntegerImplementation> nestedList) {
        //不直接使用nestedList的引用，是因为不能确定它的底层实现
        //必须保证是LinkedList, 否则下面的addFirst会很低效
        list = new LinkedList<>(nestedList);
    }

    @Override
    public boolean hasNext() {
        //循环拆分列表元素，直到列表一个元素是整数类型
        while (!list.isEmpty() && !list.getFirst().isInteger()) {
            //当列表的第一个元素是列表元素时，进入循环
            List<NestedIntegerImplementation> first = this.list.removeFirst().getList();
            //将第一个列表打平，并按照顺序添加到开头
            for (int i = first.size() - 1; i >= 0; i--)
                list.addFirst(first.get(i));
        }

        return !list.isEmpty();
    }

    @Override
    public Integer next() {
        //hasNext方法保证了第一个元素一定是整数类型
      return list.removeFirst().getInteger();
    }
}
