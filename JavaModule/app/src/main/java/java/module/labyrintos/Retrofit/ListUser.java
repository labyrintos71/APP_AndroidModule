package java.module.labyrintos.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Labyrintos on 2019-10-07
 */
public class ListUser {

    @SerializedName("page")
    private long page;

    @SerializedName("per_page")
    private long per_page;

    @SerializedName("total")
    private long total;

    @SerializedName("total_pages")
    private long total_pages;

    @SerializedName("data")
    public List<User> user;
}
