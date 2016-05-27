import board.chess;
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
			int bWins = 0;
			int sumABCalls = 0;
			long startTime = System.currentTimeMillis();

			for (int i=0; i<10; ++i) {
				chess.reset();
				while (chess.winner() == '?') {
					chess.moveAlphabeta(-1, 10000);
					if (chess.winner() != '?') break;
					chess.moveGreedy();
				}
				if (chess.winner() == 'B') ++bWins;
				sumABCalls += chess.abCalls;
			}

			long endTime = System.currentTimeMillis();
			System.out.println("Average game time: " + ((endTime-startTime)/10));
			System.out.println("Black wins: " + bWins);
			System.out.println("10 games abCalls: " + sumABCalls);
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