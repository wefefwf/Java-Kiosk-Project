package com.cafe.kiosk;

import java.util.ArrayList;
import java.util.List;

class OrderItem {
    private String menu;
    private int quantity;
    private int price;

    // 생성자
    public OrderItem(String menu, int quantity, int price) {
        this.menu = menu;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter
    public String getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    // 총 가격 계산
    public int getTotalPrice() {
        return quantity * price;
    }

    // 주문 항목 정보 출력
    @Override
    public String toString() {
        return "메뉴: " + menu + ", 수량: " + quantity + ", 가격: " + price + "원, 총 가격: " + getTotalPrice() + "원";
    }
}

public class Order {

    private List<OrderItem> orderItems = new ArrayList<>();
    private int totalPrice = 0; //현재 바구니의 총 결제 금액

    // 주문을 추가하는 메서드
    public void addOrder(String menu, int quantity, int price) { //addOrder: 주문을 장바구니에 넣는 동작
        OrderItem orderItem = new OrderItem(menu, quantity, price);//OrderItem 주문 한 줄(menu, qty, price)
        orderItems.add(orderItem);//orderItems: 리스트(주문을 담는 그릇)
        totalPrice += orderItem.getTotalPrice(); // 총 가격 계산(추가주문 이면 계속 누적)
    }

    // 주문 상세 정보를 출력 (Order 객체를 출력하면 자동으로 호출)
    @Override
    public String toString() { //객체를 문자열로 위에 있음
        String details = ""; //toString의 최종결과를 담는 그릇(details 라는 빈문자열 만들기, 주문 항목 정보 붙이기)
        for (OrderItem item : orderItems) {//for(OrderItem item : orderItems) → 리스트 안 항목을 하나씩 꺼내서 item 으로 다룸
            details += item.toString() + "\n";  
        }
        details += "총 결제 금액: " + totalPrice + "원";
        return details;
    }

    // 총 가격 반환
    public int getTotalPrice() {
        return totalPrice;
    }
}