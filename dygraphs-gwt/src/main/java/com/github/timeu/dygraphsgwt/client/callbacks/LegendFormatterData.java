package com.github.timeu.dygraphsgwt.client.callbacks;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Created by uemit.seren on 2/2/16.
 */
@JsType(isNative = true,namespace = JsPackage.GLOBAL,name="Object")
public interface LegendFormatterData {

    @JsProperty
    DygraphsJs getDygraph();

    @JsProperty
    Integer getX();

    @JsProperty(name="xHTML")
    String getXHTML();

    @JsProperty
    LegendFormatterSeries[] getSeries();
}
