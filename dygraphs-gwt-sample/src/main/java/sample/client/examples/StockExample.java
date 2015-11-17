package sample.client.examples;

import com.github.timeu.dygraphsgwt.client.Dygraphs;
import com.github.timeu.dygraphsgwt.client.DygraphsOptions;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import sample.client.DataUtils;

/**
 * Created by uemit.seren on 8/4/15.
 */
public class StockExample extends Composite {

    FlowPanel panel = new FlowPanel();
    Dygraphs dygraphs;
    Button logBtn;
    Button linearBtn;

    public StockExample() {
        initWidget(panel);
        initDygraphs();
        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.setWidth("600px");
        buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        linearBtn = new Button("Linear Scale");
        linearBtn.addClickHandler((event) -> setScale(false));
        logBtn = new Button("Log Scale");
        logBtn.addClickHandler((event)->setScale(true));
        logBtn.setEnabled(false);
        buttonPanel.add(linearBtn);
        buttonPanel.add(logBtn);
        panel.add(buttonPanel);
    }

    private void initDygraphs() {
        DygraphsOptions options = new DygraphsOptions();
        options.logscale = true;
        options.customBars = true;
        dygraphs = new Dygraphs(DataUtils.stockData(),options);
        dygraphs.setPixelSize(600,300);
        panel.add(dygraphs);

    }

    private void setScale(boolean isLog) {
        DygraphsOptions options = new DygraphsOptions();
        options.logscale = isLog;
        logBtn.setEnabled(!isLog);
        linearBtn.setEnabled(isLog);
        dygraphs.getJSO().updateOptions(options,false);
    }
}
