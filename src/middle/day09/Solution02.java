package middle.day09;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * [����A-0012] ���� ����
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AVmHH4Ngkb-ojUFy&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint ���⼭���Ͱ� ��¥ DP�ΰ���, ��ȭ���� ��������
 *       �ִ� ����ġ�� �� �� �ִ� ���� �����ϴ� 2���� �迭�� D[N][M]�� N�� ������ ��ȣ�̰� M�� ������ �����̴� -> ����?
 *       ������ ��ȣ���� �þ�鼭 �ִ밪��ġ�� �����Ҳ���, i��° ������ ��°�� vs i��° ������ �ȴ�°�� -> ������ ���� D[N][M]�� ����(DP�� �⺻�� �̹��� �ִ밪�̸� ������ �ִ뿴�����̴�) 
 */
public class Solution02 {
	
	private static int T;
	private static int N;
	private static int M;
	private static long[][] arr;	// (1 �� C[i] �� 10^9, 1 �� W[i] �� 10,000)
	private static long[][] DP;		// C�� ���� ������ Ŀ���ϱ� long���� ����
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// N���� ����
			M = Integer.parseInt(st.nextToken());	// �ִ밡�빫�� M
			
			arr = new long[N+1][2];
			
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());	// C[i]
				arr[i][1] = Integer.parseInt(st.nextToken());	// W[i]
			}
			
			DP = new long[N+1][M+1];	// ���� ����ġ �ִ밪�� ���ذ� DP�迭�̴�
			
			calcDP();
			
			
			bw.append("#").append(String.valueOf(tc)).append(" ").append(String.valueOf(DP[N][M]));
			bw.newLine();
			
		}
		bw.flush();
		bw.close();
		br.close();  
	}
	
	private static void calcDP() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {	// ���� ���õ� i������ ���Ը�ŭ �������¿��� (i-1���� ���� �ִ밪+i�� ������ġ)�� ���� i��°�� ������ ���þ��Ѱ�찪���� ũ�� ������Ʈ�Ѵ�.  
				if(j-arr[i][1] >=0) {	
					// ���� ������ ���԰� ������ ũ�⺸�� ������쿡��  -> j-arr[i][1] >=1
					// ���纸�� �����ڸ��� ����� ������ �ִ밪��ġ ���� ���纸���� ����ġ���� ���Ѱ���, -> DP[i-1][j-W[i]]+C[i]
					// ���纸���� �������� �����ͺ��� ũ�� �ִ밪�� �������ش� -> DP[i][j] = Math.max(DP[i-1][j], DP[i-1][j-W[i]]+C[i])
					DP[i][j] = Math.max(DP[i-1][j], DP[i-1][j-(int)arr[i][1]]+arr[i][0]);
				} else {
					DP[i][j] = DP[i-1][j];	
				}
			}
		}
	}
	
}

