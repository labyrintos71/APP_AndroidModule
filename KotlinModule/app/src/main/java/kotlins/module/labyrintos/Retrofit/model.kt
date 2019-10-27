package kotlins.module.labyrintos.Retrofit

/**
 * Created by Labyrintos on 2019-10-27
 */
data class UserModel(val id: Long, val email:String, val first_name:String, val last_name:String, val avatar:String )
data class SingleUserModel(val data:UserModel)