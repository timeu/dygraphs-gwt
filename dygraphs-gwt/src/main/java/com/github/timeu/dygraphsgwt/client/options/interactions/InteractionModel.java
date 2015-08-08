package com.github.timeu.dygraphsgwt.client.options.interactions;

import com.google.gwt.core.client.js.JsType;

/**
 * Created by uemit.seren on 7/30/15.
 */
@JsType
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
