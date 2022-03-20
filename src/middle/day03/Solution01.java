package middle.day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;


/**
 * [����A-0020] ž
 * http://182.193.11.65/common/satti/practiceProblem.do?problemId=AVe3-sKAoMmojUEZ
 * @author woojin2.choi
 * @hint ž�� ���̸� üũ�Կ� �־ �����̶�� �ڷᱸ���� �� �� �ִ°�
 */
public class Solution01 {

	static int N;
	static Stack<Long> stack;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			stack = new Stack<>();
			
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				stack.push(Long.parseLong(st.nextToken()));
			}
			
			long res = 0;
			// ���ÿ��� �����鼭
			while(true) {
				long temp = stack.pop();
				for(int j=stack.size()-1; j>=0; j--) {
					if(stack.get(j) >= temp) {
						res += (j+1);
						break;
					}
				}
				if(stack.size()==1) {
					break;
				}
			}
					
			bw.append("#").append(String.valueOf(i+1)).append(" ").append(String.valueOf(res%1000000007));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
		
	}

}
