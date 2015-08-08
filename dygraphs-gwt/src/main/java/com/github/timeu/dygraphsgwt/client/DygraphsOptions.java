package com.github.timeu.dygraphsgwt.client;

import com.github.timeu.dygraphsgwt.client.callbacks.AnnotationClickHandler;
import com.github.timeu.dygraphsgwt.client.callbacks.AnnotationDbClickHandler;
import com.github.timeu.dygraphsgwt.client.callbacks.AnnotationMouseOutHandler;
import com.github.timeu.dygraphsgwt.client.callbacks.AnnotationMouseOverHandler;
import com.github.timeu.dygraphsgwt.client.callbacks.ClickCallback;
import com.github.timeu.dygraphsgwt.client.callbacks.DataFunction;
import com.github.timeu.dygraphsgwt.client.callbacks.DrawCallback;
import com.github.timeu.dygraphsgwt.client.callbacks.DrawPointCallback;
import com.github.timeu.dygraphsgwt.client.callbacks.HighlightCallback;
import com.github.timeu.dygraphsgwt.client.callbacks.PointClickCallback;
import com.github.timeu.dygraphsgwt.client.callbacks.UnHighlightCallback;
import com.github.timeu.dygraphsgwt.client.callbacks.UnderlayCallback;
import com.github.timeu.dygraphsgwt.client.callbacks.ZoomCallback;
import com.github.timeu.dygraphsgwt.client.callbacks.xValueParserCallback;
import com.github.timeu.dygraphsgwt.client.options.AxesOptions;
import com.github.timeu.dygraphsgwt.client.options.AxisOptions;
import com.github.timeu.dygraphsgwt.client.options.HighlightSeriesOptions;
import com.github.timeu.dygraphsgwt.client.options.Properties;
import com.github.timeu.dygraphsgwt.client.options.SeriesOptions;
import com.github.timeu.dygraphsgwt.client.options.interactions.InteractionModel;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.core.client.js.JsNoExport;
import com.google.gwt.core.client.js.JsProperty;
import com.google.gwt.core.client.js.JsType;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeUri;
import com.googlecode.gwt.charts.client.DataTable;

import java.util.Date;

/**
 * Created by uemit.seren on 7/24/15.
 */
@JsType
public interface DygraphsOptions {



    enum SHOW_LEGEND {onmouseover, always, follow}


    /**
     * If provided, this function is called whenever the user clicks on an annotation.
     *
     * @param handler (Default: null);
     */
    @JsProperty
    void setAnnotationClickHandler(AnnotationClickHandler handler);

    /**
     * If provided, this function is called whenever the user double-clicks on an annotation.
     *
     * @param handler (Default: null);
     */
    @JsProperty
    void setAnnotationDblClickHandler(AnnotationDbClickHandler handler);

    /**
     * If provided, this function is called whenever the user mouses out of an annotation.
     *
     * @param handler (Default: null);
     */
    @JsProperty
    void setAnnotationMouseOutHandler(AnnotationMouseOutHandler handler);

    /**
     * If provided, this function is called whenever the user mouses over an annotation.
     *
     * @param handler (Default: null);
     */
    @JsProperty
    void setAnnotationMouseOverHandler(AnnotationMouseOverHandler handler);


    /**
     * Text to display above the chart.
     * You can supply any HTML for this value, not just text.
     * If you wish to style it using CSS, use the 'dygraph-label' or 'dygraph-title' classes.
     *
     * @param title (Default:null)
     */
    @JsProperty
    void setTitle(String title);

    @JsProperty
    String getTitle();


    /**
     * A function which parses x-values (i.e. the dependent series).
     * Must return a number, even when the values are dates.
     * In this case, millis since epoch are used. This is used primarily for parsing CSV data. *=Dygraphs is slightly more accepting in the dates which it will parse. See code for details.
     *
     * @param callback
     */
    void setXValueParser(xValueParserCallback callback);

    /**
     * If set, stack series on top of one another rather than drawing them independently.
     * The first series specified in the input data will wind up on top of the chart and the last will be on bottom. NaN values are drawn as white areas without a line on top, see stackedGraphNaNFill for details.
     *
     * @param isStacked (Default:false)
     */
    @JsProperty
    void setStackedGraph(boolean isStacked);

