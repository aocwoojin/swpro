package beginner.day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * BFS�� ó���� ť�� �ְ�, ť���� �������湮, ������带 �߰�(�̹� �߰ߵ� ���� �߰����� �ʴ´�) �ٽ� ť���� ���� �湮, ������� �߰� �̷���
 * �̹� �湮�� ��带 üũ�ϴ°�, ť�� ���� ����Ƽ�忡 �־���Ѵ� ��, �ܼ��� ť�� �ִ����� Ȯ���ϸ� �ȵ���
 * DFS�� ������ -> �����ִ� ���� �ǰ� ������ �Ѱ��� ����� ã�Ƴ����� �Ҷ�(�ִܰ�� ã���� DFS���� ������, DFS�� �� ã�ƺ����ϴµ�(���� ������ ã�������ְ�, �ణ�� ����ȭ�� �Ҽ��� ������ ����)
 * BFS�� ������ -> ���� �������� ���� ������? �� ���ϰ� ������, ������� ���ϰ� ���� �ذ� ��ó�� ������
 * @author woojin2.choi
 *
 */

public class Solution01 {
	
	static int[][] nodes;
	static Queue<Integer> q;
	static int step;
	static int now;
	
	public static void main(String args[]) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<T; i++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); //���� ����
			int L = Integer.parseInt(st.nextToken()); //���� �Ѱ�Ÿ�
			L = L*L; // �����صΰ�
			
			nodes = new int[N+1][4];	// x,y,visited
			q = new ArrayDeque<>();
			step = 0;
			now = 0;
			
			for(int j=1; j<=N; j++) {
				st = new StringTokenizer(br.readLine());
				nodes[j][0] = Integer.parseInt(st.nextToken());
				nodes[j][1] = Integer.parseInt(st.nextToken());
				nodes[j][2] = 0;	//visited
				nodes[j][3] = 0;	//step
			}
			
			q.offer(1);
			nodes[1][2] = 1;	// 1����� �湮����
			boolean isPos = false; 
			
			while(!q.isEmpty()) {
				now = q.poll();
				
				// ����������
				if(nodes[N][0]==nodes[now][0] && nodes[N][1]==nodes[now][1]) {
					isPos = true;
					break;
				} else {
					for(int k = 1; k<=N; k++) {
                        if(nodes[k][2] == 0
                                && L >= (nodes[now][0]-nodes[k][0])*(nodes[now][0]-nodes[k][0])
                                        + (nodes[now][1]-nodes[k][1])*(nodes[now][1]-nodes[k][1])) {
                            nodes[k][2] = 1;
                            nodes[k][3] = nodes[now][3]+1;
                            q.offer(k);
                        }
                    }
					
				}
			}
			int result = -1;
			if(isPos) {
				result = nodes[now][3];
			} else {
				result = -1;
			}
			
			System.out.println("#" + (i+1) + " " + result);
			
		}
		
	}

}
