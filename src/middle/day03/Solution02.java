package middle.day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * [����P-0019] ������ 
 * http://182.193.11.65/common/satti/practiceProblem.do?problemId=AVgFI19QAViojUEZ
 * @author woojin2.choi
 * @hint ���׸�Ʈ Ʈ���� ���ϴ� ����, ��ͷ� �ڵ带 ©�����ְ� bottom up ������� �ڵ带 �����Ҽ����ִµ�, �̰� ��ͷ�����
 */
public class Solution02 {
	
	static int[] arr;
	static int[] segtree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			//int segLength = ((int)Math.pow((int)(Math.sqrt(N))+3, 2))*2;
			int segLength = 300000;	//�ִ� �迭�� ũ�⸦ ���Ҽ������ �� ũ������... 
			
			arr = new int[N+1];
			// �迭 �ʱ�ȭ
			for(int j=1; j<=N; j++) {
				arr[j] = j;
			}
						
			segtree = new int[segLength+1];	// ���尡��� �������� �ι�
			
			init(1, N, 1);
			
			st = new StringTokenizer(br.readLine());
			int Q = Integer.parseInt(st.nextToken());
			long result=0;
			
			for(int k=0; k<Q; k++) {
				st = new StringTokenizer(br.readLine());
				int C = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(C==0) {
					int dif=0;
					if(arr[a] > b) {
						dif = (arr[a]-b)*-1;
					} else {
						dif = (b-arr[a]);
					}
					arr[a] = arr[a]+dif;
					update(1, N, 1, a, dif);
				} else {
					result += sum(1, N, 1, a, b);
				}
				
			}
			
			if(result<0) {
				result = (result%1000000007)+1000000007;
			} else {
				result = result%1000000007;
			}

			bw.append("#").append(String.valueOf(i+1)).append(" ").append(String.valueOf(result));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int init(int start, int end, int node) {
		if(start == end) {
			return segtree[node] = arr[start];
		}
		
		int mid = (start+end)/2;
		
		//�κ��� ���׸�Ʈ Ʈ���� ���ϴ°ŹǷ� ��������� �κκ����� ���������� ���� ���׸�Ʈ Ʈ�����Ѵ�.
		return segtree[node] = init(start, mid, node*2) + init(mid+1, end, node*2+1);
	}
	
	//left,right�� �κ� ���� ���ϰ��� �ϴ� ����
	public static long sum(int start, int end, int node, int left, int right) {
		
		//���׸�Ʈ Ʈ�� ���� �ۿ� �ִ°��
		if(left > end || right < start) {
			return 0;
		}
		
		//start,end�� �κ��� �������� ������ ���׸�Ʈ�� left�� right�� �� �����ԵǴ°�쿡 ����
		if(left <= start && end <= right) {
			return segtree[node];
		}
		
		int mid = (start+end)/2;
		
		return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2+1, left, right);
	}
	
	//segtree �����ؼ� ���̳��� ���� ��庰�� ������Ʈ
	public static void update(int start, int end, int node, int index, int dif) {
		if(index < start || index > end) {
			return;
		}
		
		segtree[node] += dif;
		if(start == end) {
			return;
		}
		
		int mid = (start+end)/2;
		
		update(start, mid, node*2, index, dif);
		update(mid+1, end, node*2+1, index, dif);
	}

}
