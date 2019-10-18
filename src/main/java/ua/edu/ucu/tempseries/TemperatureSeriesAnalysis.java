package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    public int getTemperatureSeriesLength() {
        return temperatureSeries.length;
    }

    private double[] temperatureSeries;
    private static final int absolute_min = -273;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        addTemps(temperatureSeries);
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
        double[] arrX2;
        if (arr == null) {
            arrX2 = new double[] {Double.NaN};
        } else {
            arrX2 = new double[arr.length*2];
            Arrays.fill(arrX2, Double.NaN);

            System.arraycopy(arr, 0, arrX2, 0, arr.length);
        }

        return arrX2;
    }

    private void checkForAbsoluteMin(double[] arr) {
        for (double incomeT: arr) {
            if (incomeT < absolute_min) {
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
