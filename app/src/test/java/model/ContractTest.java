package model;


import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class ContractTest {

    @Test
    void testContractCreation() {
        Member borrower = new Member("Borrower", "borrower@example.com", "2222222222");
        Member owner = new Member("Owner", "owner@example.com", "3333333333");
        Item item = new Item("Guitar", "An acoustic guitar", "Music", owner, 10);
        Contract contract = new Contract(borrower, item, LocalDate.now(), LocalDate.now().plusDays(7));

        assertNotNull(contract.getStartDate());
        assertNotNull(contract.getEndDate());
        assertEquals(borrower, contract.getBorrower());
        assertEquals(item, contract.getItem());
    }

    @Test
    void testValidateContractSuccess() {
        Member borrower = new Member("Borrower", "borrower@example.com", "4444444444");
        Member owner = new Member("Owner", "owner@example.com", "5555555555");
        Item item = new Item("Piano", "A grand piano", "Music", owner, 50);

        // No need to manually deduct credits; assume the borrower starts with enough credits
        Contract contract = new Contract(borrower, item, LocalDate.now(), LocalDate.now().plusDays(2));

        assertTrue(contract.validateContract()); // Should validate successfully
    }

    @Test
    void testValidateContractInsufficientCredits() {
        Member borrower = new Member("Borrower", "borrower@example.com", "6666666666");
        Member owner = new Member("Owner", "owner@example.com", "7777777777");
        Item item = new Item("Drum Set", "A complete drum set", "Music", owner, 80);
        Contract contract = new Contract(borrower, item, LocalDate.now(), LocalDate.now().plusDays(2));

        assertFalse(contract.validateContract()); // Should fail due to insufficient credits
    }
}
