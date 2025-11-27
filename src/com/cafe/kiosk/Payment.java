package com.cafe.kiosk;

import java.util.Scanner;

public class Payment {

    private boolean reward; 
    private String pay;  
    private Order order; 
    private static int orderNumber = 1;//int: instance 변수, payment 객체 하나마다 따로 존재// static: 클래스 변수, payment 클래스 전체에서 단 한개 존재,새 payment 를 만들더라도 공유
    private int rewardPoint = 0;
    private User currentUser;
    private boolean usedPoint = false;
    
    private Scanner sc; //payment 클래스안에 scanner 가 필요해! 하지만 어떤건지 몰라!라고 알려주는 것

    public Payment(Order order, Scanner user) {
        this.order = order; //총금액 확인 -> 영수증 출력 시 주문 항목 출력, payment가 정보를 읽어야해
        this.sc = user; //사용자 입력 main 에서 만든 scanner 공유
    }

 // 포인트 적립 여부 체크
    public void checkRewardPoint() {
        while (true) {
            System.out.print("포인트 적립 하시겠습니까? (Y/N)\n: ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("YES")) {
                System.out.println("아이디를 입력해주세요.\n:");
                String username = sc.nextLine().trim();
                currentUser = null;

                // 기존 사용자 확인
                for (User user : User.users) {
                    if (user.getId().equals(username)) {
                        currentUser = user;
                        break;
                    }
                }
                int usePoint = 0;
                if (currentUser != null) {
                	int addPoint1  = (int)(order.getTotalPrice()*0.05);
                	usePoint = addPoint1;
                	   currentUser.setRewardPoint(currentUser.getRewardPoint()+usePoint);
                    System.out.println(username + "님의 현재 적립금(예상 적립금 포함)은 " + currentUser.getRewardPoint() + "원입니다.");
                    System.out.println("적립금을 사용하시겠습니까?\n:");
                    String answer = sc.nextLine().trim();                    
                    if(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("YES")){
                    	while(true) {
                    	    System.out.println("사용하실 금액을 입력해주세요.");
                    	    String answer2 = sc.nextLine().trim();
                    	    
                    	    try {
                    	        usePoint = Integer.parseInt(answer2);
                    	    } catch (NumberFormatException e) {
                    	        System.out.println("숫자만 입력해주세요.");
                    	        continue; // 숫자가 아니면 다시 반복
                    	    }
                    	    if(usePoint <= currentUser.getRewardPoint()) {
                    	        currentUser.setRewardPoint(currentUser.getRewardPoint() - usePoint);
                    	        usedPoint = true;
                    	        break;
                    	    } else {
                    	        System.out.println("올바른 금액을 입력해주세요.");
                    	    }
                    		}
                    	}
                    else{  if(answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("no")){
                    System.out.println("적립금을 누적합니다.");
                    break;
                    }}
                    reward = true;
                } else {
                    // 새로 가입할건지 물어보기 
                    System.out.println("해당 아이디가 없습니다. 새로 가입하시겠습니까? (Y/N)\n:");
                    String input2 = sc.nextLine().trim();
                    while(true) {
                    if (input2.equalsIgnoreCase("Y") || input2.equalsIgnoreCase("YES")) {
                        currentUser = new User(username);
                        User.users.add(currentUser);
                        System.out.println(username + "님 회원 가입을 환영합니다!");
                        reward =true;
                        break;
                    } else if(input2.equalsIgnoreCase("N") || input2.equalsIgnoreCase("NO")) {
                        System.out.println("포인트 적립을 진행하지 않습니다.");
                        reward = false;
                        break;
                    }else {System.out.println("올바른 단어를 입력해주세요.");}}
                }
                break;

            } else if (input.equalsIgnoreCase("N") || input.equalsIgnoreCase("NO")) {
                System.out.println("포인트 적립을 원하지 않으셨습니다.");
                reward = false;
                break;

            } else {
                System.out.println("올바른 단어를 입력해주세요.");
            }
        }
    }

    // 결제 방식 선택
    public void makePayment() {
        while (true) {
            System.out.println("결제 방식을 선택하세요.");
            System.out.println("1. 카드");
            System.out.println("2. 현금");
            System.out.print("번호 입력\n: ");

            String input = sc.nextLine().trim(); //nextLine으로 바꿈

            if (input.equals("1")) {
                pay = "카드결제";
                System.out.println(pay + "를 선택하셨습니다.");
                break;
            } else if (input.equals("2")) {
                pay = "현금 결제";
                System.out.println(pay + "를 선택하셨습니다.");
                break;
            } else {
                System.out.println("잘못된 결제방식 입니다.");
            }
        }
    }

    // 결제 완료
    public void priceComplete() {
        System.out.println("총 결제 금액은 " + order.getTotalPrice() + "원 입니다.");
        System.out.println("결제를 진행해주세요.\n.\n.\n.\n.\n결제완료!");
    }

    // 영수증 출력 여부
    public void recieptPrompt() {
        while (true) {
            System.out.println("영수증을 출력하시겠습니까?(Y/N)\n:");
            String input = sc.nextLine().trim();//바꿈

            if (input.equals("Y") || input.equals("y")||input.equals("yes")||input.equals("YES")) {
                printReciept();
                break;
            } else if (input.equals("N") || input.equals("n")||input.equals("no")||input.equals("NO")) {
                System.out.println("영수증 적립 해당 없음");
                System.out.println("-------------------");
                System.out.println("주문번호 : " + orderNumber++);
                System.out.println("-------------------");
                break;
            } else {
                System.out.println("올바른 단어를 입력해주세요.");
            }
        }
    }

    // 영수증 출력
    public void printReciept() {
        System.out.println("--------영수증--------");
        System.out.println("주문번호 : " + orderNumber++);
        System.out.println("총 결제 금액 : " + order.getTotalPrice());
        System.out.println("결제 방식 : " + pay);

        if (reward) {
            int addPoint  = (int)(order.getTotalPrice()*0.05);
            if (usedPoint) {
                System.out.println("총 누적 적립 금액 : " + currentUser.getRewardPoint() + "원");
            } else {
                currentUser.setRewardPoint(currentUser.getRewardPoint() + addPoint);
                System.out.println(currentUser.getId() + "님 포인트 적립 : " + addPoint + "원 / 총 누적 적립 금액 : " + currentUser.getRewardPoint() + "원");
            }
        } else {
            System.out.println("포인트 적립 해당 없음");
        }

        System.out.println("-------------------");
    }
}
