package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    private double[] temperatureSeries;
    private static final int absolute_min = -273;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        checkForAbsoluteMin(temperatureSeries);
        this.temperatureSeries = temperatureSeries;
    }

    public double average() {
        checkIsSeriesNull();

        double avgSum = 0;

        for (double t: temperatureSeries) {
            avgSum += t;
        }

        return avgSum / temperatureSeries.length;
    }

    public double deviation() {
        checkIsSeriesNull();

        double avgTemp = average();

        double deviation = 0;

        for (double t: temperatureSeries) {
            deviation += (t - avgTemp)*(t - avgTemp);
        }

        return Math.sqrt(deviation/temperatureSeries.length);
    }

    public double min() {
        checkIsSeriesNull();
        double minValue = temperatureSeries[0];

        for (double t: temperatureSeries) {
            if (t < minValue) {
                minValue = t;
            }
        }

        return minValue;
    }

    public double max() {
        checkIsSeriesNull();

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
        checkIsSeriesNull();
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
        checkIsSeriesNull();
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        int curLength = temperatureSeries.length;

        temperatureSeries = extendArray(temperatureSeries);

        for (int i = 0; i < temps.length; i++) {
            int j = i + curLength;
            if (temperatureSeries.length < j) {
                temperatureSeries = extendArray(temperatureSeries);
            }

            temperatureSeries[j] = temps[i];
        }

        return temps.length;
    }

    private double[] extendArray(double[] arr) {
        double[] arrX2 = new double[arr.length*2];

        System.arraycopy(arr, 0, arrX2, 0, arr.length);

        return arrX2;
    }

    private void checkForAbsoluteMin(double[] arr) {
        for (double incomeT: arr) {
            if (incomeT < absolute_min) {
                throw new InputMismatchException();
            }
        }
    }

    private void checkIsSeriesNull() {
        if (temperatureSeries == null || temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Series is empty!");
        }
    }

}
