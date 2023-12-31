package com.springboot.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author pecho
 * @since 2023-10-26
 */
@Getter
@Setter
  @TableName("sys_user")
@ApiModel(value="User对象",description = "")
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * id
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 用户名
     */
      private String username;

      /**
     * 密码
     */
      private String password;

      /**
     * 昵称
     */
      private String nickname;

      /**
     * 邮箱
     */
      private String email;

      /**
     * 电话
     */
      private String phone;

      /**
     * 地址
     */
      private String address;

      /**
     * 创建时间
     */
      private Date createTime;

      /**
     * 头像
     */
      @Alias("avatar_Url")
      private String avatarUrl;
  /**
   * 是否登录
   */
      private Integer islogin;

  private String role;

  @TableField(exist = false)
  private List<Course> courses;
  @TableField(exist = false)
  private List<Course> stuCourses;

}
