package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TwitchStreams {

    @SerializedName("data")
    @Expose
    private List<Stream> streamList = null;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;

    public List<Stream> getStreamList() {
        return streamList;
    }

    public void setStreamList(List<Stream> streamList) {
        this.streamList = streamList;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}