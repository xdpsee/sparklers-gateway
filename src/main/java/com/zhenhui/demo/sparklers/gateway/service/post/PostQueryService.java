package com.zhenhui.demo.sparklers.gateway.service.post;

import com.zhenhui.demo.sparklers.common.Result;
import com.zhenhui.demo.sparklers.gateway.service.post.model.result.PostVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/post")
public interface PostQueryService {

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<PostVO> queryPost(@PathVariable("id") long postId);

    @GetMapping(value = "/category/{category}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<List<PostVO>> getCategoryPosts(@PathVariable(value = "category") long categoryId
            , @RequestParam(value = "start", defaultValue = "4294967295") Long startNo
            , @RequestParam(value = "count", defaultValue = "15") Integer count);

    @GetMapping(value = "/section/{section}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<List<PostVO>> getSectionPosts(@PathVariable("section") long sectionId
            , @RequestParam(value = "start", defaultValue = "4294967295") Long startNo
            , @RequestParam(value = "count", defaultValue = "15") Integer count);
}

