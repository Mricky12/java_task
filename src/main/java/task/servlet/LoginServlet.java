package task.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import task.DBCon;
import task.dao.AdminsDAO;
import task.dao.UsersDAO;
import task.dto.AdminsDTO;
import task.dto.UsersDTO;

//サーブレットが「どのURLで動作するか」を指定する
//task のリクエストが来たときに、このサーブレットが呼び出される
@WebServlet(name = "top",urlPatterns = "/top")

//サーブレットを作成するためのクラスを定義
//HttpServlet を継承して、HTTPリクエストを処理する能力を持たせる
public class LoginServlet extends HttpServlet {
	//シリアライズ（オブジェクトを保存・転送）する際のバージョン管理用ID。
	//サーブレットで必須ではないが、クラスがシリアライズ可能であることを明示。
	private static final long serialVersionUID = 1L;
	
	//サーブレットの初期化用コンストラクタ。
	//super() で親クラス（HttpServlet）のコンストラクタを呼び出しています。
	public LoginServlet() {
        super();
	}
	
	//HTTPのGETリクエストを処理するためのメソッド
	//ブラウザでURLを直接開いたり、リンクをクリックしたときに呼び出される
	//HttpServletRequest request: リクエスト情報（送信されたデータやヘッダーなど）を取得する
	//HttpServletResponse response: レスポンス情報（画面に返す内容）を作成する
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//レスポンス（画面に表示する内容）のデータ形式を設定。
		//text/html: HTML形式で返すことを示す。
		response.setContentType("text/html; charset=UTF-8");
//		String action = request.getParameter("action");
//
//        if (action == null || action.isEmpty()) {
//            // デフォルトの処理 (URL直接アクセス時)
//            response.getWriter().println("ログインページへようこそ！");
            request.getRequestDispatcher("top.jsp").forward(request, response);
           
		
		
	}
	@Override
    // POSTリクエスト処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
       

        try (Connection connection = DBCon.getConnection()) {
            if ("admin".equals(action)) {
                AdminsDAO adminsDAO = new AdminsDAO();
                AdminsDTO admin = adminsDAO.findAdminByEmailAndPassword(email, password, connection);

                if (admin != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("loggedInAdmin", admin);
                    response.sendRedirect("admin_usersearch.jsp");
                } else {
                    request.setAttribute("error", "※メールアドレス、パスワードが間違っています。※");
                    request.getRequestDispatcher("top.jsp").forward(request, response);
                }
            } else if ("user".equals(action)) {
                UsersDAO usersDAO = new UsersDAO();
                UsersDTO user = usersDAO.findUserByEmailAndPassword(email, password, connection);

                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("loggedInUser", user);
                    response.sendRedirect("myselftask.jsp");
                } else {
                    request.setAttribute("error", "ユーザーメールアドレスまたはパスワードが間違っています。");
                    request.getRequestDispatcher("top.jsp").forward(request, response);
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "無効なアクションです。");
            }
        } catch (SQLException e) {
            throw new ServletException("データベース接続エラー", e);
        }
    }
     
}