    @JsProperty
    boolean getStackedGraph();


    /**
     * Controls handling of NaN values inside a stacked graph.
     * NaN values are interpolated/extended for stacking purposes, but the actual point value remains NaN in the legend display.
     * Valid option values are "all" (interpolate internally, repeat leftmost and rightmost value as needed), "inside" (interpolate internally only, use zero outside leftmost and rightmost value), and "none" (treat NaN as zero everywhere).
     *
     * @param stackedGraphNaNFill (Default: all)
     */
    @JsProperty void setStackedGraphNaNFill(String stackedGraphNaNFill);

    /**
     * Number of days over which to average data.
     *
     * @param rollPeriod (Default:1)
     */
    @JsProperty
    void setRollPeriod(int rollPeriod);

    @JsProperty
    int getRollPeriod();

    /**
     * When to display the legend.
     * By default, it only appears when a user mouses over the chart.
     * Set it to "always" to always display a legend of some sort.
     *
     * @param legend (Default:SHOW_LEGEND.onmouseover)
     */
    @JsProperty
    void setLegend(String legend);

    @JsProperty
    String getLegend();


    /**
     * If the rolling average period text box should be shown.
     *
     * @param showRoller (Default:false)
     */
    @JsProperty void setShowRoller(boolean showRoller);
    @JsProperty boolean getShowRoller();

    /**
     * When set, parse each CSV cell as "low;middle;high".
     * Error bars will be drawn for each point between low and high, with the series itself going through middle.
     *
     * @param customBars (Default:false)
     */
    @JsProperty void setCustomBars(boolean customBars);
    @JsProperty boolean getCustomBars();


    /**
     * Text to display to the left of the chart's y-axis.
     * You can supply any HTML for this value, not just text.
     * If you wish to style it using CSS, use the 'dygraph-label' or 'dygraph-ylabel' classes.
     * The text will be rotated 90 degrees by default, so CSS rules may behave in unintuitive ways.
     * No additional space is set aside for a y-axis label.
     * If you need more space, increase the width of the y-axis tick labels using the yAxisLabelWidth option.
     * If you need a wider div for the y-axis label, either style it that way with CSS (but remember that it's rotated, so width is controlled by the 'height' property) or set the yLabelWidth option.
     *
     * @param ylabel (Default:null)
     */
    @JsProperty void setYlabel(String ylabel);
    @JsProperty String getYlabel();


    /**
     * Text to display to the right of the chart's secondary y-axis.
     * This label is only displayed if a secondary y-axis is present.
     * See <a href="http://dygraphs.com/tests/two-axes.html">this test</a> for an example of how to do this.
     * The comments for the 'ylabel' option generally apply here as well.
     * This label gets a 'dygraph-y2label' instead of a 'dygraph-ylabel' class.
     *
     * @param y2label (Default:null)
     */
    @JsProperty void setY2label(String y2label);

    /**
     * Width, in pixels, of the chart.
     * If the container div has been explicitly sized, this will be ignored.
     *
     * @param width (Default:480)
     */
    @JsProperty void setWidth(int width);
    @JsProperty int getWidth();


    /**
     * Height, in pixels, of the chart.
     * If the container div has been explicitly sized, this will be ignored.
     *
     * @param height (Default:320)
     */
    @JsProperty void setHeight(int height);
    @JsProperty int getHeight();




    /**
     * A name for each data series, including the independent (X) series.
     * For CSV files and DataTable objections, this is determined by context.
     * For raw data, this must be specified.
     * If it is not, default values are supplied and a warning is logged.
     *
     * @param labels (Default:["X",Y1","Y2"])
     */
    @JsProperty void setLabels(String[] labels);


    /**
     * The color of the gridlines.
     *
     * @param gridLineColor (Default:rgb(128,128,128))
     */
    @JsProperty void setGridLineColor(String gridLineColor);

    /**
     * A custom pattern array where the even index is a draw and odd is a space in pixels.
     * If null then it draws a solid line.
     * The array should have a even length as any odd lengthed array could be expressed as a smaller even length array.
     * This is used to create dashed gridlines.
     *
     * @param gridLinePattern (Default: null)
     */
    @JsProperty void setGridLinePattern(int[] gridLinePattern);

