package model.pieces;

import model.Move;
import model.Player;
import model.Square;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Rook extends AbstractStraightMovingPiece {

	public Rook(Player player) {
		super(player);
		textualRepresentation = "R";
	}

	@Override
	public void updatePossibleMoves() {
		boolean isProtectingHisKing = isProtectingHisKing();
		if (isProtectingHisKing) {
			normalMoves.clear();
		}

		List<Square> up = getSquaresInStraightLineUntilFirstPiece(AbstractStraightMovingPiece.UP, AbstractStraightMovingPiece.STAY);
		List<Square> down = getSquaresInStraightLineUntilFirstPiece(AbstractStraightMovingPiece.DOWN, AbstractStraightMovingPiece.STAY);
		List<Square> right = getSquaresInStraightLineUntilFirstPiece(AbstractStraightMovingPiece.STAY, AbstractStraightMovingPiece.RIGHT);
		List<Square> left = getSquaresInStraightLineUntilFirstPiece(AbstractStraightMovingPiece.STAY, AbstractStraightMovingPiece.LEFT);

		List<Square> all = new LinkedList<>();
		all.addAll(up);
		all.addAll(down);
		all.addAll(left);
		all.addAll(right);

		for (Square square : all) {
			if(checks(square.getPiece())){
				//TODO piece is check
			}
			if (square.isEmpty() && !isProtectingHisKing) {
				//TODO set to moves
				//TODO add to threatened squares
			}
			if (!square.isEmpty() && !square.getPiece().isSameColor(this)) {
				if (square.getPiece() instanceof King) {
					//TODO opponent is check
				} else {
					if (isProtectingHisKing) {
						//TODO piece is threatened
					} else {
						//TODO piece can be thrown
					}
				}
			}
		}
	}


}
