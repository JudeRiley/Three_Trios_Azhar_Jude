# ThreeTrios Game

## Overview

**ThreeTrios** is a strategic card placement game implemented in Java. The game involves two players, **Red** and **Blue**, who take turns placing cards on a grid. Each card has attack values in four directions—north, south, east, and west. When a card is placed adjacent to an opponent's card, a battle ensues based on the attack values of the cards. The objective is to control more cards on the grid than your opponent by the time the grid is filled.

### High-Level Assumptions

- **Players**: The game is designed for two players.
- **Grid Configuration**: The grid can have holes (non-playable cells), affecting the strategy.
- **Deck Size**: The deck size must be even and sufficient to fill the grid.
- **Battle Mechanics**: Battles are resolved based on the attack values of the cards in the relevant directions.

### Invariants Used

1. **Grid Cell Occupancy**: A cell cannot be both a hole and contain a card. This ensures that hole cells remain unplayable throughout the game.
2. **Deck Size Consistency**: The total number of card cells on the grid must be odd, and the deck size must be at least equal to the number of card cells plus one. This guarantees that the game can be completed without running out of cards.

## Quick Start

To run the game go into terminal and type
"java -jar hw7.jar [player1] [player2]", examples below.

Example: java ThreeTriosMain 'player1' 'player2', in place of player1 and player2, you can do any
combination of "human" "strategy1" "strategy2".  Strategy1 refers to flip most cards strategy and
strategy2 refers to the go for corners strategy.

Here's a snippet of the main class:
```java
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

    // Create views for each player
    ThreeTriosViewImpl view1 = new ThreeTriosViewImpl(model);
    ThreeTriosViewImpl view2 = new ThreeTriosViewImpl(model);

    // Create players based on command-line arguments
    PlayerType player1 = createPlayer(player1Type, Player.RED);
    PlayerType player2 = createPlayer(player2Type, Player.BLUE);

    // Create controllers
    ThreeTriosController controller1 = new ThreeTriosController(model, view1, player1);
    ThreeTriosController controller2 = new ThreeTriosController(model, view2, player2);

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
```
## How to Navigate the Codebase

- **Model Logic**: Start with ThreeTriosModel.java to understand the game's core logic.
- **Grid and Cells**: Explore ThreeTriosGrid.java and ThreeTriosCell.java for grid management and cell interactions.
- **Cards and Players**: Look into ThreeTriosCard.java and Player.java for card definitions and player handling.
- **Configurations**: Use GridConfigReader.java and CardConfigReader.java to understand how grids and decks are initialized.
- **Testing**: Refer to ThreeTriosInterfaceTest.java and ThreeTriosModelTest.java for examples of how the classes are used and tested.

## Key Components

The **Model** component encapsulates the game's logic and state. It includes the following key classes:

- **ThreeTrios Interface**: Defines the public methods available to interact with the game model.
- **ThreeTriosModel**: Implements the ThreeTrios interface and contains the core game logic, including card placement and battle resolution.
- **Grid Interface**: Represents the game grid and provides methods to interact with cells.
- **ThreeTriosGrid**: Implements the Grid interface, managing the grid's state and cell interactions.
- **Cell Interface**: Represents a cell on the grid, which can be a card cell or a hole.
- **ThreeTriosCell**: Implements the Cell interface, handling card placement and ownership.
- **Card Interface**: Represents a game card with attack values in four directions.
- **ThreeTriosCard**: Implements the Card interface, storing attack values and providing comparison methods.
- **Player Enum**: Represents the two players, RED and BLUE, and includes utility methods for player turns.


The **Configuration** component handles reading grid and card configurations from files:

- **GridConfigReader**: Reads grid configurations from text files to create Grid instances.
- **CardConfigReader**: Reads card data from text files to create a list of Card instances.

## Key Subcomponents

**Battle Mechanics**
- battleStep Method: Handles the battle logic when a card is placed adjacent to opponent cards. It determines which neighboring cards are flipped based on attack values.

**Direction Handling**
- Direction Enum: Represents the four cardinal directions (NORTH, SOUTH, EAST, WEST) used for card attack comparisons and grid navigation.

**Positioning**
- GridPos Class: Represents positions on the grid and provides methods to navigate to adjacent positions.

## Changes made from Assignment 5
- Refactored GridConfigReader so that method doesn't exceed 50 lines
- Fixed the implementation of ThreeTriosModel.battleStep and Grid.getLosingNeighbors so that card flipping chains properly.
- Changed GridPos to be defined by rows and columns rather than x and y positions.
- Fixed GridPos.getAdjacent.
- Added GridPos.isInBoundsFor and GridPos.accessArray Methods to simplify uses of grid positions in other classes.
- Added Move interface.
- Inverted Cell.isFilled and renamed the inverted method to Card.isOpenForPlay
- Added GetName to Card Interface
- Added Copy Constructors to Cell and Card implementations
- Added FromString static method to Player enum

## Changes for part 3
- Created a MachinePlayer that implements PlayerType, it's purpose is to represent an AI player
- Created a HumanPlayer that implements PlayerType, it represents a human player
- Created a ThreeTriosController that controls the game and listens to the view and model
- View now implements view features to follow the observer pattern
- Model now implements model features to follow the observer pattern

## Extra Credit
- Added the ability to chain strategies together with the ChainableStrategy class.