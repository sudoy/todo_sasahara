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
import javax.servlet.http.HttpSession;

import todo.beans.User;
import todo.utils.DBUtils;

@WebServlet("/login.html")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//フォワード
		getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			//データベース接続
			con = DBUtils.getConnection();

			//SQL
			sql = "SELECT id, name, mail, pass  FROM users WHERE mail = ? AND pass = MD5(?)";

			//SELECT命令の準備
			ps = con.prepareStatement(sql);

			//INSERTにデータをセット
			ps.setString(1, req.getParameter("mail"));
			ps.setString(2, req.getParameter("pass"));

			//SELECTを実行
			rs = ps.executeQuery();

			if (rs.next()){
				//ログイン成功時
				User login = new User(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("pass"));

				session.setAttribute("login", login);
				resp.sendRedirect("index.html");
			}else{
				//失敗時

				List<String> errors = new ArrayList<>();
				errors.add("メールアドレス、またはパスワードが間違っています。");
				session.setAttribute("errors", errors);

				getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
			}

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
