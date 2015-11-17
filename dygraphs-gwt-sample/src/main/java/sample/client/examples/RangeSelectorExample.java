package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.options.Properties;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
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
        DygraphsOptions options = new DygraphsOptions();
        options.customBars = true;
        options.title = "Daily Temperatures in New York vs. San Francisco";
        options.ylabel = "Temperature (F)";
        options.legend = DygraphsOptions.SHOW_LEGEND.always.name();
        Properties styles = Properties.create();
        styles.set("textAlign", "right");
        options.labelsDivStyles  = styles;
        options.showRangeSelector = true;
        options.width = 600;
        options.height = 300;
        panel.add(new Dygraphs(DataUtils.dataTemp(),options));
    }

    private void initDygraphs2() {
        DygraphsOptions options = new DygraphsOptions();
        options.customBars = true;
        options.rollPeriod = 14;
        options.showRoller = true;
        options.title = "Daily Temperatures in New York vs. San Francisco";
        options.ylabel = "Temperature (F)";
        options.legend = DygraphsOptions.SHOW_LEGEND.always.name();
        Properties styles = Properties.create();
        styles.set("textAlign","right");
        options.labelsDivStyles  = styles;
        options.showRangeSelector = true;
        options.rangeSelectorHeight = 30;
        options.rangeSelectorPlotStrokeColor  = "yellow";
        options.rangeSelectorPlotFillColor = "lightyellow";
        options.width = 600;
        options.height = 300;
        panel.add(new Dygraphs(DataUtils.dataTemp(),options));
    }
}
