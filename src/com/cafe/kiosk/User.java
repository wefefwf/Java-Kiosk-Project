package com.cafe.kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {

	public Scanner sc;
	
	 private String id;
	 private int rewardPoint;
	    
	    
	    //값얻어올때    
	    public String getId(){
	    	return id;
	    }
	    public int getReward(){
	    	return rewardPoint;
	    }
	    public int getRewardPoint() {
			return rewardPoint;
		}
		public void setRewardPoint(int rewardPoint) {
			this.rewardPoint = rewardPoint;
		}
		public void setId(String id) {
			this.id = id;
		}
		//생성자 
		public User() {};
	    public User(String id) {
	        this.id = id;
	        this.rewardPoint = 0;}
	    
	
	
	    static List<User> users = new ArrayList<>();
	
	    public void addUser(String id){
	    	users.add(new User(id));
	    }
	
	 
	
	
}
