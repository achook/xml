package pl.edu.agh.kis.pz1;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {
    @XmlElement(name = "tns:P_2B")
    public String invoiceNumber;

    @XmlElement(name = "tns:P_7")
    public String name;

    @XmlElement(name = "tns:P_8A")
    public String unit = "szt";
    @XmlElement(name = "tns:P_8B")
    public BigDecimal quantity;

    @XmlElement(name = "tns:P_9A")
    public BigDecimal singlePrice;
    @XmlElement(name = "tns:P_9B")
    public BigDecimal singleGrossPrice;

    @XmlTransient
    public BigDecimal taxAmount;

    @XmlElement(name = "tns:P_11")
    public BigDecimal totalNetPrice;
    @XmlTransient
    public BigDecimal totalGrossPrice;

    @XmlElement(name = "tns:P_12")
    public int taxRate;

    public Item(String name, BigDecimal amount, BigDecimal price,
                int taxRate, BigDecimal taxAmount,
                BigDecimal itemNetValue, BigDecimal itemGrossValue) {
        this.name = name;
        this.quantity = amount;
        this.singlePrice = price;
        this.taxRate = taxRate;
        this.taxAmount = taxAmount;
        this.totalNetPrice = itemNetValue;
        this.totalGrossPrice = itemGrossValue;
        this.singleGrossPrice = price.multiply(BigDecimal.valueOf((long) taxRate + 100)).divide(BigDecimal.valueOf(100),
                2, RoundingMode.HALF_EVEN);
    }
}
