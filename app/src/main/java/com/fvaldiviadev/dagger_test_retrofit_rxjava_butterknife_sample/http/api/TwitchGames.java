
package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TwitchGames {

    @SerializedName("data")
    @Expose
    private List<Game> gameList = null;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
