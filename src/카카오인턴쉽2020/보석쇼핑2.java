package īī�����Ͻ�2020;
import java.util.*;

public class ��������2 {
	public int[] solution(String[] gems) {
        int[] answer = new int[2];
        answer = possibility(gems);
        
        return answer;
	}
	
	// @param : ���� ������
	// @return : ��� ������ �� �� �ִ� �迭 �� ���� ª�� �տ� �ִ� �迭
	public int[] possibility(String[] gems){
		int[] answer = {};
		//�񱳿� �迭
		ArrayList<String> copy = new ArrayList<String>();
		for(String str : gems) {
			copy.add(str);
		}
		//������ ������ ��� set
		Set<String> gemSet = new TreeSet<String>();
		for(String str : gems) {
			gemSet.add(str);
		}

		//�� �������� ������
		int s = 0;
		//�� �������� ����
		int e = 0;
		
		
		while(true) {
			//�񱳿� set
			Set<String> gemSet2 = new TreeSet<String>();
			List<String> temp = copy.subList(s, e);
			for(String str : temp) {
				gemSet2.add(str);
			}
			//System.out.println(s+" "+e);
			//System.out.println(gemSet2);
			
			if(compare(gemSet,gemSet2)&&answer.length==0) {
				answer = new int[2];
				answer[0] = s+1;
				answer[1] =  e;
			}else if(compare(gemSet,gemSet2)&&(e-s)==answer[1]-answer[0]+1&&answer[0]>s+1) {
				answer[0] = s+1;
				answer[1] =  e;

			} 
			else if(compare(gemSet,gemSet2)&&(e-s)<answer[1]-answer[0]+1) {
				answer[0] = s+1;
				answer[1] =  e;
			}
			/*
			for(int i=0;i<answer.length;i++) {
				System.out.println("answer: "+answer[i]);
			}*/
			//���ϴ� �迭���� �� ��� (������ ��� ������ �ִ�)
			if(compare(gemSet,gemSet2)&&(e-s)>=answer[1]-answer[0]+1) {
				s++;
				continue;
			} 
			//e�� �� ���� �������� ���
			else if(e==copy.size()&&compare(gemSet,gemSet2)==false) {
				break;
			} else {
				e++;
			}
			
			
			
		}

		return answer;
	}
	
	//set�� ���� ������ ���ϴ� �޼ҵ�
	public boolean compare(Set<String> one,Set<String> two) {
		for(String str : one) {
			if(two.contains(str)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}
	

	public static void main(String[] args) {
		��������2 a = new ��������2();
		String[] array = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};

		//System.out.println(a.possibility(array));
		System.out.println(a.solution(array)[0]+" "+a.solution(array)[1]);

	}

}
