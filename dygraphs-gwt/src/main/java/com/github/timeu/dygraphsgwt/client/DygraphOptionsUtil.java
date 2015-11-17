package com.github.timeu.dygraphsgwt.client;

import com.github.timeu.dygraphsgwt.client.options.SeriesOptions;
import jsinterop.annotations.JsMethod;

/**
 * Created by uemit.seren on 11/17/15.
 */
public class DygraphOptionsUtil {

    @JsMethod
    public final native static void setSeriesOptions(DygraphsOptions options,String series, SeriesOptions seriesOptions) /*-{
        options[series] = seriesOptions;
    }-*/;
}
