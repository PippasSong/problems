package 카카오인턴쉽2020;
import java.util.*;

public class 경주로건설2 {
	
	public int solution(int[][] board) {

		//반드시 깊은 복사 사용하기
		int[][] copy = new int[board.length][board[0].length];
		for(int i = 0; i<copy.length; i++) {
			System.arraycopy(board[i], 0, copy[i], 0, board[0].length);
		}
        int answer = Integer.MAX_VALUE;
        //시작점
        MapNode startPoint = new MapNode(0, 0, 0, null);
        copy[0][0] = Integer.MAX_VALUE;
        Queue<MapNode> queue = new LinkedList<MapNode>();

        //시작점을 queue에 넣는다
        queue.offer(startPoint);

        
        while(!queue.isEmpty()) {
        	//큐에 있는 좌표 인쇄
        	/*
        	for(MapNode m : queue) {
        		System.out.print(m.x+""+m.y);
        		System.out.println("");
        	}*/
        	//현재 노드
        	MapNode curr = queue.remove();
        	//System.out.print(curr.x+""+curr.y+" cost: "+curr.cost);
        	//System.out.println("");
        	//목표 지점에 도착했을 경우
        	if(curr.x==board.length-1&&curr.y==board.length-1) {
        		int temp = curr.cost;
        		//System.out.println(temp);
        		if(answer>temp) {
        			answer = temp;
        		}
        		
        	}
        	//curr와 인접한 노드들
        	ArrayList<MapNode> neighbors = getNeighbor(board, curr);
        	/*
        	System.out.println("curr"+curr.x+""+curr.y+ "의 이웃은");
        	for(MapNode m : neighbors) {
    			
    			System.out.print(m.x);
    			System.out.print(m.y);
    			System.out.println("");
    		}*/
        	for(MapNode next : neighbors) {
        		//처음 시작했을 경우
        		if(curr==startPoint) {
        			//아래로 내려간 경우
        			if(curr.x == next.x) {
        				next.direction = "ver";
        			}
        			//오른쪽으로 움직인 경우
        			else {
        				next.direction = "hor";
        			}
        			next.cost = 100;
        			queue.offer(next);
        			copy[next.y][next.x]=next.cost;
        			
        		} else {
        			int newCost = Integer.MAX_VALUE;
            		//이웃 중 하나로 갔을 때 비용 계산
            		//다른 방향으로 움직이려 하면(코너) 비용에 500을 더한다
            		
            		//가로로 가다가 세로로 가는 경우
            		if(curr.direction.equals("hor")&&curr.y != next.y) {
            			newCost = curr.cost + 600;
            		}
            		//세로로 가다가 가로로 가는 경우
            		else if(curr.direction.equals("ver")&&curr.x != next.x) {
            			newCost = curr.cost + 600;
            		}
            		//방향이 같은 경우
            		else {
            			newCost = curr.cost + 100;
            		}
            		//새로 가려고 하는 길의 비용이 기존의 비용보다 적은 경우 비용이 적은 노드로 대체한다
            		//처음 도착하는 칸 일 경우
            		if(copy[next.y][next.x]==0) {
            			next.cost = newCost;
            			copy[next.y][next.x] = newCost;
            			//아래로 내려간 경우
            			if(curr.x == next.x) {
            				next.direction = "ver";
            			}
            			//오른쪽으로 움직인 경우
            			else {
            				next.direction = "hor";
            			}
            			//parentMap.put(next, curr);
            			queue.offer(next);
            			//visited.add(next);
            		}
            		//이미 방문한 경우 전보다 비용이 작거나 같은 경우에만 방문한다
            		else if(copy[next.y][next.x]>=newCost) {
            			next.cost = newCost;
            			copy[next.y][next.x] = newCost;
            			//아래로 내려간 경우
            			if(curr.x == next.x) {
            				next.direction = "ver";
            			}
            			//오른쪽으로 움직인 경우
            			else {
            				next.direction = "hor";
            			}
            			//parentMap.put(next, curr);
            			queue.offer(next);
            		}
        		}
        		

        	}
        	
        }
		return answer;
	}
	
	//인접한 칸의 좌표를 리턴하는 메소드
	public ArrayList<MapNode> getNeighbor(int[][] board , MapNode standard){
		ArrayList<MapNode> answer = new ArrayList<MapNode>();
		//위쪽 칸 존재
		if(standard.x>=0&&standard.x<board.length&&standard.y-1>=0&&standard.y-1<board.length&&board[standard.y-1][standard.x]==0) {
			answer.add(new MapNode(standard.x, standard.y-1, Integer.MAX_VALUE, null));
		}
		//왼쪽 칸 존재
		if(standard.x-1>=0&&standard.x-1<board.length&&standard.y>=0&&standard.y<board.length&&board[standard.y][standard.x-1]==0) {
			answer.add(new MapNode(standard.x-1, standard.y, Integer.MAX_VALUE, null));
		}
		//오론쪽 칸 존재
		if(standard.x+1>=0&&standard.x+1<board.length&&standard.y>=0&&standard.y<board.length&&board[standard.y][standard.x+1]==0) {
			answer.add(new MapNode(standard.x+1, standard.y, Integer.MAX_VALUE, null));
		}
		//아래쪽 칸 존재
		if(standard.x>=0&&standard.x<board.length&&standard.y+1>=0&&standard.y+1<board.length&&board[standard.y+1][standard.x]==0) {
			answer.add(new MapNode(standard.x, standard.y+1, Integer.MAX_VALUE, null));
		}
		return answer;
	}
	
	public static void main(String[] args) {
		경주로건설2 a = new 경주로건설2();
		//int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
		int[][] board = {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}};
		//int[][] board = {{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}};
		MapNode standard = new MapNode(4,2,0, null);
		//int[][] board = {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};
		System.out.println(a.solution(board));
		//a.solution(board);
		
		ArrayList<MapNode> neighbor = a.getNeighbor(board, standard);
		/*
		for(MapNode m : neighbor) {
			System.out.println("neig");
			System.out.print(m.x);
			System.out.print(m.y);
			System.out.println("");
		}*/
		

	}

}
//노드의 x,y좌표, 현재까지의 비용, 방향을 담고 있다
class MapNode {
	int x;
	int y;
	int cost = Integer.MAX_VALUE;
	String direction = null;
	//생성자
	MapNode(int x, int y, int cost, String direction){
		this.x = x;
		this.y = y;
		this.cost = cost;
		this.direction = direction;
	}
		
}