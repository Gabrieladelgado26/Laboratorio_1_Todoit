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
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvTareas", urlPatterns = {"/SvTareas"})
public class SvTareas extends HttpServlet {

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

    ListasEnlazadas listaEnlazada = new ListasEnlazadas();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Obtener el contexto del servlet
        ServletContext context = getServletContext();

        System.out.println("Corriendo metodo de eliminar");

        try {
            listaEnlazada = Archivos.leerArchivoTareas(context);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvTareas.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Obtiene el titulo de la tarea a eliminar desde los parámetros de la solicitud
        String idEliminar = request.getParameter("id");
        
        System.out.println("Valor de idEliminar: " + idEliminar);
        
        if (idEliminar.isEmpty()){
            System.out.println("Esta vacio");
        }
        
        if (idEliminar != null && !idEliminar.isEmpty()) {
            int eliminar = Integer.parseInt(idEliminar);
            listaEnlazada.eliminarTarea(eliminar);
            Archivos.escribirArchivoTareas(listaEnlazada, context);
        }
 
        // Redireccionar a la página de destino
        response.sendRedirect("login.jsp");
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

        // Redireccionar a la página de destino
        response.sendRedirect("login.jsp");
    }
}
