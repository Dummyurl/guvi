package com.guvi.gt.lataxidriver.net.invokers;

import org.json.JSONObject;

import java.util.HashMap;

import com.guvi.gt.lataxidriver.model.AuthBean;
import com.guvi.gt.lataxidriver.net.ServiceNames;
import com.guvi.gt.lataxidriver.net.WebConnector;
import com.guvi.gt.lataxidriver.net.parsers.LoginParser;
import com.guvi.gt.lataxidriver.net.utils.WSConstants;

/**
 * Created by Jemsheer K D on 28 April, 2017.
 * Package com.guvi.gt.lataxidriver.net.invokers
 * Project LaTaxiDriver
 */

public class LoginInvoker extends BaseInvoker {

    public LoginInvoker() {
        super();
    }

    public LoginInvoker(HashMap<String, String> urlParams,
                        JSONObject postData) {
        super(urlParams, postData);
    }

    public AuthBean invokeLoginWS() {

        System.out.println("POSTDATA>>>>>>>" + postData);

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.AUTH_EMAIL), WSConstants.PROTOCOL_HTTP, null, postData);

        //		webConnector= new WebConnector(new StringBuilder(ServiceNames.AUTH_EMAIL), WSConstants.PROTOCOL_HTTP, postData,null);
        //webConnector= new WebConnector(new StringBuilder(ServiceNames.MODELS), WSConstants.PROTOCOL_HTTP, null);
        String wsResponseString = webConnector.connectToPOST_service();
        //	String wsResponseString=webConnector.connectToGET_service();
        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        AuthBean authBean = null;
        if (wsResponseString.equals("")) {
            /*registerBean=new RegisterBean();
            registerBean.setWebError(true);*/
            return authBean = null;
        } else {
            authBean = new AuthBean();
            LoginParser loginParser = new LoginParser();
            authBean = loginParser.parseLoginResponse(wsResponseString);
            return authBean;
        }
    }
}
