package kotlins.module.labyrintos.Expression

import java.lang.NumberFormatException

/**
 * Created by Labyrintos on 2019-10-13
 */

class extension {
    /** assert
     * assert 는 조건식이 false 일 경우에 exception을 발생시킨다.
     * 때문에 릴리즈 코드가 아니라 디버그 전용 기능이며 주로 디버깅할때 사용.
     * 기본적으로 비활성화 되어있기때문에 아래처럼 adb로 활성화 시켜주어서
     * assert()메소드를 사용하거나 AssertionError를 던져서 처리하는게 좋다.
     */
    //adb shell setprop debu.asser 1
    object Assert{
        fun verify(condition: Boolean, message: String){
            if(!condition) throw AssertionError(message)
        }
    }
    //if 예제
    var x: Int = if (10 > 20) 5 else 10
    var y = if (10 > 20) 5 else 10

    //? nullable 객체
    val a: String = "Kotlin"
    var b: String? = null
    fun nullableTest() {
        println(b?.length) //null 출력됨
    }

    // ?: 엘비스 연산자, 좌항이 null 일때 우항값 반환
    fun elbisTest() {
        print(b?.length ?: -1) // -1
        b = "str"
        print(b?.length ?: -1) // 3
    }

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

    //data class 저장용 객체에서 사용
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


    //class

    //내책
    class Student(var roll_number: Int, var name: String = "sheldon")

    class Students constructor(var roll_number: Int, var name: String)

    fun example() {
        foo(1, b = 0.1)
        foo(a = 1, b = 0.1)
        //한번 변수를 명해주면 뒤에는 전부 명명해줘야된다
        // foo(a=1,0.1)
    }

    fun foo(a: Int = 0, b: Double = 0.0, c: String = "default") {

    }

    //getter setter
    class nullcheck {
        var string: String? = null
            get() = if (field == null) "null" else field

        var stringupper: String? = null
            get() = if (field == null) "null" else field.toString().toUpperCase()
    }

    class FakeAge {
        var age: Int = 0
            set(value) {
                field = when {
                    value < 18 -> 18
                    value in 18..30 -> value
                    else -> value - 3
                }
            }

    }

    class getexample {
        val array = mutableListOf<Int>(1, 2, 3)
        val isListBig: Boolean
            get() = array.size > 2

        var name = "test"
            get() = field.toUpperCase()
    }

    fun getsetExample() {
        getexample().isListBig
    }

    //인자 여러개 받을때
    fun varags() {
        val list = arrayOf("as", "as1", "as2", "as3")
        varargTest(*list)
    }

    fun varargTest(vararg numbers: Int) {
        numbers.map { it ->
            println(it)
        }
    }

