package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.utils.DBUtils;


@WebServlet("/entry.html")
public class EntryServlet extends HttpServlet {

	private boolean check;

	private void validate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{

		check = false;

		//題名チェック
		if(req.getParameter("title").equals("")) {

			check = true;

		}

		//文字数多い
		if(req.getParameter("title").length() > 100){

			check = true;

		}

		//重要度が1から3以外
		if(	!(req.getParameter("imp").equals("★") ||
			req.getParameter("imp").equals("★★") ||
			req.getParameter("imp").equals("★★★")) ) {
			check = true;

		}

		if(req.getParameter("limit_date") != "") {
			//形式の判定
			try {
				DateFormat format=new SimpleDateFormat("yyyy/MM/dd");
			    format.parse(req.getParameter("limit_date"));


			}catch(ParseException p) {
				check = true;

			}
		}

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

		//バリデーション
		validate(req, resp);


		if(check == true) {
			//エラー処理
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
