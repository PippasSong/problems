package 프로그래머스코드챌린지11월;

public class 프로그래밍2 {
	int zero=0;
	
	public int[] solution(String s) {
		//실행한 수
		int runNum = 0;
        int[] answer = new int[2];
        //복사
        String copy = new String(s);

        
        String convert = "";
        
        while(true) {
        	int temp = convert(copy).length();
        	//2진수 변환
        	convert = Integer.toBinaryString(temp);
        	runNum++;
            if(convert.length()==1) {
            	answer[0] = runNum;
            	answer[1] = zero;
            	return answer;
            } else {
            	copy = convert;
            }
        }
        
    }
	
	//0을 뺀 후 zero갱신
	public String convert(String s) {
		String temp = "";
		for(int i = 0; i<s.length(); i++) {
        	if(s.charAt(i)=='1') {
        		temp+=s.charAt(i);
        	} else {
        		zero++;
        	}
        }
		return temp;
	}

	public static void main(String[] args) {
		프로그래밍2 a = new 프로그래밍2();
		String s = "110010101001";
		int [] answer = a.solution(s);
		System.out.println(answer[0]+" "+answer[1]);

	}

}
