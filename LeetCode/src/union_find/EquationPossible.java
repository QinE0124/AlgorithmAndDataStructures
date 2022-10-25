package union_find;

/**
 * @author QinE
 * @create 2022-06-17 10:06
 */
public class EquationPossible {

    public boolean equationPossible(String[] equations) {
        //26个英文字母
        UnionFind uf = new UnionFind(26);
        //先让相等的字母行成连通分量
        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                uf.union(x - 'a', y - 'a');
            }
        }

        //检查不相等关系是否打破相等关系的连通性
        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                char x = eq.charAt(0);
                char y = eq.charAt(3);
                //如果想等关系成立，就是逻辑冲突
                if (!uf.connected(x - 'a', y - 'a'))
                    return false;
            }
        }

        return true;
    }
}
