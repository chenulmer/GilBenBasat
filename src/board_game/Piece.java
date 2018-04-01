package board_game;

public abstract class Piece {
    private final Alliance _alliance;

    protected Piece(Alliance alliance) {
        _alliance = alliance;
    }

    public Alliance getAlliance() {
        return _alliance;
    }
}