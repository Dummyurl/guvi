package com.guvi.gt.lataxidriver.net.invokers;

import org.json.JSONObject;

import java.util.HashMap;

import com.guvi.gt.lataxidriver.model.AccessibilityBean;
import com.guvi.gt.lataxidriver.net.ServiceNames;
import com.guvi.gt.lataxidriver.net.WebConnector;
import com.guvi.gt.lataxidriver.net.parsers.DriverAccessibilityParser;
import com.guvi.gt.lataxidriver.net.utils.WSConstants;

public class DriverAccessibilityInvoker extends BaseInvoker {

    public DriverAccessibilityInvoker() {
        super();
    }

    public DriverAccessibilityInvoker(HashMap<String, String> urlParams,
                                      JSONObject postData) {
        super(urlParams, postData);
    }

    public AccessibilityBean invokeDriverAccessibilityInvokerWS() {

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.DRIVER_ACCESSIBILITY_FETCH), WSConstants.PROTOCOL_HTTP, urlParams, null);

        //webConnector= new WebConnector(new StringBuilder(ServiceNames.MODELS), WSConstants.PROTOCOL_HTTP, null);
//    String wsResponseString=webConnector.connectToPOST_service();
        String wsResponseString = webConnector.connectToGET_service();
        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        AccessibilityBean accessibilityBean = null;
        if (wsResponseString.equals("")) {
            /*registerBean=new RegisterBean();
            registerBean.setWebError(true);*/
            return accessibilityBean = null;
        } else {
            accessibilityBean = new AccessibilityBean();
            DriverAccessibilityParser driverAccessibilityParser = new DriverAccessibilityParser();
            accessibilityBean = driverAccessibilityParser.parseDriverAccessibilityResponse(wsResponseString);
            return accessibilityBean;
        }
    }
}
