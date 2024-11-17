package school.project.signupretrofit;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository {
    private ApiService apiService;
//    private String BASEURL = "http://192.168.155.196:8000/";//192.168.1.4
    private String BASEURL = "http://192.168.1.4:8000/";//192.168.1.4
    //192.168.8.100
    //http://192.168.8.100:8000/

    public UserRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);


    }

    public void getUsers(MutableLiveData<List<UserInfo>> users, MutableLiveData<String> errormsg){
        Call<List<UserInfo>> call = apiService.getuserData();

        call.enqueue(new Callback<List<UserInfo>>() {
            @Override
            public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {
                if (response.isSuccessful() && response.body() != null){
                    users.setValue(response.body());
                    Log.d("API Response", response.body().toString());
                }else{
                    errormsg.setValue("failed to fetch data"+response.code());
                }
            }

            @Override
            public void onFailure(Call<List<UserInfo>> call, Throwable throwable) {
                errormsg.setValue("Error: "+throwable.getMessage());
            }
        });
    }


}
