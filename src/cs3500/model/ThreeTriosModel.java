package cs3500.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Represents a playable ThreeTrios game for 2 players.
 */
public class ThreeTriosModel implements ThreeTrios {

  private final Grid grid;
  private Player turn;
  private final List<Card> redHand;
  private final List<Card> blueHand;

  /**
   * Constructs and starts a three trios game with a given deck and grid layout.
   * it does not shuffle the cards but splits them evenly down the middle.
   *
   * @param deck a deck of cards to be split between the players evenly.
   * @param grid a pre-determined grid layout.
   */
  public ThreeTriosModel(List<Card> deck, Grid grid) {
    if (deck == null || grid == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    if (deck.size() < grid.getNumCardCells() + 1) {
      throw new IllegalArgumentException("Deck size must be at least equal to the "
              + "number of card cells in the grid plus one.");
    }
    if (deck.size() % 2 != 0) {
      throw new IllegalArgumentException("Deck size must be even.");
    }

    this.redHand = new ArrayList<>(deck.subList(0, deck.size() / 2));
    this.blueHand = new ArrayList<>(deck.subList(deck.size() / 2, deck.size()));
    this.grid = grid;
    this.turn = Player.RED;
  }

  /**
   * Constructs and starts a three trios game with a given deck and grid layout.
   * it uses a given random seed to shuffle the deck.
   *
   * @param deck a deck of cards to be split between the players evenly.
   * @param grid a pre-determined grid layout.
   * @param rand a random seed.
   */
  public ThreeTriosModel(List<Card> deck, Grid grid, Random rand) {
    if (deck == null || grid == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    if (deck.size() % 2 != 0) {
      throw new IllegalArgumentException("Deck size must be even.");
    }
    if (deck.size() < grid.getNumCardCells() + 1) {
      throw new IllegalArgumentException("Deck size must be at least equal to the "
              + "number of card cells in the grid plus one.");
    }

    Collections.shuffle(deck, rand);
    this.redHand = new ArrayList<>(deck.subList(0, deck.size() / 2));
    this.blueHand = new ArrayList<>(deck.subList(deck.size() / 2, deck.size()));
    this.grid = grid;
    this.turn = Player.RED;
  }

  /**
   * Constructs and starts a three trios game with each players hands pre-determined.
   *
   * @param redHand  a pre-determined hand for the red player 1.
   * @param blueHand a pre-determined hand for the blue player 2,
   * @param grid     a pre-determined grid layout.
   */
  public ThreeTriosModel(List<Card> redHand, List<Card> blueHand, Grid grid) {
    if (redHand == null || blueHand == null || grid == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    if (redHand.size() != blueHand.size()) {
      throw new IllegalArgumentException("Both hands must be the same size.");
    }
    if (redHand.size() * 2 < grid.getNumCardCells() + 1) {
      throw new IllegalArgumentException("Deck size must be at least equal to the "
              + "number of card cells in the grid plus one.");
    }

    this.redHand = redHand;
    this.blueHand = blueHand;
    this.grid = grid;
    this.turn = Player.RED;
  }

  @Override
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
          throw new IllegalStateException(this.turn
                  + "is not a value of Player that can be handled!");
      }
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid card index!");
    }
    this.grid.playCard(pos, cardToPlay, this.turn);
    this.battleStep(pos);
    this.turn = this.turn.nextPlayer();
  }

  private void battleStep(GridPos pos) {
    // gets a list of losing neighbor positions, without considering owner
    List<GridPos> losingNeighbors = this.grid.getLosingNeighbors(pos);

    //flips the neighbors
    for (GridPos neighbor : losingNeighbors) {
      this.grid.flipCardCellTo(neighbor, this.turn);
    }
    // recursively calls battleStep on each neighbor
    for (GridPos neighbor : losingNeighbors) {
      this.battleStep(neighbor);
    }
  }

  @Override
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

  @Override
  public Player getTurn() {
    return this.turn;
  }

  @Override
  public Cell[][] getCurrentGrid() {
    return this.grid.getCurrentGrid();
  }

  @Override
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











