package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.callbacks.ClickCallback;
import com.github.timeu.dygraphsgwt.client.options.HighlightSeriesOptions;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Created by uemit.seren on 7/30/15.
 */
public class HighlightClosestSeriesExample extends Composite {

    HorizontalPanel panel = new HorizontalPanel();


    public HighlightClosestSeriesExample() {
        initWidget(panel);
        makeGraph("highlightfew", 20, 50, false);
        makeGraph("highlightfew", 10, 20, true);
        makeGraph("highlightmany", 75, 50, false);
        makeGraph("highlightmany", 40, 50, true);
    }



    private JsArray<JsArrayMixed> getData(int numSeries,int numRows, boolean isStacked) {
        //List<Object[]> data = new ArrayList<>();
        JsArray<JsArrayMixed> data = JsArrayMixed.createArray().cast();

        for (int j = 0; j < numRows; ++j) {
            JsArrayMixed row = JsArrayMixed.createArray(numSeries+1).cast();
            row.set(0,j);
            data.push(row);
        }
        for (int i = 1; i < numSeries; ++i) {
            double val = 0;
            for (int j = 0; j < numRows; ++j) {
                if (isStacked) {
                    val = Math.random();
                } else {
                    val += Math.random() - 0.5;
                }
                JsArrayMixed row = data.get(j).cast();
                row.set(i,val);
            }
        }
        return data;
    }

    private void makeGraph(String cssName,int numSeries,int numRows, boolean isStacked) {

        String[] labels = new String[numSeries];
        labels[0] = "x";

        for (int i = 0; i < numSeries; ++i) {
            String label = "" + i;
            label = "s" + "000".substring(label.length()) + label;
            labels[i + 1] = label;
        }

        DygraphsOptions options = new DygraphsOptions();
        HighlightSeriesOptions highlightOptions = new HighlightSeriesOptions();
        highlightOptions.highlightCircleSize =5;
        highlightOptions.strokeBorderWidth=1;
        highlightOptions.strokeWidth = 3;
        options.width = 400;
        options.height = 320;
        options.labels = labels;
        options.stackedGraph = isStacked;
        options.highlightCircleSize  = 2;
        options.strokeWidth = 1;
        options.highlightSeriesOpts = highlightOptions;

        Dygraphs g = new Dygraphs(getData(numSeries,numRows,isStacked),options);

        ClickCallback clickCallback = (event,x,point) -> {
            if (g.getJSO().isSeriesLocked()) {
                g.getJSO().clearSelection();
            } else {
                g.getJSO().setSelection(g.getJSO().getSelection(), g.getJSO().getHighlightSeries(), true);
            }
        };
        DygraphsOptions newOptions = new DygraphsOptions();
        newOptions.clickCallback = clickCallback;
        g.getJSO().updateOptions(newOptions, false);
        g.getElement().addClassName(cssName);

        panel.add(g);
        g.getJSO().setSelection(0,"s005",false);

    }
}
