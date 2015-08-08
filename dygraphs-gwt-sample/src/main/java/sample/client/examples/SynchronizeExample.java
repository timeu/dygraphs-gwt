package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.DygraphsOptionsImpl;
import com.github.timeu.dygraphsgwt.client.ScriptInjector;
import com.github.timeu.dygraphsgwt.client.extras.Synchronizer;
import com.github.timeu.dygraphsgwt.client.extras.SynchronizerOptions;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import sample.client.DataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uemit.seren on 8/3/15.
 */
public class SynchronizeExample extends Composite {

    FlowPanel panel = new FlowPanel();
    List<DygraphsJs> gs = new ArrayList<>();
    Synchronizer sync;
    CheckBox selectionCb = new CheckBox("Selection");
    CheckBox zoomCb = new CheckBox("Zoom");

    public SynchronizeExample() {
        initWidget(panel);
        ScriptInjector.injectExtra(ScriptInjector.EXTRAS.SYNCHRONIZE);
        VerticalPanel subPanel = new VerticalPanel();
        HorizontalPanel firstPanel = new HorizontalPanel();
        HorizontalPanel secondPanel = new HorizontalPanel();
        subPanel.add(firstPanel);
        subPanel.add(secondPanel);
        panel.add(subPanel);
        Dygraphs g;
        DygraphsOptions options = new DygraphsOptionsImpl();
        options.setRollPeriod(7);
        options.setErrorBars(true);
        for (int i = 0; i < 4; i++ ) {
            HorizontalPanel panelToAdd = firstPanel;
            if (i > 1)
                panelToAdd = secondPanel;
            g = new Dygraphs(DataUtils.noisyData(),options);
            panelToAdd.add(g);
            gs.add(g.getJSO());
        }
        // Make sure to run this after adding to the panel
        sync = Dygraphs.synchronize(gs.toArray(new DygraphsJs[]{}));

        HorizontalPanel controlPanel = new HorizontalPanel();
        controlPanel.add(new HTML("Synchronize what?"));

        zoomCb.setValue(true);
        zoomCb.addValueChangeHandler((event)->updateSettings());
        selectionCb.setValue(true);
        selectionCb.addValueChangeHandler((event)->updateSettings());

        controlPanel.add(zoomCb);
        controlPanel.add(selectionCb);
        subPanel.add(controlPanel);
    }

    private void updateSettings() {
        sync.detach();
        sync = Dygraphs.synchronize(gs.toArray(new DygraphsJs[]{}),
                new SynchronizerOptions.Builder()
                        .setSelection(selectionCb.getValue())
                        .setZoom(zoomCb.getValue()).build());
    }
}
