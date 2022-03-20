package beginner.day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution01 {
	
	static int[][] matrix;
	static int[] visited;
	static int[] result;
	static int max;
	static int cnt;
	static int maxDepth;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String T = br.readLine();
		
		for(int i=0; i<Integer.parseInt(T); i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			matrix = new int[N+1][N+1];
			visited = new int[N+1];
			result = new int[N+1];
			
			
			for(int j=0; j<M; j++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());

				matrix[s][e] = 1;
			}
			
			for(int j=1; j<visited.length; j++) {
				maxDepth = 1;
				dfsR(j, 1);
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#"+(i+1));
			for(int j=1;j<result.length;j++) {
				sb.append(" "+result[j]);
			}
			sb.append("\n");
			System.out.println(sb);
			
		}
	}
	
	public static void dfsR(int index, int depth) {
		
		if(maxDepth < depth) {
			maxDepth = depth;
			
		}
		
		//�ڽĳ�尡 ������ ��� -> ����� ��尡 �ִ���
		//�ڽĳ�尡 ������ ���� ���(+1����)�ϰ� ����
		
		for(int i=1; i<matrix[index].length; i++) {
			if(matrix[index][i] == 1) {	// ����� ��尡
				dfsR(i, depth+1);			// �ű⸦ �湮
			}
		}
		
		result[index] = maxDepth;
	}
}
