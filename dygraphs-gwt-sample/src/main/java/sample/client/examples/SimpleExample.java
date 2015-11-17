package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.google.gwt.user.client.ui.Composite;

/**
 * Created by uemit.seren on 7/29/15.
 */
public class SimpleExample extends Composite {


    public SimpleExample() {
        DygraphsOptions options = new DygraphsOptions();
        options.title = "NYC vs. SF";
        options.legend = DygraphsOptions.SHOW_LEGEND.always.name().toLowerCase();
        options.showRoller = true;
        options.rollPeriod = 14;
        options.customBars = true;
        options.ylabel = "Temperature (F)";
        Dygraphs dygraphs = new Dygraphs("ny-vs-sf.txt",options);
        initWidget(dygraphs);
    }


}