    /**
     * Explicitly set the vertical range of the graph to [low, high]. This may be set on a per-axis basis to define each y-axis separately.
     *
     * @param low Minimum value of the y-axis
     * @Param high Maximum value oft he y-axis
     */
    void setValueRange(double low, double high);

    /**
     * Explicitly set the vertical range of the graph to [low, high]. This may be set on a per-axis basis to define each y-axis separately.
     *
     * @param valueRange Array of two numbers
     */
    @JsProperty void setValueRange(double[] valueRange);

    /**
     * Sets the interaction model
     *
     * @param model
     */
    @JsProperty void setInteractionModel(InteractionModel model);

    /**
     * Initially zoom in on a section of the graph.
     * Is of the form [earliest, latest], where earliest/latest are milliseconds since epoch.
     * If the data for the x-axis is numeric, the values in dateWindow must also be numbers.
     *
     * @param min Minimum value of the window
     * @param max Maximum value of the window
     */
    void setDateWindow(double min, double max);


    /**
     * Initially zoom in on a section of the graph.
     * Is of the form [earliest, latest], where earliest/latest are milliseconds since epoch.
     * If the data for the x-axis is numeric, the values in dateWindow must also be numbers.
     *
     * @param dateWindow  Array of two numbers
     */
    @JsProperty void setDateWindow(double[] dateWindow);


    /**
     * Sets the data being displayed in the chart.
     * This can only be set when calling updateOptions; it cannot be set from the constructor.
     * For a full description of valid data formats, see the <a href="http://dygraphs.com/data.html">Data Formats</a> page.
     *
     * @param csv CSV content (Default:set when constructed)
     */
    void setCSVFile(String csv);

    /**
     * Sets the data being displayed in the chart.
     * This can only be set when calling updateOptions; it cannot be set from the constructor.
     * For a full description of valid data formats, see the <a href="http://dygraphs.com/data.html">Data Formats</a> page.
     *
     * @param url URL to a CSV (Default:set when constructed)
     */
    void setUrlFile(SafeUri url);

    /**
     * Sets the data being displayed in the chart.
     * This can only be set when calling updateOptions; it cannot be set from the constructor.
     * For a full description of valid data formats, see the <a href="http://dygraphs.com/data.html">Data Formats</a> page.
     *
     * @param data Google Visualization DataTable (Default:set when constructed)
     */
    void setDataTableFile(DataTable data);

    /**
     * Sets the data being displayed in the chart.
     * This can only be set when calling updateOptions; it cannot be set from the constructor.
     * For a full description of valid data formats, see the <a href="http://dygraphs.com/data.html">Data Formats</a> page.
     *
     * @param data Array of numbers (Default:set when constructed)
     */
    void setArrayFile(JsArray<JsArrayMixed> data);

    /**
     * Draw a small dot at each point, in addition to a line going through the point.
     * This makes the individual data points easier to see, but can increase visual clutter in the chart.
     * The small dot can be replaced with a custom rendering by supplying a drawPointCallback.
     *
     * @param drawPoints (Default:false)
     */
    @JsProperty void setDrawPoints(boolean drawPoints);

    /**
     * When set, the options from this object are applied to the timeseries closest to the mouse pointer for interactive highlighting.
     * See also 'highlightCallback'. Example: highlightSeriesOpts: { strokeWidth: 3 }.
     *
     * @param highlightSeriesOpts (Default:null)
     */
    @JsProperty void setHighlightSeriesOpts(HighlightSeriesOptions highlightSeriesOpts);

    /**
     * The size in pixels of the dot drawn over highlighted points.
     *
     * @param highlightCircleSize int (default: 3)
     */
    @JsProperty void setHighlightCircleSize(int highlightCircleSize);


    /**
     * CALLBACKS
     */

    /**
     * A function to call when the canvas is clicked.
     *
     * @param callback (default:null)
     */
    @JsProperty void setClickCallback(ClickCallback callback);

    /**
     * When set, this callback gets called every time the dygraph is drawn. This includes the initial draw, after zooming and repeatedly while panning.
     *
     * @param callback (Default: null);
     */
    @JsProperty void setDrawCallback(DrawCallback callback);

