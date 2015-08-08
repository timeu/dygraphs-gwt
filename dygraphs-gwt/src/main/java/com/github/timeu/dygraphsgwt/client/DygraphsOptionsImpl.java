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
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeUri;
import com.googlecode.gwt.charts.client.DataTable;

import java.util.Date;

/**
 * Created by uemit.seren on 7/28/15.
 */
public class DygraphsOptionsImpl implements DygraphsOptions {

    public String title;
    public xValueParserCallback xValueParser;
    public boolean stackedGraph;
    public AnnotationClickHandler annotationClickHandler;
    public AnnotationDbClickHandler annotationDbClickHandler;
    public AnnotationMouseOutHandler annotationMouseOutHandler;
    public AnnotationMouseOverHandler annotationMouseOverHandler;
    public int rollPeriod;
    public String legend;
    public boolean showRoller;
    public boolean customBars;
    public String ylabel;
    public int width;
    public int height;
    public DrawCallback drawCallback;
    public PointClickCallback pointClickCallback;
    public String[] labels;
    public double strokeWidth;
    public String gridLineColor;
    public double[] valueRange;
    public InteractionModel interactionModel;
    public double[] dateWindow;
    public boolean drawPoints;
    public HighlightSeriesOptions highlightSeriesOpts;
    public int highlightCircleSize;
    public ClickCallback clickCallback;
    public UnderlayCallback underlayCallback;
    public boolean animatedZooms;
    public boolean connectSeparatedPoints;
    public boolean errorBars;
    public AxesOptions axes;
    public boolean showRangeSelector;
    public String rangeSelectorPlotFillColor;
    public String rangeSelectorPlotStrokeColor;
    public int rangeSelectorHeight;
    public Properties labelsDivStyles;
    public boolean logscale;
    public int titleHeight;
    public String xlabel;
    public boolean drawAxesAtZero;
    public boolean includeZero;
    public boolean avoidMinzero;
    public double xRangePad;
    public Double yRangePad;
    public boolean labelsKMB;
    public boolean labelsKMB2;
    public boolean displayAnnotations;
    public boolean hideOverlayOnMouseOut;
    public int labelsDivWidth;
    public ZoomCallback zoomCallback;
    public String stackedGraphNaNFill;
    public String y2label;
    public HighlightCallback highlightCallback;
    public UnHighlightCallback unHighlightCallback;
    public int[] strokePattern;
    public boolean[] visibility;
    public double colorValue;
    public double colorSaturation;
    public String[] colors;
    public double fillAlpha;
    public double sigma;
    public boolean wilsonInterval;
    public double gridLineWidth;
    public double highlightSeriesBackgroundAlpha;
    public boolean showLabelsOnHighlight;
    public boolean labelsSeparateLines;
    public boolean labelsShowZeroValues;
    public int rightGap;
    public int digitsAfterDecimal;
    public int maxNumberWidth;
    public int sigFigs;
    public boolean isZoomedIgnoreProgrammaticZoom;
    public boolean labelsUTC;
    public String delimiter;
    public boolean drawGapEdgePoints;
    public String timingName;
    public int[] gridLinePattern;
    public DrawPointCallback drawHighlightPointCallback;
    public DrawPointCallback drawPointCallback;
    public String axisLabelColor;
    public int axisLabelFontSize;
    public int axisLabelWidth;
    public String axisLineColor;
    public double axisTickSize;
    public double panEdgeFranction;
    public boolean fractions;
    public boolean fillGraph;
    public int pointSize;
    public boolean stepPlot;
    public String setStrokeBorderColor;
    public double strokeBorderWidth;
    public double axisLineWidth;


    @Override
    public void setAnnotationClickHandler(AnnotationClickHandler handler) {
        this.annotationClickHandler = handler;
    }

    @Override
    public void setAnnotationDblClickHandler(AnnotationDbClickHandler handler) {
        this.annotationDbClickHandler = handler;
    }

    @Override
    public void setAnnotationMouseOutHandler(AnnotationMouseOutHandler handler) {
        this.annotationMouseOutHandler = handler;
    }

    @Override
    public void setAnnotationMouseOverHandler(AnnotationMouseOverHandler handler) {
        this.annotationMouseOverHandler = handler;
    }

    @Override
    public void setPointClickCallback(PointClickCallback callback) {
        this.pointClickCallback = callback;
    }

