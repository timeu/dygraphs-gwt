package com.github.timeu.dygraphsgwt.client.callbacks;

import com.google.gwt.core.client.js.JsFunction;
import com.google.gwt.dom.client.NativeEvent;

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
     * @param point {@link Point}
     */
    void onClick(NativeEvent event,double x,Point point);
}
