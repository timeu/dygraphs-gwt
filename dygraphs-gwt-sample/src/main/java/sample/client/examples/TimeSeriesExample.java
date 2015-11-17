package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.Position;
import com.github.timeu.dygraphsgwt.client.options.AxesOptions;
import com.github.timeu.dygraphsgwt.client.options.AxisOptions;
import com.github.timeu.dygraphsgwt.client.options.interactions.InteractionContext;
import com.github.timeu.dygraphsgwt.client.options.interactions.InteractionModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.core.client.JsDate;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.web.bindery.event.shared.HandlerRegistration;

import java.util.Date;

/**
 * Created by uemit.seren on 7/30/15.
 */
public class TimeSeriesExample extends Composite {

    VerticalPanel panel = new VerticalPanel();
    Dygraphs dygraphs;
    boolean isDrawing = false;
    Integer lastDrawRow = null;
    Double lastDrawValue = null;
    TOOL selectedTool = TOOL.PENCIL;
    enum TOOL {ZOOM,PENCIL,ERASER}
    HTML zoomBtn;
    HTML pencilBtn;
    HTML eraserBtn;
    HandlerRegistration onMouseUpRegistration;
    double[] valueRange = new double[]{0,100};
    JsArray<JsArrayMixed> data = JsArray.createArray().cast();


    public TimeSeriesExample() {
        initWidget(panel);
        HorizontalPanel toolBar = new HorizontalPanel();
        zoomBtn = new HTML("<div id='tool_zoom' style='width:33px;height:33px;background:url(http://dygraphs.com/gallery/images/tool-palette.png) 0px 0px'></div>");
        zoomBtn.addClickHandler((event) -> changeTool(TOOL.ZOOM));
        toolBar.add(zoomBtn);
        pencilBtn = new HTML("<div id='tool_pencil' style='width:33px;height:33px;background:url(http://dygraphs.com/gallery/images/tool-palette.png) -32px 0px'></div>");
        pencilBtn.addClickHandler((event) -> changeTool(TOOL.PENCIL));
        toolBar.add(pencilBtn);
        eraserBtn =new HTML("<div id='tool_eraser' style='width:33px;height:33px;background:url(http://dygraphs.com/gallery/images/tool-palette.png) -64px 0px'></div>");
        eraserBtn.addClickHandler((event) -> changeTool(TOOL.ERASER));
        toolBar.add(eraserBtn);
        panel.add(toolBar);
        initDygraphs();
        changeTool(selectedTool);
    }

    private void initDygraphs() {
        long startDate = new Date("2002/12/29").getTime();
        long endDate = new Date().getTime();

        for (long d = startDate; d < endDate; d += 604800 * 1000) {
            long millis = d + 2 * 3600 * 1000;
            JsArrayMixed row = JsArrayMixed.createArray(2).cast();
            row.set(0, JsDate.create(millis));
            row.set(1,50);
            data.push(row);
        }

        DygraphsOptions options = new DygraphsOptions();
        options.valueRange = valueRange;
        options.labels = new String[]{"Date", "Value"};
        options.strokeWidth = 1.5;
        options.gridLineColor = "rgb(196, 196, 196)";
        options.axes = new AxesOptions.Builder().y(new AxisOptions.Builder()
            .drawGrid(false).drawAxis(false).build()).build();
        options.interactionModel = getInterationModel();
        dygraphs = new Dygraphs(data,options);
        panel.add(dygraphs);
    }

    private InteractionModel getInterationModel() {
        InteractionModel model = new InteractionModel();
        model.mousedown =(NativeEvent event,DygraphsJs g,InteractionContext context)->{
          if (selectedTool  == TOOL.ZOOM) {
              Dygraphs.getDefaultInteractionModel().mousedown.onMouseDown(event, g, context);
          } else {
              event.preventDefault();
              isDrawing = true;
              setPoint(event, g, context);
          }
        };
        model.mousemove =(event,g,context)->{
            if (selectedTool  == TOOL.ZOOM) {
                Dygraphs.getDefaultInteractionModel().mousemove.onMouseMove(event, g, context);
            } else {
                if (!isDrawing) return;
                setPoint(event, g, context);
            }
        };
        model.mouseup =(event,g,context)->{
            if (selectedTool  == TOOL.ZOOM) {
                Dygraphs.getDefaultInteractionModel().mouseup.onMouseUp(event, g, context);
            } else {
                finishDrawing();
            }
        };
        model.mouseout =(event,g,context)->{
            if (selectedTool  == TOOL.ZOOM) {
                Dygraphs.getDefaultInteractionModel().mouseout.onMouseOut(event, g, context);
            }
        };
        model.dblclick =(event,g,context)->{
            Dygraphs.getDefaultInteractionModel().dblclick.onDblClick(event, g, context);
        };
        model.mousewheel =(event,g,context)->{
            double normal = getDetltaFromEvent(event);
            double percentage = normal / 50;
            double[] axis  = g.xAxisRange();
            double xOffset = g.toDomCoords(axis[0],null, 0)[0];
            double x = getOffsetFromEvent(event) - xOffset;
            double w = g.toDomCoords(axis[1],null, 0)[0] - xOffset;
            double xPct = (w == 0 ? 0 : (x / w));

            double delta = axis[1] - axis[0];
            double increment = delta * percentage;
            double[] foo = new double[]{increment * xPct, increment * (1 - xPct)};
            double[] dateWindow = new double[]{axis[0] + foo[0], axis[1] - foo[1] };
            DygraphsOptions options = new DygraphsOptions();
            options.dateWindow = dateWindow;
            g.updateOptions(options,false);
            Dygraphs.cancelEvent(event);
        };

        return model;
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        onMouseUpRegistration = addDomHandler((event)->{
            finishDrawing();
        }, MouseUpEvent.getType());
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        if (onMouseUpRegistration != null) {
            onMouseUpRegistration.removeHandler();
        }
    }

