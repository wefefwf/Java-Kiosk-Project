package com.cafe.kiosk;

import java.util.Scanner;

public class Payment {

    private boolean reward; 
    private String pay;  
    private Order order; 
    private static int orderNumber = 1;//int: instance 변수, payment 객체 하나마다 따로 존재// static: 클래스 변수, payment 클래스 전체에서 단 한개 존재,새 payment 를 만들더라도 공유
    private int rewardPoint = 0;

    
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

            if (input.equals("Y") || input.equals("y")||input.equals("yes")||input.equals("YES")) {
                reward = true;
                break;
            } else if (input.equals("N") || input.equals("no")||input.equals("n")||input.equals("NO")) {
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
        System.out.println("결제완료!");
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
            rewardPoint = (int) (order.getTotalPrice() * 0.05);
            System.out.println("포인트 적립 : " + rewardPoint + "원");
        } else {
            System.out.println("포인트 적립 해당 없음");
        }

        System.out.println("-------------------");
    }
}
