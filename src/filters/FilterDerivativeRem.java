package filters;

import data.DataSeries;
import functions.Function;

/**
 *
 */

public class FilterDerivativeRem extends Function {
    private static final int DEFAULT_DISTANCE_MS = 20;
    private int distance_point;

    public FilterDerivativeRem(DataSeries inputData, int timeMs) {
        super(inputData);
        double dataInterval = 1;
        if(inputData.getScaling() != null) {
            dataInterval = inputData.getScaling().getSamplingInterval();
        }
        distance_point = (int) Math.round(timeMs / (dataInterval * 1000));
        if(distance_point == 0) {
            distance_point = 1;
        }
    }

    public FilterDerivativeRem(DataSeries inputData) {
       this(inputData, DEFAULT_DISTANCE_MS);
    }

    @Override
    public int get(int index) {
        if (index < distance_point) {
            return 0;
        }
        return inputData.get(index) - inputData.get(index - distance_point);
        //return Math.abs(inputData.get(index)) - Math.abs(inputData.get(index - distance_point));
    }
}

