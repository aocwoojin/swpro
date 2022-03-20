package beginner.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution02 {

	static int[] param;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String T = br.readLine();
		
		for(int i=0; i<Integer.parseInt(T); i++) {
			param = new int[10];
			int m = Integer.parseInt(br.readLine());
			for(int j=0; j<m; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				List<String> oper = new ArrayList<>();
				while(st.hasMoreTokens()) {
					oper.add(st.nextToken());
				}
				process(oper);
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#"+(i+1));
			for(int j=0; j<param.length; j++) {
				sb.append(" "+param[j]);
			}
			sb.append("\n");
			System.out.println(sb); 
		}

	}
	
	
	
	public static void process(List<String> oper) {
		if(oper.get(0).equals("add")) {
			if(oper.size()==1) {	// 1�� ������ 2�� ������ ���ؼ� 1�� ������ �����Ѵ�
				param[0] = param[0]+param[1];
			}
			if(oper.size()==2) {	// 1�������� ���� X�� ���Ѵ�
				param[0] = param[0] + Integer.parseInt(oper.get(1));
			}
		} 
		if(oper.get(0).equals("mul")) {
			if(oper.size()==1) {	// 1�� ������ 2�� ������ ���ؼ� 1�� ������ �����Ѵ�
				param[0] = param[0]*param[1];
			}
			if(oper.size()==2) {	// 1�� ������ ���� X�� ���Ѵ�.
				param[0] = param[0] * Integer.parseInt(oper.get(1));
			}
		}
		if(oper.get(0).equals("rotate")) {
			if(oper.get(1).equals("0")) {	// ��������Ʈ
				int[] temp = new int[10];
				for(int i=0; i<temp.length; i++) {
					int shift = Integer.parseInt(oper.get(2));
					
					shift = shift%10;
					temp[(temp.length-shift+i)%10] = param[i];
				}
				param = temp;
			}
			if(oper.get(1).equals("1")) {	// ��������Ʈ
				int[] temp = new int[10];
				for(int i=0; i<temp.length; i++) {
					int shift = Integer.parseInt(oper.get(2));
					shift = shift%10;
					temp[(i+shift)%10] = param[i];
				}
				param = temp;
			}
		}
	}

}
