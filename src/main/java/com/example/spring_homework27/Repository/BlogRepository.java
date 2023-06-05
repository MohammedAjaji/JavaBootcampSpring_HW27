package com.example.spring_homework27.Repository;

import com.example.spring_homework27.Model.Blog;
import com.example.spring_homework27.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {

    Blog findBlogById(Integer blogId);

    List<Blog> findBlogsByMyUser(MyUser myUser);

    Blog findBlogByTitle(String title);
}
