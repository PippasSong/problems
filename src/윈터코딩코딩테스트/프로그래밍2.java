package �����ڵ��ڵ��׽�Ʈ;
import java.util.*;

public class ���α׷���2 {
	 public String solution(String encrypted_text, String key, int rotation) {
		 //rotation
		 ArrayList<Character> encry0  = new ArrayList<>();
		 for(int i = 0; i<encrypted_text.length(); i++) {
			 encry0.add(encrypted_text.charAt(i));
		 }
		 //�ݴ��ȸ��
		 if(rotation>0) {
			 for(int j = 0; j<rotation; j++) {
				 for(int i = 0; i<encry0.size()-1; i++) {
					 Collections.swap(encry0, i, i+1);
				 }
			 }
			 
		 } 
		 //�̺κ� ����!!!
		 else if(rotation<0) {
			 for(int j = 0; j<Math.abs(rotation); j++) {
				 for(int i = encry0.size()-2; i>=0; i--) {
					 Collections.swap(encry0, i, i+1);
				 }
			 }
		 }

		 
		 //��ȣ ���ڿ��� ���ڷ� ��ȯ �� ���� �迭
		 ArrayList<Integer> encry = new ArrayList<Integer>();
		//key�� ���ڷ� ��ȯ �� ���� �迭
		 ArrayList<Integer> keyInt = new ArrayList<Integer>();
		 for(int i = 0; i<encrypted_text.length(); i++) {
			 char temp = encry0.get(i);
			 encry.add((int)temp-96);
		 }
		 for(int i = 0; i<key.length(); i++) {
			 char temp = key.charAt(i);
			 keyInt.add((int)temp-96);
		 }

		 //key�� ����
		 for(int i = 0; i<encry.size(); i++) {
			 int temp = encry.get(i)-keyInt.get(i);
			 //1�Ʒ��̸� 26���� ����
			 if(temp<1) {
				 temp = temp+26;
			 }
			 encry.set(i, temp);
		 }

		 String decodeTemp = "";
		 for(int i  : encry) {
			 int temp1 = i+96;
			 char temp = (char)temp1;
			 decodeTemp += temp;
		 }
		 System.out.println(decodeTemp);
		 
	        return decodeTemp;
	    }
	 
	 

	public static void main(String[] args) {
		���α׷���2 a = new ���α׷���2();
		String encrypted_text = "qyyigoptvfb";
		String key = "abcdefghijk";
		int rotation = 3;
		a.solution(encrypted_text, key, rotation);

	}

}
