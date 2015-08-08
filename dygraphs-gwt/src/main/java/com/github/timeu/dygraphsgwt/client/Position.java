package com.github.timeu.dygraphsgwt.client;

import com.google.gwt.core.client.js.JsProperty;
import com.google.gwt.core.client.js.JsType;

/**
 * Created by uemit.seren on 7/30/15.
 */
@JsType
public interface Position {

    @JsProperty
    int getX();

    @JsProperty
    int getY();
}
