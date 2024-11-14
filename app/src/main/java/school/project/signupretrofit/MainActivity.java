package school.project.signupretrofit;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

//    private String BASEURL = "https://dummyjson.com/";
    private String BASEURL = "https://dummyjson.com/";
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        //creating an instance of a retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        textView = findViewById(R.id.textView);
        //creating a retrofit
        ApiService apiService = retrofit.create(ApiService.class);
        Call<InfoResponse> call = apiService.getuserData();

        call.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {
                List<UserInfo> users = response.body().getUserData();

                StringBuilder displayingTextToTextView = new StringBuilder();
                for(UserInfo userInfo: users){
                    displayingTextToTextView.append("UserName: ").append(userInfo.getName()).append("\n")
                            .append("UserName: ").append(userInfo.getUserName()).append("\n")
                            .append("Email: ").append(userInfo.getEmail()).append("\n")
                            .append("Password: ").append(userInfo.getPassword()).append("\n");
                }
                textView.setText(displayingTextToTextView);
            }

            @Override
            public void onFailure(Call<InfoResponse> call, Throwable throwable) {
                Log.e("onFailure", "the Request have Failed", throwable);
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}