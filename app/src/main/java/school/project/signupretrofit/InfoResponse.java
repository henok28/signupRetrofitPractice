package school.project.signupretrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class InfoResponse {

    @SerializedName("users")
    List<UserInfo> users;

    public List<UserInfo> getUserData(){
        return users;
    }
}
