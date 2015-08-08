package com.github.timeu.dygraphsgwt.client.options;

import com.google.gwt.core.client.js.JsFunction;

/**
 * Created by uemit.seren on 8/6/15.
 */
@JsFunction
@FunctionalInterface
public interface OptFunction {
    /**
     * This is a function you can call to access various options (e.g. opts('labelsKMB')). It returns per-axis values for the option when available.
     *
     * @param opt Option
     * @return value of option
     */
    Object opt(String opt);
}
