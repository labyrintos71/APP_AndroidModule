package java.module.labyrintos.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.module.labyrintos.Retrofit.User;
import java.util.List;

/**
 * Created by Labyrintos on 2019-10-07
 */
public class PutData {
    @SerializedName("name")
    public String name;

    @SerializedName("job")
    public String job;

    @SerializedName("updatedAt")
    public String updatedAt;
}
