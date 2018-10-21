package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Consulta
 */
@WebServlet("/Consulta")
public class Consulta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Consulta() {
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
		//doGet(request, response);
        PrintWriter out = response.getWriter();
       Connection con = null; 
       Statement  stmt = null;
       ResultSet rs = null;
       
       
       String usuario = "root";
       String contraseña = "12345";
       String url = "jdbc:mysql://localhost:3306/escuela";
       
       /*int num =Integer.parseInt(request.getParameter("num"));
       String nombre =request.getParameter("nombre");
       String domicilio =request.getParameter("domicilio");
       int telefono =Integer.parseInt(request.getParameter("telefono"));*/
   
       try{
              Class.forName("com.mysql.jdbc.Driver");
              con = DriverManager.getConnection(url, usuario, contraseña);
              
              if ( con != null ) 
               
               stmt = con.createStatement();
               rs = stmt.executeQuery("select * from alumnos");
               
               
                 out.println("<!DOCTYPE html>");
                 out.println("<html>");
                 out.println("<head>");
                 out.println("<title>Servlet Consulta</title>");            
                 out.println("</head>");
                 out.println("<body>");
                 out.println("<table border='1'>");
                 out.println("<tr>");
                 out.println("<th>NC</th>");
                 out.println("<th>Nombre</th>");
                 out.println("<th>Domicilio</th>");
                 out.println("<th>Telefono</th>");
                 out.println("</tr>");
                 
                 while(rs.next()){
                     /*int nc = rs.getInt(1);
                     String nombre = rs.getString(2);
                     String domicilio = rs.getString("domicilio"); //tambien se imprime de este modo
                     int telefono = rs.getInt(4);*/
                  out.println("<tr>");   
                  out.println("<td>"+rs.getInt(1)+"</td>");
                  out.println("<td>"+rs.getString(2)+"</td>");
                  out.println("<td>"+rs.getString("domicilio")+"</td>");
                  out.println("<td>"+rs.getInt(4)+"</td>");   
                  out.println("</tr>"); 
                 }
                 out.println("</table>");
                 out.println("</body>");
                 out.println("</html>");
               
               
             
              
          }catch(ClassNotFoundException e)
          {
              System.out.println("Error"+e); 
          }catch (SQLException e)
          {
              System.out.println("Error"+e);
          }finally { 
             if ( con != null ) { 
                 try    { 
                     con.close(); 
                     stmt.close(); 
                 } catch( Exception e ) { 
                     System.out.println( e.getMessage()); 
                 } 
             }
       }
}
	}

