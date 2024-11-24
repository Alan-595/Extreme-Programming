package com.phone.modules.controller;

import com.alibaba.excel.EasyExcel;
import com.phone.commons.config.ExcelListenerConfig;
import com.phone.commons.r.Result;
import com.phone.commons.r.ResultCodeEnum;
import com.phone.commons.utils.ExcelUtil;
import com.phone.modules.domain.User;
import com.phone.modules.service.UserService;
import com.phone.modules.service.impl.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @description
 */
@RestController
@RequestMapping("excel")
public class ExcelController {
    @Autowired
    private UserService userService;

    @Resource
    private ExcelListenerConfig excelListenerConfig;

    //导出
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<User> list = userService.list();
        ExcelUtil.exportExcel(response, "电话簿", list, User.class);
    }

    //导入
    @PostMapping("/import")
    public Result<?> importExcel(@RequestParam("file") MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), User.class, excelListenerConfig).sheet().doRead();
            return Result.success();
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail(ResultCodeEnum.OPERATION_ERROR);
        }
    }

}
