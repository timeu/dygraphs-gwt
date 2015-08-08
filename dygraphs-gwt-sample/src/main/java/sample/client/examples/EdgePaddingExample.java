package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.DygraphsOptionsImpl;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.RadioButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uemit.seren on 8/4/15.
 */
public class EdgePaddingExample extends Composite {

    FlowPanel panel = new FlowPanel();
    List<Dygraphs> graphs = new ArrayList<>();
    int nRows = 50;

    public EdgePaddingExample() {
        initWidget(panel);
        panel.add(new InlineLabel("Mode:"));
        RadioButton rangePadRd = new RadioButton("mode","use {x,y} RangePad");
        rangePadRd.setValue(true);
        rangePadRd.addValueChangeHandler((event) -> changeMode(true));
        panel.add(rangePadRd);
        RadioButton originalRd = new RadioButton("mode","original");
        originalRd.addValueChangeHandler((event) -> changeMode(false));
        panel.add(originalRd);
        panel.add(new HTML(""));
        panel.add(new InlineLabel("Settings:"));
        CheckBox settingCb = new CheckBox("valueRange=[-2,2]");
        settingCb.addValueChangeHandler((event) -> changeSettings(event.getValue()));
        panel.add(settingCb);
        initDygraphs();
        changeMode(true);
    }

    private void initDygraphs() {
        DygraphsOptions options = new DygraphsOptionsImpl();
        options.setLabels(new String[]{"x","A","B"});
        options.setGridLineColor("#ccc");
        options.setIncludeZero(true);
        options.setWidth(250);
        options.setHeight(130);
        for (int oy = -2; oy <= 2; ++oy) {
            HorizontalPanel chartPanel = new HorizontalPanel();
            panel.add(chartPanel);
            for (int  ox = -1; ox <= 1; ++ox) {
                JsArray<JsArrayMixed> data = JsArray.createArray().cast();
                for (int row = 0; row < nRows; ++row) {
                    double x = row * 5 / (nRows - 1);
                    JsArrayMixed r = JsArrayMixed.createArray().cast();
                    r.push(ox * 2.5 + x - 2.5);
                    r.push(oy + Math.sin(x));
                    r.push(oy + Math.round(Math.cos(x)));
                    data.push(r);
                }

                Dygraphs g = new Dygraphs(data,options);
                graphs.add(g);
                chartPanel.add(g);
            }

        }
    }

    private void changeMode(boolean rangePad) {
        DygraphsOptions opts = new DygraphsOptionsImpl();
        if (rangePad) {
            opts.setAvoidMinZero(false);
            opts.setDrawAxesAtZero(true);
            opts.setXRangePad(3);
            opts.setYRangePad(10D);
        }
        else {
            opts.setAvoidMinZero(true);
            opts.setDrawAxesAtZero(false);
            opts.setXRangePad(0);
            opts.setYRangePad(null);
        }
        updateGraphOptions(opts);
    }

    private void changeSettings(boolean active) {
        DygraphsOptions opts = new DygraphsOptionsImpl();
        opts.setValueRange(active ? new double[]{-2,2} : null);
        updateGraphOptions(opts);
    }

    private void updateGraphOptions(DygraphsOptions options) {
       for (Dygraphs g: graphs) {
           g.getJSO().updateOptions(options,false);
       }
    }

}
