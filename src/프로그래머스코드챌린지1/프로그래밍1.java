package ���α׷��ӽ��ڵ�ç����1;
import java.util.*;

public class ���α׷���1 {
	 public int solution(int n) {
	        int answer = 0;
	        //�ݴ�� �� �迭
	        List<Integer> conver = convert(n);
	        for(int temp = 0; temp<conver.size(); temp++) {
	        	answer += Math.pow(3, conver.size()-1-temp)*conver.get(temp);
	        }

	        return answer;
	    }
	 
	 //3���� ��ȯ, �ݴ�� �� �迭�� ��ȯ�ϴ� �żҵ�
	 public List<Integer> convert(int n) {
		 //�־��� �� 
		 int num = n;
		 //�������� ������ �迭
		 List<Integer> remain = new ArrayList<>();
		 
		 while(num != 0) {
			 //������
			 int temp = num%3;
			 //���ο� ��
			 num = num/3;
			 remain.add(temp);
		 }
		 
		 
		 return remain;
	 }
	 
	 public static void main(String[] args) {
		 ���α׷���1 a = new ���α׷���1();
		 System.out.println(a.solution(125));
	 }

}
