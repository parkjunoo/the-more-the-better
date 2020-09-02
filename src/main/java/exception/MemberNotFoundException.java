package exception;

public class MemberNotFoundException extends RuntimeException
{
	public MemberNotFoundException(int id) 
	{
		super("해당 회원 정보를 찾을 수 없습니다.");
	}
}