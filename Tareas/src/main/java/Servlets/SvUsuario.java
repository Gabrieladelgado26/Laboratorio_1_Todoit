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
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvUsuario", urlPatterns = {"/SvUsuario"})
public class SvUsuario extends HttpServlet {

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
            out.println("<title>Servlet SvUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvUsuario at " + request.getContextPath() + "</h1>");
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
        
        //Obtener la sesion actual
        HttpSession session = request.getSession();

        //Obtener el contexto del servlet
        ServletContext context = getServletContext();
        
        ArrayList<Usuario> misUsuarios = new ArrayList<>();

        // Obtener la cédula del formulario enviada por POST
        String cedulaIngreso = request.getParameter("cedula");
        
        // Obtener la contrasenia del formulario enviada por POST
        String contraseniaIngreso = request.getParameter("contrasenia");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Obtener la sesion actual
        HttpSession session = request.getSession();

        //Obtener el contexto del servlet
        ServletContext context = getServletContext();
        
        ArrayList<Usuario> misUsuarios = new ArrayList<>();

        // Obtener datos del formulario enviados por POST
        String cedula = request.getParameter("cedula");
        
        String nombre = request.getParameter("nombre");

        String nombreMinuscula = nombre.toLowerCase();

        String contrasenia = request.getParameter("contrasenia");

            // Crear un objeto Perro con los datos ingresados y el nombre del archivo de imagen
            Usuario usuario = new Usuario(Integer.parseInt(cedula), nombre, contrasenia);

            // Agregar el objeto Perro a la lista de perros solo si el nombre y la imagen no están repetidos
            misUsuarios.add(usuario);

            //Guardar la lista actualizada en un archivo
            Archivos.escribirArchivo(misUsuarios, context);

            // Agregar la lista completa de perros como atributo en la sesión
            session.setAttribute("misUsuarios", misUsuarios);

            // Redireccionar a la página de destino
            response.sendRedirect("index.jsp");
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