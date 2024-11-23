package cs3500.controller;

import cs3500.model.GridPos2d;
import cs3500.model.MachinePlayer;
import cs3500.model.ModelListener;
import cs3500.model.Move;
import cs3500.model.Player;
import cs3500.model.PlayerType;
import cs3500.model.ThreeTriosModel;
import cs3500.view.ThreeTriosViewImpl;
import cs3500.view.ViewListener;

public class ThreeTriosController implements ViewListener, ModelListener {
  private final ThreeTriosModel model;
  private final ThreeTriosViewImpl view;
  private final PlayerType player;
  private boolean isMyTurn = false;
  private Integer selectedCardIndex = null;

  public ThreeTriosController(ThreeTriosModel model, ThreeTriosViewImpl view, PlayerType player) {
    this.model = model;
    this.view = view;
    this.player = player;

    model.addModelListener(this);
    view.addViewListener(this);

    updateViewTitle();
  }

  /**
   * Notifies the listener when the turn has changed.
   *
   * @param currentPlayer current player
   */
  @Override
  public void onTurnChanged(Player currentPlayer) {
    isMyTurn = currentPlayer.equals(player.getPlayerColor());
    updateViewTitle();
    view.setInputEnabled(isMyTurn);

    if (isMyTurn && player instanceof MachinePlayer) {
      player.performAction();
    }
  }

  /**
   * Notifies the listener when the game is over.
   *
   * @param winner winning player
   */
  @Override
  public void onGameOver(Player winner, int winningScore) {
    String message;
    if (winner == null) {
      message = "The game is a draw!";
    } else if (winner.equals(player.getPlayerColor())) {
      message = "You win! Your score: " + winningScore;
    } else {
      message = "You lose. Opponent's score: " + winningScore;
    }
    view.showGameOver(message);
  }

  /**
   * When a player selects a card from their hand
   * the listener will receive this event.
   *
   * @param cardIndex index of card
   */
  @Override
  public void onCardSelected(int cardIndex) {
    if (isMyTurn) {
      selectedCardIndex = cardIndex;
      view.highlightSelectedCard(cardIndex, player.getPlayerColor());
    } else {
      view.showError("It's not your turn.");
    }
  }

  /**
   * When a player selects a position on the board
   * to place a card, the listener will receive this
   * event.
   *
   * @param row row of position
   * @param col col of position
   */
  @Override
  public void onPosSelected(int row, int col) {
    if (!isMyTurn) {
      view.showError("It's not your turn.");
      return;
    }
    if (selectedCardIndex == null) {
      view.showError("Please select a card.");
      return;
    }

    try {
      model.placeCard(new GridPos2d(row, col), selectedCardIndex);
      selectedCardIndex = null;
      view.highlightSelectedCard(-1, player.getPlayerColor());
      view.highlightSelectedCard(-1, player.getPlayerColor().nextPlayer());
      view.refresh();
    } catch (IndexOutOfBoundsException e) {
      view.showError("Invalid move: " + e.getMessage());
    }
  }

  private void updateViewTitle() {
    String status = isMyTurn ? "Your Turn" : "Waiting...";
    String title = player.toString() + " - " + status;
    view.updateStatus(title);
  }

  public void handleMachinePlayerMove(MachinePlayer machinePlayer, Move move) {
    if (isMyTurn && player.equals(machinePlayer)) {
      try {
        model.placeCard(move.getPosition(), move.getCardIdxInHand());
        selectedCardIndex = null;
        view.refresh();
      } catch (IndexOutOfBoundsException e) {
        view.showError("Invalid move by machine player: " + e.getMessage());
      }
    }
  }

}
