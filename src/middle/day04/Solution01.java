package middle.day04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;


/**
 * [����A-0022] �Ҽ����
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AVgE01LQ7nyojUEZ&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint �Ҽ� ����Ʈ�� ���� ���س���(�����佺�׳׽��� ü), 1000���ڸ� ���ں��� ���ڸ� 0-9���� ��� �ٲ㰡�鼭 �Ҽ��̸� ť���ְ� bfs�� ������. ��� �ظ� ���غ��� depth�� ���ϸ�ȴ�.
 * @hint A���� B���� depth�� ���Ҷ��� visited�迭���� ������+1�� ������� depth�� ������
 */
public class Solution01 {
	
	private static int T;
	private static int A;
	private static int B;
	
	private static List<Boolean> primeList;
	private static int[] visited;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		makePrimeList(1000, 9999);
		
		
		for(int i=1; i<=T; i++) {
			
			visited = new int[9999+1];
			for(int j=0; j<visited.length; j++) {
				visited[j] = -1; 
			}
			
			st = new StringTokenizer(br.readLine());
			
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			bfs(A);
			
			bw.append("#").append(String.valueOf(i)).append(" ").append(String.valueOf(visited[B]));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	
	private static void bfs(int node) {
		Deque<Integer> queue = new ArrayDeque<>();
		queue.add(node);
		visited[node] = 0;
		
		while(!queue.isEmpty()) {
			int prime = queue.poll();
			
			for(int i=0; i<4; i++) {
				for(int j=0; j<10; j++) {	
					int newPrime = getNewNum(prime, i, j);
					if(primeList.get(newPrime)==true && newPrime >= 1000 && visited[newPrime] == -1) {
						queue.add(newPrime);
						visited[newPrime] = visited[prime]+1;	//����Ƽ�带 �̿��ؼ� depth�� ������
					}
				}
			}
		}
	}
	
	
	private static int getNewNum(int num, int index, int newNum) {
		
		int[] res = new int[4];
		res[0] = num/1000;
		res[1] = (num/100)%10;
		res[2] = ((num/10)%100)%10;
		res[3] = (num%10);
		
		if(index == 0) {
			res[0] = newNum;
		}
		if(index == 1) {
			res[1] = newNum;
		}
		if(index == 2) {
			res[2] = newNum;
		}
		if(index == 3) {
			res[3] = newNum;
		}
		
		return res[0]*1000+res[1]*100+res[2]*10+res[3];
	}
	
	//�����佺�׳׽��� ü
	private static void makePrimeList(int start, int end) {
		primeList = new ArrayList<>();
		
		primeList.add(0, false);
		primeList.add(1, false);
		
		for(int i=2; i<=end; i++) {
			primeList.add(i, true);	//�켱 ��� �Ҽ���� �ϰ� ������
		}
		
		// ������ ������� ��������.
		for(int i=2; (i*i)<=end; i++){
			if(primeList.get(i)){
				for(int j = i*i; j<=end; j+=i) {
					primeList.set(j, false);	//�Ҽ��� �ƴ� ������� �����
				}
			}
		}
		
	}
	
}
