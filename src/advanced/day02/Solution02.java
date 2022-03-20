package advanced.day02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * [����P-0085] [2021�� 01�� 16��] ��ȯ ���� 
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AXcTN4PmL3_BC0Gg
 * @author woojin2.choi
 * @hint �÷��̵� ���� �˰����� �˰��ִ°�?
 */
public class Solution02 {
	
	private static int T;
	private static int N;	//��ȭ������
	private static int M;	//��ȯ�����������->�����迭
	private static int D;	//�����ϼ�
	private static int K;	//������->Ư����带 ��� �ٲܼ����ְ� �ȹٲܼ�������
	private static long[][][] dist;
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			dist = new long[N+1][N+1][2];	//�⺻ �÷��̵���ȿ��� �Ѱ��� ������ �߰��Ѵ� -> K�� ����Ұ��ΰ� ���Ұ��ΰ� �ΰ��� ���
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					dist[i][j][0] = Integer.MAX_VALUE;
					dist[i][j][1] = Integer.MAX_VALUE;
				}
			}
			
			for(int i=1; i<=M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				dist[x][y][0] = z;
				dist[y][x][0] = z;
				dist[x][y][1] = K;
				dist[y][x][1] = K;
			}
			
			for(int k=1; k<=N; k++) {
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=N; j++) {
						//K�� ������� �ʰ� �ִܰŸ�
						dist[i][j][0] = Math.min(dist[i][j][0], dist[i][k][0]+dist[k][j][0]);
						//K�� ����Ҷ��� �ִܰŸ� K�� ����ؼ� �߰�k���� ������ K�� �߰�k���ķ� ����Ѱ��߿��� �ּҰ��� ã�°���
						dist[i][j][1] = Math.min(Math.min(dist[i][j][1], dist[i][k][1]+dist[k][j][0]), dist[i][k][0]+dist[k][j][1]);
					}
				}
			}
			
			long res = 0;
			for(int i=1; i<=D; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				res += Math.min(dist[x][y][0], dist[x][y][1]);
			}
			
			bw.append("#").append(String.valueOf(tc)).append(" ").append(String.valueOf(res));
			bw.newLine();
			
		}
		bw.flush();
		bw.close();
		br.close();
	}
}

/*
 * �÷��̵� ���� ���� �˰����� N���� ��������� N���� ���������� ��� �ִܰ�θ� �� ã�� �˰����̰�, ��ȭ�� 3���������� Ǭ��
 * � �� ���� ������ �ִܰ�δ� � ������ K�� �����ų� ������ �ʰų��̴�.
 * ���������� �־ �����ϴ�, �׸��� D[i][j]�� �밢�� i==j�ΰ�찡 0�� �ƴ϶� ���� ����� �װ� ����Ŭ�� �ִٴ� ���̴�
 * A - K - B �� ��ó���ٰ� �����ϸ� 3�������� �Ʒ�ó�� ������.
 * for K ���İ��� K
 * 	for i ����ϴ� i
 * 	  for j �����ϴ� j
 * 	    D[ij] = min(D[ij], D[ik]+D[kj])
 */
