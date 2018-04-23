package gchess.boardgame;

import gchess.boardgame.states.InGame;

import java.util.ArrayList;
import java.util.Collection;

import static gchess.chess.utils.ChessGameUtils.enemyOf;

public abstract class BoardGamePlatform<T extends BoardGame, U extends Player> implements Runnable {
    protected Collection<U> _players;
    protected T _game;
    protected int _playerTurnIndex;
    public BoardGamePlatform(ArrayList<U> players) {
        _players = players;
        _playerTurnIndex = 0;
    }

    public void run() {
        GameState state = _game.getGameState();
        // Game started; tell to all players.
        for (U player : _players) {
            player.onGameStarted(_game.getCopy());
        }
        while (state instanceof InGame) {
            U currentPlayer = ((ArrayList<U>)_players).get(_playerTurnIndex % _players.size());
            Move nextMove = currentPlayer.getNextMove(_game.getCopy());
            if (_game.getPossibleMoves().contains(nextMove)) {
                _game.makeMove(nextMove);
                // Player made move; tell to all players.
                for (U player : _players) {
                    player.onPlayerMadeMove(_game.getCopy());
                }
                _playerTurnIndex++;
                state = _game.getGameState();
            }
            /* Only for testing!!! */
            else if(nextMove instanceof UndoMove) {
                _game.undoMove();
                _playerTurnIndex--;
                state = _game.getGameState();
            }
        }
        // Game ended; tell to all players.
        for (U player : _players) {
            player.onGameEnded(_game.getCopy());
        }
    }
}