package ���α׷��ӽ��ڵ�ç����1;
import java.util.*;
import java.util.stream.IntStream;



public class ���α׷���3_2 {
	//3���� �̴� ���� ����
	ArrayList<ArrayList<Integer>> comb = new ArrayList<ArrayList<Integer>>();
	
	
	public int solution(int n, int[][] edges) {
        int answer = -1;
        int[] arr = new int[n];
        for(int i = 0; i<n; i++) {
        	arr[i] = i+1;
        }
        boolean[] visited = new boolean[n];
       
        combination(arr, visited, 0, n, 3);
        
        
        //System.out.println(comb);

        
        
        
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
	
	//Ʈ���� ���� ���ϱ� , �ִ� ������ 2���� �̻� �̸� �� ���� �߰��� �ƴϸ� �ִ� ����-1�� �߰��� 
	//BFS�� ���������� ���� �� �Ÿ� ���ϱ�
	//n�� ����� ��
	public int bfs(int start, int[][] edges, int n) {
		//������ ����
		ArrayList<MapNode> nodes = new ArrayList<MapNode>();
		for(int i = 1; i<n+1; i++) {
			nodes.add(new MapNode(i));
		}
		int answer = 0;
		MapNode startPoint = new MapNode(start);
		
		//������
		for(MapNode m : nodes) {
			if(m.name==start) {
				 startPoint = m;
			}
		}
		
		//�Ÿ� �ʱ�ȭ
		startPoint.distance = 0;
		Queue<MapNode> queue = new LinkedList<>();
		//�ߺ� ���ϱ� ���� �迭
		ArrayList<MapNode> visited = new ArrayList<MapNode>();
		
		//�������� queue�� �ִ´�
        queue.offer(startPoint);
        visited.add(startPoint);
        
        while(!queue.isEmpty()) {
        	MapNode curr = queue.remove();
        	if(curr.distance>answer) {
        		answer = curr.distance;
        	}
	        
	        //curr�� ������ ���� ���ϱ�
	        ArrayList<MapNode> neighbors = new ArrayList<>();
	        for(int i = 0; i < edges.length; i++) {
	        	int[] element = edges[i];
	        	//�迭���� ���� ����
	        	if(IntStream.of(element).anyMatch(x->x==curr.name)) {
	        		for(int j = 0; j<element.length; j++) {
	        			if(element[j]!=curr.name) {
	        				for(MapNode m : nodes) {
	        					if(m.name==element[j]) {
	        						neighbors.add(m);
	        					}
	        				}
	        			}
	        		}
	        	}
	        }
	        
	        for(MapNode next : neighbors) {
	        	if(!visited.contains(next)) {
	        		visited.add(next);
	        		queue.offer(next);
	        		next.distance = curr.distance+1;
	        	}
	        	
	        	
	        }
	        
        
        }
		
		return answer;
	}
	
		

	public static void main(String[] args) {
		���α׷���3_2 a = new ���α׷���3_2();
		int[][] edges = {{1,2},{2,3},{3,4}};
		//int[][] edges = {{1,5},{2,5},{3,5},{4,5}};
		//System.out.println(a.solution(4, edges));
		System.out.println(a.bfs(2, edges, 4));

	}
	
	

}


