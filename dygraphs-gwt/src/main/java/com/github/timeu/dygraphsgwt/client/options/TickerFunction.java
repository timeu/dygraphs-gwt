package com.github.timeu.dygraphsgwt.client.options;

import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.core.client.js.JsFunction;

/**
 * Created by uemit.seren on 8/6/15.
 */

@JsFunction
@FunctionalInterface
public interface TickerFunction {
    JsArrayMixed onTick(double a, double b, int pixels,OptFunction opts,DygraphsJs dygraphjs,JsArrayMixed vals);
}
