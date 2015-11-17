package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;

/**
 * Created by uemit.seren on 7/31/15.
 */
public class IndependantSeriesExample extends Composite {

    FlowPanel panel = new FlowPanel();

    public IndependantSeriesExample() {
        initWidget(panel);

        panel.add(new HTML("<p>By using the <i>connectSeparated</i> attribute, it's possible to display a chart of several time series with completely independent x-values.</p>" +
                "<p>The trick is to specify values for the series at the union of the x-values of all series. For one series' x values, specify <code>null</code> for each of the other series.</p> "));
        initDygraphs1();

        panel.add(new HTML("<p>For example, say you had two series:</p> \n" +
                "<table><tbody><tr> \n" +
                "<td valign=\"top\"> \n" +
                "<table> \n" +
                "  </table><table class=\"thinborder\"> \n" +
                "    <tbody><tr><th>x</th><th>A</th></tr> \n" +
                "    <tr><td>2</td><td>2</td></tr> \n" +
                "    <tr><td>4</td><td>6</td></tr> \n" +
                "    <tr><td>6</td><td>4</td></tr> \n" +
                "  </tbody></table> \n" +
                "</td> \n" +
                "<td valign=\"top\" style=\"padding-left:25px;\"> \n" +
                "  <table class=\"thinborder\"> \n" +
                "    <tbody><tr><th>x</th><th>B</th></tr> \n" +
                "    <tr><td>1</td><td>3</td></tr> \n" +
                "    <tr><td>3</td><td>7</td></tr> \n" +
                "    <tr><td>5</td><td>5</td></tr> \n" +
                "  </tbody></table> \n" +
                "</td> \n" +
                "</tr></tbody></table> \n" +
                "\n" +
                "<p>Then you would specify the following CSV or native data:</p> \n" +
                "<table><tbody><tr> \n" +
                "<td valign=\"top\"> \n" +
                "(CSV) \n" +
                "<pre id=\"csv1\">X,A,B\n" +
                "1,,3\n" +
                "2,2,\n" +
                "3,,7\n" +
                "4,6,\n" +
                "5,,5\n" +
                "6,4,</pre> \n" +
                "</td> \n" +
                "<td valign=\"top\" style=\"padding-left: 25px;\">\n" +
                "(native) \n" +
                "<pre id=\"native1\">[\n" +
                "  [1, null, 3],\n" +
                "  [2, 2, null],\n" +
                "  [3, null, 7],\n" +
                "  [4, 6, null],\n" +
                "  [5, null, 5],\n" +
                "  [6, 4, null]\n" +
                "]</pre> \n" +
                "</td> \n" +
                "</tr></tbody></table> "));

        panel.add(new HTML("<h3>Encoding a gap</h3>\n" +
                "<p>There's one extra wrinkle. What if one of the series has a missing \n" +
                "value, i.e. what if your series are something like </p><table><tbody><tr> \n" +
                "<td valign=\"top\"> \n" +
                "<table> \n" +
                "  </table><table class=\"thinborder\"> \n" +
                "    <tbody><tr><th>x</th><th>A</th></tr> \n" +
                "    <tr><td>2</td><td>2</td></tr> \n" +
                "    <tr><td>4</td><td>4</td></tr> \n" +
                "    <tr><td>6</td><td>(gap)</td></tr> \n" +
                "    <tr><td>8</td><td>8</td></tr> \n" +
                "    <tr><td>10</td><td>10</td></tr> \n" +
                "  </tbody></table> \n" +
                "</td> \n" +
                "<td valign=\"top\" style=\"padding-left:25px;\"> \n" +
                "  <table class=\"thinborder\"> \n" +
                "    <tbody><tr><th>x</th><th>B</th></tr> \n" +
                "    <tr><td>1</td><td>3</td></tr> \n" +
                "    <tr><td>3</td><td>5</td></tr> \n" +
                "    <tr><td>5</td><td>7</td></tr> \n" +
                "  </tbody></table> \n" +
                "</td> \n" +
                "</tr></tbody></table>"));

        initDygraphs2();
        
        panel.add(new HTML("<p>The gap would normally be encoded as a null, or missing value. But when you use <code>connectSeparatedPoints</code>, that has a special meaning. Instead, you have to use <code>NaN</code>. This is a bit of a hack, but it gets the job done.</p> \n" +
                "\n" +
                "<table><tbody><tr> \n" +
                "<td valign=\"top\"> \n" +
                "(CSV) \n" +
                "<pre id=\"csv2\">X,A,B\n" +
                "1,,3\n" +
                "2,2,\n" +
                "3,,5\n" +
                "4,4,\n" +
                "6,Nan,\n" +
                "8,8,\n" +
                "10,10,</pre> \n" +
                "</td> \n" +
                "<td valign=\"top\" style=\"padding-left: 25px;\"> \n" +
                "(native) \n" +
                "<pre id=\"native2\">[\n" +
                "  [1, null, 3],\n" +
                "  [2, 2, null],\n" +
                "  [3, null, 5],\n" +
                "  [4, 4, null],\n" +
                "  [5, null, 7],\n" +
                "  [6, NaN, null],\n" +
                "  [8, 8, null]\n" +
                "  [10, 10, null]\n" +
                "]</pre> \n" +
                "</td> \n" +
                "</tr></tbody></table>"));
    }

