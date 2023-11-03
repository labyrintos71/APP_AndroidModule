package com.labyrintos.skeleton.Contacts

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
            //원래 없었는데 빌드오류로 수정함
            else -> name="error"

        }
        cursor?.close()
        return name
    }

    fun getContacts(context: Context): ArrayList<ContactData> {
        val data = ArrayList<ContactData>()

        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        val cursor = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection,
            null,
            null,
            null
        )
        cursor?.let {
            while (cursor.moveToNext()) {
                data.add(
                    ContactData(
                        cursor.getString(cursor.getColumnIndex(projection[0])),
                        cursor.getString(cursor.getColumnIndex(projection[1])),
                        cursor.getString(cursor.getColumnIndex(projection[2]))
                    )
                )
            }
            cursor.close()
        }
        return data
    }
}

data class ContactData(val id: String, val name: String, val tel: String)