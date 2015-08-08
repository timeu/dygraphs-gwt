package com.github.timeu.dygraphsgwt.client.options;

import com.google.gwt.core.client.js.JsType;

/**
 * Created by uemit.seren on 8/3/15.
 */
@JsType
public class AxesOptions {

    /**
     * x-Axis options
     */
    public AxisOptions x;

    /**
     * y-Axis options
     */
    public AxisOptions y;

    /**
     * y2-Axis options
     */
    public AxisOptions y2;


    public static class Builder {

        private final AxesOptions options;

        public Builder() {
            options = new AxesOptions();
        }

        public Builder x(AxisOptions x) {
            this.options.x = x;
            return this;
        }
        public Builder y(AxisOptions y) {
            this.options.y = y;
            return this;
        }
        public Builder y2(AxisOptions y2) {
            this.options.y2 = y2;
            return this;
        }
        public AxesOptions build() {
            return options;
        }
    }
}
