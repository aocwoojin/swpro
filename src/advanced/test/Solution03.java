package advanced.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * [����P-0008] ���� 
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AVxCk7Mw2tmojUGq#
 * @author woojin2.choi
 * @hint union-find�� ���°��� ����. ���� ��� ���������� ������ �� �Է¹ް���, ���°��� �մ°����� ������ �����ϸ� �ȴ�.
 *       1) Ȯ���Ѵ� 2) �����Ѵ� 3) Ȯ���Ѵ� �� 
 *       3) Ȯ���Ѵ� 2) �մ´� 1) Ȯ���Ѵ� ������ ���Ͽ����ε带 ����ϸ� �˴ϴ�.
 */
public class Solution03 {
	
	private static int T;
	private static int N;
	private static int M;
	private static int Q;
	private static int[] parent;
	private static int[][] adjArray;
	private static int[][] querys;
	private static int[] ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			//�θ�迭�� �ʱ�ȭ, ���� �θ�� �ڱ��ڽ��� ����Ű�� �־���Ѵ�.
			parent = new int[N+1];
			for(int i=1; i<=N; i++) {
				parent[i] = i;
			}
			
			//M���� ���������� �ϴ� ����
			adjArray = new int[M+1][3];
			
			//���������� �����Ѵ�.
			for(int i=1; i<=M; i++) {
				st = new StringTokenizer(br.readLine());
				adjArray[i][0] = Integer.parseInt(st.nextToken());
				adjArray[i][1] = Integer.parseInt(st.nextToken());
				adjArray[i][2] = 1;	//�� ������ �ϴ� union�� �����̴�, 0�̸� union���� �ʵ����Ѵ�. �� ���� Q���� �ְԵȴ�
			}
			

			
			
			//�������� ����
			Q = Integer.parseInt(br.readLine());
			
			querys = new int[Q+1][3];
			for(int i=1; i<=Q; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b;
				int c;
				if(a==2) {	//����Ǿ��°�?
					b = Integer.parseInt(st.nextToken());
					c = Integer.parseInt(st.nextToken());
					querys[i][0] = a;
					querys[i][1] = b;
					querys[i][2] = c;
				} else {	//��������, �ݴ�� ���ϸ� ���߿� ������
					b = Integer.parseInt(st.nextToken());
					querys[i][0] = a;
					querys[i][1] = b;
					adjArray[b][2] = 0;	//�ʱ� union�Ҷ� �ش� �κ��� union���� �ʵ��� ����
				}
			}
			
			ans = new int[Q+1];
			
			for(int i=1; i<=M; i++) {
				if(adjArray[i][2] == 1) {	// ������ �ֵ鸸 ���� �����ϰ� ������ ���鼭 �߰��� union����
					union(adjArray[i][0], adjArray[i][1]);
				}
			}
			
			bw.append("#").append(String.valueOf(tc)).append(" ");
			// ������ �������� ���鼭 ���� ���ٷ� ���ϰ�, ���� ����� ������ union���ش�
			for(int i=Q; i>=1; i--) {
				int a = querys[i][0];
				int b = querys[i][1];
				int c = querys[i][2];
				
				if(a==2) {
					ans[i] = find(b, c);
				} else {
					union(adjArray[b][0], adjArray[b][1]);
					ans[i] = -1;
				}
			}
			
			
			for(int i=1; i<=Q; i++) {
				if(ans[i] != -1) {
					bw.append(String.valueOf(ans[i]));
				}
			}
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void union(int a, int b) {
		int x = getParent(a);
		int y = getParent(b);
		
		if(x<y) {
			parent[y] = parent[x];
		} else {
			parent[x] = parent[y];
		}
	}

	private static int getParent(int a) {
		if(parent[a] == a) {
			return a;
		}
		return parent[a] = getParent(parent[a]);	//��ξ���
	}
	
	private static int find(int a, int b) {
		int res = 0;
		int x = getParent(a);
		int y = getParent(b);
		if(x==y) {
			res = 1;
		} else {
			res = 0;
		}
		return res;
	}
}
