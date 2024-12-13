package model;

/**
 * Represents a time tracker that keeps track of the current day.
 */
public class Time {
  private int currentDayCount;

  /**
  * Constructs a Time object with the current day initialized to 0.
  */
  public Time() {
    this.currentDayCount = 0;
  }

  /**
  * Advances the current day by one.
  */
  public void advanceDay() {
    currentDayCount++;
  }

  /**
  * Gets the current day count.
  *
  * @return the current day count
  */
  public int getCurrentDay() {
    return currentDayCount;
  }

  /**
  * Sets the current day count.
  *
  * @param day the new day count to set
  */
  public void setCurrentDay(int day) {
    this.currentDayCount = day;
  }
}