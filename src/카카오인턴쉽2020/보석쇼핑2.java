package 카카오인턴쉽2020;
import java.util.*;

public class 보석쇼핑2 {
	public int[] solution(String[] gems) {
        int[] answer = new int[2];
        answer = possibility(gems);
        
        return answer;
	}
	
	// @param : 보석 진열장
	// @return : 모든 보석을 살 수 있는 배열 중 가장 짧고 앞에 있는 배열
	public int[] possibility(String[] gems){
		int[] answer = {};
		//비교용 배열
		ArrayList<String> copy = new ArrayList<String>();
		for(String str : gems) {
			copy.add(str);
		}
		//보석의 종류가 담긴 set
		Set<String> gemSet = new TreeSet<String>();
		for(String str : gems) {
			gemSet.add(str);
		}

		//투 포인터의 시작점
		int s = 0;
		//투 포인터의 끝점
		int e = 0;
		
		
		while(true) {
			//비교용 set
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
			//원하는 배열보다 길 경우 (보석은 모두 가지고 있다)
			if(compare(gemSet,gemSet2)&&(e-s)>=answer[1]-answer[0]+1) {
				s++;
				continue;
			} 
			//e가 끝 점에 도달했을 경우
			else if(e==copy.size()&&compare(gemSet,gemSet2)==false) {
				break;
			} else {
				e++;
			}
			
			
			
		}

		return answer;
	}
	
	//set의 값이 같은지 비교하는 메소드
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
		보석쇼핑2 a = new 보석쇼핑2();
		String[] array = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};

		//System.out.println(a.possibility(array));
		System.out.println(a.solution(array)[0]+" "+a.solution(array)[1]);

	}

}
