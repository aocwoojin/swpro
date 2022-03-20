package middle.day05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * [����A-0005] ���� 
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AVi99fqw3_mojUH_&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint nCk = nC(n-k), nCk = (n-1)C(k-1)+(n-1)Ck
 * @hint (a+b)^k�� ���������� a^(n-k)b^k�� ���� ����͵� ���� -> ���װ��(a�� n-k�� b�� k�� ����)
 * @hint Ȯ��� ��Ŭ���� �˰����� ���ؼ� �� ���� ���μ��� ������ �� p�� ���� 1000000007�� ������ ���ؼ�....
 * @hint mod�� �Ἥ DP�� Ǯ� �� -> Solution05�� ����
 * @hint mod�� ����, ���������� ���� 
 * @hint ������ mod���� �յ��� ������Ű�� ���ؼ��� nCr�� n!/(k!(n-k)!)�� ������ �����Ͽ� (k!(n-k)!)�� �������ٰ� mod�� �����ϵ��� �� �ڿ� ���� ����ϴ°��̴�.
 */
public class Solution04 {
	
	private static int T;
	private static int n;
	private static int k;
	
	private static long nf=1; //n!
	private static long p=1;
	
	private static final int M = 1000000007;	// mod��

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++) {
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			// �ʱ�ȭ
			nf = 1;
			p = 1;
			/*
			 * Ȯ��� ��Ŭ���� �˰����� �̿��� nCr�� ��ⷯ ���갪�� ���ϴ� ���
			 * 1.   nCr = n!/(k!(n-k)!) �̴�
			 * 2.   �纯�� ��ⷯ ������ �ϰ������, �������� ���ؼ��� mod������ ������ �ʴ´�
			 * 3.   nCr = n!*(k!(n-k)!)^-1 �̷��� �������� mod������ ���Ѵ�.
			 * 4.   n!%M * (k!(n-k)!)^1%M ���ְ� ������ �ѹ� ���ϸ� �ȴ�.
			 * 5.   n!�� ���ؼ��� n--�ϸ鼭 mod�� ������ָ� �ȴ�.
			 * 6.   ���� �����ϴϱ� K!(n-k)!�� p�� ����
			 * 7.   �츮�� ���ϰ� ���� ���� (n! * (k!(n-k)!)^-1) % 1000000007�̴�
			 * 7-1. ���� ���� ��ⷯ���̹Ƿ� ��ⷯ������ ������ ���ؾ��Ѵ�(��ⷯ�ι���) -> p*x = 1(mod M)
			 * 7-2. �����׵��? ���� Ǯ�� px+My=gcd(p,M) -> px%M + 0*y = 1 (p�� M�� ���μ�)
			 * 7-3. �� Ȯ����Ŭ������ ���°� �Ǿ����ϱ� p�� M�� �ִ������� 1�� x�� y�� ���ϴ°Ͱ� ������, y�� ���ص� �ǹ̰� ����, x�� ���ϸ� �Ǵ°��̴�. 
			 * 8.   p���� n�� k���� �־����ϱ� n-- �Ǵ� (n-k)-- �� �ݺ����� ���鼭 ��ⷯ ������ �ϸ鼭 �����ϸ� �ȴ�.
			 */
			
			// n! �ϸ鼭 modó���Ͽ� mod���� ��� ����Ѵ�.
			for(int j=n; j>=1; j--) {
				nf *= j;
				nf %= M;
			}
			
			// p���� ���ϴµ� k�� mod�Ͽ� ���ϰ� �׸����� (n-k)!���� mod�Ѵ�.
			for(int j=k; j>=1; j--) {	// k!
				p *= j;
				p %= M;
			}
			for(int j=(n-k); j>=1; j--) {	//(n-k)!
				p *= j;
				p %= M;
			}
			
			// p�� ������ ��������, ������ �������� p�� ������ ��ⷯ?
			long[] temp = extendedEuclid(p, M);	//������
			
			// ���������� n!�� ������ ���� ������ ��ⷯ������ �ѹ����ϸ� ��ⷯ�� �����Ѵ�.
			long res = (nf*temp[0])%M;
			
			if(res<0) {	// ������ ������ mod�� �ѹ� ��������
				res += M;
			}
			
			bw.append("#").append(String.valueOf(i)).append(" ").append(String.valueOf(res));
			bw.newLine();
			
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	
	//[Ȯ��� ��Ŭ���� �˰���] 
	private static long[] extendedEuclid(long a, long b) {
		long r1 = a, r2 = b;
		long s1 = 1, s2 = 0;
		long t1 = 0, t2 = 1; 
		long r, s, t, q, gcd; 
		
		while (r2>0) { 
			q = r1 / r2; 
			
			// gcd ���
			r = r1 % r2; 
			r1 = r2; 
			r2 = r; 
			
			// s ��� 
			s = s1 - q * s2; 
			s1 = s2;
			s2 = s; 
			
			// t ���
			t = t1 - q * t2; 
			t1 = t2;
			t2 = t; 
		}
		
		gcd = r1;
		s = s1;
		t = t1;
		
		return new long[] {s, t, gcd};
	}
}
