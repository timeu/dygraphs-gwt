package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.DygraphsOptionsImpl;
import com.github.timeu.dygraphsgwt.client.options.Properties;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import sample.client.DataUtils;

/**
 * Created by uemit.seren on 8/3/15.
 */
public class RangeSelectorExample extends Composite {

    FlowPanel panel = new FlowPanel();


    public RangeSelectorExample() {
        initWidget(panel);
        panel.add(new HTML("<p>No roll period</p>"));
        initDygraphs1();
        panel.add(new HTML("<p>Roll period of 14 timesteps, custom range selector height and plot color.</p>"));
        initDygraphs2();
    }

    private void initDygraphs1() {
        DygraphsOptions options = new DygraphsOptionsImpl();
        options.setCustomBars(true);
        options.setTitle("Daily Temperatures in New York vs. San Francisco");
        options.setYlabel("Temperature (F)");
        options.setLegend(DygraphsOptions.SHOW_LEGEND.always.name());
        Properties styles = Properties.create();
        styles.set("textAlign", "right");
        options.setLabelsDivStyles(styles);
        options.setShowRangeSelector(true);
        options.setWidth(600);
        options.setHeight(300);
        panel.add(new Dygraphs(DataUtils.dataTemp(),options));
    }

    private void initDygraphs2() {
        DygraphsOptions options = new DygraphsOptionsImpl();
        options.setCustomBars(true);
        options.setRollPeriod(14);
        options.setShowRoller(true);
        options.setTitle("Daily Temperatures in New York vs. San Francisco");
        options.setYlabel("Temperature (F)");
        options.setLegend(DygraphsOptions.SHOW_LEGEND.always.name());
        Properties styles = Properties.create();
        styles.set("textAlign","right");
        options.setLabelsDivStyles(styles);
        options.setShowRangeSelector(true);
        options.setRangeSelectorHeight(30);
        options.setRangeSelectorPlotStrokeColor("yellow");
        options.setRangeSelectorPlotFillColor("lightyellow");
        options.setWidth(600);
        options.setHeight(300);
        panel.add(new Dygraphs(DataUtils.dataTemp(),options));
    }
}
