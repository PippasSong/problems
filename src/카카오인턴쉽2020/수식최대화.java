package īī�����Ͻ�2020;

import java.util.*;

public class �����ִ�ȭ {
	// @param ������ ��� ���ڿ�
	// @return  ��� �� ���� �� �ִ� ���� ū ��� �ݾ�. ������ ��� ����
	public long solution(String expression) {
        long answer = 0;
       /*
        Map<Integer, ArrayList<String>>comb = combination(expression);
        for(int i=0; i<comb.size(); i++) {
        	ArrayList<String> operators = comb.get(i);
        	
        	//������� ������ ����
        	String tempResult = null;
        	for(int j = 0; j<operators.size(); j++) {
        		String temp = operators.get(j);
        		if(tempResult == null) {
        			tempResult = calculator(expression, temp, operators);
        		} else {
        			tempResult = calculator(tempResult, temp, operators);
        		}
        		
        		System.out.println(tempResult);
        	}
        	System.out.println(tempResult);
        	//������� ���밪 ���ڷ� ��ȯ
        	int numTemp = Math.abs(Integer.parseInt(tempResult));
        	if(numTemp>answer) {
        		answer = numTemp;
        	}
        	
        }*/
        return answer;
    }

	// @param ���� ���ڿ�
	// @return �ִ밪
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
			combination.put(0, temp);
			
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
	
