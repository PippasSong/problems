package 카카오인턴쉽2020;

import java.util.*;

public class 수식최대화 {
	// @param 수식이 담긴 문자열
	// @return  우승 시 받을 수 있는 가장 큰 상금 금액. 음수일 경우 절댓값
	public long solution(String expression) {
        long answer = 0;
       /*
        Map<Integer, ArrayList<String>>comb = combination(expression);
        for(int i=0; i<comb.size(); i++) {
        	ArrayList<String> operators = comb.get(i);
        	
        	//계산결과를 저장할 변수
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
        	//계산결과를 절대값 숫자로 변환
        	int numTemp = Math.abs(Integer.parseInt(tempResult));
        	if(numTemp>answer) {
        		answer = numTemp;
        	}
        	
        }*/
        return answer;
    }

	// @param 수식 문자열
	// @return 최대값
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
	// @return 우선순위 연산자로 개산한 후 남은 값을 리턴
	public String calculator(String expression, String operator) {
		//문자열 복사
		String copy = new String(expression);
		//앞 숫자를 담을 변수
		String firStr = "";
		//뒷 숫자를 담을 변수
		String secStr = "";
		//임시 문자열. 계산에 사용
		String tempStr = "";
		//operator의 index가 있는 배열
		ArrayList<Integer> operators = indexOper(expression, operator);
		
		//operator앞 숫자 구하기
		//계산한 뒤 계산 안한 operator의 인덱스 수정 필요
		for(int n=0; n< operators.size(); n++) {
			int operInd = operators.get(n);
			int startIndex = -1;
			int endIndex = -1;
			//계산한 후의 길이
			int newLength = 0;
			//firStr구하기
			
			for(int i=operInd-1; i>=0; i--) {
				if(copy.charAt(i)!='+'&&copy.charAt(i)!='-'&&copy.charAt(i)!='*') {
					firStr = copy.charAt(i)+firStr;
					startIndex = i;
				} 
				//맨 앞에 - 붙을 경우
				else if(startIndex==1) {
					firStr = "-"+firStr;
					startIndex = 0;
				} else {
					break;
				}
			}
			System.out.println(firStr);
			//secStr구하기
			for(int i=operInd+1; i<copy.length(); i++) {
				if(copy.charAt(i)!='+'&&copy.charAt(i)!='-'&&copy.charAt(i)!='*') {
					secStr = secStr + copy.charAt(i);
					endIndex = i;
				} else {
					break;
				}
			}
			System.out.println(secStr);
			
			//operator에 따른 계산
			if(operator.equals("+")) {
				tempStr = Integer.toString(Integer.parseInt(firStr)+Integer.parseInt(secStr));
				//원본 배열 재정의
				//계산식이 맨 앞에 있을때
				if(startIndex==0) {
					copy = new String(tempStr + copy.substring(endIndex+1, copy.length()));
				} 
				//계산식 앞, 뒤에 아무것도 없을 때
				else if(startIndex==0 && endIndex==copy.length()-1){
					copy = new String(tempStr);
				}
				//계산식이 중간에 있을 때
				else if(startIndex!=0 && endIndex!=copy.length()-1){
					copy = new String(copy.substring(0, startIndex) + tempStr + copy.substring(endIndex+1, copy.length()));
				}
				//계산식이 끝에 있을 때
				else if(startIndex!=0 && endIndex==copy.length()-1){
					copy = new String(copy.substring(0, startIndex) + tempStr);
				}
				newLength = tempStr.length();
			
			}
			else if(operator.equals("-")) {
				tempStr = Integer.toString(Integer.parseInt(firStr)-Integer.parseInt(secStr));
				//원본 배열 재정의
				//계산식이 맨 앞에 있을때
				if(startIndex==0) {
					copy = new String(tempStr + copy.substring(endIndex+1, copy.length()));
				} 
				//계산식 앞, 뒤에 아무것도 없을 때
				else if(startIndex==0 && endIndex==copy.length()-1){
					copy = new String(tempStr);
				}
				//계산식이 중간에 있을 때
				else if(startIndex!=0 && endIndex!=copy.length()-1){
					copy = new String(copy.substring(0, startIndex) + tempStr + copy.substring(endIndex+1, copy.length()));
				}
				//계산식이 끝에 있을 때
				else if(startIndex!=0 && endIndex==copy.length()-1){
					copy = new String(copy.substring(0, startIndex) + tempStr);
				}
				newLength = tempStr.length();
			}
			else if(operator.equals("*")) {
				tempStr = Integer.toString(Integer.parseInt(firStr)*Integer.parseInt(secStr));
				//원본 배열 재정의
				//계산식이 맨 앞에 있을때
				if(startIndex==0) {
					copy = new String(tempStr + copy.substring(endIndex+1, copy.length()));
				} 
				//계산식 앞, 뒤에 아무것도 없을 때
				else if(startIndex==0 && endIndex==copy.length()-1){
					copy = new String(tempStr);
				}
				//계산식이 중간에 있을 때
				else if(startIndex!=0 && endIndex!=copy.length()-1){
					copy = new String(copy.substring(0, startIndex) + tempStr + copy.substring(endIndex+1, copy.length()));
				}
				//계산식이 끝에 있을 때
				else if(startIndex!=0 && endIndex==copy.length()-1){
					copy = new String(copy.substring(0, startIndex) + tempStr);
				}
				newLength = tempStr.length();
			}
			//operator의 마지막 인덱스가 아니면 수정해 줘야 함
			if(n!=operators.size()-1) {
				for(int j=n+1; j<operators.size(); j++) {
					operators.set(j, operators.get(j)-(endIndex-newLength)-1);
				}
				
			}
			//firStr, secStr초기화
			firStr = "";
			secStr = "";
			
		}
		/*잘못된 코드
		//operator가 없어질 때까지 계속 계산
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
							//계산 후 초기화
							firStr = "";
							secStr = "";
						} else {
							//뒤의 문자열 추가
							tempStr+=copy.substring(i+firStr.length()+secStr.length()-2, copy.length());
							copy = new String(tempStr);
							//계산 후 초기화
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
							//계산 후 초기화
							firStr = "";
							secStr = "";
						} else {
							//뒤의 문자열 추가
							tempStr+=copy.substring(i+firStr.length()+secStr.length()-2, copy.length());
							copy = new String(tempStr);
							//계산 후 초기화
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
							//계산 후 초기화
							firStr = "";
							secStr = "";
						} else {
							//뒤의 문자열 추가
							tempStr+=copy.substring(i+firStr.length()+secStr.length()-2, copy.length());
							copy = new String(tempStr);
							//계산 후 초기화
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
	
	//@para 문자열, 연산자
	//@return 연산자의 인덱스 배열
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
	//잘못된 코드
	// @param 수식 문자열, 우선순위 연산자, 전체 연산자 배열
	// @return 매개변수 연산자로 개산한 후 남은 값을 리턴
	public String calculator(String expression, String operator, ArrayList<String> operators) {
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
		
		ArrayList<String> temp = new ArrayList<String>();
		temp.addAll(operators);
		temp.remove(operator);
		String[] strSplit = expression.split(newOperator);
		
		
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
				//연산자가 하나인 경우
				if(lastNum==-1||firstNum==-1) {
					//연산자 위치
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
				//계산할 숫자
				numToCal.add(Integer.parseInt(strSplit[i].substring(0, firstNum)));
				numToCal.add(Integer.parseInt(strSplit[i].substring(lastNum+1)));
				
				//계산할 숫자 외의 문자열
				strSplit[i] = strSplit[i].substring(firstNum, lastNum+1);
			}
			//마지막 요소일때 첫번째 숫자만 추가
			else if(i%2==1&&i==strSplit.length-1) {
				int num = strSplit[i].length();
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
				//연산자가 하나인 경우
				if(lastNum==-1||firstNum==-1) {
					//연산자 위치
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
				//계산할 숫자
				numToCal.add(Integer.parseInt(strSplit[i].substring(0, firstNum)));
				numToCal.add(Integer.parseInt(strSplit[i].substring(lastNum+1)));
				
				//계산할 숫자 외의 문자열
				strSplit[i] = strSplit[i].substring(firstNum, lastNum+1);
			}
		}

			
		//숫자를 하나씩 읽고 뒤의 부호가 operator인 경우 오퍼 뒤의 부호와 계산해서 result에 대입하기
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
		
		//계산할 수가 홀수계인 경우 앞부터 순차적으로 계산
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
	*/
	
	
	
	
	public static void main(String[] args) {
		수식최대화 b = new 수식최대화();
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
