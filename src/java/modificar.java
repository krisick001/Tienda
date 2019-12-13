/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
/**
 *
 * @author usuario
 */
@WebServlet(urlPatterns = {"/modificar"})
public class modificar extends HttpServlet {
private java.sql.Connection conexion = null;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet modificar</title>");            
            out.println("</head>");
            out.println("<body>");

            out.println("<table border=1>");
            try {
                Class.forName("org.gjt.mm.mysql.Driver");
                conexion = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "");
                Statement s = conexion.createStatement();
                ResultSet resultado = s.executeQuery("Select * from producto");
                while (resultado.next()) {
                    out.println("<form action=\"modificar\" method=\"POST\">"
                            + "<tr><td>"
                            + resultado.getObject("id")
                            + "</td><td>" + resultado.getObject("nombre")
                            + "</td><td>" + resultado.getObject("descripcion")
                            + "</td><td>" + resultado.getObject("stock")
                            + "</td><td>" + resultado.getObject("imagen")
                            + "</td><td>" + resultado.getObject("compania")
                            + "</td><td>" + resultado.getObject("plataforma")
                            + "</td><td>" + resultado.getObject("precio")
                            + "<tr>\n"
                            + "  <td></td>\n"
                            + "  <td><input type=\"submit\" >editar/td>\n"
                            + "</tr>"
                            + "</td></tr>"
                            + "</form>");
                }
                conexion.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            out.println("</table>"
                    + "<a href=\"index.html\">Cancelar</a>");
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
