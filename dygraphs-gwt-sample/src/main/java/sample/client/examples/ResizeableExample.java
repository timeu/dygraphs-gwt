package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import sample.client.DataUtils;

/**
 * Created by uemit.seren on 8/4/15.
 */
public class ResizeableExample extends Composite {

    FlowPanel panel = new FlowPanel();
    Dygraphs dygraphs;

    public ResizeableExample() {
        initWidget(panel);
        panel.getElement().getStyle().setPosition(Style.Position.ABSOLUTE);
        panel.getElement().getStyle().setLeft(0, Style.Unit.PX);
        panel.getElement().getStyle().setRight(0, Style.Unit.PX);
        panel.getElement().getStyle().setTop(0, Style.Unit.PX);
        panel.getElement().getStyle().setBottom(0, Style.Unit.PX);
    }
    @Override
    protected void onAttach() {
        super.onAttach();
        if (dygraphs == null) {
            DygraphsOptions options = new DygraphsOptions();
            options.rollPeriod = 7;
            options.errorBars = true;
            dygraphs = new Dygraphs(DataUtils.noisyData(), options);
            dygraphs.setHeight("100%");
            dygraphs.setWidth("100%");
            this.panel.add(dygraphs);
        }
        else {
            dygraphs.resize();
        }

    }
}
