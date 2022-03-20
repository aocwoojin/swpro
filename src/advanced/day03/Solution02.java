package advanced.day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [����P-0079] ���ð�ȹ 
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AXYXVMLGri_BC0Hs
 * @author woojin2.choi
 * @hint �����˰������� Ǯ�� ����, MST�� Ǯ�� ������ �Ѱ��� ����Ǿ��ִ��� �����ϴ� ������ �ʿ��ϴ�
 */
public class Solution02 {
	
	private static int T;
	private static int N;
	private static int M;
	private static Pos[] city;
	private static boolean[] hasSource;
	private static boolean[] link;

	private static class Pos {
		int x;
		int y;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

	private static class Node {
		int from;
		int to;
		long thisCost;
		Node(int from, int to, long thisCost){
			this.from = from;
			this.to = to;
			this.thisCost = thisCost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			PriorityQueue<Node> minCity = new PriorityQueue<>(new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					if(o1.thisCost - o2.thisCost < 0) {
						return -1;
					} else {
						return 1;
					}
					//return o1.thisCost - o2.thisCost;
				}
			});
			
			city = new Pos[N+1];
			hasSource = new boolean[N+1];
			link = new boolean[N+1];
			
			// ������ ������ �Է¹޴´�
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				city[i] = new Pos(x, y);
			}
			
			// ������������ ���θ� �����Ѵ�, �����˰������� �������� ���� �� �ְ� �����ϹǷ� �ʿ��ϴ�
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=M; i++) {
				int s = Integer.parseInt(st.nextToken());
				hasSource[s] = true;
			}
			
			
			
			for(int i=1; i<=N; i++) {
				if(hasSource[i]) {
					minCity.add(new Node(0, i, 0));
				}
			}
			
			long ans = 0;
			while(!minCity.isEmpty()) {
				Node current = minCity.poll();
				int from = current.from;
				int to = current.to;
				long thisCost = current.thisCost;
				
				if(link[to]) {
					continue;
				}
				
				link[to] = true;
				ans += thisCost;
				
				for(int i=1; i<=N; i++) {
					if(link[i] || i == to) {
						continue;
					}
					long cost = (long)(city[to].x - city[i].x)*(city[to].x - city[i].x) + (long)(city[to].y - city[i].y)*(city[to].y - city[i].y);
					minCity.add(new Node(to, i, cost));
				}
			}
			
			bw.append("#").append(String.valueOf(tc)).append(" ").append(String.valueOf(ans));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

}
