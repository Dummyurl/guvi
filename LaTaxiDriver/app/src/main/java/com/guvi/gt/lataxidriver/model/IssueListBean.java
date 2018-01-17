package com.guvi.gt.lataxidriver.model;

import java.util.List;

/**
 * Created by Jemsheer K D on 18 May, 2017.
 * Package com.guvi.gt.lataxidriver.model
 * Project LaTaxiDriver
 */

public class IssueListBean extends BaseBean {

    private PaginationBean pagination;
    private List<IssueBean> issues;

    public PaginationBean getPagination() {
        return pagination;
    }

    public void setPagination(PaginationBean pagination) {
        this.pagination = pagination;
    }

    public List<IssueBean> getIssues() {
        return issues;
    }

    public void setIssues(List<IssueBean> issues) {
        this.issues = issues;
    }
}
