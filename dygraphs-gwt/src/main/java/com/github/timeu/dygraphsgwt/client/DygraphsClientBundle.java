package com.github.timeu.dygraphsgwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Created by uemit.seren on 7/24/15.
 */
public interface DygraphsClientBundle extends ClientBundle {

    DygraphsClientBundle INSTANCE = GWT.create(DygraphsClientBundle.class);

    @Source("resources/dygraph-combined.js")
    TextResource dygraphjs();

    @Source("resources/dygraph-combined-dev.js")
    TextResource dygraphjs_dev();


    @Source("resources/extras/synchronizer.js")
    TextResource synchronizer();

}
