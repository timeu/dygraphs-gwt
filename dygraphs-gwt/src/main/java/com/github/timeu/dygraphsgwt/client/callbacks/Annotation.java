package com.github.timeu.dygraphsgwt.client.callbacks;

import com.google.gwt.core.client.js.JsProperty;
import com.google.gwt.core.client.js.JsType;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;

/**
 * Dygraphs lets you add annotations (markers) to individual points on your
 * chart. These markers have a short bit of text or an icon that's displayed
 * over the chart, plus a longer description that appears when you hover over them.
 *
 * Created by uemit.seren on 7/29/15.
 */

@JsType
public class Annotation {

    /**
     * The name of the series to which the annotated point belongs.
     *  @Required
     */
    public String series;

    /**
     *  The x value of the point.
     *  This should be the same as the value you specified in your CSV file, e.g. "2010-09-13".
     *  @Required
     */
    public String x;

    /**
     *  Specify in place of shortText to mark the annotation with an image rather than text.
     *  If you specify this, you must specify width and height.
     */
    public String icon;

    /**
     *  Width (in pixels) of the annotation flag or icon.
     */
    public Double width;

    /**
     * Height (in pixels) of the annotation flag or icon.
     */
    public Double height;

    /**
     * Height of the tick mark (in pixels) connecting the point to its flag or icon.
     */
    public Integer tickHeight;

    /**
     * Text that will appear on the annotation's flag.
     */
    public String shortText;

    /**
     * A longer description of the annotation which will appear when the user hovers over it.
     */
    public String text;

    /**
     * Handler that is executed when the user clicks on the annotation.
     */
    public AnnotationClickHandler clickHandler;

    /**
     * Handler that is executed when the user moves the mouse over an annotation
     */
    public AnnotationMouseOverHandler mouseOverHandler;

    /**
     * Handler that is executed when the user moves the mouse out of an annotation
     */
    public AnnotationMouseOutHandler mouseOutHandler;

    /**
     * Handler that is executed when the user double clicks on the annotation.
     */
    public AnnotationDbClickHandler dblClickHandler;


    /**
     * The DOM Div element of the annotation
     */
    public DivElement div;

    /**
     * If true, attach annotations to the x-axis, rather than to actual points.
     */
    public boolean attachAtBottom;

    /**
     *  The parsed x-Value
     */
    public Double xval;

    public Annotation(String series) {
        this.series = series;
    }

    public Annotation(String series, String x) {
        this.series = series;
        this.x = x;
    }

    /**
     * CSS class to use for styling the annotation.
     */
    public final native void setCssClas(String cssClass)/*-{
        this.cssClass=cssClass;
    }-*/;
}
