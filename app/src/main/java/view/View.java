package view;

import controller.LendingSystem;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import model.Contract;
import model.Item;
import model.Member;



/**
 * Represents the view for the Stuff Lending System.
 */
public class View {
  private final Scanner scanner = new Scanner(System.in);

  /**
   * Displays the main menu options.
   */
  public void displayMenu() {
    System.out.println("====================================");
    System.out.println("        Stuff Lending System        ");
    System.out.println("====================================");
    System.out.println("1. Add Member");
    System.out.println("2. Add Item");
    System.out.println("3. Create Lending Contract");
    System.out.println("4. View Member Info");
    System.out.println("5. View All Members");
    System.out.println("6. View All Items");
    System.out.println("7. View All Contracts");
    System.out.println("8. Advance Time");
    System.out.println("9. Exit");
  }

  /**
   * Reads user input from the console and ensures it is an integer.
   *
   * @return the selected option as an integer
   */
  public int readUserInput() {
    while (true) {
      try {
        System.out.print("Choose an option: ");
        return Integer.parseInt(scanner.nextLine().trim());
      } catch (NumberFormatException e) {
        displayMessage("Invalid input! Please enter a number.");
      }
    }
  }

  /**
   * Displays details of a specific member.
   *
   * @param member The member whose details are to be displayed
   */
  public void displayMemberDetails(Member member) {
    displayMessage("_______________________");
    displayMessage("=== Member Details ===");
    displayMessage(member.getMemberDetails());
  }

  /**
   * Displays details of a specific contract.
   *
   * @param contract The contract whose details are to be displayed
   */
  public void displayContractDetails(Contract contract) {
    displayMessage("_______________________");
    displayMessage("=== Contract Details ===");
    displayMessage(contract.getContractInfo());
  }

  /**
   * Displays details of a specific item.
   *
   * @param item The item whose details are to be displayed
   */
  public void displayItemDetails(Item item) {
    displayMessage("_______________________");
    displayMessage("=== Item Details ===");
    displayMessage(item.getItemInfo());
  }

  /**
   * Displays a message to the console.
   *
   * @param message The message to be displayed
   */
  public void displayMessage(String message) {
    System.out.println(message);
  }

  /**
   * Displays a list of all members.
   *
   * @param lendingSystem displayed the All Member details.
   */
  public void displayAllMembers(LendingSystem lendingSystem) {
    displayMessage("============ All Members ============");
    if (lendingSystem.getMembers().isEmpty()) {
      displayMessage("No members available.");
    } else {
      for (Member member : lendingSystem.getMembers()) {
        displayMemberDetails(member);
      }
    }
  }

  /**
   * Displays a list of all items.
   *
   * @param lendingSystem displayed the AllItems
   */
  public void displayAllItems(LendingSystem lendingSystem) {
    displayMessage("============ All Items ============");
    if (lendingSystem.getItems().isEmpty()) {
      displayMessage("No items available.");
    } else {
      for (Item item : lendingSystem.getItems()) {
        displayItemDetails(item);
      }
    }
  }

  /**
   * Displays a list of all contracts.
   *
   * @param lendingSystem helped to display the all the available contracts
   */
  public void displayAllContracts(LendingSystem lendingSystem) {
    displayMessage("============ All Contracts ============");
    if (lendingSystem.getContracts().isEmpty()) {
      displayMessage("No contracts available.");
    } else {
      for (Contract contract : lendingSystem.getContracts()) {
        displayContractDetails(contract);
      }
    }
  }

  /**
   * Adds a new member to the lending system.
   *
   * @param lendingSystem the lending system
   */
  public void addMember(LendingSystem lendingSystem) {
    displayMessage("Enter member name:");
    String name = scanner.nextLine();
    displayMessage("Enter member email:");
    String email = scanner.nextLine();
    displayMessage("Enter member phone number:");
    String phoneNumber = scanner.nextLine();

    lendingSystem.addMember(name, email, phoneNumber);
    displayMessage("Member added successfully!");
  }

  /**
   * Adds a new item to the lending system.
   *
   * @param lendingSystem the lending system
   */
  public void addItem(LendingSystem lendingSystem) {
    displayMessage("Enter member name for the item:");
    final String nameOfMember = scanner.nextLine();
    displayMessage("Enter item name:");
    final String namOfItem = scanner.nextLine();
    displayMessage("Enter item description:");
    final String description = scanner.nextLine();
    displayMessage("Enter item category:");
    final String category = scanner.nextLine();
    displayMessage("Enter cost per day:");

    int costPerDay;
    try {
      costPerDay = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      displayMessage("Invalid cost per day. Please enter a valid number.");
      return;
    }

    lendingSystem.addItem(nameOfMember, namOfItem, description, category, costPerDay);
    displayMessage("Item added successfully!");
  }

  /**
   * Creates a lending contract for an item.
   *
   * @param lendingSystem the lending system
   */
  public void createLendingContract(LendingSystem lendingSystem) {
    try {
      displayMessage("Enter borrower name:");
      final String borrowerName = scanner.nextLine();

      displayMessage("Enter item name:");
      String nameOfItem = scanner.nextLine();

      // Handling potential errors in date parsing
      LocalDate startDate;
      LocalDate endDate;
      try {
        displayMessage("Enter contract start date (yyyy-mm-dd):");
        startDate = LocalDate.parse(scanner.nextLine().trim());

        displayMessage("Enter contract end date (yyyy-mm-dd):");
        endDate = LocalDate.parse(scanner.nextLine().trim());
      } catch (DateTimeParseException e) {
        displayMessage("Invalid date format. Please use 'yyyy-mm-dd'.");
        return;  // Exit if date input is invalid
      }

      // Attempt to create the lending contract
      lendingSystem.createLendingContract(borrowerName, nameOfItem, startDate, endDate);


    } catch (IllegalArgumentException e) {
      // Catch any IllegalArgumentException from LendingSystem for invalid contract creation
      displayMessage("Error creating contract: " + e.getMessage());

    } catch (Exception e) {
      // Catch any other unexpected errors
      displayMessage("An unexpected error occurred: " + e.getMessage());
    }
  }

  /**
   * Views information for a specific member.
   *
   * @param lendingSystem the lending system
   */
  public void viewMemberInfo(LendingSystem lendingSystem) {
    displayMessage("Enter member name to view info:");
    String memberFinding = scanner.nextLine();
    lendingSystem.viewMemberInfo(memberFinding);
  }

  /**
   * Advances the time in the lending system.
   *
   * @param lendingSystem the lending system
   */
  public void advanceTime(LendingSystem lendingSystem) {
    lendingSystem.advanceTime();
    displayMessage("Time advanced to day " + lendingSystem.getCurrentDay());
  }

  /**
   * Exits the lending system.
   *
   * @return false to stop the application
   */
  public boolean exitSystem() {
    displayMessage("Exiting the system...");
    return false;
  }
}
