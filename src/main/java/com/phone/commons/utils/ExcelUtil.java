package com.phone.commons.utils;

import com.alibaba.excel.EasyExcel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @description
 */
public class ExcelUtil {

    public static void exportExcel(HttpServletResponse response, String fileNamePrefix, List<?> dataList, Class<?> dataClass) throws IOException {
        // 设置响应内容类型
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");

        String fileName = URLEncoder.encode(fileNamePrefix, "UTF-8") + ".xlsx";

        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        try (OutputStream out = response.getOutputStream()) {
            EasyExcel.write(out, dataClass)
                    .sheet(fileNamePrefix)
                    .doWrite(dataList);
        } catch (IOException e) {
            throw new IOException("文件导出失败", e);
        }
    }

}
