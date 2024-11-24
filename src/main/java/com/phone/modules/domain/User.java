package com.phone.modules.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 电话表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ExcelIgnore
    private Integer id;

    /**
     * 用户名
     */
    @ExcelProperty(value = "姓名")
    @ColumnWidth(20)
    private String name;

    /**
     * 手机号码
     */
    @ExcelProperty(value = "手机号")
    @ColumnWidth(20)
    private String phone;

    /**
     * 家庭电话
     */
    @ExcelProperty(value = "家庭电话")
    @ColumnWidth(20)
    private String familyPhone;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    @ColumnWidth(20)
    private String remark;

    /**
     * 地址
     */
    @ExcelProperty(value = "地址")
    @ColumnWidth(20)
    private String addr;

    /**
     * 抖音号
     */
    @ExcelProperty(value = "抖音号")
    @ColumnWidth(20)
    private String douyin;

    /**
     * 邮箱
     */
    @ExcelProperty(value = "邮箱")
    @ColumnWidth(20)
    private String email;

    /**
     * 书签（1：加入，2：取消）
     */
    @ExcelIgnore
    private Integer status;

    /**
     *头像
     */
    @ExcelIgnore
    private String image;

    /**
     * 文件
     */
    @TableField(exist = false)
    @ExcelIgnore
    private MultipartFile file;

}
