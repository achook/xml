package pl.edu.agh.kis.pz1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws JAXBException, IOException {
        var inputFileName = "input.csv";

        var csvReader = new CSVReader(inputFileName);
        csvReader.read();
        var invoices = csvReader.getInvoices();

        JPKFile jpkFile = new JPKFile();
        jpkFile.addInvoices(invoices);

        JAXBContext jaxbContext = JAXBContext.newInstance(JPKFile.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(jpkFile, System.out);
    }
}
