import board.chess;
import pieces.Pawn;

import java.util.BitSet;
import java.util.Random;

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

		/*
		{
			BitSet set = genRandomBitset();

			System.out.println("zobrist = new BitSet[][]{");
			for (int y=0; y<6; ++y) {
				System.out.print("{");
				for (int x=0; x<5; ++x) {
					System.out.print("BitSet.valueOf(new byte[]");
					System.out.print(getByteArrayString());
					System.out.print(")");
					if (x < 4) {
						System.out.print(", ");
					}
				}
				System.out.print("}");
				if (y < 5) {
					System.out.print(",");
				}
				System.out.print("\n");
			}
			System.out.println("};");
			System.out.println(getByteArrayString());
			for (int i=0; i < set.size(); ++i) {
				System.out.print(set.get(i) ? '1' : '0');
			}
			System.out.print('\n');
			System.out.println(set.toString());
			System.out.println(new Pawn(0, 0, true).getValue(3, 1, true));
			System.out.println(new Pawn(0, 0, true).getValue(1, 1, true));
			System.out.println(new Pawn(0, 0, true).getValue(3, 4, false));
			System.out.println(new Pawn(0, 0, true).getValue(1, 4, false));
		}
		*/

		{
			int bWins = 0;
			long startTime = System.currentTimeMillis();

			for (int i=0; i<10; ++i) {
				chess.reset();
				while (chess.winner() == '?') {
					chess.moveAlphabeta(-1, 10000);
					if (chess.winner() != '?') break;
					chess.moveGreedy();
				}
				if (chess.winner() == 'B') ++bWins;
			}

			long endTime = System.currentTimeMillis();
			System.out.println("Average game time: " + ((endTime-startTime)/10));
			System.out.println("Black wins: " + bWins);
		}

		{
			zeromq.start();
		}
	}

	private static BitSet genRandomBitset() {
		Random rand = new Random();
		byte[] randomBytes = new byte[16];
		rand.nextBytes(randomBytes);
		return BitSet.valueOf(randomBytes);
	}

	private static String getByteArrayString() {
		String toReturn = "";

		Random rand = new Random();
		byte[] bytes = new byte[16];
		rand.nextBytes(bytes);

		toReturn += "{";
		for (int i=0; i<bytes.length; ++i) {
			toReturn += bytes[i];
			if (i < bytes.length-1) {
				toReturn += ",";
			}
		}
		toReturn += "}";
		return toReturn;
	}
}