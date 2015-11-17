package com.github.timeu.dygraphsgwt.client.extras;

import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.google.gwt.canvas.dom.client.Context2d;

/**
 * Created by uemit.seren on 9/2/15.
 */
public class DrawingShapes {

    public final static native void drawDEFAULT(DygraphsJs g, String series,Context2d canvas,double cx,double cy,String color,float radius) /*-{
        $wnd.Dygraph.Circles.DEFAULT(g,series,canvas,cx,cy,color,radius);
    }-*/;

    public final static native void drawTRIANGLE(DygraphsJs g, String series,Context2d canvas,double cx, double cy,String color,float radius) /*-{
        $wnd.Dygraph.Circles.TRIANGLE(g,series,canvas,cx,cy,color,radius);
    }-*/;

    public final static native void drawPLUS(DygraphsJs g, String series,Context2d canvas,double cx, double cy,String color,float radius) /*-{
        $wnd.Dygraph.Circles.PLUS(g,series,canvas,cx,cy,color,radius);
    }-*/;

    public final static native void drawCIRCLE(DygraphsJs g, String series,Context2d canvas,double cx, double cy,String color,float radius) /*-{
        $wnd.Dygraph.Circles.CIRCLE(g,series,canvas,cx,cy,color,radius);
    }-*/;

    public final static  native void drawSQUARE(DygraphsJs g, String series,Context2d canvas,double cx, double cy,String color,float radius) /*-{
        $wnd.Dygraph.Circles.SQUARE(g,series,canvas,cx,cy,color,radius);
    }-*/;

    public final static native void drawPENTAGON(DygraphsJs g, String series,Context2d canvas,double cx, double cy,String color,float radius) /*-{
        $wnd.Dygraph.Circles.PENTAGON(g,series,canvas,cx,cy,color,radius);
    }-*/;

    public final static native void drawHEXAGON(DygraphsJs g, String series,Context2d canvas,double cx, double cy,String color,float radius) /*-{
        $wnd.Dygraph.Circles.HEXAGON(g,series,canvas,cx,cy,color,radius);
    }-*/;

    public final static native void drawSTAR(DygraphsJs g, String series,Context2d canvas,double cx, double cy,String color,float radius) /*-{
        $wnd.Dygraph.Circles.STAR(g,series,canvas,cx,cy,color,radius);
    }-*/;

    public final static native void drawEX(DygraphsJs g, String series,Context2d canvas,double cx, double cy,String color,float radius) /*-{
        $wnd.Dygraph.Circles.EX(g,series,canvas,cx,cy,color,radius);
    }-*/;
}
