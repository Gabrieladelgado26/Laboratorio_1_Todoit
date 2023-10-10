package Servlets;

import com.mycompany.mundo.Archivos;
import com.mycompany.mundo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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

        // Obtener la sesión actual
        HttpSession session = request.getSession();

        // Obtener el contexto del servlet
        ServletContext context = getServletContext();

        ArrayList<Usuario> misUsuarios = new ArrayList<>();

        // Leer usuarios existentes desde el archivo
        Archivos.leerArchivo(misUsuarios, context);

        // Obtener datos del formulario enviados por POST
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String contrasenia = request.getParameter("contrasenia");

        // Verificar si la cédula ya existe
        boolean cedulaExistente = false;
        
        for (Usuario existingUsuario : misUsuarios) {
            if (existingUsuario.getCedula() == Integer.parseInt(cedula)) {
                cedulaExistente = true;
                break;
            }
        }

        if (!cedulaExistente) {
            // Si la cédula no existe, agregamos el nuevo usuario
            Usuario usuario = new Usuario(Integer.parseInt(cedula), nombre, contrasenia);
            misUsuarios.add(usuario);

            // Escribir la lista actualizada en el archivo
            Archivos.escribirArchivo(misUsuarios, context);

            String existente = "verdadero";
            request.setAttribute("existente", existente);
            
        } else {
            // La cédula ya existe
            String existente = "falso";
            request.setAttribute("existente", existente);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
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
