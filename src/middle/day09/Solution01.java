package middle.day09;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * [����P-0022] LIS 2
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AVhxH7BQpbuojUH_&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint DP���� ������, adhoc���� �����̴�,
 *       ��� ����Ǽ��� ���ϰ� �Ǹ� N^2�� ����ð��� �ҿ������, lis��� ���ĵ� �ӽù迭�� �����鼭 ������ �ִ밪�� ���غ��� ������� Ǫ�� ������
 *       https://shoark7.github.io/programming/algorithm/3-LIS-algorithms ����
 */
public class Solution01 {
	
	private static int T;
	private static int N;	// ������ ����
	private static int[] arr;	// �Է¹��� �迭
	private static int[] lis;	// LIS�� �ִ� ������ �������� �迭(�� �迭�� ������ ���� LIS�� �ƴ� �ܼ��� LIS�� ���� ���̸��� ���ϰ� �; ���� �迭��)
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			lis = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			lis[0] = arr[0];	// �Ǿ��� ���� �׳� �־��
			int i=1;	// arr�� ������(0���� lis�� �־����ϱ�)
			int j=0;	// lis�� ������
			
			while(i < N) {	//arr�迭 �� ���� ������
				if(lis[j] < arr[i]) {	// lis�迭�� ������������ ũ�� lis �ǵڿ� �ִ´�
					lis[j+1] = arr[i];
					j++;
					i++;
				} else if(lis[j] == arr[i]){	// ������ arr�����͸� �÷��ְ� ��ŵ
					i++;
				} else {						// ������ lis�迭�� ��ġ�� ã�ƿͼ� �ű�� �ְ� ��ü
					int position = binarySearch(0, j, arr[i]);
					lis[position] = arr[i];
					i++;
				}
			}
			
			int res = j+1;	// lis�迭�� ������+1���� lis�����ε� �̰� ���� �� ���� ��������ΰ��̴�
			
			bw.append("#").append(String.valueOf(tc)).append(" ").append(String.valueOf(res));
			bw.newLine();
			
		}
		bw.flush();
		bw.close();
		br.close();  
	}
	
	private static int binarySearch(int left, int right, int target) {
		
		int mid = 0;
		
		while(left<right) {
			mid = (left+right)/2;	// 2������ Ž���Ҳ��� Ÿ���� mid���� �������̸� left�� ���������� �ٿ��ְ� �ݴ�� right�� �������� �ٿ�����
			if(lis[mid] < target) {	// lis �迭�� ������ �Ǿ��ֱ� ������ lis[mid]�� ���� �����ͼ� �� ���� target���� ������ left�� mid�� �ö󰡼� �ٽ� Ž���Ѵ�
				left = mid+1;
			} else {				// lis�� ���ĵ� ���¿��� lis[mid]���� �����ͼ� Ÿ���� ���ʿ� ������ right���� mid�� �ٿ��ְ� �ٽ� Ž��
				right = mid;
			}
		}
		
		return right;	// left�� right���� Ŀ���Ŵϱ� right�� lis�迭�ȿ� ������ ��ġ�� �Ȱ��̴�
	}
	
	
}

