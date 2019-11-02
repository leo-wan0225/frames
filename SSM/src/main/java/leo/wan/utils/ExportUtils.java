package leo.wan.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@Slf4j
public class ExportUtils<E, T> {
    /**
     * 用响应流的方式将文件返回下载到客户端
     *
     * @param file
     * @param response
     * @param fileName
     * @throws IOException
     */
    public void exportFile(File file, HttpServletResponse response, String fileName) throws IOException {
        //设置消息头
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);//可能需要urlEncode一下
        response.setContentType("application/octet-stream");
        OutputStream os = null;
        InputStream is = null;
        try {
            os = response.getOutputStream();
            is = new FileInputStream(file);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = is.read(buff)) > 0) {
                os.write(buff, 0, length);
            }
        } catch (IOException e) {

        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.flush();
                os.close();
            }
        }

    }

    /**
     * 导出excel
     */
    public void exportExcel(HttpServletResponse response, Map<String, List<T>> sheetDatas, Map heads, String fileName) throws IOException {
        Workbook workbook = ExcelUtils.createWorkBook(sheetDatas, heads);
        //设置消息头
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);//可能需要urlEncode一下
        response.setContentType("application/octet-stream");
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            workbook.write(os);
        } catch (IOException e) {
            log.error("下载excel失败", e);
        } finally {
            if (os != null) {
                os.flush();
                os.close();
            }

        }
    }
}