    private void initDygraphs1() {
        JsArray<JsArrayMixed> data = JsArray.createArray(6).cast();
        JsArrayMixed row = JsArrayMixed.createArray(3).cast();
        row.set(0,1);
        row.set(1,(JavaScriptObject)null);
        row.set(2,3);
        data.set(0,row);

        row = JsArrayMixed.createArray(3).cast();
        row.set(0,2);
        row.set(1,2);
        row.set(2,(JavaScriptObject)null);
        data.set(1,row);

        row = JsArrayMixed.createArray(3).cast();
        row.set(0,3);
        row.set(1,(JavaScriptObject)null);
        row.set(2,7);
        data.set(2,row);

        row = JsArrayMixed.createArray(3).cast();
        row.set(0,4);
        row.set(1,5);
        row.set(2,(JavaScriptObject)null);
        data.set(3,row);

        row = JsArrayMixed.createArray(3).cast();
        row.set(0,5);
        row.set(1,(JavaScriptObject)null);
        row.set(2,5);
        data.set(4,row);

        row = JsArrayMixed.createArray(3).cast();
        row.set(0, 6);
        row.set(1, 3);
        row.set(2, (JavaScriptObject) null);
        data.set(5, row);


        DygraphsOptions options = new DygraphsOptions();
        options.labels = new String[]{"x", "A", "B"};
        options.connectSeparatedPoints = true;
        options.drawPoints = true;
        Dygraphs g = new Dygraphs(data,options);
        g.getElement().getStyle().setFloat(Style.Float.RIGHT);
        g.getElement().getStyle().setMarginRight(50, Style.Unit.PX);
        g.setPixelSize(400,300);
        panel.add(g);

    }



    private void initDygraphs2() {
        DygraphsOptions options = new DygraphsOptions();
        options.drawPoints = true;
        options.connectSeparatedPoints = true;
        Dygraphs g = new Dygraphs("x,A,B\n" +
                        "1,,3\n" +
                        "2,2,\n" +
                        "3,,5\n" +
                        "4,4,\n" +
                        "5,,7\n" +
                        "6,NaN,\n" +
                        "8,8,\n" +
                        "10,10,\n",
                options);
        g.getElement().getStyle().setFloat(Style.Float.RIGHT);
        g.getElement().getStyle().setMarginRight(50, Style.Unit.PX);
        g.setPixelSize(400, 300);
        panel.add(g);
    }
}
