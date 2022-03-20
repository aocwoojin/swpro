package middle.day10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * [����A-0010] ���� ū ���簢��
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AVmHHkRQkYOojUFy&_menuId=AVUU732mAAHBC0c9&_menuF=true#
 * @author woojin2.choi
 * @hint ���簢���� ũ�⸦ �����ϴ� ����� ���������� �� ������ ������ ��� 1�ΰ�쿡�� ���� �����ٰ� 2�� ������ 2¥�� ���簢���� �����°� �̿�����
 *       1�� 0�� ����� �Է��غ���
 */
public class Solution02 {
	
	private static int T;
	private static int N;
	private static int M;
	private static int[][] mat;
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc<=T; tc++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			mat = new int[N][M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					int t = Integer.parseInt(st.nextToken());
					mat[i][j] = (t==1) ? 0 : 1; 
				}
			}
			
			int max = 0;
			
			// ������ܺ��� �����ؼ� �� ������ �� �߿��� �ּҰ�+1�� ���翡�ٰ� ������Ʈ, �� ���簡 ���� 1�ΰ�츸
			for(int i=1; i<N; i++) {
				for(int j=1; j<M; j++) {
					if(mat[i][j] == 1) {
						int a = mat[i-1][j-1];
						int b = mat[i-1][j];
						int c = mat[i][j-1];
						int min = Math.min(Math.min(a, b), c)+1;	// ������� ���� �� ���߿� �� ���� ���� +1�� ����
						mat[i][j] = min;
						if(min > max) {
							max = min;
						}
					}
				}
			}
			
			bw.append("#").append(String.valueOf(tc)).append(" ").append(String.valueOf(max));
			bw.newLine();
			
		}
		bw.flush();
		bw.close();
		br.close();  
	}
}

/*
 * 1. ���簢���� ���̸� ���ϱ� ���ؼ��� M*N�� �ð��� ������ ����� ã�ƾ��Ѵ�.
 * 2. �迭�� ���鼭 ���� �������� ������ �����ϴ� ����� ã�ƾ��Ѵ�.
 * 3. 1,1���� �����Ͽ� �������, ��, ������ �� �߿��� ���� ���� ���� + 1�� ���� ������ i,j�� �ִ´�, �� ���� i,j�� ���� 1�ΰ�쿡�� �Ѵ�.
 *    (�簢���� ũ��� ���������� 1�� ������Ű�� ũ�Ⱑ �����Ǵ°��� �̿��ϴ°��̴�)
 * 4. �迭�� ũ�⸸ŭ ���鼭 max���� ����ϸ� �ȴ�. �ٵ� �̰� ��� ������? �Դٰ� ������ Ad������
 */
