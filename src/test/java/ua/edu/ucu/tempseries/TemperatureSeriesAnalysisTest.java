package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {
    private TemperatureSeriesAnalysis temperatureSeriesAnalysis;
    private double[] emptyTemperatureSeries = {};
    private double[] oneElementTemperatureSeries = {-1.0};

    @Before
    public void setUp() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        this.temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
    }

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(oneElementTemperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(emptyTemperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double expResult = 1.0;

        double actualResult = temperatureSeriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test
    public void testDeviationWithTwoElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0, 9.0 };
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 5;

        // call tested method
        double actualResult = seriesAnalysis.deviation();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(emptyTemperatureSeries);

        // expect exception here
        seriesAnalysis.deviation();
    }

    @Test
    public void testDeviation() {
        double expResult = 3.7416573867739;

        double actualResult = temperatureSeriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMinWithOneElementArray() {
        // setup input data and expected result
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(oneElementTemperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.min();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(emptyTemperatureSeries);

        // expect exception here
        seriesAnalysis.deviation();
    }

    @Test
    public void testMin() {
        double expResult = -5.0;

        double actualResult = temperatureSeriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMaxWithOneElementArray() {
        // setup input data and expected result
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(oneElementTemperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.max();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(emptyTemperatureSeries);

        // expect exception here
        seriesAnalysis.max();
    }

    @Test
    public void testMax() {
        double expResult = 5.0;

        double actualResult = temperatureSeriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValueWithOneElementArray() {
        // setup input data and expected result
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(oneElementTemperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.findTempClosestToValue(5);

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToValueWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(emptyTemperatureSeries);

        // expect exception here
        seriesAnalysis.max();
    }

    @Test
    public void testFindTempClosestToValue() {
        double expResult = 3.0;

        double actualResult = temperatureSeriesAnalysis.findTempClosestToValue(2.7);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero() {
        double expResult = 1.0;

        double actualResult = temperatureSeriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempLessThanWithOneElementArray() {
        // setup input data and expected result
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(oneElementTemperatureSeries);
        double[] expResult = new double[1];

        // call tested method
        double[] actualResult = seriesAnalysis.findTempsLessThan(-2);

        // compare expected result with actual result
        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempLessThanWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(emptyTemperatureSeries);

        // expect exception here
        assertArrayEquals(new double[1], seriesAnalysis.findTempsLessThan(42), 0.0001);
    }

    @Test
    public void testFindTempLessThan() {
        double[] expResult = new double[] {-5.0, 1.0};

        double[] actualResult = temperatureSeriesAnalysis.findTempsLessThan(2.7);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempGreaterThanWithOneElementArray() {
        // setup input data and expected result
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(oneElementTemperatureSeries);
        double[] expResult = new double[] {-1.0};

        // call tested method
        double[] actualResult = seriesAnalysis.findTempsGreaterThan(-2);

        // compare expected result with actual result
        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempGreaterThanWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(emptyTemperatureSeries);

        // expect exception here
        assertArrayEquals(new double[1], seriesAnalysis.findTempsGreaterThan(42), 0.0001);
    }

    @Test
    public void testFindTempGreaterThan() {
        double[] expResult = new double[] {-5.0, 1.0};

        double[] actualResult = temperatureSeriesAnalysis.findTempsLessThan(2.7);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTempsWithAbsoluteMin() {
        double[] newTemps = new double[] {1, 1, 2, -420, 5, 8, 13, 21, 34, 55};
        // expect exception here
        temperatureSeriesAnalysis.addTemps(newTemps);
    }

    @Test
    public void testAddTemps() {
        double[] newTemps = new double[] {1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
        int currentSum = newTemps.length + temperatureSeriesAnalysis.getTemperatureSeriesLength();
        int expResult = (int) Math.pow(2, Math.ceil(Math.log(currentSum)/Math.log(2)));
        // expect exception here
        int actualResult = temperatureSeriesAnalysis.addTemps(newTemps);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testEmptyConstructor() {
        TemperatureSeriesAnalysis empt = new TemperatureSeriesAnalysis();

        assertTrue(empt instanceof TemperatureSeriesAnalysis);
    }

    @Test(expected = InputMismatchException.class)
    public void testConstructorWithWrongParameters() {
        double[] inputData = {2.0, -700, 13.4};
        TemperatureSeriesAnalysis seriesWithParameters = new TemperatureSeriesAnalysis(inputData);

        double expectedSeriesLength = 4;

        assertEquals(expectedSeriesLength, seriesWithParameters.getTemperatureSeriesLength(), 0.0001);
    }

    @Test
    public void testConstructorWithParameters() {
        double[] inputData = {2.0, -70, 13.4};
        TemperatureSeriesAnalysis seriesWithParameters = new TemperatureSeriesAnalysis(inputData);

        double expectedSeriesLength = 4;

        assertEquals(expectedSeriesLength, seriesWithParameters.getTemperatureSeriesLength(), 0.0001);
    }



    @Test(expected = IllegalArgumentException.class)
    public void testSummaryStatisticsWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(emptyTemperatureSeries);

        seriesAnalysis.summaryStatistics();
    }

    @Test
    public void testSummaryStatistics() {
        assertTrue(temperatureSeriesAnalysis.summaryStatistics() instanceof TempSummaryStatistics);
    }
}
