package Servlets;

import com.mycompany.mundo.Archivos;
import com.mycompany.mundo.ListasEnlazadas;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Gabriela Delgado - Leidy Tatiana Cuasquer
 */
@WebServlet(name = "SvEditar", urlPatterns = {"/SvEditar"})
public class SvEditar extends HttpServlet {

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
            out.println("<title>Servlet SvEditar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvEditar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Este método se llama cuando se recibe una solicitud POST de un usuario para editar una tarea
     *
     * @param request El objeto HttpServletRequest que contiene la solicitud HTTP
     * @param response El objeto HttpServletResponse que se utilizará para enviar la respuesta HTTP
     * @throws ServletException Excepción que se lanza si hay un error en el servlet
     * @throws IOException Excepción que se lanza si hay un error de entrada o de salida
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Imprime un mensaje de depuración indicando que se está ingresando al método doGet
        System.out.println("Entrando en metodo doGet");

        // Obtiene el contexto del servlet
        ServletContext context = getServletContext();

        // Obtén los parámetros del formulario
        String id = request.getParameter("id");
        int idTarea = Integer.parseInt(id); // Convierte el ID de tarea en un entero
        String nuevoTitulo = request.getParameter("titulo");
        String nuevaDescripcion = request.getParameter("descripcion");
        String nuevaFecha = request.getParameter("fecha");

        // Imprime los valores obtenidos de los parámetros para depuración
        System.out.println(idTarea);
        System.out.println(nuevoTitulo);
        System.out.println(nuevaDescripcion);
        System.out.println(nuevaFecha);

        // Crea una instancia de la clase ListasEnlazadas
        ListasEnlazadas listaEnlazada = new ListasEnlazadas();

        try {
            // Carga la lista de tareas desde un archivo utilizando el contexto del servlet
            listaEnlazada = Archivos.leerArchivoTareas(context);
        } catch (ClassNotFoundException ex) {
            // Maneja una excepción en caso de error al leer el archivo
            Logger.getLogger(SvTareas.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Llama al método EditarTarea para editar la tarea en la lista
        listaEnlazada.EditarTarea(idTarea, nuevoTitulo, nuevaDescripcion, nuevaFecha);

        // Escribe la lista actualizada de tareas en un archivo
        Archivos.escribirArchivoTareas(listaEnlazada, context);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
