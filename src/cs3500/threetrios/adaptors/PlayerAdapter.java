package cs3500.threetrios.adaptors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cs3500.threetrios.code.model.Card;
import cs3500.threetrios.code.model.Player;
import cs3500.threetrios.code.model.ReadOnlyThreeTriosModel;
import cs3500.threetrios.provider.code.controller.IController;
import cs3500.threetrios.provider.code.model.ICard;
import cs3500.threetrios.provider.code.model.IThreeTriosGame;
import cs3500.threetrios.provider.code.model.PlayerColor;
import cs3500.threetrios.provider.code.players.IPlayer;

/**
 * This is the player adapter class that uses the provider player to work with
 * our model.
 */
public class PlayerAdapter implements IPlayer {
  private final Player ourColor;
  private final ReadOnlyThreeTriosModel ourModel;

  /**
   * The constructor for the player adapter.
   *
   * @param ourColor the color the local player is
   * @param ourModel the local model
   */
  public PlayerAdapter(Player ourColor, ReadOnlyThreeTriosModel ourModel) {
    if (ourColor == null) {
      throw new IllegalArgumentException("Player can't be null!");
    }
    this.ourColor = ourColor;
    this.ourModel = ourModel;
  }

  /**
   * This method works to get the ICards in the hand of an IPlayer in the game Three Trios.
   *
   * @return a list of ICards that makes up the hand of an IPlayer.
   */
  @Override
  public List<ICard> getHand() {
    List<Card> ourHand = ourModel.getHand(ourColor);
    List<ICard> ret = new ArrayList<>();
    for (Card card : ourHand) {
      ret.add(new CardAdapter(card));
    }
    return ret;
  }

  /**
   * This method works to get the IPlayer's PlayerColor, being either RED or BLUE in the game
   * Three Trios.
   *
   * @return the PlayerColor of the IPlayer.
   */
  @Override
  public PlayerColor getPlayerColor() {
    switch (ourColor) {
      case BLUE:
        return PlayerColor.BLUE;
      case RED:
        return PlayerColor.RED;
      default:
        throw new IllegalArgumentException("Unsupported Player Color.");
    }
  }

  /**
   * This method works to get the ICards that make up the hand of an IPlayer as well as any cards
   * in the grid that have the same color as this IPlayer's PlayerColor, which changes through
   * battling and flipping of the cards during the game of Three Trios.
   *
   * @return the list of ICards that make up the IPlayer's hand and any ICard on the grid
   */
  @Override
  public List<ICard> getAllPlayerCards() {
    return List.of();
  }

  /**
   * This method works to set the hand of an IPlayer while dealing the cards. This can only
   * happen once, as we only deal the cards to the IPlayers once. This method sets a field in the
   * Player class, and thus does not return anything / returns void.
   *
   * @param hand represents the list of ICards that we want to set the IPlayer's hand to be.
   */
  @Override
  public void setHand(List<ICard> hand) {
    // na
  }

  /**
   * This method works to remove an ICard from an IPlayer's hand, for example after an IPlayer
   * plays a card to the board, this card must be removed from said IPlayer's hand.
   *
   * @param cardIdx represents the index of the ICard in the list of ICards (the IPlayer's hand)
   *                that we want to remove.
   * @return the ICard at the cardIdx of the player's hand (list of ICards) that we removed.
   */
  @Override
  public ICard removeCardFromHand(int cardIdx) {
    return null;
  }

  /**
   * This method works to add an ICard to an IPlayer's all cards list of ICards, for example, after
   * an IPlayer's card battles and wins, the other losing card should be added to this IPlayer's
   * list of all cards, representing the flipping of that losing card.
   *
   * @param card represents the ICard that we want to add to the list of ICards in the game
   *             Three Trios.
   */
  @Override
  public void addToAllCards(ICard card) {
    // na
  }

  /**
   * This method works to remove an ICard to an IPlayer's all cards list of ICards, for example,
   * after an IPlayer's card battles and loses, the losing card should be removed from this
   * IPlayer's list of all cards, representing the flipping of that losing card.
   *
   * @param otherCard represents the ICard that we want to remove from the list of ICards in the
   *                  game Three Trios.
   */
  @Override
  public void removeCardFromAllPlayerCards(ICard otherCard) {
    // na
  }

  /**
   * This method is used by the player to determine their move in the game.
   * It is optional because for the human player, the return will be empty. For the AI Player,
   * the return will be the best based on their strategy. In the case of a tie, it will give
   * the move with uppermost-leftmost location and the best card for that position with an index
   * closest to 0 in the hand. In the case there is no valid move, the move is the upper-most,
   * left-most open position and the card at index 0.
   *
   * @param model represents the game's grid.
   * @return an optional best or valid move.
   */
  @Override
  public Optional<IMove> getMove(IThreeTriosGame model) {
    return Optional.empty();
  }

  @Override
  public IPlayer copy() {
    return null;
  }

  @Override
  public void addControllerListener(IController controller) {
    // There is no way for us to access the appropriate listeners to achieve this functionality.
    // The providers code handles Observers and listeners in a very different way to our code,
    // obscuring them from the user's view.
  }

  @Override
  public void notifyPlayersChoseAMove(int cardIdx, int row, int col) {
    // There is no way for us to access the appropriate listeners to achieve this functionality.
    // The providers code handles Observers and listeners in a very different way to our code,
    // obscuring them from the user's view.
  }

  @Override
  public void notifyPlayersChoseACard(int cardIdx) {
    // There is no way for us to access the appropriate listeners to achieve this functionality.
    // The providers code handles Observers and listeners in a very different way to our code,
    // obscuring them from the user's view.
  }

  @Override
  public void notifyPlayersChoseAPosn(int selectedRow, int selectedCol) {
    // There is no way for us to access the appropriate listeners to achieve this functionality.
    // The providers code handles Observers and listeners in a very different way to our code,
    // obscuring them from the user's view.
  }

  @Override
  public void notifyPlayerDeselectedCard() {
    // There is no way for us to access the appropriate listeners to achieve this functionality.
    // The providers code handles Observers and listeners in a very different way to our code,
    // obscuring them from the user's view.
  }

  @Override
  public void youTurn(IThreeTriosGame model) {
    // There is no way for us to access the appropriate listeners to achieve this functionality.
    // The providers code handles Observers and listeners in a very different way to our code,
    // obscuring them from the user's view.
  }
}
