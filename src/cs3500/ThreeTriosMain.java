package cs3500;

import cs3500.controller.ThreeTriosController;
import cs3500.model.Grid;
import cs3500.model.Card;
import cs3500.model.HumanPlayer;
import cs3500.model.Player;
import cs3500.model.PlayerType;
import cs3500.model.ThreeTriosModel;
import cs3500.model.GridPos2d;

import cs3500.model.config.CardConfigReader;
import cs3500.model.config.GridConfigReader;
import cs3500.view.ThreeTriosView;
import cs3500.view.ThreeTriosViewImpl;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * This is the driver class for the program.
 */
public final class ThreeTriosMain {
  /**
   * This is the main class that runs the program.
   * @param args command line arguments
   * @throws IOException when input invalid
   */
  public static void main(String[] args) throws IOException {
    String gridConfigPath = Paths.get("test", "cs3500",
            "testingConfigs", "board_connected_holes.txt").toString();
    String cardConfigPath = Paths.get("test", "cs3500",
            "testingConfigs", "cards_weak_red.txt").toString();

    GridConfigReader gridConfigReader = new GridConfigReader(gridConfigPath);
    Grid grid = gridConfigReader.readGrid();

    CardConfigReader cardConfigReader = new CardConfigReader(cardConfigPath);
    List<Card> deck = cardConfigReader.readCards();

    ThreeTriosModel model = new ThreeTriosModel(deck, grid);
    ThreeTriosViewImpl view1 = new ThreeTriosViewImpl(model);
    ThreeTriosViewImpl view2 = new ThreeTriosViewImpl(model);

    PlayerType player1 = new HumanPlayer(Player.RED);
    PlayerType player2 = new HumanPlayer(Player.BLUE);

    ThreeTriosController controller1 = new ThreeTriosController(model, view1, player1);
    ThreeTriosController controller2 = new ThreeTriosController(model, view2, player2);
    model.startGame();
  }
}
