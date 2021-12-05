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

    private void checkSize() {
        if (size == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
    }

    public double average() {
        checkSize();
        double averageVal = 0;
        for (double i : temperatureSeries) {
            averageVal += i;
        }
        return averageVal / size;
    }

    public double deviation() {
        checkSize();
        double deviationVal = 0;
        double averageVal = average();
        for (double i : temperatureSeries) {
            deviationVal += (i - averageVal) * (i - averageVal);
        }
        return Math.sqrt(deviationVal / size);
    }

    public double min() {
        checkSize();
        double minVal = temperatureSeries[0];
        for (int i = 1; i < size; i++) {
            if (temperatureSeries[i] < minVal) {
                minVal = temperatureSeries[i];
            }
        }
        return minVal;
    }

    public double max() {
        checkSize();
        double maxVal = temperatureSeries[0];
        for (int i = 1; i < size; i++) {
            if (temperatureSeries[i] > maxVal) {
                maxVal = temperatureSeries[i];
            }
        }
        return maxVal;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        checkSize();
        double minDistance = Math.abs(temperatureSeries[0] - tempValue);
        double closestVal = temperatureSeries[0];
        for (int i = 1; i < size; i++) {
            double distance = Math.abs(temperatureSeries[i] - tempValue);
            if (distance == minDistance && temperatureSeries[i] > closestVal) {
                closestVal = temperatureSeries[i];
            }
            if (distance < minDistance) {
                minDistance = distance;
                closestVal = temperatureSeries[i];
            }
        }
        return closestVal;
    }

    private double[] findTemps(double tempValue, boolean flag) {
        checkSize();
        int num = 0;
        if (flag) {
            for (double i : temperatureSeries) {
                if (i < tempValue) {
                    num++;
                }
            }
        } else {
            for (double i : temperatureSeries) {
                if (i > tempValue) {
                    num++;
                }
            }
        }

        double[] temps = new double[num];
        int counter = 0;
        if (flag) {
            for (double i : temperatureSeries) {
                if (i < tempValue) {
                    temps[counter] = i;
                    counter++;
                }
            }
        } else {
            for (double i : temperatureSeries) {
                if (i > tempValue) {
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
