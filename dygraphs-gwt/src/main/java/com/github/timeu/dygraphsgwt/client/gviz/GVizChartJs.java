package com.github.timeu.dygraphsgwt.client.gviz;

import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.js.JsProperty;
import com.google.gwt.core.client.js.JsType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.Selection;

/**
 * Created by uemit.seren on 8/5/15.
 */
@JsType
interface GVizChartJs {

    /**
     * Creates a dygraph options and draws it.
     *
     * @param data {DataTable}
     * @param options options to pass to Dygraphs
     */
    void draw(DataTable data,DygraphsOptions options);

    /**
     * Google charts compatible setSelection
     * Only row selection is supported, all points in the row will be highlighted
     * @param selection
     */
    void setSelection(Selection[] selection);

    /**
     * Returns the selected datapoints
     */
    Selection[] getSelection();


    /**
     * Returns the {@link DygraphsJs} instance.
     */
    @JsProperty DygraphsJs getDate_graph();
}
