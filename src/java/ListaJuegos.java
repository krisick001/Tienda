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
@WebServlet(urlPatterns = {"/ListaJuegos"})
public class ListaJuegos extends HttpServlet {

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
            out.println("<DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<style>"
                    + "body{background:rgba(0,0,0,0.2)}"
                    + "#contenedor {margin:0 auto;width:30%;height:200px;border:1px solid white; border-radius:25px 15px;}"
                    + "table{margin:10px;}"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div id='contenedor'>");
            out.println("<table border=1>");
            try {
                Class.forName("org.gjt.mm.mysql.Driver");
                conexion = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "");
                Statement s = conexion.createStatement();
                ResultSet resultado = s.executeQuery("Select * from producto");
                while (resultado.next()) {
                    out.println("<form action=\"modificar\" method=\"POST\">"
                            + ""
                            + "" + resultado.getObject("id")
                            + "" + resultado.getObject("nombre")
                            + "" + resultado.getObject("descripcion")
                            + "" + resultado.getObject("stock")
                            + "" + resultado.getObject("imagen")
                            + "" + resultado.getObject("compania")
                            + "" + resultado.getObject("plataforma")
                            + "" + resultado.getObject("precio")
                            + ""
                            + ""
                            + "<input type=\"submit\" value=<\"Modificar\">editar"
                            + ""
                            + ""
                            + "</form>");
                }
                conexion.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            out.println("</table>"
                    + "<a href=\"index.html\"></a>");
            out.println("</div>"
                    + "</body>"
                    + "</html>");
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
