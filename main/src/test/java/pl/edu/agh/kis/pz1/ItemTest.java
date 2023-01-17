package pl.edu.agh.kis.pz1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

class ItemTest {
    @Test
    void constructor() {
        String name = "testname";
        BigDecimal amount = BigDecimal.valueOf(1);
        BigDecimal price = BigDecimal.valueOf(1);
        int taxRate = 23;
        BigDecimal taxAmount = BigDecimal.valueOf(1);
        BigDecimal itemNetValue = BigDecimal.valueOf(1);
        BigDecimal itemGrossValue = BigDecimal.valueOf(1);
        Item item = new Item(name, amount, price, taxRate, taxAmount, itemNetValue, itemGrossValue);

        assertEquals(name, item.name);
        assertEquals(amount, item.quantity);
        assertEquals(price, item.singlePrice);
        assertEquals(taxRate, item.taxRate);
        assertEquals(taxAmount, item.taxAmount);
        assertEquals(itemNetValue, item.totalNetPrice);
        assertEquals(itemGrossValue, item.totalGrossPrice);
        assertEquals(BigDecimal.valueOf(1.23), item.singleGrossPrice);
    }
}