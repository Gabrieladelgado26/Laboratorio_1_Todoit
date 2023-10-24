
package Servlets;

import com.mycompany.mundo.Archivos;
import com.mycompany.mundo.ListasEnlazadas;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author INES CHAR
 */
@WebServlet(name = "SvBuscarOrdenar", urlPatterns = {"/SvBuscarOrdenar"})
public class SvBuscarOrdenar extends HttpServlet {

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
            throws ServletException, IOException, FileNotFoundException {

        // Obtener el nombre de usuario
        String nombreUsuario = request.getParameter("usuarioNombre");

        ListasEnlazadas listaEnlazada = new ListasEnlazadas();
        
        ServletContext context = getServletContext();

        try {
            // Leer la lista de tareas desde el contexto del servlet
            listaEnlazada = Archivos.leerArchivoTareas(context);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvBuscarOrdenar.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (listaEnlazada == null) {
            listaEnlazada = new ListasEnlazadas();
        }

        // Obtener el valor del input de búsqueda por ID
        String inputId = request.getParameter("inputId");

        // Generar la tabla de tareas según la búsqueda
        String tablaTareas = "";
        
        if (inputId != null && !inputId.isEmpty()) {
            // Si se proporciona un ID, generar una tabla de tareas filtrada por ese ID
            tablaTareas = listaEnlazada.generarTablaBusqueda(inputId);
        } else {
            // Si no se proporciona un ID, generar la tabla de todas las tareas
            tablaTareas = listaEnlazada.MostrarLista();
        }
        response.sendRedirect("login.jsp?buscar=" + inputId + "&usuarioNombre=" + nombreUsuario);
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