    private void finishDrawing() {
        isDrawing = false;
        lastDrawRow = null;
        lastDrawValue = null;
    }

    private static native final int getDetltaFromEvent(NativeEvent event) /*-{
        return event.detail ? event.detail * -1 : event.wheelDelta / 40;
    }-*/;

    private static native final int getOffsetFromEvent(NativeEvent event) /*-{
        return event.offsetX;
    }-*/;


    private void setPoint(NativeEvent event,DygraphsJs g,InteractionContext context) {
        Position graphPos = Dygraphs.findPos(g.getGraphDiv());
        double canvasx = Dygraphs.pageX(event) - graphPos.getX();
        double canvasy = Dygraphs.pageY(event) - graphPos.getY();
        double[] xy = g.toDataCoords(canvasx, canvasy);
        double x = xy[0];
        double value = xy[1];
        int rows = g.numRows();
        int closest_row = -1;
        double smallest_diff = -1;
        // TODO(danvk): binary search
        for (int row = 0; row < rows; row++) {
            double date = g.getValue(row, 0);  // millis
            double diff = Math.abs(date - x);
            if (smallest_diff < 0 || diff < smallest_diff) {
                smallest_diff = diff;
                closest_row = row;
            }
        }

        if (closest_row != -1) {
            if (lastDrawRow == null) {
                lastDrawRow = closest_row;
                lastDrawValue = value;
            }
            double coeff = (value - lastDrawValue) / (closest_row - lastDrawRow);
            if (closest_row == lastDrawRow) coeff = 0.0;
            int minRow = Math.min(lastDrawRow, closest_row);
            int maxRow = Math.max(lastDrawRow, closest_row);
            for (int row = minRow; row <= maxRow; row++) {
                if (selectedTool == TOOL.PENCIL) {
                    Double val = lastDrawValue + coeff * (row - lastDrawRow);
                    val = Math.max(valueRange[0], Math.min(val, valueRange[1]));
                    data.get(row).set(1,val);
                    if (val == null) {
                        GWT.log("ERROR");
                    }
                } else if (selectedTool == TOOL.ERASER) {
                    data.get(row).set(1,(JavaScriptObject)null);
                }
            }
            lastDrawRow = closest_row;
            lastDrawValue = value;
            DygraphsOptions options = new DygraphsOptions();
            options.setArrayFile(data);
            g.updateOptions(options,false);
            g.setSelection(closest_row,null,false);  // prevents the dot from being finnicky.

        }
    }


    private void changeTool(TOOL tool) {
        selectedTool = tool;
        zoomBtn.getElement().getFirstChildElement().getStyle().setProperty("backgroundPosition","0px 0px");
        pencilBtn.getElement().getFirstChildElement().getStyle().setProperty("backgroundPosition","-32px 0px");
        eraserBtn.getElement().getFirstChildElement().getStyle().setProperty("backgroundPosition","-64px 0px");
        switch (selectedTool) {
            case ZOOM:
                zoomBtn.getElement().getFirstChildElement().getStyle().setProperty("backgroundPosition","0px -32px");
                dygraphs.getElement().getStyle().setCursor(Style.Cursor.CROSSHAIR);
                break;
            case PENCIL:
                pencilBtn.getElement().getFirstChildElement().getStyle().setProperty("backgroundPosition","-32px -32px");
                dygraphs.getElement().getStyle().setProperty("cursor","url(http://dygraphs.com/gallery/images/cursor-pencil.png) 2 30, auto");
                break;
            case ERASER:
                eraserBtn.getElement().getFirstChildElement().getStyle().setProperty("backgroundPosition","-64px -32px");
                dygraphs.getElement().getStyle().setProperty("cursor","url(http://dygraphs.com/gallery/images/cursor-eraser.png) 10 30, auto");
                break;
        }
    }

}
