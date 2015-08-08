package com.github.timeu.dygraphsgwt.client.gviz;


import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.ScriptInjector;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.Selection;

/**
 * A wrapper around the Dygraph class which implements the
 * interface for a GViz (aka Google Visualization API) visualization.
 * It is designed to be a drop-in replacement for Google's AnnotatedTimeline,
 * so the documentation at
 * http://code.google.com/apis/chart/interactive/docs/gallery/annotatedtimeline.html
 * translates over directly.
 *
 * Created by uemit.seren on 8/5/15.
 */
public class GVizChart extends Widget  {


    protected final GVizChartJs gvizObj;


    public GVizChart() {
        initElement();
        gvizObj = createGVizJso();
    }

    private void initElement() {
        Element div = DOM.createDiv();
        setElement(div);
    }

    public DygraphsJs getJSO() {
        if (gvizObj == null)
            return null;
        return gvizObj.getDate_graph();
    }

    /**
     * Draws the chart
     *
     * @param data {@link DataTable}
     * @param options {@link DygraphsOptions} options
     */
    public void draw(DataTable data,DygraphsOptions options) {
        gvizObj.draw(data,options);
    }

    /**
     * Google charts compatible setSelection
     * Only row selection is supported, all points in the row will be highlighted
     * @param selection
     */
    public void setSelection(Selection[] selection) {
        if (selection == null) {
            selection = new Selection[]{};
        }
        gvizObj.setSelection(selection);
    }

    /**
     * Returns the selected datapoints
     */
    public Selection[] getSelection() {
        return gvizObj.getSelection();
    }

    protected GVizChartJs createGVizJso() {
        ScriptInjector.injectScript();
        return createNativeGVizJso(getElement());
    }

    protected final native GVizChartJs createNativeGVizJso(Element element) /*-{
        return new $wnd.Dygraph.GVizChart(container);
    }-*/;
}
