/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jnino
 */
public class HolaMundoServlet extends HttpServlet {

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
        /*response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HolaMundoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HolaMundoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
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

        //Elementos
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String[] tecnologias = request.getParameterValues("tecnologia");
        String genero = request.getParameter("genero");
        String[] musicas = request.getParameterValues("musica");
        String passwordDefault = "123456";
        PrintWriter out = response.getWriter();

        System.out.println("name: " + usuario + "\t Password: " + password);

        //Manejo cookies 
        Cookie[] cookies = request.getCookies();
        boolean usuarioNuevo = true;
        String mensaje=null;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("visitanteRecurrente") && c.getValue().equals("si")) {
                    usuarioNuevo = false;
                    break;
                }
            }
        }

        if (usuarioNuevo) {
            Cookie visitante = new Cookie("visitanreRecurrente", "si");
            response.addCookie(visitante);
            mensaje = "Bienvenido por primera vez";
        } else {
            mensaje = "Bienvenido de nuevo";
        }
        
        //Manejo Session
        String mensajeSesion;
        HttpSession httpSession = request.getSession();
        Integer contadorVisitas = (Integer)(httpSession.getAttribute("contadorVisitas"));
        
        System.out.println("contadorVisitasEntra: " +contadorVisitas);
        
        if (contadorVisitas == null){
            contadorVisitas=1;
            mensajeSesion ="Hola por primera vez";
        }
        else{
            contadorVisitas++;
            mensajeSesion ="Hola otra vez";
        }
        
        httpSession.setAttribute("contadorVisitas", contadorVisitas);
        System.out.println("contadorVisitasSale: " +contadorVisitas);
        
        
        
        

        if (password.equals(passwordDefault)) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HolaMundoServlet</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h1>Parametros procesados por el servlet</h1>");
            out.println("<h2>" + mensaje + "</h2>");
            
            out.println("<h2>" + mensajeSesion + "</h2>");
            out.println("<h2>" + "Numero Visitas" + contadorVisitas + "</h2>");
            

            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<td>");
            out.println("Usuario");
            out.println("</td>");
            out.println("<td>");
            out.println(usuario);
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>");
            out.println("Password");
            out.println("</td>");
            out.println("<td>");
            out.println(password);
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>");
            out.println("Genero");
            out.println("</td>");
            out.println("<td>");
            out.println(genero);
            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>");
            out.println("Tecnologia");
            out.println("</td>");
            out.println("<td>");
            for (String tecnologia : tecnologias) {
                out.println(tecnologia);
            }

            out.println("</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>");
            out.println("Musica");
            out.println("</td>");
            out.println("<td>");
            if (musicas != null) {
                for (String musica : musicas) {
                    out.println(musica);
                }
            }

            out.println("</td>");
            out.println("</tr>");
            out.println("</table>");

            out.println("</body>");
            out.println("</html>");

        } else {
            response.sendError(response.SC_UNAUTHORIZED, "Password Incorrecto");
        }

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
