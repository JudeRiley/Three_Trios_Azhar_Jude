package cs3500.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ThreeTriosModel implements ThreeTrios {

  private final Grid grid;
  private Player turn;
  private final List<Card> redHand;
  private final List<Card> blueHand;

  // total number of cards needs to be AT LEAST grid.numberOfCardCells + 1

  public ThreeTriosModel(List<Card> deck, Grid grid) {
    if (deck == null || grid == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    if (!(deck.size() >= grid.getNumCardCells() + 1)) {
      throw new IllegalArgumentException("Deck size must be at least equal to the "
              + "number of card cells in the grid plus one.");
    }
    if (deck.size() % 2 != 0) {
      throw new IllegalArgumentException("Deck size must be even.");
    }

    this.redHand = deck.subList(0, deck.size() / 2);
    this.blueHand = deck.subList(deck.size() / 2, deck.size());
    this.grid = grid;
    this.turn = Player.RED;
  }

  public ThreeTriosModel(List<Card> deck, Grid grid, Random rand) {
    if (deck == null || grid == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    if (deck.size() % 2 != 0) {
      throw new IllegalArgumentException("Deck size must be even.");
    }
    if (!(deck.size() >= grid.getNumCardCells() + 1)) {
      throw new IllegalArgumentException("Deck size must be at least equal to the "
              + "number of card cells in the grid plus one.");
    }

    Collections.shuffle(deck, rand);
    this.redHand = deck.subList(0, deck.size() / 2);
    this.blueHand = deck.subList(deck.size() / 2, deck.size());
    this.grid = grid;
    this.turn = Player.RED;
  }

  public ThreeTriosModel(List<Card> redHand, List<Card> blueHand, Grid grid) {
    if (redHand == null || blueHand == null || grid == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    if (redHand.size() != blueHand.size()) {
      throw new IllegalArgumentException("Both hands must be the same size.");
    }
    if (!(redHand.size() * 2 >= grid.getNumCardCells() + 1)) {
      throw new IllegalArgumentException("Deck size must be at least equal to the "
              + "number of card cells in the grid plus one.");
    }

    this.redHand = redHand;
    this.blueHand = blueHand;
    this.grid = grid;
    this.turn = Player.RED;
  }

  public void placeCard(GridPos pos, int cardIdx) {
    Card cardToPlay;
    try {
      switch (turn) {
        case RED:
          cardToPlay = redHand.remove(cardIdx);
          break;
        case BLUE:
          cardToPlay = blueHand.remove(cardIdx);
          break;
        default:
          throw new IllegalStateException(turn + "is not a value of Player that can be handled!");
      }
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid card index!");
    }
    this.grid.playCard(pos, cardToPlay, turn);
    this.turn = this.battleStep(pos);
  }

  public Player battleStep(GridPos pos) {
    List<GridPos> neighborsList;

    //gets a list of neighbors that need to be flipped
    neighborsList = new ArrayList<>(this.grid.getLosingNeighbors(pos));

    //flips all of those neighbors
    for (GridPos neighbor : neighborsList) {
      grid.flipCardCellTo(neighbor, turn);
    }

    //recursively calls battleStep on each neighbor
    for (GridPos neighbor : neighborsList) {
      this.battleStep(neighbor);
    }

    //returns the next player. Only the return value of the first call to battleStep is used;
    return turn.nextPlayer();
  }

  public Player getWinner() {
    if (!(this.grid.isSaturated() || (this.redHand.isEmpty() && this.blueHand.isEmpty()))) {
      throw new IllegalStateException("Game is still ongoing!");
    }

    if (this.grid.getScoreOf(Player.RED) > this.grid.getScoreOf(Player.BLUE)) {
      return Player.RED;
    } else if (this.grid.getScoreOf(Player.RED) < this.grid.getScoreOf(Player.BLUE)) {
      return Player.BLUE;
    } else { // if tie
      return null;
    }
  }

  //Test to make sure that a call to getTurn() does to return a MUTABLE reference to turn
  public Player getTurn() {
    return this.turn;
  }

  @Override
  public Cell[][] getCurrentGrid() {
    return this.grid.getCurrentGrid();
  }

  public List<Card> getHand(Player player) {
    List<Card> targetHand;
    switch (player) {
      case RED:
        targetHand = new ArrayList<>(this.redHand);
        break;
      case BLUE:
        targetHand = new ArrayList<>(this.blueHand);
        break;
      default:
        throw new IllegalStateException(player + "is an unsupported value of player!");
    }
    return targetHand;
  }


}











