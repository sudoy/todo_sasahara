package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.utils.DBUtils;


@WebServlet("/entry.html")
public class EntryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//フォワード
		getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		req.setCharacterEncoding("utf-8");

		try{
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "INSERT INTO list(title, detail, imp, limit_date)VALUES(?,?,?,?)";

			//INSERT準備
			ps = con.prepareStatement(sql);

			//INSERTにデータをセット
			ps.setString(1, req.getParameter("title"));
			ps.setString(2, req.getParameter("detail"));
			ps.setString(3, req.getParameter("imp"));
			ps.setString(4, req.getParameter("limit_date"));

			//INSERT実行
			ps.executeUpdate();



		}catch(Exception e){
			throw new ServletException(e);
		}finally{

			try{
				DBUtils.close(ps);
				DBUtils.close(con);

			}catch(Exception e){

			}
		}
		//フォームにリダイレクト
		resp.sendRedirect("index.html");


	}

}
