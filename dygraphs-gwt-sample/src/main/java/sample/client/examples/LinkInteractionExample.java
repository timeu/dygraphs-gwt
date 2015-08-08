package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.DygraphsOptionsImpl;
import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.core.client.JsDate;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.Date;

/**
 * Created by uemit.seren on 8/3/15.
 */
public class LinkInteractionExample extends Composite {

    FlowPanel panel = new FlowPanel();
    double[] desiredRange;
    double[] origRange = new double[2];
    Date lastTime = new Date();
    Dygraphs dygraphs;


    public LinkInteractionExample() {
        initWidget(panel);
        initDygraphs();
        HorizontalPanel zoomPanel = new HorizontalPanel();
        zoomPanel.setSpacing(10);
        zoomPanel.add(new HTML("<b>Zoom</b>"));
        Anchor hourLink = new Anchor("hour");
        hourLink.addClickHandler((event)->zoom(3600));
        Anchor dayLink = new Anchor("day");
        dayLink.addClickHandler((event) -> zoom(86400));
        Anchor weekLink = new Anchor("week");
        weekLink.addClickHandler((event) -> zoom(604800));
        Anchor monthLink = new Anchor("month");
        monthLink.addClickHandler((event) -> zoom(30 * 86400));
        Anchor fullLink = new Anchor("full");
        fullLink.addClickHandler((event) -> reset());
        zoomPanel.add(hourLink);
        zoomPanel.add(dayLink);
        zoomPanel.add(weekLink);
        zoomPanel.add(monthLink);
        zoomPanel.add(fullLink);
        zoomPanel.add(new HTML("<b>Pan:</b>"));
        Anchor panLeft = new Anchor("left");
        panLeft.addClickHandler((e)->pan(-1));
        Anchor panRight = new Anchor("right");
        panRight.addClickHandler((e)->pan(1));
        zoomPanel.add(panLeft);
        zoomPanel.add(panRight);
        panel.add(zoomPanel);

    }

    private void initDygraphs() {

        JsArray<JsArrayMixed> r = JsArray.createArray().cast();
        double baseTime = Date.parse("2008/07/01");
        int num = (int) (24 * 0.25 * 365);
        for (int i = 0; i < num; i++) {
            JsArrayMixed row = JsArrayMixed.createArray(3).cast();
            row.set(0,JsDate.create(baseTime + (i * 3600 * 1000)));
            row.set(1,i + 50 * (i % 60));  // line
            row.set(2,i * (num - i) * 4.0 / num ); // parabola
            r.push(row);
        }
        origRange[0] = r.get(0).getNumber(0);
        origRange[1] = r.get(r.length()-1).getNumber(0);
        DygraphsOptions options = new DygraphsOptionsImpl();
        options.setRollPeriod(7);;
        options.setAnimatedZooms(true);
        options.setWidth(600);
        options.setHeight(300);
        options.setLabels(new String[]{"Date", "a", "b"});
        dygraphs = new Dygraphs(r,options);
        panel.add(dygraphs);
    }

    private void reset() {
        desiredRange = origRange;
        animate();
    }

    private void approachRange() {
       if (desiredRange == null)
           return;
        double[] range = dygraphs.getJSO().xAxisRange();
        if (Math.abs(desiredRange[0] - range[0]) < 60 &&
                Math.abs(desiredRange[1] - range[1]) < 60) {
            DygraphsOptions options = new DygraphsOptionsImpl();
            options.setDateWindow(desiredRange);
            dygraphs.getJSO().updateOptions(options,false);
            // (do not set another timeout.)
        } else {
            double[] newRange = new double[2];
            newRange[0] = 0.5 * (desiredRange[0] + range[0]);
            newRange[1] = 0.5 * (desiredRange[1] + range[1]);
            DygraphsOptions options = new DygraphsOptionsImpl();
            options.setDateWindow(newRange);
            dygraphs.getJSO().updateOptions(options,false);
            animate();
        }
    }

    private void animate() {
        AnimationScheduler.get().requestAnimationFrame((timestamp) -> {
            if ((timestamp - lastTime.getTime()) >= 50) {
                approachRange();
            } else {
                animate();
            }
        });
    }

    private void zoom(double res) {
        double[] w = dygraphs.getJSO().xAxisRange();
        desiredRange = new double[]{w[0], w[0] + res * 1000};
        animate();

    }

    private void pan(int dir) {
        double[] w = dygraphs.getJSO().xAxisRange();
        double scale = w[1] - w[0];
        double amount = scale * 0.25 * dir;
        desiredRange = new double[]{ w[0] + amount, w[1] + amount };
        animate();
    }
}
