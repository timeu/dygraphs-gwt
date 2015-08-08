package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.github.timeu.dygraphsgwt.client.DygraphsOptionsImpl;
import com.github.timeu.dygraphsgwt.client.callbacks.Annotation;
import com.github.timeu.dygraphsgwt.client.callbacks.DataFunction;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uemit.seren on 7/29/15.
 */
public class AnnotationExample extends Composite {

    private VerticalPanel panel = new VerticalPanel();
    private FlowPanel list = new FlowPanel();
    private FlowPanel events = new FlowPanel();
    private int lastAnnotationX = 0;
    private List<Annotation> annotations = new ArrayList<>();
    private Dygraphs dygraphs;
    private String saveBg;
    private int num = 0;

    public AnnotationExample() {

        initWidget(panel);
        panel.add(new HTML("<p>Click any point to add an annotation to it or click 'Add Annotation'."));
        HorizontalPanel buttonPanel = new HorizontalPanel();
        Button addBtn = new Button("Add Annotation");
        addBtn.addClickHandler(event -> {
            int x = lastAnnotationX + 2;
            Annotation annotation = new Annotation("line","200610"+x);
            annotation.shortText = String.valueOf(x);
            annotation.text="Line "+x;
            annotation.tickHeight=10;
            annotations.add(annotation);
            lastAnnotationX = x;
            dygraphs.getJSO().setAnnotations(annotations.toArray(new Annotation[]{}));
        });

        Button shoveBottomBtn = new Button("Shove to bottom");
        shoveBottomBtn.addClickHandler(event ->{
            boolean toBottom = shoveBottomBtn.getText().equals("Shove to bottom");
            Annotation[] annotations = dygraphs.getJSO().annotations();
            for (Annotation annotation: annotations) {
                annotation.attachAtBottom = toBottom;
            }
            dygraphs.getJSO().setAnnotations(annotations);
            if (toBottom) {
                shoveBottomBtn.setText("Lift back up");
            }
            else {
                shoveBottomBtn.setText("Shove to bottom");
            }
        });

        buttonPanel.add(addBtn);
        buttonPanel.add(shoveBottomBtn);
        panel.add(buttonPanel);
        panel.add(list);
        initDygraphs();
        panel.add(events);

    }

    private static Annotation createAnnotation(int x) {
        Annotation annotation = new Annotation("sine wave","200610" +x);
        annotation.shortText=String.valueOf(x);
        annotation.text="Stock Market Crash "+ x;
        return annotation;
    }

    private static String getNameFromAnnotation(Annotation annotation) {
        return "(" + annotation.series + ", " + annotation.x + ")";
    }

    public void initDygraphs() {
        for (int x=10;x< 15;x+=2) {
            annotations.add(createAnnotation(x));
            lastAnnotationX = x;
        }
        Annotation specialAnnot = new Annotation("another line", "20061013");
        specialAnnot.icon ="http://dygraphs.com/gallery/images/dollar.png";
        specialAnnot.width=18D;
        specialAnnot.height=23D;
        specialAnnot.tickHeight=4;
        specialAnnot.text="Anothjer one";
        specialAnnot.setCssClas("annotation");
        specialAnnot.clickHandler =(annot, point, dygraph, event) -> events.add(new HTML("Special Handler"));
        annotations.add(specialAnnot);
        DygraphsOptions options = new DygraphsOptionsImpl();
        options.setRollPeriod(1);
        options.setShowRoller(true);
        options.setWidth(480);
        options.setHeight(320);
        options.setDrawCallback((g, initialDraw) -> {
            Annotation[] anns = g.annotations();
            list.clear();
            for (Annotation annotation : anns) {
                String name = getNameFromAnnotation(annotation);
                String shortText = (annotation.shortText != null ? annotation.shortText : "(icon)");
                HTML listItem = new HTML("<div id='" + name + "'>" + name + ": " + shortText + " -> " + annotation.text + " </div>");
                list.add(listItem);
            }
        });
        options.setAnnotationClickHandler((ann, point, dygraphjs, event) -> {
            events.add(new HTML("<div>click: " + getNameFromAnnotation(ann) + "</div>"));
        });

        options.setAnnotationDblClickHandler((ann, point, dygraphjs, event) -> {
            events.add(new HTML("<div>dblclick: " + getNameFromAnnotation(ann) + "</div>"));
        });
        options.setAnnotationMouseOverHandler((ann, point, dygraphjs, event) -> {
            DOM.getElementById(getNameFromAnnotation(ann)).getStyle().setFontWeight(Style.FontWeight.BOLD);
            saveBg = ann.div.getStyle().getBackgroundColor();
            ann.div.getStyle().setBackgroundColor("#ddd");
        });
        options.setAnnotationMouseOutHandler((ann, point, dygraphjs, event) -> {
            DOM.getElementById(getNameFromAnnotation(ann)).getStyle().setFontWeight(Style.FontWeight.NORMAL);
            ann.div.getStyle().setBackgroundColor(saveBg);
            GWT.log("mouseout");
        });
        options.setPointClickCallback((event, point) -> {
            if (point.getAnnotation()!=null) return;

            // If not, add one.
            Annotation annotation = new Annotation(point.getName());
            annotation.xval = point.getXval();
            annotation.shortText = String.valueOf(num);
            annotation.text = "Annotation #"+num;
            annotations.add(annotation);
            dygraphs.getJSO().setAnnotations(annotations.toArray(new Annotation[]{}));
            num++;
        });



        dygraphs = new Dygraphs(()-> {
            String r = "date,parabola,line,another line,sine wave\n";
            for (int i = 1; i <= 31; i++) {
                r += "200610" + (i < 10 ? "0"+i : String.valueOf(i));
                r += "," + 10*(i*(31-i));
                r += "," + 10*(8*i);
                r += "," + 10*(250 - 8*i);
                r += "," + 10*(125 + 125 * Math.sin(0.3*i));
                r += "\n";
            }
            return r;
        },options);
        panel.add(dygraphs);
        dygraphs.getJSO().setAnnotations(annotations.toArray(new Annotation[]{}));
    }



}
