package pl.edu.agh.kis.pz1;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JPKFileTest {
    @Test
    void constructor() {
        JPKFile jpkFile = new JPKFile();
        assertNotNull(jpkFile);
    }

    @Test
    void addInvoices() {
        JPKFile jpkFile = new JPKFile();

        String companyName = "testcompany";
        String address = "testaddress";
        String nip = "testnip";
        String date = "testdate";
        String sellDate = "testsellDate";
        String invoiceNumber = "testinvoiceNumber";
        BigDecimal totalNetPrice = BigDecimal.valueOf(1);
        BigDecimal totalGrossPrice = BigDecimal.valueOf(1);

        Invoice invoice = new Invoice(companyName, address, nip, date, sellDate, invoiceNumber, totalNetPrice, totalGrossPrice);
        var invoiceList = new ArrayList<Invoice>();
        invoiceList.add(invoice);

        jpkFile.addInvoices(invoiceList);

        assertEquals(invoiceList, jpkFile.invoices);
    }
}