    /**
     * When set, this callback gets called every time a new point is highlighted.
     *
     * @param callback (Default: null)
     */
    @JsProperty void setHighlightCallback(HighlightCallback callback);

    /**
     * When set, this callback gets called every time the user stops highlighting any point by mousing out of the graph.
     *
     * @param callback (Default: null)
     */
    @JsProperty void setUnhighlightCallback(UnHighlightCallback callback);

    /**
     * A function to call when a data point is clicked. and the point that was clicked.
     *
     * @param callback (Default: null);
     */
    @JsProperty
    void setPointClickCallback(PointClickCallback callback);

    /**
     * A function to call when the zoom window is changed (either by zooming in or out).
     *
     * @param callback (Default: null);
     */
    @JsProperty
    void setZoomCallback(ZoomCallback callback);

    /**
     * When set, this callback gets called before the chart is drawn. It details on how to use this.
     *
     * @param callback (default: null)
     */
    @JsProperty void setUnderlayCallback(UnderlayCallback callback);

    /**
     * Draw a custom item when a point is highlighted.
     * Default is a small dot matching the series color.
     * This method should constrain drawing to within pointSize pixels from (cx, cy) Also see drawPointCallback
     *
     * @param callback (default: null)
     */
    @JsProperty void setDrawHighlightPointCallback(DrawPointCallback callback);


    /**
     * Draw a custom item when drawPoints is enabled.
     * Default is a small dot matching the series color.
     * This method should constrain drawing to within pointSize pixels from (cx, cy). Also see drawHighlightPointCallback
     *
     * @param callback (default: null)
     */
    @JsProperty void setDrawPointCallback(DrawPointCallback callback);



    /**
     * Set this option to animate the transition between zoom windows.
     * Applies to programmatic and interactive zooms.
     * Note that if you also set a drawCallback, it will be called several times on each zoom.
     * If you set a zoomCallback, it will only be called after the animation is complete.
     *
     * @param animatedZooms (Default:false)
     */
    @JsProperty void setAnimatedZooms(boolean animatedZooms);



    /**
     * Usually, when Dygraphs encounters a missing value in a data series, it interprets this as a gap and draws it as such.
     * If, instead, the missing values represents an x-value for which only a different series has data, then you'll want to connect the dots by setting this to true.
     * To explicitly include a gap with this option set, use a value of NaN.
     *
     * @param connectSeparatedPoints (Default:false)
     */
    @JsProperty void setConnectSeparatedPoints(boolean connectSeparatedPoints);

    /**
     *  Defines per-series options.
     *  Its keys match the y-axis label names, and the values are dictionaries themselves that contain options specific to that series.
     *  When this option is missing, it falls back on the old-style of per-series options comingled with global options.
     *
     * @param series Name of the series
     * @param seriesOptions Options for the series
     */
    void setSeriesOptions(String series, SeriesOptions seriesOptions);

    /**
     *  Does the data contain standard deviations? Setting this to true alters the input format (see above).
     *
     * @param errorBars (Default: false)
     */
    @JsProperty void setErrorBars(boolean errorBars);


    /**
     *  Defines per-axis options. Valid keys are 'x', 'y' and 'y2'.
     *  Only some options may be set on a per-axis basis.
     *  If an option may be set in this way, it will be noted on this page.
     *  See also documentation on per-series and per-axis options.
     *
     * @param options (Default: null)
     */
    @JsProperty void setAxes(AxesOptions options);

    /**
     * Show or hide the range selector widget.
     *
     * @param showRangeSelector (Default:false)
     */
    @JsProperty void setShowRangeSelector(boolean showRangeSelector);


    /**
     * Height, in pixels, of the range selector widget.
     * This option can only be specified at Dygraph creation time.
     *
     * @param rangeSelectorHeight (Default:40)
     */
    @JsProperty void setRangeSelectorHeight(int rangeSelectorHeight);


    /**
     * The range selector mini plot stroke color.
     * This can be of the form "#AABBCC" or "rgb(255,100,200)" or "yellow".
     * You can also specify null or "" to turn off stroke.
     *
     * @param rangeSelectorPlotStrokeColor (Default:#808FAB)
     */
    @JsProperty void setRangeSelectorPlotStrokeColor(String rangeSelectorPlotStrokeColor);

