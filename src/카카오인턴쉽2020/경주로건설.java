package 카카오인턴쉽2020;
import java.util.*;

public class 경주로건설 {
	
	public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        ArrayList<int[]> index = new ArrayList<int[]>();
        for(int i = 0; i < board.length; i++) {
        	for(int j = 0; j < board[i].length; j++) {
        		if(board[i][j]==0) {
        			int[] temp = new int[2];
        			temp[0] = i;
        			temp[1] = j;
        			index.add(temp);
        		}
        	}
        }
        //시작점
        int[] startPoint = index.get(0);
        //끝점
        int[] endPoint = index.get(index.size()-1);
        //중복 탐사를 방지하기 위한 배열
        ArrayList<int[]> visited = new ArrayList<int[]>();
        Queue<int[]> queue = new LinkedList<int[]>();
        //앞 값은 next, 뒤 값은 현재
        //value 값을 arraylist<int[]>로 재정의 하기
        HashMap<int[], int[]> parentMap = new HashMap<int[], int[]>();
        //시작점을 queue에 넣는다
        queue.offer(startPoint);
        //커브를 줄이기 위해 next값과 previous 값을 비교해 queue에 넣을 순서를 정한다
        int[] previous = null;
        //queue가 빌 때 까지 루프
        while(!queue.isEmpty()) {
        	int[] curr = queue.remove();
        	//마지막에 도착한 경우
        	if(curr.equals(endPoint)) {
        		//이전 answer 값과 비교해서 answer 값 갱신
        		int temp = cost(parentMap, startPoint, endPoint);
        		if(temp<answer) {
        			answer = temp;
        		}
        		
        	}
        	ArrayList<int[]> neighbors = getNeighbor(index, curr);
        	//neighbors의 원소(next가 될 것들)를 previous와의 관계를 보고 직선 관계가 아니면 swap한다
        	//버블 정렬
        	for(int j = 0; j<neighbors.size(); j++) {
        		for(int i = 0; i<neighbors.size()-1; i++) {
            		if(previous!=null) {
            			if(Math.sqrt(Math.pow(neighbors.get(i)[0]-previous[0], 2)+Math.pow(neighbors.get(i)[1]-previous[1], 2))!=2) {
            				Collections.swap(neighbors, i, i+1);
            			}
            		}
            	}
        	}
        	
        	for(int[] next : neighbors) {
        		if(!visited.contains(next)) {
        			visited.add(next);
        			parentMap.put(next, curr);
        			queue.offer(next);
        			//처음에는 null뜰것
        			if(parentMap.containsKey(curr)) {
        				previous = parentMap.get(curr);
        			}
        			
        		}
        	}
        }
        
        
        
        /*
        int[] standard = {2,2};
        ArrayList<int[]> a = getNeighbor(index, standard);
        for(int[] i : a) {
        	for(int j = 0; j<i.length; j++) {
        		System.out.print(i[j]);
        	}
        	System.out.println("");
        }*/
        return answer;
    }
	
	//경로를 찾은 경우 parentMap을 재구성해서 비용을 계산해 리턴
    public int cost(HashMap<int[], int[]> parentMap, int[] startPoint, int[] endPoint) {
    	int answer = Integer.MAX_VALUE;
    	LinkedList<int[]> path = new LinkedList<int[]>();
    	int[] curr = endPoint;
    	while (!curr.equals(startPoint)) {
			path.addFirst(curr);
			curr = parentMap.get(curr);
		}
    	path.addFirst(startPoint);
    	//직선도로 수
    	int straight = path.size()-1;
    	//코너 수
    	int corner = 0;
    	for(int i = 1; i<path.size(); i++) {
    		if(i==1) {
    			continue;
    		} else if(Math.sqrt(Math.pow(path.get(i-2)[0]-path.get(i)[0], 2)+Math.pow(path.get(i-2)[1]-path.get(i)[1], 2))!=2) {
    			corner++;
    		}
    	}
    	/*
    	for(int[] i : path) {
        	for(int j = 0; j<i.length; j++) {
        		System.out.print(i[j]);
        	}
        	System.out.println("");
    	}*/
        System.out.println("straight: "+straight+" corner : "+ corner);
    	answer = (straight*100)+(corner*500);
    	return answer;
    }
	
	//인접한 칸의 좌표를 리턴하는 메소드
	public ArrayList<int[]> getNeighbor(ArrayList<int[]> index, int[] standard){
		ArrayList<int[]> answer = new ArrayList<int[]>();
		for(int[] i : index) {
			//기준점과 같은 값
			if(i[0]==standard[0]&&i[1]==standard[1]) {
				
			} else if((i[0]==standard[0]&&(i[1]==standard[1]+1||i[1]==standard[1]-1))||(i[1]==standard[1]&&(i[0]==standard[0]+1||i[0]==standard[0]-1))) {
				answer.add(i);
			}
		}
		return answer;
	}
	
	
	public static void main(String[] args) {
		경주로건설 a = new 경주로건설();
		//int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
		int[][] board = {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};
		System.out.println(a.solution(board));

	}

}
