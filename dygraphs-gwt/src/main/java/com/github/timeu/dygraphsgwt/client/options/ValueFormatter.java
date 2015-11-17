package com.github.timeu.dygraphsgwt.client.options;

import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import jsinterop.annotations.JsFunction;


/**
 * Created by uemit.seren on 8/6/15.
 */
@JsFunction
@FunctionalInterface
public interface ValueFormatter {
    /**
     * Function to provide a custom display format for the values displayed on mouseover.
     * This does not affect the values that appear on tick marks next to the axes.
     * To format those, see axisLabelFormatter.
     * This is usually set on a per-axis basis.
     *
     * @param value The value to be formatted. This is always a number. For date axes, it's millis since epoch. You can call new Date(millis) to get a Date object.
     * @param opts This is a function you can call to access various options (e.g. opts('labelsKMB')). It returns per-axis values for the option when available.
     * @param seriesName The name of the series from which the point came, e.g. 'X', 'Y', 'A', etc.
     * @param dygraphjs The dygraph object for which the formatting is being done
     * @param row The row of the data from which this point comes. g.getValue(row, 0) will return the x-value for this point.
     * @param col The column of the data from which this point comes. g.getValue(row, col) will return the original y-value for this point. This can be used to get the full confidence interval for the point, or access un-rolled values for the point.
     */
    void onValueFormatter(long value,OptFunction opts,String seriesName,DygraphsJs dygraphjs,int row,int col);
}
