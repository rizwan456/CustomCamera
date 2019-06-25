package com.rizz.customcamera.model;

public class Filter {
    private int filterId;
    private String filterName;

    public Filter(int filterId, String filterName) {
        this.filterId = filterId;
        this.filterName = filterName;
    }

    public int getFilterId() {
        return filterId;
    }

    public void setFilterId(int filterId) {
        this.filterId = filterId;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }
}
