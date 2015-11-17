package com.github.timeu.dygraphsgwt.client.callbacks;


import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Created by uemit.seren on 7/31/15.
 */
@JsType(isNative = true,namespace = JsPackage.GLOBAL,name="Object")
public interface Area {

    /**
     * Returns the x coordinates of the area
     */
    @JsProperty
    double getX();

    /**
     * Returns the y coordinates of the area
     */
    @JsProperty double getY();

    /**
     * Returns the width of the area
     */
    @JsProperty double getW();

    /**
     * Returns the height of the area
     */
    @JsProperty double getH();
}
