import pieces.Pawn;

public class main {
	public static int intZeromq = 54361; // CHANGE THIS - OPTIONAL
	public static String strName = "'OR'1'='1"; // CHANGE THIS - REQUIRED
	
	public static void main(String[] args) {
		{
			assert main.intZeromq > 1024;
			assert main.intZeromq < 65535;
			
			assert main.strName.length() > 2;
			assert main.strName.length() < 16;
			assert main.strName.indexOf(" ") == -1;
		}

		{
			System.out.println(new Pawn(0, 0, true).getValue(3, 1, true));
			System.out.println(new Pawn(0, 0, true).getValue(1, 1, true));
			System.out.println(new Pawn(0, 0, true).getValue(3, 4, false));
			System.out.println(new Pawn(0, 0, true).getValue(1, 4, false));
		}

		{
			zeromq.start();
		}
	}
}