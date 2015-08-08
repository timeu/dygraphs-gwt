package sample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Created by uemit.seren on 8/5/15.
 */
public interface DataClientBundle extends ClientBundle {

    DataClientBundle INSTANCE = GWT.create(DataClientBundle.class);

    @Source("data/stockData.csv")
    TextResource stockData();

}
