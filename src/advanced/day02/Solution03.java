package advanced.day02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * �˰��� ������ȸ
 * @author woojin2.choi
 *
 */
public class Solution03 {
	
	private static int T;
	private static int N;
	private static int[] Gi;
	private static int offset=1;
	private static int[] tree;
	private static long[] sum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			
			//�ε��� Ʈ���� ���ϱ� ���� �������� ���Ѵ�.
			while(offset < N) {
				offset = offset*2;
			}
			
			tree = new int[offset*2];	//Ʈ������=�������ǵι�
			
			sum = new long[N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				int ai = Integer.parseInt(st.nextToken());
				updateGcdTree(i, ai);	//�Է¹����鼭 Ʈ���� ����
				sum[i+1] = ai + sum[i];
			}
			
			Gi = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				Gi[i] = Integer.parseInt(st.nextToken());
			}
			
			long ans=0;
			for(int i=0; i<N; i++) {
				int l = Math.max(i-Gi[i], 0);
				int r = Math.min(i+Gi[i], N-1);
				int gcd = queryGcdTree(l, r);
				ans += (sum[r+1]-sum[l])/gcd;
			}
			
			bw.append("#").append(String.valueOf(tc)).append(" ").append(String.valueOf(ans));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();  
		
	}
	
	
	// ��Ŭ���� ȣ����
	// a�� b�߿��� ū���� a�� �������� b�� �ϰ� ��� ������, �������� 0�̵Ǹ� �����¼�(b)�� �ִ������̴�
	private static int euclid(int a, int b) {
		int gcd = 0;
		if(a==0 && b!=0) return gcd=b;
		if(a!=0 && b==0) return gcd=a;
		
		if(a<b) {
			int temp = a;
			a = b;
			b = temp;
		}
		int res = 0;
		
		while(true) {
			res = a%b;
			if(res == 0) {
				gcd = b;
				break;
			} else {
				a = b;
				b = res;
			}
		}
		return gcd;
	}
	
	// x��° ��忡 v��� ���� �ְ� ���׸�Ʈ Ʈ���� ������Ʈ �Ѵ�
	private static void updateGcdTree(int x, int v) {
		int node = offset+x;	// ex) 2�� ������ ���� segTree������ 9�� ��尡 ��
		tree[node] = v;
		for(int i=node/2; i>=1; i=i/2) {
			tree[i] = euclid(tree[i*2], tree[i*2+1]);
		}
	}
	
	private static int queryGcdTree(int l, int r) {
		int left = l+offset;
		int right = r+offset;
		int res = 0;
		for(; left<=right; left/=2,right/=2) {	// ���ʰ� �������� ������������ �ϴµ�, �ݾ� �������� ����Ʈ�� �θ�� �̵��ϰ� �ǹǷ�, ��ź�� ��ġ ���ϰ���
			if(left%2 == 1) {
				res = euclid(res, tree[left++]);
			}
			if(right%2 == 0) {
				res = euclid(res, tree[right--]);
			}
		}
		return res;
	}
}
