package importer;

import com.syswin.xwtoon.db.utils.excel.ExcelHelper;
import com.syswin.xwtoon.db.utils.excel.Print;
import com.syswin.xwtoon.db.utils.excel.Transfer2Sql;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 145846 on 2016/9/20.
 */
public class DoImport {
	public static void main(String[] args) throws IOException {
		try {
			ExcelHelper excelHelper = new ExcelHelper(DoImport.class.getResource("/").getPath() + "cc.xls");
			Map<Integer, String> mapping = new HashMap<Integer, String>();
			mapping.put(1, "sql1");
			mapping.put(3, "ttt");
			Transfer2Sql transfer2Sql = new Transfer2Sql(mapping, "test_tableName");
			// 只是打印，后面可以换个类做单条或者批量插入数据库
			Print print = new Print();
			excelHelper.traversal(transfer2Sql, print);
			// excelHelper.traversal(importExcel2Db, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
