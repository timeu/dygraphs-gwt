package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.DygraphsOptionsImpl;
import com.github.timeu.dygraphsgwt.client.options.Properties;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import sample.client.DataUtils;

/**
 * Created by uemit.seren on 8/4/15.
 */
public class RollPeriodsErrorBarsExample extends Composite {

    FlowPanel panel = new FlowPanel();
    DygraphsOptions options = new DygraphsOptionsImpl();

    public RollPeriodsErrorBarsExample() {
        initWidget(panel);
        options.setCustomBars(true);
        options.setTitle("Daily Temperatures in New York vs. San Francisco");
        options.setYlabel("Temperature (F)");
        options.setLegend(DygraphsOptions.SHOW_LEGEND.always.name());
        Properties styles = Properties.create();
        styles.set("textAlign","right");
        options.setLabelsDivStyles(styles);
        panel.add(new HTML("<p>No roll period.</p>"));
        initDygraphs2();
        panel.add(new HTML("<p>Roll period of 14 timesteps.</p>"));
        initDygraphs1();

    }

    private void initDygraphs2() {
        Dygraphs g= new Dygraphs(DataUtils.dataTemp(),options);
        g.setPixelSize(600,300);
        panel.add(g);

    }

    private void initDygraphs1() {
        options.setShowRoller(true);
        options.setRollPeriod(14);
        Dygraphs g = new Dygraphs(DataUtils.dataTemp(),options);
        g.setPixelSize(600,300);
        panel.add(g);
    }
}