    fun varargTest(vararg a: String) {
        for (a_ in a) {
            println(a_)
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
    //일반 함수
    fun makeText(): String {
        return "x = $x y = $y"
    }

    //코틀린에선s Unit이 void
    fun printText(): Unit {
        print("x = $x y = $y")
    }

    //람다식은 왼쪽이든 오른쪽이든 지정 해줘야됨
    fun a(): Int = 1

    //람다식, 자료형 지정
    var sum: (Int, Int) -> Int = { a: Int, b: Int -> a + b }
    //람다식, 자료형 오른쪽에만 지정
    var sum1 = { a: Int, b: Int -> a + b }
    //람다식, 자료형 왼쪽에만 지정
    var sum2: (Int, Int) -> Int = { a, b -> a + b }

    //람다식, 리턴값이 없을경우 예제
    val funcSayHi: (String) -> Unit = { name: String -> println("Hi $name") }

    //메소드에 람다식을 인수로 받는 예제
    fun main(args: Array<String>) {
        temp(1, 2, { a: Int, b: Int -> a + b })
        temp(1, 2) { a: Int, b: Int -> a + b }
        temp(1, 2, ::sum3)
        emptyrambda(3) { 7 }
        emptyrambda(3, { 7 })
        emptyrambda(3, { -> 7 })
        emptyrambdas(3) { a: Int -> a + 7 }
    }

    fun temp(a: Int, b: Int, c: (Int, Int) -> Int) = c(a, b)
    fun sum3(a: Int, b: Int): Int {
        return a + b
    }

    fun emptyrambda(a: Int, c: () -> Any) = c()
    fun emptyrambdas(a: Int, c: (a: Int) -> Any) = c(a)

    //익명함수
    var funcSum = fun(a: Int, b: Int): Int { return a + b }
    /*
    람다식 안에서 return 할 수 없는 경우가 있습니다.
    처리 중간에 조건을 주고 중간에 빠져나오고 싶지만 return이 없는 람다식의 경우입니다.
    아래 예제와 같은 람다식에서 조건에 일치하는 경우 처리를 종료하고 싶은 경우를 보겠습니다.

    numbers.forEach { number ->
        if (number % 2 == 1) {
            // 여기에서 처리를 끝내고 싶은 경우
        }

        ...
    }
    이러한 경우에는 람다식이 아닌 익명 함수를 사용하는 것이 좋습니다.

    numbers.forEach(fun(number: Int) {
        if (number % 2 == 1) {
            return // 처리 종류
        }

        ...
    })
    */

    //Object
    //싱글톤 지원 기능
    //오브젝트 는 싱글통 지원을 위해 만들어짐. 저상태로 사용해도됨
    //companion 오브젝트는 클래스에서 오브젝트로 선언한 부분만 싱글톤화 됨.
    class tester {
        init {
            println("init")
            println("testfun" + this.hashCode())
        }

        fun testfun() {
            println("testfun" + this.hashCode())

        }

        companion object Foo {
            fun callFoo() = println("Foo " + this.hashCode())
            var foo = "foo"
        }
    }

    object Foo {
        fun callFoo() = println("Foo " + this.hashCode())
        var foo = "foo"
    }

    //use
    //use 키워드는 스트림의 close를 자동으로 해줌
    //안드로이드에서는 이게 있음openFileInput
    /*   FileInputStream("file.txt").use {
           input ->
           var data = input.read()
           println(data)
       }*/


    //확장함수
    var strs = "섹스"
    val addStr = fun String.(str: String): String {
        return this + str
    }

  /*  fun String.deleteLastStr(successor: String): String {
        return this + successor
    }*/
    // 여기서 리시버타입은 String이된다. 즉 확장하는 자기 자신의 타입인것
    //책에서 나오는 리터럴은 람다를 넘겨준 식. 즉 표현식 그 자체임
    //         여기 부분
    private fun String.deleteLastStr(): String {
        return this.substring(0,this.length-1)
    }

    private fun Any.deleteLastStrㄴ(): String {
        return this.toString().substring(0)
    }
    var s = 1
    // str = str.addStr("하기 딱 좋은 날씨로구나.")

    //확장함수 심화
    fun extendFun() {
        fun String.lastChar1(): Char = this.get(this.length - 1)

        //this 생략 가능
        fun String.lastChar2(): Char = get(length - 1)

        fun <T> Collection<T>.convertToString(
            separator: String = ",",
            prefix: String = "(",
            postfix: String = ")"
        ): String {
            var result = prefix
            this.map { it.toString()+separator }.forEach { result = result + it }
            // == this.forEach { result = result + it + separator }
            return result.deleteLastStr() + postfix
        }

        //String 제네릭
        fun Collection<String>.join(
            separator: String = ",",
            prefix: String = "(",
            postfix: String = ")"
        ): String = this.toString() + " numbers"
        //사용
        val intlist = listOf(1, 2, 3, 4)
        //intlist.join() //에러!!! Int 타입은 불가능하다
        // 사용
        val list = listOf("1", "2", "3")
        print(list.joinToString())
    }

    //확장 프로퍼티
    class extendProperty {
        //get() 구현
        val String.lastChar: Char
            get() = get(length - 1)
        //  get() { return last().toString()}

        //리스트인경우 get(), set() 구현
        var List<Any>.name: String
            get() = name
            set(value) {
                name = last().toString()
            }

        fun test() {
            "zerog".lastChar        //확장 프로퍼티 호출
            val list = listOf("a", "b", "c")
            list.name              //get()
            list.name = "d"      //set()
        }
    }
}