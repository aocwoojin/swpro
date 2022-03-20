package beginner.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution02 {

	public static class emp implements Comparable<Object>{
		int age;
		int cl;
		public emp(int age, int cl) {
			this.age = age;
			this.cl = cl;
		}
		
		@Override
		public int compareTo(Object o) {
			
			// 1�������� cl�� �����ϰ� 2�������� ���̷� ������������ ����
			if(this.cl == ((emp)o).cl) {	// cl�� �������� ������������ �Ѵ�
				return ((emp)o).age - this.age;
			} else {
				return this.cl - ((emp)o).cl;
			}
			
			
		}
	}
	
	/**
	 * Arrays.sort -> ����Ǻ� �������̶� unstable sort�� : ������ ������ ������ �������� �ʴ´� 
	 * @param args
	 */
	
	public static void main(String args[]) {
	
		ArrayList<emp> al = new ArrayList<>();
		
		al.add(new emp(50, 4));
		al.add(new emp(32, 2));
		al.add(new emp(38, 3));
		al.add(new emp(35, 3));
		al.add(new emp(30, 2));
		al.add(new emp(51, 4));
		al.add(new emp(28, 1));
		
		Collections.sort(al);
		
		for(int i=0; i<al.size(); i++) {
			System.out.println("("+al.get(i).cl+","+al.get(i).age+")");
		}
		
		ArrayList<emp> al1 = new ArrayList<>();
		
		al1.add(new emp(50, 4));
		al1.add(new emp(32, 2));
		al1.add(new emp(38, 3));
		al1.add(new emp(35, 3));
		al1.add(new emp(30, 2));
		al1.add(new emp(51, 4));
		al1.add(new emp(28, 1));
		
		//Arrays.sort(al1);
	}
}
