/*
package java.module.labyrintos.Retrofit.SyncExample;

*/
/**
 * Created by Labyrintos on 2019-10-10
 *//*

https://yoo-hyeok.tistory.com/79 비동기로 할일이 있을 떄 참고하면 될듯
public class ExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GitHubService gitHubService = GitHubService.retrofit.create(GitHubService.class);
                final Call<List<Contributor>> call = gitHubService.repoContributors("square", "retrofit");
                new NetworkCall().execute(call);
            }
        });


    }
    private class NetworkCall extends AsyncTask<Call, Void, String> {

        @Override
        protected String doInBackground(Call[] params) {
            try {
                Call<List<Contributor>> call = params[0];
                Response<List<Contributor>> response = call.execute();
                return response.body().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            final TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(result);
        }
    }
}
*/
