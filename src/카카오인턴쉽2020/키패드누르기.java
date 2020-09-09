package 카카오인턴쉽2020;

import java.util.HashMap;
import java.util.Map;

public class 키패드누르기 {
	String rightHand = "#";
	String leftHand = "*";
	
	//왼손, 오른손의 현재 위치를 받고 number을 어느 손으로 누를지 결정
	//마지막으로 왼손, 오른손의 위치를 정한다.
	public String whichHand(int number, String hand) {
		String finger = "";
		//키패드를 hashmap형태로 구현
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
		
		//매개변수를 문자로 변환
		String toWord = Integer.toString(number);
		
		//1,4,7, 3,6,9를 누르는 경우
		if(toWord.equals("1")||toWord.equals("4")||toWord.equals("7")) {
			finger = "L";
			leftHand = toWord;
			return finger;
		} else if(toWord.equals("3")||toWord.equals("6")||toWord.equals("9")) {
			finger = "R";
			rightHand = toWord;
			return finger;
		}
		
		
		//위의 숫자를 제외한 나머지 숫자를 누르는 경우
		//왼손이 가까운 경우
		if(distance(keyPad.get(rightHand), keyPad.get(toWord)) > distance(keyPad.get(leftHand), keyPad.get(toWord))) {
			finger = "L";
			leftHand = toWord;
		} else if(distance(keyPad.get(rightHand), keyPad.get(toWord)) == distance(keyPad.get(leftHand), keyPad.get(toWord))) {
			//거리가 같은 경우
			if(hand.equals("left")) {
				finger = "L";
				leftHand = toWord;
			} else if(hand.equals("right")) {
				finger = "R";
				rightHand = toWord;
			}
				
		} else if(distance(keyPad.get(rightHand), keyPad.get(toWord)) < distance(keyPad.get(leftHand), keyPad.get(toWord))) {
			//오른손이 가까운 경우
			finger = "R";
			rightHand = toWord;
		}
		
		return finger;
	}
	
	//좌표간 거리를 구해주는 메소드
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
		키패드누르기 a = new 키패드누르기();
		System.out.println(a.distance(one, two));
		System.out.println(a.whichHand(0,"right"));
		int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		System.out.println(a.solution(numbers, "right"));

	}
}
