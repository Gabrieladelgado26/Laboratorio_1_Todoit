package Servlets;

import com.mycompany.mundo.Archivos;
import com.mycompany.mundo.Usuario;
import java.io.IOException;
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
     * Este método se llama cuando se recibe una solicitud POST de un usuario
     * para iniciar sesión
     *
     * @param request El objeto HttpServletRequest que contiene la solicitud
     * HTTP
     * @param response El objeto HttpServletResponse que se utilizará para
     * enviar la respuesta HTTP
     * @throws ServletException Excepción que se lanza si hay un error en el
     * servlet
     * @throws IOException Excepción que se lanza si hay un error de entrada o
     * de salida
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener el valor de la cédula y la contraseña desde la solicitud HTTP
        int cedula = Integer.parseInt(request.getParameter("cedula"));
        String contrasenia = request.getParameter("contrasenia");

        // Obtener el contexto del servlet 
        ServletContext context = getServletContext();

        // Verificar si el usuario existe utilizando la función verificarUsuario
        String user = verificarUsuario(cedula, contrasenia, context);

        // Si el usuario existe, configurar atributos en la solicitud y redirigir a "login.jsp"
        if (user != null) {
            // request.setAttribute("nombre", user.getNombre());
            request.getRequestDispatcher("login.jsp?usuarioNombre=" + user).forward(request, response);
        } else {
            // Si el usuario no existe, redirigir a "index.jsp" con un parámetro "noExistente"
            request.getRequestDispatcher("index.jsp?noExistente=" + "false").forward(request, response);
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

    public static String verificarUsuario(int cedulaVerificada, String contraseñaVerificada, ServletContext context) throws IOException {
        ArrayList<Usuario> misUsuarios = new ArrayList<>();

        Archivos.leerArchivo(misUsuarios, context);

        for (Usuario usuario : misUsuarios) {
            if (usuario.getCedula() == cedulaVerificada && 
                usuario.getContrasenia().equals(contraseñaVerificada)) {
                return usuario.getNombre();
            }
        }
        return null;
    }

}
