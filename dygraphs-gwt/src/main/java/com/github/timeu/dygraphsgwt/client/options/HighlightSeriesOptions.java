package com.github.timeu.dygraphsgwt.client.options;


import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Created by uemit.seren on 7/30/15.
 */
@JsType(isNative = true,namespace = JsPackage.GLOBAL,name="Object")
public class HighlightSeriesOptions {

    /**
     * The width of the lines connecting data points.
     * This can be used to increase the contrast or some graphs.
     * Default: 1.0
     */
    public int strokeWidth;

    /**
     * Draw a border around graph lines to make crossing lines more easily distinguishable.
     * Useful for graphs with many lines.
     * Default: null
     */
    public Integer strokeBorderWidth;

    /**
     * The size in pixels of the dot drawn over highlighted points.
     * Default: 3
     */
    public int highlightCircleSize;

}
