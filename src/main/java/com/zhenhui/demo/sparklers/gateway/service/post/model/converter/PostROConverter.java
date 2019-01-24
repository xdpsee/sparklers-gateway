package com.zhenhui.demo.sparklers.gateway.service.post.model.converter;

import com.zhenhui.demo.sparklers.gateway.service.post.model.params.PostCreateRO;
import com.zhenhui.sparkler.api.domain.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PostROConverter {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createAt", expression = "java(new java.util.Date())"),
            @Mapping(target = "updateAt", expression = "java(new java.util.Date())"),
            @Mapping(target = "status", expression = "java(1)"),
    })
    PostDto to(PostCreateRO source);
}





