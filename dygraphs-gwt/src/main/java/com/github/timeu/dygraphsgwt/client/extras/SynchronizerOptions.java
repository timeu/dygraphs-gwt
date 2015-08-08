package com.github.timeu.dygraphsgwt.client.extras;

import com.google.gwt.core.client.js.JsProperty;
import com.google.gwt.core.client.js.JsType;

/**
 * Allows the user to set the options for the Synchronizer Plugin
 *
 * Created by uemit.seren on 8/3/15.
 */
@JsType
public class SynchronizerOptions{

    /**
     * if true then the selections will be synchronized
     * Default: true
     */
    public boolean selection;

    /**
     * if true then the zooming will be synchronized
     * Default: true
     */
    public boolean zoom;

    /**
     * if true then the y-axis is also synchronized.
     * Has only effect if zoom is true
     * Default: true
     */
    public boolean range;

    public static class Builder {
        private SynchronizerOptions options;

        public Builder() {
            options = new SynchronizerOptions();
        }

        public Builder setSelection(boolean selection) {
            this.options.selection = selection;
            return this;
        }

        public Builder setZoom(boolean zoom) {
            this.options.zoom = zoom;
            return this;
        }

        public Builder setRange(boolean range) {
            this.options.range = range;
            return this;
        }

        public SynchronizerOptions build() {
            return options;
        }
    }

}
