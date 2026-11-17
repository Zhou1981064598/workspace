package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "UserInfo对象", description = "")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户工号")
    private Integer userId;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "0为男，1为女")
    private Integer userSex;

    @ApiModelProperty(value = "用户身份证号码")
    private String userIdentity;

    @ApiModelProperty(value = "Email")
    private String studentEmail;

    @ApiModelProperty(value = "用户手机号码")
    private String userPhone;

    @ApiModelProperty(value = "0学生，1教师，2管理员")
    private Integer userRole;

    @ApiModelProperty(value = "用户密码")
    private String userPassword;

    @ApiModelProperty(value = "用户登录令牌")
    private String userToken;

    @ApiModelProperty(value = "教师职称")
    private Integer teacherRole;

    @ApiModelProperty(value = "是否停用,0否，1是")
    private Boolean userAbandon;

    @ApiModelProperty(value = "学生是否毕业，0否，1是")
    private Boolean studentGraduate;

    @ApiModelProperty(value = "是否登录过，0否，1是")
    private Boolean hasLogin;

    private Date createTime;

    private Integer createPerson;

    private Date lastUpgradeTime;

    private Integer lastUpgradePerson;

}
