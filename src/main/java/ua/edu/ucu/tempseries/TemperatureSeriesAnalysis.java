package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    private static final int ABSOLUTE_MIN = -273;
    private double[] temperatureSeries;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        addTemps(temperatureSeries);
    }

    public int getTemperatureSeriesLength() {
        return temperatureSeries.length;
    }

    public double average() {
        checkIsSeriesNull(temperatureSeries);

        double avgSum = 0;

        for (double t: temperatureSeries) {
            avgSum += t;
        }

        return avgSum / temperatureSeries.length;
    }

    public double deviation() {
        checkIsSeriesNull(temperatureSeries);

        double avgTemp = average();

        double deviation = 0;

        for (double t: temperatureSeries) {
            deviation += (t - avgTemp)*(t - avgTemp);
        }

        return Math.sqrt(deviation/temperatureSeries.length);
    }

    public double min() {
        checkIsSeriesNull(temperatureSeries);
        double minValue = temperatureSeries[0];

        for (double t: temperatureSeries) {
            if (t < minValue) {
                minValue = t;
            }
        }

        return minValue;
    }

    public double max() {
        checkIsSeriesNull(temperatureSeries);

        double maxValue = temperatureSeries[0];

        for (double t: temperatureSeries) {
            if (t > maxValue) {
                maxValue = t;
            }
        }

        return maxValue;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        checkIsSeriesNull(temperatureSeries);
        double closest = temperatureSeries[0];
        double differenceFromValue = Math.abs(tempValue - closest);
        for (double t: temperatureSeries) {
            double check = Math.abs(tempValue - t);
            if (check < differenceFromValue) {
                closest = t;
                differenceFromValue = check;
            }
        }

        return closest;
    }

    public double[] findTempsLessThan(double tempValue) {
        double[] result = new double[1];
        int j = 0;
        for (double t : temperatureSeries) {
            if (t < tempValue) {
                if (result.length < j + 1) {
                    result = extendArray(result);
                }
                result[j] = t;
                j++;
            }
        }

        return result;
    }

    public double[] findTempsGreaterThan(double tempValue) {
        double[] result = new double[1];
        int j = 0;
        for (double t : temperatureSeries) {
            if (t > tempValue) {
                if (result.length < j + 1) {
                    result = extendArray(result);
                }
                result[j] = t;
                j++;
            }
        }

        return result;
    }

    public TempSummaryStatistics summaryStatistics() {
        checkIsSeriesNull(temperatureSeries);
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        checkIsSeriesNull(temps);
        checkForAbsoluteMin(temps);
        int j;

        if (temperatureSeries == null) {
            j = 0;
        } else {
            j = Arrays.binarySearch(temperatureSeries, Double.NaN);
            if (j < 0) {
                j = Math.abs(j)-1;
            }
        }

        temperatureSeries = extendArray(temperatureSeries);
        for (double temp : temps) {
            if (temperatureSeries.length <= j) {
                temperatureSeries = extendArray(temperatureSeries);
            }

            temperatureSeries[j] = temp;
            j++;
        }

        return temperatureSeries.length;
    }

    private double[] extendArray(double[] arr) {
        double[] arrX;
        if (arr == null) {
            arrX = new double[] {Double.NaN};
        } else {
            arrX = new double[arr.length*2];
            Arrays.fill(arrX, Double.NaN);

            System.arraycopy(arr, 0, arrX, 0, arr.length);
        }

        return arrX;
    }

    private void checkForAbsoluteMin(double[] arr) {
        for (double incomeT: arr) {
            if (incomeT < ABSOLUTE_MIN) {
                throw new InputMismatchException();
            }
        }
    }

    private void checkIsSeriesNull(double[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Series is empty!");
        }
    }

}
