package middle.day08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * [����P-0006] ���� �� �� ����
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AViorrEgkKmojUH_&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint �÷��̵� ���� �˰����� �˰��ִ°�?
 */
public class Solution03 {
	
	private static int T;
	private static int N;
	private static long[][] dist;
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			dist = new long[N+1][N+1];
			
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=N; j++) {
					dist[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int k=1; k<=N; k++) {
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=N; j++) {
						dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
					}
				}
			}
			
			long res = 0;
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					res = Math.max(res, dist[i][j]);
				}
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
