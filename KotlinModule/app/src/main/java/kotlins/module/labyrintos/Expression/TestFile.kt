package kotlins.module.labyrintos.Expression

import java.lang.NumberFormatException

/**
 * Created by Labyrintos on 2019-10-13
 */

class TestFile {
    //if 예제
    var x: Int = if (10 > 20) 5 else 10
    var y = if (10 > 20) 5 else 10


    // when
    //기초
    fun when_example() {
        when (x) {
            1 -> println(1)
            2 -> println(2)
            3, 4 -> println("over 2")
            in 5..10 -> println("5~10")
            else -> println("else")
        }
        //switch와 if를 섞은것처럼 사용

        var abs = 50
        when {
            abs <= 10 -> println("a <= 10")
            else -> println("a > 10")
        }


        var ab: (String, String) -> String = { a, b -> "$a $b" }
    }

    //심화
    fun when_example2() {
        val x = ob(2, true, 500)
        when (x.value) {
            maginNum(x.value) -> println("$x is a magic number and ${if (x.valid) "valid" else "invalid"}")
            in (1..10) -> {
                println("lies between 1 to 10, value: ${if (x.value < x.max) x.value else x.max}")
            }
            20, 21 -> println("as df")
            else -> println("asfddsa")
        }
    }

    data class ob(val value: Int, val valid: Boolean, val max: Int)

    fun maginNum(a: Int): Int {
        return if (a in (15..25)) a else 0
    }


    //try catch
    fun try_example() {
        val str = "123"
        var a: Int? = try {
            str.toInt()
        } catch (e: NumberFormatException) {
            -1
        }
    }

    // swap
    fun also_example() {
        var a = 1
        var b = 1
        a = b.also { b = a }
    }

    fun apply_example() {
        var result = Dog(12).also { it.age = 13 }
        var result2 = Dog(12).apply { age = 13 }
    }

    data class Dog(var age: Int)


    //클래스

    //내책
    class Student(var roll_number: Int, var name: String = "sheldon")

    class Students constructor(var roll_number: Int, var name: String)
    fun example(){
        foo(1,b=0.1)
        foo(a=1,b=0.1)
        //한번 변수를 명해주면 뒤에는 전부 명명해줘야된다
        // foo(a=1,0.1)
    }

    fun foo (a:Int=0, b: Double =0.0, c:String="default"){

    }
    //게터 세터
    class nullcheck {
        var string: String? = null
            get() = if (field == null) "123" else field

    }
    class FakeAge {
        var age: Int = 0
            set(value) {
                field = when {
                    value < 18 -> 18
                    value in 18..30 -> value
                    else -> value -3
                }
            }

    }
    //lateinit
    /*class SampleActivity {

        private lateinit var sampleAdapter: SampleAdapter

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_sample_main)

            // 부르는 시점 초기화
            sampleAdapter = SampleAdapter(ImageLoaderAdapterViewModel(this@SampleMainActivity, 3))

            sampleAdapter.addItem()
            sampleAdapter.notifyDataSetChanged()
        }
    }*/
    fun print(): String {
        return "x = $x y = $y"
    }

    fun a(): Int = 1
    //인자 여러개 받을때
    /*fun main(args: Array<String>) {
        varargTest(1,2,3,4)
    }

    fun varargTest(vararg numbers: Int) {
        numbers.map { it ->
            println(it)
        }
    }*/
    var sum: (Int, Int) -> Int = { a: Int, b: Int -> a + b }
    var sum1 = { a: Int, b: Int -> a + b }
    var sum2: (Int, Int) -> Int = { a, b -> a + b }
    fun temp(a: Int, b: Int, c: (Int, Int) -> Int) = c(a, b)
    fun sum3(a: Int, b: Int): Int {
        return a + b
    }

    fun main(args: Array<String>) {
        temp(1, 2, ::sum3)
    }
}