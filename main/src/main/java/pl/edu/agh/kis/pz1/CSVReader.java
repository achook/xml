package pl.edu.agh.kis.pz1;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CSVReader {
    private final String fileName;
    private final FileReader fileReader;
    private final ArrayList<Invoice> invoices;

    public CSVReader(String f) throws FileNotFoundException {
        fileName = f;
        fileReader = new FileReader(fileName);
        invoices = new ArrayList<>();
    }

    static String changeCommaToDot(String s) {
        return s.replace(",", ".");
    }

    static String formatPrice(String s) {
        return s.replace(" ", "")
                .replace("zł", "")
                .replace(",", ".");
    }

    static BigDecimal parseNumber(String s) {
        return new BigDecimal(changeCommaToDot(s));
    }

    static BigDecimal parsePrice(String s) {
        return new BigDecimal(formatPrice(s));
    }

    public void read() throws IOException {//wczytanie pliku CSV
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder()
                .setIgnoreEmptyLines(false).setAllowMissingColumnNames(true).setDelimiter('\t').build()
                .parse(fileReader);

        records.iterator().next(); //pominięcie pierwszej linii

        ArrayList<String> parsedInvoices = new ArrayList<>();

        for (CSVRecord r : records) {
            String product = r.get(6);
            BigDecimal amount = parseNumber(r.get(7));
            BigDecimal price = parsePrice(r.get(8));

            int taxRate = Integer.parseInt(r.get(9));
            BigDecimal taxAmount = parsePrice(r.get(10));

            BigDecimal itemNetValue = parsePrice(r.get(11));
            BigDecimal itemGrossValue = parsePrice(r.get(12));

            var item = new Item(product, amount, price, taxRate, taxAmount, itemNetValue, itemGrossValue);

            String invoiceNumber = r.get(5);
            if (!parsedInvoices.contains(invoiceNumber)) {
                parsedInvoices.add(invoiceNumber);

                String companyName = r.get(0);
                String address = r.get(1);
                String nip = r.get(2);
                String date = r.get(3);
                String sellDate = r.get(4);

                BigDecimal netValue = parsePrice(r.get(13));
                BigDecimal grossValue = parsePrice(r.get(14));

                Invoice invoice = new Invoice(companyName, address, nip, date, sellDate, invoiceNumber,
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
