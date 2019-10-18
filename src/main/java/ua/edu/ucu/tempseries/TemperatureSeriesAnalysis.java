package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    private double[] temperatureSeries;
    private static final int absolute_min = -273;

    public TemperatureSeriesAnalysis() {

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        CheckForAbsoluteMin(temperatureSeries);
        this.temperatureSeries = temperatureSeries;
    }

    public double average() {
        CheckIsSeriesNull();

        double ser_sum = 0;

        for (double t: temperatureSeries) {
            ser_sum += t;
        }

        double result = ser_sum / temperatureSeries.length;

        return result;
    }

    public double deviation() {
        CheckIsSeriesNull();

        double avg_temp = average();

        double sum = 0;
        double deviation = 0;

        for(double t: temperatureSeries) {
            deviation += Math.pow(t - avg_temp, 2);
        }

        double result = Math.sqrt(deviation/temperatureSeries.length);

        return result;
    }

    public double min() {
        CheckIsSeriesNull();
        double min_value = temperatureSeries[0];

        for (double t: temperatureSeries) {
            if (t < min_value) {
                min_value = t;
            }
        }

        return min_value;
    }

    public double max() {
        CheckIsSeriesNull();

        double max_value = temperatureSeries[0];

        for (double t: temperatureSeries) {
            if (t > max_value) {
                max_value = t;
            }
        }

        return max_value;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        CheckIsSeriesNull();
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
        CheckIsSeriesNull();
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        int cur_length = temperatureSeries.length;

        temperatureSeries = extendArray(temperatureSeries);

        for (int i = 0; i < temps.length; i++) {
            int j = i + cur_length;
            if (temperatureSeries.length < j){
                temperatureSeries = extendArray(temperatureSeries);
            }

            temperatureSeries[j] = temps[i];
        }

        return temps.length;
    }

    private double[] extendArray(double[] arr){
        double[] arr_x_2 = new double[arr.length*2];

        System.arraycopy(arr, 0, arr_x_2, 0, arr.length);

        return arr_x_2;
    }

    private void CheckIsSeriesNull(){
        if ( temperatureSeries == null || temperatureSeries.length == 0){
            throw new IllegalArgumentException("Series is empty!");
        }
    }

    private void CheckForAbsoluteMin(double[] arr){
        for (double in_t: arr) {
            if (in_t < absolute_min){
                throw new InputMismatchException();
            }
        }
    }

}
