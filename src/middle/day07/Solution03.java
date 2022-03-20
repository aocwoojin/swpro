package middle.day07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * [����P-0051] ������ Ű������ 
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AWPsyDwQgTqojUGz&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint LCA�� ����ϵ�, bfs�� �̿��Ͽ� depth�� ���ϴ� ���ÿ�, ������ Ű�� �ڼ��� Ű���� ũ�� ��� max height���� ������Ʈ �ϰ�������
 *       �������������� ã���� ���ÿ� �� ����� max height���� ���� Ű�� ū ������ Ű�̴�.
 *       LCA �Ķ���Ͱ� �������̸�, �ΰ��� �������� ������ �������Ѵ�. -> ���������� depth�� ������� ������ �Ѹ� ������ �ϴ� ���� �ƴϾ���
 */
public class Solution03 {
	
	private static int T;
	private static int N;	//������ ����
	private static int Q;	//������ ��
	private static int[] depth;	//bfs�� visited�迭
	private static int[] ki;
	private static ArrayList<Integer>[] adjList;
	private static int[][] parent;	//������������ LCA�� ���� parent[k][v] �迭, k�� 2�� ������
	private static final int MAX_K = 14;	//N�� 10000���̴ϱ� 14�� �ȴ�. 2�� 14�� 16384 
	
	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// N�� 10������ �ִ��̹Ƿ� 1�ķ� �� Ʈ���� worst���̽��ε� �̷��� Max K���� 17�� ������ ��
			Q = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[N + 1];
			for (int j=0; j<=N; j++) {
				adjList[j] = new ArrayList<>();
			}

			parent = new int[MAX_K+1][N+1]; 
			depth = new int[N+1];
			ki = new int[N+1];
			
			// 1. ���� �Է��� �޴´�
			for (int j=1; j<=N; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); 
				int b = Integer.parseInt(st.nextToken());
				adjList[a].add(j);	// j�� �θ�� a�̰�
				ki[j] = b;			// j�� Ű�� b�̴�
			}
			
			
			// 2. bfs�� ���鼭 depth�� visited���ٰ� ���ϰ�, �ٷ� �� �θ��� parent[0][v]�� �Է��ؼ� dp�� ������ �� �ִ� ȯ���� ������
			bfs(1, 0);	// ������1, depth0
			
			// 3. parent[k][v]���� ���Ѵ�, ��ȭ���� p[k][v] = p[k-1][p[k-1][v]]��
			aces_find();

			bw.append("#").append(String.valueOf(i));
			for(int j=1; j<=Q; j++) {
				st = new StringTokenizer(br.readLine());
				int K = Integer.parseInt(st.nextToken());
				long r=0;
				if(K==1) {
					int temp = Integer.parseInt(st.nextToken());
					r = ki[temp];
				} else if(K==2) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					r = ki[lca(x, y)];
				} else {
					int temp[] = new int[K];
					for(int k=0; k<K; k++) {
						temp[k] = Integer.parseInt(st.nextToken());
					}
					int x = temp[0];
					//�ּҰ��������� ������� ������ k����� ��������� ��� ���Ѵ�
					int res=0;
					for(int k=1; k<K; k++) {
						if(k==1) {
							res = lca(x, temp[k]);
						} else {
							res = lca(res, temp[k]);
						}
					}
					r = ki[res];
				}
				bw.append(" ").append(String.valueOf(r));
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	// bfs�� ����, ������ �����ϰ�, parent�� ���ʰ��� �������ش�
	private static void bfs(int node, int d) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(node);
		depth[node] = d;
		parent[0][node] = 0;	//root�� �θ�� 0
		ki[node] = ki[node];
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int i=0; i<adjList[now].size(); i++) {
				int next = adjList[now].get(i);
				if(depth[next] == 0) {
					q.add(next);
					depth[next] = depth[now]+1;
					parent[0][next] = now;
					ki[next] = Math.max(ki[now], ki[next]);
				}
			}
			
		}
	}
	
	// 2^n ��° ������� �� �����صд�.
	private static void aces_find() {
		for (int K = 1; K < MAX_K; K++) {
			for (int V = 1; V <= N; V++) {
				parent[K][V] = parent[K - 1][parent[K - 1][V]];
			}
		}
	}

	// x �� y �� ���� ���� ã��
	private static int lca(int x, int y) {
		// ū���� �ڷ�
		if (depth[x] > depth[y]) {
			int temp = x;
			x = y;
			y = temp;
		}
		
		// "y �� depth" �� "x �� depth" �� ������ ������ y �� ����ø� = ���� ���߱�
		for (int i = MAX_K; i >= 0; i--) {
			if (depth[y] - depth[x] >= (1 << i)) { // Math.pow(2, i)�� �ð��ʰ�����
				y = parent[i][y]; // ����ø�
			}
		}
		
		// x, y�� depth�� ����������.
		// ������ ���̱��� ����÷��� ��, x �� y �� ���ٸ� ���� ������ ���ٴ� ���̴�
		// �װ��� ���� ���� �����̴�.
		if (x == y)
			return x;  // LCA

		
		// 1) x �� y �� ���� �ʴٸ�, ��Ʈ�������� ó������ ������ ���� ���� ������ ���� ������ Ž��
		// 2) ���� ���� �������� �̵� 
		// 3) 1 ~ 2�ܰ� �ݺ�
		//    (ó������ �޶��� ��ġ���� �׵��� �θ� �� ���� ������ �ٽ� ã��)
		// ó������ �޶��� ��ġ�� ã�Ҵٸ�
		// �ű�� �̵��ؼ� �� �� ������ ���� �ּҰ��������� ã�� ������ �ȴ�. 
		for (int i = MAX_K; i >= 0; i--) {
			if (parent[i][x] != parent[i][y]) {
				x = parent[i][x];
				y = parent[i][y];
			}
		}

		return parent[0][x];
	}
	
}


