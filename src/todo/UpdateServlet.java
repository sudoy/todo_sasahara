package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.beans.Todo;
import todo.utils.DBUtils;

@WebServlet("/update.html")
public class UpdateServlet extends HttpServlet {
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

			//GETパラメータを取得
			String id = req.getParameter("id");

			//SQL
			sql = "SELECT * FROM list WHERE id = ?";

			//SELECT準備
			ps = con.prepareStatement(sql);

			//パラメータをSELECTにセット
			ps.setString(1, id);

			//SELECTを実行
			rs = ps.executeQuery();

			rs.next();

			Todo list = new Todo(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getString("detail"),
					rs.getString("imp"),
					rs.getDate("limit_date"));

			req.setAttribute("list", list);

		}catch(Exception e){
			throw new ServletException(e);
		}finally{

			try{
				DBUtils.close(rs);
				DBUtils.close(ps);
				DBUtils.close(con);
			}catch(Exception e){

			}
		}

		//フォワード
		getServletContext().getRequestDispatcher("/WEB-INF/update.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//フォームにリダイレクト
		resp.sendRedirect("index.html");
	}



}
