package com.cafe.kiosk;

class MenuItems {
	
	//메뉴 이름 가격 
	private String menuName;
	private int price;
	
	//---------------------------getter/setter
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
		
	}
//--------------------------------	
	//생성자
	public MenuItems(String menuName , int price){
		this.menuName = menuName;
		this.price = price;
	}
}

public class Menu {

	MenuItems menu[] = {
		new MenuItems("아메리카노",2000),
		new MenuItems("카페라떼",3800),
		new MenuItems("에스프레소",3000),
		new MenuItems("캬라멜 마끼아또",4000),
		new MenuItems("바닐라 라떼",3600)
	};
	//메뉴 목록안내
	public void showMenu(){
		System.out.println("###메뉴 목록 안내###");
		for(int i = 0; i < menu.length; i++) {
			System.out.printf("▷%s : %d원\n",menu[i].getMenuName(), menu[i].getPrice());
		}
	}
	//오늘의 추천 메뉴
	public void showRecommend(){
		int randomMenu = (int)(Math.random()*5);
		System.out.println("▶오늘의 추천 메뉴는 " + menu[randomMenu].getMenuName() + "입니다.");
	}
	
	
	//이름으로 가격조회
    public int getPrice(String menuName) {
        for (MenuItems item : menu) { //for문이 메뉴 전체를 돌며 비교
            if (item.getMenuName().equals(menuName)) { //이름이 같으면 -> 그 메뉴의 가격 return
                return item.getPrice();
            }
        }
        return -1; //아니면 -1
    }
	//menu 오버라이딩
	@Override
	public String toString() {
		return "카페 키오스크_메뉴 안내 및 오늘의 메뉴 추천관련 클래스";
	}
}

