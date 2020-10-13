package 프로그래머스코드챌린지1;
import java.util.*;

public class 프로그래밍1 {
	 public int solution(int n) {
	        int answer = 0;
	        //반대로 된 배열
	        List<Integer> conver = convert(n);
	        for(int temp = 0; temp<conver.size(); temp++) {
	        	answer += Math.pow(3, conver.size()-1-temp)*conver.get(temp);
	        }

	        return answer;
	    }
	 
	 //3진법 변환, 반대로 된 배열을 반환하는 매소드
	 public List<Integer> convert(int n) {
		 //주어진 수 
		 int num = n;
		 //나머지를 저장할 배열
		 List<Integer> remain = new ArrayList<>();
		 
		 while(num != 0) {
			 //나머지
			 int temp = num%3;
			 //새로운 몫
			 num = num/3;
			 remain.add(temp);
		 }
		 
		 
		 return remain;
	 }
	 
	 public static void main(String[] args) {
		 프로그래밍1 a = new 프로그래밍1();
		 System.out.println(a.solution(125));
	 }

}
