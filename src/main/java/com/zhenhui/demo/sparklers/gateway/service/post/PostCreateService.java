package com.zhenhui.demo.sparklers.gateway.service.post;

import com.zhenhui.demo.sparklers.common.Result;
import com.zhenhui.demo.sparklers.gateway.service.post.model.params.PostCreateRO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
public interface PostCreateService {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<Long> createPost(@RequestBody PostCreateRO createRO);

}
