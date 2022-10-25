import java.util.HashMap;
import java.util.Map;

/*
 * 题目描述：给定一个长度为 n 的整型数组 arr 和一个整数 k(k>1) 。
 * 已知 arr 中只有 1 个数出现一次，其他的数都出现 k 次。请输出只出现了 1 次的数。
 * 示例1
 * 输入：[5,4,1,1,5,1,5],3 
 * 输出：4 
 * 示例2
 * 输入：[2,2,1],2
 * 输出：1

 */
public class CountTest {
	
	public static void main(String[] args) {
		
		int[] arr = new int[] {5,4,1,1,5,1,5};
		int number = getNumber(arr, 3);
		System.out.println(number);
		
	}
	
	/**
	 * 
	  * @date 2021年10月21日下午12:53:40
	  * @param arr
	  * @param k
	  * @return 如果存在则次数为1的数则返回此数，不存在则返回
	  *
	 */
	private static int getNumber(int[] arr, int k) {
	
		
		HashMap<Integer, Integer> map = getCount(arr);

		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getKey() == 1) {
				
				return entry.getValue();
				
			}
		}
		
		return Integer.MIN_VALUE;
		
	}
	
	private static HashMap getCount(int[] arr) {
		HashMap<Integer, Integer> counts = new HashMap<>();
		int count = 0;
		int temp = 0;
		for (int i = 0; i < arr.length; i++) {
			temp = arr[i];
			for (int j = 0; j < arr.length; j++) {
				if (arr[j] == temp) {
					count++;
				}
		}
			counts.put(count, temp);
			//置0
			count = 0;
		
		}
			
		return counts;
		
	}

}
