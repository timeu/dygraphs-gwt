package com.github.timeu.dygraphsgwt.client.options;

import com.google.gwt.core.client.js.JsType;

/**
 * Created by uemit.seren on 8/3/15.
 */

@JsType
public class SeriesOptions {

    /**
     * A per-series color definition.
     * Used in conjunction with, and overrides, the colors option.
     */
    public String color;

    /**
     * The width of the lines connecting data points.
     * This can be used to increase the contrast or some graphs.
     * Default: 1.0
     */
    public double strokeWidth;

    /**
     *  Draw a small dot at each point, in addition to a line going through the point.
     *  This makes the individual data points easier to see, but can increase visual clutter in the chart.
     *  The small dot can be replaced with a custom rendering by supplying a drawPointCallback.
     *  Default: false
     */
    public boolean drawPoints;

    /**
     * The size of the dot to draw on each point in pixels (see drawPoints).
     * A dot is always drawn when a point is "isolated", i.e. there is a missing point on either side of it.
     * This also controls the size of those dots.
     * Default: 1
     */
    public double pointSize;

    /**
     *  The size in pixels of the dot drawn over highlighted points.
     *  Default: 3
     */
    public double highlightCircleSize;

    /**
     * Mark this series for inclusion in the range selector.
     * The mini plot curve will be an average of all such series.
     * If this is not specified for any series, the default behavior is to average all the series.
     * Setting it for one series will result in that series being charted alone in the range selector.
     * Default: false;
     */
    public boolean showInRangeSelector;
}