    /**
     * The range selector mini plot fill color.
     * This can be of the form "#AABBCC" or "rgb(255,100,200)" or "yellow".
     * You can also specify null or "" to turn off fill.
     *
     * @param rangeSelectorPlotFillColor (Default:#A7B1C4)
     */
    @JsProperty void setRangeSelectorPlotFillColor(String rangeSelectorPlotFillColor);



    /**
     * Additional styles to apply to the currently-highlighted points div.
     * For example, { 'fontWeight': 'bold' } will make the labels bold.
     * In general, it is better to use CSS to style the .dygraph-legend class than to use this property.
     *
     * @param styles (Default: null)
     */
    @JsProperty void setLabelsDivStyles(Properties styles);


    /**
     * Show data labels in an external div, rather than on the graph.
     * This value can either be a div element or a div id.
     *
     * @param labelsDiv (Default:null)
     */
    @JsNoExport void setLabelsDiv(String labelsDiv);

    /**
     * Show data labels in an external div, rather than on the graph.
     * This value can either be a div element or a div id.
     *
     * @param labelsDiv (Default:null)
     */
    @JsNoExport  void setLabelsDiv(Element labelsDiv);

    /**
     * Width (in pixels) of the div which shows information on the currently-highlighted points.
     *
     * @param labelsDivWidth (Default:250)
     */
    @JsProperty void setLabelsDivWidth(int labelsDivWidth);

    /**
     * When set for a y-axis, the graph shows that axis in log scale.
     * Any values less than or equal to zero are not displayed. Not compatible with showZero, and ignores connectSeparatedPoints.
     * Also, showing log scale with valueRanges that are less than zero will result in an unviewable graph.
     *
     * @param logscale (Default:false)
     */
    @JsProperty void setLogscale(boolean logscale);

    /**
     * Height of the chart title, in pixels.
     * This also controls the default font size of the title.
     * If you style the title on your own, this controls how much space is set aside above the chart for the title's div.
     *
     * @param titleHeight (Default:18)
     */
    @JsProperty void setTitleHeight(int titleHeight);

    /**
     * Text to display below the chart's x-axis.
     * You can supply any HTML for this value, not just text.
     * If you wish to style it using CSS, use the 'dygraph-label' or 'dygraph-xlabel' classes.
     *
     * @param xlabel (Default:null)
     */
    @JsProperty void setXlabel(String xlabel);


    /**
     * When set, draw the X axis at the Y=0 position and the Y axis at the X=0 position if those positions are inside the graph's visible area.
     * Otherwise, draw the axes at the bottom or left graph edge as usual.
     *
     * @param drawAxesAtZero (default: true)
     */
    @JsProperty void setDrawAxesAtZero(boolean drawAxesAtZero);

    /**
     * Usually, dygraphs will use the range of the data plus some padding to set the range of the y-axis.
     * If this option is set, the y-axis will always include zero, typically as the lowest value.
     * This can be used to avoid exaggerating the variance in the data
     *
     * @param includeZero (Default:false)
     */
    @JsProperty void setIncludeZero(boolean includeZero);

    /**
     * When set, the heuristic that fixes the Y axis at zero for a data set with the minimum Y value of zero is disabled.
     * This is particularly useful for data sets that contain many zero values, especially for step plots which may otherwise have lines not visible running along the bottom axis.
     *
     * @param avoidMinzero (Default:false)
     */
    @Deprecated
    @JsProperty void setAvoidMinZero(boolean avoidMinzero);


    /**
     *  Add the specified amount of extra space (in pixels) around the X-axis value range to ensure points at the edges remain visible.
     *
     * @param xRangePad (default: 0)
     */
    void setXRangePad(double xRangePad);

    /**
     *  If set, add the specified amount of extra space (in pixels) around the Y-axis value range to ensure points at the edges remain visible.
     *  If unset, use the traditional Y padding algorithm.
     *
     * @param yRangePad (default: null)
     */
    void setYRangePad(Double yRangePad);

    /**
     * Show K/M/B for thousands/millions/billions on y-axis.
     *
     * @param labelsKMB (Default:false)
     */
    @JsProperty void setLabelsKMB(boolean labelsKMB);

