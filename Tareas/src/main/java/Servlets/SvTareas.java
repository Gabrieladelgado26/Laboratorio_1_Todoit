package Servlets;

import com.mycompany.mundo.Archivos;
import com.mycompany.mundo.ListasEnlazadas;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvTareas", urlPatterns = {"/SvTareas"})
public class SvTareas extends HttpServlet {

    ListasEnlazadas listaEnlazada = new ListasEnlazadas();

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
            out.println("<title>Servlet SvTareas</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvTareas at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        // Obtiene el contexto del servlet
        ServletContext context = getServletContext();
        
        String idTarea = request.getParameter("id"); // Obtener el ID de la tarea a eliminar

        if (listaEnlazada.EliminarNodo(Integer.parseInt(idTarea))) {
            Archivos.escribirArchivoTareas(listaEnlazada, context); // Guardar los cambios
            response.setStatus(HttpServletResponse.SC_OK); // Tarea eliminada con éxito
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // No se encontró la tarea
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileNotFoundException {

        // Obtiene la sesión actual del usuario
        HttpSession session = request.getSession();

        // Obtiene el contexto del servlet
        ServletContext context = getServletContext();

        try {
            listaEnlazada = Archivos.leerArchivoTareas(context);
            if (listaEnlazada == null) {
                listaEnlazada = new ListasEnlazadas();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvTareas.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Obtiene los datos del formulario enviados por POST
        String id = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String fecha = request.getParameter("fecha");
        String cedula = request.getParameter("cedula");

        listaEnlazada.AgregarNodo(Integer.parseInt(id), titulo, descripcion, fecha, cedula);

        Archivos.escribirArchivoTareas(listaEnlazada, context);

        request.setAttribute("cedula", cedula);
        request.setAttribute("nombre", request.getParameter("nombre"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtiene el contexto del servlet
        ServletContext context = getServletContext();
        
        String idTarea = request.getParameter("id"); // Obtener el ID de la tarea a eliminar

        if (listaEnlazada.EliminarNodo(Integer.parseInt(idTarea))) {
            Archivos.escribirArchivoTareas(listaEnlazada, context); // Guardar los cambios
            response.setStatus(HttpServletResponse.SC_OK); // Tarea eliminada con éxito
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // No se encontró la tarea
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtiene el contexto del servlet
        ServletContext context = getServletContext();
        
        String idTarea = request.getParameter("id"); // Obtener el ID de la tarea a editar
        String nuevoTitulo = request.getParameter("titulo"); // Obtener el nuevo título
        String nuevaDescripcion = request.getParameter("descripcion"); // Obtener la nueva descripción
        String nuevaFecha = request.getParameter("fecha"); // Obtener la nueva fecha

        if (listaEnlazada.EditarTarea(Integer.parseInt(idTarea), nuevoTitulo, nuevaDescripcion, nuevaFecha)) {
            Archivos.escribirArchivoTareas(listaEnlazada, context); // Guardar los cambios
            response.setStatus(HttpServletResponse.SC_OK); // Tarea editada con éxito
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // No se encontró la tarea
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

}
