package com.github.timeu.dygraphsgwt.client.callbacks;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Created by uemit.seren on 2/2/16.
 */
@JsType(isNative = true,namespace = JsPackage.GLOBAL,name="Object")
public interface LegendFormatterSeries {

    @JsProperty
    String getDashHTML();

    @JsProperty
    String getLabel();

    @JsProperty
    String getLabelHTML();

    @JsProperty(name="isVisible")
    boolean isVisible();

    @JsProperty
    String getColor();

    @JsProperty
    Integer getY();

    @JsProperty(name="yHTML")
    String getYHTML();

    @JsProperty(name="isHighlighted")
    boolean isHighlighted();
}
