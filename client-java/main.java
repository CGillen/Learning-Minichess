import board.chess;
import pieces.*;

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
			System.out.println(new Pawn(0, 1, true).getValue(0, 1, true));
			System.out.println(new Pawn(1, 1, true).getValue(1, 1, true));
			System.out.println(new Pawn(2, 1, true).getValue(2, 1, true));
			System.out.println(new Pawn(3, 1, true).getValue(3, 1, true));
			System.out.println(new Pawn(4, 1, true).getValue(4, 1, true));

			System.out.println(new Rook(0, 0, true).getValue(0, 0, true));
			System.out.println(new Knight(1, 0, true).getValue(1, 0, true));
			System.out.println(new Bishop(2, 0, true).getValue(2, 0, true));
			System.out.println(new Queen(3, 0, true).getValue(3, 0, true));
			System.out.println(new King(4, 0, true).getValue(4, 0, true));

			System.out.println(new Empty(4, 0).getValue(4, 0, false));
			System.out.println(new Empty(4, 1).getValue(4, 1, false));
			System.out.println(new Empty(4, 2).getValue(4, 2, false));
			System.out.println(new Empty(4, 3).getValue(4, 3, false));
			System.out.println(new Empty(4, 4).getValue(4, 4, false));
			System.out.println(new Empty(5, 0).getValue(5, 0, false));
			System.out.println(new Empty(4, 1).getValue(5, 1, false));
			System.out.println(new Empty(4, 2).getValue(5, 2, false));
			System.out.println(new Empty(4, 3).getValue(5, 3, false));
			System.out.println(new Empty(4, 4).getValue(5, 4, false));

			System.out.println(new Pawn(0, 4, true).getValue(0, 4, false));
			System.out.println(new Pawn(1, 4, true).getValue(1, 4, false));
			System.out.println(new Pawn(2, 4, true).getValue(2, 4, false));
			System.out.println(new Pawn(3, 4, true).getValue(3, 4, false));
			System.out.println(new Pawn(4, 4, true).getValue(4, 4, false));

			System.out.println(new Rook(0, 5, true).getValue(4, 5, false));
			System.out.println(new Knight(1, 5, true).getValue(3, 5, false));
			System.out.println(new Bishop(2, 5, true).getValue(2, 5, false));
			System.out.println(new Queen(3, 5, true).getValue(1, 5, false));
			System.out.println(new King(4, 5, true).getValue(0, 5, false));
		}
		*/

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
		}
		*/

		/*
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
		*/

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