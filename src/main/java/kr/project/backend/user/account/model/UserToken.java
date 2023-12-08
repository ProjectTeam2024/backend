package kr.project.backend.user.account.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class UserToken {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String wpayToken;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String wpayRefreshToken;
}
