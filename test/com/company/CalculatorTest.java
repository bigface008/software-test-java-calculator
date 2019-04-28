package com.company;

import com.sun.org.apache.xalan.internal.xsltc.dom.CachedNodeListIterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator calc;
    final private double max_error = 0.000001;

    @Before
    public void setUp() throws Exception {
        calc = new Calculator();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void calculateBi() {
        // Test wrong input.
//        assertTrue(Double.isNaN(calc.calculateBi(Calculator.BiOperatorModes.normal, 0.0)));
//        calc.reset();
//        Double t = calc.calculateBi(Calculator.BiOperatorModes.add, 1.0);
//        assertEquals(new Double(2.0), t);
//        // Test +.
//        assertEquals(calc.calculateBi(Calculator.BiOperatorModes.add, 1.0).doubleValue(), 2.0, 0.000001);
    }

    @Test
    public void calculateEqual() {
    }

    @Test
    public void reset() {
    }

    @Test
    public void calculateMono() {
        // Test square.
        assertEquals(0.0, calc.calculateMono(Calculator.MonoOperatorModes.square, 0.0), max_error);
        assertEquals(1.0, calc.calculateMono(Calculator.MonoOperatorModes.square, 1.0), max_error);
        assertEquals(81.0, calc.calculateMono(Calculator.MonoOperatorModes.square, 9.0), max_error);
        assertEquals(81.0, calc.calculateMono(Calculator.MonoOperatorModes.square, -9.0), max_error);

        // Test squareRoot.
        assertEquals(0.0, calc.calculateMono(Calculator.MonoOperatorModes.squareRoot, 0.0), max_error);
        assertEquals(1.0, calc.calculateMono(Calculator.MonoOperatorModes.squareRoot, 1.0), max_error);
        assertEquals(3.0, calc.calculateMono(Calculator.MonoOperatorModes.squareRoot, 9.0), max_error);
        assertTrue(Double.isNaN(calc.calculateMono(Calculator.MonoOperatorModes.squareRoot, -9.0)));

        // Test oneDividedBy.
        assertEquals(1.0, calc.calculateMono(Calculator.MonoOperatorModes.oneDevidedBy, 1.0), max_error);
        assertEquals(0.5, calc.calculateMono(Calculator.MonoOperatorModes.oneDevidedBy, 2.0), max_error);
        assertEquals(-0.5, calc.calculateMono(Calculator.MonoOperatorModes.oneDevidedBy, -2.0), max_error);
        assertEquals(0.25, calc.calculateMono(Calculator.MonoOperatorModes.oneDevidedBy, 4.0), max_error);
        assertTrue(Double.isInfinite(calc.calculateMono(Calculator.MonoOperatorModes.oneDevidedBy, 0.0)));

        // Test cos.
        assertEquals(1.0, calc.calculateMono(Calculator.MonoOperatorModes.cos, 0.0), max_error);

        // Test sin.
        assertEquals(0.0, calc.calculateMono(Calculator.MonoOperatorModes.sin, 0.0), max_error);

        // Test tan.
        assertEquals(0.0, calc.calculateMono(Calculator.MonoOperatorModes.tan, 0.0), max_error);

        // Test log.
        assertEquals(0.0, calc.calculateMono(Calculator.MonoOperatorModes.log, 1.0), max_error);
        assertEquals(1.0, calc.calculateMono(Calculator.MonoOperatorModes.log, 10.0), max_error);
        assertTrue(Double.isInfinite(calc.calculateMono(Calculator.MonoOperatorModes.log, 0.0)));

        // Test rate.
    }
}