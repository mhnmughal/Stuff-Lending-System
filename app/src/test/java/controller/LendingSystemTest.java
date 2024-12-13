package controller;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LendingSystemTest {

    private LendingSystem lendingSystem;

    @BeforeEach
    void setUp() {
        lendingSystem = new LendingSystem();
    }

    @Test
    void testAddMember() {
        lendingSystem.addMember("Alice black", "alice@example.com", "987654321");
        assertEquals(4, lendingSystem.getMembers().size()); // Two members already in initializeData
        assertEquals("Alice black", lendingSystem.getMembers().get(3).getName());
    }

    @Test
    void testAddItem() {
        String message = lendingSystem.addItem("John Doe", "Computer", "Gaming PC", "Electronic", 30);
        assertEquals("Item added successfully for John Doe", message);
        assertEquals(7, lendingSystem.getItems().size()); // Additional items from initializeData
        assertEquals("Computer", lendingSystem.getItems().get(6).getName());
    }

    @Test
    void testAddItemForNonExistingMember() {
        String message = lendingSystem.addItem("Nonexistent Member", "Camera", "DSLR Camera", "Electronic", 50);
        assertEquals("Member not found!", message);
    }

    @Test
    void testCreateLendingContract() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);
        String message = lendingSystem.createLendingContract("Jane Smith", "Bicycle", startDate, endDate);
        assertEquals("Contract created successfully!", message);
        assertEquals(2, lendingSystem.getContracts().size()); // Including one from initializeData
    }

    @Test
    void testCreateLendingContractWithInsufficientCredits() {
        Member member = lendingSystem.findMember("Alice Brown");
        member.deductCredits(member.getCredits()); // Deduct all credits for insufficient balance

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);

        String message = lendingSystem.createLendingContract("Alice Brown", "Projector", startDate, endDate);
        assertEquals("Not enough credits for the borrower.", message);
    }

    @Test
    void testCreateLendingContractForNonExistingItem() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);
        String message = lendingSystem.createLendingContract("Jane Smith", "Nonexistent Item", startDate, endDate);
        assertEquals("Borrower or Item not found, or item is not available for the selected dates.", message);
    }

    @Test
    void testCreateLendingContractForUnavailableItem() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);

        // Assuming this contract already exists in the initial data
        lendingSystem.createLendingContract("Jane Smith", "Laptop", startDate, endDate);

        String message = lendingSystem.createLendingContract("Alice Brown", "Laptop", startDate, endDate);
        assertEquals("Borrower or Item not found, or item is not available for the selected dates.", message);
    }

    @Test
    void testViewMemberInfo() {
        lendingSystem.viewMemberInfo("John Doe");
        Member member = lendingSystem.findMember("John Doe");
        assertNotNull(member);
        assertEquals("John Doe", member.getName());
    }

    @Test
    void testAdvanceTime() {
        lendingSystem.advanceTime();
        assertEquals(1, lendingSystem.getCurrentDay());
    }

    @Test
    void testFindMember() {
        Member member = lendingSystem.findMember("John Doe");
        assertNotNull(member);
        assertEquals("John Doe", member.getName());
    }

    @Test
    void testFindItem() {
        Item item = lendingSystem.findItem("Laptop");
        assertNotNull(item);
        assertEquals("Laptop", item.getName());
    }

    @Test
    void testIsItemAvailable() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);
        Item item = lendingSystem.findItem("Guitar");
        assertTrue(lendingSystem.isItemAvailable(item, startDate, endDate));
    }

    @Test
    void testIsValidContract() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);
        Member borrower = lendingSystem.findMember("Jane Smith");
        Item item = lendingSystem.findItem("Projector");
        assertTrue(lendingSystem.isValidContract(borrower, item, startDate, endDate));
    }
}
