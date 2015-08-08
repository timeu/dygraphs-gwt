package com.github.timeu.dygraphsgwt.client.callbacks;

import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.js.JsFunction;

/**
 * Created by uemit.seren on 8/6/15.
 */
@JsFunction
@FunctionalInterface
public interface DrawPointCallback {

    /**
     * Draw a custom item
     *
     * @param dygraphs the reference graph
     * @param seriesName the name of the series
     * @param context the canvas to draw on
     * @param cx center x coordinate
     * @param cy center y coordinate
     * @param color series color
     * @param pointSize the radius of the image
     * @param idx the row-index of the point in the data
     */
    void onDraw(DygraphsJs dygraphs,String seriesName,Context2d context,double cx,double cy,String color,int pointSize,int idx);
}
