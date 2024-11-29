package cs3500.threetrios.controller;

import cs3500.threetrios.model.GridPos2d;
import cs3500.threetrios.model.MachinePlayer;
import cs3500.threetrios.model.Move;
import cs3500.threetrios.model.Player;
import cs3500.threetrios.model.PlayerType;
import cs3500.threetrios.model.ThreeTrios;
import cs3500.threetrios.view.ThreeTriosView;

/**
 * The controller will listen to the view and the model.  It will
 * also be updating the view and model based on what the player has done.
 */
public class ThreeTriosController implements Controller {
  private final ThreeTrios model;
  private final ThreeTriosView view;
  private final PlayerType player;
  private boolean isMyTurn = false;
  private Integer selectedCardIndex = null;

  /**
   * Constructor for the controller.
   * @param model model used
   * @param view view used
   * @param player player used
   */
  public ThreeTriosController(ThreeTrios model, ThreeTriosView view, PlayerType player) {
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

    if (isMyTurn) {
      view.bringToFront();
      if (player instanceof MachinePlayer) {
        player.performAction();
      }
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

  @Override
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
