package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by uemit.seren on 7/31/15.
 */
public class FunctionPlotterExample extends Composite {

    FlowPanel panel = new FlowPanel();
    TextArea textArea = new TextArea();
    ListBox presetsDD = new ListBox();
    TextBox fromTb = new TextBox();
    TextBox toTb = new TextBox();
    Dygraphs dygraphs;
    int width = 600;
    int height = 300;
    Map<String,String[]> presets = new HashMap<>();

    public FunctionPlotterExample() {
        presets.put("id",new String[]{"-10","10","function(x) {\n  return x;\n}"});
        presets.put("sine",new String[]{"-10","10","function(x) {\n  return Math.sin(x);\n}"});
        presets.put("taylor",new String[]{"-3","3","function(x) {\n  return [Math.cos(x), 1 - x*x/2 + x*x*x*x/24];\n}"});
        presets.put("sawtooth",new String[]{"-10","10","function(x) {\n  var y = 0;\n  for (var i = 1; i < 20; i+=2) {\n    y += Math.sin(i * x)/i;\n  }\n  var final = 1 - 2*(Math.abs(Math.floor(x / Math.PI)) % 2);\n  return [4/Math.PI * y, final];\n}"});

        initWidget(panel);
        panel.add(new HTML("<p><b>Equation: </b><br>"));
        textArea.setVisibleLines(10);
        textArea.setCharacterWidth(80);
        textArea.setValue("function(x) {\n" +
                "  return [0.1 * x, 0.1 * x + Math.sin(x), 0.1*x + Math.cos(x)];\n" +
                "}");
        panel.add(textArea);

        presetsDD.addItem("(custom)", "custom");
        presetsDD.addItem("Identity", "id");
        presetsDD.addItem("Sine Wave", "sine");
        presetsDD.addItem("Taylor series", "taylor");
        presetsDD.addItem("Sawtooth", "sawtooth");
        presetsDD.setSelectedIndex(0);
        presetsDD.getElement().getStyle().setDisplay(Style.Display.INLINE_BLOCK);

        presetsDD.addChangeHandler((event) -> {
            String value = presetsDD.getSelectedValue();
            if (value.equalsIgnoreCase("custom")) { return; }

            String[] preset = presets.get(value);
            fromTb.setValue(preset[0]);
            toTb.setValue(preset[1]);
            textArea.setValue(preset[2]);
            plot();
        });

        HorizontalPanel presetContainer = new HorizontalPanel();
        presetContainer.add(new HTML("<b>Preset functions:</b>"));
        presetContainer.add(presetsDD);
        panel.add(presetContainer);
        panel.add(new HTML("<p></p>"));
        HorizontalPanel rangeContainer = new HorizontalPanel();
        rangeContainer.add(new HTML("<b>x range:</b>"));
        fromTb.setVisibleLength(5);
        fromTb.setValue("-10");

        toTb.setVisibleLength(5);
        toTb.setValue("10");
        rangeContainer.add(fromTb);
        rangeContainer.add(new InlineLabel("to"));
        rangeContainer.add(toTb);
        panel.add(rangeContainer);
        Button plotBtn = new Button("Plot");

        plotBtn.addClickHandler((ev)->plot());

        panel.add(new HTML("<p></p>"));
        panel.add(plotBtn);
        plot();

    }



    private void plot() {
        if (dygraphs != null) {
            if (panel.remove(dygraphs)) ;
        }

        String eq = textArea.getValue();


        double x1 = Double.parseDouble(fromTb.getValue());
        double x2 = Double.parseDouble(toTb.getValue());
        double xs = 1.0 * (x2 - x1) / width;

        JsArray<JsArrayMixed> data = JsArray.createArray().cast();
        for (int i = 0; i < width; i++) {
            double x = x1 + i * xs;
            JsArrayNumber y = evalFn(eq, x);
            JsArrayMixed row = JsArrayMixed.createArray().cast();
            row.push(x);
            for (int j = 0; j < y.length(); j++) {
                row.push(y.get(j));
            }
            data.push(row);
        }
        dygraphs = new Dygraphs(data,null);
        dygraphs.setPixelSize(width,height);
        panel.add(dygraphs);
    }

    private native JsArrayNumber evalFn(String eq,double x)
    /*-{
        eval("fn = "+ eq);
        y = fn(x);
        if (y.length > 0) {
            return y
        }
        return [y];
    }-*/;
}
