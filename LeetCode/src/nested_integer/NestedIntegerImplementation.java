package nested_integer;

import java.util.List;

/**
 * NestedInteger实现
 * @author QinE
 * @create 2022-06-14 15:32
 */
public class NestedIntegerImplementation {
    private Integer val;
    private List<NestedIntegerImplementation> list;

    public NestedIntegerImplementation(Integer val) {
        this.val = val;
        this.list = null;
    }

    public NestedIntegerImplementation(List<NestedIntegerImplementation> list) {
        this.list = list;
        this.val = null;
    }

    //如果里面存的是一个整数，则返回true，否则返回false
    public boolean isInteger() {
        return val != null;
    }

    //如果其中存的是一个整数，则返回这个整数，否则返回null
    public Integer getInteger() {
        return this.val;
    }

    //如果其中存的是一个列表，则返回这个列表，否则返回null
    public List<NestedIntegerImplementation> getList() {
        return this.list;
    }
}
