package com.github.timeu.dygraphsgwt.client.options.interactions;

import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.core.client.js.JsProperty;
import com.google.gwt.core.client.js.JsType;
import com.google.gwt.dom.client.NativeEvent;

/**
 * Created by uemit.seren on 7/30/15.
 */

@JsType
public interface InteractionContext {

    /**
     * @return left distance of the canvas in DOM coordinates
     */
    @JsProperty double getPx();

    /**
     *
     * @return top distance of the canvas in DOM coordinates
     */
    @JsProperty double getPy();

    /**
     * Tracks whether the mouse is down right now
     *
     * @return true if zooming
     */
    @JsProperty boolean getIsZooming();

    /**
     * Tracks whether the mouse is panning
     *
     * @return true if panning
     */
    @JsProperty boolean getIsPanning();

    /**
     * If it is panning, whether it is 1 or 2-dimensional
     *
     * @return true if it is 2 dimensional
     */
    @JsProperty boolean getIs2DPan();

    /**
     * Disable zooming out if panning
     *
     * @return true if double click shouldn't be disabled
     */
    @JsProperty boolean getCancelNextDblclick();

    /**
     * Disables dbclick
     *
     * @param cancel disable double click
     */
    @JsProperty void setCancelNextDblclick(boolean cancel);

    /**
     *
     * @return pixel coordinates
     */
    @JsProperty double getDragStartX();

    /**
     *
     * @return pixel coordinates
     */
    @JsProperty double getDragStartY();

    /**
     *
     * @return pixel coordinates
     */
    @JsProperty double getDragEndX();

    /**
     *
     * @return pixel coordinates
     */
    @JsProperty double getDragEndY();

    /**
     *
     * @return 1 if horizontal and 2 if vertical
     */
    @JsProperty int getDragDirection();

    /**
     *
     * @return 1 if horizontal and 2 if vertical
     */
    @JsProperty int getPrevDragDirection();

    /**
     *
     * @return pixel coordinates
     */
    @JsProperty double getPrevEndX();

    /**
     *
     * @return pixel coordinates
     */
    @JsProperty double getPrevEndY();

    /**
     * The value on the left side of the graph when a pan operation starts.
     */
    @JsProperty double getInitialLeftmostDate();

    //TODO wait until name works
    //@JsProperty(name="xUnitsPerPixel") double getXUnitsPerPixel();

    /**
     * The range in second/value units that the viewport encompasses during a panning operation.
     */
    @JsProperty double getDateRange();

    /**
     * Values for use with panEdgeFraction, which limit how far outside the graph's data boundaries it can be panned.
     */
    @JsProperty JsArrayMixed getBoundedDates();

    /**
     * Values for use with panEdgeFraction, which limit how far outside the graph's data boundaries it can be panned.
     */
    @JsProperty
    JsArray<JsArrayMixed> getBoundedValues();

    /**
     * Initialize the context. Call when the mouse is pressed down to track the start positions
     *
     * @param event DOM Event
     * @param g Dygraphs instance
     * @param context the {@link InteractionContext}
     */
    void initializeMouseDown(NativeEvent event, DygraphsJs g, InteractionContext context);

    /**
     *  Call when you want to end the interaction (i.e. mouseup)
     */
    void destroy();
}
