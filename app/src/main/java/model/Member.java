package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a member with personal information and owned items.
 */
public class Member {
  private static final int INITIAL_CREDITS = 100; // Constant for initial credits
  private static final int CREDIT_INCREMENT = 100; // Constant for credits added when an item is owned

  private final String memberId; // Changed to final as it's generated once
  private String name;
  private String email;
  private String phoneNumber;
  private int credits;
  private final List<Item> ownedItems;
  private final LocalDate createdDate;

  /**
  * Constructor to create a new member.
  *
  * @param name        the name of the member
  * @param email       the email of the member
  * @param phoneNumber the phone number of the member
  */
  public Member(String name, String email, String phoneNumber) {
    this.memberId = generateUniqueId();
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.credits = INITIAL_CREDITS;
    this.ownedItems = new ArrayList<>();
    this.createdDate = LocalDate.now();
  }

  private String generateUniqueId() {
    return UUID.randomUUID().toString().substring(0, 6);
  }

  // Getters
  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public int getCredits() {
    return credits;
  }

  public List<Item> getOwnedItems() {
    return new ArrayList<>(ownedItems); // Return a copy for immutability
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public String getMemberId() {
    return memberId;
  }

  /**
  * Updates the member's information.
  *
  * @param name        the new name of the member
  * @param email       the new email of the member
  * @param phoneNumber the new phone number of the member
  */
  public void updateMemberInfo(String name, String email, String phoneNumber) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  /**
  * Returns a string representation of the member's details.
  *
  * @return member details
  */
  public String getMemberDetails() {
    return "Member ID: " + memberId + "\n"
      + "Name: " + name + "\n"
      + "Email: " + email + "\n"
      + "Phone: " + phoneNumber + "\n"
      + "Credits: " + credits;
  }

  /**
  * Adds an item to the member's owned items and increments credits.
  *
  * @param item the item to be added
  */
  public void addItem(Item item) {
    this.ownedItems.add(item);
    this.credits += CREDIT_INCREMENT;
  }

  /**
  * Deducts credits from the member.
  *
  * @param amount the amount to deduct
  * @throws IllegalArgumentException if not enough credits are available
  */
  public void deductCredits(int amount) {
    if (this.credits >= amount) {
      this.credits -= amount;
    } else {
      throw new IllegalArgumentException("Not enough credits to deduct " + amount + ".");
    }
  }

  /**
  * Adds credits to the member.
  *
  * @param amount the amount to add
  */
  public void addCredits(int amount) {
    this.credits += amount;
  }
}