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

/**
 *
 * @author Karoll Gabriela Delgado - Leidy Tatiana Cuasquer
 */
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Este método se llama cuando se recibe una solicitud POST de un usuario para iniciar sesión
     * @param request El objeto HttpServletRequest que contiene la solicitud
     * HTTP
     * @param response El objeto HttpServletResponse que se utilizará para enviar la respuesta HTTP
     * @throws ServletException Excepción que se lanza si hay un error en el servlet
     * @throws IOException Excepción que se lanza si hay un error de entrada o de salida
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

    /**
     * Verifica si un usuario con la cédula y contraseña proporcionadas existe
     * en una lista de usuarios almacenada en un archivo.
     *
     * @param cedulaVerificada La cédula del usuario a verificar.
     * @param contraseñaVerificada La contraseña del usuario a verificar.
     * @param context El contexto del servlet para acceder al archivo de usuarios.
     * @return El nombre del usuario si se encuentra, o null si no se encuentra.
     * @throws IOException En caso de errores al leer el archivo.
     */
    public static String verificarUsuario(int cedulaVerificada, String contraseñaVerificada, ServletContext context) throws IOException {
        // Crea una lista para almacenar los usuarios
        ArrayList<Usuario> misUsuarios = new ArrayList<>();

        // Lee el archivo de usuarios y carga los datos en la lista
        Archivos.leerArchivo(misUsuarios, context);

        // Itera a través de la lista de usuarios
        for (Usuario usuario : misUsuarios) {
            // Comprueba si el usuario tiene la misma cédula y contraseña proporcionadas
            if (usuario.getCedula() == cedulaVerificada
                    && usuario.getContrasenia().equals(contraseñaVerificada)) {
                // Si se encuentra el usuario, devuelve su nombre
                return usuario.getNombre();
            }
        }

        // Si no se encuentra el usuario, retorna null
        return null;
    }
}
