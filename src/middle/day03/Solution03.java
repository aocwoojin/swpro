package middle.day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * [����A-0030] � Ʈ��
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AWm4ATkwhTiojUFD&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint 2��Ʈ���� �����(���ʺ�����������) �� ���� ���� ���� ���������� ���������� ������ �÷����鼭 ���Ͽ� ��ȯ�Ѵ�. 
 */
public class Solution03 {
	
	private static int[] MAX_HEAP;	// Max Heap ���� ����Ʈ���� �ڷᱸ�� ����
	private static int N;
	private static long LAST_INDEX;
	private static long LAST_DEPTH;
	
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<T; i++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			// �� �������� ���� �ִ�� ����
			MAX_HEAP = new int[1000001];
			
			st = new StringTokenizer(br.readLine());
			
			for(int j=1; j<=N; j++) {
				MAX_HEAP[j] = Integer.parseInt(st.nextToken());
			}
			
			getMaxHeap();
			
			bw.append("#").append(String.valueOf(i+1)).append(" ").append(String.valueOf(LAST_DEPTH)).append(" ").append(String.valueOf(LAST_INDEX));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int getMaxHeap() {
		
		int retVal = MAX_HEAP[1];	// �ƽ��� �ֻ�� ���� ����
		
		MAX_HEAP[1] = MAX_HEAP[N];	// ������ ���� �ƽ����� �ֻ����� �÷����� ���ϸ鼭 ��������
		MAX_HEAP[N] = 0;			// ������ ���� �ʱ�ȭ
		
		int i=1;
		int depth=1;
		while(true) {
			int childMax = Math.max(MAX_HEAP[i*2], MAX_HEAP[i*2+1]);	// ���� ����� �ڽĳ�� �ΰ��߿� ū��
			// �� �� �Ѱ��� ������ ũ�� 
			if(childMax > MAX_HEAP[i]) {
				if(childMax == MAX_HEAP[i*2]) {	// ������ �� ũ�� �����̶� ����ġ 
					swap(i, i*2);
					i = i*2;
				} else {						// �������� �� ũ�� �������̶� ����ġ
					swap(i, i*2+1);
					i = i*2+1;
				}
			} else {
				break;
			}
			depth++;
		}
		LAST_INDEX = i;			// �ֻ����������� �������ٰ� ������ ��� i
		LAST_DEPTH = depth-1;	// �׸��� �� ����, �������� ++�� �Ǿ�������ϱ� 1������
		
		for(int k=0; k<LAST_DEPTH; k++) {
			if(k==0) {
				LAST_INDEX = LAST_INDEX - 1; 
			} else {
				LAST_INDEX = LAST_INDEX - (long)Math.pow(2, k);	// �ε������� ������ĭ �����ö����� 2�� ������ŭ ���ָ� ���ʺ��� ���°���� ����Ҽ�����
			}
		}
		return retVal;
	}
	
	public static void swap(int a, int b) {
		int temp = MAX_HEAP[a];
		MAX_HEAP[a] = MAX_HEAP[b];
		MAX_HEAP[b] = temp;
	}

}
