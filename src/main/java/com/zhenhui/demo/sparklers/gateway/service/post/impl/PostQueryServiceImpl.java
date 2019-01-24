package com.zhenhui.demo.sparklers.gateway.service.post.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhenhui.demo.sparklers.common.Error;
import com.zhenhui.demo.sparklers.common.Result;
import com.zhenhui.demo.sparklers.gateway.service.post.PostQueryService;
import com.zhenhui.demo.sparklers.gateway.service.post.model.converter.PostVOConverter;
import com.zhenhui.demo.sparklers.gateway.service.post.model.result.PostVO;
import com.zhenhui.demo.sparklers.gateway.utils.CurrentUser;
import com.zhenhui.sparkler.api.domain.PostDto;
import com.zhenhui.sparkler.api.domain.PostQueryDto;
import com.zhenhui.sparkler.api.service.PostReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("unchecked")
@RestController
public class PostQueryServiceImpl implements PostQueryService {

    @Reference(version = "1.0.0")
    private PostReadService postReadService;
    @Autowired
    private PostVOConverter postVOConverter;

    @Override
    public Result<PostVO> queryPost(long postId) {

        final PostDto post = postReadService.getPost(postId);
        if (null == post) {
            return Result.error(404, Error.DATA_NOT_FOUND.name(), "");
        }

        long userId = CurrentUser.getUserId();

        try {
            return Result.success(postVOConverter.to(post));
        } catch (Exception e) {
            return Result.error(Error.APPLICATION_ERROR);
        }
    }

    @Override
    public Result<List<PostVO>> getCategoryPosts(long categoryId, Long startNo, Integer count) {

        final PostQueryDto query = PostQueryDto.forCategory(categoryId, startNo, count);

        try {
            return Result.success(getPosts(query));
        } catch (Exception e) {
            return Result.error(Error.APPLICATION_ERROR);
        }
    }

    @Override
    public Result<List<PostVO>> getSectionPosts(long sectionId, Long startNo, Integer count) {
        final PostQueryDto query = PostQueryDto.forSection(sectionId, startNo, count);

        try {
            return Result.success(getPosts(query));
        } catch (Exception e) {
            return Result.error(Error.APPLICATION_ERROR);
        }
    }

    private List<PostVO> getPosts(PostQueryDto query) {
        List<PostDto> posts = postReadService.listPost(query);

        Collection<PostVO> result = postVOConverter.to(posts);

        return new ArrayList<>(result);
    }
}
