package kotlins.module.labyrintos.RetrofitForRXJava

import com.google.gson.annotations.SerializedName

/**
 * Created by Labyrintos on 2019-10-27
 */
data class GithubResponseModel (val total_count : Int, var items:List<GithubRepoModel>){
    data class GithubRepoModel(var id: Long, var name: String, var full_name:String)
}

/*
class GithubResponseModels {
    @SerializedName("total_count")
    val totalCount: Int =  0

    @SerializedName("items")
    val items: List<GithubRepoModel> = listOf()
}

class GithubRepoModels {
    @SerializedName("id")
    val id: Long = 0

    @SerializedName("name")
    val name: String = ""

    @SerializedName("full_name")
    val fullName: String = ""
}
*/

