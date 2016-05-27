package pieces;

import board.chess;

import java.util.BitSet;
import java.util.Vector;

/**
 * Created by Corey on 4/6/2016.
 */
public class Pawn extends Piece {

	private static BitSet[][]	zobrist = new BitSet[][]{
			{BitSet.valueOf(new byte[]{0,87,-54,-27,-65,110,58,16,-88,98,34,4,12,-74,-58,100}), BitSet.valueOf(new byte[]{-106,7,56,31,109,91,92,106,-73,-36,-21,108,-16,33,-110,126}), BitSet.valueOf(new byte[]{58,-110,102,92,-85,-23,28,85,-34,-66,-48,44,125,43,12,-101}), BitSet.valueOf(new byte[]{-115,-104,-11,24,-26,-40,-1,65,97,116,-59,97,-119,-74,67,-81}), BitSet.valueOf(new byte[]{-13,125,123,100,104,80,120,5,-105,31,-30,-8,13,109,52,-112})},
			{BitSet.valueOf(new byte[]{-108,-23,127,41,-7,85,32,74,-86,-63,89,-39,113,106,-69,115}), BitSet.valueOf(new byte[]{125,110,112,-76,44,9,-24,116,54,-51,9,46,105,-57,11,48}), BitSet.valueOf(new byte[]{19,5,48,88,-68,12,9,-20,33,90,22,40,24,38,52,122}), BitSet.valueOf(new byte[]{16,-64,71,110,-77,-43,-106,-11,9,58,37,124,-124,58,-69,99}), BitSet.valueOf(new byte[]{-115,-112,-60,-12,48,90,6,-24,93,-74,-99,-29,36,-55,-36,-42})},
			{BitSet.valueOf(new byte[]{61,-26,34,-49,73,17,5,-117,-67,-69,80,81,-30,-4,-15,-117}), BitSet.valueOf(new byte[]{-63,-18,70,-21,74,-69,14,-82,45,110,40,1,-66,-21,24,108}), BitSet.valueOf(new byte[]{-60,25,113,-96,46,-1,-62,112,107,-46,7,76,126,-65,36,-52}), BitSet.valueOf(new byte[]{8,97,-23,100,-73,123,-23,118,-20,-17,97,-56,59,-16,-85,76}), BitSet.valueOf(new byte[]{-81,-5,-94,94,-14,-36,75,-125,-53,16,-122,52,56,20,-11,-64})},
			{BitSet.valueOf(new byte[]{117,-16,-80,-128,-9,119,75,-91,31,-72,4,-3,80,102,62,86}), BitSet.valueOf(new byte[]{-48,107,39,100,-71,79,-95,108,-53,-74,-60,-105,62,112,17,-53}), BitSet.valueOf(new byte[]{119,18,95,-13,30,-116,15,65,65,77,-69,67,-77,-34,101,28}), BitSet.valueOf(new byte[]{65,112,-104,-55,-106,-108,-13,118,-85,-116,-27,-82,-14,68,118,27}), BitSet.valueOf(new byte[]{-29,-67,-95,-14,-22,-110,126,4,89,85,6,-87,41,-46,-26,-58})},
			{BitSet.valueOf(new byte[]{-67,106,89,108,85,-6,-41,31,-43,72,120,-52,5,84,34,80}), BitSet.valueOf(new byte[]{31,14,-27,-66,102,-43,100,95,90,-21,-16,-81,107,-38,-8,70}), BitSet.valueOf(new byte[]{-5,-86,-57,116,-89,-17,-7,33,-70,5,53,-26,-90,57,5,-94}), BitSet.valueOf(new byte[]{84,14,16,19,72,66,-44,96,87,-93,39,-10,123,-74,26,-43}), BitSet.valueOf(new byte[]{-124,3,88,-30,109,-123,-65,-95,-107,-85,-94,119,123,123,96,16})},
			{BitSet.valueOf(new byte[]{-57,82,81,-4,-102,-4,61,10,69,-57,-118,77,-111,51,64,76}), BitSet.valueOf(new byte[]{-122,46,49,-98,-123,5,-14,6,22,12,83,107,-75,-105,-126,-3}), BitSet.valueOf(new byte[]{6,-62,35,20,85,94,-65,-2,-37,119,87,102,5,25,-12,99}), BitSet.valueOf(new byte[]{31,79,-125,-16,94,-104,80,0,27,-125,-104,-23,-28,97,-128,-87}), BitSet.valueOf(new byte[]{-6,88,-116,-114,97,70,120,119,14,-34,8,-71,0,52,22,26})}
	};

	public Pawn(int x, int y, boolean white) {
		super(white ? 'P' : 'p');
		value = new double[][]{
				{60, 60, 60, 60, 60},
				{40, 40, 40, 40, 40},
				{30, 30, 30, 30, 30},
				{20, 20, 25, 25, 25},
				{20, 20, 30, 35, 40},
				{0, 0, 0, 0, 0, 0},
		};
	}

	@Override
	public Vector<Move> possibleMoves(int x, int y) {
		Vector moves = new Vector<>();
		int yDirection = Character.isUpperCase(type) ? 1 : -1;

		if (x < 4 && chess.isEnemy(x + 1, y + yDirection)) moves.add(new Move(x, y, 1, yDirection));
		if (x > 0 && chess.isEnemy(x - 1, y + yDirection)) moves.add(new Move(x, y, -1, yDirection));
		if (chess.isNothing(x, y+yDirection)) moves.add(new Move(x, y, 0, yDirection));

		return moves;
	}

	public static BitSet getZobrist(int x, int y) {
		return zobrist[y][x];
	}
}
