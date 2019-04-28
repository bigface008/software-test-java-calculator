package com.company;

import com.sun.org.apache.xalan.internal.xsltc.dom.CachedNodeListIterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator calc;
    final private double max_error = 0.000001;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        calc = new Calculator();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void calculateBi() {
        // Test add.
        calc.calculateBi(Calculator.BiOperatorModes.add, 1.0);
        assertEquals(2.0, calc.calculateEqual(1.0).doubleValue(), max_error);
        calc.reset();
        calc.calculateBi(Calculator.BiOperatorModes.add, 17.4);
        assertEquals(50.9, calc.calculateEqual(33.5).doubleValue(), max_error);
        calc.reset();
        calc.calculateBi(Calculator.BiOperatorModes.add, 1.0);
        assertEquals(-9.0, calc.calculateEqual(-10.0).doubleValue(), max_error);
        calc.reset();
        calc.calculateBi(Calculator.BiOperatorModes.add, 10000.0);
        assertEquals(20000.0, calc.calculateEqual(10000.0).doubleValue(), max_error);
        calc.reset();

        // Test minus.
        calc.calculateBi(Calculator.BiOperatorModes.minus, 1.0);
        assertEquals(0.0, calc.calculateEqual(1.0).doubleValue(), max_error);
        calc.reset();
        calc.calculateBi(Calculator.BiOperatorModes.minus, 17.4);
        assertEquals(-16.1, calc.calculateEqual(33.5).doubleValue(), max_error);
        calc.reset();
        calc.calculateBi(Calculator.BiOperatorModes.minus, 1.0);
        assertEquals(11.0, calc.calculateEqual(-10.0).doubleValue(), max_error);
        calc.reset();
        calc.calculateBi(Calculator.BiOperatorModes.minus, 10000.0);
        assertEquals(0.0, calc.calculateEqual(10000.0).doubleValue(), max_error);
        calc.reset();

        // Test multiply.
        calc.calculateBi(Calculator.BiOperatorModes.multiply, 1.0);
        assertEquals(0.0, calc.calculateEqual(0.0).doubleValue(), max_error);
        calc.reset();
        calc.calculateBi(Calculator.BiOperatorModes.multiply, 14.0);
        assertEquals(294.0, calc.calculateEqual(21.0).doubleValue(), max_error);
        calc.reset();
        calc.calculateBi(Calculator.BiOperatorModes.multiply, 17.4);
        assertEquals(582.9, calc.calculateEqual(33.5).doubleValue(), max_error);
        calc.reset();
        calc.calculateBi(Calculator.BiOperatorModes.multiply, -17.4);
        assertEquals(-582.9, calc.calculateEqual(33.5).doubleValue(), max_error);
        calc.reset();

        // Test divide.
        calc.calculateBi(Calculator.BiOperatorModes.divide, 1.0);
        assertEquals(1.0, calc.calculateEqual(1.0).doubleValue(), max_error);
        calc.reset();
        calc.calculateBi(Calculator.BiOperatorModes.divide, 16.0);
        assertEquals(4.0, calc.calculateEqual(4.0).doubleValue(), max_error);
        calc.reset();
        calc.calculateBi(Calculator.BiOperatorModes.divide, -17.4);
        assertEquals(-5.8, calc.calculateEqual(3.0).doubleValue(), max_error);
        calc.reset();
        calc.calculateBi(Calculator.BiOperatorModes.divide, 17.4);
        assertTrue(Double.isInfinite(calc.calculateEqual(0.0)));
        calc.reset();

        // Test xpowerofy.
        calc.calculateBi(Calculator.BiOperatorModes.xpowerofy, 2.0);
        assertEquals(2.0, calc.calculateEqual(1.0).doubleValue(), max_error);
        calc.reset();
        calc.calculateBi(Calculator.BiOperatorModes.xpowerofy, 2.0);
        assertEquals(8.0, calc.calculateEqual(3.0).doubleValue(), max_error);
        calc.reset();
        calc.calculateBi(Calculator.BiOperatorModes.xpowerofy, 4.0);
        assertEquals(0.0625, calc.calculateEqual(-2.0).doubleValue(), max_error);
        calc.reset();
        calc.calculateBi(Calculator.BiOperatorModes.xpowerofy, -4.0);
        assertEquals(0.0625, calc.calculateEqual(-2.0).doubleValue(), max_error);
        calc.reset();

        // Test error: wrong BiOperatorModes.
        thrown.expect(Error.class);
        calc.calculateBi(Calculator.BiOperatorModes.enumnum, 1.0);
        calc.calculateEqual(1.0);

        // Test error: start equation with BiOperatorModes.normal.
        // calc.calculateBi(Calculator.BiOperatorModes.normal, 1.0);
        // Conclusion: normal part of calculateBiImpl() is unreacheable.
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
        assertEquals(0.5, calc.calculateMono(Calculator.MonoOperatorModes.cos, Math.PI / 3), max_error);
        assertEquals(0.5, calc.calculateMono(Calculator.MonoOperatorModes.cos, -Math.PI / 3), max_error);
        assertEquals(0.0, calc.calculateMono(Calculator.MonoOperatorModes.cos, Math.PI / 2), max_error);
        assertEquals(0.0, calc.calculateMono(Calculator.MonoOperatorModes.cos, -Math.PI / 2), max_error);
        assertEquals(-0.5, calc.calculateMono(Calculator.MonoOperatorModes.cos, 2 * Math.PI / 3), max_error);
        assertEquals(-0.5, calc.calculateMono(Calculator.MonoOperatorModes.cos, -2 * Math.PI / 3), max_error);
        assertEquals(-1.0, calc.calculateMono(Calculator.MonoOperatorModes.cos, Math.PI), max_error);
        assertEquals(-1.0, calc.calculateMono(Calculator.MonoOperatorModes.cos, -Math.PI), max_error);

        // Test sin.
        assertEquals(0.0, calc.calculateMono(Calculator.MonoOperatorModes.sin, 0.0), max_error);
        assertEquals(0.5, calc.calculateMono(Calculator.MonoOperatorModes.sin, Math.PI / 6), max_error);
        assertEquals(-0.5, calc.calculateMono(Calculator.MonoOperatorModes.sin, -Math.PI / 6), max_error);
        assertEquals(1.0, calc.calculateMono(Calculator.MonoOperatorModes.sin, Math.PI / 2), max_error);
        assertEquals(-1.0, calc.calculateMono(Calculator.MonoOperatorModes.sin, -Math.PI / 2), max_error);
        assertEquals(0.5, calc.calculateMono(Calculator.MonoOperatorModes.sin, 5 * Math.PI / 6), max_error);
        assertEquals(-0.5, calc.calculateMono(Calculator.MonoOperatorModes.sin, -5 * Math.PI / 6), max_error);
        assertEquals(0.0, calc.calculateMono(Calculator.MonoOperatorModes.sin, Math.PI), max_error);
        assertEquals(0.0, calc.calculateMono(Calculator.MonoOperatorModes.sin, -Math.PI), max_error);

        // Test tan.
        assertEquals(0.0, calc.calculateMono(Calculator.MonoOperatorModes.tan, 0.0), max_error);
        assertEquals(1.0, calc.calculateMono(Calculator.MonoOperatorModes.tan, Math.PI / 4), max_error);
        assertEquals(-1.0, calc.calculateMono(Calculator.MonoOperatorModes.tan, -Math.PI / 4), max_error);
        // JVM will throw an Error if the following instruction is executed.
        // assertTrue(Double.isInfinite(calc.calculateMono(Calculator.MonoOperatorModes.tan, Math.PI / 2)));

        // Test log.
        assertEquals(0.0, calc.calculateMono(Calculator.MonoOperatorModes.log, 1.0), max_error);
        assertEquals(1.0, calc.calculateMono(Calculator.MonoOperatorModes.log, 10.0), max_error);
        assertEquals(2.0, calc.calculateMono(Calculator.MonoOperatorModes.log, 100.0), max_error);
        assertTrue(Double.isInfinite(calc.calculateMono(Calculator.MonoOperatorModes.log, 0.0)));

        // Test rate.
        assertEquals(0.01, calc.calculateMono(Calculator.MonoOperatorModes.rate, 1.0), max_error);
        assertEquals(0.0, calc.calculateMono(Calculator.MonoOperatorModes.rate, 0.0), max_error);
        assertEquals(0.11, calc.calculateMono(Calculator.MonoOperatorModes.rate, 11.0), max_error);
        assertEquals(-0.11, calc.calculateMono(Calculator.MonoOperatorModes.rate, -11.0), max_error);
        assertEquals(1.00, calc.calculateMono(Calculator.MonoOperatorModes.rate, 100.0), max_error);

        // Test error.
        thrown.expect(Error.class);
        calc.calculateMono(Calculator.MonoOperatorModes.enumnum, 1.0);
    }
}