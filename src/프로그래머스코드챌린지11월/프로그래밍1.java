package ���α׷��ӽ��ڵ�ç����11��;

public class ���α׷���1 {
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
