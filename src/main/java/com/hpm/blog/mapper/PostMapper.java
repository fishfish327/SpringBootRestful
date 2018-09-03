package com.hpm.blog.mapper;

import com.hpm.blog.model.Post;

import java.util.List;

public interface PostMapper {
    int add(Post post);
    Post findOne(Post Param);
    List<Post> all();

    void update(Post post);

    void delete(int id);

}
