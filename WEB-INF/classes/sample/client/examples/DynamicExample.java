package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.DygraphsOptionsImpl;
import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by uemit.seren on 7/30/15.
 */
public class DynamicExample extends Composite {

    FlowPanel panel = new FlowPanel();
    Date lastTime = new Date();
    Dygraphs dygraphs;
    JsArray<JsArrayMixed> data = JsArray.createArray().cast();

    public DynamicExample() {
        initWidget(panel);
        initDygraphs();
        panel.add(new HTML("<p>This test is modeled after a \n" +
                "<a href=\"http://www.highcharts.com/demo/?example=dynamic-update&amp;theme=default\">highcharts\n" +
                "test</a>. New points should appear once per second. Try zooming and \n" +
                "panning over to the right edge to watch them show up.</p>"));
    }

    private void initDygraphs() {


        Date t = new Date();
        for (int i = 10; i >= 0; i--) {
            double x = new Date(t.getTime() - i * 1000).getTime();
            JsArrayMixed row = JsArrayMixed.createArray().cast();
            row.push(x);
            row.push(Math.random());
            data.push(row);
        }

        DygraphsOptions options = new DygraphsOptionsImpl();
        options.setDrawPoints(true);
        options.setShowRoller(true);
        options.setLabels(new String[]{"Time", "Random"});
        dygraphs = new Dygraphs(data,options);
        dygraphs.setPixelSize(600,300);
        // It sucks that these things aren't objects, and we need to store state in window.
        AnimationScheduler.get().requestAnimationFrame((timestamp) -> this.render(timestamp), getElement());
        panel.add(dygraphs);
    }

    public void render(double currentTimeStamp) {
        Date currentTime = new Date();
        if ((currentTime.getTime()- lastTime.getTime()) >= 1000) {
            lastTime = currentTime;
            double y = Math.random();
            JsArrayMixed row = JsArrayMixed.createArray().cast();
            row.push(currentTime.getTime());
            row.push(y);
            data.push(row);
            DygraphsOptions opt = new DygraphsOptionsImpl();
            opt.setArrayFile(data);
            dygraphs.getJSO().updateOptions(opt,false);
        }
        AnimationScheduler.get().requestAnimationFrame((timestamp) -> this.render(currentTimeStamp),dygraphs.getElement());
    }
}
