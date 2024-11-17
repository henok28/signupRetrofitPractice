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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

//    private String BASEURL = "https://dummyjson.com/";
//    private String BASEURL = "https://dummyjson.com/";
    private TextView textView;
    private UserViewModel userViewModel;
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
        /*
        * Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
                *
                * this was before using MVVM
        *
        *



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

         */

        textView = findViewById(R.id.textView);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        //observing the live data for the user info
        LiveData<List<UserInfo>> userLiveData = userViewModel.getUsers();

        userLiveData.observe(this, new Observer<List<UserInfo>>() {
            @Override
            public void onChanged(List<UserInfo> userInfos) {
                if (userInfos!=null){
                    UserInfo users = userInfos.get(1);
                    textView.setText(users.getFirstname());// 192.168.1.4
                }

            }
        });

        /*
        * userLiveData.observe(this, new Observer<UserInfo>() {
            @Override
            public void onChanged(UserInfo infoResponse) {
                if (infoResponse!=null){
                    textView.setText(infoResponse.getFirstname());
//                    StringBuilder userData = new StringBuilder();
//                    for (int i = 0; i<userList.size(); i++){
//                        UserInfo user = userList.get(i);
//                        userData.append(user.getFirstName()).append("\n")
//                                .append(user.getLastName()).append("\n")
//                                .append(user.getEmail()).append("\n")
//                                .append(user.getEmailVerifiedAt()).append("\n");

//                    textView.setText(userData);

                }else {
                    textView.setText("not fetched");
                }

            }
        });*/


        LiveData<String> errorLiveData = userViewModel.getErrorMessage();
        errorLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                if (errorMessage != null) {

                    // Display the error message
                    textView.setText(errorMessage + "in case of faliure");
                }
            }
        });
        userViewModel.fetchUsers();


    }
}