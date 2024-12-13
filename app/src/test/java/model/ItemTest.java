package model;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Member owner;
    private Item item;

    @BeforeEach
    void setUp() {
        // Create a mock Member object
        owner = new Member("John Doe", "john@example.com", "11111222333");
        item = new Item("Lawn Mower", "A gas-powered lawn mower", "Garden Equipment", owner, 15);
    }

    @Test
    void testConstructor_ValidInput() {
        assertNotNull(item);
        assertEquals("Lawn Mower", item.getName());
        assertEquals("A gas-powered lawn mower", item.getDescription());
        assertEquals("Garden Equipment", item.getCategory());
        assertEquals(owner, item.getOwner());
        assertEquals(15, item.getCostPerDay());
    }

    @Test
    void testConstructor_NullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Item(null, "Description", "Category", owner, 10);
        });
    }

    @Test
    void testConstructor_NullDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Item("Name", null, "Category", owner, 10);
        });
    }

    @Test
    void testConstructor_NullCategory() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Item("Name", "Description", null, owner, 10);
        });
    }

    @Test
    void testConstructor_NullOwner() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Item("Name", "Description", "Category", null, 10);
        });
    }

    @Test
    void testConstructor_NegativeCostPerDay() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Item("Name", "Description", "Category", owner, -5);
        });
    }

    @Test
    void testGetItemInfo() {
        String expectedInfo = "Item Name: Lawn Mower\n"
                + "Description: A gas-powered lawn mower\n"
                + "Category: Garden Equipment\n"
                + "Owner: John Doe";
        assertEquals(expectedInfo, item.getItemInfo());
    }

    @Test
    void testAddContract() {
        Contract contract = new Contract(owner,item, LocalDate.now(), LocalDate.now().plusDays(5));
        item.addContract(contract);
        assertEquals(1, item.getContracts().size());
    }

    @Test
    void testAddContract_NullContract() {
        assertThrows(IllegalArgumentException.class, () -> {
            item.addContract(null);
        });
    }

    @Test
    void testGetContracts_ReturnsCopy() {
        Contract contract1 = new Contract(owner, item, LocalDate.now(), LocalDate.now().plusDays(5));
        item.addContract(contract1);

        // Get contracts and try to modify the returned list
        List<Contract> contracts = item.getContracts();
        contracts.clear(); // This should not affect the original list in the item

        assertEquals(1, item.getContracts().size()); // Original list should remain unchanged
    }

    @Test
    void testToString() {
        String expectedString = "Item{"
                + "itemId='" + item.getItemId() + '\''
                + ", name='Lawn Mower'"
                + ", description='A gas-powered lawn mower'"
                + ", category='Garden Equipment'"
                + ", owner=John Doe"
                + ", costPerDay=15"
                + ", createdDate=" + item.getCreatedDate() // Assuming getCreatedDate() is added
                + ", contracts=0"
                + '}';
        assertEquals(expectedString, item.toString());
    }
}