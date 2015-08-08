package com.github.timeu.dygraphsgwt.client.callbacks;

import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.js.JsFunction;

/**
 * Created by uemit.seren on 7/31/15.
 */

@FunctionalInterface
@JsFunction
public interface UnderlayCallback {

    /**
     * When set, this callback gets called before the chart is drawn.
     * It details on how to use this.
     *
     * @param canvas {@link Context2d} the canvas drawing context on which to draw
     * @param area {@link Area} An object with {x,y,w,h} properties describing the drawing area.
     * @param dygraphjs {@link DygraphsJs} the reference graph
     */
    void onUnderlay(Context2d canvas,Area area,DygraphsJs dygraphjs);
}
