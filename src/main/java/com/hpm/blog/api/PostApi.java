package com.hpm.blog.api;

import com.alibaba.fastjson.JSONObject;
import com.hpm.blog.annotation.CurrentUser;
import com.hpm.blog.annotation.LoginRequired;
import com.hpm.blog.model.Post;
import com.hpm.blog.model.User;
import com.hpm.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostApi {
    private PostService postService;

    @Autowired
    public PostApi(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("")
    @LoginRequired
    public Post add(@RequestBody Post post, @CurrentUser User user) {
        post.setAuthorId(user.getId());
        post = postService.add(post);
        return post;
    }

    @GetMapping("")
    public List<Post> all() {
        return postService.all();
    }

 
    @LoginRequired
    @PutMapping("/{id}")
    public Post update(@RequestBody Post post, @PathVariable int id, @CurrentUser User currentUser) {
        post.setId(id);
        return postService.update(post, currentUser);
    }


    @LoginRequired
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable int id, @CurrentUser User currentUser) {
        postService.delete(id, currentUser);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "删除成功");
        return jsonObject;
    }








}