    /**
     * Show k/M/G for kilo/Mega/Giga on y-axis.
     * This is different than labelsKMB in that it uses base 2, not 10.
     *
     * @param labelsKMG2 (Default:false)
     */
    @JsProperty void setLabelsKMG2(boolean labelsKMG2);

    /**
     * Causes string columns following a data series to be interpreted as annotations on points in that series.
     * This is the same format used by Google's AnnotatedTimeLine chart.
     *
     * @param displayAnnotations (Default:false)
     */
    @JsProperty void setDisplayAnnotations(boolean displayAnnotations);

    /**
     * Whether to hide the legend when the mouse leaves the chart area.
     *
     * @param hideOverlayOnMouseOut (Default:true)
     */
    @JsProperty void setHideOverlayOnMouseOut(boolean hideOverlayOnMouseOut);


    /**
     * Color for x- and y-axis labels. This is a CSS color string.
     *
     * @param axisLabelColor (Default:black)
     */
    @JsProperty void setAxisLabelColor(String axisLabelColor);

    /**
     * Size of the font (in pixels) to use in the axis labels, both x- and y-axis.
     *
     * @param axisLabelFontSize (Default:14)
     */
    @JsProperty void setAxisLabelFontSize(int axisLabelFontSize);

    /**
     * Width (in pixels) of the containing divs for x- and y-axis labels.
     *
     * @param axisLabelWidth (Default:50)
     */
    @JsProperty void setAxisLabelWidth(int axisLabelWidth);


    /**
     * Width, in pixels, of the x-axis labels.
     *
     * @param xAxisLabelWidth (Default:50)
     */
    @JsProperty void setXAxisLabelWidth(int xAxisLabelWidth);

    /**
     * Width, in pixels, of the y-axis labels. This also affects the amount of space available for a y-axis chart label.
     *
     * @param yAxisLabelWidth (Default:50)
     */
    @JsProperty void setYAxisLabelWidth(int yAxisLabelWidth);

    /**
     * Color of the x- and y-axis lines.
     * Accepts any value which the HTML canvas strokeStyle attribute understands, e.g. 'black' or 'rgb(0, 100, 255)'.
     *
     * @param axisLineColor (Default:black)
     */
    @JsProperty void setAxisLineColor(String axisLineColor);

    /**
     * Thickness (in pixels) of the x- and y-axis lines.
     *
     * @param axisLineWidth (Default:0.3)
     */
    @JsProperty void setAxisLineWidth(double axisLineWidth);
    /**
     * The size of the line to display next to each tick mark on x- or y-axes.
     *
     * @param axisTickSize (Default:3.0)
     */
    @JsProperty void setAxisTickSize(double axisTickSize);


    /**
     * A value representing the farthest a graph may be panned, in percent of the display.
     * For example, a value of 0.1 means that the graph can only be panned 10% pased the edges of the displayed values. null means no bounds.
     *
     * @param panEdgeFraction (Default:null)
     */
    @JsProperty void setPanEdgeFraction(double panEdgeFraction);

    /**
     * When set, attempt to parse each cell in the CSV file as "a/b", where a and b are integers.
     * The ratio will be plotted.
     * This allows computation of Wilson confidence intervals (see below).
     *
     * @param fractions (Default:false)
     */
    @JsProperty void setFractions(boolean fractions);

    /**
     * Width of the div which contains the y-axis label.
     * Since the y-axis label appears rotated 90 degrees, this actually affects the height of its div.
     *
     * @param yLabelWidth (Default:18)
     */
    void setYLabelWidth(double yLabelWidth);

    /**
     * Height of the x-axis label, in pixels.
     * This also controls the default font size of the x-axis label.
     * If you style the label on your own, this controls how much space is set aside below the chart for the x-axis label's div.
     *
     * @param xLabelHeight (Default:18)
     */
    void setXLabelHeight(double xLabelHeight);

    /**
     * Should the area underneath the graph be filled?
     * This option is not compatible with error bars.
     *
     * @param fillGraph (Default:false)
     */
    @JsProperty void setFillGraph(boolean fillGraph);

