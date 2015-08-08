package com.github.timeu.dygraphsgwt.client.callbacks;

import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.google.gwt.core.client.js.JsFunction;

/**
 * Created by uemit.seren on 7/29/15.
 */
@FunctionalInterface
@JsFunction
public interface DrawCallback {

    /**
     * When set, this callback gets called every time the dygraph is drawn.
     * This includes the initial draw, after zooming and repeatedly while panning.
     *
     * @param dygraphjs {@link DygraphsJs}
     * @param isInitial True if this is the initial draw, false for subsequent draws.
     */
    void onDraw(DygraphsJs dygraphjs,boolean isInitial);
}
