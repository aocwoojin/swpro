package middle.day04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


/**
 * [����P-0005] Ȯ�� ��Ŭ���� ȣ����
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AViormMQkJeojUH_&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint ��Ŭ���� ȣ�������� GCD(a, b)�� ���� �� �ִ� -> �������� ������ ������ ��� mod�����Ѵ�
 * @hint Ȯ��� ��Ŭ���� ȣ������ ���ؼ� as+bt=GCD(a, b)�� �����ϴ� s, t, gdc�� ���� �� �ִ�. �̷������� �ȳ��õ��ϰ� gcd������ �ٸ������� ���ܼ� ���ü��� ����
 */
public class Solution02 {
	
	private static int T;
	private static int x;
	private static int y;
	private static int z;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++) {
			st = new StringTokenizer(br.readLine());
			
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			
			int[] res = extendedEuclid(x, y);
			if(z >= res[2] && z%res[2]==0) {
				if(z>res[2]) {
					res[0] = res[0]*(z/res[2]);
					res[1] = res[1]*(z/res[2]);
				}
				bw.append("#").append(String.valueOf(i)).append(" ").append(String.valueOf(res[0])).append(" ").append(String.valueOf(res[1]));
				bw.newLine();
			} else {
				bw.append("#").append(String.valueOf(i)).append(" ").append("-1");
				bw.newLine();
			}
			
			
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	// [��Ŭ���� ȣ����]
	// ū���� �������� mod����
	// �������� �������� mod����
	// 0�� ���ö����� �ϸ� ���� mod�����ڰ� �ִ�������
	/* 1112�� 695�� �ִ�����(GCD)
	   1112 mod 695 = 417
       695  mod 417 = 278
       417  mod 278 = 139
       278  mod 139 = 0    => 139(GCD) */
	private static int euclid(int a, int b) {
		int maxVal = Math.max(a, b);
		int gcd;
		int res;
		int tmp;
		if(maxVal != a) {
			tmp=a; a=b; b=tmp;
		} 
		
		while(true) {
			res = a%b;
			if(res==0) {
				gcd = b;
				break;
			} else {
				a=b;
				b=res;
			}
		}
		return gcd;
	}
	
	/*
	[Ȯ��� ��Ŭ���� �˰���]
	as+bt=gcd(a,b)�� �Ǵ� ���� s,t�� ������ ���Ҽ��ִ�
	
	s�� t�� ���ϱ� ���ؼ��� �ʱⰪ�� �������ִµ�,
	s�� ���ϱ� ���ؼ��� s1 = 1, s2 = 0�� ������ ��Ŭ���� �˰����� �����ϰ�,
	t�� ���ϱ� ���ؼ��� t1 = 0, t2 = 1�� ������ ��Ŭ���� �˰����� �����Ѵ�.
	�׸���, s�� t�� ��꿡���� r1 �� r2�� ���� q�� ����Ѵ�.
	s = s1 - q x s2; 
	t = t1 - q x t2;
	�� ����� ������.
	*/
	private static int[] extendedEuclid(int a, int b) {
		int r1 = a, r2 = b;
		int s1 = 1, s2 = 0;
		int t1 = 0, t2 = 1; 
		int r, s, t, q, gcd; 
		
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
		
		s = s1;
		t = t1;
		gcd = r1;
		
		return new int[] {s, t, gcd};
	}
}
