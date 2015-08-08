package com.github.timeu.dygraphsgwt.client;

import com.github.timeu.dygraphsgwt.client.callbacks.Annotation;
import com.github.timeu.dygraphsgwt.client.callbacks.ReadyCallback;
import com.github.timeu.dygraphsgwt.client.options.interactions.InteractionModel;
import com.google.gwt.core.client.js.JsProperty;
import com.google.gwt.core.client.js.JsType;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.MouseWheelEvent;

import java.util.List;

/**
 * Created by uemit.seren on 7/24/15.
 */

@JsType(prototype = "Dygraph")
public interface DygraphsJs {

    void updateOptions(DygraphsOptions options,boolean noRedraw);

    void resize();

    void ready(ReadyCallback callback);

    void setAnnotations(Annotation[] annotations);

    Annotation[] annotations();

    double[] xAxisRange();

    double[] toDomCoords(Double x,Double y,int axis);

    double[] toDataCoords(double canvasx, double canvasy);

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

    double[] yAxisRange(int axis);

    double[][] yAxisRanges();

    String[] getColors();
}
