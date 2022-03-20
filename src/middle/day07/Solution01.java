package middle.day07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * [����P-0026] ���� ���� ����
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AVmF8smgebiojUFy&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint LCA�˰����� �˰��ִ°�?, shfit������ �����ϴ°�?
 */
public class Solution01 {
	
	private static int T;
	private static int N;	//������ ����
	private static int Q;	//������ ��
	private static int[] depth;	//bfs�� visited�迭
	private static ArrayList<Integer>[] adjList;
	private static int[][] parent;	//������������ LCA�� ���� parent[k][v] �迭, k�� 2�� ������
	private static final int MAX_K = 17;	//2^16 65536�̰� 2^17�� 13....�̴ϱ� MAX_K���� 17�� ��� LCA���鼭 "��������" 
	
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
			
			// 1. ���� �Է��� �޴´�
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<N; j++) {
				int a = Integer.parseInt(st.nextToken());
				adjList[a].add(j+1);	//�θ� a�� ����� ������(j+1)���� add�Ͽ� ��������Ʈ�� �����
			}
			
			
			// 2. bfs�� ���鼭 depth�� visited���ٰ� ���ϰ�, �ٷ� �� �θ��� parent[0][v]�� �Է��ؼ� dp�� ������ �� �ִ� ȯ���� ������
			//    ������ bfs�� ���ؼ� parent[0][v]�� ���ؾ��ϴµ�, ���⼭�� �־�����, �׷��� bfs���� ������
			bfs(1, 0);	// ������1, depth0
			
			// 3. parent[k][v]���� ���Ѵ�, ��ȭ���� p[k][v] = p[k-1][p[k-1][v]]��
			aces_find();

			
			// 4. LCA������ �¿��, MAX_K���� �����ؼ� �Ʒ��� �������鼭 �ٸ������� �����ϴٰ� �޶����� ���� node�� x, y�� ���� �÷��ִ� �۾��� �ϸ� ��
			//    �׷��� ���������� �ö󰣰��� �θ� �ּҰ��������� �ȴ�.
			bw.append("#").append(String.valueOf(i));
			for(int j = 1; j <= Q; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				bw.append(" ").append(String.valueOf(lca(a, b)));
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
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int i=0; i<adjList[now].size(); i++) {
				int next = adjList[now].get(i);
				if(depth[next] == 0) {
					q.add(next);
					depth[next] = depth[now]+1;
					parent[0][next] = now;
				}
			}
			
		}
	}
	
	// 2^n ��° ������� �� �����صд�.
		static void aces_find() {
			
			// 2^0 = 1
			// K = 0 �� BFS(DFS) �� ���� �� �����صξ���
			for (int K = 1; K < MAX_K; K++) {
				for (int V = 1; V <= N; V++) {
					// K = 2, V = 17 parent[2][17] = parent[1][11]
					
					// 17�� 2�� �� �θ� = 17�� �θ��� �θ��
					// K = 1, V = 17 
					// parent[1][17] = parent[0][parent[0][17]] = parent[0][14]
					parent[K][V] = parent[K - 1][parent[K - 1][V]];
				}
			}
		}

		// x �� y �� ���� ���� ã��
		static int lca(int x, int y) {
			// 3  5
			// 7  4
			
			// ��Ʈ ���ؿ��� �� �Ʒ��ִ� ������ y, �� �����ִ� ������ x �� �����.
			// depth �� �� �۴ٴ°� ��Ʈ�� �� �����ٴ� ��
			// depth �� ū ������ y �� �����.
			if (depth[x] > depth[y]) {
				int temp = x;
				x = y;
				y = temp;
			}
			
			// depth
			// x        3
			// y        14

			
			// "y �� depth" �� "x �� depth" �� ������ ������ y �� ����ø� = ���� ���߱�
			for (int i = MAX_K; i >= 0; i--) {
				
				// depth ���̰� 11�̸�
				// 2^3 = 8 
				// 2^1 = 2
				// 2^0 = 1
				
				// 1 << i ���� �Ʒ��� ����.
				//                K             K            K               K       K       K       K
				// [i, 1 << i] = [17, 131072], [16, 65536], [15, 32768] ... [3, 8], [2, 4], [1, 2], [0, 1]
				
				// 1    ������ = 1
				// 10   ������ = 2
				// 100  ������ = 4
				
				// (1 << i) = 2 �� i �� ���ϱ�
				// MAX_K = 17
				// depth ���� 11
				
				//         11                 8
				if (depth[y] - depth[x] >= (1 << i)) { // Math.pow(2, i)
					y = parent[i][y]; // 2^k
					// depth ���� 11
					// 8��ŭ �÷����ϱ�
					// depth ���� 3
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
