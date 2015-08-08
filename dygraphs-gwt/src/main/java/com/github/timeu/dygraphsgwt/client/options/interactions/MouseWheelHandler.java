package com.github.timeu.dygraphsgwt.client.options.interactions;

import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.google.gwt.core.client.js.JsFunction;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.MouseWheelEvent;


/**
 * Created by uemit.seren on 7/30/15.
 */
@FunctionalInterface
@JsFunction
public interface MouseWheelHandler {

    /**
     * Called when mousewheel is turned.
     *
     * @param event {@link NativeEvent}
     * @param dygraphsjs {@link DygraphsJs}
     * @param context {@link InteractionContext}
     */
    void onMouseWheel(NativeEvent event,DygraphsJs dygraphsjs,InteractionContext context);
}
