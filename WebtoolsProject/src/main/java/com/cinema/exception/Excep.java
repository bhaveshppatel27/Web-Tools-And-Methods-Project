package com.cinema.exception;

public class Excep extends Exception
{
	public Excep(String message)
	{
		super(message);
	}
	
	public Excep(String message, Throwable cause)
	{
		super(message,cause);
	}
}