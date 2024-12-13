package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Contract;
import model.Item;
import model.Member;
import model.Time;

/**
 * Represents the Controller(LendingSystem) for the Stuff Lending System.
 */

public class LendingSystem {

  /**
   * 'Messages' for different action performed by the methods.
   */
  private static final String MEMBER_NOT_FOUND_MESSAGE = "Member not found!";
  private static final String ITEM_ADDED_SUCCESS_MESSAGE = "Item added successfully for ";
  private static final String CONTRACT_CREATED_SUCCESS_MESSAGE = "Contract created successfully!";
  private static final String INSUFFICIENT_CREDITS_MESSAGE = "Not enough credits for the borrower.";
  private static final String ITEM_NOT_AVAILABLE_MESSAGE =
          "Borrower or Item not found, or item is not available for the selected dates.";

  private final List<Member> members;
  private final List<Item> items;
  private final List<Contract> contracts;
  private final Time time;

  /**
   * Constructor of LendingSystem(Controller) class for members, Item, Contracts and Time.
   */
  public LendingSystem() {
    this.members = new ArrayList<>();
    this.items = new ArrayList<>();
    this.contracts = new ArrayList<>();
    this.time = new Time();
    initializeData();
  }

  public List<Member> getMembers() {
    return members;
  }

  public List<Item> getItems() {
    return items;
  }

  public List<Contract> getContracts() {
    return contracts;
  }

  public int getCurrentDay() {
    return time.getCurrentDay();
  }

  /**
  * Adds a new member to the lending system.
  */
  public void addMember(String name, String email, String phoneNumber) {
    Member member = new Member(name, email, phoneNumber);
    members.add(member);
  }

  // New Method to Initialize Data
  private void initializeData() {
    // Adding sample members for demonstration
    Member johnDoe = new Member("John Doe", "john@example.com", "123456789");
    Member janeSmith = new Member("Jane Smith", "jane.smith@example.com", "987654321");
    Member aliceBrown = new Member("Alice Brown", "alice.brown@example.com", "555666777");

    // Adding members to the system
    members.add(johnDoe);
    members.add(janeSmith);
    members.add(aliceBrown);

    // Adding sample items for each member
    addItem(johnDoe.getName(), "Laptop", "Gaming Laptop", "Electronic", 50);
    addItem(johnDoe.getName(), "Camera", "DSLR Camera", "Photography", 30);

    addItem(janeSmith.getName(), "Bicycle", "Mountain Bike", "Sports", 20);
    addItem(janeSmith.getName(), "Tent", "Camping Tent", "Outdoor", 25);

    addItem(aliceBrown.getName(), "Guitar", "Electric Guitar", "Musical Instrument", 40);
    addItem(aliceBrown.getName(), "Projector", "HD Projector", "Electronic", 35);

    // Creating a sample lending contract
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = startDate.plusDays(5);
    String contractMessage = createLendingContract(janeSmith.getName(), "Laptop", startDate, endDate);
    System.out.println(contractMessage);
  }

  /**
  * Adds a new item to a specific member.
  */
  public String addItem(String nameOfMember, String itemName, String description,
      String category, int costPerDay) {
    Member member = findMember(nameOfMember);
    if (member != null) {
      Item item = new Item(itemName, description, category, member, costPerDay);
      member.addItem(item);
      items.add(item);
      return ITEM_ADDED_SUCCESS_MESSAGE + nameOfMember;
    } else {
      return MEMBER_NOT_FOUND_MESSAGE;
    }
  }

  /**
  * Creates a lending contract if conditions are met.
  */
  public String createLendingContract(String borrowerName, String nameOfItem,
      LocalDate startDate, LocalDate endDate) {
    Member borrower = findMember(borrowerName);
    Item item = findItem(nameOfItem);
    if (isValidContract(borrower, item, startDate, endDate)) {
      Contract contract = new Contract(borrower, item, startDate, endDate);
      if (contract.validateContract()) {
        contracts.add(contract);
        item.addContract(contract);
        return CONTRACT_CREATED_SUCCESS_MESSAGE;
      } else {
        return INSUFFICIENT_CREDITS_MESSAGE;
      }
    } else {
      return ITEM_NOT_AVAILABLE_MESSAGE;
    }
  }

  /**
   * Displays information about a specific member using the View class.
   */
  public void viewMemberInfo(String findingMemberName) {
    Member member = findMember(findingMemberName);
    if (member != null) {
      member.getMemberDetails();
    } else {
      System.out.println(MEMBER_NOT_FOUND_MESSAGE);
    }
  }

  /**
  * Advances the system time by one day.
  */
  public void advanceTime() {
    time.advanceDay();
    System.out.println("Current day: " + time.getCurrentDay());
  }

  public Member findMember(String name) {
    return members.stream().filter(member ->
    member.getName().equals(name)).findFirst().orElse(null);
  }

  public Item findItem(String name) {
    return items.stream().filter(item ->
    item.getName().equals(name)).findFirst().orElse(null);
  }

  /**
  * Checks if an item is available between the specified dates.
  */
  public boolean isItemAvailable(Item item, LocalDate startDate, LocalDate endDate) {
    return item.getContracts().stream().noneMatch(contract ->
    !(endDate.isBefore(contract.getStartDate()) || startDate.isAfter(contract.getEndDate())));
  }

  public boolean isValidContract(Member borrower, Item item, LocalDate startDate, LocalDate endDate) {
    return borrower != null && item != null && isItemAvailable(item, startDate, endDate);
  }
}