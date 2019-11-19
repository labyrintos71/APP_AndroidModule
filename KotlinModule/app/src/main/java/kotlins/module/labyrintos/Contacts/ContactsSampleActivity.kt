package kotlins.module.labyrintos.Contacts

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlins.module.labyrintos.Permission.PermissionActivity
import kotlins.module.labyrintos.R
import org.jetbrains.anko.toast

/**
 * Created by Labyrintos on 2019-11-03
 */

class ContactsSampleActivity :PermissionActivity(){
    override val requiredPermission: Array<String>
        get() = arrayOf(
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS
        )

    override fun doSomething() {
        ContactsReader.getContacts(applicationContext)
        ContactsReader.getNameOfTell(applicationContext,"이수현")
    }

}