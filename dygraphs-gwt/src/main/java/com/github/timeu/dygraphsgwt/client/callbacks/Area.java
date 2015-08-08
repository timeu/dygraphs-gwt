package com.github.timeu.dygraphsgwt.client.callbacks;

import com.google.gwt.core.client.js.JsProperty;
import com.google.gwt.core.client.js.JsType;

/**
 * Created by uemit.seren on 7/31/15.
 */
@JsType
public interface Area {

    /**
     * Returns the x coordinates of the area
     */
    @JsProperty double getX();

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
