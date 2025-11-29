package edu.pasadena.cs.cs03b;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;

public class TestTaxTableTools {

    @Test
    public void testGetValue() {
        TaxTableTools ttt = new TaxTableTools();

        double iResult = ttt.getTaxRate(11600);  // Expected tax rate: 10%
        assertEquals(0.10, iResult);

       iResult = ttt.getTaxRate(11599);  // Expected tax rate: 10%
        assertEquals(0.10, iResult);

        iResult = ttt.getTaxRate(47151);  // Expected tax rate: 22%
        assertEquals(0.22, iResult);

        iResult = ttt.getTaxRate(100520);  // Expected tax rate: 22%
        assertEquals(0.22, iResult);

        iResult = ttt.getTaxRate(191949);  // Expected tax rate: 24%
        assertEquals(0.24, iResult);

        iResult = ttt.getTaxRate(243725);  // Expected tax rate: 32%
        assertEquals(0.32, iResult);

        iResult = ttt.getTaxRate(609349);  // Expected tax rate: 35%
        assertEquals(0.35, iResult);

        iResult = ttt.getTaxRate(609351);  // Expected tax rate: 37%
        assertEquals(0.37, iResult);
    }

   
}