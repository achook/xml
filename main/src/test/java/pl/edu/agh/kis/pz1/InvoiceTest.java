package pl.edu.agh.kis.pz1;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {
    @Test
    void constructor() {
        String companyName = "testcompany";
        String address = "testaddress";
        String nip = "testnip";
        String date = "testdate";
        String sellDate = "testsellDate";
        String invoiceNumber = "testinvoiceNumber";
        BigDecimal totalNetPrice = BigDecimal.valueOf(1);
        BigDecimal totalGrossPrice = BigDecimal.valueOf(1);

        Invoice invoice = new Invoice(companyName, address, nip, date, sellDate, invoiceNumber, totalNetPrice, totalGrossPrice);

        assertEquals(companyName, invoice.companyName);
        assertEquals(address, invoice.companyAddress);
        assertEquals(nip, invoice.companyNIP);
        assertEquals(date, invoice.invoiceDate);
        assertEquals(sellDate, invoice.sellDate);
        assertEquals(invoiceNumber, invoice.number);
        assertEquals(totalNetPrice, invoice.totalNetPrice);
        assertEquals(totalGrossPrice, invoice.totalGrossPrice);
    }

    @Test
    void addItem() {
        String name = "testname";
        BigDecimal amount = BigDecimal.valueOf(1);
        BigDecimal price = BigDecimal.valueOf(1);
        int taxRate = 23;
        BigDecimal taxAmount = BigDecimal.valueOf(1);
        BigDecimal itemNetValue = BigDecimal.valueOf(1);
        BigDecimal itemGrossValue = BigDecimal.valueOf(1);
        Item item = new Item(name, amount, price, taxRate, taxAmount, itemNetValue, itemGrossValue);

        Invoice invoice = new Invoice("testcompany", "testaddress", "testnip", "testdate",
                "testsellDate", "testinvoiceNumber", BigDecimal.valueOf(1), BigDecimal.valueOf(1));

        invoice.addItem(item);

        assertEquals(item, invoice.items.get(0));
    }
}