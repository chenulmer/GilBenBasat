package board_game;

import java.util.Collection;

public abstract class BoardGame <T extends Board> {
    protected T _board;
    protected Alliance[] _allianceCycle;
    protected int _allianceTurnIndex;
    protected BoardGame(T board, Collection allianceCycle) {
        _board = board;
        _allianceCycle = (Alliance[]) allianceCycle.toArray();
        _allianceTurnIndex = 0;
    }

    public abstract Collection<Move> getPossibleMoves();

    public abstract GameState getGameState();

    public abstract void makeMove(Move move);

    public abstract void undoMove();
}
