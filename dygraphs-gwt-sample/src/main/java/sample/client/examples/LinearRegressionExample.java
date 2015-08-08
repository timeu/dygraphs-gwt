package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsJs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.DygraphsOptionsImpl;
import com.github.timeu.dygraphsgwt.client.callbacks.Area;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.core.client.js.JsProperty;
import com.google.gwt.core.client.js.JsType;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uemit.seren on 8/4/15.
 */
public class LinearRegressionExample  extends Composite {

    FlowPanel panel = new FlowPanel();
    List<Double[]> coeffs = new ArrayList<>();
    Dygraphs dygraphs;

    public LinearRegressionExample() {
        initWidget(panel);
        coeffs.add(null);
        coeffs.add(null);
        coeffs.add(null);
        panel.add(new HTML("<p>Click the buttons to generate linear regressions over either data \n" +
                "series. If you zoom in and then click the regression button, the regression \n" +
                "will only be run over visible points. Zoom back out to see what the local \n" +
                "regression looks like over the full data.</p> "));
        initDygraphs();
        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.setWidth("480px");
        buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_JUSTIFY);
        Button regressionY1Btn = new Button("Regression (Y1)");
        regressionY1Btn.addClickHandler((event)->regression(1));
        Button regressionY2Btn = new Button("Regression (Y2)");
        regressionY2Btn.addClickHandler((event)->regression(2));
        Button clearBtn = new Button("Clear Lines");
        clearBtn.addClickHandler((event)->clearLines());
        buttonPanel.add(regressionY1Btn);
        buttonPanel.add(regressionY2Btn);
        buttonPanel.add(clearBtn);
        panel.add(buttonPanel);
    }

    @JsType
    private static  interface RGB {
        @JsProperty
        int getG();
        @JsProperty int getR();
        @JsProperty int getB();
    }

    private void initDygraphs() {
        DygraphsOptions options = new DygraphsOptionsImpl();
        JsArray<JsArrayMixed> data = JsArray.createArray().cast();
        for (int i = 0; i < 120; i++) {
            JsArrayMixed row = JsArrayMixed.createArray().cast();
            row.push(i);
            row.push(i / 5.0 + 10.0 * Math.sin(i / 3.0));
            row.push(30.0 - i / 5.0 - 10.0 * Math.sin(i / 3.0 + 1.0));
            data.push(row);
        }
        options.setLabels(new String[]{"X","Y1","Y2"});
        options.setDrawPoints(true);
        options.setDrawAxesAtZero(true);
        options.setStrokeWidth(0.0);
        options.setUnderlayCallback((ctx,area,g)->drawLines(ctx,area));
        dygraphs = new Dygraphs(data,options);
        panel.add(dygraphs);

    }

    private void clearLines() {
        for (int i=0;i< coeffs.size();i++){
            coeffs.set(i,null);
        }
        dygraphs.getJSO().updateOptions(new DygraphsOptionsImpl(),false);
    }

    private void drawLines(Context2d ctx,Area area) {
        if (dygraphs == null || dygraphs.getJSO() == null)
            return;
        DygraphsJs g = dygraphs.getJSO();
        double[] range = g.xAxisRange();
        for (int i=0;i<coeffs.size();i++ ) {
            Double[] coeff = coeffs.get(i);
            if (coeff==null) continue;
            double a = coeff[1];
            double b = coeff[0];

            double x1 = range[0];
            double y1 = a * x1 + b;
            double x2 = range[1];
            double y2 = a * x2 + b;

            double[] p1 = g.toDomCoords(x1, y1,0);
            double[] p2 = g.toDomCoords(x2, y2,0);

            RGB c = toRGB(g.getColors()[i - 1]);
            int rC = (int) Math.floor(255 - 0.5 * (255 - c.getR()));
            int gC = (int) Math.floor(255 - 0.5 * (255 - c.getG()));
            int bC = (int) Math.floor(255 - 0.5 * (255 - c.getB()));
            String color = "rgb(" + rC + "," + gC + "," + bC + ")";
            ctx.save();
            ctx.setStrokeStyle(color);
            ctx.setLineWidth(1.0);
            ctx.beginPath();
            ctx.moveTo(p1[0], p1[1]);
            ctx.lineTo(p2[0], p2[1]);
            ctx.closePath();
            ctx.stroke();
            ctx.restore();
        }
    }

    private native final RGB toRGB(String color) /*-{
        return $wnd.Dygraph.toRGB_(color);
    }-*/;

    private void regression(int series) {
        double[] range = dygraphs.getJSO().xAxisRange();

        double sum_xy = 0.0, sum_x = 0.0, sum_y = 0.0, sum_x2 = 0.0, num = 0;
        for (int i = 0; i < dygraphs.getJSO().numRows(); i++) {
            double x = dygraphs.getJSO().getValue(i, 0);
            if (x < range[0] || x > range[1]) continue;

            double y = dygraphs.getJSO().getValue(i, series);
            num++;
            sum_x += x;
            sum_y += y;
            sum_xy += x * y;
            sum_x2 += x * x;
        }

        double a = (sum_xy - sum_x * sum_y / num) / (sum_x2 - sum_x * sum_x / num);
        double b = (sum_y - a * sum_x) / num;

        coeffs.set(series,new Double[]{b, a});
        GWT.log("coeffs(" + series + "): [" + b + ", " + a + "]");
        dygraphs.getJSO().updateOptions(new DygraphsOptionsImpl(),false);  // forces a redraw.
    }
}
