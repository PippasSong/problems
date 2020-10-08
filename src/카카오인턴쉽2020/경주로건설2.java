package īī�����Ͻ�2020;
import java.util.*;

public class ���ַΰǼ�2 {
	
	public int solution(int[][] board) {

		//�ݵ�� ���� ���� ����ϱ�
		int[][] copy = new int[board.length][board[0].length];
		for(int i = 0; i<copy.length; i++) {
			System.arraycopy(board[i], 0, copy[i], 0, board[0].length);
		}
        int answer = Integer.MAX_VALUE;
        //������
        MapNode startPoint = new MapNode(0, 0, 0, null);
        copy[0][0] = Integer.MAX_VALUE;
        Queue<MapNode> queue = new LinkedList<MapNode>();

        //�������� queue�� �ִ´�
        queue.offer(startPoint);

        
        while(!queue.isEmpty()) {
        	//ť�� �ִ� ��ǥ �μ�
        	/*
        	for(MapNode m : queue) {
        		System.out.print(m.x+""+m.y);
        		System.out.println("");
        	}*/
        	//���� ���
        	MapNode curr = queue.remove();
        	//System.out.print(curr.x+""+curr.y+" cost: "+curr.cost);
        	//System.out.println("");
        	//��ǥ ������ �������� ���
        	if(curr.x==board.length-1&&curr.y==board.length-1) {
        		int temp = curr.cost;
        		//System.out.println(temp);
        		if(answer>temp) {
        			answer = temp;
        		}
        		
        	}
        	//curr�� ������ ����
        	ArrayList<MapNode> neighbors = getNeighbor(board, curr);
        	/*
        	System.out.println("curr"+curr.x+""+curr.y+ "�� �̿���");
        	for(MapNode m : neighbors) {
    			
    			System.out.print(m.x);
    			System.out.print(m.y);
    			System.out.println("");
    		}*/
        	for(MapNode next : neighbors) {
        		//ó�� �������� ���
        		if(curr==startPoint) {
        			//�Ʒ��� ������ ���
        			if(curr.x == next.x) {
        				next.direction = "ver";
        			}
        			//���������� ������ ���
        			else {
        				next.direction = "hor";
        			}
        			next.cost = 100;
        			queue.offer(next);
        			copy[next.y][next.x]=next.cost;
        			
        		} else {
        			int newCost = Integer.MAX_VALUE;
            		//�̿� �� �ϳ��� ���� �� ��� ���
            		//�ٸ� �������� �����̷� �ϸ�(�ڳ�) ��뿡 500�� ���Ѵ�
            		
            		//���η� ���ٰ� ���η� ���� ���
            		if(curr.direction.equals("hor")&&curr.y != next.y) {
            			newCost = curr.cost + 600;
            		}
            		//���η� ���ٰ� ���η� ���� ���
            		else if(curr.direction.equals("ver")&&curr.x != next.x) {
            			newCost = curr.cost + 600;
            		}
            		//������ ���� ���
            		else {
            			newCost = curr.cost + 100;
            		}
            		//���� ������ �ϴ� ���� ����� ������ ��뺸�� ���� ��� ����� ���� ���� ��ü�Ѵ�
            		//ó�� �����ϴ� ĭ �� ���
            		if(copy[next.y][next.x]==0) {
            			next.cost = newCost;
            			copy[next.y][next.x] = newCost;
            			//�Ʒ��� ������ ���
            			if(curr.x == next.x) {
            				next.direction = "ver";
            			}
            			//���������� ������ ���
            			else {
            				next.direction = "hor";
            			}
            			//parentMap.put(next, curr);
            			queue.offer(next);
            			//visited.add(next);
            		}
            		//�̹� �湮�� ��� ������ ����� �۰ų� ���� ��쿡�� �湮�Ѵ�
            		else if(copy[next.y][next.x]>=newCost) {
            			next.cost = newCost;
            			copy[next.y][next.x] = newCost;
            			//�Ʒ��� ������ ���
            			if(curr.x == next.x) {
            				next.direction = "ver";
            			}
            			//���������� ������ ���
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
	
	//������ ĭ�� ��ǥ�� �����ϴ� �޼ҵ�
	public ArrayList<MapNode> getNeighbor(int[][] board , MapNode standard){
		ArrayList<MapNode> answer = new ArrayList<MapNode>();
		//���� ĭ ����
		if(standard.x>=0&&standard.x<board.length&&standard.y-1>=0&&standard.y-1<board.length&&board[standard.y-1][standard.x]==0) {
			answer.add(new MapNode(standard.x, standard.y-1, Integer.MAX_VALUE, null));
		}
		//���� ĭ ����
		if(standard.x-1>=0&&standard.x-1<board.length&&standard.y>=0&&standard.y<board.length&&board[standard.y][standard.x-1]==0) {
			answer.add(new MapNode(standard.x-1, standard.y, Integer.MAX_VALUE, null));
		}
		//������ ĭ ����
		if(standard.x+1>=0&&standard.x+1<board.length&&standard.y>=0&&standard.y<board.length&&board[standard.y][standard.x+1]==0) {
			answer.add(new MapNode(standard.x+1, standard.y, Integer.MAX_VALUE, null));
		}
		//�Ʒ��� ĭ ����
		if(standard.x>=0&&standard.x<board.length&&standard.y+1>=0&&standard.y+1<board.length&&board[standard.y+1][standard.x]==0) {
			answer.add(new MapNode(standard.x, standard.y+1, Integer.MAX_VALUE, null));
		}
		return answer;
	}
	
	public static void main(String[] args) {
		���ַΰǼ�2 a = new ���ַΰǼ�2();
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
//����� x,y��ǥ, ��������� ���, ������ ��� �ִ�
class MapNode {
	int x;
	int y;
	int cost = Integer.MAX_VALUE;
	String direction = null;
	//������
	MapNode(int x, int y, int cost, String direction){
		this.x = x;
		this.y = y;
		this.cost = cost;
		this.direction = direction;
	}
		
}