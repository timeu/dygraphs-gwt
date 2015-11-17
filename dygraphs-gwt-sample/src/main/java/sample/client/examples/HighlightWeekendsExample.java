package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.callbacks.Area;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.JsDate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;


/**
 * Created by uemit.seren on 7/31/15.
 */
public class HighlightWeekendsExample extends Composite {

    FlowPanel panel = new FlowPanel();

    public HighlightWeekendsExample() {
        initWidget(panel);
        initDygraphs();
        panel.add(new HTML("<p>When you zoom and pan, the weekend regions remain highlighted.</p>"));
    }

    private void initDygraphs() {
        // Some sample data
        String data = "2011-01-01," + Math.random()*100 + "\n" +
                "2011-01-02," + Math.random()*100 + "\n" +
                "2011-01-03," + Math.random()*100 + "\n" +
                "2011-01-04," + Math.random()*100 + "\n" +
                "2011-01-05," + Math.random()*100 + "\n" +
                "2011-01-06," + Math.random()*100 + "\n" +
                "2011-01-07," + Math.random()*100 + "\n" +
                "2011-01-08," + Math.random()*100 + "\n" +
                "2011-01-09," + Math.random()*100 + "\n" +
                "2011-01-10," + Math.random()*100 + "\n" +
                "2011-01-11," + Math.random()*100 + "\n" +
                "2011-01-12," + Math.random()*100 + "\n" +
                "2011-01-13," + Math.random()*100 + "\n" +
                "2011-01-14," + Math.random()*100 + "\n" +
                "2011-01-15," + Math.random()*100 + "\n" +
                "2011-01-16," + Math.random()*100 + "\n" +
                "2011-01-17," + Math.random()*100 + "\n" +
                "2011-01-18," + Math.random()*100 + "\n" +
                "2011-01-19," + Math.random()*100 + "\n" +
                "2011-01-20," + Math.random()*100 + "\n" +
                "2011-01-21," + Math.random()*100 + "\n" +
                "2011-01-22," + Math.random()*100 + "\n" +
                "2011-01-23," + Math.random()*100 + "\n" +
                "2011-01-24," + Math.random()*100 + "\n" +
                "2011-01-25," + Math.random()*100 + "\n" +
                "2011-01-26," + Math.random()*100 + "\n" +
                "2011-01-27," + Math.random()*100 + "\n" +
                "2011-01-28," + Math.random()*100 + "\n" +
                "2011-01-29," + Math.random()*100 + "\n" +
                "2011-01-30," + Math.random()*100 + "\n" +
                "2011-01-31," + Math.random()*100 + "\n";

        DygraphsOptions options = new DygraphsOptions();
        options.labels = new String[]{"Date", "Value"};
        options.underlayCallback = (canvas, area, g) -> {
            canvas.setFillStyle("rgba(255, 255, 102, 1.0)");
            double min_data_x = g.getValue(0, 0);
            double max_data_x = g.getValue(g.numRows() - 1, 0);

            // get day of week

            JsDate d = JsDate.create((long)min_data_x);
            int dow = d.getUTCDay();


            long w = (long)min_data_x;
            // starting on Sunday is a special case
            if (dow == 0) {
                highlightPeriod(w, w + 12 * 3600 * 1000, canvas, area, g);
            }
            // find first saturday
            while (dow != 6) {
                w += 24 * 3600 * 1000;
                d = JsDate.create((long)min_data_x);
                dow = d.getDay();
            }
            // shift back 1/2 day to center highlight around the point for the day
            w -= 12 * 3600 * 1000;
            while (w < max_data_x) {
                double start_x_highlight = w;
                double end_x_highlight = w + 2 * 24 * 3600 * 1000;
                // make sure we don't try to plot outside the graph
                if (start_x_highlight < min_data_x) {
                    start_x_highlight = min_data_x;
                }
                if (end_x_highlight > max_data_x) {
                    end_x_highlight = max_data_x;
                }
                highlightPeriod(start_x_highlight, end_x_highlight, canvas, area, g);
                // calculate start of highlight for next Saturday
                w += 7 * 24 * 3600 * 1000;
            }

        };
        panel.add(new Dygraphs(data,options));
    }

    private void  highlightPeriod(double xStart,double xEnd,Context2d canvas, Area area,DygraphsJs g) {
        double canvas_left_x = g.toDomXCoord(xStart);
        double canvas_right_x = g.toDomXCoord(xEnd);
        double canvas_width = canvas_right_x - canvas_left_x;
        canvas.fillRect(canvas_left_x, area.getY(), canvas_width, area.getH());
    }
}
