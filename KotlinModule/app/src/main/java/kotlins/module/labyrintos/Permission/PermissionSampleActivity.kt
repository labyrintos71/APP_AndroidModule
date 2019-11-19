package kotlins.module.labyrintos.Permission

import android.Manifest.permission.*
import org.jetbrains.anko.toast

/**
 * Created by Labyrintos on 2019-11-04
 */
class PermissionSampleActivity : PermissionActivity() {
    override val requiredPermission: Array<String>
        get() = arrayOf(
            READ_EXTERNAL_STORAGE
        )

    override fun doSomething() {
        toast("안녕!").show()
    }
}