package com.apitest.UIAutomation.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserVo {
    private String userId;
    private String password;
}
