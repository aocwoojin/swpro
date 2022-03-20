package advanced.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [����P-0009] �ִ� ���
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AVior5LwkNmojUH_&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint �׳� ����� ���ͽ�Ʈ��
 */
public class Solution01 {
	
	private static int T;
	private static int N;
	private static int M;
	
	private static ArrayList<Node>[] adjList;
	private static int visited[];
	
	private static class Node{
		int node;
		int cost;
		Node(int node, int cost){
			this.node = node;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	//������ ��
			M = Integer.parseInt(st.nextToken());	//������ ��
			
			//��������Ʈ�� �����Ѵ�, ����� ������ŭ �����ؼ� adjList[s].get(i)�� s���� �����ϴ� �������� i�� �ִ�, �׶� �� ��忡�� �ڽ�Ʈ�� ����
			adjList = new ArrayList[N+1];
			for(int i=1; i<=N; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			//M���� ������ �Էµȴ�
			for(int i=1; i<=M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());	//����
				int e = Integer.parseInt(st.nextToken());	//����
				int c = Integer.parseInt(st.nextToken());	//����cost
				adjList[s].add(new Node(e, c));
				adjList[e].add(new Node(s, c));	//����� �׷�����
			}
			
			visited = new int[N+1];
			
			int res = dijkstra(1, N);	// 1������ N���� ���� �ּҺ���� ���ΰ�
			
			bw.append("#").append(String.valueOf(tc)).append(" ").append(String.valueOf(res));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int dijkstra(int S, int D) {
		int ans=-1;	//���������� -1�� ����Ѵ�
		
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.cost - o2.cost;	//����� ���������� min heap�� ����
			}
		});
		
		//���� �������� S�� ���� �ڽ�Ʈ�� 0���� ������ ������ dijkstra�� �����Ѵ�
		pq.add(new Node(S, 0));
		visited[S]=1;
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			int currentNode = current.node;
			int currentCost = current.cost;
			visited[currentNode]=1;	//�̴¼��� �湮�Ѱ���
			
			if(visited[currentNode] > 1)	//�ѹ��̻� ���°��� �ּҰ��� �ƴϹǷ� �н��Ѵ�
                continue;
			
			if(currentNode == D && visited[currentNode]==1) {
				ans = current.cost;
				break;
			}
			
			for(int i=0; i<adjList[currentNode].size(); i++) {
				int nextNode = adjList[currentNode].get(i).node;
				int nextCost = adjList[currentNode].get(i).cost;
				
				if(visited[nextNode] == 0) {	//�湮���� ���� ���̸� ť�������Ѵ�.
					pq.add(new Node(nextNode, currentCost+nextCost));
				}
			}
			
		}
		
		return ans;
	}

}
