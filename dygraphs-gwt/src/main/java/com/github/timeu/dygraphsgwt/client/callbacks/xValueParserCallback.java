package com.github.timeu.dygraphsgwt.client.callbacks;


import jsinterop.annotations.JsFunction;

/**
 * Created by uemit.seren on 7/28/15.
 */
@FunctionalInterface
@JsFunction
public interface xValueParserCallback {
    /**
     * A function which parses x-values (i.e. the dependent series).
     * Must return a number, even when the values are dates.
     * In this case, millis since epoch are used. This is used primarily for parsing CSV data. *=Dygraphs is slightly more accepting in the dates which it will parse. See code for details.
     * Default: parseFloat() or Date.parse()*
     *
     * @param value the x-value
     * @return the parsed number
     */
    Number parseXValue(String value);
}
