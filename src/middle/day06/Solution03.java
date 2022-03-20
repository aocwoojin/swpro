package middle.day06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [����P-0007] ���� ���θ�
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AVioru3AkLGojUH_&_menuId=AVUU732mAAHBC0c9&_menuF=true#
 * @author woojin2.choi
 * @hint kruskal �˰����� �̿��Ѵ�, �⺻������ ũ�罺Į �˰����� union-find�� ������� �����Ѵ�
 */
public class Solution03 {
	
	private static int T;
	private static int N;
	private static int M;
	private static int K;

	private static List<Edge> edges;
	private static int[] parent;
	
	private static class Edge{
		public int start;
		public int end;
		public int cost;
		
		Edge(int start, int end, int cost){
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}
	
	public static void main(String args[]) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int i = 1; i <= T; i++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			// ��������Ʈ ����
			edges = new ArrayList<>();	//
			
			
			// union-find�� ���� �θ�迭 �ʱ�ȭ(�ڱ��ڽ�)
			parent = new int[N+1];
			for(int j=0; j<=N; j++) {
				parent[j] = j;
			}
			
			long temp = 0;
			for(int j=0; j<M; j++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());	// start
				int e = Integer.parseInt(st.nextToken());	// end
				int c = Integer.parseInt(st.nextToken());	// cost
				edges.add(new Edge(s, e, c*-1));	//�̹� �Ǽ��� �ֵ��� ������ �ʱ�ȭ -> �����ϰԵǸ� -�� ū�ֵ��� ���� union�� �ǹǷ� -1 �̷��ֵ��� �ڷ� �и��� -> �������ö��
				temp += c;
			}
			
			for(int j=0; j<K; j++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());	// start
				int e = Integer.parseInt(st.nextToken());	// end
				int c = Integer.parseInt(st.nextToken());	// cost
				edges.add(new Edge(s, e, c));
			}
			
			//���������ؼ� �����ϸ�, find�� ã������ true�ΰ�쿡�� �ı��Ǿ���� �ֵ��̴�
			Collections.sort(edges, new Comparator<Edge>() {
				@Override
				public int compare(Edge o1, Edge o2) {
					return o1.cost-o2.cost;
				}
			});
			
			
			long res = 0;
			int cnt=0;
			for(int j=0; j<edges.size(); j++) {
				if(cnt==N-1) {
					break;
				}
				if(find(edges.get(j).start, edges.get(j).end)) {
					
				} else {
					union(edges.get(j).start, edges.get(j).end);
					res += edges.get(j).cost;
					cnt++;
				}
			}
			
			res = temp+res;
			
			bw.append("#").append(String.valueOf(i)).append(" ").append(String.valueOf(res));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void union(int a, int b) {
		
		int x = getParent(a);
		int y = getParent(b);
		
		if(x<y) {
			parent[y] = parent[x];
		} else {
			parent[x] = parent[y];
		}
		
	}
	
	private static boolean find(int a, int b) {
		int x = getParent(a);
		int y = getParent(b);
		return x==y;
	}
	
	// �θ� �������µ�, �θ� �������鼭 �θ��� �θ� ã�� ������Ʈ �صд�
	private static int getParent(int x) {
		if(parent[x]==x) {
			return x;
		}
		return parent[x] = getParent(parent[x]);
	}
}





/*
 * �ּҽ���Ʈ�� -> ��� ������ Ʈ��������(����Ŭ�̾���) �����Ҷ�, �ּ��� ���� cost�� ���غ��°�
 * �������, ��� ��ǻ�� ��Ʈ��ũ�� ����������, ������ ����� �ְ�, �̰� �ּҺ��(����ٽ�)���� �ϰ�ʹ�
 * �̷��� MST�� ����ϸ� ��(�̴ϸ� ���д� Ʈ��)
 * cost������ ������ ����Ʈ�� �����ϰ��� union-find�� �ϵ�, ������ N-1���� �����ϰ� ������ �ȴ�.
 * �̴ϸ� ���д� Ʈ���� ����� �˰����� ũ�罺Į �˰����̴� 
*/
