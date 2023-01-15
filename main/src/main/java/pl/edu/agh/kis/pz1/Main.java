package pl.edu.agh.kis.pz1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, JAXBException {
        JPKFile jpkFile = new JPKFile();

        JAXBContext jaxbContext = JAXBContext.newInstance(JPKFile.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(jpkFile, System.out);

    }
}
