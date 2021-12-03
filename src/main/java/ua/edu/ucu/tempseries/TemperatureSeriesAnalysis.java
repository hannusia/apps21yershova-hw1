package ua.edu.ucu.tempseries;

import lombok.Getter;

import java.util.InputMismatchException;

@Getter
public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private int size;
    private final double absoluteZero = -273.0;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[0];
        size = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries;
        this.size = temperatureSeries.length;
    }

    private void checkSize(){
        if (size == 0){
            throw new IllegalArgumentException("Array is empty");
        }
    }

    public double average() {
        checkSize();
        double average_val = 0;
        for (double i : temperatureSeries){
            average_val += i;
        }
        return average_val / size;
    }

    public double deviation() {
        checkSize();
        double deviation_val = 0;
        double average_val = average();
        for (double i : temperatureSeries){
            deviation_val += (i - average_val) * (i - average_val);
        }
        return Math.sqrt(deviation_val / size);
    }

    public double min() {
        checkSize();
        double min_val = temperatureSeries[0];
        for (int i = 1; i < size; i++){
            if (temperatureSeries[i] < min_val){
                min_val = temperatureSeries[i];
            }
        }
        return min_val;
    }

    public double max() {
        checkSize();
        double max_val = temperatureSeries[0];
        for (int i = 1; i < size; i++){
            if (temperatureSeries[i] > max_val){
                max_val = temperatureSeries[i];
            }
        }
        return max_val;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        checkSize();
        double min_distance = Math.abs(temperatureSeries[0] - tempValue);
        double closest_val = temperatureSeries[0];
        for (int i = 1; i < size; i++){
            double distance = Math.abs(temperatureSeries[i] - tempValue);
            if (distance == min_distance && temperatureSeries[i] > closest_val){
                closest_val = temperatureSeries[i];
            }
            if (distance < min_distance){
                min_distance = distance;
                closest_val = temperatureSeries[i];
            }
        }
        return closest_val;
    }

    private double[] findTemps(double tempValue, boolean flag){
        checkSize();
        int num = 0;
        if (flag){
            for (double i : temperatureSeries){
                if (i < tempValue){
                    num++;
                }
            }
        }else{
            for (double i : temperatureSeries){
                if (i > tempValue){
                    num++;
                }
            }
        }

        double[] temps = new double[num];
        int counter = 0;
        if (flag){
            for (double i : temperatureSeries){
                if (i < tempValue){
                    temps[counter] = i;
                    counter++;
                }
            }
        }else{
            for (double i : temperatureSeries){
                if (i > tempValue){
                    temps[counter] = i;
                    counter++;
                }
            }
        }
        return temps;
    }

    public double[] findTempsLessThen(double tempValue) {
        return findTemps(tempValue, true);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        return findTemps(tempValue, false);
    }

    public TempSummaryStatistics summaryStatistics() {
        checkSize();
        TempSummaryStatistics statistics = new TempSummaryStatistics(this);
        return statistics;
    }

    public int addTemps(double... temps) {
        int newLen = temperatureSeries.length;
        if (size == 0) {
            newLen = 1;
        }
        double[] newSeries = new double[newLen];

        while (newSeries.length < size + temps.length) {
            newSeries = new double[newSeries.length * 2];

            int j = 0;
            while (j < temperatureSeries.length) {
                newSeries[j] = temperatureSeries[j];
                j++;
            }
        }

        int k = 0;
        while (k < temps.length) {
            if (temps[k] < absoluteZero) {
                throw new InputMismatchException();
            }
            newSeries[k+size] = temps[k];
            k++;
        }

        temperatureSeries = newSeries;
        size += temps.length;
        return size;
    }
}
