package todo.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class HTMLUtils {

	public static String dateFormat(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		return sdf.format(d);
	}

}



