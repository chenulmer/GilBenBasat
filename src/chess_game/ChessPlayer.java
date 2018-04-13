package chess_game;

import board_game.Alliance;
import board_game.Move;
import board_game.Player;


public abstract class ChessPlayer extends Player<ChessGame> {
    public ChessPlayer(String name, Alliance alliance) {
        super(name, alliance);
    }
}
