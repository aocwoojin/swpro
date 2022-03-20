package advanced.day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
//���߿� �ٽ� Ǯ���
/**
 * [����P-0074] 0 to 99 
 * @author woojin2.choi
 * @hint ����, �Ľ�Į�� �ﰢ���� �̿��ؼ� ���� ��� ������������ ���ع����鼭 ����ϸ� �Ǵµ� �� ��ǰ
 */
public class Solution01 {
 
    public static void main(String[] args) throws NumberFormatException, IOException {
 
        int pascal[][] = new int[1001][1001];
 
        // O(N^2) // �Ľ�Į �̸� ���
        for (int i = 2; i < 1000; i++) {
            pascal[i + 1][0] = 1;
            for (int j = 1; j < i - 1; j++) {
                pascal[i + 1][j] = (pascal[i][j - 1] + pascal[i][j]) % 10;
            }
            pascal[i + 1][i - 1] = 1;
        }
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            int N = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());
 
            int[] A = new int[1000];
            int[] B = new int[1000];
 
            /// ī��迭
            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; st.hasMoreTokens();) {
                A[i++] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; st.hasMoreTokens();) {
                B[i++] = Integer.parseInt(st.nextToken());
            }
 
            int AA10 = 0;
            int BB10 = 0;
            int AA1 = 0;
            int BB1 = 0;
 
            // ���� ī��� ���� �� ���ϱ�
            for (int i = 0; i < N - 1; i++) {
                AA10 += A[i] * pascal[N][i];
                BB10 += B[i] * pascal[N][i];
                AA1 += A[i + 1] * pascal[N][i];
                BB1 += B[i + 1] * pascal[N][i];
                 
                // ��� 10���� ������ ������
                AA10 %= 10;
                BB10 %= 10;
                AA1 %= 10;
                BB1 %= 10;
            }
 
            int AResult = 0;
            int BResult = 0;
 
            // Q �ݺ��ϸ鼭 ó���ϱ� -> O(Qx1000) -> O(Qx1) �� ���̴°� �н��� ����
            for (int i = 0; i < Q; i++) {
 
                st = new StringTokenizer(br.readLine().trim());
                int turn = Integer.parseInt(st.nextToken());
                 
                // 1���϶� ī�屳ü
                if (turn == 1) {
                    int s1 = Integer.parseInt(st.nextToken()) - 1;
                    int s2 = Integer.parseInt(st.nextToken()) - 1;
 
                    int AGap10 = s1 < N - 1 ? (B[s2] - A[s1]) * pascal[N][s1] : 0; // s1 < N - 1 �ǹ� : ������ ī�尡 ������ ī��� ���� ���� ����
                    int BGap10 = s2 < N - 1 ? (A[s1] - B[s2]) * pascal[N][s2] : 0;
 
                    int AGap1 = s1 > 0 ? (B[s2] - A[s1]) * pascal[N][s1-1] : 0; // s1 < N - 1 �ǹ� : ������ ī�尡 ù��° ī��� ���� ���� ����
                    int BGap1 = s2 > 0 ? (A[s1] - B[s2]) * pascal[N][s2-1] : 0;
 
                    // A�� 10�� ����, B�� 10�� ���� ���
                    AA10 = (AA10 + (AGap10 + 1000)) % 10; // pascal�� ���ϰ� ����ó���� �ϱ⶧���� ������ ū 10�� ����� ����
                    BB10 = (BB10 + (BGap10 + 1000)) % 10; // pascal�� ���ϰ� ����ó���� �ϱ⶧���� ������ ū 10�� ����� ����
 
                    // A�� 1�� ����, B�� 1�� ���� ���
                    AA1 = (AA1 + (AGap1 + 1000)) % 10; // pascal�� ���ϰ� ����ó���� �ϱ⶧���� ������ ū 10�� ����� ����
                    BB1 = (BB1 + (BGap1 + 1000)) % 10; // pascal�� ���ϰ� ����ó���� �ϱ⶧���� ������ ū 10�� ����� ����
 
                    // ī�� ��ü
                    int temp = B[s2];
                    B[s2] = A[s1];
                    A[s1] = temp;
                     
                } else if (turn == 2) {
                    // 2���϶� A ȥ�� ī�屳ü
                    int s1 = Integer.parseInt(st.nextToken()) - 1;
                    int s2 = Integer.parseInt(st.nextToken()) - 1;
 
                    int AGap10_1 = s1 < N - 1 ? (A[s2] - A[s1]) * pascal[N][s1] : 0;
                    int AGap10_2 = s2 < N - 1 ? (A[s1] - A[s2]) * pascal[N][s2] : 0;
 
                    int AGap1_1 = s1 > 0 ? (A[s2] - A[s1]) * pascal[N][s1-1] : 0;
                    int AGap1_2 = s2 > 0 ? (A[s1] - A[s2]) * pascal[N][s2-1] : 0;
 
                    AA10 = (AA10 + (AGap10_1 + AGap10_2 + 1000)) % 10; // pascal�� ���ϰ� ����ó���� �ϱ⶧���� ������ ū 10�� ����� ����
                    AA1 = (AA1 + (AGap1_1 + AGap1_2 + 1000)) % 10; // pascal�� ���ϰ� ����ó���� �ϱ⶧���� ������ ū 10�� ����� ����
 
                    int temp = A[s2];
                    A[s2] = A[s1];
                    A[s1] = temp;
                     
                } else if (turn == 3) {
                    // 3���϶� B ȥ�� ī�屳ü
                    int s1 = Integer.parseInt(st.nextToken()) - 1;
                    int s2 = Integer.parseInt(st.nextToken()) - 1;
 
                    int BGap10_1 = s1 < N - 1 ? (B[s2] - B[s1]) * pascal[N][s1] : 0;
                    int BGap10_2 = s2 < N - 1 ? (B[s1] - B[s2]) * pascal[N][s2] : 0;
 
                    int BGap1_1 = s1 > 0 ? (B[s2] - B[s1]) * pascal[N][s1-1] : 0;
                    int BGap1_2 = s2 > 0 ? (B[s1] - B[s2]) * pascal[N][s2-1] : 0;
 
                    BB10 = (BB10 + (BGap10_1 + BGap10_2 + 1000)) % 10;
                    BB1 = (BB1 + (BGap1_1 + BGap1_2 + 1000)) % 10;
 
                    int temp = B[s2];
                    B[s2] = B[s1];
                    B[s1] = temp;
                } else if (turn == 4) {
                    // 4���϶� ���� ī������ ���ϱ�
                    int ACur = AA10 * 10 + AA1;
                    int BCur = BB10 * 10 + BB1;
                    AResult += ACur;
                    BResult += BCur;
                }
            }
            System.out.println("#"+tc+" " + AResult + " " + BResult);
        }
    }
 
}
