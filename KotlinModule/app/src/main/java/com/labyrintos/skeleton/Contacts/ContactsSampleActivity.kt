package com.labyrintos.skeleton.Contacts

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.labyrintos.skeleton.Permission.PermissionActivity

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