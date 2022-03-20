package advanced.day02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * [����P-0086] [2021�� 01�� 17��] �ް���ȹ  
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AXcTOzQ2MK_BC0Gg
 * @author woojin2.choi
 * @hint �ް����� K���̴ϱ�, 0~K���� �������հ� K+1~M������ ���������� ���� ū���� ã�´�
 *       ������DP�� ������DP�� ���� ���ؼ� �� ���� ���� ���� ū ���� ������ �ȴ�.
 */
public class Solution01 {
	
	private static int T;
	private static int N;
	private static int M;
	private static int K;
	
	private static Pjt[] P;	//������Ʈ ����
	private static long[] fD;
	private static long[] bD;
	
	private static class Pjt{
		int s;	//��������
		int e;	//��������
		int c;	//��������
		Pjt(int s, int e, int c){
			this.s = s;
			this.e = e;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//������Ʈ�� ����
			M = Integer.parseInt(st.nextToken());	//���ϴ±Ⱓ
			K = Integer.parseInt(st.nextToken());	//�ް��Ⱓ
			
			P = new Pjt[N+1];
			
			
			fD = new long[M+2];
			bD = new long[M+2];
			
			//������Ʈ ���� �Է¹ޱ�
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				P[i] = new Pjt(s, e, c);
			}
			
			long ans = 0;
			
			Arrays.sort(P, 1, P.length, new Comparator<Pjt>() {
				@Override
				public int compare(Pjt o1, Pjt o2) {
					return o1.e - o2.e;
				}
			});
			for(int i=1, j=1; i<=M; i++) {
				fD[i] = fD[i-1];
				for(; j<=N && P[j].e==i; j++) {	//������ i�Ͽ� ������ ������ ������ �ֵ��� ���� �ִ밪�� �����Ѵ�.
					fD[i] = Math.max(fD[i], fD[P[j].s-1]+P[j].c);
				}
			}
			fD[M+1] = fD[M];
			
			bD[M+1] = 0;
			Arrays.sort(P, 1, P.length, new Comparator<Pjt>() {
				@Override
				public int compare(Pjt o1, Pjt o2) {
					return o2.s - o1.s;
				}
			});
			for(int i=M, j=1; i>0; i--) {
				bD[i] = bD[i+1];
				for(; j<=N && P[j].s==i; j++) {
					bD[i] = Math.max(bD[i], bD[P[j].e+1]+P[j].c);
				}
			}
			
			bD[0] = bD[1];
			
			for(int i=1; i<=M-K+1; i++) {
				ans = Math.max(ans, fD[i-1]+bD[i+K]);
			}
			
			bw.append("#").append(String.valueOf(tc)).append(" ").append(String.valueOf(ans));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();  
		
	}
	
	
	
}
