package 프로그래머스코드챌린지1;
import java.util.*;

public class 프로그래밍2 {
	//정답 저장용 
	int zero = 0;
	int one = 0;
	 
	
	public int[] solution(int[][] arr) {
	        int[] answer = new int[2];
	   
	        recursion(arr);
	        //System.out.println(zero);
	        //System.out.println(one);
	        answer[0] = zero;
	        answer[1] = one;
	        
	        
	        return answer;
	    }
	
	//재귀 함수
	public void recursion(int[][] arr) {
		HashMap<Integer, int[][]> temp = incoding(arr);
       
		//반환하는 해시맵이 없을 때까지 반속 실행
        while(temp.size()!=0) {
        	//System.out.println(temp.size());

    		for(int i : temp.keySet()) {
        		recursion(temp.get(i));
        	}
    		break;
        }
	}
	
	public HashMap<Integer, int[][]> incoding(int[][] arr) {
		//변환 안된 것들을 반환
		HashMap<Integer, int[][]> notAnswer = new HashMap<Integer, int[][]>();
		int[][] temp = new int[arr.length/2][arr.length/2];
		int i =0;
		//x축 기준점
		for(int x = 0; x<=arr.length-arr.length/2; x+=arr.length/2) {
			//y축 기준점
			for(int y = 0; y<=arr.length-arr.length/2; y+=arr.length/2) {
				//temp에 왼쪽 위부터 잘라서 배열을 만듬
				//x축
				for(int j = 0; j<arr.length/2; j++) {
					//y축
					for(int k = 0; k<arr.length/2; k++) {
						temp[k][j] = arr[y+k][x+j];
					}
				}
				
				int standard = temp[0][0];
				for(int j = 0; j<temp.length; j++) {
					for(int k = 0; k<temp.length; k++) {
						if(standard != temp[k][j]) {
							//다른 원소가 있는 경우 해시맵에 포함
							notAnswer.put(i, temp);
							
						}
					}
					continue;
				}
				//System.out.println(standard);
				//모든 요소가 같다면 hashmap에 포함시키지 않는다. 필드 값 갱신
				if(!notAnswer.containsKey(i)&&standard==0) {
					zero++;
				} else if(!notAnswer.containsKey(i)&&standard==1) {
					one++;
				}
				
				
				//초기화
				temp = new int[arr.length/2][arr.length/2];
				i++;
			}
			
			
		}
		return notAnswer;
		
	}

	public static void main(String[] args) {
		프로그래밍2 a = new 프로그래밍2();
		int[][] arr = {{1,1,0,0},{1,0,0,0},{1,0,0,1},{1,1,1,1}};
		//int[][] arr = {{0,0},{0,0}};
		//System.out.println(a.incoding(arr).size());
		//
		//System.out.println(a.incoding(arr).keySet());
		System.out.println(a.solution(arr));

	}

}
