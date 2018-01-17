package com.guvi.gt.lataxidriver.model;

import java.util.List;

/**
 * Created by Jemsheer K D on 19 May, 2017.
 * Package com.guvi.gt.lataxidriver.model
 * Project LaTaxiDriver
 */

public class CommentListBean extends BaseBean {

    private List<CommentBean> comments;
    private PaginationBean pagination;

    public List<CommentBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentBean> comments) {
        this.comments = comments;
    }

    public PaginationBean getPagination() {
        return pagination;
    }

    public void setPagination(PaginationBean pagination) {
        this.pagination = pagination;
    }
}
