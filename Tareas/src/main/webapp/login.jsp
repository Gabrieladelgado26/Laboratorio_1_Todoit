
<%@page import="com.mycompany.mundo.Archivos"%>
<%@page import="com.mycompany.mundo.Usuario"%>
<%@page import="com.mycompany.mundo.ListasEnlazadas"%>

<!-- Inclución de la plantilla de header -->
<%@include file= "templates/header.jsp" %>

<% String nombreUsuario = request.getParameter("usuarioNombre");%>
<%
    String validacion = request.getParameter("idVerificado");
    
    String inputId = request.getParameter("buscar");

    if (inputId == null){
        inputId = "";
    }
    
    if (validacion == null) {
        String nuevoParametro = "nada";
        response.sendRedirect("login.jsp?idVerificado=" + nuevoParametro + "&usuarioNombre=" + nombreUsuario + "&buscar=" + inputId);
    }
    
    // Crear una instancia de la clase ListasEnlazadas
    ListasEnlazadas listaEnlazada = new ListasEnlazadas();

    // Obtener el contexto del servlet
    ServletContext context = getServletContext();

    listaEnlazada = Archivos.leerArchivoTareas(context);

    // Verificamos si listaTareas no es null antes de llamar a verificar
    boolean verificar = listaEnlazada.verificar();

    if (listaEnlazada == null) {
        listaEnlazada = new ListasEnlazadas();
    }

    System.out.println(verificar);
%>


