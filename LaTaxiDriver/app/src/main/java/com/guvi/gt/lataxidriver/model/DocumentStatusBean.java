package com.guvi.gt.lataxidriver.model;

import java.util.ArrayList;
import java.util.List;

import com.guvi.gt.lataxidriver.util.AppConstants;

/**
 * Created by Jemsheer K D on 28 April, 2017.
 * Package com.guvi.gt.lataxidriver.model
 * Project LaTaxiDriver
 */

public class DocumentStatusBean extends BaseBean {

    private List<DocumentBean> documents = new ArrayList<>();

    public List<DocumentBean> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentBean> documents) {
        this.documents = documents;
    }

    public boolean isAllDocumentsUploaded() {


        if (documents != null && !documents.isEmpty()) {
            for (DocumentBean bean : documents) {
                if (!bean.isUploaded() || (bean.getDocumentStatus() != AppConstants.DOCUMENT_STATUS_PENDING_APPROVAL
                        && bean.getDocumentStatus() != AppConstants.DOCUMENT_STATUS_APPROVED)) {
                    return false;
                }
            }
        }

        return true;
    }
}
