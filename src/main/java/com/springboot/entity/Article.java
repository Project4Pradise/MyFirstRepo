package com.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author pecho
 * @since 2023-12-06
 */
@Getter
@Setter
  public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * id
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 标题
     */
      private String name;

      /**
     * 内容
     */
      private String content;

      /**
     * 发布人
     */
      private String user;

      /**
     * 发布时间
     */
      private String time;


}
