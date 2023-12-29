package kr.project.backend.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class UserToken {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String accessToken;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String refreshToken;
}