	// @param ���� ���ڿ�, �켱���� ������, ��ü ������ �迭
	// @return �켱���� �����ڷ� ������ �� ���� ���� ����
	public String calculator(String expression, String operator) {
		//���ڿ� ����
		String copy = new String(expression);
		//�� ���ڸ� ���� ����
		String firStr = "";
		//�� ���ڸ� ���� ����
		String secStr = "";
		//�ӽ� ���ڿ�. ��꿡 ���
		String tempStr = "";
		//operator�� index�� �ִ� �迭
		ArrayList<Integer> operators = indexOper(expression, operator);
		
		//operator�� ���� ���ϱ�
		//����� �� ��� ���� operator�� �ε��� ���� �ʿ�
		for(int n=0; n< operators.size(); n++) {
			int operInd = operators.get(n);
			int startIndex = -1;
			int endIndex = -1;
			//����� ���� ����
			int newLength = 0;
			//firStr���ϱ�
			
			for(int i=operInd-1; i>=0; i--) {
				if(copy.charAt(i)!='+'&&copy.charAt(i)!='-'&&copy.charAt(i)!='*') {
					firStr = copy.charAt(i)+firStr;
					startIndex = i;
				} 
				//�� �տ� - ���� ���
				else if(startIndex==1) {
					firStr = "-"+firStr;
					startIndex = 0;
				} else {
					break;
				}
			}
			System.out.println(firStr);
			//secStr���ϱ�
			for(int i=operInd+1; i<copy.length(); i++) {
				if(copy.charAt(i)!='+'&&copy.charAt(i)!='-'&&copy.charAt(i)!='*') {
					secStr = secStr + copy.charAt(i);
					endIndex = i;
				} else {
					break;
				}
			}
			System.out.println(secStr);
			
			//operator�� ���� ���
			if(operator.equals("+")) {
				tempStr = Integer.toString(Integer.parseInt(firStr)+Integer.parseInt(secStr));
				//���� �迭 ������
				//������ �� �տ� ������
				if(startIndex==0) {
					copy = new String(tempStr + copy.substring(endIndex+1, copy.length()));
				} 
				//���� ��, �ڿ� �ƹ��͵� ���� ��
				else if(startIndex==0 && endIndex==copy.length()-1){
					copy = new String(tempStr);
				}
				//������ �߰��� ���� ��
				else if(startIndex!=0 && endIndex!=copy.length()-1){
					copy = new String(copy.substring(0, startIndex) + tempStr + copy.substring(endIndex+1, copy.length()));
				}
				//������ ���� ���� ��
				else if(startIndex!=0 && endIndex==copy.length()-1){
					copy = new String(copy.substring(0, startIndex) + tempStr);
				}
				newLength = tempStr.length();
			
			}
			else if(operator.equals("-")) {
				tempStr = Integer.toString(Integer.parseInt(firStr)-Integer.parseInt(secStr));
				//���� �迭 ������
				//������ �� �տ� ������
				if(startIndex==0) {
					copy = new String(tempStr + copy.substring(endIndex+1, copy.length()));
				} 
				//���� ��, �ڿ� �ƹ��͵� ���� ��
				else if(startIndex==0 && endIndex==copy.length()-1){
					copy = new String(tempStr);
				}
				//������ �߰��� ���� ��
				else if(startIndex!=0 && endIndex!=copy.length()-1){
					copy = new String(copy.substring(0, startIndex) + tempStr + copy.substring(endIndex+1, copy.length()));
				}
				//������ ���� ���� ��
				else if(startIndex!=0 && endIndex==copy.length()-1){
					copy = new String(copy.substring(0, startIndex) + tempStr);
				}
				newLength = tempStr.length();
			}
			else if(operator.equals("*")) {
				tempStr = Integer.toString(Integer.parseInt(firStr)*Integer.parseInt(secStr));
				//���� �迭 ������
				//������ �� �տ� ������
				if(startIndex==0) {
					copy = new String(tempStr + copy.substring(endIndex+1, copy.length()));
				} 
				//���� ��, �ڿ� �ƹ��͵� ���� ��
				else if(startIndex==0 && endIndex==copy.length()-1){
					copy = new String(tempStr);
				}
				//������ �߰��� ���� ��
				else if(startIndex!=0 && endIndex!=copy.length()-1){
					copy = new String(copy.substring(0, startIndex) + tempStr + copy.substring(endIndex+1, copy.length()));
				}
				//������ ���� ���� ��
				else if(startIndex!=0 && endIndex==copy.length()-1){
					copy = new String(copy.substring(0, startIndex) + tempStr);
				}
				newLength = tempStr.length();
			}
			//operator�� ������ �ε����� �ƴϸ� ������ ��� ��
			if(n!=operators.size()-1) {
				for(int j=n+1; j<operators.size(); j++) {
					operators.set(j, operators.get(j)-(endIndex-newLength)-1);
				}
				
			}
			//firStr, secStr�ʱ�ȭ
			firStr = "";
			secStr = "";
			
		}
		/*�߸��� �ڵ�
		//operator�� ������ ������ ��� ���
		while(copy.indexOf(operator)!=-1) {
			for(int i=0; i<copy.length(); i++) {
				if(copy.charAt(i)!='+'&&copy.charAt(i)!='*'&&copy.charAt(i)!='-') {
					firStr+=copy.charAt(i);
					System.out.println(firStr);
				} else if(copy.charAt(i)=='+'||copy.charAt(i)=='*'||copy.charAt(i)=='-') {
					if(copy.charAt(i)=='+') {
						for(int j=i+1; j<copy.length(); j++) {
							if(copy.charAt(j)!='+'&&copy.charAt(j)!='*'&&copy.charAt(j)!='-') {
								secStr+=copy.charAt(j);
								System.out.println(secStr);
							} else {
								break;
							}
						}
						int num1 = Integer.parseInt(firStr);
						int num2 = Integer.parseInt(secStr);
						tempStr+=num1+num2;
						if(i+firStr.length()+2+secStr.length()+1==copy.length()) {
							copy = new String(tempStr);
							//��� �� �ʱ�ȭ
							firStr = "";
							secStr = "";
						} else {
							//���� ���ڿ� �߰�
							tempStr+=copy.substring(i+firStr.length()+secStr.length()-2, copy.length());
							copy = new String(tempStr);
							//��� �� �ʱ�ȭ
							firStr = "";
							secStr = "";
						}
						System.out.println(copy);
					} else if(copy.charAt(i)=='*') {
						for(int j=i+1; j<copy.length(); j++) {
							if(copy.charAt(j)!='+'&&copy.charAt(j)!='*'&&copy.charAt(j)!='-') {
								secStr+=copy.charAt(j);
								System.out.println(secStr);
							} else {
								break;
							}
						}
						int num1 = Integer.parseInt(firStr);
						int num2 = Integer.parseInt(secStr);
						tempStr+=num1*num2;
						if(i+firStr.length()+2+secStr.length()+1==copy.length()) {
							copy = new String(tempStr);
							//��� �� �ʱ�ȭ
							firStr = "";
							secStr = "";
						} else {
							//���� ���ڿ� �߰�
							tempStr+=copy.substring(i+firStr.length()+secStr.length()-2, copy.length());
							copy = new String(tempStr);
							//��� �� �ʱ�ȭ
							firStr = "";
							secStr = "";
						}
						System.out.println(copy);
					} else if(copy.charAt(i)=='-') {
						for(int j=i+1; j<copy.length(); j++) {
							if(copy.charAt(j)!='+'&&copy.charAt(j)!='*'&&copy.charAt(j)!='-') {
								secStr+=copy.charAt(j);
							} else {
								break;
							}
						}
						int num1 = Integer.parseInt(firStr);
						int num2 = Integer.parseInt(secStr);
						tempStr+=num1-num2;
						if(i+firStr.length()+2+secStr.length()+1==copy.length()) {
							copy = new String(tempStr);
							//��� �� �ʱ�ȭ
							firStr = "";
							secStr = "";
						} else {
							//���� ���ڿ� �߰�
							tempStr+=copy.substring(i+firStr.length()+secStr.length()-2, copy.length());
							copy = new String(tempStr);
							//��� �� �ʱ�ȭ
							firStr = "";
							secStr = "";
							System.out.println(tempStr);
						}
						System.out.println(copy);
					}
				}
			}
		}*/
		
			
		return copy;
	}
	
