
<%@page import="com.mycompany.mundo.Archivos"%>
<%@page import="com.mycompany.mundo.Usuario"%>
<%@page import="com.mycompany.mundo.ListasEnlazadas"%>

<!-- Inclución de la plantilla de header -->
<%@include file= "templates/header.jsp" %>

<body class="img js-fullheight" style="background-image: url(images/back.jpg);">
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
                                <a class="nav-link active" aria-current="page" href="index.jsp"></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" style="margin-right: 30px;" href="#">Inicio</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Opciones
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">Opción 1</a></li>
                                    <li><a class="dropdown-item" href="#">Opción 2</a></li>
                                    <li><a class="dropdown-item" href="#">Opción 3</a></li>
                                </ul>
                            </li>
                        </ul>
                        <form class="d-flex" role="search" enctype="multipart/form-data">
                            <input class="form-control me-2" type="search" id="inputNombre" placeholder="Busqueda" aria-label="Search">
                            <button href="#" type="button" class="btn btn-outline-success" >Buscar</button>
                        </form>
                    </div>
                    <!-- Navbar-->
                    <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                <li>
                                <center>
                                    <img src="./images/iconoUsuario.png" alt="" width="150px" height="150px" style="display: block; margin: 0 auto;">
                                </center>
                        </li>
                        <li><a style="text-align: center;" class="dropdown-item heading-section"><%out.println(request.getAttribute("nombre"));%></a></li>
                        <li><input type="hidden" id="nombre" name="nombre" type="text" value="<%out.println(request.getAttribute("nombre"));%>"></li>
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
                    <h3 align="center" class="color-txt">Bienvenid@ al sistema de Gestión de Tareas</h3>
                    <h4 align="center"><%out.println(request.getAttribute("nombre"));%></h4>
                    <input type="hidden" id="nombre" name="nombre" type="text" value="<%out.println(request.getAttribute("nombre"));%>">
                </div>
            </div>
            <hr>

            <!-- Columna izquierda para el formulario -->
            <br><div class="col-lg-4 col-md-4"> <!-- Clase de división en cuatro columnas -->
                <div class="card card-body"> <!-- Tarjeta de trabajo -->
                    <h3>Agregar tarea</h3><br><hr><br> <!-- Titulo del formulario para agregar una tarea -->
                    <form action="SvTareas" method="POST">
                        <!-- Formulario que recibe todos los datos para agregar una tarea -->
                        <div class="col-auto">
                            <label class="visually-hidden">Id</label>
                            <div class="input-group">
                                <div class="input-group-text">Id</div>
                                <input id="id" name="id" type="text" class="form-control" required>
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
                        <input type="hidden" id="cedula" name="cedula" type="text" value="<%out.println(request.getAttribute("cedula"));%>">
                        <input type="hidden" id="nombre" name="nombre" type="text" value="<%out.println(request.getAttribute("nombre"));%>">
                        <!-- Botón de tipo submit que permite agregar una tarea -->
                        <br><br><button type="submit" class="btn btn-success"">Agregar tarea</button>
                        <br>
                    </form>
                </div> <!-- Cierre de la clase card card-body -->
            </div> <!-- Cierre de la clase col-lg-4 col-md-4 -->

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
                                ListasEnlazadas listaEnlazada = new ListasEnlazadas();
                                // Obtener el contexto del servlet
                                ServletContext context = getServletContext();

                                listaEnlazada = Archivos.leerArchivoTareas(context);
                                if (listaEnlazada == null) {
                                    listaEnlazada = new ListasEnlazadas();
                                }
                                out.println(listaEnlazada.MostrarLista());
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

    <!-- Inclución del css -->
    <link rel="stylesheet" href="css/css.css">

    <!-- Inclución de la plantilla de footer -->
    <%@include file= "templates/footer.jsp" %>