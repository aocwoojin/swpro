package advanced.day01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [����P-0089] [2021�� 01�� 30��] ������ �׷� 
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AXda99VGYnXBC0Gg
 * @author woojin2.choi
 * @hint ���ͽ�Ʈ�� �̿��ؼ� �ִܰŸ��� ���Ѵ�. ������ �����޴� �ķ��� ����ó�� Ȱ��ǹǷ� ���������� ��ȯ�Ͽ� Ǯ�� �� ���̴�.
 */
public class Solution02 {
	
	private static int T;
	private static int N;	//��尳��
	private static int M;	//��������
	private static int K;	//ģ���鼶���󰳼�
	private static ArrayList<Node>[] adjList;
	private static int[] food;
	private static int[] dist;
	
	private static class Node{
		int node;
		int cost;
		Node(int node, int cost){
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
				adjList[i] = new ArrayList<Node>();
			}
			
			for(int i=1; i<=M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				adjList[e].add(new Node(s, c));	//������ ���ͽ�Ʈ�� ���ؼ� start�� e�� �ٲ㼭 �ִ´�
			}
			
			food = new int[N+1];
			for(int i=1; i<=K; i++) {
				st = new StringTokenizer(br.readLine());
				int island = Integer.parseInt(st.nextToken());
				int supply = Integer.parseInt(st.nextToken());
				food[island] = supply;
			}
			
			dist = new int[N+1];
			for(int i=1; i<=N; i++) {
				dist[i] = Integer.MAX_VALUE;
			}
			
			int res = dijkstra(N, 1);
			
			
			bw.append("#").append(String.valueOf(tc)).append(" ").append(String.valueOf(res));
			bw.newLine();
			
		}
		bw.flush();
		bw.close();
		br.close();  
	}
	
	private static int dijkstra(int start, int end) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.cost - o2.cost;
			}
		});
		
		pq.add(new Node(N, 0));	//N�������� �����ϴ� �������̰� ���� cost�� 0���� �����Ѵ�
		dist[start] = 0;

		while(!pq.isEmpty()) {
			Node current = pq.poll();
			int currentNode = current.node;
			
			if (dist[currentNode] < current.cost) continue;
			
			for(int i=0; i<adjList[currentNode].size(); i++) {
				int nextNode = adjList[currentNode].get(i).node;
				int nextCost = adjList[currentNode].get(i).cost;
				
				//������ ���鼭 �Ļ��� ������ �� �Դ´�, ��� �Ծ��µ� ������ 0���μ����ϰ� �������� �Ѿ ����� ���ؼ� ���Ѵ�
				//�ι�°���ÿ��� 6-4-3-1�� ���°� �� ���������� 4->1(10)->3 �̸� �������� 3�� ��µ�
				//6-4-2-1�� �����ϸ� 4->1(5)->2 �� �������� 2�� ��Ƿ�
				//cost���� 2���� �ö��� 3���� �ö��� ���ϸ� 2<3�̹Ƿ� 2�� ��� �����־���Ѵ�.
				int cost = Math.max(dist[currentNode]-food[currentNode], 0)+nextCost;
				
				if(cost < dist[nextNode]) {
					dist[nextNode] = cost;
					pq.add(new Node(nextNode, cost));
				}
				
			}
			
		}
		
		return dist[1];	
	}
}
