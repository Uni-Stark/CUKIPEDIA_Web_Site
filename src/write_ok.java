import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;


/**
 * Servlet implementation class write_ok
 */
@WebServlet("/write_ok")
public class write_ok extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public write_ok() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      response.getWriter().append("Served at: ").append(request.getContextPath());
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
	   HttpSession session=request.getSession();
		String ID;
		HttpSession ch = request.getSession();
		if (ch.getAttribute("ID") == null) {
			response.sendRedirect("needlogin.jsp");
			session.invalidate();
		}
        response.setContentType("text/html;charset=utf-8");
         BoardInfo info = new BoardInfo();
         String memo = new String(request.getParameter("question").getBytes("8859_1"), "UTF-8");
         memo = memo.replaceAll("\\\\","\\\\\\\\");
         String []filter_word={"\\\"","\\'","\""};
         String temp = "\\";
         for(int i = 0; i<filter_word.length;i++){
        	 memo = memo.replaceAll(filter_word[i],temp.concat(filter_word[i]));

         }
         String gr= (String) session.getAttribute("gr");  
         String size=(String) session.getAttribute("seqsize");
         
         
        // session.invalidate();
         int cnt = 0;
         try {
             Connection con = null;
             con = DriverManager.getConnection(
);
             java.sql.Statement st = null;
             st = con.createStatement();
             st.executeUpdate("insert into board (con, gr, seq, level) values ('" + memo + "',"+ gr +","+ size + ", 1 )");
             response.sendRedirect("MakeBoard?subject="+gr);
             }
           catch (SQLException sqex) {
             System.out.println("getMessage : " + sqex.getMessage());
             System.out.println("getSQLState : " + sqex.getSQLState());
          }
          
          request.setAttribute("search_info", info);
       }
}