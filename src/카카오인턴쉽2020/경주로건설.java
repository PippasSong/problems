package īī�����Ͻ�2020;
import java.util.*;

public class ���ַΰǼ� {
	
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
        //������
        int[] startPoint = index.get(0);
        //����
        int[] endPoint = index.get(index.size()-1);
        //�ߺ� Ž�縦 �����ϱ� ���� �迭
        ArrayList<int[]> visited = new ArrayList<int[]>();
        Queue<int[]> queue = new LinkedList<int[]>();
        //�� ���� next, �� ���� ����
        //value ���� arraylist<int[]>�� ������ �ϱ�
        HashMap<int[], int[]> parentMap = new HashMap<int[], int[]>();
        //�������� queue�� �ִ´�
        queue.offer(startPoint);
        //Ŀ�긦 ���̱� ���� next���� previous ���� ���� queue�� ���� ������ ���Ѵ�
        int[] previous = null;
        //queue�� �� �� ���� ����
        while(!queue.isEmpty()) {
        	int[] curr = queue.remove();
        	//�������� ������ ���
        	if(curr.equals(endPoint)) {
        		//���� answer ���� ���ؼ� answer �� ����
        		int temp = cost(parentMap, startPoint, endPoint);
        		if(temp<answer) {
        			answer = temp;
        		}
        		
        	}
        	ArrayList<int[]> neighbors = getNeighbor(index, curr);
        	//neighbors�� ����(next�� �� �͵�)�� previous���� ���踦 ���� ���� ���谡 �ƴϸ� swap�Ѵ�
        	//���� ����
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
        			//ó������ null���
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
	
	//��θ� ã�� ��� parentMap�� �籸���ؼ� ����� ����� ����
    public int cost(HashMap<int[], int[]> parentMap, int[] startPoint, int[] endPoint) {
    	int answer = Integer.MAX_VALUE;
    	LinkedList<int[]> path = new LinkedList<int[]>();
    	int[] curr = endPoint;
    	while (!curr.equals(startPoint)) {
			path.addFirst(curr);
			curr = parentMap.get(curr);
		}
    	path.addFirst(startPoint);
    	//�������� ��
    	int straight = path.size()-1;
    	//�ڳ� ��
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
	
	//������ ĭ�� ��ǥ�� �����ϴ� �޼ҵ�
	public ArrayList<int[]> getNeighbor(ArrayList<int[]> index, int[] standard){
		ArrayList<int[]> answer = new ArrayList<int[]>();
		for(int[] i : index) {
			//�������� ���� ��
			if(i[0]==standard[0]&&i[1]==standard[1]) {
				
			} else if((i[0]==standard[0]&&(i[1]==standard[1]+1||i[1]==standard[1]-1))||(i[1]==standard[1]&&(i[0]==standard[0]+1||i[0]==standard[0]-1))) {
				answer.add(i);
			}
		}
		return answer;
	}
	
	
	public static void main(String[] args) {
		���ַΰǼ� a = new ���ַΰǼ�();
		//int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
		int[][] board = {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};
		System.out.println(a.solution(board));

	}

}
