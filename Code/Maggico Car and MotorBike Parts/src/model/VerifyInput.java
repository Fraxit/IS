package model;

public class VerifyInput
{
	private static VerifyInput sing = null;

	private VerifyInput(){}

	public static synchronized VerifyInput getVerifyInput() {
		if(sing == null)
		{
			sing = new VerifyInput();
		}
		return sing;	
	}

	public boolean inputIsEmpty(String str)
	{
		if(str.equals("") || str.trim().equals("") || str == null)
		{
			return true;
		}
		return false;
	}
}
