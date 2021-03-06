package com.guvi.gt.lataxi.net.invokers;


import org.json.JSONObject;

import java.util.HashMap;

import com.guvi.gt.lataxi.model.LocationBean;
import com.guvi.gt.lataxi.net.ServiceNames;
import com.guvi.gt.lataxi.net.WebConnector;
import com.guvi.gt.lataxi.net.parsers.SavedLocationParser;
import com.guvi.gt.lataxi.net.utils.WSConstants;

public class SavedLocationInvoker extends BaseInvoker {

    public SavedLocationInvoker() {
        super();
    }

    public SavedLocationInvoker(HashMap<String, String> urlParams,
                                JSONObject postData) {
        super(urlParams, postData);
    }

    public LocationBean invokeDummyWS() {

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.SAVED_LOCATION), WSConstants.PROTOCOL_HTTP, urlParams, null);

        //webConnector= new WebConnector(new StringBuilder(ServiceNames.MODELS), WSConstants.PROTOCOL_HTTP, null);
//    String wsResponseString=webConnector.connectToPOST_service();
        String wsResponseString = webConnector.connectToGET_service(true);
        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        LocationBean locationBean = null;
        if (wsResponseString.equals("")) {
            /*registerBean=new RegisterBean();
            registerBean.setWebError(true);*/
            return locationBean = null;
        } else {
            locationBean = new LocationBean();
            SavedLocationParser savedLocationParser = new SavedLocationParser();
            locationBean = savedLocationParser.parseSavedLocationResponse(wsResponseString);
            return locationBean;

        }
    }
}