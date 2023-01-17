package pl.edu.agh.kis.pz1;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

public class XLSXReader {
    private final String fileName;
    private final FileInputStream fileStream;
    private final ArrayList<Invoice> invoices;

    public XLSXReader(String f) throws FileNotFoundException {
        fileName = f;
        fileStream = new FileInputStream(fileName);
        invoices = new ArrayList<>();
    }

    public void read() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        var iter = sheet.rowIterator();
        iter.next(); //pominięcie pierwszej linii

        ArrayList<String> parsedInvoices = new ArrayList<>();

        while (iter.hasNext()) {
            Row r = (Row) iter.next();

            String product = r.getCell(6).getStringCellValue();
            var amount = BigDecimal.valueOf(r.getCell(7).getNumericCellValue());
            var price = BigDecimal.valueOf(r.getCell(8).getNumericCellValue());
            int taxRate = (int) r.getCell(9).getNumericCellValue();
            var taxAmount = BigDecimal.valueOf(r.getCell(10).getNumericCellValue());
            var itemNetValue = BigDecimal.valueOf(r.getCell(11).getNumericCellValue());
            var itemGrossValue = BigDecimal.valueOf(r.getCell(12).getNumericCellValue());

            var item = new Item(product, amount, price, taxRate, taxAmount, itemNetValue, itemGrossValue);

            String invoiceNumber = r.getCell(5).getStringCellValue();
            if (!parsedInvoices.contains(invoiceNumber)) {
                parsedInvoices.add(invoiceNumber);

                String companyName = r.getCell(0).getStringCellValue();
                String address = r.getCell(1).getStringCellValue();
                String nip = r.getCell(2).getStringCellValue();
                String date = r.getCell(3).getDateCellValue().toString();
                String sellDate = r.getCell(4).getDateCellValue().toString();

                var netValue = BigDecimal.valueOf(r.getCell(13).getNumericCellValue());
                var grossValue = BigDecimal.valueOf(r.getCell(14).getNumericCellValue());

                var invoice = new Invoice(companyName, address, nip, date, sellDate, invoiceNumber,
                        netValue, grossValue);

                invoice.addItem(item);
                invoices.add(invoice);
                continue;
            }

            for (Invoice invoice : invoices) {
                if (invoice.number.equals(invoiceNumber)) {
                    invoice.addItem(item);
                    break;
                }
            }
        }
    }

    public ArrayList<Invoice> getInvoices() {
        return invoices;
    }
}
