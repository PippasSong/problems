package ���α׷��ӽ��ڵ�ç����1;
import java.util.*;

public class ���α׷���3 {
	//3���� �̴� ���� ����
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
	
	//��Ʈ��ŷ�� ��� n���� r���� �̾� ������ ������ ����
	public void combination(int[] arr, boolean[] visited, int start, int n, int r) {
		//visited���� r�� ��ŭ true�� �� ���
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
		���α׷���3 a = new ���α׷���3();
		int[][] edges = {{1,2},{2,3},{3,4}};
		a.solution(4, edges);

	}

}
