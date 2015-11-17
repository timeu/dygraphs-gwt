package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.Position;
import com.github.timeu.dygraphsgwt.client.ScriptInjector;
import com.github.timeu.dygraphsgwt.client.options.interactions.InteractionModel;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import sample.client.DataUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by uemit.seren on 8/4/15.
 */
public class CustomInteractioModelsExample extends Composite {

    FlowPanel panel = new FlowPanel();
    DygraphsJs lastClickedGraph = null;
    boolean funActive = false;
    Context2d funCanvas = null;
    List<Integer[]> processed = new ArrayList<>();


    public CustomInteractioModelsExample() {
        initWidget(panel);
        initDefault();
        initEmpty();
        initCustom();
        initFun();
        RootLayoutPanel.get().addDomHandler((e) -> {
            lastClickedGraph = null;
        }, MouseWheelEvent.getType());
        RootLayoutPanel.get().addDomHandler((e) -> {
            lastClickedGraph = null;
        }, ClickEvent.getType());
    }

    private void initFun() {
        panel.add(new HTML("<h3>Fun model!</h3><div style=\"width:600px;\">\n" +
                "  <p style=\"text-align:center;\">Keep the mouse button pressed, and hover over all points\n" +
                "    to mark them.</p></div>"));
        DygraphsOptions options = createOptions();
        InteractionModel model = new InteractionModel();
        model.mousedown =(event,g,context)->{
            context.initializeMouseDown(event, g, context);
            funActive = true;
            model.mousemove.onMouseMove(event, g, context); // in case the mouse went down on a data point.
        };
        model.mouseup =(event,g,context)->{
            if (funActive) {
                funActive = false;
            }
        };
        model.mousemove =(event,g,context)->{
            int RANGE = 7;
            if (funActive) {
                Position graphPos = Dygraphs.findPos(g.getGraphDiv());
                double canvasx = Dygraphs.pageX(event) - graphPos.getX();
                double canvasy = Dygraphs.pageY(event) - graphPos.getY();

                int rows = g.numRows();
                // Row layout:
                // [date, [val1, stdev1], [val2, stdev2]]
                for (int row = 0; row < rows; row++) {
                    double date = g.getValue(row, 0);
                    double x = g.toDomCoords(date, null,0)[0];
                    double diff = Math.abs(canvasx - x);
                    if (diff < RANGE) {
                        for (int col = 1; col < 3; col++) {
                            // TODO(konigsberg): these will throw exceptions as data is removed.
                            double[] vals = ScriptInjector.getMultipleValue(g,row, col);
                            if (vals == null ) { continue; }
                            double val = vals[0];
                            double y = g.toDomCoords(null, val,0)[1];
                            double diff2 = Math.abs(canvasy - y);
                            if (diff2 < RANGE) {
                                boolean found = false;
                                for (Integer[] stored : processed) {
                                    if(stored[0] == row && stored[1] == col) {
                                        found = true;
                                        break;
                                    }
                                }
                                if (!found) {
                                    processed.add(new Integer[]{row, col});
                                    drawFun(x, y);
                                }
                                return;
                            }
                        }
                    }
                }
            }
        };
        model.dblclick =(event,g,context)-> restorePosition(g);
        options.underlayCallback = (canvas,area,g)->{
            funCanvas = canvas;
        };
        options.interactionModel = model;
        Dygraphs dygraphjs =new Dygraphs(DataUtils.noisyData(),options);
        dygraphjs.setPixelSize(600, 300);
        panel.add(dygraphjs);
    }

    private void drawFun(double x, double y) {
        funCanvas.setStrokeStyle("#000000");
        funCanvas.setFillStyle("#FFFF00");
        funCanvas.beginPath();
        funCanvas.arc(x,y,5,0,Math.PI*2,true);
        funCanvas.closePath();
        funCanvas.stroke();
        funCanvas.fill();
    }

    private void initCustom() {
        panel.add(new HTML("<h3>Custom interaction model</h3><div style=\"width:600px;\">\n" +
                "  <p style=\"text-align:center;\"> Zoom in: double-click, scroll wheel<br>\n" +
                "    Zoom out: ctrl-double-click, scroll wheel<br>\n" +
                "    Standard Zoom: shift-click-drag\n" +
                "    Standard Pan: click-drag<br>\n" +
                "    Restore zoom level: press button<br></p></div>"));

        DygraphsOptions options = createOptions();
        InteractionModel model = new InteractionModel();
        model.mousedown =(event,g,context)->{
            context.initializeMouseDown(event, g, context);
            if (event.getAltKey() || event.getShiftKey()) {
                Dygraphs.startZoom(event, g, context);
            } else {
                Dygraphs.startPan(event, g, context);
            }
        };
        model.mouseup =(event,g,context)->{
            if (context.getIsPanning()) {
                Dygraphs.endPan(event, g, context);
            } else if (context.getIsZooming()) {
                Dygraphs.endZoom(event, g, context);
            }
        };
        model.mousemove =(event,g,context)->{
            if (context.getIsPanning()) {
                Dygraphs.movePan(event, g, context);
            } else if (context.getIsZooming()) {
                Dygraphs.moveZoom(event, g, context);
            }
        };
        model.click =(event,g,context)->{
            lastClickedGraph = g;
            Dygraphs.cancelEvent(event);
        };
        model.dblclick =(event,g,context)->{
            // Reducing by 20% makes it 80% the original size, which means
            // to restore to original size it must grow by 25%

            int[] offsets = getOffsetsFromEvent(event);

            double[] percentages = offsetToPercentage(g, offsets[0], offsets[1]);
            double xPct = percentages[0];
            double yPct = percentages[1];

            if (event.getCtrlKey()) {
                zoom(g, -0.25, xPct, yPct);
            } else {
                zoom(g, +0.2, xPct, yPct);
            }
        };
        model.mousewheel =(event,g,context)->{
            if (lastClickedGraph != g) {
                return;
            }
            double normal = getDetltaFromEvent(event);
            // For me the normalized value shows 0.075 for one click. If I took
            // that verbatim, it would be a 7.5%.
            double percentage = normal / 50;

            int[] offsets = getOffsetsFromEvent(event);
            double[] percentages = offsetToPercentage(g, offsets[0], offsets[1]);
            double xPct = percentages[0];
            double yPct = percentages[1];

            zoom(g, percentage, xPct, yPct);
            Dygraphs.cancelEvent(event);
        };

        options.interactionModel = model;

        Dygraphs g = new Dygraphs(DataUtils.noisyData(),options);
        g.setPixelSize(600, 300);
        Button restoreBtn = new Button("Restore position");
        restoreBtn.addClickHandler((event)->restorePosition(g.getJSO()));
        panel.add(restoreBtn);
        panel.add(g);
    }

