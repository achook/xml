package pl.edu.agh.kis.pz1;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {
    @Test
    void changeCommaToDot() {
        var input = "1,23";
        var output = CSVReader.changeCommaToDot(input);

        assertEquals("1.23", output);
    }

    @Test
    void formatPrice() {
        var input = "1.23";
        var output = CSVReader.formatPrice(input);

        assertEquals(input, output);
    }

    @Test
    void parseNumber() {
        var input = "1.23";
        var output = CSVReader.parseNumber(input);

        assertEquals(new BigDecimal("1.23"), output);
    }

    @Test
    void parsePrice() {
        var input = "1,23zł";
        var output = CSVReader.parsePrice(input);

        assertEquals(new BigDecimal("1.23"), output);
    }
}