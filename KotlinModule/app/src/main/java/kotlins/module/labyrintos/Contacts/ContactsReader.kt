package kotlins.module.labyrintos.Contacts

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract

/**
 * Created by Labyrintos on 2019-11-02
 */
object ContactsReader {
    fun getNameOfTell(context: Context?, tell: String): String {
        var name = tell
        val cursor = context?.contentResolver?.query(
            Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(tell)),
            arrayOf(ContactsContract.PhoneLookup.DISPLAY_NAME), null, null, null
        )
        when (cursor?.moveToFirst()) {
            true -> name = cursor.getString(0)

        }
        cursor?.close()
        return name
    }
}