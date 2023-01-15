package pl.edu.agh.kis.pz1;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

import java.math.BigDecimal;
import java.util.ArrayList;

@XmlType(propOrder = {})
public class Invoice {
    @XmlElement(name = "tns:KodWaluty")
    static public String currencyCode = "PLN";

    @XmlElement(name = "tns:P_1")
    public String invoiceDate;

    @XmlElement(name = "tns:P_2A")
    public String number;

    @XmlElement(name = "tns:P_3A")
    public String companyName;
    @XmlElement(name = "tns:P_3B")
    public String customerName;
    @XmlElement(name = "tns:P_3C")
    public String sellerName;
    @XmlElement(name = "tns:P_3D")
    public String sellerAddress;
    @XmlElement(name = "tns:P_4A")
    public String sellerCountryCode;
    @XmlElement(name = "tns:P_4B")
    public String sellerNIP;


    @XmlElement(name = "tns:P_5B")
    public String companyNIP;


    @XmlElement(name = "tns:P_6")
    public String sellDate;

    @XmlElement(name = "tns:P_13_1")
    public BigDecimal totalNetPrice;

    @XmlElement(name = "tns:P_14_1")
    public BigDecimal totalTaxAmount;

    @XmlElement(name = "tns:P_15")
    public BigDecimal totalGrossPrice;

    @XmlTransient
    public String companyAddress;
    @XmlTransient
    public ArrayList<Item> items;

    @XmlElement(name = "tns:P_16")
    static public String p16 = "false";


    @XmlElement(name = "tns:P_17")
    static public String p17 = "false";

    @XmlElement(name = "tns:P_18")
    static public String p18 = "false";

    @XmlElement(name = "tns:P_18A")
    static public String p18a = "false";

    @XmlElement(name = "tns:P_19")
    static public String p19 = "false";

    @XmlElement(name = "tns:P_20")
    static public String p20 = "false";

    @XmlElement(name = "tns:P_21")
    static public String p21 = "false";

    @XmlElement(name = "tns:P_22")
    static public String p22 = "false";

    @XmlElement(name = "tns:P_23")
    static public String p23 = "false";

    @XmlElement(name = "tns:P_106E_2")
    static public String p106e2 = "false";

    @XmlElement(name = "tns:P_106E_3")
    static public String p106e3 = "false";

    @XmlElement(name = "tns:RodzajFaktury")
    static public String invoiceType = "VAT";

    public Invoice(String companyName, String address, String nip,
                   String date, String sellDate, String invoiceNumber,
                   BigDecimal totalNetPrice, BigDecimal totalGrossPrice) {
        this.companyName = companyName;
        this.companyAddress = address;
        this.companyNIP = nip;

        this.invoiceDate = date;
        this.sellDate = sellDate;

        this.customerName = companyName + " " + address;

        this.number = invoiceNumber;

        this.totalNetPrice = totalNetPrice;
        this.totalGrossPrice = totalGrossPrice;
        this.totalTaxAmount = totalGrossPrice.subtract(totalNetPrice);

        this.items = new ArrayList<>();

        this.sellerName = "Jan Kowalski";
        this.sellerAddress = "ul. Kowalska 1, 00-000 Warszawa";
        this.sellerCountryCode = "PL";
        this.sellerNIP = "1234567890";
    }

    public void addItem (Item item) {
        items.add(item);
    }
}
