package 윈터코딩코딩테스트;
import java.util.*;

public class 프로그래밍1 {
	 public String solution(int n, int[][] delivery) {
		 //value O: 재고있음
		 //value X: 재고없음
		 //value ?: 재고모름
		 HashMap<Integer, String> gift = new HashMap<>();
		 for(int i = 0; i<n; i++) {
			 //처음에는 모두 모르는 상태로 설정
			 gift.put(i+1, "?");
		 }
		 //배송을 한 경우 재고가 있으므로 재고 있음으로 설정
		 for(int i = 0; i<delivery.length; i++) {
			 if(delivery[i][2]==1) {
				 gift.put(delivery[i][0], "O");
				 gift.put(delivery[i][1], "O");
			 }
		 }
		 //배송을 하지 않았는 데 한개가 재고 있는 경우 나머지 하나는 무조건 재고 없다
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
		프로그래밍1 a = new 프로그래밍1();
		System.out.println(a.solution(n, delivery));

	}

}
