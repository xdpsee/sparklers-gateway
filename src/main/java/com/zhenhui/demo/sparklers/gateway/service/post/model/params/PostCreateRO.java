package com.zhenhui.demo.sparklers.gateway.service.post.model.params;

import com.zhenhui.sparkler.api.domain.common.Content;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class PostCreateRO {
    @NotNull
    private Integer type;
    @NotNull
    private String title;
    @Nullable
    private String description;
    @NotNull @Valid
    private Content content;
    @NotNull
    private Long creatorId;
    @NotNull @Min(1)
    private Long sectionId;
}

