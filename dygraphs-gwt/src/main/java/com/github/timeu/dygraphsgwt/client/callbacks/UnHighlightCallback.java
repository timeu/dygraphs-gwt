package com.github.timeu.dygraphsgwt.client.callbacks;

import com.google.gwt.core.client.js.JsFunction;
import com.google.gwt.dom.client.NativeEvent;

/**
 * Created by uemit.seren on 8/6/15.
 */
@JsFunction
@FunctionalInterface
public interface UnHighlightCallback {
    /**
     * When set, this callback gets called every time the user stops highlighting any point by mousing out of the graph.
     *
     * @param event the mouse event
     */
    void onUnhighlight(NativeEvent event);
}
