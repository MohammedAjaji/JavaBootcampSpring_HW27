package com.example.spring_homework27.Service;

import com.example.spring_homework27.ApiException.ApiException;
import com.example.spring_homework27.Model.Blog;
import com.example.spring_homework27.Model.MyUser;
import com.example.spring_homework27.Repository.AuthRepository;
import com.example.spring_homework27.Repository.BlogRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final BlogRepository blogRepository;
    public void registerUser(MyUser myUser) {
        String hash = new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hash);
        authRepository.save(myUser);
    }


    public void updateUserPassword(MyUser myUser, String password) {
        MyUser oldUser = authRepository.findMyUserById(myUser.getId());

        String hash = new BCryptPasswordEncoder().encode(password);
        oldUser.setPassword(hash);
        authRepository.save(oldUser);
    }

    public void updateUserUsername(MyUser myUser, String username) {
        MyUser oldUser = authRepository.findMyUserById(myUser.getId());

        oldUser.setUsername(username);
        authRepository.save(oldUser);
    }

    public void deleteUser(MyUser myUser) {

//        if (!(userId.equals(myUser.getId()))){
//            throw new ApiException("Not Authorized");
//        }
//
        List<Blog> blogs = blogRepository.findBlogsByMyUser(myUser);
        for (int i = 0; i < blogs.size(); i++) {
            blogs.get(i).setMyUser(null);
        }
        MyUser oldUser = authRepository.findMyUserById(myUser.getId());
        authRepository.delete(oldUser);
    }
}
