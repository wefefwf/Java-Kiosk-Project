package com.cafe.kiosk;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner user = new Scanner(System.in);//사용자의 키보드 입력을 위해 생성 UserScanner
        Menu menu = new Menu();
        User usermanager = new User();

        while (true) {

            Order order = new Order(); //프로그램을 계속 실행해서 한 주문이 끝나면 다시 처음
            Payment payment = new Payment(order, user);//order: 사용자가 지금까지 선택한 메뉴, 수량 , 가격을 담고 있는 객체
            //user: scanner 즉 사용자가 입력을 받을 도구

            // 메뉴 출력
            menu.showMenu();
            menu.showRecommend();

            System.out.println("\n원하시는 메뉴를 입력해주세요.\n:");
            String selectMenu;

            while (true) {
                selectMenu = user.nextLine().trim(); //사용자 입력을 한 줄 읽고 앞뒤 공백 제거(실수완화)
                int price = menu.getPrice(selectMenu);//menu에서 가격을 얻음
                if (price <= 0) { //메뉴가 있으면 0이하의 값으로 돌려줌 **menu의 -1**
                    System.out.println("없는 메뉴입니다. 다시 입력하세요:");
                } else {
                    break;
                }
            }// 수량 입력
            System.out.println("수량을 입력해주세요:");
            int qty;//while문 안에서 만든 qty값을 while밖에서도 써야함, (order.addOrder)
            while (true) {
                try {
                    qty = Integer.parseInt(user.nextLine().trim()); //사용자가 입력한 줄을 문자열로 가져옴/공백제거/문자열 정수 변환(숫자가 아니면 모두 오류)/뱐환 성공하면 qty에 넣어줌
                    break;
                } catch (Exception e) {
                    System.out.println("숫자로 입력해주세요:");
                }
            }

            int price = menu.getPrice(selectMenu);//사용자가 선택한 메뉴 이름을 기반으로 가격받아옴
            order.addOrder(selectMenu, qty, price);//오더 객체에서 입력받은걸 여기에 저장

            // 추가 주문
            while (true) {
                System.out.println("추가 주문하시겠습니까? (Y/N)");
                String more = user.nextLine().trim();

                if (more.equalsIgnoreCase("n") || more.equalsIgnoreCase("no") || more.equalsIgnoreCase("N") || more.equalsIgnoreCase("NO")) break;
                if (more.equalsIgnoreCase("y") || more.equalsIgnoreCase("yes")|| more.equalsIgnoreCase("Y")|| more.equalsIgnoreCase("YES")) {
                	
                	menu.showMenu(); //추가 주문이 나오도록
                    menu.showRecommend();

                    // 메뉴 다시 받기
                    System.out.println("\n원하시는 메뉴를 입력해주세요:");
                    while (true) {
                        selectMenu = user.nextLine().trim();
                        price = menu.getPrice(selectMenu);
                        if (price <= 0)
                            System.out.println("없는 메뉴입니다. 다시 입력하세요:");
                        else
                            break;
                    }

                    // 수량 입력
                    System.out.println("수량을 입력해주세요:");
                    while (true) {
                        try {
                            qty = Integer.parseInt(user.nextLine().trim());
                            break;
                        } catch (Exception e) {
                            System.out.println("숫자로 입력해주세요:");
                        }
                    }

                    order.addOrder(selectMenu, qty, price);
                } else {
                    System.out.println("올바른 단어를 입력해주세요.");
                }
            }

            // 주문 내역 출력
            System.out.println("\n===== 주문 내역 =====");
            System.out.println(order);

            // 결제 처리
            payment.checkRewardPoint();
            payment.makePayment();
            payment.priceComplete();
            payment.recieptPrompt();

            // 다시 시작 여부
            System.out.println("처음으로 가려면 Enter키를 누르세요.");
            user.nextLine(); // pause
        }
    }
}
