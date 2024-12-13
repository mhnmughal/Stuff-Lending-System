Here's the updated test report based on the test cases you provided:

---

# Test Report: Stuff Lending System

*Version:* 1.0  
*Date:* 19-08-2024  
*Environment:* Windows 11, Visual Studio Code  
*Performed by:* sitara

## General

- Cloned the application from GitLab into a new directory.
  Command executed: ./gradlew run -q --console=plain.
- Documented any issues encountered during the above steps and proceeded with the remaining test cases.

# Overview

This test report covers the testing of the Stuff Lending System, focusing on Member Management, Item Management, Contract Management, Time Management, and Code Quality modules.

## Table of Contents

1. [Member Management Tests](#member-management-tests)
2. [Item Management Tests](#item-management-tests)
3. [Contract Management Tests](#contract-management-tests)
4. [Time Management Tests](#time-management-tests)
5. [Code Quality Tests](#code-quality-tests)

---

## Test Results

| Case    | Result | Notes |
|---------|--------|-------|
| 1.1     | ✔      |       |
| 1.2     | ✔      |       |
| 1.3     | ✔      |       |
| 1.4     | ✔      |       |
| 1.5     | ✔      |       |
| 2.1     | ✔      |       |
| 2.2     | ✔      |       |
| 2.3     | ✔      |       |
| 3.1     | ✔      |       |
| 3.2     | ✔      |       |
| 3.3     | ✔      |       |
| 4.1     | ✔      |       |
| 5.1     | ✔      |       |

---

## 1. Member Management Tests

### Test Case 1.1: Create Member (Test Method: testMemberCreation)
- *Description:* Verifies the creation of a new member and the initial values for their details.
- *Test Steps:* Create a member with valid details and check for correctness.
- *Expected Result:* Member is successfully created with correct name, email, phone number, and initial credits of 100.

### Test Case 1.2: Update Member Information (Test Method: testUpdateMemberInfo)
- *Description:* Verifies the functionality for updating a member’s details.
- *Test Steps:* Update an existing member's name, email, and phone number, and check the updated values.
- *Expected Result:* Member information is successfully updated.

### Test Case 1.3: Add Item to Member (Test Method: testAddItem)
- *Description:* Verifies that a member can add an item to their list of owned items.
- *Test Steps:* Create an item, add it to a member, and verify the item addition and updated credits.
- *Expected Result:* Item is added to the member’s owned items list, and credits increase by 100.

### Test Case 1.4: Deduct Credits (Test Method: testDeductCredits)
- *Description:* Verifies the deduction of credits from a member’s account.
- *Test Steps:* Deduct a specified amount from the member's credits and verify the balance.
- *Expected Result:* Credits are successfully deducted, and the balance is updated accordingly.

### Test Case 1.5: Insufficient Credits (Test Method: testDeductCreditsInsufficient)
- *Description:* Verifies the system’s behavior when deducting more credits than available.
- *Test Steps:* Attempt to deduct more credits than the member has and check for proper exception handling.
- *Expected Result:* The system throws an exception with the message "Not enough credits."

---

## 2. Item Management Tests

### Test Case 2.1: Create Item (Test Method: testItemCreation)
- *Description:* Verifies the creation of a new item for a member.
- *Test Steps:* Create a new item and associate it with a member.
- *Expected Result:* Item is created successfully with the correct name, category, cost per day, and owner.

### Test Case 2.2: Add Contract to Item (Test Method: testAddContract)
- *Description:* Verifies that a contract can be added to an item.
- *Test Steps:* Create an item and add a contract associated with the item.
- *Expected Result:* Contract is successfully added to the item's contract list.

### Test Case 2.3: View Item Details (Test Method: testViewItemDetails)
- *Description:* Verifies that item details can be viewed.
- *Test Steps:* View the details of an item and check for correctness (manual verification required).
- *Expected Result:* Item details are printed and manually verified.

---

## 3. Contract Management Tests

### Test Case 3.1: Create Contract (Test Method: testContractCreation)
- *Description:* Verifies the creation of a lending contract between a borrower and an item.
- *Test Steps:* Create a borrower, owner, and item, then create a contract for borrowing the item.
- *Expected Result:* Contract is successfully created with correct details such as borrower, item, start date, and end date.

### Test Case 3.2: Validate Contract Success (Test Method: testValidateContractSuccess)
- *Description:* Verifies that a contract is validated successfully when the borrower has sufficient credits.
- *Test Steps:* Create a contract and validate it.
- *Expected Result:* The contract is validated successfully, and the transaction proceeds.

### Test Case 3.3: Validate Contract Insufficient Credits (Test Method: testValidateContractInsufficientCredits)
- *Description:* Verifies that contract validation fails when the borrower does not have enough credits.
- *Test Steps:* Create a contract where the borrower has insufficient credits and validate it.
- *Expected Result:* The contract validation fails due to insufficient credits.

---

## 4. Time Management Tests

### Test Case 4.1: Advance Time (Test Method: testAdvanceTime)
- *Description:* Verifies the functionality of advancing time within the system.
- *Test Steps:* Advance the time by a specified number of days and verify that the current day is updated.
- *Expected Result:* The current day is successfully updated.

---

## 5. Code Quality Tests

### Test Case 5.1: Code Quality (Test Method: codeQuality)
- *Description:* Verifies that the code meets quality standards set by CheckStyle and SpotBugs.
- *Test Steps:* Run CheckStyle and SpotBugs reports and check the total number of issues.
- *Expected Result:* The number of issues is below the maximum threshold of 400.

---

## Conclusion

The Stuff Lending System has been thoroughly tested for its key functionalities, including Member Management, Item Management, Contract Management, Time Management, and Code Quality. All tested functionalities have been verified to work as expected, and the code meets the quality standards.

---  

*Report Prepared by:* Kumail  
*Date:* 21-10-2024

---