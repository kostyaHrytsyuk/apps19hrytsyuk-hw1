package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TempSummaryStatisticsTest {

    private TempSummaryStatistics stats;

    @Before
    public void setUp() {
        this.stats = new TempSummaryStatistics(1.0, 2.0, 0.0, 10.0);
    }

    @Test
    public void testGetAvgTemp() {
        double expectedValue = 1.0;

        assertEquals(expectedValue, stats.getAvgTemp(), 0.00001);
    }

    @Test
    public void testGetDevTemp() {
        double expectedValue = 2.0;

        assertEquals(expectedValue, stats.getDevTemp(), 0.00001);
    }

    @Test
    public void testGetMinTemp() {
        double expectedValue = 0.0;
        double actualResult = stats.getMinTemp();
        assertEquals(expectedValue, actualResult, 0.00001);
    }

    @Test
    public void testGetMaxTemp() {
        double expectedValue = 10.0;

        assertEquals(expectedValue, stats.getMaxTemp(), 0.00001);
    }

}
