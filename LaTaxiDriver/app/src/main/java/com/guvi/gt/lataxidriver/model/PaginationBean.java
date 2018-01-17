package com.guvi.gt.lataxidriver.model;

/**
 * Created by Jemsheer K D on 29 November, 2016.
 * Package com.guvi.gt.lataxidriver.model
 * Project LaTaxiDriver
 */

public class PaginationBean extends BaseBean {
    private int totalPages;
    private int currentPage;
    private int totalCount;
    private int total;
    private int count;
    private int perPage;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
}
