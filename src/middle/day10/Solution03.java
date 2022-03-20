package middle.day10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * [����P-0019] Matrix Chain Multiplication
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AVi9-viw4LyojUH_&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint �޸������̼��� �̿��� ���ȣ�������� Ǯ�� �� ����, DP�迭�� for���� �̿��Ϸ��� DP�迭�� �� �׾ƾ��Ѵ�.
 *       �޸������̼��� DP[1,4] = DP[1,1]*DP[2,4] or DP[1,2]*DP[3,4] or DP[1,3]*DP[4,4] �̷������� ���µ�, �� ��������� ĳ���� �̿��ؼ�
 *       ��Ϳ����� �ٿ��ش�
 */
public class Solution03 {
	
	private static int T;
	private static int n;		//����� ����
	private static long[] P;	// ��İ��� ���������ϴ� �迭
	private static long[][] memo;	// �޸������̼��� ���� �迭
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc<=T; tc++) {
			n = Integer.parseInt(br.readLine());
			
			P = new long[n+2];
			memo = new long[n+1][n+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=n+1; i++) {
				P[i] = Integer.parseInt(st.nextToken());
			}
			
			long res = getMCM(1, n);
			
			bw.append("#").append(String.valueOf(tc)).append(" ").append(String.valueOf(res));
			bw.newLine();
			
		}
		bw.flush();
		bw.close();
		br.close();  
	}
	
	private static long getMCM(int s, int e) {
		if(s==e) {
			return 0;
		}
		
		if(memo[s][e] != 0) {
			return memo[s][e];
		}
		
		long res=Integer.MAX_VALUE;
		for(int k=s; k<e; k++) {
			long t = getMCM(s, k) + getMCM(k+1, e) + P[s]*P[k+1]*P[e+1];
			res = Math.min(t, res);
		}
		memo[s][e] = res;
		return res;
	}
}

/*
���ȣ���� ��ͽ��� ���غ����Ѵ�.
A1 = 10*100
A2 = 100*5
A3 = 5*50
A4 = 50*20
�̷��� �����ϸ�
P�迭��
P = {0,10,100,5,50,20}���� ����

D[1,4] = MIN((D[1,1]�� D[2,4]) or (D[1,2]�� D[3,4]) or (D[1,3]�� D[4,4]))
D[1,1] + D[2,4] = D[1,1] + D[2,4] + P1*P2*P5 -> Ps*pk+1*pe
D[1,2] + D[3,4] = D[1,2] + D[3,4] + P1*P3*P5
D[1,3] + D[4,4] = D[1,3] + D[4,4] + P1*P4*P5
���� ���� ������ �� �����Ƿ�
K���� �����Կ� ���� ��ͷ� ȣ�� ��, ���� ���� ���� �����Ѵ�.
�׷��� ��� ��͸� ����Ÿ�ԵǸ� ��귮�� ��������.
���⼭ �޸������̼��� �ִ´�.

1. �������� MCM(int s, int e)����
   s == e �ΰ�쿡�� 0���� �����ϸ� �ȴ�.
2. �޸������̼�
   memo[s][e]�� ���� �ִٸ� �װɾ���, ���̻� ��͸� Ÿ������
3. �޸��� ���� ������ ó�� ���ϴ°��̴ϱ� for�� Ÿ�鼭 k���� ���� �ڸ��鼭 �ּҰ��� ���غ���

*/
