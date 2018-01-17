package com.guvi.gt.lataxi.net.invokers;


import org.json.JSONObject;

import java.util.HashMap;

import com.guvi.gt.lataxi.model.DriverRatingBean;
import com.guvi.gt.lataxi.net.ServiceNames;
import com.guvi.gt.lataxi.net.WebConnector;
import com.guvi.gt.lataxi.net.parsers.DriverRatingParser;
import com.guvi.gt.lataxi.net.utils.WSConstants;

public class DriverRatingInvoker extends BaseInvoker{

    public DriverRatingInvoker() {
        super();
    }

    public DriverRatingInvoker(HashMap<String, String> urlParams,
                               JSONObject postData) {
        super(urlParams, postData);
    }

    public DriverRatingBean invokeDriverRatingWS() {

        System.out.println("POSTDATA>>>>>>>" + postData);

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.DRIVER_RATING), WSConstants.PROTOCOL_HTTP, null, postData);

        String wsResponseString = webConnector.connectToPOST_service();

        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        DriverRatingBean driverRatingBean = null;
        if (wsResponseString.equals("")) {

            return driverRatingBean = null;

        } else {
            driverRatingBean = new DriverRatingBean();
            DriverRatingParser driverRatingParser = new DriverRatingParser();
            driverRatingBean = driverRatingParser.parseDriverRatingResponse(wsResponseString);
            return driverRatingBean;
        }
    }
}
