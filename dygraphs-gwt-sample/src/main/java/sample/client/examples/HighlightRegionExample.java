package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.DygraphsOptionsImpl;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;

/**
 * Created by uemit.seren on 7/31/15.
 */
public class HighlightRegionExample extends Composite {

    FlowPanel panel = new FlowPanel();

    public HighlightRegionExample() {
        initWidget(panel);
        initDygraphs();
        panel.add(new HTML("<p>When you zoom and pan, the region remains highlighted.</p>"));
    }

    private void initDygraphs() {
        // A basic sinusoidal data series.
        JsArray<JsArrayMixed> data = JsArray.createArray(1000).cast();
        for (int i = 0; i < 1000; i++) {
            double base = 10 * Math.sin(i / 90.0);
            JsArrayMixed row = JsArrayMixed.createArray(3).cast();
            row.set(0,i);
            row.set(1,base);
            row.set(2,base + Math.sin(i /2.0));
            data.set(i,row);
        }

        // Shift one portion out of line.
        int highlight_start = 450;
        int highlight_end = 500;
        for (int i = highlight_start; i <= highlight_end; i++) {
            double val = data.get(i).getNumber(2);
            data.get(i).set(2,val+5.0);
        }
        DygraphsOptions options = new DygraphsOptionsImpl();
        options.setLabels(new String[]{"X", "Est.", "Actual"});
        options.setAnimatedZooms(true);
        options.setUnderlayCallback((canvas,area,g) -> {
            double[] bottom_left = g.toDomCoords((double) highlight_start, -20d,0);
            double[] top_right = g.toDomCoords((double) highlight_end, 20d,0);

            double left = bottom_left[0];
            double right = top_right[0];

            canvas.setFillStyle("rgba(255, 255, 102, 1.0)");
            canvas.fillRect(left, area.getY(), right - left, area.getH());
        });

        panel.add(new Dygraphs(
                data,
                options));
    }
}
