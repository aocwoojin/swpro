package middle.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * ���� �׽�Ʈ 
 * �ð� ���� : 10���� Test Input �Է½� C/C++ 1�� / Java 1��
 * �޸� ���� : Stack : 1 Mbytes  /  Total : 256 Mbytes
 * 
 * [����]
 * �ҵ��� ��ũ���� �� �����̴�! �� ���� K������ �ҵ��� N���� �������� �ϳ��� ���������� Ǯ�� ��� �ִ�. 
 * �� ���������� ������1 �� ������ N�̶�� �������. �� ���������� M���� �ܹ��� ��� ����Ǿ� �ִ�. (��� �浵 ������� �������� ���� �ʴ�.)
 * �ҵ��� �׵��� ��ũ�е��� ���� ���������� ���̱⸦ ���Ѵ�. ������ ����� �ҵ��� ��� �������� ������ �� ���� ���ɼ��� �ִ�.(�ܹ��� �����̱� ������) 
 * �ҵ��� ���� �󸶳� ���� ���������� ��� �ҵ��� ���� �� �ִ��� ���������.
 * 
 * [�Է�]
 * ù ��° �ٿ� �׽�Ʈ ���̽��� ���� T�� �־�����. �̾ T�� ���̽��� �Է°��� �־�����. 
 * �� �׽�Ʈ ���̽��� ù��° ���� ������ ������ �������� ���еǾ� �ԷµǾ�����. K(1 <= K <= 100),N(1 <= N <= 1,000),M(1 <= M <= 10,000)
 * ���̽��� �� ��° ���� K+1��° �ٱ����� K������ �ҵ��� ó�� Ǯ�� ����ִ� ��ġ�� �� �ٿ� �ϳ��� �־�����.
 * K+2��° �ٺ��� M+K+1��° �ٱ����� �ܹ��� ������ ���� ������S �� ����E�� �Է����� �־�����.
 * 
 * [���]
 * ������ �׽�Ʈ ���̽��� ���Ͽ� #x(x�� �׽�Ʈ ���̽� ��ȣ�� �ǹ�)�� ����ϰ� ������ �ϳ� �� ���� ��� �Ұ� ���� �� �ִ� ������ �������� ���� ����Ѵ�.
 * 
 * [����� ��]
 * (�Է�)
 * 1
 * 2 4 4
 * 2
 * 3
 * 1 2
 * 1 4
 * 2 3
 * 3 4
 * 
 * (���)
 * #1 2
 * 
 * [��Ʈ]
 * ��� �ҵ��� ������ 3,4���� ���� �� �ִ�.
 */
public class Solution01 {
	
	private static int T;
	private static int K;
	private static int N;
	private static int M;
	private static int[] cows;	// �ҵ��� �ִ� ������ ��ġ�� ������
	private static int[] visited;
	private static int[] cnts;
	
	private static int[][] dir;	// ����

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// �ҹ迭
			cows = new int[K+1];
			// ������ �湮Ƚ��
			cnts = new int[N+1];
			// ������������
			dir = new int[N+1][N+1];
			
			// �Ұ� �ִ� ������ ���� �Է� cows[1]=3 �̸� 1���Ұ� 3�� �������� �ִ�
			int cp=1;
			for(int j=0; j<K; j++) {
				cows[cp] = Integer.parseInt(br.readLine());
				cp++;
			}
			
			for(int j=0; j<M; j++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				dir[s][e] = 1;
			}
			
			
			// ��� �Ҹ� ���鼭 visited�� ++�Ѵ�
			for(int j=1; j<cows.length; j++) {
				visited = new int[N+1];
				bfs(cows[j]);
			}
			
			int res = getMaxCnt();
			
			bw.append("#").append(String.valueOf(i+1)).append(" ").append(String.valueOf(res));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void bfs(int node) {
		Deque<Integer> queue = new ArrayDeque<>();
		queue.add(node);
		visited[node] = 1;
		cnts[node]++;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			 
			for(int i=1; i<=N; i++) {
				if(visited[i] == 0 && dir[now][i] == 1) {
					queue.add(i);
					visited[i] = 1;
					cnts[i]++;
				}
			}
		}
	}
	
	private static int getMaxCnt() {
		
		Arrays.sort(cnts);
		int res=0;
		int maxval = cnts[cnts.length-1];
		for(int i=cnts.length-1; i>=1; i--) {
			if(cnts[i] == maxval) {
				res++;
			} else {
				break;
			}
		}
		
		return res;
	}

}
