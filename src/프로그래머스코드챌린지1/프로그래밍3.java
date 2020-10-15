package 프로그래머스코드챌린지1;
import java.util.*;

public class 프로그래밍3 {
	//3개를 뽑는 조합 저장
	ArrayList<ArrayList<Integer>> comb = new ArrayList<ArrayList<Integer>>();
	
	
	public int solution(int n, int[][] edges) {
        int answer = 0;
        int[] arr = new int[n];
        for(int i = 0; i<n; i++) {
        	arr[i] = i+1;
        }
        boolean[] visited = new boolean[n];
       
        combination(arr, visited, 0, n, 3);
        
        System.out.println(comb);
        
        
        
        
        return answer;
    }
	
	//백트레킹을 사용 n에서 r개를 뽑아 조합한 경우들을 리턴
	public void combination(int[] arr, boolean[] visited, int start, int n, int r) {
		//visited에서 r개 만큼 true가 된 경우
		if(r==0) {
			ArrayList<Integer> temp = new ArrayList<>();
			for(int i = 0; i<visited.length; i++) {
				if(visited[i]) {
					temp.add(arr[i]);
				}
			}
			comb.add(temp);
			return;
		}
		
		for(int i=start; i<arr.length; i++) {
			visited[i] = true;
			combination(arr, visited, i+1, n, r-1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) {
		프로그래밍3 a = new 프로그래밍3();
		int[][] edges = {{1,2},{2,3},{3,4}};
		a.solution(4, edges);

	}

}