/*
 * �ּҰ������� LCA
 * 1. BFS�� ���ؼ� ��� �������� depth�� ���Ѵ� -> �����迭�� �����صд�
 * 2. �� ������ depth�� ����� -> �θ��������� �ð��ܴٴ� ���̴ϱ� �̴� BFSŽ���ϸ鼭 depth�� �����Ҷ� �θ����������� parent�迭�� �����صд�
 *    Ʈ���� 1���̸� ��Ʈ ã�µ� �־��� ������ �ȴ� 1��Ʈ���� 10���� ���� 10���� ��Ʈ�� ã�⶧���� ������
 *    �׷��� parent�迭�� 1���� �迭�� �ٷ� �� ���� �������� �ʰ� parnet[k][v]�� 2���� �迭�� 2^k��° ���� ������ȣ�� �����Ѵ�.
 *    ex) parent[1][4] -> 4������� 2^1��° ����, parent[16][7] 7�� ����� 2^4��° ���� 
 *    ���� parent[k][v]�� ��ȭ������ ǥ�� �����ϴ� 
 *    parent[k][v] = parent[k-1][parent[k-1][v]] -> 2^(k+1) = 2^k+2^k == 2�� k+1�¹�° �θ�� 2��k�¹�° �θ��� 2�� k�¹�° �θ�� ����
 *    2�������� ���ؼ� ��ȭ���� �����͸� dp�� �� ����Ѵ�
 *    ���� �� ������ depth�� ���ߴ� �뵵�� parent�迭�� ���� 2^k�¾� �ö󰡼� ������ ���� depth�� �����
 *    ���̰� �����ϰ� ���������� LCA�� 2^k�¾� ���ϸ鼭 �ö󰡸鼭 ã��
 *    MAXK�� ����� ������ �ʰ��ϴ� �ּ� 2�ǹ�� ���̴� -> Math.pow�� �ϸ� �ð��ʰ� ���ϱ� 1 << i �̷��� 2^i ���� ���ؾ��Ѵ�
 *    depth���̰� 11�̶�� �Ҷ�
 *    if(depth[y] - depth[x] >= (1<<k){ 1<<i�� �پ��鼭 (i�� --) 	ó�� �����ϴ� ���� i�� 3�ΰ�쿡 ó�� �����ϴϱ� 
 *    	y = depth[k][y] depth���̰� 11�̴ϱ� 8��ŭ �ø��� �۾��ϴ� -> �������� k�� 3�̴�
 *    }
 *    ������ ���絵 ������ LCA�̰� 
 *    ��κ��� ���� �������̴�,
 *    �̷��� ��Ʈ���� ó������ ������ ���� ���� ������ �����x������ Ž���ϰ�
 *    ó������ ���� ���� ��ġ�� ������ �׵��� �θ� �� ���� ������ �ٽ� ã�´�
 *    
 *    int i=MAXK; i>=0; i--�ϸ鼭 for���� ���µ�,
 *    	if(parent[i][x] != parent[i][y])	//�޶����� ù ��ġ�� x�� y�� ���� �÷��ش�
 *    		x = parent[i][x]
 *    		y = parent[i][y]
 *    	}
 *    �̷��� ������ ������ ��� �ø��ٺ��� �ּҰ��������� �������°��̴�
*/
