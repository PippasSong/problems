package ���α׷��ӽ��ڵ�ç����11��;

public class ���α׷���2 {
	int zero=0;
	
	public int[] solution(String s) {
		//������ ��
		int runNum = 0;
        int[] answer = new int[2];
        //����
        String copy = new String(s);

        
        String convert = "";
        
        while(true) {
        	int temp = convert(copy).length();
        	//2���� ��ȯ
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
	
	//0�� �� �� zero����
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
		���α׷���2 a = new ���α׷���2();
		String s = "110010101001";
		int [] answer = a.solution(s);
		System.out.println(answer[0]+" "+answer[1]);

	}

}
