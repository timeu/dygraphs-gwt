package com.github.timeu.dygraphsgwt.client.callbacks;


import jsinterop.annotations.JsFunction;

/**
 * Created by uemit.seren on 8/6/15.
 */
@JsFunction
@FunctionalInterface
public interface ZoomCallback {
    /**
     *  A function to call when the zoom window is changed (either by zooming in or out).
     *
     * @param minDate milliseconds since epoch
     * @param maxDate milliseconds since epoch.
     * @param yRange is an array of [bottom, top] pairs, one for each y-axis.
     */
    void onZoom(long minDate,long maxDate,double[] yRange);
}
