package com.github.timeu.dygraphsgwt.client;


import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Created by uemit.seren on 7/30/15.
 */
@JsType(isNative = true,namespace = JsPackage.GLOBAL,name="Object")
public interface Position{

    @JsProperty
    int getX();

    @JsProperty
    int getY();
}
