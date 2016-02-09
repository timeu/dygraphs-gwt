package com.github.timeu.dygraphsgwt.client.options;


import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Created by uemit.seren on 8/3/15.
 */
@JsType(isNative = true,namespace = JsPackage.GLOBAL,name="Object")
public class AxisOptions {

    /**
     *  Whether to display gridlines in the chart.
     *  This may be set on a per-axis basis to define the visibility of each axis' grid separately.
     *  Default: true for x and y, false for y2
     */
    public boolean drawGrid;

    /**
     *  Whether to draw the specified axis.
     *  This may be set on a per-axis basis to define the visibility of each axis separately. Setting this to false also prevents axis ticks from being drawn and reclaims the space for the chart grid/lines.
     *  Default: true for x and y, false for y2
     */
    public boolean drawAxis;

    /**
     *  Show K/M/B for thousands/millions/billions on y-axis.
     *  Default: false
     */
    public boolean labelsKMB;

    /**
     *  Only valid for y and y2, has no effect on x: This option defines whether the y axes should align their ticks or if they should be independent.
     *  Possible combinations:
     *  1.) y=true, y2=false (default): y is the primary axis and the y2 ticks are aligned to the the ones of y. (only 1 grid)
     *  2.) y=false, y2=true: y2 is the primary axis and the y ticks are aligned to the the ones of y2. (only 1 grid)
     *  3.) y=true, y2=true: Both axis are independent and have their own ticks. (2 grids) 4.) y=false, y2=false: Invalid configuration causes an error.
     */
    public boolean independentTicks;


    /**
     *  A custom pattern array where the even index is a draw and odd is a space in pixels.
     *  If null then it draws a solid line.
     *  The array should have a even length as any odd lengthed array could be expressed as a smaller even length array.
     *  This is used to create dashed gridlines.
     *  Default: null
     */
    public double[] gridLinePattern;

    /**
     * Function to call to format the tick values that appear along an axis. This is usually set on a per-axis basis.
     * Default: Depends on the data type
     */
    public AxisLabelFormatter axisLabelFormatter;

    /**
     *  Number of pixels to require between each x- and y-label.
     *  Larger values will yield a sparser axis with fewer ticks.
     *  This is set on a per-axis basis.
     *  Default: 70 (x-axis) or 30 (y-axes)
     */
    public int pixelsPerLabel;


    /**
     * Function to provide a custom display format for the values displayed on mouseover.
     * This does not affect the values that appear on tick marks next to the axes.
     * To format those, see axisLabelFormatter.
     * This is usually set on a per-axis basis.
     * Default: Depends on the type of your data.
     */
    public ValueFormatter valueFormatter;

    /**
     * Width (in pixels) of the containing divs for x- and y-axis labels.
     * For the y-axis, this also controls the width of the y-axis.
     * Note that for the x-axis, this is independent from pixelsPerLabel, which controls the spacing between labels.
     * Default: 50 (y-axis), 60 (x-axis)
     */
    public int axisLabelWidth;

    /**
     * Size of the font (in pixels) to use in the axis labels, both x- and y-axis.
     * Default: 14
     */
    public int axisLabelFontSize;

    /**
     * This lets you specify an arbitrary function to generate tick marks on an axis.
     * The tick marks are an array of (value, label) pairs.
     * The built-in functions go to great lengths to choose good tick marks so, if you set this option, you'll most likely want to call one of them and modify the result.
     * See dygraph-tickers.js for an extensive discussion. This is set on a per-axis basis.
     * Default: Dygraph.dateTicker or Dygraph.numericTicks
     */
    public TickerFunction ticker;

    public static class Builder {

        private final AxisOptions options;

        public Builder() {
            options = new AxisOptions();
        }

        public Builder drawGrid(boolean drawGrid) {
            this.options.drawGrid = drawGrid;
            return this;
        }
        public Builder drawAxis(boolean drawAxis) {
            this.options.drawAxis = drawAxis;
            return this;
        }
        public Builder labelsKMB(boolean labelsKMB) {
            this.options.labelsKMB = labelsKMB;
            return this;
        }
        public Builder independentTicks(boolean independentTicks) {
            this.options.independentTicks = independentTicks;
            return this;
        }
        public Builder gridLinePattern(double[] gridLinePattern) {
            this.options.gridLinePattern = gridLinePattern;
            return this;
        }

        public Builder axisLabelFormatter(AxisLabelFormatter axisLabelFormatter) {
            this.options.axisLabelFormatter = axisLabelFormatter;
            return this;
        }

        public Builder pixelsPerLabel(int pixelsPerLabel) {
            this.options.pixelsPerLabel = pixelsPerLabel;
            return this;
        }

        public Builder valueFormatter(ValueFormatter valueFormatter) {
            this.options.valueFormatter = valueFormatter;
            return this;
        }

        public AxisOptions build() {
            return options;
        }

        public Builder axisLabelWidth(int axislabelWith) {
            this.options.axisLabelWidth = axislabelWith;
            return this;
        }

        public Builder axisLabelFontSize(int axisLabelFontSize) {
            this.options.axisLabelFontSize = axisLabelFontSize;
            return this;
        }
    }
}
