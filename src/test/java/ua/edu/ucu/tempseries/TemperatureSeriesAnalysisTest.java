package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

public class TemperatureSeriesAnalysisTest {
    private final TemperatureSeriesAnalysis seriesAnalysisOneEl1, seriesAnalysisOneEl2,
            seriesAnalysisEmpty, seriesAnalysisManyEl1, seriesAnalysisManyEl2, seriesAnalysisManyEl3;
    public TemperatureSeriesAnalysisTest(){
        double[] tempSeries1 = {15.6};
        this.seriesAnalysisOneEl1 = new TemperatureSeriesAnalysis(tempSeries1);

        double[] tempSeries2 = {-34.54};
        this.seriesAnalysisOneEl2 = new TemperatureSeriesAnalysis(tempSeries2);

        this.seriesAnalysisEmpty = new TemperatureSeriesAnalysis();

        double[] tempSeries3 = {1.2, 1.2, 1.2, 1.2, 1.2, 1.2, 1.2, 1.2};
        this.seriesAnalysisManyEl1 = new TemperatureSeriesAnalysis(tempSeries3);

        double[] tempSeries4 = {56, -56, 76.9, -76.9, 31, -31, -16.9, 16.9};
        this.seriesAnalysisManyEl2 = new TemperatureSeriesAnalysis(tempSeries4);

        double[] tempSeries5 = {3.0, -5.0, 1.0, 5.0};
        this.seriesAnalysisManyEl3 = new TemperatureSeriesAnalysis(tempSeries5);
    }

