package middle.day01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [����A-0028] ������ 
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AVhMrR7QJyOojUH_&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint ��������, 2�����迭�� ������ġ�� ����� ������ �߶����� 4������ ��ͷ� �����.
 *
 */
public class Solution01 {
	
	private static int[][] PAPER;
	private static int cnt0 = 0;
	private static int cnt1 = 0;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			// ���� ����
			PAPER = new int[N][N];
			// �ʱ�ȭ
			cnt0=0;
			cnt1=0;
			// ���� �ʱ�ȭ
			for(int j=0; j<N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0; k<N; k++) {
					PAPER[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			// Ž��
			getPaperCount(PAPER, 0, 0, N);
			System.out.println("#"+i+" "+cnt0+" "+cnt1);
			
		}
	}
	
	private static void getPaperCount(int[][] paper, int start_x, int start_y, int size) {
		if(size==0) {
			return;
		}
		
		int num = getValue(paper, start_x, start_y, size);

		if(num != -1) {		// paper�� �� ���ڷ� �Ǿ��ְų�, ����� 1�̸� �� ���� ī��Ʈ �ø��� ����
			if(num == 0) {
				cnt0++;
			} else {
				cnt1++;
			}
			return;
		} else {			// �ƴϸ� ������ ���
			getPaperCount(paper, start_x, start_y, size/2);	//������
			getPaperCount(paper, start_x, start_y+size/2, size/2);	//��������
			getPaperCount(paper, start_x+size/2, start_y, size/2);	//���ʾƷ�
			getPaperCount(paper, start_x+size/2, start_y+size/2, size/2);	//�����ʾƷ�
		}
	}
	
	// �Ѱ��� ���ڷ� �Ǿ������� �� ���ڸ�, �ƴϸ� -1�� ����
	private static int getValue(int[][] paper, int start_x, int start_y, int size) {
		int startVal = paper[start_x][start_y];
		boolean check = true;
		
		for(int i=start_x; i<start_x+size; i++) {
			for(int j=start_y; j<start_y+size; j++) {
				if(paper[i][j]==startVal) {
					check = true;
				} else {
					check = false;
					break;
				}
			}
			if(!check) {
				break;
			}
		}
		
		if(!check) {
			return -1;
		} else {
			return startVal;
		}
	}

}









