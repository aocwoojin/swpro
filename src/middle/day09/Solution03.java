package middle.day09;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * [����P-0027] �⸧ ��
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AWGIpxTgwQOojUEb&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint �̹� ���ÿ� ������ ������ ���ÿ��� �˳��ϰ� ä���� �⸧���̶� ���� ���ÿ��� �߰��� �⸧�� �־������� 
 *       �⸧�� L�� ä���� �뷮���� �ּҰ��� ���س�����, �� �ּҰ��� �̿��ؼ� �� �ּҰ��� ���ϰ�... �ݺ�����
 */
public class Solution03 {
	
	private static int T;
	private static int N;	// ������ ��
	private static int L;	// �⸧�� �뷮
	private static int[] cost;	// ���ú� �⸧��
	private static int[] dist;	// �����ǰ� �̵��Ÿ� dist[0] = 0�̴� ù��° ���ÿ� �ö������� �Ÿ��ϱ� 0���� ����, dist[1] -> 0�� ���ÿ��� 1�� ���÷��� �Ÿ�
	private static int[][] DP;
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			cost = new int[N+1];	// 1�����ú��� ����
			dist = new int[N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<N; i++) {
				if(i!=N) {
					cost[i] = Integer.parseInt(st.nextToken());
					dist[i] = Integer.parseInt(st.nextToken());
				} else {
					cost[i] = Integer.parseInt(st.nextToken());
				}
			}
			
			DP = new int[N+1][L+1];
			for(int i=1; i<=L; i++) {
				DP[1][i] = cost[1]*i;
			}
			
			for(int i=2; i<=N; i++) {
				if(dist[i-1] > L) {	// ���� ���÷� �������� ���
					DP[N][0] = -1;
					break;
				}
				DP[i][0] = DP[i-1][dist[i-1]];
				
				for(int j=1; j<=L; j++) {
					DP[i][j] = DP[i][j-1]+cost[i];
					if(j+dist[i-1] <= L) {
						DP[i][j] = Math.min(DP[i][j], DP[i-1][j+dist[i-1]]);
					}
				}
			}
			
			bw.append("#").append(String.valueOf(tc)).append(" ").append(String.valueOf(DP[N][0]));
			bw.newLine();
			
		}
		bw.flush();
		bw.close();
		br.close();  
	}
}

/*
������ǲ �Ʒ��� ������ 25�� ���;���
1
4 8
3 5 1 3 2 6 7 

���� ������ �������� ǥ�� �׸���, ���� �⸧���� �ִ�� �������������� ���� ���ú� �⸧�� �ּҰ��� �����Ѵٰ� ��������
�ֳ�, ������� 2�� ���ÿ� ���������� �⸧�� 1L ���°�� ��� �ִ°� �ּҺ���ΰ�?
�׷� ���� 1�����ÿ��� 1L�� �� �ִ°��̶� 2�����ñ��� �ͼ� �⸧�� �޲����� 2�����ÿ��� 1L�� �ִ°� ������ �ϳ����� �� ���� �⸧���� ��°��ε�
�װ� ��ȭ������ ǥ���ϴ°��̴�.

DP[���絵��][�����⸧] = DP[��������][1L�ʰ��Ͽ�����] vs DP[��������][������������] + ���絵�ÿ��� 1L�ֱ�
�� ���߿��� ���������� DP�迭�� ������Ʈ �س�����, ���߿� DP�� DP[i][j]�� �����Ѻ����� ������ �ּҰ��ȴ�
�ֳĸ� �ּҷ� ���Ѱ� vs DP[��������][������������] + ���絵�ÿ��� 1L�ֱ� �̱� �����̴� -> DP�� ����

�� ������ ǥ�� �����ڸ�
	0	1	2	3	4	5	6	7	8
1	0	3	6	9	12	15	18	21	24	
2	15	16	17	18	19	20	21	22	23
3	18	19	20	21	22	23	25	27	29
4	25

DP[1][i]�� ù ������ ������ �ݾ׺��� 1~8L���� ä������ �ݾ��� �� �������̴�.
DP[2][0]�� �ι�° ���ÿ� ���������� "ù��°���ÿ��� 5L�� ä�������� ��"�̴�.
DP[2][1]�� ù��° ���ÿ��� 6L�־����� vs ù��°���ÿ��� 5L�ְ� �ι�°���ÿ��� 1L�־�����
                  ��, DP[1][6] vs DP[2][0]+��°����cost
DP[2][2]�� ù��° ���ÿ��� 7L�־����� vs ù��°���ÿ��� 5L�ְ� �ι�°���ÿ��� 2L�־�����
                  �ٵ� �̰� �ٷ� �տ� ����� DP[2][1]���� cost�ѹ� ���ѰŶ� �����ϱ�
  DP[i][j] = DP[i][j-1]+cost[i];
  if(j+dist[i-1] <= L) {
	DP[i][j] = Math.min(DP[i][j], DP[i-1][j+dist[i-1]]);
  }
  �̷� ��ȭ���� ���ü��ִ°��̴�
 �������� j+dist[i-1] �� ���� L�� �Ѿ�� ��찡 �ֵ�
 �̰� �����뷮�� �ʰ��ϴ� �ּҰ��� ���⶧���� �̰� �׳� �ٷ� ���� ���� ����Ǵ°��̴�. 
 DP[N][0]���� ���ϸ� N��° ���ÿ� ������ �⸧�� �ϳ��� ���� ���¸� �ּҷ� �����ϰ� �°��̴�
*/
