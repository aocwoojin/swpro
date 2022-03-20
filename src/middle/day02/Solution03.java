package middle.day02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


/**
 * [����P-0020] �޸��� 
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AVmF7-jAeXGojUFy&_menuId=AVUU732mAAHBC0c9&_menuF=true
 * @author woojin2.choi
 * @hint MergeSort�� ������������ �����ϸ鼭 �����ϰ��� �ٽ� �պ����� �����Ҷ�, �տ� �ִ� �߿��� �� �ִ� ������� ���� ���ؼ� �����ϴ� ���
 * @hint ������� int�� �ȳ��ü������Ƿ� answer�� long���� �����ؾ���
 */
public class Solution03 {
	
	private static int T;
	private static int N;	//�޸��⼱��
	private static long answer = 0;
	private static int[] array;
	private static int[] sortedArray;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++) {
			 N = Integer.parseInt(br.readLine());
			 st = new StringTokenizer(br.readLine());
			 answer = 0;
			 array = new int[N];
			 sortedArray = new int[N];
			 
			 for(int j=0; j<N; j++) {
				 array[j] = Integer.parseInt(st.nextToken());
			 }
			 
			 mergeSort(array, 0, N-1);
			 
			 bw.append("#").append(String.valueOf(i)).append(" ").append(String.valueOf(answer));
			 bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void mergeSort(int[] array, int left, int right) {
		if(left<right) {
			int mid = (left+right)/2;
			mergeSort(array, left, mid);
			mergeSort(array, mid+1, right);
			merge(array, left, mid, right);
		}
	}
	
	private static void merge(int[] array, int left, int mid, int right) {
		// MergeSort�� �ٽ�, while���� ����
		int i=left;		//���յ� ���� �迭�� ������ġ
		int j=mid+1;	//���յ� ���� �迭�� ������ġ
		int k=left;		//������ ����� �����ϴ� �迭�� ������ġ
		
		while(i<=mid && j<=right) {	//�� �迭�� �ϳ��� ���������� ����
			if(array[i]<array[j]) {	//������������ �����ذ��鼭 �����ϴ°�쿡�� ���� �����Ѵ�
				answer = answer+(mid-i+1); //���ʹ迭�� Ŀ��(i)�������� ���ʹ迭�� ��(mid)������ �����̹Ƿ� mid-i+1�� ���� �迭�� ��ġ�� ã�ư��� ������ -> �׸��׷�������
				sortedArray[k] = array[j];
				j++;
			} else {
				sortedArray[k] = array[i];
				i++;
			}
			k++;
		}
		
		// ���� �迭�� ������� ������ ���� �迭�� sotedArray���ٰ� �ϰ������Ѵ�.
		if(i>mid) {
			while(j<=right) {
				sortedArray[k] = array[j];
				k++; 
				j++;
			}
		} else {
			while(i<=mid) {
				sortedArray[k] = array[i];
				k++; 
				i++;
			}
		}
		
		// �����迭���ٰ� ���ĵȰ��� �����Ѵ�
		for(int t=left; t<=right; t++) {
			array[t] = sortedArray[t];
		}
		
	}

}
