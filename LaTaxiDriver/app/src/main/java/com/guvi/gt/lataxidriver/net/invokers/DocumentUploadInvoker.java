package com.guvi.gt.lataxidriver.net.invokers;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import com.guvi.gt.lataxidriver.model.BasicBean;
import com.guvi.gt.lataxidriver.net.ServiceNames;
import com.guvi.gt.lataxidriver.net.WebConnector;
import com.guvi.gt.lataxidriver.net.parsers.BasicParser;
import com.guvi.gt.lataxidriver.net.utils.WSConstants;

public class DocumentUploadInvoker extends BaseInvoker {

    public DocumentUploadInvoker() {
        super();
    }

    public DocumentUploadInvoker(HashMap<String, String> urlParams,
                                    JSONObject postData) {
        super(urlParams, postData);

    }

    public BasicBean invokeDocumentUploadWS(List<String> fileList) {

        System.out.println("POSTDATA>>>>>>>" + postData);

        WebConnector webConnector;

        webConnector = new WebConnector(new StringBuilder(ServiceNames.DOCUMENT_UPDATE),
                WSConstants.PROTOCOL_HTTP, null, postData, fileList);

        String wsResponseString = webConnector.connectToMULTIPART_POST_service("document_update");

        System.out.println(">>>>>>>>>>> response: " + wsResponseString);
        BasicBean basicBean = null;
        if (wsResponseString.equals("")) {

            return basicBean = null;
        } else {
            basicBean = new BasicBean();
            BasicParser basicParser = new BasicParser();
            basicBean = basicParser.parseBasicResponse(wsResponseString);
            return basicBean;
        }
    }
}
