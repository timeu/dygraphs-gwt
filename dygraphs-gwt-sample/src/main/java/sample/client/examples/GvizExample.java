package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.gviz.GVizChart;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.Selection;

import java.util.Date;

/**
 * Created by uemit.seren on 8/4/15.
 */
public class GvizExample extends Composite {

    FlowPanel panel = new FlowPanel();

    public GvizExample() {
        initWidget(panel);
        ChartLoader loader = new ChartLoader();
        loader.loadApi(() -> {
            initAnnotatedTimeLine();
            initSelection();
        });
    }

    private void initSelection() {
        panel.add(new HTML("<p>Get/Set/Clear selection via gviz API:</p>"));
        DataTable dataTable = DataTable.create();
        dataTable.addColumn(ColumnType.DATE,"Date");
        dataTable.addColumn(ColumnType.NUMBER, "Column A");
        dataTable.addColumn(ColumnType.NUMBER, "Column B");
        dataTable.addRow(new Date("2009/07/01"), 1, 7);
        dataTable.addRow(new Date("2009/07/08"), 2, 4);
        dataTable.addRow(new Date("2009/07/15"), 3, 3);
        dataTable.addRow(new Date("2009/07/22"), 4, 0);
        dataTable.addRow(new Date("2009/07/26"), 5, 2);
        dataTable.addRow(new Date("2009/07/27"),2,7);
        dataTable.addRow(new Date("2009/07/28"),3,8);
        dataTable.addRow(new Date("2009/08/01"),4,9);
        dataTable.addRow(new Date("2009/08/02"), 3, 10);
        dataTable.addRow(new Date("2009/08/03"), 3, 8);
        GVizChart chart = new GVizChart();
        chart.setPixelSize(700, 240);
        DygraphsOptions options = new DygraphsOptions();
        options.hideOverlayOnMouseOut = false;
        panel.add(chart);
        chart.draw(dataTable, options);
        HorizontalPanel buttonPanel = new HorizontalPanel();
        Button setSelBtn = new Button("setSelection()");
        Button clearSelBtn = new Button("clearSelection()");
        Button getSelBtn = new Button("getSelection()");
        HTML selectionPanel = new HTML();
        setSelBtn.addClickHandler((e)->{
            Selection[] sel = new Selection[2];
            Selection selection = Selection.create();
            selection.setColumn(1);
            selection.setRow(2);
            sel[0]= selection;
            selection = Selection.create();
            selection.setColumn(2);
            selection.setRow(2);
            sel[1] = selection;
            chart.setSelection(sel);
        });
        clearSelBtn.addClickHandler((e)->{
            chart.setSelection(null);
        });
        getSelBtn.addClickHandler((e) -> {
            Selection[] selections = chart.getSelection();
            String text = "<p>Selection: [";
            for (int i=0;i<selections.length;i++) {
                Selection sel = selections[0];
                text+= i + "=&gt; {row: "+ sel.getRow() +
                        " col: "+ sel.getColumn() + "}";
            }
            text+="]</p>";
            selectionPanel.setHTML(selectionPanel.getHTML()+text);
        });
        buttonPanel.add(setSelBtn);
        buttonPanel.add(getSelBtn);
        buttonPanel.add(clearSelBtn);
        panel.add(buttonPanel);

        panel.add(selectionPanel);
    }

    private void initAnnotatedTimeLine() {
        panel.add(new HTML("<p>AnnotatedTimeLine:</p>"));
        DataTable dataTable = DataTable.create();
        dataTable.addColumn(ColumnType.DATE,"Date");
        dataTable.addColumn(ColumnType.NUMBER,"Sold Pencils");
        dataTable.addColumn(ColumnType.STRING,"title1");
        dataTable.addColumn(ColumnType.STRING,"text1");
        dataTable.addColumn(ColumnType.NUMBER,"Sold Pens");
        dataTable.addColumn(ColumnType.STRING,"title2");
        dataTable.addColumn(ColumnType.STRING, "text2");
        dataTable.addRow(new Date(2008, 11, 1), 30000, null, null, 40645, null, null);
        dataTable.addRow(new Date(2008,11,2),14045,null,null,20374,null,null);
        dataTable.addRow(new Date(2008,11,3),55022,null,null,50766,null,null);
        dataTable.addRow(new Date(2008,11,4),75284,null,null,14334,"Out of Stock","Ran out of stock on pens at 4pm");
        dataTable.addRow(new Date(2008,11,5),41476,"Bought Pens","Bought 200k pens",66467,null,null);
        dataTable.addRow(new Date(2008, 11, 6), 33322, null, null, 39463, null, null);
        for (int i = 1; i < 14; i++) {
            dataTable.addRow(new Date(2008, 11 , 6 + i), i * 1000, "title1-" + i, "text1 " + (i * 1000 - i), (i * 2000 + i), "title2-" + i, "text2" + i * 1000);
        }

        GVizChart chart = new GVizChart();
        chart.setPixelSize(700, 240);
        DygraphsOptions options = new DygraphsOptions();
        options.displayAnnotations = true;
        options.labelsKMB = true;
        panel.add(chart);
        chart.draw(dataTable,options);
    }
}