    private void initEmpty() {
        panel.add(new HTML("<h3>Empty interaction model</h3><div style=\"width:600px;\">\n" +
                "  <p style=\"text-align:center;\">Click and drag all you like, it won't do anything!</p></div>"));


        DygraphsOptions options = createOptions();
        options.interactionModel = new InteractionModel();
        Dygraphs g = new Dygraphs(DataUtils.noisyData(),options);
        g.setPixelSize(600, 300);
        panel.add(g);

    }

    private void initDefault() {
        panel.add(new HTML("<h3>Default interaction model</h3><div style=\"width:600px;\">\n" +
                "  <p style=\"text-align:center;\">Zoom: click-drag, Pan: shift-click-drag, Restore: double-click</p></div>"));
        DygraphsOptions options = createOptions();
        Dygraphs g =new Dygraphs(DataUtils.noisyData(),options);
        g.setPixelSize(600,300);
        panel.add(g);
    }

    private DygraphsOptions createOptions() {
        DygraphsOptions options = new DygraphsOptions();
        options.errorBars = true;
        return options;
    }

    private void restorePosition(DygraphsJs g) {
        DygraphsOptions options = new DygraphsOptions();
        options.dateWindow = null;
        options.valueRange = null;
        g.updateOptions(options, false);
    }

    private double[] offsetToPercentage(DygraphsJs g, double offsetX, double offsetY) {
        // This is calculating the pixel offset of the leftmost date.
        double xOffset = g.toDomCoords(g.xAxisRange()[0], null,0)[0];
        double[] yar0 = g.yAxisRange(0);

        // This is calculating the pixel of the higest value. (Top pixel)
        double yOffset = g.toDomCoords(null, yar0[1],0)[1];

        // x y w and h are relative to the corner of the drawing area,
        // so that the upper corner of the drawing area is (0, 0).
        double x = offsetX - xOffset;
        double y = offsetY - yOffset;

        // This is computing the rightmost pixel, effectively defining the
        // width.
        double w = g.toDomCoords(g.xAxisRange()[1], null,0)[0] - xOffset;

        // This is computing the lowest pixel, effectively defining the height.
        double h = g.toDomCoords(null, yar0[0],0)[1] - yOffset;

        // Percentage from the left.
        double xPct =  w ==0 ? 0.0  : (x / w);
        // Percentage from the top.
        double yPct = h == 0 ? 0 : (y / h);

        // The (1-) part below changes it from "% distance down from the top"
        // to "% distance up from the bottom".
        return new double[]{xPct, (1-yPct)};
    }

    private static native final int getDetltaFromEvent(NativeEvent event) /*-{
        return event.detail ? event.detail * -1 : event.wheelDelta / 40;
    }-*/;

    private static native final int[] getOffsetsFromEvent(NativeEvent event) /*-{
        if (!(event.offsetX && event.offsetY)){
            event.offsetX = event.layerX - event.target.offsetLeft;
            event.offsetY = event.layerY - event.target.offsetTop;
        }
        return [event.offsetX,event.offsetY];
    }-*/;

    private void zoom(DygraphsJs g, double zoomInPercentage, Double xBias, Double yBias) {
        if (xBias == null)
            xBias = 0.5;
        if (yBias == null)
            yBias = 0.5;
        double[][] yAxes = g.yAxisRanges();
        double[][] newYAxes = new double[yAxes.length][];
        for (int i = 0; i < yAxes.length; i++) {
            newYAxes[i] = adjustAxis(yAxes[i], zoomInPercentage, yBias);
        }
        DygraphsOptions options = new DygraphsOptions();
        options.dateWindow = adjustAxis(g.xAxisRange(),zoomInPercentage,xBias);
        options.valueRange = newYAxes[0];
        g.updateOptions(options,false);
    }
    private double[] adjustAxis(double[] axis, double zoomInPercentage, double bias) {
        double delta = axis[1] - axis[0];
        double increment = delta * zoomInPercentage;
        double[] foo = new double[]{increment * bias, increment * (1-bias)};
        return new double[] {axis[0] + foo[0], axis[1] - foo[1] };
    }
}
