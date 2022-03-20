package middle.day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * [����P-0031] �ִ�� �ּ� 
 * http://182.193.11.65/common/satti/practiceProblem.do?problemId=AWWt-lFA-hGojUGz
 * @author woojin2.choi
 * @hint ������ �ִ� �ּ� �� �̷����� ������ ���׸�Ʈ Ʈ����
 */
public class Solution04 {
	
	private static int N;
	private static int Q;
	private static int[] minSegTree;
	private static int[] maxSegTree;
	//private static int[] sumSegTree;
	private static int SIZE;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<T; i++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			// ���� ����Ʈ���� ������ �ϱ� ������ ������ 2�� �������� ���ϴ� ���, ���� 2�� �α״� ���⶧���� log e�� ���Ʒ��� �־��༭ log 2�� ����� ����
			SIZE = (int)Math.pow(2, (int)(Math.log(N)/Math.log(2))+1);	
			
			int v=0;
			int cnt=1;
			minSegTree = new int[2*SIZE];
			maxSegTree = new int[2*SIZE];
			
			st = new StringTokenizer(br.readLine());
			for(int x=SIZE; x<2*SIZE; x++) {
				minSegTree[x] = Integer.MAX_VALUE;
				maxSegTree[x] = Integer.MIN_VALUE;
				if(cnt <= N) {					// ���׸�Ʈ Ʈ���� �ʱ�ȭ �޼ҵ� ������ �ʿ���� �׳� update�� �����ص� ���ɻ� ����
					v = Integer.parseInt(st.nextToken());
					updateMinSegTree(cnt, v);	// cnt��°�� �������ٰ� v������ update�϶�, cnt�� N���� Ŀ���� �ʱ�ȭ�� �϶�
					updateMaxSegTree(cnt, v);	// cnt��°�� �������ٰ� v������ update�϶�, cnt�� N���� Ŀ���� �ʱ�ȭ�� �϶�
					cnt++;
				}
			}
			
			int q, a, b;
			int min = 0;
			int max = 0;
			for(int j=1; j<=Q; j++) {
				st = new StringTokenizer(br.readLine());
				q = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				if(q==0) {	// a~b �󱸰��� �ִ��ּ� ���ϰ�
					min += queryMinSegTree(a, b);
					max += queryMaxSegTree(a, b);
				} else {	// a�� b�� ������Ʈ
					updateMinSegTree(a, b);
					updateMaxSegTree(a, b);
				}
			}
			
			bw.append("#").append(String.valueOf(i+1)).append(" ").append(String.valueOf(max)).append(" ").append(String.valueOf(min));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}

	// x��° ��忡 v��� ���� �ְ� ���׸�Ʈ Ʈ���� ������Ʈ �Ѵ�
	private static void updateMinSegTree(int x, int v) {
		int node = x+SIZE-1;	// ex) 2�� ������ ���� segTree������ 9�� ��尡 ��
		minSegTree[node] = v;
		for(int i=node/2; i>=1; i=i/2) {
			minSegTree[i] = Math.min(minSegTree[i*2], minSegTree[i*2+1]);
		}
	}
	
	// left���� right������ �������� ���ϴ°��ε�
	// left�� Ȧ���̸� �ڱ��ڽ��� ���� ���ϰ� ���������� ��ĭ�̵� -> Ȧ/¦ ���� �ϳ��ε�, Ȧ�̸� ���� �������� ����̴�
	// right�� ¦���̸� ���������� �ڱ��ڽ��� ���� ���ϰ� �������� ��ĭ�̵� -> �̰͵� Ȧ/¦ ���� �ϳ��ε�, ¦����°��� �����ʰ��� ���׸�Ʈ�� �����ִٴ� �Ҹ���
    private static int queryMinSegTree(int l, int r) {
		int left = l+SIZE-1;
		int right = r+SIZE-1;
		int res = Integer.MAX_VALUE;
		for(; left<=right; left/=2,right/=2) {	// ���ʰ� �������� ������������ �ϴµ�, �ݾ� �������� ����Ʈ�� �θ�� �̵��ϰ� �ǹǷ�, ��ź�� ��ġ ���ϰ���
			if(left%2 == 1) {
				res = Math.min(res, minSegTree[left++]);
			}
			if(right%2 == 0) {
				res = Math.min(res, minSegTree[right--]);
			}
		}
		return res;
	}
	
	// x��° ��忡 v��� ���� �ְ� ���׸�Ʈ Ʈ���� ������Ʈ �Ѵ�
	private static void updateMaxSegTree(int x, int v) {
		int node = x+SIZE-1;	// ex) 2�� ������ ���� segTree������ 9�� ��尡 ��
		maxSegTree[node] = v;
		for(int i=node/2; i>=1; i=i/2) {
			maxSegTree[i] = Math.max(maxSegTree[i*2], maxSegTree[i*2+1]);
		}
	}
	private static int queryMaxSegTree(int l, int r) {
		int left = l+SIZE-1;
		int right = r+SIZE-1;
		int res = Integer.MIN_VALUE;
		for(; left<=right; left/=2,right/=2) {
			if(left%2 == 1) {
				res = Math.max(res, maxSegTree[left++]);
			}
			if(right%2 == 0) {
				res = Math.max(res, maxSegTree[right--]);
			}
		}
		return res;
	}
	
	/* sum�Ҷ��� �̰� ����� min, max, sum ��� ����� �Ȱ���, ���� mod������ �־ �й��Ģ�� �Ǵϱ� �׳� ����ǰ�.. */
	/*
	// x��° ��忡 v��� ���� �ְ� ���׸�Ʈ Ʈ���� ������Ʈ �Ѵ�
	private static void updateSumSegTree(int x, int v) {
		int node = x+SIZE-1;	// ex) 2�� ������ ���� segTree������ 9�� ��尡 ��
		sumSegTree[node] = v;
		for(int i=node/2; i>=1; i=i/2) {
			sumSegTree[i] = sumSegTree[i*2] + sumSegTree[i*2+1];
		}
	}
	
	private static int querySumSegTree(int l, int r) {
		int left = l+SIZE-1;
		int right = r+SIZE-1;
		int res = 0;
		for(; left<=right; left/=2,right/=2) {
			if(left%2 == 1) {
				res = res + sumSegTree[left++];
			}
			if(right%2 == 0) {
				res = res + sumSegTree[right--];
			}
		}
		return res;
	}
	*/
		
}


