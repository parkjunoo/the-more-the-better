package io.playdata.themorethebetter.exception;

public class SameMemberException extends RuntimeException
{
	public SameMemberException() 
	{
		super("이미 존재하는 회원입니다!");
	}
}
