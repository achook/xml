package pl.edu.agh.kis.pz1;

import jakarta.xml.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;

@XmlRootElement(name = "tns:JPK")
public class JPKFile {

    static public class Header {
        @XmlElement(name = "tns:KodFormularza")
        String formCode;

        @XmlElement(name = "tns:WariantFormularza")
        String formVariant;

        @XmlElement(name = "tns:CelZlozenia")
        String purpose;

        @XmlElement(name = "tns:DataWytworzeniaJPK")
        String creationDate;

        @XmlElement(name = "tns:DataOd")
        String startDate;

        @XmlElement(name = "tns:DataDo")
        String endDate;

        @XmlElement(name = "tns:KodUrzedu")
        String officeCode;

        public Header() {
            formCode = "JPK_FA";
            formVariant = "3";
            purpose = "1";
            creationDate = "2021-05-20";
            startDate = "2021-05-01";
            endDate = "2021-05-20";
            officeCode = "123456789";
        }
    }

    static public class Subject {
        static public class SubjectIdentity {
            @XmlElement(name = "etd:NIP")
            String nip;

            @XmlElement(name = "etd:PelnaNazwa")
            String fullName;

            SubjectIdentity() {
                nip = "1234567890";
                fullName = "Jan Kowalski";
            }
        }

        static public class SubjectAddress {
            @XmlElement(name = "etd:KodKraju")
            String countryCode;

            @XmlElement(name = "etd:Wojewodztwo")
            String province;

            @XmlElement(name = "etd:Powiat")
            String district;

            @XmlElement(name = "etd:Gmina")
            String commune;

            @XmlElement(name = "etd:Ulica")
            String street;

            @XmlElement(name = "etd:NrDomu")
            String houseNumber;

            @XmlElement(name = "etd:NrLokalu")
            String apartmentNumber;

            @XmlElement(name = "etd:Miejscowosc")
            String city;

            @XmlElement(name = "etd:KodPocztowy")
            String postalCode;

            SubjectAddress() {
                countryCode = "PL";
                province = "Dolnośląskie";
                district = "Wrocławski";
                commune = "Wrocław";
                street = "Koszykowa";
                houseNumber = "1";
                apartmentNumber = "1";
                city = "Wrocław";
                postalCode = "50-000";
            }
        }

        @XmlElement(name = "tns:IdentyfikatorPodmiotu")
        SubjectIdentity identity;

        @XmlElement(name = "tns:AdresPodmiotu")
        SubjectAddress address;

        Subject() {
            identity = new SubjectIdentity();
            address = new SubjectAddress();
        }
    }

    @XmlElement(name = "tns:Naglowek")
    public Header header;

    @XmlElement(name = "tns:Podmiot1")
    public Subject subject;

    @XmlElement(name = "tns:Faktura")
    public ArrayList<Invoice> invoices;

    @XmlElement(name = "tns:FakturaWiersz")
    public ArrayList<Item> items;

    public JPKFile() {
        this.header = new Header();
        this.subject = new Subject();
        this.invoices = new ArrayList<>();

        var testInvoice = new Invoice("Test Company", "Test Address", "1234567890",
                "2021-05-20", "2021-05-20", "1",
                new BigDecimal("100.00"), new BigDecimal("120.00"));

        testInvoice.addItem(new Item("Test Item", new BigDecimal("1"), new BigDecimal("100.00"), 23,
                new BigDecimal("23.00"), new BigDecimal("123.00"), new BigDecimal("100.00")));

        this.invoices.add(testInvoice);
    }


}
