package middle.day07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * [����A-0037] �ٽɵ���1
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AXAeBo1w7JyojUHL&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint dfs�� �����ϸ鼭 �湮�� ������ order���ϰ�, dfs ���� ������ low���� ����Ͽ� ������ ū���� �Ǻ�
 *       �Ǵ� dfs�����Ҷ��� child���� 2���� ū��쿡�� �������� �ȴ�.
 */
public class Solution02 {
	
	private static int T;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc<= T; tc++) {
			st = new StringTokenizer(br.readLine());
		}

	}
	
	/*
	static ArrayList<Integer>[] adjList; // 1 -> 2,3
	static int[] order; // DFS ���� �湮 ���� �����
	static boolean[] isCutVertex; // ������ ���� �����
	static int number; // ���� �湮 ����

	
	// * 7 7
	// * 1 4
	// * 4 5
	// * 5 1
	// * 1 6
	// * 6 7
	// * 2 7
	// * 7 3
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); // ���� ��
		int E = Integer.parseInt(st.nextToken()); // ���� ��

		adjList = new ArrayList[V + 1];
		order = new int[V + 1];
		isCutVertex = new boolean[V + 1];
		number = 1; // ���� �湮 ������ 1�� ����

		for (int i = 1; i <= V; i++)
			adjList[i] = new ArrayList();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);
		}

		dfs(1, true);

		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if (isCutVertex[i]) {
				sb.append(i).append(" ");
				cnt++;
			}
		}
		System.out.println(cnt + "\n" + sb);
	}

	// ������ Ž�� : DFS Spanning Tree
	static int dfs(int here, boolean isRoot) {

		// DFS Ž�� ���� ����, low �� �ʱⰪ�� �ڱ� �ڽ��� order
		int min = order[here] = number++;

		int child = 0; // �ڽ� �� count

		for (int next : adjList[here]) {
			
			// * ���� �̹� DFS���� Ž���� �����̶��
			// * ���� ������ �湮������ Ž���� ������ �湮 ���� ��
			// * min��(=low)�� ã�´�.
			if (order[next] > 0) {
				min = Math.min(min, order[next]);
				continue;
			}

			child++;
			int low = dfs(next, false);

			
			// * ���� A�� ���� ����(root)�� �ƴ� ��
			// * A�� �������� �ڽ� ������ ���� A�� ��ġ�� �ʰ�
			// * ���� A���� ���� �湮��ȣ�� ���� �������� �� �� ���ٸ� �������̴�.
			if (!isRoot && order[here] <= low)
				isCutVertex[here] = true;

			min = Math.min(min, low);
		}

		// * ���� A�� ���� ����(root)�� �� �ڽ� ���� 2�� �̻��̸� �������̴�.
		if (isRoot)
			isCutVertex[here] = (child >= 2);

		return min;
	}
	*/

}

/*
 * �������� dfs�� ��ȸ�ϸ鼭 �ϴ� order�� ����Ѵ�.
 * 
 *    2			6
 * 1     4  5  
 *    3			7
 *    
 * �̷���쿡 dfs�� 1>2>4>5>6>3 ������ �湮�Ѵ�.
 * 1�� �������� �ƴ������� dfs�������� child���� 1�̴� 
 * 4�� �������� ������ 4�� child�� 2�̴� 4���� ���ƿԴٰ� �湮���� ���� 3�� �湮�߱⶧���� child�� 2�ΰ��̴�.
 * dfs�� �������� �ڽź��� �Ʒ������� �湮�ϴ� ������ order���� min���� ������ order������ ������ �������� �ƴϴ� -> ������ ���� �� ���� ������ ����
 * 
*/
