package kotlins.module.labyrintos.Dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import kotlins.module.labyrintos.R

/**
 * Created by Labyrintos on 2019-11-28
 */

class CustomDialog(context: Context) : Dialog(context){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setCanceledOnTouchOutside(false)
     /*   window.setBackgroundDrawable(ColorDrawable())
        window.setDimAmount(0.0f)
        bind.title = this.title
        bind.massage = this.msg
        bind.okay = this.okay

        bind.okayButton.setOnClickListener(this)*/
        setContentView(R.layout.activity_main)
    }

    class Builder(context: Context){
        private val dialog = CustomDialog(context)
        fun setTitle(text:String): Builder {
            dialog.title = text
            return this
        }
        fun setMassage(text:String): Builder {
            dialog.msg = text
            return this
        }
        fun setOkayButton(text:String): Builder {
            dialog.okay = text
            return this
        }
        fun show(): CustomDialog {
            dialog.show()
            return dialog
        }
    }
}