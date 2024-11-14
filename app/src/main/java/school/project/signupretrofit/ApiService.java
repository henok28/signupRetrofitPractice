package school.project.signupretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("c/1e70-1219-4dab-b888")
    Call<InfoResponse> getuserData();




}
