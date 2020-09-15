package īī�����Ͻ�2020;

import java.util.*;

public class �����ִ�ȭ {
	// @param ������ ��� ���ڿ�
	// @return  ��� �� ���� �� �ִ� ���� ū ��� �ݾ�. ������ ��� ����
	public long solution(String expression) {
        long answer = 0;
        return answer;
    }

	// @param ���� ���ڿ�
	// @return �ִ밪
	public Map<Integer,List<String>> combination(String expression) {
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
		Map<Integer,List<String>> combination = new HashMap<Integer,List<String>>();
		
		
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
	// @return �Ű����� �����ڷ� ������ �� ���� ���� ����
	public String calculator(String expresssion, String operator, ArrayList<String> operators) {
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
		
		ArrayList<String> temp = operators;
		temp.remove(operator);

		String[] strSplit = expresssion.split(newOperator);
		
		
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
				//����� ����
				numToCal.add(Integer.parseInt(strSplit[i].substring(0, firstNum)));
				numToCal.add(Integer.parseInt(strSplit[i].substring(lastNum+1)));
				
				//����� ���� ���� ���ڿ�
				strSplit[i] = strSplit[i].substring(firstNum, lastNum+1);
			}
			//������ ����϶� ù��° ���ڸ� �߰�
			else if(i%2==1&&i==strSplit.length-1) {
				int num = strSplit[i].length();
				System.out.println(num);
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
				//����� ����
				numToCal.add(Integer.parseInt(strSplit[i].substring(0, firstNum)));
				numToCal.add(Integer.parseInt(strSplit[i].substring(lastNum+1)));
				
				//����� ���� ���� ���ڿ�
				strSplit[i] = strSplit[i].substring(firstNum, lastNum+1);
			}
		}

			

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
		//����� ���� Ȧ������ ��� ����ȣ�� �ϳ��� ����̹Ƿ� ����ؼ� ���� �����Ѵ�
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
	
	
	
	
	
	public static void main(String[] args) {
		�����ִ�ȭ b = new �����ִ�ȭ();
		String a = "8+8*8-9+8";
		System.out.println(b.combination(a));
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("-");
		arr.add("*");
		System.out.println(b.calculator("50*3*2", "*", arr));
		
	}

}