    /**
     * The size of the dot to draw on each point in pixels (see drawPoints).
     * A dot is always drawn when a point is "isolated", i.e. there is a missing point on either side of it.
     * This also controls the size of those dots.
     *
     * @param pointSize (Default:1)
     */
    @JsProperty void setPointSize(int pointSize);

    /**
     * When set, display the graph as a step plot instead of a line plot.
     *
     * @param stepPlot (Default:false)
     */
    @JsProperty void setStepPlot(boolean stepPlot);

    /**
     * Color for the line border used if strokeBorderWidth is set.
     *
     * @param strokeBorderColor (Default:white)
     */
    @JsProperty void setStrokeBorderColor(String strokeBorderColor);

    /**
     * Draw a border around graph lines to make crossing lines more easily distinguishable.
     * Useful for graphs with many lines.
     *
     * @param strokeBorderWidth (Default:null)
     */
    @JsProperty void setStrokeBorderWidth(double strokeBorderWidth);


    /**
     * The width of the lines connecting data points.
     * This can be used to increase the contrast or some graphs.
     *
     * @param strokeWidth (Default:1.0)
     */
    @JsProperty void setStrokeWidth(double strokeWidth);


    /**
     * A custom pattern array where the even index is a draw and odd is a space in pixels.
     * If null then it draws a solid line.
     * The array should have a even length as any odd lengthed array could be expressed as a smaller even length array.
     * This is used to create dashed lines.

     * @param strokePattern (default: null)
     */
    @JsProperty void setStrokePattern(int[] strokePattern);

    /**
     * Which series should initially be visible?
     * Once the Dygraph has been constructed, you can access and modify the visibility of each series using the visibility and setVisibility methods.
     *
     * @param visibility Array of booleans (Default: [true, true, ...])
     */
    @JsProperty void setVisiblity(boolean[] visibility);

    /**
     * If colors is not specified, value of the data series colors, as in hue/saturation/value. (0.0-1.0, default 0.5)
     *
     * @param colorValue (Default:0.5)
     */
    @JsProperty void setColorValue(double colorValue);

    /**
     * If colors is not specified, saturation of the automatically-generated data series colors.
     *
     * @param colorSaturation (Default:1.0)
     */
    @JsProperty void setColorSaturation(double colorSaturation);

    /**
     * List of colors for the data series. These can be of the form "#AABBCC" or "rgb(255,100,200)" or "yellow", etc.
     * If not specified, equally-spaced points around a color wheel are used.
     *
     * @param colors (Default:see description)
     */
    @JsProperty void setColors(String[] colors);

    /**
     * Error bars (or custom bars) for each series are drawn in the same color as the series, but with partial transparency.
     * This sets the transparency.
     * A value of 0.0 means that the error bars will not be drawn, whereas a value of 1.0 means that the error bars will be as dark as the line for the series itself.
     * This can be used to produce chart lines whose thickness varies at each point.
     *
     * @param fillAlpha (Default:0.15)
     */
    @JsProperty void setFillAlpha(double fillAlpha);

    /**
     * When errorBars is set, shade this many standard deviations above/below each point.
     *
     * @param sigma (Default:2.0)
     */
    @JsProperty void setSigma(double sigma);

    /**
     * Use in conjunction with the "fractions" option.
     * Instead of plotting +/- N standard deviations, dygraphs will compute a Wilson confidence interval and plot that.
     * This has more reasonable behavior for ratios close to 0 or 1.
     *
     * @param wilsonInterval (Default:true)
     */
    @JsProperty void setWilsonInterval(boolean wilsonInterval);

    /**
     * Thickness (in pixels) of the gridlines drawn under the chart.
     * The vertical/horizontal gridlines can be turned off entirely by using the drawXGrid and drawYGrid options.
     *
     * @param gridLineWidth (Default:0.3)
     */
    @JsProperty void setGridLineWidth(double gridLineWidth);

    /**
     * Fade the background while highlighting series.
     * 1=fully visible background (disable fading), 0=hiddden background (show highlighted series only).
     *
     * @param highlightSeriesBackgroundAlpha (Default:0.5)
     */
    @JsProperty void setHighlightSeriesBackgroundAlpha(double highlightSeriesBackgroundAlpha);