    //-----------------TEST AVERAGE-----------------
    @Test
    public void testAverageWithOneElementArray() {
        double expResult1 = 15.6;
        double actualResult1 = seriesAnalysisOneEl1.average();

        double expResult2 = -34.54;
        double actualResult2 = seriesAnalysisOneEl2.average();

        assertEquals(expResult1, actualResult1, 0.00001);
        assertEquals(expResult2, actualResult2, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        seriesAnalysisEmpty.average();
    }

    @Test
    public void testAverage() {
        double expResult1 = 1.2;
        double actualResult1 = seriesAnalysisManyEl1.average();

        double expResult2 = 0.0;
        double actualResult2 = seriesAnalysisManyEl2.average();

        double expResult3 = 1.0;
        double actualResult3 = seriesAnalysisManyEl3.average();

        assertEquals(expResult1, actualResult1, 0.00001);
        assertEquals(expResult2, actualResult2, 0.00001);
        assertEquals(expResult3, actualResult3, 0.00001);
    }

    //-----------------TEST DEVIATION-----------------
    @Test
    public void testDeviationWithOneElementArray() {
        double expResult1 = 0.0;
        double actualResult1 = seriesAnalysisOneEl1.deviation();

        double expResult2 = 0.0;
        double actualResult2 = seriesAnalysisOneEl2.deviation();

        assertEquals(expResult1, actualResult1, 0.00001);
        assertEquals(expResult2, actualResult2, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray(){
        seriesAnalysisEmpty.deviation();
    }

    @Test
    public void testDeviation() {
        double expResult1 = 0.0;
        double actualResult1 = seriesAnalysisManyEl1.deviation();

        double expResult2 = Math.sqrt(14.0);
        double actualResult2 = seriesAnalysisManyEl3.deviation();

        assertEquals(expResult1, actualResult1, 0.00001);
        assertEquals(expResult2, actualResult2, 0.00001);
    }

    //-----------------TEST MIN-----------------
    @Test
    public void testMinWithOneElementArray(){
        double expResult = 15.6;
        double actualResult = seriesAnalysisOneEl1.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArray(){
        seriesAnalysisEmpty.min();
    }

    @Test
    public void testMin() {
        double expResult1 = 1.2;
        double actualResult1 = seriesAnalysisManyEl1.min();

        double expResult2 = -76.9;
        double actualResult2 = seriesAnalysisManyEl2.min();

        double expResult3 = -5.0;
        double actualResult3 = seriesAnalysisManyEl3.min();

        assertEquals(expResult1, actualResult1, 0.00001);
        assertEquals(expResult2, actualResult2, 0.00001);
        assertEquals(expResult3, actualResult3, 0.00001);
    }

    //-----------------TEST MAX-----------------
    @Test
    public void testMaxWithOneElementArray(){
        double expResult = -34.54;
        double actualResult = seriesAnalysisOneEl2.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithEmptyArray(){
        seriesAnalysisEmpty.max();
    }

    @Test
    public void testMax() {
        double expResult1 = 1.2;
        double actualResult1 = seriesAnalysisManyEl1.max();

        double expResult2 = 76.9;
        double actualResult2 = seriesAnalysisManyEl2.max();

        double expResult3 = 5.0;
        double actualResult3 = seriesAnalysisManyEl3.max();

        assertEquals(expResult1, actualResult1, 0.00001);
        assertEquals(expResult2, actualResult2, 0.00001);
        assertEquals(expResult3, actualResult3, 0.00001);
    }

    //-----------------TEST CLOSEST-----------------
    @Test
    public void testFindTempClosestToZeroWithOneElementArray(){
        double expResult = 15.6;
        double actualResult = seriesAnalysisOneEl1.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValueOneWithElementArray(){
        double expResult = -34.54;
        double actualResult = seriesAnalysisOneEl2.findTempClosestToValue(17.67);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToZeroWithEmptyArray(){
        seriesAnalysisEmpty.findTempClosestToZero();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToValueWithEmptyArray(){
        seriesAnalysisEmpty.findTempClosestToValue(15);
    }

    @Test
    public void testFindTempClosestToZero() {
        double expResult = 16.99;
        double actualResult = seriesAnalysisManyEl2.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValue() {
        double expResult = -5.0;
        double actualResult = seriesAnalysisManyEl3.findTempClosestToValue(-7.34);

        assertEquals(expResult, actualResult, 0.00001);
    }

    //-----------------TEST LESS-----------------
    @Test
    public void testFindTempsLessThenWithOneElementArray() {
        double[] expResult = {};
        double[] actualResult = seriesAnalysisOneEl1.findTempsLessThen(-4.5);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsLessThenWithEmptyArray(){
        seriesAnalysisEmpty.findTempsLessThen(3.6);
    }

    @Test
    public void testFindTempsLessThen(){
        double[] expResult = {-56, -76.9, -31, -16.99};
        double[] actualResult = seriesAnalysisManyEl2.findTempsLessThen(0.0);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    //-----------------TEST GREATER-----------------
    @Test
    public void testFindTempsGreaterThenValueWithOneElementArray() {
        double[] expResult = {15.6};
        double[] actualResult = seriesAnalysisOneEl1.findTempsGreaterThen(-2.0);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsGreaterThenWithWithEmptyArray() {
        seriesAnalysisEmpty.findTempsGreaterThen(1.0);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] expResult = {3.0, 5.0};
        double[] actualResult = seriesAnalysisManyEl3.findTempsGreaterThen(2.0);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }


    //-----------------TEST STATISTICS-----------------
    @Test
    public void testSummaryStatisticsWithOneElementArray() {
        double expAvg = -34.54;
        double expDev = 0.0;
        double expMin = -34.54;
        double expMax = -34.54;

        TempSummaryStatistics statistics = seriesAnalysisOneEl2.summaryStatistics();

        assertEquals(statistics.getAvgTemp(), expAvg, 0.00001);
        assertEquals(statistics.getDevTemp(), expDev, 0.00001);
        assertEquals(statistics.getMinTemp(), expMin, 0.00001);
        assertEquals(statistics.getMaxTemp(), expMax, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSummaryStatisticsWithEmptyArray() {
        seriesAnalysisEmpty.summaryStatistics();
    }

    @Test
    public void testSummaryStatistics() {

        double expAvg = 1.0;
        double expDev = Math.sqrt(14.0);
        double expMin = -5.0;
        double expMax = 5.0;

        TempSummaryStatistics statistics = seriesAnalysisManyEl3.summaryStatistics();

        assertEquals(statistics.getAvgTemp(), expAvg, 0.00001);
        assertEquals(statistics.getDevTemp(), expDev, 0.00001);
        assertEquals(statistics.getMinTemp(), expMin, 0.00001);
        assertEquals(statistics.getMaxTemp(), expMax, 0.00001);
    }


    //-----------------TEST ADD TEMPS-----------------
    @Test
    public void testAddTempsWithOneElementArray() {
        double[] newSeries = {1.0, 4.0};
        seriesAnalysisOneEl1.addTemps(newSeries);
        double[] expResult = {15.6, 1.0, 4.0, 0.0};
        double[] actualResult = seriesAnalysisOneEl1.getTemperatureSeries();

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAddTempsWithEmptyArray() {
        double[] newSeries = {1.0, 4.0};
        seriesAnalysisEmpty.addTemps(newSeries);
        double[] expResult = {1.0, 4.0};
        double[] actualResult = seriesAnalysisEmpty.getTemperatureSeries();

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAddTemps() {
        double[] newSeries = {1.0, 4.0};
        seriesAnalysisManyEl3.addTemps(newSeries);
        double[] expResult = {3.0, -5.0, 1.0, 5.0, 1.0, 4.0, 0.0, 0.0};
        double[] actualResult = seriesAnalysisManyEl3.getTemperatureSeries();

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

}


