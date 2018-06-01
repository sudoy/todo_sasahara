package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

import todo.utils.DBUtils;


@WebServlet("/entry.html")
public class EntryServlet extends HttpServlet {


	private List<String> validate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{

		List<String> errors = new ArrayList<>();

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

		if(req.getParameter("limit_date") != "") {
			//形式の判定
			try {
				DateFormat format=new SimpleDateFormat("yyyy/MM/dd");
			    format.parse(req.getParameter("limit_date"));

			}catch(ParseException p) {

				errors.add("期限は「YYYY/MM/DD」形式で入力して下さい。");

			}
		}
		return errors;

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//フォワード
		getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		List<String> errors = validate(req, resp);


		if(errors.size() > 0) {
			//エラー処理
			req.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);
		}else {

			Connection con = null;
			PreparedStatement ps = null;
			String sql = null;

			//正規処理
			try{
				//データベース接続
				con = DBUtils.getConnection();

				if(req.getParameter("limit_date").equals("")){

					//SQL
					sql = "INSERT INTO list(title, detail, imp)VALUES(?,?,?)";

					//INSERT準備
					ps = con.prepareStatement(sql);

					//INSERTにデータをセット
					ps.setString(1, req.getParameter("title"));
					ps.setString(2, req.getParameter("detail"));
					ps.setString(3, req.getParameter("imp"));

				}else {

					//SQL
					sql = "INSERT INTO list(title, detail, imp, limit_date)VALUES(?,?,?,?)";

					//INSERT準備
					ps = con.prepareStatement(sql);

					//INSERTにデータをセット
					ps.setString(1, req.getParameter("title"));
					ps.setString(2, req.getParameter("detail"));
					ps.setString(3, req.getParameter("imp"));
					ps.setString(4, req.getParameter("limit_date"));

				}

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

}
