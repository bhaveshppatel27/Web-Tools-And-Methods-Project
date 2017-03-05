package com.cinema.Addons;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.cinema.dao.BankAccountDAO;
import com.cinema.exception.Excep;
import com.cinema.pojo.BankAccount;


public class RandomNumberGenerator {

	BankAccountDAO bankAccountDAO = new BankAccountDAO();
	
	public int getCardNumber(){
		

		Random r = new Random();
		int low = 100000;
		int high = 999999;
		int cardnumber = r.nextInt(high-low) + low;
		
		try {
			ArrayList<BankAccount> baList = (ArrayList<BankAccount>) bankAccountDAO.list();
			
			for(BankAccount ba : baList){
				if(ba.getCardnumber() == cardnumber){
					return getCardNumber();
				}
			}
		} catch (Excep e) {
			
			e.printStackTrace();
		}
		
		
		return cardnumber;
		
	}
	
public int getPIN(){
		

		Random r = new Random();
		int low = 1000;
		int high = 9999;
		int pin = r.nextInt(high-low) + low;
		
		return pin;
		
	}
	
}
