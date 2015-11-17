package com.github.timeu.dygraphsgwt.client.callbacks;


import com.google.gwt.dom.client.NativeEvent;
import jsinterop.annotations.JsFunction;

/**
 * Created by uemit.seren on 8/6/15.
 */
@JsFunction
@FunctionalInterface
public interface HighlightCallback {

    /**
     * When set, this callback gets called every time a new point is highlighted.
     *
     * @param event the JavaScript mousemove event
     * @param x the x-coordinate of the highlighted points
     * @param points an array of highlighted points.
     * @param row integer index of the highlighted row in the data table, starting from 0
     * @param seriesName name of the highlighted series, only present if highlightSeriesOpts is set
     */
    void onHighlight(NativeEvent event,long x,Point[] points,int row, String seriesName);
}
