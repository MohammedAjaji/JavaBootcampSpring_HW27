package com.example.spring_homework27.Service;

import com.example.spring_homework27.ApiException.ApiException;
import com.example.spring_homework27.Model.Blog;
import com.example.spring_homework27.Model.MyUser;
import com.example.spring_homework27.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }


    public void addBlog(MyUser myUser, Blog blog) {
        blog.setMyUser(myUser);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer userId, Blog blog, Integer blogId) {
        Blog oldBlog = blogRepository.findBlogById(blogId);
        if (oldBlog == null){
            throw new ApiException("Blog Not Found");
        }

        if (!(userId.equals(oldBlog.getMyUser().getId()))){
            throw new ApiException("Not Authorized");
        }

        oldBlog.setTitle(blog.getTitle());
        oldBlog.setBody(blog.getBody());
        blogRepository.save(oldBlog);
    }

    public void deleteBlog(Integer userId, Integer blogId) {
        Blog blog = blogRepository.findBlogById(blogId);
        if (blog == null){
            throw new ApiException("Blog Not Found");
        }

        if (!(userId.equals(blog.getMyUser().getId()))){
            throw new ApiException("Not Authorized");
        }
        blogRepository.delete(blog);
    }

    public List<Blog> getUserBlogs(MyUser myUser) {
        return blogRepository.findBlogsByMyUser(myUser);
    }

    public Blog getBlogById(Integer blogId) {
        return blogRepository.findBlogById(blogId);
    }

    public Blog getBlogByTitle(String blogTitle) {
        return blogRepository.findBlogByTitle(blogTitle);
    }
}
