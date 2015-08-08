package com.github.timeu.dygraphsgwt.client.callbacks;

import com.google.gwt.core.client.js.JsFunction;

/**
 * Created by uemit.seren on 7/28/15.
 */
@FunctionalInterface
@JsFunction
public interface ReadyCallback {

    /**
     * Called when the Dygraphs instance if ready/renderd.
     * Use this callback to interact with the chart
     */
    void onReady();
}
