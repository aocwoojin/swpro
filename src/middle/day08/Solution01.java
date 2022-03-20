package middle.day08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [����P-0012] ��Ƽ �����ϱ�
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AVdKhCFQdAaojUEZ&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint ���ͽ�Ʈ�� �˰����� �˰��ִ°�? �⺻���� ���ͽ�Ʈ��� ���������� ��� �������� �ִܰŸ��̴� 1:N
 *       X�� ���δٴ°�? -> ���������� ������ ������, ���ͽ�Ʈ�� ���ؼ� X�� ���϶��� �ִܰŸ��� ��庰�� ���Ҽ��ִ�.
 *       X���� �ٽ� ������ ���ٴ°�? -> �������� ���ͽ�Ʈ�� ���ؼ� N���� ���κ��� X������ �ִܰŸ��� ���ϴ°Ͱ� ����.
 */
public class Solution01 {
	
	private static int T;
	private static int N;	//�л��� -> V
	private static int M;	//���Ǽ� -> E
	private static int X;	//��Ƽ�� ���� ����� �л��� ��ȣ -> �������̶�� �Ҹ�
	private static List<Node>[] adjList;
	private static List<Node>[] adjListB;
	private static int[] dist;
	
	private static class Node{
		public int node;
		public int cost;
		
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
			X = Integer.parseInt(st.nextToken());
			
			
			adjList = new ArrayList[N+1];
			adjListB = new ArrayList[N+1];
			for (int i=1; i<=N; i++) {
				adjList[i] = new ArrayList<Node>();
				adjListB[i] = new ArrayList<Node>();
			}
			
			
			for(int i=1; i<=M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				adjList[s].add(new Node(e, t));
				adjListB[e].add(new Node(s, t));
			}
			
			long res = 0;
			
			int[] a = dijkstra(X, adjListB);
			int[] b = dijkstra(X, adjList);
			for(int i=1; i<=N; i++) {
				a[i] += b[i];
			}
			Arrays.sort(a);
			res = a[a.length-1];
			bw.append("#").append(String.valueOf(tc)).append(" ").append(String.valueOf(res));
			bw.newLine();
			
		}
		bw.flush();
		bw.close();
		br.close();  
	}
	
	private static int[] dijkstra(int S, List<Node>[] adjList) {
		dist = new int[N+1];
		// ���ʴ� ���Ѵ�� �ʱ�ȭ�Ѵ� 
		for(int i=1; i<=N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[S] = 0; 	//������� 0���� �����Ѵ�.
		
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.cost - o2.cost;
			}
		});
		pq.add(new Node(S, dist[S]));
		
		while(!pq.isEmpty()) {
			// ť���� ������ ���� cost�� �ִܰŸ��̴�, �켱����ť���� �̹� cost�� ���������ϱ�
			Node current = pq.poll();
			int currentNode = current.node;
			
			if(current.cost > dist[current.node]) {	//dist[current.node]�� �������� ��� �����ϴ°��̴ϱ� �����ڽ�Ʈ�� �� ũ�� ���ʿ䵵 ����
				continue;
			}
			
			// �������� ������ �׸��ϰ� �������
			//if (currentNode == destinationNode) {
			//	break;
			//}
			
			for(int i=0; i<adjList[currentNode].size(); i++) {	//���� ����� �ֵ��� ������
				int nextNode = adjList[currentNode].get(i).node;	//���� ���� ����� ������ �湮�ҿ���
				int nextCost = adjList[currentNode].get(i).cost;	//���� ���� ����� ������ �������
				
				if(dist[nextNode] > dist[currentNode]+nextCost) {	//�� �ܰŸ��� ��쿡 ���� �����Ѵ�.
					dist[nextNode] = dist[currentNode] + nextCost;
					pq.offer(new Node(nextNode, dist[nextNode]));	//�� �ܰŸ��� ���ŵǾ����� ť�� �ְ� ������
				}
			}
		}
		return dist;
	}
}



/*
 * 1. ��� ��带 ����
 * 2. �ִܰŸ� ���̺��� �ʱ�ȭ(dist�迭)
 * 3. �湮���� ���� ��� �߿��� �ִܰŸ��� ���� ª�� ��带 ����
 * 4. �ش� ��带 ���� ���� ���� ���� ����� ����Ͽ� �ִܰŸ� ���̺��� ����
 * 5. 3~4�� �ݺ�
 * 
 * ���� ������� D[S]=0���� �����Ѵ� -> �ּ� ��(�켱���� ť)�� ����� S�� ����
 * �湮���� ���� ���� �߿��� D[K]�� �ּ��� ���� I ���� -> �ּ� ��(�켱���� ť)���� �� �����ִ� ���� I�� ����
 * D[j] = D[i] + W�� ���� -> �ּ���(�켱���� ť)�� ���� J ����
 * �켱���� ť���� �̴´ٴ°��� �� ������ �ִܰŸ��� Ȯ���� �Ǿ��ܤ����̴�, ���Ŀ� �켱���� ť���� �������� �ִܰŸ��� �ƴ϶� �����ϸ� �ȴ�.
 */