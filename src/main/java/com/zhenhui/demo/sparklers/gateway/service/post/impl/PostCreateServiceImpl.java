package com.zhenhui.demo.sparklers.gateway.service.post.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhenhui.demo.sparklers.common.Error;
import com.zhenhui.demo.sparklers.common.Result;
import com.zhenhui.demo.sparklers.gateway.service.post.PostCreateService;
import com.zhenhui.demo.sparklers.gateway.service.post.model.converter.PostROConverter;
import com.zhenhui.demo.sparklers.gateway.service.post.model.params.PostCreateRO;
import com.zhenhui.demo.sparklers.gateway.utils.ValidationUtils;
import com.zhenhui.sparkler.api.domain.PostDto;
import com.zhenhui.sparkler.api.service.PostWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
public class PostCreateServiceImpl implements PostCreateService {

    @Autowired
    private PostROConverter postROConverter;
    @Reference(version = "1.0.0")
    private PostWriteService postWriteService;

    @Override
    public Result<Long> createPost(PostCreateRO createRO) {
        try {
            ValidationUtils.validate(createRO);

            PostDto post = postROConverter.to(createRO);

            long postId = postWriteService.createPost(createRO.getSectionId(), post);
            return Result.success(postId);

        } catch (IllegalArgumentException e) {
            return Result.error(400, Error.INVALID_INPUT.name(), e.getMessage());
        } catch (Exception e) {
            return Result.error(Error.APPLICATION_ERROR);
        }
    }
}
