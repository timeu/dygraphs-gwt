package com.github.timeu.dygraphsgwt.client.callbacks;

import jsinterop.annotations.JsFunction;

/**
 * Created by uemit.seren on 2/2/16.
 */
@FunctionalInterface
@JsFunction
public interface LegendFormatterCallback {
    /**
     * Handler that is called for suppylying a custom legend.
     *
     * @param data Object of {@link LegendFormatterData}
     */
    String getFormat(LegendFormatterData data);
}
