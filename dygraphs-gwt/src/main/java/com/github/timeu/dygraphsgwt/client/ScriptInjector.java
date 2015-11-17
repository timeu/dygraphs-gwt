package com.github.timeu.dygraphsgwt.client;


import com.github.timeu.dygraphsgwt.client.callbacks.DataFunction;
import com.github.timeu.dygraphsgwt.client.extras.Synchronizer;
import com.github.timeu.dygraphsgwt.client.extras.SynchronizerOptions;
import com.github.timeu.dygraphsgwt.client.options.interactions.InteractionContext;
import com.github.timeu.dygraphsgwt.client.options.interactions.InteractionModel;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.resources.client.TextResource;
import com.googlecode.gwt.charts.client.DataSource;

/**
 * Created by uemit.seren on 7/27/15.
 */
public class ScriptInjector {



    public enum EXTRAS {SYNCHRONIZE,SHAPES,CROSSHAIR}

    public static void injectScript() {
        injectScript(false);
    }

    public static void injectScript(boolean isDev) {
        if (!isInjected()) {
            String dygraphsJsCode = DygraphsClientBundle.INSTANCE.dygraphjs().getText();
            if (isDev) {
                dygraphsJsCode = DygraphsClientBundle.INSTANCE.dygraphjs_dev().getText();
            }
            com.google.gwt.core.client.ScriptInjector.fromString(dygraphsJsCode).setWindow(com.google.gwt.core.client.ScriptInjector.TOP_WINDOW).inject();
        }
    }

    public static void injectExtra(EXTRAS extra) {
        if (!isExtrasInjected(extra.name().toLowerCase())) {
            com.google.gwt.core.client.ScriptInjector.fromString(getExtraJsCode(extra).getText()).setWindow(com.google.gwt.core.client.ScriptInjector.TOP_WINDOW).inject();
        }
    }

    private static TextResource getExtraJsCode(EXTRAS extra) {
        switch (extra) {
            case SYNCHRONIZE:
                return DygraphsClientBundle.INSTANCE.synchronizer();
            case SHAPES:
                return DygraphsClientBundle.INSTANCE.shapes();
            case CROSSHAIR:
                return DygraphsClientBundle.INSTANCE.crosshair();
            default:
                throw new RuntimeException("Extra "+ extra + " not available");
        }
    }

    private static final native boolean isInjected() /*-{
        if (!(typeof $wnd.Dygraph === "undefined") && !(null === $wnd.Dygraph)) {
            return true;
        }
        return false;
    }-*/;

    private static final native boolean isExtrasInjected(String extra) /*-{
        if (!(typeof $wnd.Dygraph === "undefined") && !(null === $wnd.Dygraph) && !(typeof $wnd.Dygraph[extra] === 'undefined')) {
            return true;
        }
        return false;
    }-*/;

    /*public static DygraphsJs createJso(Element container) {

    } */

    protected static DygraphsJs createJso(Element container, String data, DygraphsOptions options) {
        injectScript();
        return createNativeJsoWithString(container, data, options);
    }

    protected static DygraphsJs createJso(Element container, DataFunction function, DygraphsOptions options) {
        injectScript();
        return createNativeJsoWithFunction(container, function, options);
    }

    protected static DygraphsJs createJso(Element container, DataSource data, DygraphsOptions options) {
        injectScript();
        return createNativeJsoWithDataTable(container, data, options);
    }

    protected static DygraphsJs createJso(Element container, JsArray<JsArrayMixed> data, DygraphsOptions options) {
        injectScript();
        return createNativeJsoWithJsArray(container, data, options);
    }

    protected static final native DygraphsJs createNativeJsoWithJsArray(Element container, JsArray<JsArrayMixed> data, DygraphsOptions options) /*-{
        return new $wnd.Dygraph(container, data, options);
    }-*/;

    protected static final native DygraphsJs createNativeJsoWithString(Element container, String data, DygraphsOptions options) /*-{
        return new $wnd.Dygraph(container, data, options);
    }-*/;

    protected static final native DygraphsJs createNativeJsoWithFunction(Element container, DataFunction func, DygraphsOptions options) /*-{
        return new $wnd.Dygraph(container, func, options);
    }-*/;

    protected static final native DygraphsJs createNativeJsoWithDataTable(Element container, DataSource data, DygraphsOptions options) /*-{
        return new $wnd.Dygraph(container, data, options);
    }-*/;

    /* FIXME once js magic functions work */
    protected static native InteractionModel getDefaultInteractionModel() /*-{
        return $wnd.Dygraph.defaultInteractionModel;
    }-*/;

    /* FIXME once js magic functions work */
    protected static native void cancelEvent(NativeEvent event) /*-{
        return $wnd.Dygraph.cancelEvent(event);
    }-*/;

    protected static native Synchronizer synchronize(DygraphsJs[] gs,SynchronizerOptions options) /*-{
        return $wnd.Dygraph.synchronize(gs,options);
    }-*/;

    protected static native Synchronizer synchronize(DygraphsJs[] gs) /*-{
        return $wnd.Dygraph.synchronize(gs);
    }-*/;


    protected static final native Position findPos(Element div) /*-{
        return $wnd.Dygraph.findPos(div);
    }-*/;

    protected static final native int pageX(NativeEvent event) /*-{
        return $wnd.Dygraph.pageX(event);
    }-*/;

    protected static final native int pageY(NativeEvent event) /*-{
        return $wnd.Dygraph.pageY(event);
    }-*/;

    protected static final native void startZoom(NativeEvent event, DygraphsJs g, InteractionContext context) /*-{
         $wnd.Dygraph.startZoom(event,g,context);
    }-*/;

    protected static final native void startPan(NativeEvent event, DygraphsJs g, InteractionContext context) /*-{
        $wnd.Dygraph.startPan(event,g,context);
    }-*/;

    protected static final native void endZoom(NativeEvent event, DygraphsJs g, InteractionContext context) /*-{
        $wnd.Dygraph.endZoom(event,g,context);
    }-*/;

    protected static final native void endPan(NativeEvent event, DygraphsJs g, InteractionContext context) /*-{
        $wnd.Dygraph.endPan(event,g,context);
    }-*/;

    protected static final native void moveZoom(NativeEvent event, DygraphsJs g, InteractionContext context) /*-{
        $wnd.Dygraph.moveZoom(event,g,context);
    }-*/;

    protected static final native void movePan(NativeEvent event, DygraphsJs g, InteractionContext context) /*-{
        $wnd.Dygraph.movePan(event,g,context);
    }-*/;

    // TODO required because getValue can return an array instead of a Number
    public static final native double[] getMultipleValue(DygraphsJs g,int row, int col) /*-{
        return g.getValue(row,col);
    }-*/;

}
