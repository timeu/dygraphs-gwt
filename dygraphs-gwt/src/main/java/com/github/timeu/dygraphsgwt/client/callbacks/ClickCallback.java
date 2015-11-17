package com.github.timeu.dygraphsgwt.client.callbacks;

import com.google.gwt.dom.client.NativeEvent;
import jsinterop.annotations.JsFunction;

/**
 * Created by uemit.seren on 7/30/15.
 */
@FunctionalInterface
@JsFunction
public interface ClickCallback {

    /**
     * Handler that is called when user clicks on the chart.
     *
     * @param event {@link NativeEvent}
     * @param x the x-value
     * @param points Array of {@link Point}
     */
    void onClick(NativeEvent event,double x,Point[] points);
}
