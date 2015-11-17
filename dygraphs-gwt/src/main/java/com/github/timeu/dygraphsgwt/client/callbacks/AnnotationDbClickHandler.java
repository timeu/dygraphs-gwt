package com.github.timeu.dygraphsgwt.client.callbacks;

import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.google.gwt.event.dom.client.MouseEvent;
import jsinterop.annotations.JsFunction;

/**
 * Created by uemit.seren on 7/29/15.
 */
@FunctionalInterface
@JsFunction
public interface AnnotationDbClickHandler {
    /**
     * Called when the user double clicks on an annotation.
     *
     * @param annotation {@link Annotation}
     * @param point {@link Point}
     * @param dygraphjs {@link DygraphsJs}
     * @param event {@link MouseEvent}
     */
    void onDbClick(Annotation annotation,Point point,DygraphsJs dygraphjs,MouseEvent event);
}
