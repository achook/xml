package pl.edu.agh.kis.pz1;

import jakarta.xml.bind.JAXBException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws JAXBException, IOException {
        /*
        var inputFileName = "input.csv";

        var csvReader = new CSVReader(inputFileName);
        csvReader.read();
        var invoices = csvReader.getInvoices();
         */

        var inputFileName = "input.xlsx";
        var xlsxReader = new XLSXReader(inputFileName);
        xlsxReader.read();
        var invoices = xlsxReader.getInvoices();

        JPKFile jpkFile = new JPKFile();
        jpkFile.addInvoices(invoices);

        var outputFileName = "output.xml";
        var xmlWriter = new XMLWriter(outputFileName, jpkFile);

        xmlWriter.write();
    }
}
