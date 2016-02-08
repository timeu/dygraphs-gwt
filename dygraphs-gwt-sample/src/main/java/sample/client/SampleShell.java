package sample.client;

import com.google.gwt.cell.client.AbstractSafeHtmlCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.i18n.client.HasDirection;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SingleSelectionModel;
import sample.client.examples.AnnotationExample;
import sample.client.examples.CSSLabelStylingExample;
import sample.client.examples.CustomInteractioModelsExample;
import sample.client.examples.DynamicExample;
import sample.client.examples.EdgePaddingExample;
import sample.client.examples.FunctionPlotterExample;
import sample.client.examples.GvizExample;
import sample.client.examples.HighlightClosestSeriesExample;
import sample.client.examples.HighlightRegionExample;
import sample.client.examples.HighlightWeekendsExample;
import sample.client.examples.IndependantSeriesExample;
import sample.client.examples.LegendFormatterExample;
import sample.client.examples.LinearRegressionExample;
import sample.client.examples.LinkInteractionExample;
import sample.client.examples.PerSeriesPropertiesExample;
import sample.client.examples.RangeSelectorExample;
import sample.client.examples.ResizeableExample;
import sample.client.examples.RollPeriodsErrorBarsExample;
import sample.client.examples.SimpleExample;
import sample.client.examples.StockExample;
import sample.client.examples.SynchronizeExample;
import sample.client.examples.TimeSeriesExample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uemit.seren on 7/29/15.
 */
public class SampleShell extends ResizeComposite {

    interface SampleShellUiBinder extends UiBinder<Widget, SampleShell> {
    }

    private static SampleShellUiBinder uiBinder = GWT.create(
            SampleShellUiBinder.class);
    @UiField(provided=true)
    CellList<ExampleInfo> navList;
    @UiField
    ScrollPanel examplePanel;
    @UiField
    DivElement nameElem;
    @UiField
    Anchor tabExample;
    @UiField
    Anchor tabSource;
    @UiField
    Anchor tabStyle;

    private HTML contentSource = new HTML();
    private String sourceContent;

    private static final String SELECTED_TAB_COLOR = "#333333";
    private SingleSelectionModel<ExampleInfo> selectionModel = new SingleSelectionModel<>();

    public interface CellListResource extends CellList.Resources {


        CellListResource INSTANCE = GWT.create(CellListResource.class);

        @Source({CellList.Style.DEFAULT_CSS, "CellTableStyle.css"})
        @Override
        CellList.Style cellListStyle();
    }


    private static class ExampleCellRenderer extends AbstractSafeHtmlRenderer<ExampleInfo> {

        @Override
        public SafeHtml render(ExampleInfo object) {
            return SafeHtmlUtils.fromTrustedString(object.getExample());
        }
    }

    private static class ExampleCell extends AbstractSafeHtmlCell<ExampleInfo> {

        public ExampleCell() {
            super(new ExampleCellRenderer());
        }

        @Override
        protected void render(Context context, SafeHtml data, SafeHtmlBuilder sb) {
            if (data != null) {
                sb.append(data);
            }
        }
    }

    private List<ExampleInfo> exampleInfos = new ArrayList<ExampleInfo>();

    public SampleShell() {
        initList();
        contentSource.getElement().getStyle().setBackgroundColor("#eee");
        contentSource.getElement().getStyle().setMargin(10.0, Style.Unit.PX);
        contentSource.getElement().getStyle().setProperty(
                "border", "1px solid #c3c3c3");
        contentSource.getElement().getStyle().setProperty("padding", "10px 2px");
        CellListResource.INSTANCE.cellListStyle().ensureInjected();
        navList = new CellList(new ExampleCell(),CellListResource.INSTANCE);
        navList.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.DISABLED);
        initWidget(uiBinder.createAndBindUi(this));
        // Push data into the CellList.
        navList.setRowCount(exampleInfos.size(), true);
        navList.setRowData(0, exampleInfos);

        // Add a selection model using the same keyProvider.

        navList.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(event -> {
            ExampleInfo selected = selectionModel.getSelectedObject();
            if (selected != null) {
                History.newItem(selected.getNameToken(), true);
            }
        });
        // Setup a history handler to reselect the associate menu item.
        final ValueChangeHandler<String> historyHandler = event -> {
            // Get the content widget associated with the history token.
            ExampleInfo exampleInfo = getExcampleForToken(
                    event.getValue());
            if (exampleInfo == null) {
                return;
            }
            // Select the node in the tree.
            selectionModel.setSelected(exampleInfo, true);

            // Display the content widget.
            showExample(exampleInfo);
        };
        History.addValueChangeHandler(historyHandler);

