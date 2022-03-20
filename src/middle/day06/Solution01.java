package middle.day06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * [����P-0023] ������ ������ ���� 
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AViVPEuwaJmojUH_&_menuId=AVUU732mAAHBC0c9&_menuF=true#
 * @author woojin2.choi
 * @hint ���Ͽ� ���ε� �˰����� �˰��ִ����� ������, getParent���� ��ξ����� �ؼ� find�Ұ�(���� ���� �Ҷ� �� ���ڰ� union�� �Ǿ������� �θ�迭�� ���� �ٸ��������Ƿ�)
 *
 */
public class Solution01 {
	
	private static int T;
	private static int N;
	private static int Q;
	
	private static int[] parent;
	
	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int i = 1; i <= T; i++) {
			N = Integer.parseInt(br.readLine());
			Q = Integer.parseInt(br.readLine());
			
			parent = new int[N+1];
			for(int j=0; j<=N; j++) {
				parent[j] = j;
			}
			
			int q, a, b, res=0;
			for(int j=1; j<=Q; j++) {
				st = new StringTokenizer(br.readLine());
				q = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				if(q==0) {
					union(a, b);
				} else {
					res += find(a, b);
				}
			}
			bw.append("#").append(String.valueOf(i)).append(" ").append(String.valueOf(res));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	
	private static void union(int a, int b) {
		int x = getParent(a);
		int y = getParent(b);
		
		// ���� ������ ������Ʈ
		if(x<y) {
			parent[y] = parent[x];
		} else {
			parent[x] = parent[y];
		}
		
	}
	
	// union������ �θ� �������鼭 ��ε� �����Ѵ�
	private static int getParent(int t) {
		if(parent[t] == t) {
			return t;
		}
		// ��ξ���
		return parent[t] = getParent(parent[t]);
	}
	
	private static int find(int a, int b) {
		int res=0;
		int x = getParent(a);	//ã������ ��ξ���
		int y = getParent(b);	//ã������ ��ξ���
		if(x==y) {
			res = 1;
		} else {
			res = 0;
		}
		return res;
	}
	
	
}

/*
 * union-find
 * 1. �θ�迭�� �����ϰ� �ڽ��� �ڱ� �ڽ��� �θ�� �����Ѵ�.(�ʱ�ȭ)
 * 2. union�ÿ��� �� ���� �θ� ���Ͽ� ��������(�Ǵ� ū����)�� �θ�� �θ�迭�� ������Ʈ �Ѵ� -> ���� �θ� �ȴ�.
 * 3. �θ� �������� getParent �޼ҵ� ȣ��ÿ��� ��ξ������� ����Ѵ�(��͸� ���� �θ� Ž���� ��������� �θ�迭�� ������Ʈ)
 * 4. find�ÿ��� �θ� �������� ����� getParent�� �̿��ؼ� ������� �ؼ� ������ �ڿ� ���� �񱳸� �ؾ��Ѵ�
*/
