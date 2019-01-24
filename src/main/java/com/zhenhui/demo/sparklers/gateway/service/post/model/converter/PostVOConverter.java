package com.zhenhui.demo.sparklers.gateway.service.post.model.converter;

import com.zhenhui.demo.sparklers.gateway.middleware.clients.UserQueryServiceClient;
import com.zhenhui.demo.sparklers.gateway.service.post.model.result.PostVO;
import com.zhenhui.demo.uic.api.domain.UserDto;
import com.zhenhui.sparkler.api.domain.PostDto;
import org.mapstruct.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Mapper(componentModel = "spring")
public abstract class PostVOConverter {

    @Autowired
    private UserQueryServiceClient userQueryServiceClient;

    @Mappings({
            @Mapping(target = "updateAt", expression = "java(source.getUpdateAt().getTime())"),
            @Mapping(target = "createAt", expression = "java(source.getCreateAt().getTime())"),
            @Mapping(target = "user", ignore = true)
    })
    public abstract PostVO to(PostDto source);

    @InheritConfiguration
    public abstract Collection<PostVO> to(Collection<PostDto> sources);

    @AfterMapping
    void after(PostDto source, @MappingTarget PostVO target) {
        final UserDto userDto = userQueryServiceClient.getUser(source.getCreatorId());
        target.setUser(convert(userDto));
    }

    @AfterMapping
    void after(Collection<PostDto> sources, @MappingTarget Collection<PostVO> targets) {
        Set<Long> userIds = sources.stream().map(PostDto::getCreatorId).collect(Collectors.toSet());

        Map<Long, UserDto> userMap = userQueryServiceClient.getUsers(userIds);

        Map<Long, Long> postUserMap = sources.stream().collect(toMap(PostDto::getId, PostDto::getCreatorId));

        targets.forEach(postVO -> {
            Long userId = postUserMap.get(postVO.getId());
            if (userId != null) {
                UserDto userDto = userMap.get(userId);
                postVO.setUser(convert(userDto));
            }
        });
    }

    private PostVO.UserVO convert(UserDto userDto) {
        if (userDto != null) {
            PostVO.UserVO userVO = new PostVO.UserVO();
            BeanUtils.copyProperties(userDto, userVO);
            return userVO;
        }

        return null;
    }

}

