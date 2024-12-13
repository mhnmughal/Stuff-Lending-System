package controller;

import view.View;

/**
 * The main application class for the Lending System.
 */
public class App {

  /**
   * The entry point of the application.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    LendingSystem lendingSystem = new LendingSystem();
    View view = new View();
    boolean running = true;

    while (running) {
      view.displayMenu();
      int choice = view.readUserInput();

      switch (choice) {
        case 1:
          view.addMember(lendingSystem);
          break;
        case 2:
          view.addItem(lendingSystem);
          break;
        case 3:
          view.createLendingContract(lendingSystem);
          break;
        case 4:
          view.viewMemberInfo(lendingSystem);
          break;
        case 5:
          view.displayAllMembers(lendingSystem);
          break;
        case 6:
          view.displayAllItems(lendingSystem);
          break;
        case 7:
          view.displayAllContracts(lendingSystem);
          break;
        case 8:
          view.advanceTime(lendingSystem);
          break;
        case 9:
          running = view.exitSystem();
          break;
        default:
          view.displayMessage("Invalid option! Please try again.");
      }
    }
  }
}