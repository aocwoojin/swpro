package middle.day08;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * ��Ȧ
 * ��������
 */
public class Solution02 {
	
	private static int T;
	private static int N;
	private static int M;
	
	static int[] dist; // �ִܰŸ� ����� �迭
	static ArrayList<Edge> edgeList; // ���� ����Ʈ
	
	static class Edge {
		int s, e, c; // start, end ,cost

		public Edge(int s, int e, int c) {
			this.s = s;
			this.e = e;
			this.c = c;
		}
	}
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // ������ ����
			M = Integer.parseInt(st.nextToken()); // ������ ����

			edgeList = new ArrayList<Edge>();

			dist = new int[N + 1];

			for (int i = 1; i <= N; i++) {
				dist[i] = Integer.MAX_VALUE;
			}

			int a = 0, b = 0, c = 0;

			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());

				edgeList.add(new Edge(a, b, c));
				edgeList.add(new Edge(b, a, c));
			}

			bellmanford(1);

			for (int i = 1; i <= N; i++) {
				System.out.println(dist[i] + " ");
			}
			
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static boolean bellmanford(int S) {

		dist[S] = 0;

		for (int k = 1; k <= N - 1; k++) {	// ������ ���� - 1��ŭ �ݺ�

			for (int j = 0; j < edgeList.size(); j++) {
				Edge edge = edgeList.get(j);

				int s = edge.s;
				int e = edge.e;
				int cost = edge.c;

				if (dist[s] != Integer.MAX_VALUE && dist[e] > dist[s] + cost) {
					dist[e] = dist[s] + cost;
				}
			}

		}

		// �ִܰŸ��� �ϼ��Ǿ�� ��

		// ���� ����Ŭ ã��
		for (int j = 0; j < edgeList.size(); j++) {
			Edge edge = edgeList.get(j);

			int s = edge.s;
			int e = edge.e;
			int cost = edge.c;

			if (dist[e] > dist[s] + cost) {
				return true;
			}
		}

		return false;
	}
}

/*
 * �⺻������ ���ͽ�Ʋ�� ���� ����� ���� �˰���������, ������ ������ �ְų� ���� ����Ŭ ���翩�θ� Ȯ���� �� ������ ����Ѵ�.
 * ��������� O(VE)�� ���ͽ�Ʈ�󺸴� �����ϱ� ������ ���ų� �ϴ���, ���������� ��û���� �������(���ͽ�Ʈ��� 10������ �����ϴ�) ����Ѵ�.
 * 1. ��� ��带 ����
 * 2. �ִܰŸ� ���̺��� �ʱ�ȭ(dist�迭)
 * 3. ������ ������ N-1�� �ݺ�
 *    3-1. ��ü ���� E���� �ϳ��� Ȯ��
 *    3-2. �� ������ ���� �ٸ� ���� ���� ����� ����Ͽ� �ִܰŸ� ���̺��� ����
 * 4. ���� ���� ��ȯ�� üũ�ϱ� ���ؼ� �ѹ��� �����Ѵ�.
 * 
 *  for(int i=1; i<=N; i++){//��尹�� -1����ŭ ������ 1�� �� ������ ���� ����Ŭ�� �ִ������� �˻��Ҷ��
 *  	for(int j=1; j<=E; j++){	//������ ������ŭ ������. �� N*E�� �ȴ�.
 *  
 */