	//@para ���ڿ�, ������
	//@return �������� �ε��� �迭
	public ArrayList<Integer> indexOper(String expression, String operator) {
		ArrayList<Integer> answer = new ArrayList<Integer>();
		for(int i = 0; i<expression.length();) {
			if(expression.indexOf(operator, i)!=-1) {
				answer.add(expression.indexOf(operator, i));
				i+=expression.indexOf(operator, i)+2;
			} else {
				break;
			}
			
		}
		return answer;
	}
	
	
	
	/*
	//�߸��� �ڵ�
	// @param ���� ���ڿ�, �켱���� ������, ��ü ������ �迭
	// @return �Ű����� �����ڷ� ������ �� ���� ���� ����
	public String calculator(String expression, String operator, ArrayList<String> operators) {
		String result = "";
		String newOperator = null;
		//Ư�����ڷ� ��ȯ�ϱ� ���� ��ȯ
		if(operator.equals("*")) {
			newOperator = "\\*";
		} else if(operator.equals("+")) {
			newOperator = "\\+";
		} else if(operator.equals("-")) {
			newOperator = "\\-";
		}
		
		ArrayList<String> temp = new ArrayList<String>();
		temp.addAll(operators);
		temp.remove(operator);
		String[] strSplit = expression.split(newOperator);
		
		
		//����� ���� ���� �迭
		ArrayList<Integer> numToCal = new ArrayList<Integer>();
		//����� ��ģ �� ���ڿ�
		ArrayList<String> numResult = new ArrayList<String>();
		
		
		for(int i=0; i<strSplit.length; i++) {
			//ù��° ����϶� ������ ���ڸ� �߰�
			if(i%2==0&&i==0) {
				int num = -1;
				for(String str : temp) {
					if(strSplit[i].lastIndexOf(str)>num) {
						num = strSplit[i].lastIndexOf(str);
					}
				}
				//����� ����
				numToCal.add(Integer.parseInt(strSplit[i].substring(num+1)));
				//����� ���� ���� ���ڿ�
				strSplit[i] = strSplit[i].substring(0, num+1);
			} if(i%2==0&&i!=0) {
				int lastNum = -1;
				int firstNum = strSplit.length;
				for(String str : temp) {
					if(strSplit[i].lastIndexOf(str)>lastNum) {
						lastNum = strSplit[i].lastIndexOf(str);
					}
				}
				for(String str : temp) {
					if(strSplit[i].indexOf(str)<firstNum) {
						firstNum = strSplit[i].indexOf(str);
					}
				}
				//�����ڰ� ���� ���
				if(lastNum==firstNum&&lastNum==-1) {
					numToCal.add(Integer.parseInt(strSplit[i]));
					continue;
				}
				//�����ڰ� �ϳ��� ���
				if(lastNum==-1||firstNum==-1) {
					//������ ��ġ
					int index = -1;
					if(lastNum>firstNum) {
						index = lastNum;
					} else {
						index = firstNum;
					}
					numToCal.add(Integer.parseInt(strSplit[i].substring(0,index)));
					numToCal.add(Integer.parseInt(strSplit[i].substring(index+1)));
					continue;
				}
				//����� ����
				numToCal.add(Integer.parseInt(strSplit[i].substring(0, firstNum)));
				numToCal.add(Integer.parseInt(strSplit[i].substring(lastNum+1)));
				
				//����� ���� ���� ���ڿ�
				strSplit[i] = strSplit[i].substring(firstNum, lastNum+1);
			}
			//������ ����϶� ù��° ���ڸ� �߰�
			else if(i%2==1&&i==strSplit.length-1) {
				int num = strSplit[i].length();
				for(String str : temp) {
					if(strSplit[i].indexOf(str)<num) {
						if(strSplit[i].indexOf(str)!=-1) {
							num = strSplit[i].indexOf(str);
						}
					}
				}
				//����� ����
				numToCal.add(Integer.parseInt(strSplit[i].substring(0, num)));
				//����� ���� ���� ���ڿ�
				strSplit[i] = strSplit[i].substring(num);
			} if(i%2==1&&i!=strSplit.length-1) {
				int lastNum = -1;
				int firstNum = strSplit[i].length();
				for(String str : temp) {
					if(strSplit[i].lastIndexOf(str)>lastNum) {
						lastNum = strSplit[i].lastIndexOf(str);
					}
				}
				for(String str : temp) {
					if(strSplit[i].indexOf(str)<firstNum) {
						firstNum = strSplit[i].indexOf(str);
					}
				}
				
				//�����ڰ� ���� ���
				if(lastNum==firstNum&&lastNum==-1) {
					numToCal.add(Integer.parseInt(strSplit[i]));
					continue;
				}
				//�����ڰ� �ϳ��� ���
				if(lastNum==-1||firstNum==-1) {
					//������ ��ġ
					int index = -1;
					if(lastNum>firstNum) {
						index = lastNum;
					} else {
						index = firstNum;
					}
					numToCal.add(Integer.parseInt(strSplit[i].substring(0,index)));
					numToCal.add(Integer.parseInt(strSplit[i].substring(index+1)));
					continue;
				}
				//����� ����
				numToCal.add(Integer.parseInt(strSplit[i].substring(0, firstNum)));
				numToCal.add(Integer.parseInt(strSplit[i].substring(lastNum+1)));
				
				//����� ���� ���� ���ڿ�
				strSplit[i] = strSplit[i].substring(firstNum, lastNum+1);
			}
		}

			
		//���ڸ� �ϳ��� �а� ���� ��ȣ�� operator�� ��� ���� ���� ��ȣ�� ����ؼ� result�� �����ϱ�
		//���
		//����� ���� ¦������ ���
		if(numToCal.size()%2==0) {
			for(int i=0; i<numToCal.size(); i+=2) {
				if(operator.equals("*")) {
					numResult.add(Integer.toString(numToCal.get(i)*numToCal.get(i+1)));
				} else if(operator.equals("+")) {
					numResult.add(Integer.toString(numToCal.get(i)+numToCal.get(i+1)));
				} else if(operator.equals("-")) {
					numResult.add(Integer.toString(numToCal.get(i)-numToCal.get(i+1)));
				}
					
			}
		}
		
		//����� ���� Ȧ������ ��� �պ��� ���������� ���
		else if(numToCal.size()%2==1) {
			int resultNum = numToCal.get(0);
			for(int i=1; i<numToCal.size(); i++) {
				if(operator.equals("*")) {
					resultNum = resultNum*numToCal.get(i);
				} else if(operator.equals("+")) {
					resultNum = resultNum+numToCal.get(i);
				} else if(operator.equals("-")) {
					resultNum = resultNum-numToCal.get(i);
				}
					
			}
			return Integer.toString(resultNum);
		}

		
		//�ϼ��� ���ڿ� �����
		int numResultIndex = 0;
		for(int i=0; i<strSplit.length-1; i+=2) {
			result+=strSplit[i];
			result+=numResult.get(numResultIndex);
			result+=strSplit[i+1];
			numResultIndex++;
		}
		//��갪�� ���� ¦���� ��� ������ ��� �߰�
		if(numResult.size()%2==0) {
			result+=numResult.get(numResult.size()-1);
		}
		
		
		return result;
	}
	*/
	
	
	
	
	public static void main(String[] args) {
		�����ִ�ȭ b = new �����ִ�ȭ();
		String a = "8+8*8-9+8";
		//System.out.println(b.combination(a));
		ArrayList<String> c = new ArrayList<String>();
		c.add("*");
		c.add("-");
		c.add("+");
		System.out.println(b.calculator("50*3*2", "*"));
		//System.out.println(b.indexOper("100-60000-500+20", "-"));
		//System.out.println(b.solution("100-200*300-500+20"));
		
	}

}
