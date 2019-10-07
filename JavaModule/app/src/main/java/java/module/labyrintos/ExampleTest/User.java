package java.module.labyrintos.ExampleTest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Labyrintos on 2019-10-07
 */
public class User {
    // SerializedName JSON 필드명 매칭
    // Expose Null 일경우 생략
    @SerializedName("id")
    public long id;
    @SerializedName("email")
    public String email;
    @SerializedName("first_name")
    @Expose
    public String first_name;
    @SerializedName("last_name")
    @Expose
    public String last_name;
    @SerializedName("avatar")
    public String avatar;
}