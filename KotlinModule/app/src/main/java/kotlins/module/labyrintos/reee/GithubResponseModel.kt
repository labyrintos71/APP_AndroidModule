package kotlins.module.labyrintos.reee

import com.google.gson.annotations.SerializedName

/**
 * Created by Labyrintos on 2019-10-27
 */

class GithubResponseModel {
    @SerializedName("total_count")
    val totalCount: Int =  0

    @SerializedName("items")
    val items: List<GithubRepoModel> = listOf()
}