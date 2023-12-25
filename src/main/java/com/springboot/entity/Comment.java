package com.springboot.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author pecho
 * @since 2023-12-11
 */
@Getter
@Setter
  @TableName("t_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 内容
     */
      private String content;

      /**
     * 评论人id
     */
      @TableField("user_id")
      private Integer userId;

      /**
     * 评论时间
     */
      private String time;

      /**
     * 父级id
     */
      private Integer pid;

      @TableField(exist = false)
      private String pNickname;//父节点用户昵称

      @TableField(exist = false)
      private Integer pUserId;//父节点用户id

      /**
     * 最上级评论id
     */
      private Integer originId;

      /**
     * 关联文章id
     */
      private Integer articleId;
      @TableField(exist = false)
      private String nickname;
      @TableField(exist = false)
      private String avatarUrl;
      @TableField(exist = false)
      private List<Comment> children;


}
