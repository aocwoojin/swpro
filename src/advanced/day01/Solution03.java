package advanced.day01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * [����P-0088] [2021�� 01�� 24��] ȭ�� 
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AXc2jKH2YajBC0Gg
 * @author woojin2.choi
 * @hint �ε���Ʈ���� Ǯ��, ������ ������+��ȣ������������ �����ϰ��� ������ ���������� �ε��� Ʈ���� �μ�Ʈ�Ѵ�
 *       �μ�Ʈ�Ѵ����� �ڽź��� ���� ������ ����+��+1�� ������ ������ ������ �Ǵ°�
 */
public class Solution03 {
	
	private static int T;
	private static int N; //�ǹ��Ǽ�
	private static int offset = 1;
	private static Building[] Buildings;
	private static int[] P;
	private static int[] tree;
		
	private static class Building{
		int index;
		int height;
		Building(int index, int height){
			this.index = index;
			this.height = height;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			
			
			
			//�ǹ�����, �ǹ������� ������ 2���������� �������̰� �� ������ ������尪�� ������ �ȴ�.
			N = Integer.parseInt(br.readLine());
			
			Buildings = new Building[N];	//�ǹ�����
			Buildings[0] = new Building(0,0);
			P = new int[N+1];	//ȭ���Ŀ�
			
			// �ǹ����̿� ��ȣ �迭 ����
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				Buildings[i] = new Building(i, Integer.parseInt(st.nextToken()));
			}
			// ȭ���Ŀ� �迭 ����
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				P[i] = Integer.parseInt(st.nextToken());
			}
			
			// �ΉH��Ʈ���� �������� ����
			while(offset < N) {
				offset = offset * 2;
			}
		
			
			tree = new int[2*offset];	//�ε���Ʈ������
			
			// Buildings�迭�� �����ϴµ�, Ű��ū��, Ű�� ������ �ε����� ������ ������ ����!
			Arrays.sort(Buildings, new Comparator<Building>() {
				@Override
				public int compare(Building o1, Building o2) {
					if(o1.height == o2.height) {
                        return o1.index - o2.index;
                    } else {
                        return o2.height - o1.height;
                    }

				}
			});
			
			long ans = 0;
			
			for(int i=0; i<N; i++) {
                Building building = Buildings[i];
                int min = getLowerBuilding(offset, offset + building.index);
                ans = ans + getBuilding(min + P[building.index] + 1);
                update(offset + building.index);
            }
			
			bw.append("#").append(String.valueOf(tc)).append(" ").append(String.valueOf(ans));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();  
		
	}
	
	private static void update(int pos) {
		while(pos >= 1) {
			 tree[pos] = tree[pos] + 1;
			 pos = (pos / 2);
		}
	}

	// �ε��� Ʈ������ ������ ������ ������ ���ϴ°�
	// offset~offset+building.index������ ������ ���ϴ°��� ���� ������ ���� ������ ��ΰ��� �˱������̴�.
	// ������ ���� ������ ������, �� �������� ���� ȭ���Ŀ��� �հ� ���� ���� + 1�� �ϸ� ������ �Ǵ°��̴�
	// ���������� ȭ���� �ȸ����ϱ� �� �ڽź��� ���� ������ ȭ���Ŀ��� �հ� ���°��� -> �ε��� Ʈ���� ������������ �����ϴ� ������ �װ���
	private static int getLowerBuilding(int left, int right) {
		int ans = 0;
		for(;left<=right;left/=2,right/=2) {
			if(left%2 == 1) {
				ans = ans + tree[left++];
			}
			if(right%2 == 0) {
				ans = ans + tree[right--];
			}
		}
		return ans;
	}
	
	// �ε��� Ʈ������ ���� ���Ե� ������ �߿��� ���° ������ �������� �����ΰ�?
	private static int getBuilding(int nth) {
		int pos = 1;
		if(tree[pos] < nth) {
			return 0;
		} else {
			while(pos < offset) {
				if(tree[pos*2] >= nth) {
					pos = pos*2;
				} else {
					nth -= tree[pos*2];
					pos = pos*2+1;
				}
			}
			return pos-offset+1;
		}
	}
	
}
