package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.options.SeriesOptions;
import com.google.gwt.user.client.ui.Composite;

/**
 * Created by uemit.seren on 8/3/15.
 */
public class PerSeriesPropertiesExample extends Composite {

    Dygraphs dygraphs;

    public PerSeriesPropertiesExample() {

        DygraphsOptions options = new DygraphsOptions();
        options.strokeWidth = 2;
        SeriesOptions parabolaOptions = new SeriesOptions();
        parabolaOptions.drawPoints = true;
        parabolaOptions.strokeWidth = 0.0;
        parabolaOptions.pointSize = 4;
        parabolaOptions.highlightCircleSize =6;
        options.setSeriesOptions("parabola",parabolaOptions);

        SeriesOptions lineOptions = new SeriesOptions();
        lineOptions.drawPoints = true;
        lineOptions.strokeWidth = 1.0;
        lineOptions.pointSize = 1.5;
        options.setSeriesOptions("line",lineOptions);

        SeriesOptions sineeOptions = new SeriesOptions();
        sineeOptions.strokeWidth = 3.0;
        sineeOptions.highlightCircleSize = 10;
        options.setSeriesOptions("sine wave",sineeOptions);

        dygraphs = new Dygraphs(() ->{
            String r = "date,parabola,line,another line,sine wave\n";
            for(int i = 1;i<=31;i++) {
                r += "200610" + (i < 10 ?  "0" + i : String.valueOf(i));
                r += "," + 10 * (i * (31 - i));
                r += "," + 10 * (8 * i);
                r += "," + 10 * (250 - 8 * i);
                r += "," + 10 * (125 + 125 * Math.sin(0.3 * i));
                r += "\n";
            }

            return r;
        },options);
        initWidget(dygraphs);
    }
}
