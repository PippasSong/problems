package īī�����Ͻ�2020;

import java.util.*;

public class �����ִ�ȭ {
	// @param ������ ��� ���ڿ�
	// @return  ��� �� ���� �� �ִ� ���� ū ��� �ݾ�. ������ ��� ����
	public long solution(String expression) {
        long answer = 0;
        Map<Integer, ArrayList<String>>comb = combination(expression);
        //System.out.println("comisize: "+comb);
        for(int i=0; i<comb.size(); i++) {
        	ArrayList<String> operators = comb.get(i);
        	//System.out.println("combi : "+ operators);
        	//������� ������ ����
        	long tempResult = 0;
        	tempResult = calculator(expression, operators);
        	if(tempResult>answer) {
        		answer = tempResult;
        	}
        	//�ʱ�ȭ
        	tempResult = 0;

        }
        return answer;
    }

	// @param ���� ���ڿ�
	// @return ������ �������� ����
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
		//������ �켱���� ����� ��
		Map<Integer,ArrayList<String>> combination = new HashMap<Integer,ArrayList<String>>();
		
		
		//�������� ������ ���� ����� �� 
		if(listOperator.size()==1) {
			combination.put(0, new ArrayList<String>(listOperator));
		} else if(listOperator.size()==2) {
			combination.put(0, new ArrayList<String>(listOperator));
			ArrayList<String> temp = new ArrayList<String>();
			
			//���� �ٲپ� �ֱ�
			temp.add(listOperator.get(1));
			temp.add(listOperator.get(0));
			combination.put(1, temp);
			
		} else if(listOperator.size()==3) {
			int keyFactor = 0;
			for(int i=0; i<listOperator.size(); i++) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(listOperator.get(i));
				//ù��° ���� ������ �迭
				ArrayList<String> temp2 = new ArrayList<String>(listOperator);
				temp2.remove(i);
				temp.add(temp2.get(0));
				temp.add(temp2.get(1));
				combination.put(keyFactor, temp);
				keyFactor++;
				
				//������ 2�� �ٲ㼭 ����
				ArrayList<String> temp3 = new ArrayList<String>(temp);
				Collections.swap(temp3, 1, 2);
				combination.put(keyFactor, temp3);
				keyFactor++;
			}
		
			
		}
		
        return combination;
	}
	
	// @param ���� ���ڿ�, ��ü ������ �迭
	// @return �켱������� ����� ��
	public long calculator(String expression, ArrayList<String> operators) {
		//�����
		long answer = -1;
		//���ڵ��� ���� �迭
		ArrayList<Long> numbers = new ArrayList<>();
		//��ȣ���� ���� �迭
		ArrayList<Character> signs = new ArrayList<>();
		
		String temp = "";
		for(int i = 0; i < expression.length(); i++) {
			//�ӽ÷� ���ڸ� ���� ����
			
			if(expression.charAt(i)!='+'&&expression.charAt(i)!='-'&&expression.charAt(i)!='*') {
				temp = temp + expression.charAt(i);
			} else if(expression.charAt(i)=='+'||expression.charAt(i)=='-'||expression.charAt(i)=='*') {
				numbers.add(Long.parseLong(temp));
				//�ʱ�ȭ
				temp = "";
				if(expression.charAt(i)=='+') {
					signs.add('+');
				} else if(expression.charAt(i)=='-') {
					signs.add('-');
				} else if(expression.charAt(i)=='*') {
					signs.add('*');
				}
			}
			//������ ����
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
				//������ ��, ���� ����
				long firstNum = numbers.get(signIdx);
				long secNum = numbers.get(signIdx+1);
				//���� �� ���� ����
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
		�����ִ�ȭ b = new �����ִ�ȭ();
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
