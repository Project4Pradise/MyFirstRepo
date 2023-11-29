package com.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2023-11-24
 */
@Getter
@Setter
  public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * id
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String name;

      /**
     * 学分
     */
      private Integer score;

      /**
     * 上课时间
     */
      private String times;

      /**
     * 是否开课
     */
      private Boolean state;

      /**
     * 授课老师id
     */
      private Integer teacherId;

      @TableField(exist = false)
      private String teacher;


}
