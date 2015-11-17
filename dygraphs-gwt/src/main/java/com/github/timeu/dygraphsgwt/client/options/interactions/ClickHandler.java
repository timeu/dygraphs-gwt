package com.github.timeu.dygraphsgwt.client.options.interactions;

import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.google.gwt.dom.client.NativeEvent;
import jsinterop.annotations.JsFunction;

/**
 * Created by uemit.seren on 7/30/15.
 */

@FunctionalInterface
@JsFunction
public interface ClickHandler {

    /**
     * Called when mouse is clicked.
     *
     * @param event {@link NativeEvent}
     * @param dygraphsjs {@link DygraphsJs}
     * @param context {@link InteractionContext}
     */
    void onClick(NativeEvent event,DygraphsJs dygraphsjs,InteractionContext context);
}
