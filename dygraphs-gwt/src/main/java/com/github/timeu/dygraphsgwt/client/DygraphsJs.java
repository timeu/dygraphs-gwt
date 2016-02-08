package com.github.timeu.dygraphsgwt.client;

import com.github.timeu.dygraphsgwt.client.callbacks.Annotation;
import com.github.timeu.dygraphsgwt.client.callbacks.Area;
import com.github.timeu.dygraphsgwt.client.callbacks.ReadyCallback;
import com.google.gwt.dom.client.Element;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * Created by uemit.seren on 7/24/15.
 */

@JsType(isNative = true,namespace = JsPackage.GLOBAL,name="Dygraphs")
public interface DygraphsJs {

    void updateOptions(DygraphsOptions options, boolean noRedraw);

    void resize();

    void ready(ReadyCallback callback);

    void setAnnotations(Annotation[] annotations);

    Annotation[] annotations();

    double[] xAxisRange();

    double[] toDomCoords(Double x,Double y,int axis);

    double[] toDataCoords(double canvasx, double canvasy);

    double toDataXCoord(double cancasx);

    double toDataYCoord(double cancasy);

    @JsProperty
    Element getGraphDiv();

    int numRows();

    double getValue(int row, int col);

    void setSelection(int row,String seriesName,boolean locked);

    boolean isSeriesLocked();

    void clearSelection();

    int getSelection();

    String getHighlightSeries();

    double toDomXCoord(Number xStart);

    double toDomYCoord(Number yStart, int axis);

    double[] yAxisRange(int axis);

    double[][] yAxisRanges();

    String[] getColors();

    Area getArea();

    void drawGraph_();

    String[] getLabels();
}
