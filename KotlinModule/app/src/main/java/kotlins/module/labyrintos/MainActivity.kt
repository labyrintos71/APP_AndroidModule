package kotlins.module.labyrintos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlins.module.labyrintos.Expression.TestFile
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val test = test()
        test.tests=2
        text.text= test.tests.toString() +""
        assert(test.tests>3)
         assert(false) {"123"}
        throw AssertionError("123")
    }
    class test{
        var tests=1
    }
}
