package com.github.timeu.dygraphsgwt.client.options.interactions;

import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.google.gwt.core.client.js.JsFunction;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;

/**
 * Created by uemit.seren on 7/30/15.
 */
@FunctionalInterface
@JsFunction
public interface MouseDownHandler {

    /**
     * Called when mouse is pressed down.
     *
     * @param event {@link NativeEvent}
     * @param dygraphsjs {@link DygraphsJs}
     * @param context {@link InteractionContext}
     */
    void onMouseDown(NativeEvent event,DygraphsJs dygraphsjs,InteractionContext context);
}
