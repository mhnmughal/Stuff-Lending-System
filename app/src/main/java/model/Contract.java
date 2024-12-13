package model;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a contract for borrowing an item.
 */
public class Contract {
  private final String contractId; // Changed to camelCase
  private final Member borrower;
  private final Item item;
  private final LocalDate startDate;
  private final LocalDate endDate;
  private final int creditsTransferred;

  /**
   * Constructs a Contract with the specified borrower, item, start date, and end date.
   *
   * @param borrower the member borrowing the item
   * @param item     the item being borrowed
   * @param startDate the start date of the contract
   * @param endDate   the end date of the contract
   */
  public Contract(Member borrower, Item item, LocalDate startDate, LocalDate endDate) {
    this.contractId = UUID.randomUUID().toString().substring(0, 6);
    this.borrower = borrower;
    this.item = item;
    this.startDate = startDate;
    this.endDate = endDate;
    this.creditsTransferred = item.getCostPerDay() * calculateDuration();
  }

  /**
   * Calculates the duration of the contract in days.
   *
   * @return the duration of the contract
   */
  private int calculateDuration() {
    return (int) (endDate.toEpochDay() - startDate.toEpochDay());
  }

  // Data access methods
  public Member getBorrower() {
    return borrower;
  }

  public Item getItem() {
    return item;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public int getCreditsTransferred() {
    return creditsTransferred;
  }

  /**
   * Provides information about the contract.
   *
   * @return a string containing contract information
   */
  public String getContractInfo() { // Changed to camelCase
    return "Contract ID: " + contractId + "\n"
      + "Borrower: " + borrower.getName() + "\n"
      + "Item: " + item.getName() + "\n"
      + "Credits Transferred: "
      + creditsTransferred;
  }

  /**
   * Validates the contract by checking if the borrower has enough credits.
   *
   * @return true if the contract is valid and credits are deducted, false otherwise
   */
  public boolean validateContract() {
    if (borrower.getCredits() >= creditsTransferred) {
      borrower.deductCredits(creditsTransferred);
      item.getOwner().addCredits(creditsTransferred);
      return true;
    }
    return false;
  }
}