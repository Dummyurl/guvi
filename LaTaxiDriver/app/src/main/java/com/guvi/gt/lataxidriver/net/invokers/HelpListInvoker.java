package com.guvi.gt.lataxidriver.net.invokers;

import org.json.JSONObject;

import java.util.HashMap;

import com.guvi.gt.lataxidriver.model.HelpListBean;
import com.guvi.gt.lataxidriver.net.ServiceNames;
import com.guvi.gt.lataxidriver.net.WebConnector;
import com.guvi.gt.lataxidriver.net.parsers.HelpListParser;
import com.guvi.gt.lataxidriver.net.utils.WSConstants;

/**
 * Created by Jemsheer K D on 20 May, 2017.
 * Package com.guvi.gt.lataxidriver.net.invokers
 * Project LaTaxiDriver
 */

public class HelpListInvoker extends BaseInvoker {

    public HelpListInvoker() {
        super();
    }

    public HelpListInvoker(HashMap<String, String> urlParams,
                           JSONObject postData) {
        super(urlParams, postData);
    }

    public HelpListBean invokeHelpListWS() {

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.HELP_LIST), WSConstants.PROTOCOL_HTTP, urlParams, null);

        //webConnector= new WebConnector(new StringBuilder(ServiceNames.MODELS), WSConstants.PROTOCOL_HTTP, null);
//    String wsResponseString=webConnector.connectToPOST_service();
        String wsResponseString = webConnector.connectToGET_service();
        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        HelpListBean helpListBean = null;
        if (wsResponseString.equals("")) {
            /*registerBean=new RegisterBean();
            registerBean.setWebError(true);*/
            return helpListBean = null;
        } else {
            helpListBean = new HelpListBean();
            HelpListParser helpListParser = new HelpListParser();
            helpListBean = helpListParser.parseHelpListResponse(wsResponseString);
            return helpListBean;
        }
    }
}
