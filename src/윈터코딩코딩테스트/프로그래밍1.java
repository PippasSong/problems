package �����ڵ��ڵ��׽�Ʈ;
import java.util.*;

public class ���α׷���1 {
	 public String solution(int n, int[][] delivery) {
		 //value O: �������
		 //value X: ������
		 //value ?: ����
		 HashMap<Integer, String> gift = new HashMap<>();
		 for(int i = 0; i<n; i++) {
			 //ó������ ��� �𸣴� ���·� ����
			 gift.put(i+1, "?");
		 }
		 //����� �� ��� ��� �����Ƿ� ��� �������� ����
		 for(int i = 0; i<delivery.length; i++) {
			 if(delivery[i][2]==1) {
				 gift.put(delivery[i][0], "O");
				 gift.put(delivery[i][1], "O");
			 }
		 }
		 //����� ���� �ʾҴ� �� �Ѱ��� ��� �ִ� ��� ������ �ϳ��� ������ ��� ����
		 for(int i = 0; i<delivery.length; i++) {
			 if(delivery[i][2]==0) {
				 if(gift.get(delivery[i][0]).equals("O")) {
					 gift.put(delivery[i][1],"X");
				 } else if (gift.get(delivery[i][1]).equals("O")) {
					 gift.put(delivery[i][0],"X");
				 }
			 }
		 }
	        String answer = "";
	        for(int i = 1; i<n+1; i++) {
	        	answer += gift.get(i);
	        }
	        return answer;
	    }

	public static void main(String[] args) {
		//int n = 6;
		//int[][] delivery = {{1,3,1},{3,5,0},{5,4,0},{2,5,0}};
		int n = 7;
		int[][] delivery = {{5,6,0},{1,3,1},{1,5,0},{7,6,0},{3,7,1},{2,5,0}};
		���α׷���1 a = new ���α׷���1();
		System.out.println(a.solution(n, delivery));

	}

}
