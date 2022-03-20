package middle.day02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


/**
 * [����A-0026] �����̰� ���� �� 
 * http://182.193.11.65/common/satti/practiceProblem.do?problemId=AVkBFrMAY1uojUGs
 * @author woojin2.choi
 * @hint array�� �����Ͽ� ������Ʈ ��(�Ǿ�, �ǵ�)�� ���鼭 �� ���� ���� ũ�� �ڿ��� ��ĭ ���̰�, �� ���� ���� ������ �տ��� ��ĭ �ø���.
 */
public class Solution02 {
	
	private static int T;
	private static int X;	//������ �ʺ�
	private static int N;	//����� ����
	private static int[] array;	//��� �迭
	private static int L1, L2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++) {
			X = Integer.parseInt(br.readLine());
			N = Integer.parseInt(br.readLine());
			
			//���迭 �ʱ�ȭ
			array = new int[N];
			//cm�� nano�� �ٲ�
			X = X*10000000;
			L1 = L2 = 0;
			
			for(int j=0; j<N; j++) {
				array[j] = Integer.parseInt(br.readLine());
			}
			
			//�迭�� �����Ѵ�
			Arrays.sort(array);
			
			int j=0;
			int k=array.length-1;
			boolean available = false;
			while(N!=0) {	//�� ��... N�� 0�� �׽�Ʈ ���̽��� ���� ������
				if(j==k) {	
					break;
				}
				if(array[j]+array[k] > X) {	//���ڰ� ũ�� �������� ���̰�
					k--;
				} else if(array[j]+array[k] < X) {	//���ڰ� ������ ������ �ø���
					j++;
				} else {	//�������� ��������
					L1 = array[j];
					L2 = array[k];
					available = true;
					break;
				}
			}
			
			if(available) {
				bw.append("#").append(String.valueOf(i)).append(" yes ").append(String.valueOf(L1)).append(" ").append(String.valueOf(L2));
				bw.newLine();
			} else {
				bw.append("#").append(String.valueOf(i)).append(" danger");
				bw.newLine();
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
