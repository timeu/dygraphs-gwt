package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.callbacks.LegendFormatterCallback;
import com.github.timeu.dygraphsgwt.client.callbacks.LegendFormatterData;
import com.github.timeu.dygraphsgwt.client.callbacks.LegendFormatterSeries;
import com.github.timeu.dygraphsgwt.client.options.HighlightSeriesOptions;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uemit.seren on 2/3/16.
 */
public class LegendFormatterExample extends Composite {

    private VerticalPanel panel = new VerticalPanel();
    private Dygraphs dygraphs;
    DivElement legendElement;

    public LegendFormatterExample() {

        initWidget(panel);
        legendElement = Document.get().createDivElement();
        panel.add(new HTML("This page demonstrates use of <a href=\"../options.html#legendFormatter\"><code>legendFormatter</code></a>, " +
                "which can be used to create more complex legends than <code>valueFormatter</code>"));
        panel.getElement().appendChild(legendElement);
        initDygraphs();
    }

    public void initDygraphs() {
        DygraphsOptions options = new DygraphsOptions();
        options.legend="always";
        options.xlabel="Date";
        options.ylabel="Count";
        options.labelsDiv = legendElement;
        options.legendFormatter = new LegendFormatterCallback() {
            @Override
            public String getFormat(LegendFormatterData data) {
                if (data.getX() == null) {
                    // This happens when there's no selection and {legend: 'always'} is set.
                    String retval = "<br>";
                    for (LegendFormatterSeries series: data.getSeries()) {
                        retval+=series.getDashHTML()+" "+ series.getLabelHTML()+"<br>";
                    }
                    return retval;
                }
                String html = data.getDygraph().getLabels()[0] + ": " + data.getXHTML();
                for (LegendFormatterSeries series:data.getSeries()) {
                    if (series.isVisible()) {
                        String labeledData = series.getLabelHTML()+": "+ series.getYHTML();
                        if (series.isHighlighted()) {
                            labeledData = "<b>" + labeledData+"</b>";
                        }
                        html+="<br>" + series.getDashHTML()+" " + labeledData;
                    }
                }
                return html;
            }
        };

        HighlightSeriesOptions highlightSeriesOptions = new HighlightSeriesOptions();
        highlightSeriesOptions.strokeWidth = 2;
        options.highlightSeriesOpts = highlightSeriesOptions;

        dygraphs = new Dygraphs(()-> {
            String r = "date,parabola,line,another line,sine wave\n";
            for (int i = 1; i <= 31; i++) {
                r += "200610" + (i < 10 ? "0"+i : String.valueOf(i));
                r += "," + 10*(i*(31-i));
                r += "," + 10*(8*i);
                r += "," + 10*(250 - 8*i);
                r += "," + 10*(125 + 125 * Math.sin(0.3*i));
                r += "\n";
            }
            return r;
        },options);
        panel.add(dygraphs);
    }

}
