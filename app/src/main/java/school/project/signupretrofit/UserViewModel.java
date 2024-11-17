package school.project.signupretrofit;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private MutableLiveData<List<UserInfo>> users = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository();
    }

    public LiveData<List<UserInfo>> getUsers() {
        return users;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void fetchUsers(){
        userRepository.getUsers(users, errorMessage);
    }
}
