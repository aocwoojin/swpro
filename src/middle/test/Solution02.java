package middle.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * ���� �׽�Ʈ2
 * �ð� ���� : 16 ���� Test Input �Է½� C/C++ 1.5�� / Java 2��
 * �޸� ���� : Stack : 1 Mbytes  /  Total : 256 Mbytes
 * 
 * [����]
 * N���� ���ð� �ְ�, M���� ���ΰ� �ִ�. �ϳ��� ���δ� �� ���� ���̸� �����ϸ�, ������ �� ���ø� �����ϴ� ���ΰ� ���� �� ���� �� �ִ�. 
 * ���ÿ��� 1���� N���� ��ȣ�� ������� �Ű����ְ�, ���θ��� �������µ� �ɸ��� �ð��� �־�����.
 * ������ ���� ������ �־����� ��, 1�� ���ÿ��� N�� ���÷� ���� �ּ� �ð��� ���α׷��� �ۼ��Ͻÿ�
 * 
 * [�Է�] 
 * �Է� ���Ͽ��� ���� �׽�Ʈ ���̽��� ���Ե� �� �ִ�. 
 * ������ ù° �ٿ� ���̽��� ���� T�� �־�����, ���� ���ʷ� T�� �׽�Ʈ ���̽��� �־�����. (1 �� T �� 20) 
 * �� ���̽��� ù �ٿ� ������ �� N�� ������ �� M�� �������� ���еǾ� �־�����. (1 �� N �� 100,000, 1 �� M �� 300,000)
 * �׸��� ���� M���� �ٿ� �� ���ο� ���� ������ ��Ÿ���� �� ������ �������� ���еǾ� �־�����. 
 * "a b c"��� �־����� �� a�� ���ÿ� b�� ���ø� �����ϴ� ���ΰ� �����ϸ�, �� ���θ� �����µ� �ɸ��� �ð��� c��� ���� �ǹ��Ѵ�. 
 * (1 �� a, b �� N, 1 �� c �� 10,000, a �� b)
 * 
 * [���] 
 * �� �׽�Ʈ ���̽��� ���� ������� ǥ��������� ����ϸ�, �� ���̽����� ���� ���ۿ� ��#x���� ����Ͽ��� �Ѵ�. 
 * �̶� x�� ���̽��� ��ȣ�̴�. ���� �ٿ�, 1�� ���ÿ��� N�� ���÷� ���� �ּ� �ð��� ����Ѵ�. 
 * ���� 1�� ���ÿ��� N�� ���÷� �� �� ���ٸ� -1�� ����Ѵ�.
 * 
 * [����� ��]
 * (�Է�)
 * 2     �� 2 test cases in total
 * 3 3   �� 1st case
 * 1 2 3
 * 2 3 1
 * 1 3 2
 * 6 9   �� 2nd case
 * 1 2 2
 * 1 3 4
 * 2 3 1
 * 2 5 2
 * 2 4 4
 * 3 5 3
 * 4 5 3
 * 4 6 2
 * 5 6 2
 * 
 * (���)
 * #1 2
 * #2 6
 * @author woojin2.choi
 * @hint �� ���ͽ�Ʈ��
 */
public class Solution02 {
	
	private static int T;
	private static int N;	//������ ��
	private static int M;	//������ ��
	private static int K=1;	//���ÿ��� �ѹ��� �����ϱ�
	private static ArrayList<Node>[] adjList;	//��������Ʈ(�����)
	private static int[] visited;	//���湮Ƚ��, 1���� �湮�ϸ� �ִܰŸ��̴�
	
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
			
			adjList = new ArrayList[N+1];	//��������Ʈ ����
			for(int i=1; i<=N; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			for(int i=1; i<=M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				adjList[s].add(new Node(e, c));
				adjList[e].add(new Node(s, c));
			}	//��������Ʈ ����
			
			visited = new int[N+1];
			
			int res = dijkstra(1);
			
			bw.append("#").append(String.valueOf(tc)).append(" ").append(String.valueOf(res));
			bw.newLine();
			
		}
		bw.flush();
		bw.close();
		br.close();  
	}
	
	private static int dijkstra(int s) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.cost - o2.cost;	// ������ cost�� �����ϸ鼭 �����ϵ��� �Ѵ�
			}
		});
		
		pq.add(new Node(s, 0));
		
		while(!pq.isEmpty()) {
			
			Node current = pq.poll();	//N��° �����¼��� N��° �ּҴ�
			int currentNode = current.node;
			
			visited[currentNode]++;
			
			if(visited[currentNode] > K) {	//1�� �̻� ������ �׳� �о�
				continue;
			}
			if(visited[currentNode] == K && currentNode == N) {	// ���ϰ���� N�� K�� ������ ���
				return current.cost;
			}
			
			for(int i=0; i<adjList[currentNode].size(); i++) {
				int nextNode = adjList[currentNode].get(i).node;
				int nextCost = adjList[currentNode].get(i).cost;
				if(visited[nextNode] < K) {
					pq.add(new Node(nextNode, current.cost + nextCost));
				}
			}
		}
		return -1;
	}
	
}

