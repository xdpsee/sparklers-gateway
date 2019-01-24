package com.zhenhui.demo.sparklers.gateway.service.post.model.result;

import com.zhenhui.sparkler.api.domain.common.Content;
import lombok.Data;

@Data
public class PostVO {

    private Long id;

    private Integer type;

    private String title;

    private String description;

    private Content content;

    private Long createAt;

    private Long updateAt;

    private Integer status = 0;

    private UserVO user;

    @Data
    public static class UserVO {

        public Long id;

        public String name;

        public String avatar;
    }
}
