package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents an item that can be rented or borrowed.
 */
public class Item {
  private final String itemId;
  private final String name;
  private final String description;
  private final String category;
  private final Member owner;
  private final int costPerDay;
  private final LocalDate createdDate;
  private final List<Contract> contracts;

  /**
   * Constructs an Item with the specified details.
   *
   * @param name        the name of the item
   * @param description the description of the item
   * @param category    the category of the item
   * @param owner       the owner of the item
   * @param costPerDay  the cost per day for renting the item
   * @throws IllegalArgumentException if named, description, category, or owner is null
   */
  public Item(String name, String description, String category, Member owner, int costPerDay) {
    if (name == null || description == null || category == null || owner == null) {
      throw new IllegalArgumentException("Name, description, category, and owner cannot be null");
    }
    if (costPerDay < 0) {
      throw new IllegalArgumentException("Cost per day cannot be negative");
    }

    this.itemId = UUID.randomUUID().toString().substring(0, 6);
    this.name = name;
    this.description = description;
    this.category = category;
    this.owner = owner;
    this.costPerDay = costPerDay;
    this.createdDate = LocalDate.now();
    this.contracts = new ArrayList<>();
  }

  // Getters
  public String getName() {
    return name;
  }

  public Member getOwner() {
    return owner;
  }

  public int getCostPerDay() {
    return costPerDay;
  }

  public String getCategory() {
    return category;
  }

  public List<Contract> getContracts() {
    return new ArrayList<>(contracts); // Return a copy to prevent modification
  }

  public String getDescription() {
    return description;
  }

  public String getItemId() {
    return itemId;
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  /**
   * Returns a string representation of the item.
   *
   * @return a string containing the item's details
   */
  public String getItemInfo() {
    return "Item Name: " + name + "\n"
      + "Description: " + description + "\n"
      + "Category: " + category + "\n"
      + "Owner: " + owner.getName();
  }

  /**
    * Adds a contract to the item.
    *
    * @param contract the contract to be added
    */
  public void addContract(Contract contract) {
    if (contract == null) {
      throw new IllegalArgumentException("Contract cannot be null");
    }
    this.contracts.add(contract);
  }

  @Override
  public String toString() {
    return "Item{"
      + "itemId='" + itemId + '\''
      + ", name='" + name + '\''
      + ", description='" + description + '\''
      + ", category='" + category + '\''
      + ", owner=" + owner.getName()
      + ", costPerDay=" + costPerDay
      + ", createdDate=" + createdDate
      + ", contracts=" + contracts.size()
      + '}';
  }
}