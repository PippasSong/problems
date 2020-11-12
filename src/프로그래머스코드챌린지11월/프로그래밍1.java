package 프로그래머스코드챌린지11월;

public class 프로그래밍1 {
	public int solution(int[] a, int[] b) {
        int answer = 0;
        for(int i=0; i < a.length; i++) {
        	int temp = a[i]*b[i];
        	answer+=temp;
        }
        return answer;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
