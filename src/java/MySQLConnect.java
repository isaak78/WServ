
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;

/**
 * Servlet 
 */

public class MySQLConnect extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://172.17.0.2:3306/form2", "root", "xxxxxx");
            PreparedStatement pst = conn.prepareStatement("Select username,password from pessoa where username=? and password=?");
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                out.println("<br> Login OK <br><br> Bem-Vindo ao Form2");
            } 
            else {
                out.println("<br> Login Incorrecto <br><br> Adeus");
            }
        } 
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
