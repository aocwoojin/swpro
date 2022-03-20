package middle.day06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * [����P-0008] �Ӱ� ���
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AVior0UgkM6ojUH_&_menuId=AVUU732mAAHBC0c9&_menuF=true#
 * @author woojin2.choi
 * @hint DAG�׷���(����׷���)�� ���������ϴ� ����� �˰��ִ°�?, �׸��� indegree�� 0�� �Ǳ� ���� ������node���� ���� cost�� ������� �� cost�� ���Ѱ�
 *       �̹� to��忡 �ִ� �ڽ�Ʈ���� ũ�� ������Ʈ �ϴ°��̴�, �����ؼ� ���� �湮���� ���� ����� to��带 ������Ʈ�ϸ鼭 cost���� ��� ������ while���� ���ư���.
 */
public class Solution02 {
	
	private static int T;
	private static int N;
	private static int M;
	
	private static ArrayList<TopoEdge>[] adj;
	private static int[] indegrees;

	private static class TopoEdge{
		public int end;
		public int cost;
		
		TopoEdge(int end, int cost){
			this.end = end;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// ������ ������ �����ϴ� ����Ʈ ����
			adj = new ArrayList[M+1];
			for(int j=0; j<adj.length; j++) {
				adj[j] = new ArrayList<>();
			}
			
			indegrees = new int[N+1];
			
			int s, e, c;
			// ������ ������ ��������
			for(int j=1; j<=M; j++) {
				st = new StringTokenizer(br.readLine());
				s = Integer.parseInt(st.nextToken());
				e = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				adj[s].add(new TopoEdge(e, c));
				indegrees[e]++;	//indegree���� 1�� ���ؼ� �ʱ�ȭ�� �谣���� ������ ����
			}
			
			long res=0;
			
			res = topologicalSort();
			
			bw.append("#").append(String.valueOf(i)).append(" ").append(String.valueOf(res));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static long topologicalSort() {	//��������
		
		Deque<Integer> queue = new ArrayDeque<>();
		ArrayList<Integer> ordered = new ArrayList<>();
		int[] cost = new int[N+1];	//�������� �ε�׸���0�϶� cost�� ������Ʈ �ϴ� �迭
		
		for(int i=1; i<=N; i++) {
			if(indegrees[i]==0) {
				queue.add(i);
			}
		}
		
		int now;
		while(!queue.isEmpty()) {
			now = queue.poll();
			
			// ���������� ��� �湮������ �����Ѵ�
			ordered.add(now);
			
			for(int i=0; i<adj[now].size(); i++) {
				
				int to = adj[now].get(i).end;	//����� ����� ��������
				cost[to] = Math.max(cost[to], cost[now]+adj[now].get(i).cost);	//������ ��忡�ٰ� ���簪�� ���ߴµ� �����ͺ��� ū���� �־��ش� -> ��������
				indegrees[to]--;				//����� ���������� indegree���� ���̰�, 0�̵Ǹ� ť���ְ� Ž���Ѵ�
				if(indegrees[to] == 0) {
					queue.add(to);
				}
			}
		}
	
		return cost[N];
	}
	
}

/*
����� �����Կ� �־ ���������� �ϵ��� �ϳ��� �𿩼� ������� ���ϴ� ����� ã�� ���Ĺ���
��Ÿũ����Ʈ�� ���尰����, ������ �������� ���� � ������ ���� �������� �ٸ������� ���� ���������� ��ٷȴٰ� �� �Ǹ� �����ϴ�..
��ȸ����� ���ʿ� indegree�� 0�� ��� ������ ť�� �ְ�, 
�ϴϾ� ���ڿ�for�� ���鼭  ����� ������ indegree���� 1�� �ٿ�������, indegree���� 0�ΰ͵鸸 ť�� �ְ� �ٽ� ������.
*/
