package middle.day10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * [����P-0028] LCS
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AVmHHWGgkXKojUFy&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint �� ���ڿ� ���� MN����� ���� DP�� Ǯ��,
 *       LCS�� �迭���� N�� M�� ������ ���ʴ밢��+1, �ٸ��� �����̳� ���ʿ��� ū���� ��������
 *       �� ã���� LCS[N][M]�� ���� �ִ���� �̴ϱ�, ������ ���ڸ� ã�ư��� �ȴ�.
 *       ������ ���� NM���� ã�ư���, N�� M�� ������ �밢�� ���� ���� �ö󰡸鼭 ���ÿ� �� ���ڸ� �����ϰ�,
 *       �ٸ��� ���ʿ��� �Դ��� �����ʿ��� �Դ��� ���� �ش� ��ġ�� �̵��Ѵ�, 0�� ���������� ����ϸ� �������� LCS�� �Ѱ��� ã�� �� �ֵ�
 */
public class Solution01 {
	
	private static int T;
	private static int[][] LCS;
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc<=T; tc++) {
			String A = br.readLine();
			String B = br.readLine();
			int N = A.length();
			int M = B.length();
			
			char[] sentA = new char[N+1];
			char[] sentB = new char[M+1];
			
			for(int i=1; i<=N; i++) {
				sentA[i] = A.charAt(i-1);
			}
			for(int i=1; i<=M; i++) {
				sentB[i] = B.charAt(i-1);
			}
			
			LCS = new int[N+1][M+1];
					
			// LCS 0�������� ����0�̴�
			for(int i=1; i<=N; i++) {
				for(int j=0; j<=M; j++) {
					if(j==0) {
						LCS[i][j] = 0;
					} else {
						// ���ϴ� ���ڰ� ������쿡�� ���ʻ�ܰ� + 1����
						if(sentA[i] == sentB[j]) {
							LCS[i][j] = LCS[i-1][j-1]+1;
						} else {	// �ٸ���쿡�� ���ʰ� ���� �� �� ū�ɷ� ����
							LCS[i][j] = Math.max(LCS[i][j-1], LCS[i-1][j]);
						}
					}
				}
			}
			
			int LCS_length = LCS[N][M];
			
			char[] res = new char[LCS_length];
					
			int n = N;
			int m = M;
			int c = LCS_length-1;
			while(c >= 0) {
				//������ ���� ���� �ö󰡰� ���� char�� stack�� �ִ´�
				if(sentA[n]==sentB[m]) {
					res[c] = sentA[n];
					n--;
					m--;
					c--;
				} else {	//�ٸ��� ���ʿ��� �°��� ������ �°��� ã�Ƽ� �׸��� ����
					if(LCS[n][m-1] == LCS[n][m]) {	// ���ʿ��� �°��
						m--;
					} else {						// ������ �°��
						n--;
					}
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
LCS ���� ���� �κм����� ���� ���ϱ� ���ؼ���, DP�� Ȱ���Ͽ� Ǭ��
1. 0�� �� �տ� ������ �迭�� ����� -> �� ���ڿ� ��� �տ� 0���� 0�� �ִ´� -> �������� �����ϰų� ����, ������ �����Ҽ��ֱ⶧��
2. N M �迭�� ���ϸ鼭 O(NM)Ž���� �Ҳ���, �� ���ڰ� ������ �������� ����, �ٸ��� ���ʰ��̶� ���ʰ��̶� ���߿� ū���� �ְ� O(NM)Ž���� �Ѵ�.
3. LCS�� ������ ���� �ִ�� ���� LCS�� ���̰��� ���´�
4. �� ���� ��ŭ ũ���� �迭�� ���� LCS�� ���� ���̺��� ������ �����ذ��鼭 LCS ���ڿ� �Ѱ��� ������
5. N�̰� M�϶� �� ���ڰ� ������ ���� ���� �̵��ϰ� �ش� ���ڸ� �迭�� �ִ´�.
     �� ���ڰ� �ٸ���쿡�� ���ʿ��� �Դ���, ���ʿ��� �Դ��� Ȯ���ϰ� �� ��ġ�� �����͸� �ٿ��ش�.
     ���� ���̸�ŭ �迭�� ������ �����ϸ� �ȴ�.
     
���� 
1
ABCBDAB
BDCABA
���� LCS ���̺��� �Ʒ��� ����.

	0	A	C	A	Y	K	P
0	0	0	0	0	0	0	0
C	0	0	1	1	1	1	1
A	0	1	1	2	2	2	2
P	0	1	1	2	2	2	3
C	0	1	2	2	2	2	3
A	0	1	2	3	3	3	3
K	0	1	2	3	3	4	4

*/