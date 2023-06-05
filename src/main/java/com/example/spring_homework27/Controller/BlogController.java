package com.example.spring_homework27.Controller;

import com.example.spring_homework27.Model.Blog;
import com.example.spring_homework27.Model.MyUser;
import com.example.spring_homework27.Service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/get")
    public ResponseEntity getBlogs(){
        List<Blog> blogs = blogService.getBlogs();
        return ResponseEntity.status(200).body(blogs);
    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal MyUser myUser, @RequestBody Blog blog){
        blogService.addBlog(myUser, blog);
        return ResponseEntity.status(200).body("Blog Added");
    }

    @PutMapping("/update/{blogId}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal MyUser myUser, @RequestBody Blog blog, @PathVariable Integer blogId){
        blogService.updateBlog(myUser.getId(), blog, blogId);
        return ResponseEntity.status(200).body("Blog Updated");
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer blogId){
        blogService.deleteBlog(myUser.getId(), blogId);
        return ResponseEntity.status(200).body("Blog Deleted");
    }

    @GetMapping("get-user-blogs")
    public ResponseEntity getUserBlogs(@AuthenticationPrincipal MyUser myUser){
        List<Blog> blogs = blogService.getUserBlogs(myUser);
        return ResponseEntity.status(200).body(blogs);
    }

    @GetMapping("get-id/{blogId}")
    public ResponseEntity getBlogById(@PathVariable Integer blogId){
        Blog blog = blogService.getBlogById(blogId);
        return ResponseEntity.status(200).body(blog);
    }

    @GetMapping("get-title/{blogTitle}")
    public ResponseEntity getBlogByTitle(@PathVariable String blogTitle){
        Blog blog = blogService.getBlogByTitle(blogTitle);
        return ResponseEntity.status(200).body(blog);
    }
}
