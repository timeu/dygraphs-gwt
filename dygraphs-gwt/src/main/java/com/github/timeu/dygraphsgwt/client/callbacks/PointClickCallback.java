package com.github.timeu.dygraphsgwt.client.callbacks;

import com.google.gwt.event.dom.client.MouseEvent;
import jsinterop.annotations.JsFunction;

/**
 * Created by uemit.seren on 7/30/15.
 */
@FunctionalInterface
@JsFunction
public interface PointClickCallback {
    /**
     * A function to call when a data point is clicked. and the point that was clicked.
     *
     * @param event Mousevent
     * @param point Point that was clicked
     */
    void onClick(MouseEvent event,Point point);
}
