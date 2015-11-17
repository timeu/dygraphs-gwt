package com.github.timeu.dygraphsgwt.client.options.interactions;


import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Created by uemit.seren on 7/30/15.
 */
@JsType(isNative = true,namespace = JsPackage.GLOBAL,name="Object")
public class InteractionModel {

    /**
     *
     */
    public MouseDownHandler mousedown;

    /**
     *
     */
    public MouseMoveHandler mousemove;

    /**
     *
     */
    public MouseUpHandler mouseup;

    /**
     *
     */
    public MouseOutHandler mouseout;

    /**
     *
     */
    public ClickHandler click;

    /**
     *
     */
    public MouseWheelHandler mousewheel;

    /**
     *
     */
    public DblClickHandler dblclick;
}
