package com.eb.service;

import com.eb.entity.Commercial;
import com.eb.entity.Domestic;
import com.eb.exception.InvalidConnectionException;
import com.eb.exception.InvalidReadingException;

public class ConnectionService {
	
	public boolean validate(int currentReading, int previousReading, String type)
	boolean result=false;
	boolean prevcureresult=false;
	boolean typeresult=false;
	
	if(currentReading<previousReading||currentReading<0||previousReading<0) {
		throw new InvalidReadingException();
	}
	else {
		prevcureresult=true;
	}
	if(!(type.equals("Domestic")||type.equals("Commercial"))) {
		throw new InvalidConnectionException();
		
	}
	else {
		typeresult=true;
		
	}
	if(prevcureresult&&typeresult) {
		result=true;
	}
     
	return result;
	}
   
public float calculateBillAmt(int currentReading, int previousReading, String type) {
	boolean result=false;
	float endbill=0.0f;
	
	try {
		result=validate(currentReading, previousReading, type);
		
	}
	catch(InvalidReadingException e) {
		
		return -1;
		
	}
	catch(InvalidConnectionException ce) {
		
		return -2;
		
	}
	if(result==true) {
		if(type.equals("Domestic")) {
			
			Domestic domestic=new Domestic(previousReading,currentReading,new float[] {2.3f,4.2f,5.5f});
			endbill=domestic.computeBil();
		}
		else if(type.equals("Commercial")) {
			
			Commercial commercial=new Commercial(previousReading,currentReading,new float[] {5.2f,6.8f,8.3f});
			 
			endbill=commercial.computeBil();
			
		}
	}
	return endbill;
}

 public String generateBill(int currentReading, int previousReading, String type)
 throws InvalidReadingException, InvalidConnectionException{
	 
	 float finalBill=0.0f;
	 
	 finalBill=calculateBillAmt(currentReading, previousReading, type);
	 if(finalBill==-1) {
		 throw new InvalidReadingException();
	 }
	 else if(finalBill==-2) {
		 throw new InvalidConnectionException();
		 
	 }
	 else {
		 return"Amount to be paid:"+finalBill;
	 }
	 
	 }
 }
