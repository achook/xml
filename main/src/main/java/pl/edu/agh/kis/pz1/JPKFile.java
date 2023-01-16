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

    static public class InvoiceControl {
        @XmlElement(name = "tns:LiczbaFakur")
        int invoiceCount;

        @XmlElement(name = "tns:WartoscFaktur")
        BigDecimal invoicesValue;

        InvoiceControl() {
            invoiceCount = 0;
            invoicesValue = BigDecimal.ZERO;
        }

        InvoiceControl(ArrayList<Invoice> invoices) {
            invoiceCount = invoices.size();
            invoicesValue = BigDecimal.ZERO;
            for (Invoice invoice : invoices) {
                invoicesValue = invoicesValue.add(invoice.totalGrossPrice);
            }
        }
    }

    static public class ElementControl {
        @XmlElement(name = "tns:LiczbaWierszyFaktur")
        int invoiceRowCount;

        @XmlElement(name = "tns:WartoscWierszyFaktur")
        BigDecimal invoicesValue;

        ElementControl() {
            invoiceRowCount = 0;
            invoicesValue = BigDecimal.ZERO;
        }

        ElementControl(ArrayList<Item> items) {
            invoiceRowCount = items.size();
            invoicesValue = BigDecimal.ZERO;
            for (Item item : items) {
                invoicesValue = invoicesValue.add(item.totalGrossPrice);
            }
        }
    }

    @XmlElement(name = "tns:Naglowek")
    public Header header;

    @XmlElement(name = "tns:Podmiot1")
    public Subject subject;

    @XmlElement(name = "tns:Faktura")
    public ArrayList<Invoice> invoices;

    @XmlElement(name = "tns:FakturaCtrl")
    public InvoiceControl invoiceControl;

    @XmlElement(name = "tns:FakturaWiersz")
    public ArrayList<Item> items;

    @XmlElement(name = "tns:FakturaWierszCtrl")
    public ElementControl elementControl;

    public JPKFile() {
        this.header = new Header();
        this.subject = new Subject();

        this.invoices = new ArrayList<>();
        this.items = new ArrayList<>();

        this.invoiceControl = new InvoiceControl(this.invoices);
        this.elementControl = new ElementControl(this.items);
    }

    public void addInvoices(ArrayList<Invoice> invoices) {
        this.invoices.addAll(invoices);

        for (Invoice invoice : invoices) {
            this.items.addAll(invoice.items);
        }

        this.invoiceControl = new InvoiceControl(this.invoices);
        this.elementControl = new ElementControl(this.items);
    }


}
