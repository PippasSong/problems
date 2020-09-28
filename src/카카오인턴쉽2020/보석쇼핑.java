package 카카오인턴쉽2020;
import java.util.*;
public class 보석쇼핑 {
	
	public int[] solution(String[] gems) {
        int[] answer = {};
        ArrayList<ArrayList<Integer>> poss = possibility(gems);
        for(ArrayList<Integer> temp : poss) {
        	//맨 처음
        	if(answer.length==0) {
        		answer = new int[temp.size()];
        		for(int i = 0; i<temp.size(); i++) {
        			answer[i] = temp.get(i);
        		}
        	}
        	//temp의 크기가 기존 정답보다 작은 경우
        	else if(answer.length > temp.size()) {
        		answer = new int[temp.size()];
        		for(int i = 0; i<temp.size(); i++) {
        			answer[i] = temp.get(i);
        		}
        	}
        	//temp의 크기가 정답과 같고 시작하는 수가 더 작을 때 
        	else if(answer.length == temp.size()&&answer[0]>temp.get(0)) {
        		answer = new int[temp.size()];
        		for(int i = 0; i<temp.size(); i++) {
        			answer[i] = temp.get(i);
        		}
        	}
        }
        int[] ans = new int[2];
        ans[0] = answer[0];
        ans[1] = answer[answer.length-1];
        return ans;
    }
	
	// @param : 보석 진열장
	// @return : 모든 보석을 살 수 있는 배열을 담은 배열
	public ArrayList<ArrayList<Integer>> possibility(String[] gems){
		ArrayList<ArrayList<Integer>> answer = new ArrayList<>();
		Set<String> gemSet = new HashSet<String>();
		for(String str : gems) {
			gemSet.add(str);
		}
		//모든 원소가 들어있는것을 확인할 큐 생성
		Queue<String> gemQueue = new LinkedList<String>(gemSet);
		
		//큐 복사
		Queue<String> copyQueue = new LinkedList<String>(gemQueue);
		//주어진 배열의 첫 번째 요소부터 읽고 queue가 빌 때 까지 읽기, 배열 생성, queue 초기화
		for(int i = 0; i < gems.length; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for(int j=i; copyQueue.isEmpty()==false&&j<gems.length; j++) {
				if(copyQueue.contains(gems[j])==true) {
					temp.add(j+1);
					copyQueue.remove(gems[j]);
					
				}
				//마지막까지 돌려서  조합을 찾지 못하는 경우
				else if(copyQueue.contains(gems[j])==false && j==gems.length-1) {
					break;
				}
				else if(copyQueue.contains(gems[j])==true && j==gems.length-1) {
					temp.add(j+1);
					copyQueue.remove(gems[j]);
					break;
				}
				else if(copyQueue.contains(gems[j])==false) {
					temp.add(j+1);
				}
			}
			//조합이 가능한 경우만 정답에 추가
			if(copyQueue.isEmpty()&&answer.size()==0) {
				answer.add(temp);
			}
			//최적화를 위해 전보다 작거나 같은 배열만 추가한다
			else if(copyQueue.isEmpty()&&answer.get(0).size()>=temp.size()) {
				answer.add(temp);
			}
			
			//초기화
			copyQueue = new LinkedList<String>(gemQueue);
		}
		
		return answer;
	}
	public static void main(String[] args) {
		보석쇼핑 a = new 보석쇼핑();
		String[] array = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		System.out.println(a.solution(array)[0]+" "+a.solution(array)[1]);
	}

}