        // Show the initial example.
        if (History.getToken().length() > 0) {
            History.fireCurrentHistoryState();
        } else {
            ExampleInfo exampleInfo = exampleInfos.get(0);
            selectionModel.setSelected(exampleInfo, true);
        }
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        if (examplePanel.getWidget() == null) {
            examplePanel.setWidget(selectionModel.getSelectedObject().getWidget());
        }

    }


    private ExampleInfo getExcampleForToken(String token) {
        if (token == null || token.isEmpty())
            return null;
        for (ExampleInfo exampleInfo : exampleInfos) {
            if (token.equalsIgnoreCase(exampleInfo.getNameToken()) || token.equalsIgnoreCase(exampleInfo.getExample())) {
                return exampleInfo;
            }
        }
        return null;
    }

    private void initList() {
        exampleInfos.add(new ExampleInfo("Simple", new SimpleExample(),"simple"));
        exampleInfos.add(new ExampleInfo("Annotations", new AnnotationExample(),"annotations"));
        exampleInfos.add(new ExampleInfo("Time Series Drawing Demo", new TimeSeriesExample(),"drawing"));
        exampleInfos.add(new ExampleInfo("Dynamic Update",new DynamicExample(),"dynamic-update"));
        exampleInfos.add(new ExampleInfo("Highlight Closest Series",new HighlightClosestSeriesExample(),"highlighted-series"));
        exampleInfos.add(new ExampleInfo("Highlight Region",new HighlightRegionExample(),"highlighted-region"));
        exampleInfos.add(new ExampleInfo("Highlight Weekends",new HighlightWeekendsExample(),"highlighted-weekends"));
        exampleInfos.add(new ExampleInfo("Independent Series",new IndependantSeriesExample(),"independent-series"));
        exampleInfos.add(new ExampleInfo("Function Plotter",new FunctionPlotterExample(),"plotter"));
        exampleInfos.add(new ExampleInfo("Link Interaction",new LinkInteractionExample(),"link-interaction"));
        exampleInfos.add(new ExampleInfo("Per-series properties",new PerSeriesPropertiesExample(),"per-series"));
        exampleInfos.add(new ExampleInfo("Synchronization",new SynchronizeExample(),"synchronize"));
        exampleInfos.add(new ExampleInfo("Range Selector",new RangeSelectorExample(),"range-selector"));
        exampleInfos.add(new ExampleInfo("Resizable Graph",new ResizeableExample(),"resize"));
        exampleInfos.add(new ExampleInfo("Stock Chart Demo",new StockExample(),"stock"));
        exampleInfos.add(new ExampleInfo("CSS label styling",new CSSLabelStylingExample(),"styled-chart-labels"));
        exampleInfos.add(new ExampleInfo("Roll Periods and Error Bars",new RollPeriodsErrorBarsExample(),"temperature-sf-ny"));
        exampleInfos.add(new ExampleInfo("Custom interaction models",new CustomInteractioModelsExample(),"interaction"));
        exampleInfos.add(new ExampleInfo("Linear Regressions",new LinearRegressionExample(),"linear-regression"));
        exampleInfos.add(new ExampleInfo("Edge Padding",new EdgePaddingExample(),"edge-padding"));
        exampleInfos.add(new ExampleInfo("Google Visualization Example",new GvizExample(),"gviz"));

        exampleInfos.add(new ExampleInfo("Legend Formatter",new LegendFormatterExample(),"legend-formatter"));
    }

    @UiHandler("tabExample")
    public void onClickTabExample(ClickEvent e) {
        showExample(selectionModel.getSelectedObject());
    }

    private void showExample(ExampleInfo exampleInfo) {
        // Set the highlighted tab.
        tabExample.getElement().getStyle().setColor(SELECTED_TAB_COLOR);
        tabStyle.getElement().getStyle().clearColor();
        tabSource.getElement().getStyle().clearColor();
        if (exampleInfo == null)
            return;
        nameElem.setInnerText(exampleInfo.getExample());
        if (!isAttached())
            return;
        examplePanel.setWidget(exampleInfo.getWidget());
    }

    @UiHandler("tabStyle")
    public void onClickTabCSS(ClickEvent e) {
        showStyle();
    }

    private void showStyle() {
        // Set the highlighted tab.
        tabExample.getElement().getStyle().clearColor();
        tabStyle.getElement().getStyle().setColor(SELECTED_TAB_COLOR);
        tabSource.getElement().getStyle().clearColor();

        contentSource.setHTML("CSS Style", HasDirection.Direction.LTR);
        examplePanel.setWidget(new ScrollPanel(contentSource));
    }

    @UiHandler("tabSource")
    public void onClickTabSource(ClickEvent e) {
        showSource();
    }

    private void showSource() {
        // Set the highlighted tab.
        contentSource.setHTML("Loading...");
        examplePanel.setWidget(contentSource);
        ExampleInfo exampleInfo = selectionModel.getSelectedObject();
        tabExample.getElement().getStyle().clearColor();
        tabStyle.getElement().getStyle().clearColor();
        tabSource.getElement().getStyle().setColor(SELECTED_TAB_COLOR);
        if (exampleInfo == null)
            return;
        String sourceFileName = exampleInfo.getWidget().getClass().getSimpleName()+".java";

        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET,getSourceUrl(sourceFileName));
        try {
            builder.sendRequest("", new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    contentSource.setHTML("<pre>"+response.getText()+"</pre>", HasDirection.Direction.LTR);
                    examplePanel.setWidget(new ScrollPanel(contentSource));
                }
                @Override
                public void onError(Request request, Throwable throwable) {
                    GWT.log(throwable.getMessage());
                }
            });
        }
        catch (RequestException ex) {
            GWT.log(ex.getMessage());
        }

    }

    private String getSourceUrl(String sourceFileName) {
        return "https://cdn.rawgit.com/timeu/dygraphs-gwt/master/dygraphs-gwt-sample/src/main/java/sample/client/examples/"+sourceFileName;

    }

}
