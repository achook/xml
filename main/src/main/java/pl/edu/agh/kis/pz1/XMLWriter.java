package pl.edu.agh.kis.pz1;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;

public class XMLWriter {
    private String fileName;
    private JPKFile jpkFile;

    public XMLWriter(String outputFileName, JPKFile jpkFile) {
        this.fileName = outputFileName;
        this.jpkFile = jpkFile;
    }

    public void write() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(JPKFile.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(jpkFile, new File(fileName));
    }
}
