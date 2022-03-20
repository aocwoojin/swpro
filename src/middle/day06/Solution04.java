package middle.day06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * [����A-0002] Ű����
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AVW5D7ywA9qojUHl&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint dfs�� ������(1)�� ������(-1)�� �����ϸ鼭 ������+������ ���� N-1�ΰ�찡 �ڽ��� Ű ��ġ�� �˼��ִ� �����
 */
public class Solution04 {
	
	private static int T;
	private static int N;
	private static int M;
	private static int[][] adjMat;
	private static boolean[] visited;
	private static int man;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int i = 1; i <= T; i++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			// ������� �ʱ�ȭ(N�� 500�����ϱ� ��ĵ� ������)
			adjMat = new int[N+1][N+1];
			
			for(int j=1; j<=M; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMat[a][b] = -1;	// Ű �������� a->b -1�� �صθ� dfs�ϸ鼭 -1�ΰ͵鸸 ã���� �ȴ�.
				adjMat[b][a] = 1;	// Ű ū���� b->a 1�� �صθ� dfs�ϸ鼭 1�ΰ͵鸸 ã�����
			}
			
			int res=0;
			
			for(int j=1; j<=N; j++) {
				visited = new boolean[N+1];
				
				man = 0;
				
				dfs(i, 1); //���� ������ Ű���� ��� ī��Ʈ
				dfs(i, -1); //���� ������ Űū ��� ī��Ʈ
				
				if(man-1 == N) {
					res++;	// ���� �����ϰ� Űū��� Ű���� ��� �� ��� man�� �־��µ� N-1�� ������ ���� Ű�� ���°������ �˼��ֵ�
				}
			}
			
			bw.append("#").append(String.valueOf(i)).append(" ").append(String.valueOf(res));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void dfs(int node, int direction) {
		visited[node] = true;	//�湮�ߵ��� üũ����
		
		man++;	//����� ���� -> ���� ���Ե�
		
		// ���� ����� ���� ��� Ž��
		for(int i=1; i<=N; i++) {
			if(visited[i]==false && adjMat[node][i] == direction) {
				dfs(i, direction);
			}
		}
	}
}

/*
DFS�� ������ ���������� ������ ������ ���������+������ ū����� -1 �̸� ��Ű�� �ƴ»����
�̰� ��� �����س�? 
*/