<body class="img js-fullheight" style="background-image: url(images/fondoL.jpeg);">
    <!-- Clase contenedora -->
    <div class="container p-4">
        <div class="row">
            <nav class="navbar navbar-expand-lg bg-body-tertiary" style="margin-bottom: 20px;">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">Gestión de tareas</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="login.jsp?usuarioNombre=<%out.print(nombreUsuario);%>"></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" style="margin-right: 30px;" href="login.jsp?usuarioNombre=<%out.print(nombreUsuario);%>">Inicio</a>
                            </li>
                        </ul>
                        <form action="SvBuscar" method="GET" class="d-flex">
                            <input type="text" name="usuarioNombre" class="form-control" value="<%=nombreUsuario%>" hidden>
                            <input class="form-control me-2" type="number" type="search" name="inputId" placeholder="ID de la tarea" aria-label="Search" max="999999999" min="0" oninput="this.value = this.value.slice(0, 9)" required>
                            <button class="btn btn-outline-primary" type="submit">Buscar</button>
                        </form>
                    </div>
                    <!-- Navbar-->
                    <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                <li>
                                <center>
                                    <img src="./images/fondoLog.jpeg" alt="" width="160px" height="150px" style="display: block; margin: 0 auto;">
                                </center>
                        </li>
                        <li><a style="text-align: center;" class="dropdown-item heading-section"><%out.println(request.getParameter("usuarioNombre"));%></a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a style="text-align: left;" class="dropdown-item left-align" href="index.jsp">Cerrar sesión</a></li>
                    </ul>
                    </li>
                    </ul>
                </div>
            </nav><!-- Cierre de etiqueta nav -->

            <!-- Mensaje de bienvenida -->
            <div class="card card-body" align-items: center;>
                <div>
                    <h3 align="center">Bienvenid@ al sistema de Gestión de Tareas</h3>
                    <h4 align="center"><%out.println(request.getParameter("usuarioNombre"));%></h4>
                </div>
            </div>
            <hr>

            <!-- Columna izquierda para el formulario -->
            <br><div class="col-lg-4 col-md-4"> <!-- Clase de división en cuatro columnas -->
                <div class="card card-body"> <!-- Tarjeta de trabajo -->
                    <h3>Agregar tarea</h3> <!-- Titulo del formulario para agregar una tarea -->
                    <!-- Formulario que recibe todos los datos para agregar una tarea -->
                    <form action="SvTareas" method="POST">
                        <input type="hidden" name="usuarioNombre" type="text" value="<%= nombreUsuario%>">
                        <div class="col-auto">
                            <label class="visually-hidden">Id</label>
                            <div class="input-group">
                                <div class="input-group-text">Id</div>
                                <input id="id" name="id" type="text" class="form-control" required pattern="[0-9]+" title="Por favor, ingrese solo números">
                            </div>
                        </div>

                        <br><div class="col-auto">
                            <label class="visually-hidden">Título</label>
                            <div class="input-group">
                                <div class="input-group-text">Título</div>
                                <input id="titulo" name="titulo" type="text" class="form-control" required>
                            </div>
                        </div>

                        <br><div class="col-auto">
                            <label class="visually-hidden">Descripción</label>
                            <div class="input-group">
                                <div class="input-group-text">Descripción</div>
                                <textarea id="descripcion" name="descripcion" type="text" class="form-control" required></textarea>
                            </div>
                        </div>

                        <br><div class="col-auto">
                            <label class="visually-hidden">Fecha</label>
                            <div class="input-group">
                                <div class="input-group-text">Fecha</div>
                                <input id="fecha" name="fecha" type="date" class="form-control" required>
                            </div>
                        </div>

                        <%
                            //Condicional if para saber si existen tareas en el archivo
                            if (verificar == true) {
                        %>
                        <!-- En caso que si existan tareas (Muestra boton que pregunta donde agregar) -->
                        <br><center><button id="agregarTareaBtn" type="button" class="btn btn-primary">Agregar tarea</button></center>
                        <%
                        } else if (verificar == false) {
                        %>
                        <!-- En caso de que no existan tareas muestra el boton para agregar la tarea directamente -->
                        <br><center><button type="submit" class="btn btn-primary">Agregar tarea</button></center>
                        <%
                            }
                        %>
                        <div id="radioButtonsContainer" style="display: none;">
                            <!-- Radio buttons para elegir la posición de la tarea -->

                            <div class="form-check" style="margin-bottom: 10px">
                                <input type="radio" name="posicion" id="inicio" value="inicio" class="form-check-input" checked>
                                <label for="inicio" class="form-check-label">Al inicio</label>
                            </div>

                            <div class="form-check d-flex" style="max-width: 500px;">
                                <input type="radio" name="posicion" id="antes" value="antes" class="form-check-input" >
                                <label for="antes" class="form-check-label" style="margin-right: 42px; white-space: nowrap;"> Anterior a una tarea</label>

                                <!-- Input para ID Anterior (deshabilitado inicialmente) -->
                                <div class="input-group" style="max-width: 200px;">
                                    <input id="idAnterior" name="idAnterior" type="number" class="form-control" disabled placeholder="ID" max="999" min="0" oninput="this.value = this.value.slice(0, 3)" required>
                                </div>
                            </div>

                            <div class="form-check d-flex" style="max-width: 500px;">
                                <input type="radio" name="posicion" id="despues" value="despues" class="form-check-input" >
                                <label for="despues" class="form-check-label" style="margin-right: 30px; white-space: nowrap;"> Después de una tarea</label>

                                <!-- Input para ID Siguiente (deshabilitado inicialmente) -->
                                <div class="input-group" style="max-width: 200px;">
                                    <input id="idSiguiente" name="idSiguiente" type="number" class="form-control" disabled placeholder="ID" max="999" min="0" oninput="this.value = this.value.slice(0, 3)" required>
                                </div>
                            </div>

                            <div class="form-check">
                                <input type="radio" name="posicion" id="final" value="final" class="form-check-input" >
                                <label for="final" class="form-check-label">Al final</label>
                            </div>
                            <center><button id="agregarTareaFinalBtn" type="submit" class="btn btn-primary" style="display: none; margin-top: 15px;">Agregar Tarea</button></center>
                        </div>
                    </form> <!-- Cierre de la etiqueta form -->
                </div> <!-- Cierre de la clase card card-body -->
            </div> <!-- Cierre de la clase col-lg-4 col-md-4 -->

            <!---------------------------------------- Verificación de ID -------------------------------------------->
            <%
                String idVerificado = request.getParameter("idVerificado");
                if (idVerificado != null && idVerificado.equals("false")) {
            %>
            <!-- Método que permite mostrar el modal que informa que ya existe el ID en la lista -->
            <script>
                $(document).ready(function () {
                    idExistente();
                });
            </script>
            <%
                }
                if (idVerificado != null && idVerificado.equals("error")) {
            %>
            <!-- Método que permite mostrar el modal que informa que el ID no es valido -->
            <script>
                $(document).ready(function () {
                    idNoValido();
                });
            </script>
            <%
                }
            %>

            <!-- Columna del lado derecho para la tabla de datos -->
            <div class="col-lg-8 col-md-8">
                <div class="card card-body"> <!-- Tarjeta de trabajo -->
                    <table class="table table-striped table-hover"> <!-- Estilo de la tabla de datos -->
                        <thead>
                            <tr>
                                <!-- Titulos de la tabla de datos -->
                                <th>Id</th>
                                <th>Título</th>
                                <th>Descripción</th>
                                <th>Fecha</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>  
                            <%
                                String tablaTareas = "";

                                if (inputId != null && !inputId.isEmpty()) {
                                    // Mustra la tabla con la tarea del ID buscado
                                    tablaTareas = listaEnlazada.tablaBusqueda(inputId);
                                } else {
                                    // Muestra la tabla de tareas completa
                                    tablaTareas = listaEnlazada.MostrarLista();
                                }
                                out.println(tablaTareas);  // Imprime la tabla de tareas
                            %>
                        </tbody>
                    </table> <!-- Cierre de la etiqueta table-->
                </div> <!-- Cierre de la clase card card-body -->
            </div> <!-- Cierre de la clase col-lg-8 col-md-8 -->
        </div>
    </div>           

    <!-- Modal de confirmación para eliminar tarea -->
    <div class="modal fade" id="eliminar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="eliminarLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body">
                    <h4 align="center">¿Estás seguro de que deseas eliminar esta tarea?</h4>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal" style="margin-right: 10px;">Cancelar</button>
                    <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModalConfirm" onclick="eliminarTarea(1)">Eliminar</button>
                </div>
            </div>
        </div>
    </div>

    <!------------------------------------------ Modales para editar las caracteristicas ---------------------------------------------->

    <!-- Modal principal para editar las caracteristicas de una tarea -->
    <div class="modal fade" id="editModalConfirm" tabindex="-1" aria-labelledby="editModalLabelConfirm" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabelConfirm">Editar información de la tarea</h5>
                </div>
                <div class="modal-body">
                    <div id="editModalConfirm" style="display: flex; justify-content: center;">
                        <button href="#" type="button" class="btn btn-success" data-bs-toggle="modal" style="margin-right: 15px;" data-bs-target="#editTitulo" >Editar título</button>
                        <button href="#" type="button" class="btn btn-success" data-bs-toggle="modal" style="margin-right: 15px;" data-bs-target="#editDescripcion" >Editar descripción</button>
                        <button href="#" type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#editFecha" >Editar Fecha</button>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="margin-right: 10px;">Cancelar</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal para editar el titulo la tarea -->
    <div class="modal fade" id="editTitulo" tabindex="-1" role="dialog" aria-labelledby="editTituloLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <form id="miFormularioTitulo">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editTituloLabel">Editar titulo</></h5>
                    </div>
                    <div class="modal-body">
                        <div id="tarea-detalles" style="display: flex; justify-content: center;">
                            <label class="visually-hidden" for="descripcion">Titulo</label>
                            <div class="input-group">
                                <div class="input-group-text">Ingresa el nuevo título:</div>
                                <input type="text" class="form-control" id="nuevoTitulo" name="titulo" required>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editModalConfirm" style="margin-right: 10px;">Cancelar</button>
                        <button type="submit" class="btn btn-danger" onclick="editarCaracteristicas('nuevoTitulo')">Actualizar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Modal para editar la descripción de la tarea -->
    <div class="modal fade" id="editDescripcion" tabindex="-1" aria-labelledby="editDescripcionLabelConfirm" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <form id="miFormularioDescripcion">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editDescripcionLabelConfirm">Editar descripción</span></h5>
                    </div>
                    <div class="modal-body">
                        <div id="tarea-detalles" style="display: flex; justify-content: center;">
                            <label class="visually-hidden" for="descripcion">Descripción</label>
                            <div class="input-group">
                                <div class="input-group-text">Ingresa la nueva descripción:</div>
                                <textarea type="text" class="form-control" id="nuevaDescripcion" name="descripcion" required></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editModalConfirm" style="margin-right: 10px;">Cancelar</button>
                        <button type="submit" class="btn btn-danger" onclick="editarCaracteristicas('miFormularioDescripcion')">Actualizar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Modal para editar la fecha de la tarea -->
    <div class="modal fade" id="editFecha" tabindex="-1" aria-labelledby="editFechaLabelConfirm" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <form id="miFormularioFecha" >
                    <div class="modal-header">
                        <h5 class="modal-title" id="editFechaLabelConfirm">Editar foto</span></h5>
                    </div>
                    <div class="modal-body">
                        <div id="tarea-detalles" style="display: flex; justify-content: center;">
                            <label class="visually-hidden" for="fecha">Fecha</label>
                            <div class="input-group">
                                <div class="input-group-text">Ingresa la nueva fecha:</div>
                                <input type="date" class="form-control" id="nuevaFecha" name="nuevaFecha" required>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editModalConfirm" style="margin-right: 10px;">Cancelar</button>
                        <button type="submit" class="btn btn-danger" onclick="editarCaracteristicas('miFormularioFecha')">Actualizar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Modal que se muestra en caso de que el ID de la tarea ya este en el sistema -->
    <div class="modal fade" id="modalError" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="modalErrorLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body text-center align-middle">
                    <h2>Datos no validos</h2>
                    <p>El ID que intenta ingresar ya se encuentra registrado, por favor verifique la información</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="modalErrorReferencia" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="modalErrorReferenciaLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body text-center align-middle">
                    <h2>Datos no validos</h2>
                    <p>El ID de referencia no es valido, por favor verifique la información</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Inclución de las funciones que se encuentran en el script -->
    <script>
        <%@include file= "scripts/script.js" %>
    </script>  

    <!-- Inclución del css -->
    <link rel="stylesheet" href="css/css.css">

    <!-- Inclución de la plantilla de footer -->
    <%@include file= "templates/footer.jsp" %>