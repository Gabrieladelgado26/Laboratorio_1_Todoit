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
 * @author Karoll Gabriela Delgado - Leidy Tatiana Cuasquer
 */
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
        
        // Obtener el contexto del servlet
        ServletContext context = getServletContext();

        // Obtener el nombre de usuario desde los parámetros de la solicitud
        String nombreUsuario = request.getParameter("usuarioNombre");

        System.out.println("Corriendo método de eliminar"); // Imprime un mensaje de depuración

        try {
            listaEnlazada = Archivos.leerArchivoTareas(context);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvTareas.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Obtener el ID de la tarea a eliminar desde los parámetros de la solicitud
        String idEliminar = request.getParameter("id");

        System.out.println("Valor de idEliminar: " + idEliminar);

        if (idEliminar != null && !idEliminar.isEmpty()) {
            int eliminar = Integer.parseInt(idEliminar);
            listaEnlazada.eliminarTarea(eliminar);
            Archivos.escribirArchivoTareas(listaEnlazada, context);
        }

        String idVerificado = "true";

        // Redireccionar a la página de destino (login.jsp)
        response.sendRedirect("login.jsp?usuarioNombre=" + nombreUsuario + "&idVerificado=" + idVerificado);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileNotFoundException {
        String nombreUsuario = request.getParameter("usuarioNombre");

        // Obtener el contexto del servlet
        ServletContext context = getServletContext();

        try {
            listaEnlazada = Archivos.leerArchivoTareas(context);
            if (listaEnlazada == null) {
                listaEnlazada = new ListasEnlazadas();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SvLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Obtener los datos del formulario enviados por POST
        String posicion = request.getParameter("posicion");
        String id = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String fecha = request.getParameter("fecha");
        String idVerificado = "";

        // Verificar si el ID ya existe en la lista
        if (!listaEnlazada.idExistente(Integer.parseInt(id))) {
            // Si el ID no existe, agrega la tarea a la lista
            if (posicion == null) {
                posicion = "inicio";
            }
            if ("inicio".equals(posicion)) {
                listaEnlazada.agregarNodoInicio(Integer.parseInt(id), titulo, descripcion, fecha);
                idVerificado = "true";
                Archivos.escribirArchivoTareas(listaEnlazada, context);
            } else if ("antes".equals(posicion)) {
                String idAnterior = request.getParameter("idAnterior");

                // Verificar si el ID de referencia existe antes de agregar la tarea
                if (!listaEnlazada.idExistente(Integer.parseInt(idAnterior))) {
                    // Maneja el caso en el que el ID de referencia no existe
                    idVerificado = "error";
                } else {
                    // Verificar si idAnterior no es nulo ni está vacío antes de analizarlo como un entero
                    if (idAnterior != null && !idAnterior.isEmpty()) {
                        listaEnlazada.agregarNodoAntes(Integer.parseInt(id), Integer.parseInt(idAnterior), titulo, descripcion, fecha);
                        idVerificado = "true";
                        Archivos.escribirArchivoTareas(listaEnlazada, context);
                    } else {
                        // Manejar el caso en el que idAnterior es nulo o está vacío
                        idVerificado = "error";
                    }
                }
            } else if ("despues".equals(posicion)) {
                String idSiguiente = request.getParameter("idSiguiente");

                // Verificar si el ID de referencia existe antes de agregar la tarea
                if (!listaEnlazada.idExistente(Integer.parseInt(idSiguiente))) {
                    // Maneja el caso en que el ID de referencia no existe
                    idVerificado = "error";
                } else {
                    // Verificar si idSiguiente no es nulo ni está vacío antes de analizarlo como un entero
                    if (idSiguiente != null && !idSiguiente.isEmpty()) {
                        listaEnlazada.agregarNodoDespues(Integer.parseInt(id), Integer.parseInt(idSiguiente), titulo, descripcion, fecha);
                        idVerificado = "true";
                        Archivos.escribirArchivoTareas(listaEnlazada, context);
                    } else {
                        // Manejar el caso en el que idAnterior es nulo o está vacío
                        idVerificado = "error";
                    }
                }
            } else if ("final".equals(posicion)) {
                listaEnlazada.agregarNodoFinal(Integer.parseInt(id), titulo, descripcion, fecha);
                idVerificado = "true";
                Archivos.escribirArchivoTareas(listaEnlazada, context);
            }
        } else {
            idVerificado = "false";
        }

        // Redireccionar a la página de destino (login.jsp)
        response.sendRedirect("login.jsp?usuarioNombre=" + nombreUsuario + "&idVerificado=" + idVerificado);
    }
}
