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

Here's a simple example to get you started with the **ThreeTrios** game:

```java
public class ThreeTriosGameDemo {
    public static void main(String[] args) throws IOException {
        // Initialize the grid from a configuration file
        GridConfigReader gridReader = new GridConfigReader("path/to/your/grid_config.txt");
        Grid grid = gridReader.readGrid();

        // Initialize the deck from a configuration file
        CardConfigReader cardReader = new CardConfigReader("path/to/your/card_config.txt");
        List<Card> deck = cardReader.readCards();

        // Create the game model
        ThreeTrios game = new ThreeTriosModel(deck, grid);

        // Place a card on the grid
        game.placeCard(new GridPos(0, 0), 0); // Player RED places a card at position (0,0)

        // Get the current turn
        Player currentPlayer = game.getTurn();

        // Display the current grid state
        Cell[][] currentGrid = game.getCurrentGrid();

        // Continue the game...
    }
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
