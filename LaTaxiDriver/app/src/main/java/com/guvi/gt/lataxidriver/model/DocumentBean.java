package com.guvi.gt.lataxidriver.model;

/**
 * Created by Jemsheer K D on 28 April, 2017.
 * Package com.guvi.gt.lataxidriver.model
 * Project LaTaxiDriver
 */

public class DocumentBean extends BaseBean {

    private String id;
    private int type;
    private String name;
    private boolean isUploaded;
    /**
     * Document Status - current status of the document
     * 0 - Not Uploaded
     * 1 - Document Uploaded, Pending Approval
     * 2 - Document Uploaded and Approved
     * 3 - Document Uploaded and Rejected
     */
    private int documentStatus;
    private String documentURL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUploaded() {
        return isUploaded;
    }

    public void setUploaded(boolean uploaded) {
        isUploaded = uploaded;
    }

    public int getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(int documentStatus) {
        this.documentStatus = documentStatus;
    }

    public String getDocumentURL() {
        return documentURL;
    }

    public void setDocumentURL(String documentURL) {
        this.documentURL = documentURL;
    }
}
