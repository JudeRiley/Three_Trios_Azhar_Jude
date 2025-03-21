package cs3500.threetrios;

import cs3500.threetrios.adaptors.ModelAdapter;
import cs3500.threetrios.adaptors.ViewAdapter;
import cs3500.threetrios.code.controller.Controller;
import cs3500.threetrios.code.controller.ThreeTriosController;
import cs3500.threetrios.code.model.Grid;
import cs3500.threetrios.code.model.Card;
import cs3500.threetrios.code.model.HumanPlayer;
import cs3500.threetrios.code.model.MachinePlayer;
import cs3500.threetrios.code.model.Player;
import cs3500.threetrios.code.model.PlayerType;
import cs3500.threetrios.code.model.ThreeTriosModel;

import cs3500.threetrios.code.model.config.CardConfigReader;
import cs3500.threetrios.code.model.config.GridConfigReader;
import cs3500.threetrios.code.strategies.FlipMostCardsTactic;
import cs3500.threetrios.code.strategies.GoForCornersTactic;
import cs3500.threetrios.code.strategies.TacticOrFirstOpenSpaceStrategy;
import cs3500.threetrios.code.view.ThreeTriosView;
import cs3500.threetrios.code.view.ThreeTriosViewImpl;
import cs3500.threetrios.provider.code.guiview.ThreeTriosFrame;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * This is the driver class for the program.
 */
public final class ThreeTriosMain {
  /**
   * This is the main class that runs the program.
   *
   * @param args command line arguments
   * @throws IOException when input invalid
   */
  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      System.out.println("Usage: java GameLauncher <player1> <player2>");
      System.out.println("Where <playerX> is 'human', 'strategy1', or 'strategy2'");
      return;
    }

    String player1Type = args[0];
    String player2Type = args[1];

    String gridConfigPath = Paths.get("test", "cs3500",
            "testingConfigs", "board_connected_holes.txt").toString();
    String cardConfigPath = Paths.get("test", "cs3500",
            "testingConfigs", "cards_large.txt").toString();

    // Read configurations
    GridConfigReader gridConfigReader = new GridConfigReader(gridConfigPath);
    Grid grid = gridConfigReader.readGrid();

    CardConfigReader cardConfigReader = new CardConfigReader(cardConfigPath);
    List<Card> deck = cardConfigReader.readCards();

    // Create the model
    ThreeTriosModel model = new ThreeTriosModel(deck, grid);
    System.out.println(model.getTurn());

    // Create views for each player
    ThreeTriosView view1 = new ThreeTriosViewImpl(model);

    ThreeTriosFrame provider = new ThreeTriosFrame(new ModelAdapter(model));
    ThreeTriosView view2 = new ViewAdapter(provider);

    // Create players based on command-line arguments
    PlayerType player1 = createPlayer(player1Type, Player.RED);
    PlayerType player2 = createPlayer(player2Type, Player.BLUE);

    // Create controllers
    Controller controller1 = new ThreeTriosController(model, view1, player1);
    Controller controller2 = new ThreeTriosController(model, view2, player2);

    // If players are MachinePlayers, set their controllers and models
    if (player1 instanceof MachinePlayer) {
      ((MachinePlayer) player1).setController(controller1);
      ((MachinePlayer) player1).setModel(model);
    }
    if (player2 instanceof MachinePlayer) {
      ((MachinePlayer) player2).setController(controller2);
      ((MachinePlayer) player2).setModel(model);
    }

    model.startGame();
  }

  // Helper method for creating a player
  private static PlayerType createPlayer(String playerType, Player playerColor) {
    switch (playerType.toLowerCase()) {
      case "human":
        return new HumanPlayer(playerColor);
      case "strategy1":
        return new MachinePlayer(playerColor, new TacticOrFirstOpenSpaceStrategy(
                new FlipMostCardsTactic()));
      case "strategy2":
        return new MachinePlayer(playerColor, new TacticOrFirstOpenSpaceStrategy(
                new GoForCornersTactic()));
      default:
        System.out.println("Unknown player type: " + playerType);
        System.out.println("Valid types are 'human', 'strategy1', 'strategy2'");
        System.exit(1);
        return null;
    }
  }


}
