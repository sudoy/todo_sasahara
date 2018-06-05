package todo.utils;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HTMLUtils {

	public static String dateFormat(Date d) {
		if(d != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			return sdf.format(d);

		}else {
			return "";
		}
	}

	public static boolean checkLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		HttpSession session = req.getSession();
		Object log = session.getAttribute("login");

		if (log == null){
			//未承認
			resp.sendRedirect("login.html");
			return false;
		}else {
			return true;
		}


	}
}



