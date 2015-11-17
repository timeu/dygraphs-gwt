package com.github.timeu.dygraphsgwt.client;

import com.github.timeu.dygraphsgwt.client.callbacks.DataFunction;
import com.github.timeu.dygraphsgwt.client.extras.Synchronizer;
import com.github.timeu.dygraphsgwt.client.extras.SynchronizerOptions;
import com.github.timeu.dygraphsgwt.client.options.interactions.InteractionContext;
import com.github.timeu.dygraphsgwt.client.options.interactions.InteractionModel;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.DataSource;

/**
 * Created by uemit.seren on 7/24/15.
 */
public class Dygraphs extends Widget implements RequiresResize {

    protected final DygraphsJs jso;
    protected boolean isReady = false;

    private final Scheduler.ScheduledCommand resizeCmd = new Scheduler.ScheduledCommand() {
        public void execute() {
            resizeCmdScheduled = false;
            handleResize();
        }
    };
    private boolean resizeCmdScheduled = false;

    public Dygraphs(String data,DygraphsOptions options) {
        initElement();
        jso = ScriptInjector.createJso(getElement(),data,options);
        jso.ready(() -> ready());
    }

    public Dygraphs(JsArray<JsArrayMixed> data,DygraphsOptions options) {
        initElement();
        jso = ScriptInjector.createJso(getElement(),data,options);
        jso.ready(() -> ready());
    }

    public Dygraphs(DataFunction function,DygraphsOptions options) {
        initElement();
        jso = ScriptInjector.createJso(getElement(),function,options);
        jso.ready(() -> ready());
    }

    public Dygraphs(DataSource dataTable,DygraphsOptions options) {
        initElement();
        jso = ScriptInjector.createJso(getElement(),dataTable,options);
        jso.ready(() -> ready());
    }

    private void initElement() {
        Element div = DOM.createDiv();
        setElement(div);
    }


    //FIXME until static methods work on prototype
    public static InteractionModel getDefaultInteractionModel() {
        return ScriptInjector.getDefaultInteractionModel();
    }

    public static Synchronizer synchronize(DygraphsJs[] gs,SynchronizerOptions options) {
        return ScriptInjector.synchronize(gs, options);
    }

    public static Synchronizer synchronize(DygraphsJs[] gs) {
        return ScriptInjector.synchronize(gs);
    }

    //FIXME until static methods work on prototype
    public static void cancelEvent(NativeEvent event) {
        ScriptInjector.cancelEvent(event);
    }


    private void ready()  {
        isReady = true;
        resize();
    }

    public DygraphsJs getJSO() {
        return jso;
    }

    public void resize() {
        if (jso != null && isAttached()) {
           scheduleResize();
        }
    }

    private void handleResize() {
        if (!isAttached() || jso == null) {
            return;
        }
        jso.resize();
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        if (isReady) {
            resize();
        }
    }

    @Override
    public void onResize() {
        resize();
    }


    public static Position findPos(Element div) {
        return ScriptInjector.findPos(div);
    }

    public static int pageX(NativeEvent event) {
        return ScriptInjector.pageX(event);
    }

    public static int pageY(NativeEvent event) {
        return ScriptInjector.pageY(event);
    }

    /**
     * Schedule a resize handler. We schedule the event so the DOM has time to
     * update the offset sizes, and to avoid duplicate resize events from both a
     * height and width resize.
     */
    private void scheduleResize() {
        if (isAttached() && !resizeCmdScheduled) {
            resizeCmdScheduled = true;
            Scheduler.get().scheduleDeferred(resizeCmd);
        }
    }


    public static void startZoom(NativeEvent event, DygraphsJs g, InteractionContext context) {
        ScriptInjector.startZoom(event,g,context);
    }

    public static void startPan(NativeEvent event, DygraphsJs g, InteractionContext context) {
        ScriptInjector.startPan(event, g, context);
    }

    public static void endZoom(NativeEvent event, DygraphsJs g, InteractionContext context) {
        ScriptInjector.endZoom(event, g, context);
    }

    public static void endPan(NativeEvent event, DygraphsJs g, InteractionContext context) {
        ScriptInjector.endPan(event, g, context);
    }

    public static void moveZoom(NativeEvent event, DygraphsJs g, InteractionContext context) {
        ScriptInjector.moveZoom(event, g, context);
    }

    public static void movePan(NativeEvent event, DygraphsJs g, InteractionContext context) {
        ScriptInjector.movePan(event, g, context);
    }
    public void redraw() {
        jso.drawGraph_();
    }

}
