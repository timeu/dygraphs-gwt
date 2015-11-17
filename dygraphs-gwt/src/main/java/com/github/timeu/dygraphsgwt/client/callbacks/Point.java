package com.github.timeu.dygraphsgwt.client.callbacks;


import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Some callbacks take a point argument.
 *
 * Created by uemit.seren on 7/29/15.
 */
@JsType(isNative = true,namespace = JsPackage.GLOBAL,name="Object")
public interface Point {

    /**
     * The {@link Annotation} if there is one
     */
    @JsProperty
    Annotation getAnnotation();

    /**
     * The canvas coordinates at which the point is drawn.
     */
    @JsProperty double getCanvasx();

    /**
     * The canvas coordinates at which the point is drawn.
     */
    @JsProperty double getCanvasy();

    /**
     * The row number of the point in the data set
     */
    @JsProperty int getIdx();

    /**
     * The name of the data series to which the point belongs
     */
    @JsProperty String getName();

    /**
     * xval scaled between 0 and 1
     */
    @JsProperty double getX();

    /**
     * yval scaled between 0 and 1
     */
    @JsProperty double getY();

    /**
     * The data coordinates of the point (with dates/times as millis since epoch)
     */
    @JsProperty double getXval();

    /**
     * The data coordinates of the point (with dates/times as millis since epoch)
     */
    @JsProperty double getYval();

}
