package com.github.timeu.dygraphsgwt.client.gviz;

import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.google.gwt.dom.client.Element;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.Selection;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Created by uemit.seren on 8/5/15.
 */
@JsType(isNative = true,namespace = "Dygraph",name="GVizChart")
class GVizChartJs {

    public GVizChartJs(Element element) {

    }

    /**
     * Creates a dygraph options and draws it.
     *
     * @param data {DataTable}
     * @param options options to pass to Dygraphs
     */
    public native void draw(DataTable data,DygraphsOptions options);

    /**
     * Google charts compatible setSelection
     * Only row selection is supported, all points in the row will be highlighted
     * @param selection
     */
    public native void setSelection(Selection[] selection);

    /**
     * Returns the selected datapoints
     */
    public native Selection[] getSelection();


    /**
     * Returns the {@link DygraphsJs} instance.
     */
    @JsProperty public native DygraphsJs getDate_graph();
}