    /**
     * Whether to show the legend upon mouseover.
     *
     * @param showLabelsOnHighlight (Default:true)
     */
    @JsProperty void setShowLabelsOnHighlight(boolean showLabelsOnHighlight);



    /**
     * Put <br/> between lines in the label string.
     * Often used in conjunction with labelsDiv.
     *
     * @param labelsSeparateLines (Default:false)
     */
    @JsProperty void setLabelsSeparateLines(boolean labelsSeparateLines);

    /**
     * Show zero value labels in the labelsDiv.
     *
     * @param labelsShowZeroValues (Default:true)
     */
    @JsProperty void setLabelsShowZeroValues(boolean labelsShowZeroValues);

    /**
     * Number of pixels to leave blank at the right edge of the Dygraph.
     * This makes it easier to highlight the right-most data point.
     *
     * @param rightGap (Default:5)
     */
    @JsProperty void setRightGap(int rightGap);


    /**
     * Unless it's run in scientific mode (see the sigFigs option), dygraphs displays numbers with digitsAfterDecimal digits after the decimal point.
     * Trailing zeros are not displayed, so with a value of 2 you'll get '0', '0.1', '0.12', '123.45' but not '123.456' (it will be rounded to '123.46').
     * Numbers with absolute value less than 0.1^digitsAfterDecimal (i.e. those which would show up as '0.00') will be displayed in scientific notation.
     *
     * @param digitsAfterDecimal (Default:2)
     */
    @JsProperty void setDigitsAfterDecimal(int digitsAfterDecimal);


    /**
     * When displaying numbers in normal (not scientific) mode, large numbers will be displayed with many trailing zeros (e.g. 100000000 instead of 1e9).
     * This can lead to unwieldy y-axis labels.
     * If there are more than maxNumberWidth digits to the left of the decimal in a number, dygraphs will switch to scientific notation, even when not operating in scientific mode.
     * If you'd like to see all those digits, set this to something large, like 20 or 30.
     *
     * @param maxNumberWidth (Default:6)
     */
    @JsProperty void setMaxNumberWidth(int maxNumberWidth);

    /**
     * By default, dygraphs displays numbers with a fixed number of digits after the decimal point.
     * If you'd prefer to have a fixed number of significant figures, set this option to that number of sig figs.
     * A value of 2, for instance, would cause 1 to be display as 1.0 and 1234 to be displayed as 1.23e+3.
     *
     * @param sigFigs (Default:null)
     */
    @JsProperty void setSigFigs(int sigFigs);


    /**
     * When this option is passed to updateOptions() along with either the dateWindow or valueRange options, the zoom flags are not changed to reflect a zoomed state.
     * This is primarily useful for when the display area of a chart is changed programmatically and also where manual zooming is allowed and use is made of the isZoomed method to determine this.
     *
     * @param isZoomedIgnoreProgrammaticZoom (Default:false)
     */
    @JsProperty void setIsZoomedIgnoreProgrammaticZoom(boolean isZoomedIgnoreProgrammaticZoom);

    /**
     * Show date/time labels according to UTC (instead of local time).
     *
     * @param labelsUTC (default: false)
     */
    @JsProperty void setLabelsUTC(boolean labelsUTC);

    /**
     * Height, in pixels, of the x-axis.
     * If not set explicitly, this is computed based on axisLabelFontSize and axisTickSize.
     *
     * @param height (default: null)
     */
    void setXAxisHeight(Integer height);

    /**
     * The delimiter to look for when separating fields of a CSV file.
     * Setting this to a tab is not usually necessary, since tab-delimited data is auto-detected.
     *
     * @param delimiter (default: ',')
     */
    @JsProperty void setDelimiter(String delimiter);

    /**
     * Draw points at the edges of gaps in the data. This improves visibility of small data segments or other data irregularities.
     *
     * @param drawGapEdgePoints (Default: false)
     */
    @JsProperty void setDrawGapEdgePoints(boolean drawGapEdgePoints);


    /**
     * Set this option to log timing information. The value of the option will be logged along with the timimg,
     * so that you can distinguish multiple dygraphs on the same page.
     *
     * @param timingName (Default: null)
     */
    @JsProperty void setTimingName(String timingName);



}
