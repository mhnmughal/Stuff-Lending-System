package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void testMemberCreation() {
        Member member = new Member("John Doe", "john@example.com", "1234567890");
        assertEquals("John Doe", member.getName());
        assertEquals("john@example.com", member.getEmail());
        assertEquals("1234567890", member.getPhoneNumber());
        assertEquals(100, member.getCredits()); // Initial credits should be 100
        assertNotNull(member.getMemberId()); // Ensure member ID is generated
    }

    @Test
    void testUpdateMemberInfo() {
        Member member = new Member("Jane Doe", "jane@example.com", "0987654321");
        member.updateMemberInfo("Jane Smith", "jane.smith@example.com", "1112223333");
        assertEquals("Jane Smith", member.getName());
        assertEquals("jane.smith@example.com", member.getEmail());
        assertEquals("1112223333", member.getPhoneNumber());
    }

    @Test
    void testAddSingleItem() {
        Member member = new Member("Alice", "alice@example.com", "9876543210");
        Item item = new Item("Book", "A fascinating book", "Literature", member, 10);
        member.addItem(item);
        assertEquals(1, member.getOwnedItems().size());
        assertEquals(item, member.getOwnedItems().get(0));
        assertEquals(200, member.getCredits()); // Credits should increase by 100
    }

    @Test
    void testAddMultipleItems() {
        Member member = new Member("Bob", "bob@example.com", "1231231234");
        Item item1 = new Item("Book", "A fascinating book", "Literature", member, 10);
        Item item2 = new Item("Game", "A fun board game", "Entertainment", member, 15);

        member.addItem(item1);
        member.addItem(item2);

        assertEquals(2, member.getOwnedItems().size());
        assertEquals(item1, member.getOwnedItems().get(0));
        assertEquals(item2, member.getOwnedItems().get(1));
        assertEquals(300, member.getCredits()); // Credits should increase by 200
    }

    @Test
    void testDeductCredits() {
        Member member = new Member("Charlie", "charlie@example.com", "4564564567");
        member.deductCredits(50);
        assertEquals(50, member.getCredits()); // Should be 100 - 50 = 50
    }

    @Test
    void testDeductCreditsInsufficient() {
        Member member = new Member("David", "david@example.com", "7897897890");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            member.deductCredits(150); // Not enough credits
        });
        assertEquals("Not enough credits to deduct 150.", exception.getMessage());
    }

    @Test
    void testAddCredits() {
        Member member = new Member("Eve", "eve@example.com", "3213213210");
        member.addCredits(50);
        assertEquals(150, member.getCredits()); // Should be 100 + 50 = 150
    }

    @Test
    void testMemberIdUniqueness() {
        Member member1 = new Member("Frank", "frank@example.com", "6546546543");
        Member member2 = new Member("Grace", "grace@example.com", "9879879876");

        assertNotEquals(member1.getMemberId(), member2.getMemberId(), "Member IDs should be unique");
    }

    @Test
    void testOwnedItemsImmutability() {
        Member member = new Member("Hannah", "hannah@example.com", "1111111111");
        Item item = new Item("Laptop", "A powerful laptop", "Electronics", member, 1000);
        member.addItem(item);

        // Attempt to modify the returned owned items list
        List<Item> ownedItems = member.getOwnedItems();
        ownedItems.clear(); // This should not affect the member's owned items

        assertEquals(1, member.getOwnedItems().size(), "Owned items list should be immutable");
    }
}