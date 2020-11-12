package 프로그래머스코드챌린지11월;
import java.util.*;


public class 프로그래밍3 {
	//스타 수열의 길이는 두개씩 짝저어진 것의 길이
	 public int solution(int[] a) {
		 int answer = -1;
		 	List<Integer> copy = new ArrayList<>();
		 	for(int i = 0; i<a.length; i++) {
		 		copy.add(a[i]);
		 	}
	        HashMap<Integer, Integer> map = new HashMap<>();
	        for(int i = 0; i<a.length; i++) {
	        	if(map.containsKey(a[i])) {
	        		map.put(a[i], map.get(a[i])+1);
	        	} else {
	        		map.put(a[i], 1);
	        	}
	        }
	        
	        //등장하는 숫자를 하나씩 기준으로 해서 스타배열 만든다
	        int mostNum = -1;
	        List<Integer> starAry = new ArrayList<Integer>();
	        Iterator<Integer> iter = map.keySet().iterator();
	        while(iter.hasNext()) {
	        	//초기화
	        	starAry = new ArrayList<Integer>();
	        	copy = new ArrayList<>();
			 	for(int i = 0; i<a.length; i++) {
			 		copy.add(a[i]);
			 	}
	        	int keys = iter.next();
	        	mostNum = keys;
	        	
	        	System.out.println("key "+ mostNum);
		        int i = 0;
		        while(i <copy.size()) {
		        	if(copy.get(i)==mostNum) {
		        		if(i-1>=0&&copy.get(i-1)!=mostNum) {
		        			
			        		starAry.add(copy.get(i-1));
		        			starAry.add(copy.get(i));
		        			if(i+1<copy.size()-1) {
		        			copy = copy.subList(i+1, copy.size());
		        			i=0;
		        			} else {
		        				break;
		        			}
	
		        		}
			        	else if(i+1<copy.size()-1&&copy.get(i+1)!=mostNum) {
		        			starAry.add(copy.get(i));
		        			starAry.add(copy.get(i+1));
		        			if(i+2<copy.size()-1) {
		        				copy = copy.subList(i+2, copy.size());
		        				i=0;
		        			} else {
		        				break;
		        			}
		        			
		        		}else {
		        			i++;
		        		}
		        	}
		        	 else {
	        			i++;
	        		}
		        	//System.out.println(i);
		        	//System.out.println(copy);
		        	
		        	
		        }
		        System.out.println(starAry);
		        if(starAry.size()>answer) {
	        		answer = starAry.size();
	        	}
	        }
	        
	        
	       
	        
	        //System.out.println(starAry);
	        
	        return answer;
	    }

	public static void main(String[] args) {
		프로그래밍3 s = new 프로그래밍3();
		int[] a = {0};
		//int[] a = {5,2,3,3,5,3};
		//int[] a = {0,3,3,0,7,2,0,2,2,0};
		//이런 경우는 오류가 있으므로 모든 키에 대해서 실행해야함
		//int[] a = {2,5,2,5,3,3,3,3,3,3,3,3,3,3,3,3,3,2,5,2,5};
		System.out.println(s.solution(a));
	}

}
