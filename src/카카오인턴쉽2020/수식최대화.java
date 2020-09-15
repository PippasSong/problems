package 카카오인턴쉽2020;

import java.util.*;

public class 수식최대화 {
	// @param 수식이 담긴 문자열
	// @return  우승 시 받을 수 있는 가장 큰 상금 금액. 음수일 경우 절댓값
	public long solution(String expression) {
        long answer = 0;
        return answer;
    }

	// @param 수식 문자열
	// @return 최대값
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
		//연산자 우선순위 경우의 수
		Map<Integer,List<String>> combination = new HashMap<Integer,List<String>>();
		
		
		//연산자의 개수에 따라서 경우의 수 
		if(listOperator.size()==1) {
			combination.put(0, new ArrayList<String>(listOperator));
		} else if(listOperator.size()==2) {
			combination.put(0, new ArrayList<String>(listOperator));
			ArrayList<String> temp = new ArrayList<String>();
			
			//순서 바꾸어 넣기
			temp.add(listOperator.get(1));
			temp.add(listOperator.get(0));
			combination.put(0, temp);
			
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
	
	// @param 수식 문자열, 우선순위 연산자, 전체 연산자 배열
	// @return 매개변수 연산자로 개산한 후 남은 값을 리턴
	public String calculator(String expresssion, String operator, ArrayList<String> operators) {
		String result = "";
		String newOperator = null;
		//특수문자로 변환하기 위해 변환
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
		
		
		//계산할 수를 담을 배열
		ArrayList<Integer> numToCal = new ArrayList<Integer>();
		//계산을 마친 뒤 문자열
		ArrayList<String> numResult = new ArrayList<String>();
		
		
		for(int i=0; i<strSplit.length; i++) {
			//첫번째 요소일때 마지막 숫자만 추가
			if(i%2==0&&i==0) {
				int num = -1;
				for(String str : temp) {
					if(strSplit[i].lastIndexOf(str)>num) {
						num = strSplit[i].lastIndexOf(str);
					}
				}
				//계산할 숫자
				numToCal.add(Integer.parseInt(strSplit[i].substring(num+1)));
				//계산할 숫자 외의 문자열
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
				//연산자가 없는 경우
				if(lastNum==firstNum&&lastNum==-1) {
					numToCal.add(Integer.parseInt(strSplit[i]));
					continue;
				}
				//계산할 숫자
				numToCal.add(Integer.parseInt(strSplit[i].substring(0, firstNum)));
				numToCal.add(Integer.parseInt(strSplit[i].substring(lastNum+1)));
				
				//계산할 숫자 외의 문자열
				strSplit[i] = strSplit[i].substring(firstNum, lastNum+1);
			}
			//마지막 요소일때 첫번째 숫자만 추가
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
				//계산할 숫자
				numToCal.add(Integer.parseInt(strSplit[i].substring(0, num)));
				//계산할 숫자 외의 문자열
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
				
				//연산자가 없는 경우
				if(lastNum==firstNum&&lastNum==-1) {
					numToCal.add(Integer.parseInt(strSplit[i]));
					continue;
				}
				//계산할 숫자
				numToCal.add(Integer.parseInt(strSplit[i].substring(0, firstNum)));
				numToCal.add(Integer.parseInt(strSplit[i].substring(lastNum+1)));
				
				//계산할 숫자 외의 문자열
				strSplit[i] = strSplit[i].substring(firstNum, lastNum+1);
			}
		}

			

		//계산
		//계산할 수가 짝수계인 경우
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
		//계산할 수가 홀수계인 경우 계산부호가 하나인 경우이므로 계산해서 값을 리턴한다
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

		
		//완성된 문자열 만들기
		int numResultIndex = 0;
		for(int i=0; i<strSplit.length-1; i+=2) {
			result+=strSplit[i];
			result+=numResult.get(numResultIndex);
			result+=strSplit[i+1];
			numResultIndex++;
		}
		//계산값의 수가 짝수을 경우 마지막 결과 추가
		if(numResult.size()%2==0) {
			result+=numResult.get(numResult.size()-1);
		}
		
		
		return result;
	}
	
	
	
	
	
	public static void main(String[] args) {
		수식최대화 b = new 수식최대화();
		String a = "8+8*8-9+8";
		System.out.println(b.combination(a));
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("-");
		arr.add("*");
		System.out.println(b.calculator("50*3*2", "*", arr));
		
	}

}
