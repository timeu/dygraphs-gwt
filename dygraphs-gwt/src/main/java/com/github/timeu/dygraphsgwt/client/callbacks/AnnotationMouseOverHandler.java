package com.github.timeu.dygraphsgwt.client.callbacks;

import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.google.gwt.core.client.js.JsFunction;
import com.google.gwt.event.dom.client.MouseEvent;

/**
 * Created by uemit.seren on 7/30/15.
 */
@FunctionalInterface
@JsFunction
public interface AnnotationMouseOverHandler {

    /**
     * Called when the user moves the mouse out of an annotation.
     *
     * @param annotation {@link Annotation}
     * @param point {@link Point}
     * @param dygraphjs {@link DygraphsJs}
     * @param event {@link MouseEvent}
     */
    void onMouseOver(Annotation annotation,Point point,DygraphsJs dygraphjs,MouseEvent event);
}
