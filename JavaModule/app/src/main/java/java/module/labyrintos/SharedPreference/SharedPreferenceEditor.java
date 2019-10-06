package java.module.labyrintos.SharedPreference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

/**
 * Created by Labyrintos on 2019-10-06
 */
public class SharedPreferenceEditor {

    private SharedPreferences sharedPref ;

    public SharedPreferenceEditor(Activity activity) {
        //기본, 액티비티를 이름으로 사용함
        sharedPref = activity.getPreferences(Context.MODE_PRIVATE);

        // 매개변수 파일명 지정
         sharedPref = activity.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        //PreferenceManager.getDefaultSharedPreferences()함수는 기본으로 앱의 패키지명을 파일명으로 사용함
         sharedPref = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    public void readSharedPreference(){
         String data1 = sharedPref.getString("data1", "none");
         int data2 = sharedPref.getInt("data2", 0);
     }

     public void writeSharedPreference(){
         SharedPreferences.Editor editor = sharedPref.edit();
         editor.putString("data1", "hello"); // 문자열 데이터 타입 함수
         editor.putInt("data2", 100); // 숫자 데이터 타입 함수
         editor.commit();
     }
}
