package middle.day08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [����P-0011] K��° �ִ� ���
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AVdFc4WQIqOojUEZ&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint ���ͽ�Ʈ��, ť���� N��°�� �����°��� �� N��°�� ª�� �����̴�(�켱���� ť���� �����ؼ� �̱⶧����)
 *       ����� longŸ������ �ؾ� 1�� Ʋ���ű��� �������ִ�.
 *       ť���� �������� K��° �̻����� ������ ��쿡�� ť���ٰ� ����� ������ �ִ°��� �ﰡ�ض� -> �ӵ��� ��������.
 *       �׸��� ������ ��� N�� K�� ���͵� �����ض�, �׷��� �ӵ��� �� ���ϼ��ִ�.
 */
public class Solution04 {
	
	private static int T;
	private static int N;	//�����ǰ��� 10�����ϱ� ���ͽ�Ʈ��� Ǯ��
	private static int M;	//�����ǰ���
	private static int K;	//���° �ִܰ���ΰ�?
	private static ArrayList<Node>[] adjList;
	private static int[] visited;
	
	private static class Node{
		public int node;	// ����� ���۰� ������
		public long cost;	// cost
		
		Node(int node, long cost){
			this.node = node;
			this.cost = cost;
		}
	}
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[N+1];
			for(int i=1; i<=N; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			
			for(int i=1; i<=M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				adjList[x].add(new Node(y, z));	//�����
				adjList[y].add(new Node(x, z));	//�����
			}
			
			long res = dijkstra(1, N);
			
			bw.append("#").append(String.valueOf(tc)).append(" ").append(String.valueOf(res));
			bw.newLine();
			
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	
	private static long dijkstra(int S, int D) {
		long ans = -1;
		visited = new int[N+1];
		
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return (int)o1.cost - (int)o2.cost;
			}
		});
		
		pq.offer(new Node(S, 0));
		
		while(!pq.isEmpty()) {
			// ���ͽ�Ʈ��� �����̾�Ƽť���� �����¼��� �ּҰŸ��� Ȯ���Ǵ°��� �̿��ϸ�, K��°�� ª�� ��θ� ���� �� �ִ�
			Node current = pq.poll();
			int currentNode = current.node;
			visited[currentNode]++;
			
			if(visited[currentNode] > K)
                continue;
			
			if(currentNode == N && visited[currentNode]==K) {
				ans = current.cost;
				break;
			}
			
			for(int i=0; i<adjList[currentNode].size(); i++) {
				int nextNode = adjList[currentNode].get(i).node;
				int nextCost = (int)adjList[currentNode].get(i).cost;
				
				if(visited[nextNode] < K) {
					pq.offer(new Node(nextNode, current.cost+nextCost));
				}
			}
		}
		return ans;
	}
}

