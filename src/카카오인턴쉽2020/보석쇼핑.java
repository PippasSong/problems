package īī�����Ͻ�2020;
import java.util.*;
public class �������� {
	
	public int[] solution(String[] gems) {
        int[] answer = {};
        ArrayList<ArrayList<Integer>> poss = possibility(gems);
        for(ArrayList<Integer> temp : poss) {
        	//�� ó��
        	if(answer.length==0) {
        		answer = new int[temp.size()];
        		for(int i = 0; i<temp.size(); i++) {
        			answer[i] = temp.get(i);
        		}
        	}
        	//temp�� ũ�Ⱑ ���� ���亸�� ���� ���
        	else if(answer.length > temp.size()) {
        		answer = new int[temp.size()];
        		for(int i = 0; i<temp.size(); i++) {
        			answer[i] = temp.get(i);
        		}
        	}
        	//temp�� ũ�Ⱑ ����� ���� �����ϴ� ���� �� ���� �� 
        	else if(answer.length == temp.size()&&answer[0]>temp.get(0)) {
        		answer = new int[temp.size()];
        		for(int i = 0; i<temp.size(); i++) {
        			answer[i] = temp.get(i);
        		}
        	}
        }
        int[] ans = new int[2];
        ans[0] = answer[0];
        ans[1] = answer[answer.length-1];
        return ans;
    }
	
	// @param : ���� ������
	// @return : ��� ������ �� �� �ִ� �迭�� ���� �迭
	public ArrayList<ArrayList<Integer>> possibility(String[] gems){
		ArrayList<ArrayList<Integer>> answer = new ArrayList<>();
		Set<String> gemSet = new HashSet<String>();
		for(String str : gems) {
			gemSet.add(str);
		}
		//��� ���Ұ� ����ִ°��� Ȯ���� ť ����
		Queue<String> gemQueue = new LinkedList<String>(gemSet);
		
		//ť ����
		Queue<String> copyQueue = new LinkedList<String>(gemQueue);
		//�־��� �迭�� ù ��° ��Һ��� �а� queue�� �� �� ���� �б�, �迭 ����, queue �ʱ�ȭ
		for(int i = 0; i < gems.length; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for(int j=i; copyQueue.isEmpty()==false&&j<gems.length; j++) {
				if(copyQueue.contains(gems[j])==true) {
					temp.add(j+1);
					copyQueue.remove(gems[j]);
					
				}
				//���������� ������  ������ ã�� ���ϴ� ���
				else if(copyQueue.contains(gems[j])==false && j==gems.length-1) {
					break;
				}
				else if(copyQueue.contains(gems[j])==true && j==gems.length-1) {
					temp.add(j+1);
					copyQueue.remove(gems[j]);
					break;
				}
				else if(copyQueue.contains(gems[j])==false) {
					temp.add(j+1);
				}
			}
			//������ ������ ��츸 ���信 �߰�
			if(copyQueue.isEmpty()&&answer.size()==0) {
				answer.add(temp);
			}
			//����ȭ�� ���� ������ �۰ų� ���� �迭�� �߰��Ѵ�
			else if(copyQueue.isEmpty()&&answer.get(0).size()>=temp.size()) {
				answer.add(temp);
			}
			
			//�ʱ�ȭ
			copyQueue = new LinkedList<String>(gemQueue);
		}
		
		return answer;
	}
	public static void main(String[] args) {
		�������� a = new ��������();
		String[] array = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		System.out.println(a.solution(array)[0]+" "+a.solution(array)[1]);
	}

}
