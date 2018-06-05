package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.beans.Todo;
import todo.utils.DBUtils;
import todo.utils.HTMLUtils;

@WebServlet("/update.html")
public class UpdateServlet extends HttpServlet {

	private List<String> validate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{

		List<String> errors = new ArrayList<>();

		if(req.getParameter("id") == null || req.getParameter("id").equals("")) {
			errors.add("不正なアクセスです。");

		}

		//題名チェック
		if(req.getParameter("title").equals("")) {
			errors.add("題名は必須入力です。");

		}

		//文字数多い
		if(req.getParameter("title").length() > 100){

			errors.add("題名は100文字以内にしてください。");

		}

		//重要度が1から3以外
		if(	!(req.getParameter("imp").equals("★") ||
			req.getParameter("imp").equals("★★") ||
			req.getParameter("imp").equals("★★★")) ) {

			errors.add("不正なアクセスです。");

		}

		if(!req.getParameter("limit_date").equals("")) {
			//形式の判定
			try {
				DateFormat df=new SimpleDateFormat("yyyy/MM/dd");

			    String s1 = req.getParameter("limit_date");
			    String s2 = df.format(df.parse(s1));

			    if(s1.equals(s2)) {

			    }else {
			    	errors.add("期限は「YYYY/MM/DD」形式で入力して下さい。");
			    }

			}catch(ParseException p) {

				errors.add("期限は「YYYY/MM/DD」形式で入力して下さい。");

			}
		}
		return errors;

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if(!HTMLUtils.checkLogin(req, resp)) {
			return;
		}

		HttpSession session = req.getSession();


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
			sql = "SELECT id, title, detail, imp, limit_date FROM list WHERE id = ?";

			//SELECT準備
			ps = con.prepareStatement(sql);

			//パラメータをセット
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

			//フォワード
			getServletContext().getRequestDispatcher("/WEB-INF/update.jsp").forward(req, resp);

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



	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if(!HTMLUtils.checkLogin(req, resp)) {
			return;
		}

		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		Object log = session.getAttribute("login");

		List<String> errors = validate(req, resp);


		if(errors.size() > 0) {
			//エラー処理
			session.setAttribute("errors", errors);

			getServletContext().getRequestDispatcher("/WEB-INF/update.jsp").forward(req, resp);

		}else {

			Connection con = null;
			PreparedStatement ps = null;
			String sql = null;

			try {
				con = DBUtils.getConnection();

				sql = "UPDATE list SET title = ?, detail = ?, imp = ?, limit_date = ? WHERE id = ?";

				//準備
				ps = con.prepareStatement(sql);

				//データをセット
				ps.setString(1, req.getParameter("title"));
				ps.setString(2, req.getParameter("detail"));
				ps.setString(3, req.getParameter("imp"));

				if(req.getParameter("limit_date").equals("")) {
					ps.setString(4, null);
				}else {
					ps.setString(4, req.getParameter("limit_date"));
				}

				ps.setString(5, req.getParameter("id"));

				//実行
				ps.executeUpdate();

				//遷移
				List<String> successes = new ArrayList<>();
				successes.add("更新しました。");
				session.setAttribute("successes", successes);

				//リダイレクト
				resp.sendRedirect("index.html");


			}catch(Exception e){
				throw new ServletException(e);

			}finally{

				try{
					DBUtils.close(ps);
					DBUtils.close(con);

				}catch(Exception e){

				}
			}


		}

	}



}
