package cs3500.model.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.model.Card;
import cs3500.model.ThreeTriosCard;

/**
 * Reads a card configuration file and constructs a list of Card objects.
 */
public class CardConfigReader {

  private final String filePath;

  /**
   * Constructs a CardConfigReader with the specified file path.
   *
   * @param filePath the path to the card database file
   */
  public CardConfigReader(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Reads the card configuration and creates a list of Card objects.
   *
   * @return a list of Card objects
   * @throws IOException if an I/O error occurs while reading the file
   */
  public List<Card> readCards() throws IOException {
    List<Card> cards = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      int lineNumber = 1;
      while ((line = reader.readLine()) != null) {
        line = line.trim();

        // Skip empty lines
        if (line.isEmpty()) {
          lineNumber++;
          continue;
        }
        String[] tokens = line.split("\\s+");
        if (tokens.length != 5) {
          throw new IllegalArgumentException("Invalid format at line " + lineNumber
                  + ": expected 5 tokens, got " + tokens.length);
        }
        String name = tokens[0];
        try {
          int north = parseAttackValue(tokens[1]);
          int south = parseAttackValue(tokens[2]);
          int east = parseAttackValue(tokens[3]);
          int west = parseAttackValue(tokens[4]);

          Card card = new ThreeTriosCard(name, north, south, east, west);
          cards.add(card);
        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Invalid attack value at line " + lineNumber, e);
        }
        lineNumber++;
      }
    }
    return cards;
  }

  /**
   * Parses the attack value string and returns its integer representation.
   *
   * @param value the attack value string
   * @return the integer attack value
   * @throws NumberFormatException if the value is invalid
   */
  private int parseAttackValue(String value) throws NumberFormatException {
    if ("A".equalsIgnoreCase(value)) {
      return 10;
    } else {
      int val = Integer.parseInt(value);
      if (val < 1 || val > 9) {
        throw new NumberFormatException("Attack value out of range: " + value);
      }
      return val;
    }
  }
}

