package com.github.timeu.dygraphsgwt.client.options;

import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.google.gwt.core.client.js.JsFunction;

/**
 * Created by uemit.seren on 8/6/15.
 */
@FunctionalInterface
@JsFunction
public interface AxisLabelFormatter {
    /**
     * Function to call to format the tick values that appear along an axis. This is usually set on a per-axis basis.
     *
     * @param number Either a number (for a numeric axis) or a Date object (for a date axis)
     * @param granularity specifies how fine-grained the axis is. For date axes, this is a reference to the time granularity enumeration, defined in dygraph-tickers.js, e.g. Dygraph.WEEKLY.
     * @param opts a function which provides access to various options on the dygraph, e.g. opts('labelsKMB').
     * @param js the referenced graph
     */
    void onFormat(double number,String granularity,OptFunction opts,DygraphsJs js);
}
