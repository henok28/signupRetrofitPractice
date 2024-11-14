package school.project.signupretrofit;

import com.google.gson.annotations.SerializedName;

public class UserInfo {
    @SerializedName("name")
    String Name;

    @SerializedName("username")
    String userName;

    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
