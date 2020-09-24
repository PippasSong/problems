package 카카오인턴쉽2020;

import java.util.*;

public class 수식최대화 {
	// @param 수식이 담긴 문자열
	// @return  우승 시 받을 수 있는 가장 큰 상금 금액. 음수일 경우 절댓값
	public long solution(String expression) {
        long answer = 0;
        Map<Integer, ArrayList<String>>comb = combination(expression);
        //System.out.println("comisize: "+comb);
        for(int i=0; i<comb.size(); i++) {
        	ArrayList<String> operators = comb.get(i);
        	//System.out.println("combi : "+ operators);
        	//계산결과를 저장할 변수
        	long tempResult = 0;
        	tempResult = calculator(expression, operators);
        	if(tempResult>answer) {
        		answer = tempResult;
        	}
        	//초기화
        	tempResult = 0;

        }
        return answer;
    }

	// @param 수식 문자열
	// @return 가능한 연산자의 조합
	public Map<Integer,ArrayList<String>> combination(String expression) {
		Set<String>operator = new HashSet<String>();
		if(expression.indexOf("+")!=-1) {
			operator.add("+");
		} if(expression.indexOf("-")!=-1) {
			operator.add("-");
		} if(expression.indexOf("*")!=-1) {
			operator.add("*");
		}
		
		
		List<String> listOperator = new ArrayList<String>(operator);
		//연산자 우선순위 경우의 수
		Map<Integer,ArrayList<String>> combination = new HashMap<Integer,ArrayList<String>>();
		
		
		//연산자의 개수에 따라서 경우의 수 
		if(listOperator.size()==1) {
			combination.put(0, new ArrayList<String>(listOperator));
		} else if(listOperator.size()==2) {
			combination.put(0, new ArrayList<String>(listOperator));
			ArrayList<String> temp = new ArrayList<String>();
			
			//순서 바꾸어 넣기
			temp.add(listOperator.get(1));
			temp.add(listOperator.get(0));
			combination.put(1, temp);
			
		} else if(listOperator.size()==3) {
			int keyFactor = 0;
			for(int i=0; i<listOperator.size(); i++) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(listOperator.get(i));
				//첫번째 값을 삭제한 배열
				ArrayList<String> temp2 = new ArrayList<String>(listOperator);
				temp2.remove(i);
				temp.add(temp2.get(0));
				temp.add(temp2.get(1));
				combination.put(keyFactor, temp);
				keyFactor++;
				
				//마지막 2개 바꿔서 대입
				ArrayList<String> temp3 = new ArrayList<String>(temp);
				Collections.swap(temp3, 1, 2);
				combination.put(keyFactor, temp3);
				keyFactor++;
			}
		
			
		}
		
        return combination;
	}
	
	// @param 수식 문자열, 전체 연산자 배열
	// @return 우선순위대로 계산한 값
	public long calculator(String expression, ArrayList<String> operators) {
		//계산결과
		long answer = -1;
		//숫자들을 담을 배열
		ArrayList<Long> numbers = new ArrayList<>();
		//부호들을 담을 배열
		ArrayList<Character> signs = new ArrayList<>();
		
		String temp = "";
		for(int i = 0; i < expression.length(); i++) {
			//임시로 숫자를 담을 변수
			
			if(expression.charAt(i)!='+'&&expression.charAt(i)!='-'&&expression.charAt(i)!='*') {
				temp = temp + expression.charAt(i);
			} else if(expression.charAt(i)=='+'||expression.charAt(i)=='-'||expression.charAt(i)=='*') {
				numbers.add(Long.parseLong(temp));
				//초기화
				temp = "";
				if(expression.charAt(i)=='+') {
					signs.add('+');
				} else if(expression.charAt(i)=='-') {
					signs.add('-');
				} else if(expression.charAt(i)=='*') {
					signs.add('*');
				}
			}
			//마지막 숫자
			if(i==expression.length()-1) {
				numbers.add(Long.parseLong(temp));
			}
			
		}
		//System.out.println(numbers);
		//System.out.println(signs);
		
		for(String str : operators) {
			while(signs.indexOf(str.charAt(0))!=-1) {
				//System.out.println(numbers);
				//System.out.println(signs);
				int signIdx = signs.indexOf(str.charAt(0));
				signs.remove(signIdx);
				//연산자 앞, 뒤의 숫자
				long firstNum = numbers.get(signIdx);
				long secNum = numbers.get(signIdx+1);
				//제거 후 새로 대입
				numbers.remove(signIdx);
				numbers.remove(signIdx);
				if(str=="+") {
					numbers.add(signIdx, firstNum+secNum);
				} else if(str=="-") {
					numbers.add(signIdx, firstNum-secNum);
				} else if(str=="*") {
					numbers.add(signIdx, firstNum*secNum);
				}
			}
			
		}
		//System.out.println(numbers);
		answer = Math.abs(numbers.get(0));
		return answer;
	}
	

	public static void main(String[] args) {
		수식최대화 b = new 수식최대화();
		String a = "8+8*8-9+8";
		//System.out.println(b.combination(a));
		ArrayList<String> c = new ArrayList<String>();
		c.add("*");
		c.add("+");
		c.add("-");
		//System.out.println(b.calculator("50*3*2", "*"));
		//System.out.println(b.calculator("100-200*300-500+20", c));
		System.out.println(b.solution("100-200*300-500+20"));
		//System.out.println(b.indexOper("100-60000-500+20", "-"));
		//System.out.println(b.solution("100-200*300-500+20"));
		
	}

}
