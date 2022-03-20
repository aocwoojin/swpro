package advanced.day04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [����P-0070] ���൹ ���� 
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AXQoWJ7wZJyojUHh&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint �������ķ� Ǫ�°��̶�µ� �� ��ǰ
 */
public class Solution03 {
    static int N, M, K, a, b, p, q, nodeK, cnt, cur;
    static long result;
    static ArrayList<Integer> adjList[];
    static int indegree[];
 
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        int T = Integer.parseInt(br.readLine().trim());
 
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
 
            adjList = new ArrayList[N + 1];
            indegree = new int[N + 1];
 
            for (int i = 1; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }
 
            result = 0;
 
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                p = Integer.parseInt(st.nextToken());
                q = Integer.parseInt(st.nextToken());
                // ���ἱ���� �� ���� ������ ȹ���ϴ� ��� ������ ��, �� ū ������ ȹ���ϴ� ��� ������ ������ ����
                // ������ ū ���� -> ���� ������ ���ϰ� �Ǵ� ���Ⱓ������ �ϰ� ��ȯ 
                // when p < q then a <- b
                // when p > q then a -> b
                if (p < q) {
                    // a <- b
                    indegree[a]++;
                    adjList[b].add(a);
                    result += q;
                } else if (p > q) {
                    // a -> b
                    indegree[b]++;
                    adjList[a].add(b);
                    result += p;
                } else {
                    // p,q�� ������ ��� �ϸ� ������� �ջ� (� ���൹�� �̾Ƶ� �� ���� �����Ƿ�, �ִ� ������ ó������ �ջ�)
                    result += p;
                }
            }
 
             
            PriorityQueue<Integer> pq = new PriorityQueue<>();
 
            for (int i = 1; i <= N; i++) {
                //indegree 0�� ���� pq�� �߰�
                if (indegree[i] == 0) {
                    pq.add(i);
                }
            }
 
            cnt = 0;
            while (!pq.isEmpty()) {
                cur = pq.poll();
                cnt++;
                if (cnt == K) {
                    nodeK = cur;    //k��° ���൹ ����
                    break;
                }
                //�������� ����� ������ Ž���ϸ� ����� ����� indegree ���� ����, indegree 0�� ���� pq�� �߰�
                for (int next : adjList[cur]) { 
                    if (--indegree[next] == 0) {
                        pq.offer(next);
                    }
                }
            }
            System.out.println("#" + testCase + " " + result + " " + nodeK);
        }
    }
}
