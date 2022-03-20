package middle.day04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [����P-0034] ��Ȳ�� ��ȣ���� -> Ÿ�ӿ����� ��Ǯ����
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AWlHLZ0A5gOojUFD&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @ Fail, K���� �ʹ� ū�� �̰� �������� ����. ���ڸ����� ����� �������� ���ϴ� ����� ������ �ð��ʰ��� ��.
 */
public class Solution03 {
	
	private static int T;
	private static String K;
	private static int L;
	private static int p;
	
	private static boolean[] primeList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		initPrimeList(10000000);
		
		for(int i=1; i<=T; i++) {
			st = new StringTokenizer(br.readLine());
			
			K = st.nextToken();
			L = Integer.parseInt(st.nextToken());
			
			boolean check = true;
			for(int j=L; j>=2; j--) {
				if(primeList[j]==true && getR(K, j)==0) {
					//BAD
					check = false;
					p = j;
				} 
			}
			
			if(check) {
				bw.append("#").append(String.valueOf(i)).append(" ").append("GOOD");
				bw.newLine();
			} else {
				bw.append("#").append(String.valueOf(i)).append(" ").append("BAD").append(" ").append(String.valueOf(p));
				bw.newLine();
			}
			
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void initPrimeList(int N) {
		primeList = new boolean[N+1];
		primeList[0] = false;
		primeList[1] = false;
		
		// �⺻ ��� �Ҽ��̴ٶ�� �ϰ�
		Arrays.fill(primeList, true);
		
		for(int i=2; i*i<=N; i++) {
			if(primeList[i]){	// �Ҽ��϶��� ����� ���������� ����ȭ�� ��
				for(int j=i*2; j<=N; j=j+i) {
					primeList[j] = false;
				}
			}
		}
	}
	
	private static int getR(String bigNum, int m) {
		int r=0;
		for(int i=0; i<bigNum.length(); i++) {
			r = r*10;
			r += bigNum.charAt(i)-'0';
			r = r%m;
		}
		return r;
	}
	
}
