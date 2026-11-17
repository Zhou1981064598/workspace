package com.zhou.deliverysystem.param;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@TableName("user")
public class UserParam  {
    @NotNull(message = "用户名不能为空")
    @NotBlank(message = "用户名不能全为空格")
    @Size(message = "用户名长度为6-25个字符",min = 6,max = 25)
    @Pattern(message = "用户名最少为4位,包括至少一个大写字母,一个小写字母，1个数字,一个特殊字符)",
            regexp = "^.*(?=.{6,25})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*?.]).*$"
    )
    private String userName;
    @NotNull(message = "密码不能为空")
    @NotBlank(message = "密码不能全为空格")
    @Size(message = "密码长度为6-25个字符",min = 6,max = 25)
    private String passWord;
}
