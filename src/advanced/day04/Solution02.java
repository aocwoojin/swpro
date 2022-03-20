package advanced.day04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
/**
 * [����P-0071] ���� Ž��  
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AXRsTTMw-16ojUHh&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint ���������ε� ��ǰ ���߿� �ٽ� Ǯ�����, �������带 �ι������� �ȴٰ��ߴµ�, �� �ҽ��� �ι������� ���� ����
 */
public class Solution02 {
    static int V, E, A;
    static final long Inf = 300000000001L;
    static long [][] D; //�Ƿε�,��ΰ���,õ���Ƚ��
    static class GRAPH{
        int end = 0;
        long cost = 0;
        GRAPH(int end, long cost){
            super();
            this.end = end;
            this.cost = cost;
        }
    }
 
    static ArrayList<GRAPH> [] Graph;
    //BUG, NO, �ּ��Ƿε�, �ּ�õ����̿� ��ΰ���
      
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
 
        int t = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=t; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
 
            Graph = new ArrayList [V];
            D = new long [V][3]; 
            for(int i=0; i<=V-1; i++) {
                Graph[i] = new ArrayList<GRAPH>();
                D[i][0] = Inf;
            }
 
            for(int i=1; i<=E; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                Graph[start].add(new GRAPH(end, cost));
                Graph[end].add(new GRAPH(start, cost));
            }
 
            for(int i=1; i<=A; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                Graph[start].add(new GRAPH(end, cost*(-1)));
            }
 
            int minus_cycle = 0;
            int update = 0;

            D[0][0] = 0; //���
            for(int i=0; i<=V-1; i++) {
                update = 0;
                for(int j=0; j<=V-1; j++) {
                    for(GRAPH next:Graph[j]) {
                        if(D[next.end][0] > D[j][0] + next.cost) {
                            D[next.end][0] = D[j][0] + next.cost;
                            D[next.end][1]++;
                            if(next.cost < 0) {
                                D[next.end][2] = D[j][2] + 1;
                            } else {
                                D[next.end][2] = D[j][2];
                            }
 
                            if(i==V-1) {
                                minus_cycle = 1;
                            }
                            
                            update++;
                         } else if (D[next.end][0] == D[j][0] + next.cost) { //�ڡ�õ�� ��� Ƚ�� �ּҰ����� ���šڡ�                      
                            if(next.cost < 0) {
                                D[next.end][2] = Math.min(D[next.end][2], D[j][2] + 1);
                            } else {
                                D[next.end][2] = Math.min(D[next.end][2], D[j][2]);
                            }
                        }
                    }                   
                }
                if(update==0) break;
            }
 
            String answer = "";
            if(minus_cycle == 1) {
                bw.write("#" + tc + " " + "BUG");
            } else {             
                if(D[V-1][0] > 1000000000L) {
                    bw.write("#" + tc + " " + "NO");
                } else {
                    bw.write("#" + tc + " " + D[V-1][0] + " " + D[V-1][2]);
                }
            }           
 
            bw.newLine();
            bw.flush();
        }
        bw.close();
        br.close();
    }
 
}
