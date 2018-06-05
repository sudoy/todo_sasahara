package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.utils.DBUtils;
import todo.utils.HTMLUtils;

@WebServlet("/delete.html")
public class DeleteServlet extends HttpServlet {

	private List<String> validate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{

		List<String> errors = new ArrayList<>();

		//idチェック
		if(req.getParameter("id") == null || req.getParameter("id").equals("")) {
			errors.add("不正なアクセスです。");

		}
		return errors;

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if(!HTMLUtils.checkLogin(req, resp)) {
			return;
		}

		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();

		List<String> errors = validate(req, resp);



		if(errors.size() > 0) {
			//エラー処理
			session.setAttribute("errors", errors);

			//リダイレクト
			resp.sendRedirect("index.html");

		}else {

			Connection con = null;
			PreparedStatement ps = null;
			String sql = null;

			try {
				con = DBUtils.getConnection();

				sql = "DELETE FROM list WHERE id = ?";

				//準備
				ps = con.prepareStatement(sql);

				//データをセット
				ps.setString(1, req.getParameter("id"));

				//実行
				ps.executeUpdate();


				//遷移
				List<String> successes = new ArrayList<>();
				successes.add("削除しました。");
				session.setAttribute("successes", successes);

				//フォームにリダイレクト
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
