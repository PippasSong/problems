package 카카오인턴쉽2020;
import java.util.*;

public class 보석쇼핑3 {
	public int[] solution(String[] gems) {
		int[] answer = new int[2];
		//보석의 종류가 담긴 set
		Set<String> gemSet = new TreeSet<String>();
		for(String str : gems) {
			gemSet.add(str);
		}
		
		//for문을 돌리면서 현재 원소들을 확인할 HashMap
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		//초기 answer의 size
		int size = Integer.MAX_VALUE;

		
		//투 포인터의 시작점
		int s = 0;
		//투 포인터의 끝점
		int e = 0;
		
		//현재 구간이 모든 원소를 포함하지 않으면 e를 늘린다
		//보석을 모두 포함하면 s를 늘린다
		//e가 끝에 도달하면 정지
		
		for(int i=0; i<gems.length; i++) {
			if(gemSet.size()!=map.size()) {
				if(!map.containsKey(gems[i])) {
					map.put(gems[i], 1);
				} else {
					map.put(gems[i], map.get(gems[i])+1);
				}
				e++;
			}
			
			//모든 보석을 포함한 경우 s값을 최대로 올려주면서, 기존 결과보다 작은 경우에만 새로운 answer에 대입한다
			while(gemSet.size()==map.size()) {
				//모든 보석을 담고 있는경우이면서 기존 결과보다 작은 경우
				if(gemSet.size()==map.size()&&e-(s+1)<size) {
					answer[0] = s+1;
					answer[1] = e;
					size = e-(s+1);
				}
				//s를 한 단계 올리고 HashMap 갱신
				map.put(gems[s], map.get(gems[s])-1);
				//이 경우 모든 보석을 포함하지 못하므로 while 루프를 빠저나오게 된다
				if(map.get(gems[s])==0) {
					map.remove(gems[s]);
				}
				s++;	
			}
			
		}
		//System.out.println(answer[0]);
		//System.out.println(answer[1]);

		return answer;
	}
	
	

	public static void main(String[] args) {
		보석쇼핑3 a = new 보석쇼핑3();
		String[] array = {"A", "B", "A", "A", "A", "C", "A", "B"};

		//System.out.println(a.possibility(array));
		System.out.println(a.solution(array));

		//System.out.println(a.solution(array)[0]+" "+a.solution(array)[1]);

	}

}
