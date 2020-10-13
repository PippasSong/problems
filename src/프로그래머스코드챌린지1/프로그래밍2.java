package ���α׷��ӽ��ڵ�ç����1;
import java.util.*;

public class ���α׷���2 {
	//���� ����� 
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
	
	//��� �Լ�
	public void recursion(int[][] arr) {
		HashMap<Integer, int[][]> temp = incoding(arr);
       
		//��ȯ�ϴ� �ؽø��� ���� ������ �ݼ� ����
        while(temp.size()!=0) {
        	//System.out.println(temp.size());

    		for(int i : temp.keySet()) {
        		recursion(temp.get(i));
        	}
    		break;
        }
	}
	
	public HashMap<Integer, int[][]> incoding(int[][] arr) {
		//��ȯ �ȵ� �͵��� ��ȯ
		HashMap<Integer, int[][]> notAnswer = new HashMap<Integer, int[][]>();
		int[][] temp = new int[arr.length/2][arr.length/2];
		int i =0;
		//x�� ������
		for(int x = 0; x<=arr.length-arr.length/2; x+=arr.length/2) {
			//y�� ������
			for(int y = 0; y<=arr.length-arr.length/2; y+=arr.length/2) {
				//temp�� ���� ������ �߶� �迭�� ����
				//x��
				for(int j = 0; j<arr.length/2; j++) {
					//y��
					for(int k = 0; k<arr.length/2; k++) {
						temp[k][j] = arr[y+k][x+j];
					}
				}
				
				int standard = temp[0][0];
				for(int j = 0; j<temp.length; j++) {
					for(int k = 0; k<temp.length; k++) {
						if(standard != temp[k][j]) {
							//�ٸ� ���Ұ� �ִ� ��� �ؽøʿ� ����
							notAnswer.put(i, temp);
							
						}
					}
					continue;
				}
				//System.out.println(standard);
				//��� ��Ұ� ���ٸ� hashmap�� ���Խ�Ű�� �ʴ´�. �ʵ� �� ����
				if(!notAnswer.containsKey(i)&&standard==0) {
					zero++;
				} else if(!notAnswer.containsKey(i)&&standard==1) {
					one++;
				}
				
				
				//�ʱ�ȭ
				temp = new int[arr.length/2][arr.length/2];
				i++;
			}
			
			
		}
		return notAnswer;
		
	}

	public static void main(String[] args) {
		���α׷���2 a = new ���α׷���2();
		int[][] arr = {{1,1,0,0},{1,0,0,0},{1,0,0,1},{1,1,1,1}};
		//int[][] arr = {{0,0},{0,0}};
		//System.out.println(a.incoding(arr).size());
		//
		//System.out.println(a.incoding(arr).keySet());
		System.out.println(a.solution(arr));

	}

}
