package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.DygraphsOptionsImpl;
import com.google.gwt.user.client.ui.Composite;

/**
 * Created by uemit.seren on 7/29/15.
 */
public class SimpleExample extends Composite {


    public SimpleExample() {
        DygraphsOptions options = new DygraphsOptionsImpl();
        options.setTitle("NYC vs. SF");
        options.setLegend(DygraphsOptions.SHOW_LEGEND.always.name().toLowerCase());
        options.setShowRoller(true);
        options.setRollPeriod(14);
        options.setCustomBars(true);
        options.setYlabel("Temperature (F)");
        Dygraphs dygraphs = new Dygraphs("ny-vs-sf.txt",options);
        initWidget(dygraphs);
    }


}