    @Override
    public void setZoomCallback(ZoomCallback callback) {
         this.zoomCallback = callback;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    //FIXME required until name in @JsProperty works
    public final native void setXValueParser(xValueParserCallback callback)/*-{
        this.xValueParser = callback;
    }-*/;

    @Override
    public void setStackedGraph(boolean isStacked) {
        this.stackedGraph = isStacked;
    }

    @Override
    public boolean getStackedGraph() {
        return stackedGraph;
    }

    @Override
    public void setStackedGraphNaNFill(String stackedGraphNaNFill) {
       this.stackedGraphNaNFill = stackedGraphNaNFill;
    }

    @Override
    public void setRollPeriod(int rollPeriod) {
        this.rollPeriod = rollPeriod;
    }

    @Override
    public int getRollPeriod() {
        return rollPeriod;
    }

    @Override
    public void setLegend(String legend) {
        this.legend = legend;
    }

    @Override
    public String getLegend() {
        return legend;
    }

    @Override
    public void setShowRoller(boolean showRoller) {
        this.showRoller = showRoller;
    }

    @Override
    public boolean getShowRoller() {
        return showRoller;
    }

    @Override
    public void setCustomBars(boolean customBars) {
        this.customBars = customBars;
    }

    @Override
    public boolean getCustomBars() {
        return customBars;
    }

    @Override
    public void setYlabel(String ylabel) {
        this.ylabel = ylabel;
    }

    @Override
    public String getYlabel() {
        return ylabel;
    }

    @Override
    public void setY2label(String y2label) {
       this.y2label = y2label;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setDrawCallback(DrawCallback callback) {
        this.drawCallback = callback;
    }

    @Override
    public void setHighlightCallback(HighlightCallback callback) {
       this.highlightCallback = callback;
    }

    @Override
    public void setUnhighlightCallback(UnHighlightCallback callback) {
        this.unHighlightCallback = callback;
    }

    @Override
    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    @Override
    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    @Override
    public void setStrokePattern(int[] strokePattern) {
       this.strokePattern = strokePattern;
    }

    @Override
    public void setVisiblity(boolean[] visibility) {
       this.visibility = visibility;
    }

    @Override
    public void setColorValue(double colorValue) {
       this.colorValue = colorValue;
    }

    @Override
    public void setColorSaturation(double colorSaturation) {
       this.colorSaturation = colorSaturation;
    }

    @Override
    public void setColors(String[] colors) {
       this.colors = colors;
    }

    @Override
    public void setFillAlpha(double fillAlpha) {
        this.fillAlpha = fillAlpha;
    }

    @Override
    public void setSigma(double sigma) {
        this.sigma = sigma;
    }

    @Override
    public void setWilsonInterval(boolean wilsonInterval) {
       this.wilsonInterval = wilsonInterval;
    }

    @Override
    public void setGridLineWidth(double gridLineWidth) {
       this.gridLineWidth = gridLineWidth;
    }

    @Override
    public void setHighlightSeriesBackgroundAlpha(double highlightSeriesBackgroundAlpha) {
       this.highlightSeriesBackgroundAlpha = highlightSeriesBackgroundAlpha;
    }

    @Override
    public void setShowLabelsOnHighlight(boolean showLabelsOnHighlight) {
        this.showLabelsOnHighlight = showLabelsOnHighlight;
    }

    @Override
    public void setLabelsSeparateLines(boolean labelsSeparateLines) {
        this.labelsSeparateLines  = labelsSeparateLines;
    }

    @Override
    public void setLabelsShowZeroValues(boolean labelsShowZeroValues) {
       this.labelsShowZeroValues = labelsShowZeroValues;
    }

    @Override
    public void setRightGap(int rightGap) {
        this.rightGap = rightGap;
    }

    @Override
    public void setDigitsAfterDecimal(int digitsAfterDecimal) {
        this.digitsAfterDecimal = digitsAfterDecimal;
    }

    @Override
    public void setMaxNumberWidth(int maxNumberWidth) {
        this.maxNumberWidth = maxNumberWidth;
    }

    @Override
    public void setSigFigs(int sigFigs) {
        this.sigFigs = sigFigs;
    }

    @Override
    public void setIsZoomedIgnoreProgrammaticZoom(boolean isZoomedIgnoreProgrammaticZoom) {
        this.isZoomedIgnoreProgrammaticZoom = isZoomedIgnoreProgrammaticZoom;
    }

    @Override
    public void setLabelsUTC(boolean labelsUTC) {
        this.labelsUTC = labelsUTC;
    }

    @Override
    public final native void setXAxisHeight(Integer height) /*-{
        this.xAxisheight = height;
    }-*/;

    @Override
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public void setDrawGapEdgePoints(boolean drawGapEdgePoints) {
       this.drawGapEdgePoints = drawGapEdgePoints;
    }

    @Override
    public void setTimingName(String timingName) {
        this.timingName = timingName;
    }

    @Override
    public void setGridLineColor(String gridLineColor) {
        this.gridLineColor = gridLineColor;
    }

    @Override
    public void setGridLinePattern(int[] gridLinePattern) {
        this.gridLinePattern = gridLinePattern;
    }

    @Override
    public void setValueRange(double low, double high) {
        this.setValueRange(new double[]{low, high});
    }

    @Override
    public void setValueRange(double[] valueRange) {
        this.valueRange = valueRange;
    }

    @Override
    public void setInteractionModel(InteractionModel model) {
        this.interactionModel = model;
    }

    @Override
    public void setDateWindow(double min, double max) {
        setDateWindow(new double[]{min,max});
    }

    @Override
    public void setDateWindow(double[] dateWindow) {
        this.dateWindow = dateWindow;
    }

    @Override
    public final native void setCSVFile(String csv) /*-{
        this.file = csv;
    }-*/;

    @Override
    public  void setUrlFile(SafeUri url) {
        setCSVFile(url.asString());
    }

    @Override
    public final native void setDataTableFile(DataTable data) /*-{
        this.file = data;
    }-*/;

    @Override
    public final native void setArrayFile(JsArray<JsArrayMixed> data) /*-{
        this.file = data;
    }-*/;

    @Override
    public void setDrawPoints(boolean drawPoints) {
        this.drawPoints = drawPoints;
    }

    @Override
    public void setHighlightSeriesOpts(HighlightSeriesOptions highlightSeriesOpts) {
        this.highlightSeriesOpts = highlightSeriesOpts;
    }

    @Override
    public void setHighlightCircleSize(int highlightCircleSize) {
        this.highlightCircleSize = highlightCircleSize;
    }

    @Override
    public void setClickCallback(ClickCallback callback) {
        this.clickCallback = callback;
    }

    @Override
    public void setAnimatedZooms(boolean animatedZooms) {
        this.animatedZooms = animatedZooms;
    }

    @Override
    public void setUnderlayCallback(UnderlayCallback callback) {
        this.underlayCallback = callback;
    }

    @Override
    public void setDrawHighlightPointCallback(DrawPointCallback callback) {
        this.drawHighlightPointCallback = callback;
    }

    @Override
    public void setDrawPointCallback(DrawPointCallback callback) {
        this.drawPointCallback = callback;
    }

    @Override
    public void setConnectSeparatedPoints(boolean connectSeparatedPoints) {
        this.connectSeparatedPoints = connectSeparatedPoints;
    }

    @Override
    public final native void setSeriesOptions(String series, SeriesOptions seriesOptions)/*-{
        this[series] = seriesOptions;
    }-*/;

    @Override
    public void setErrorBars(boolean errorBars) {
        this.errorBars = errorBars;
    }

    @Override
    public void setAxes(AxesOptions options) {
        this.axes = options;
    }

    @Override
    public void setShowRangeSelector(boolean showRangeSelector) {
        this.showRangeSelector = showRangeSelector;
    }

    @Override
    public void setRangeSelectorHeight(int rangeSelectorHeight) {
        this.rangeSelectorHeight = rangeSelectorHeight;
    }

    @Override
    public void setRangeSelectorPlotStrokeColor(String rangeSelectorPlotStrokeColor) {
        this.rangeSelectorPlotStrokeColor = rangeSelectorPlotStrokeColor;
    }

    @Override
    public void setRangeSelectorPlotFillColor(String rangeSelectorPlotFillColor) {
        this.rangeSelectorPlotFillColor = rangeSelectorPlotFillColor;
    }

    @Override
    public void setLabelsDivStyles(Properties styles) {
        this.labelsDivStyles = styles;
    }

    @Override
    public native final void setLabelsDiv(String labelsDiv) /*-{
        this.labelsDiv = labelsDiv;
    }-*/;

    @Override
    public native final void setLabelsDiv(Element labelsDiv) /*-{
        this.labelsDiv = labelsDiv;
    }-*/;


    @Override
    public void setLabelsDivWidth(int labelsDivWidth) {
        this.labelsDivWidth = labelsDivWidth;
    }

    @Override
    public void setLogscale(boolean logscale) {
        this.logscale = logscale;
    }

    @Override
    public void setTitleHeight(int titleHeight) {
        this.titleHeight = titleHeight;
    }

    @Override
    public void setXlabel(String xlabel) {
        this.xlabel = xlabel;

    }

    @Override
    public void setDrawAxesAtZero(boolean drawAxesAtZero) {
        this.drawAxesAtZero = drawAxesAtZero;
    }

    @Override
    public void setIncludeZero(boolean includeZero) {
        this.includeZero = includeZero;
    }

    @Override
    public void setAvoidMinZero(boolean avoidMinzero) {
        this.avoidMinzero = avoidMinzero;
    }

    @Override
    public native final void setXRangePad(double xRangePad) /*-{
        this.xRangePad = xRangePad;
    }-*/;

    @Override
    public  void setYRangePad(Double yRangePad) {
         if(this.yRangePad == null) {
             this.clearYRangePad();
         }
        else {
             this.setNativeYRangePad(yRangePad.doubleValue());
         }
    }

    @Override
    public void setLabelsKMB(boolean labelsKMB) {
        this.labelsKMB = labelsKMB;
    }

    @Override
    public void setLabelsKMG2(boolean labelsKMG2) {
        this.labelsKMB2 = labelsKMG2;
    }

    @Override
    public void setDisplayAnnotations(boolean displayAnnotations) {
        this.displayAnnotations = displayAnnotations;
    }

    @Override
    public void setHideOverlayOnMouseOut(boolean hideOverlayOnMouseOut) {
        this.hideOverlayOnMouseOut = hideOverlayOnMouseOut;
    }

    @Override
    public void setAxisLabelColor(String axisLabelColor) {
         this.axisLabelColor = axisLabelColor;
    }

    @Override
    public void setAxisLabelFontSize(int axisLabelFontSize) {
        this.axisLabelFontSize = axisLabelFontSize;
    }

    @Override
    public void setAxisLabelWidth(int axisLabelWidth) {
       this.axisLabelWidth = axisLabelWidth;
    }

    @Override
    public final native void setXAxisLabelWidth(int xAxisLabelWidth) /*-{
         this.xAxisLabelWidth = xAxisLabelWidth;
    }-*/;

    @Override
    public final native void setYAxisLabelWidth(int yAxisLabelWidth) /*-{
         this.yAxisLabelWidth = yAxisLabelWidth;
    }-*/;

    @Override
    public void setAxisLineColor(String axisLineColor) {
        this.axisLineColor = axisLineColor;
    }

    @Override
    public void setAxisLineWidth(double axisLineWidth) {
         this.axisLineWidth = axisLineWidth;
    }

    @Override
    public void setAxisTickSize(double axisTickSize) {
        this.axisTickSize = axisTickSize;
    }

    @Override
    public void setPanEdgeFraction(double panEdgeFraction) {
        this.panEdgeFranction = panEdgeFraction;
    }

    @Override
    public void setFractions(boolean fractions) {
         this.fractions = fractions;
    }

    @Override
    public final native void setYLabelWidth(double yLabelWidth) /*-{
        this.yLabelWidth = yLabelWidth;
    }-*/;

    @Override
    public final native void setXLabelHeight(double xLabelHeight) /*-{
        this.xLabelHeight = xLabelHeight;
    }-*/;

    @Override
    public void setFillGraph(boolean fillGraph) {
        this.fillGraph = fillGraph;
    }

    @Override
    public void setPointSize(int pointSize) {
        this.pointSize = pointSize;
    }

    @Override
    public void setStepPlot(boolean stepPlot) {
        this.stepPlot = stepPlot;
    }

    @Override
    public void setStrokeBorderColor(String strokeBorderColor) {
       this.setStrokeBorderColor = strokeBorderColor;
    }

    @Override
    public void setStrokeBorderWidth(double strokeBorderWidth) {
        this.strokeBorderWidth = strokeBorderWidth;
    }

    private native final void setNativeYRangePad(double value) /*-{
        this.yRangePad = value;
    }-*/;

    private native void clearYRangePad() /*-{
        this.yRangePad = null;
    }-*/;

}
