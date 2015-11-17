package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.options.Properties;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import sample.client.DataUtils;

/**
 * Created by uemit.seren on 8/4/15.
 */
public class CSSLabelStylingExample extends Composite {

    FlowPanel panel = new FlowPanel();
    DygraphsOptions options = new DygraphsOptions();

    public CSSLabelStylingExample() {
        initWidget(panel);
        options.rollPeriod = 7;
        options.legend = DygraphsOptions.SHOW_LEGEND.always.name();
        options.title = "High and Low Temperatures";
        options.titleHeight = 32;
        options.ylabel = "Temperature (F)";
        options.xlabel = "Date (Ticks indicate the start of the indicated time period)";
        Properties styles = Properties.create();
        styles.set("text-align","right");
        styles.set("background","none");
        options.labelsDivStyles = styles;
        options.strokeWidth = 1.5;
        panel.add(new HTML("<p class=\"infotext\">This chart's labels are styled</p>"));
        initDygraphs1();
        panel.add(new HTML("<p class=\"infotext\">This version of the chart uses the default styles:</p>"));
        initDygraphs2();

    }

    private void initDygraphs2() {
        Dygraphs g = new Dygraphs(DataUtils.data,options);
        g.setPixelSize(600,300);
        g.setStyleName("chart");
        panel.add(g);
    }

    private void initDygraphs1() {
        Dygraphs g = new Dygraphs(DataUtils.data,options);
        g.setPixelSize(600,300);
        g.addStyleName("styled_chart chart");
        panel.add(g);
    }


}
