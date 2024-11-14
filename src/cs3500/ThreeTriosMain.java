package cs3500;

import cs3500.model.Grid;
import cs3500.model.Card;
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
    ThreeTriosView view = new ThreeTriosViewImpl(model);

    // Testing stuff to see the view
    model.placeCard(new GridPos2d(2, 0), 0);
    model.placeCard(new GridPos2d(0, 0), 0);

    model.placeCard(new GridPos2d(2, 1), 9);
    model.placeCard(new GridPos2d(0, 1), 0);

    model.placeCard(new GridPos2d(2, 3), 9);
    model.placeCard(new GridPos2d(0, 2), 0);

    model.placeCard(new GridPos2d(2, 4), 0);

    model.placeCard(new GridPos2d(2, 2), 0);
    view.makeVisible();
  }
}
