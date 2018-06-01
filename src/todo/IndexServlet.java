package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.beans.Todo;
import todo.utils.DBUtils;

@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			//データベース接続

			con = DBUtils.getConnection();

			//SQL
			sql = "SELECT id, title, detail, imp, limit_date FROM list ORDER BY id";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			//SELECTを実行
			rs = ps.executeQuery();

			List<Todo> list = new ArrayList<>();

			while(rs.next()) {
				Todo a = new Todo(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("detail"),
						rs.getString("imp"),
						rs.getDate("limit_date"));

				list.add(a);

			}

			//JSPへ渡す
			req.setAttribute("list", list);


			//フォワード
			getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);

		}catch(Exception e){
			throw new ServletException(e);

		}finally {
			try{
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			}catch(Exception e){

			}
		}


	}

}
