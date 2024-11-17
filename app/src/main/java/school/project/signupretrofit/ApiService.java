package school.project.signupretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("api/show")//http://192.168.1.4:8000/api/show
//https://dummyjson.com/c/78d0-025f-47ce-a288  https://dummyjson.com/c/cd5c-cf8e-47a1-a723
//    https://dummyjson.com/c/1e70-1219-4dab-b888
    Call<List<UserInfo>> getuserData();






}
