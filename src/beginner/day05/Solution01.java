package beginner.day05;

import java.util.Arrays;
import java.util.Comparator;

public class Solution01 {
	
	public static void main(String args[]) {
		Integer[] a = new Integer[10];
		
		for(int i=0; i<10; i++) {
			a[i] = i+1;
		}
		
		
		
		Arrays.sort(a, 5, 10, new Comparator<>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;	//������ ����� �ڸ��� �ٲ۴�, ������ ������ �ڸ��� �ٲ��� �ʴ´�
			}
		});
		
		for(int i=0; i<10; i++) {
			System.out.println(a[i] + " ");
		}
		
		//Arrays.sort(a, 5, 10, (o1, o2) -> o2-o1);	//���ٽ����� ǥ��
	}
	
}
