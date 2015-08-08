package sample.client;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * Created by uemit.seren on 7/29/15.
 */
public class ExampleInfo {

    private final String example;
    private final IsWidget widget;
    private final String nameToken;

    public ExampleInfo(String example, IsWidget widget) {
        this(example,widget,null);
    }

    public ExampleInfo(String example, IsWidget widget, String nameToken) {
        this.example = example;
        this.widget = widget;
        this.nameToken = nameToken;
    }

    public String getExample() {
        return example;
    }

    public IsWidget getWidget() {
        return widget;
    }

    public String getNameToken() {
        if (nameToken != null && !nameToken.isEmpty()) {
            return nameToken;
        }
        return example;
    }
}
