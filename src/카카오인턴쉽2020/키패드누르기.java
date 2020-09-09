package īī�����Ͻ�2020;

import java.util.HashMap;
import java.util.Map;

public class Ű�е崩���� {
	String rightHand = "#";
	String leftHand = "*";
	
	//�޼�, �������� ���� ��ġ�� �ް� number�� ��� ������ ������ ����
	//���������� �޼�, �������� ��ġ�� ���Ѵ�.
	public String whichHand(int number, String hand) {
		String finger = "";
		//Ű�е带 hashmap���·� ����
		Map<String, int[]> keyPad = new HashMap<String, int[]>();
		int[][] padNum = {{0,0}, {0,1}, {0,2}, {1,0}, {1,1}, {1,2}, {2,0}, {2,1}, {2,2}, {3,0}, {3,1}, {3,2}};
		keyPad.put("1", padNum[0]);
		keyPad.put("2", padNum[1]);
		keyPad.put("3", padNum[2]);
		keyPad.put("4", padNum[3]);
		keyPad.put("5", padNum[4]);
		keyPad.put("6", padNum[5]);
		keyPad.put("7", padNum[6]);
		keyPad.put("8", padNum[7]);
		keyPad.put("9", padNum[8]);
		keyPad.put("*", padNum[9]);
		keyPad.put("0", padNum[10]);
		keyPad.put("#", padNum[11]);
		
		//�Ű������� ���ڷ� ��ȯ
		String toWord = Integer.toString(number);
		
		//1,4,7, 3,6,9�� ������ ���
		if(toWord.equals("1")||toWord.equals("4")||toWord.equals("7")) {
			finger = "L";
			leftHand = toWord;
			return finger;
		} else if(toWord.equals("3")||toWord.equals("6")||toWord.equals("9")) {
			finger = "R";
			rightHand = toWord;
			return finger;
		}
		
		
		//���� ���ڸ� ������ ������ ���ڸ� ������ ���
		//�޼��� ����� ���
		if(distance(keyPad.get(rightHand), keyPad.get(toWord)) > distance(keyPad.get(leftHand), keyPad.get(toWord))) {
			finger = "L";
			leftHand = toWord;
		} else if(distance(keyPad.get(rightHand), keyPad.get(toWord)) == distance(keyPad.get(leftHand), keyPad.get(toWord))) {
			//�Ÿ��� ���� ���
			if(hand.equals("left")) {
				finger = "L";
				leftHand = toWord;
			} else if(hand.equals("right")) {
				finger = "R";
				rightHand = toWord;
			}
				
		} else if(distance(keyPad.get(rightHand), keyPad.get(toWord)) < distance(keyPad.get(leftHand), keyPad.get(toWord))) {
			//�������� ����� ���
			finger = "R";
			rightHand = toWord;
		}
		
		return finger;
	}
	
	//��ǥ�� �Ÿ��� �����ִ� �޼ҵ�
	public int distance(int[] one, int[] two) {
		int dis = 0;
		dis = Math.abs(one[0]-two[0]) + Math.abs(one[1]-two[1]);
		return dis;
	}
	
	public String solution(int[] numbers, String hand) {
        String answer = "";
        for(int i=0; i<numbers.length; i++) {
        	String temp = whichHand(numbers[i], hand);
        	answer+=temp;
        }
        return answer;
    }
	
	public static void main(String[] args) {
		int[] one = {0,0};
		int[] two = {3,0};
		Ű�е崩���� a = new Ű�е崩����();
		System.out.println(a.distance(one, two));
		System.out.println(a.whichHand(0,"right"));
		int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		System.out.println(a.solution(numbers, "right"));

	}
}
