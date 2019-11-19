package kotlins.module.labyrintos.Toolbar

/**
 * Created by Labyrintos on 2019-11-20
 */
/* xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <labyrintos71.test.CenterTitleToolbar
        android:id="@+id/my_toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
        android:background="?attr/colorPrimary"
        android:gravity="center"
        android:elevation="10dp"
        app:contentInsetStart="0dp">

        <FrameLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="#80000000">
            <Button
                android:layout_width="24dp"
                android:layout_height="24dp"
                android:background="@drawable/settings"
                android:layout_gravity="center_vertical"/>
            <TextView
                android:id="@+id/title_text"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:text="테스트" />
        </FrameLayout>
    </labyrintos71.test.CenterTitleToolbar>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</LinearLayout>

menu
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <!-- "Mark Favorite", should appear as action button if possible -->
    <item
        android:id="@+id/action_settings"
        android:title="환경설정"
        android:icon="@drawable/settings"
        app:showAsAction="ifRoom"/>

    <!-- Settings, should always be in the overflow -->
    <item android:id="@+id/action_settings2"
        android:title="항목1"
        app:showAsAction="never"/>

    <item android:id="@+id/action_settings3"
        android:title="항목2"
        app:showAsAction="never"/>
</menu>
Activity
: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(my_toolbar)
        supportActionBar?.apply {
            //좌측 기본 뒤로가기버튼 생성
            setDisplayHomeAsUpEnabled(true)
            //setDisplayShowHomeEnabled(true)
            //홈버튼 아이콘 커스텀 24dp 넣으면 맞음
            //setHomeAsUpIndicator(R.drawable.settings)
            setDisplayShowTitleEnabled(false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> Toast.makeText(this,"홈 클릭ㅌ됨",Toast.LENGTH_SHORT).show()
            R.id.action_settings -> Toast.makeText(this,"세팅 클릭ㅌ됨",Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this,"나머지ㅏ 클릭ㅌ됨",Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}


 */