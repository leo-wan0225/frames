package leo.wan.test.unit;

import leo.wan.model.QuestionItem;
import leo.wan.utils.ExcelUtils;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class TestExcelUtils {
    @Test
    public void testExcel() throws IOException {
        File file = new File("C:\\Users\\wj\\Desktop\\test.xlsx");
        List<List<String>> restuls = ExcelUtils.parserExcel(FileUtils.openInputStream(file));
        for (int i = 0; i < restuls.size(); i++) {
            System.out.print("第" + (i + 1) + "行");
            if (restuls.get(i) == null) {
                System.out.println("null");
            } else {
                System.out.println(restuls.get(i).get(0));
            }
        }
        System.out.println(restuls);
    }

    @Test
    public void export() throws IOException {
        File file = new File("C:\\Users\\wj\\Desktop\\questionItem.xlsx");
        List<QuestionItem> questionItems = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            QuestionItem questionItem = new QuestionItem();
            questionItem.setId(i);
           questionItem.setAttribute("Attribute" + i);
            questionItem.setSubArrtibute("SubArrtibute" + i);
            questionItem.setUpdateTime(new Date());
            questionItems.add(questionItem);
        }
        Map map = new HashMap(16);
        map.put("mysheet", questionItems);
        Map sheetNames = new LinkedHashMap();
        sheetNames.put("id", "id值");
        sheetNames.put("attribute", "属性");
        sheetNames.put("subArrtibute", "子属性");
        sheetNames.put("questionType", "问题类型");
        sheetNames.put("updateTime", "更新时间");
        Workbook workbook = ExcelUtils.createWorkBook(map, sheetNames);
        OutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
    }

}
