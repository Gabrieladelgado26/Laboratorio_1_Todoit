
package Servlets;

import com.mycompany.mundo.Archivos;
import com.mycompany.mundo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

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
            out.println("<title>Servlet SvLogin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvLogin at " + request.getContextPath() + "</h1>");
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
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int cedula = Integer.parseInt(request.getParameter("cedula"));
        String contrasenia=request.getParameter("contrasenia");
        
        ServletContext context=getServletContext();
       
        Usuario user = verificarUsuario(cedula,contrasenia, context);
        
        if (user != null)
        {
            request.setAttribute("cedula", user.getCedula());
            request.setAttribute("nombre", user.getNombre());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else
        {
            request.getRequestDispatcher("index.jsp?noExistente="+"false").forward(request, response);
        }
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
    
    public static Usuario verificarUsuario(int cedulaV, String contraseñaV, ServletContext context) throws IOException{
        ArrayList<Usuario> misUsuarios = new ArrayList<>();

        Archivos.leerArchivo(misUsuarios, context);
        
        for (Usuario u: misUsuarios){
            if (u.getCedula()==cedulaV && u.getContrasenia().equals(contraseñaV)){
                return u;
            }
        }
        return null;
    }

}
