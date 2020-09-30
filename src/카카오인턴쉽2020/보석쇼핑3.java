package īī�����Ͻ�2020;
import java.util.*;

public class ��������3 {
	public int[] solution(String[] gems) {
		int[] answer = new int[2];
		//������ ������ ��� set
		Set<String> gemSet = new TreeSet<String>();
		for(String str : gems) {
			gemSet.add(str);
		}
		
		//for���� �����鼭 ���� ���ҵ��� Ȯ���� HashMap
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		//�ʱ� answer�� size
		int size = Integer.MAX_VALUE;

		
		//�� �������� ������
		int s = 0;
		//�� �������� ����
		int e = 0;
		
		//���� ������ ��� ���Ҹ� �������� ������ e�� �ø���
		//������ ��� �����ϸ� s�� �ø���
		//e�� ���� �����ϸ� ����
		
		for(int i=0; i<gems.length; i++) {
			if(gemSet.size()!=map.size()) {
				if(!map.containsKey(gems[i])) {
					map.put(gems[i], 1);
				} else {
					map.put(gems[i], map.get(gems[i])+1);
				}
				e++;
			}
			
			//��� ������ ������ ��� s���� �ִ�� �÷��ָ鼭, ���� ������� ���� ��쿡�� ���ο� answer�� �����Ѵ�
			while(gemSet.size()==map.size()) {
				//��� ������ ��� �ִ°���̸鼭 ���� ������� ���� ���
				if(gemSet.size()==map.size()&&e-(s+1)<size) {
					answer[0] = s+1;
					answer[1] = e;
					size = e-(s+1);
				}
				//s�� �� �ܰ� �ø��� HashMap ����
				map.put(gems[s], map.get(gems[s])-1);
				//�� ��� ��� ������ �������� ���ϹǷ� while ������ ���������� �ȴ�
				if(map.get(gems[s])==0) {
					map.remove(gems[s]);
				}
				s++;	
			}
			
		}
		//System.out.println(answer[0]);
		//System.out.println(answer[1]);

		return answer;
	}
	
	

	public static void main(String[] args) {
		��������3 a = new ��������3();
		String[] array = {"A", "B", "A", "A", "A", "C", "A", "B"};

		//System.out.println(a.possibility(array));
		System.out.println(a.solution(array));

		//System.out.println(a.solution(array)[0]+" "+a.solution(array)[1]);

	}

}
