<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.mundo.Usuario"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://kit.fontawesome.com/424ce1386e.js" crossorigin="anonymous"></script>
        <script src="scripts/script.js" type="text/javascript"></script>   
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Gestión de tareas</title>
    </head>
    <body>
        <!-- Clase contenedora -->
        <div class="container p-4">
            <div class="row">
                <img src="./images/front.jpg" alt=""> <!-- Imagen en la parte superior de la página web -->
                <!-- Navbar -->
                <nav class="navbar navbar-expand-lg bg-body-tertiary" style="margin-bottom: 20px;">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#"></a>
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
                    </div>
                </nav><!-- Cierre de etiqueta nav -->

                <!-- Mensaje de bienvenida con el nombre del usuario que inicio sesión -->
                <div class="align text-center">
                    <h3>Bienvenid@ <%out.println(request.getAttribute("nombre"));%></h3>
                </div><br>

                <!-- Columna izquierda para el formulario -->
                <br><div class="col-lg-4 col-md-4"> <!-- Clase de división en cuatro columnas -->
                    <div class="card card-body"> <!-- Tarjeta de trabajo -->
                        <h3>Agregar tarea</h3><br> <!-- Titulo del formulario para agregar una tarea -->

                        <!-- Formulario que recibe todos los datos para agregar una tarea -->
                        <div class="col-auto">
                            <label class="visually-hidden">Id</label>
                            <div class="input-group">
                                <div class="input-group-text">Id</div>
                                <input type="text" class="form-control" required>
                            </div>
                        </div>

                        <br><div class="col-auto">
                            <label class="visually-hidden">Título</label>
                            <div class="input-group">
                                <div class="input-group-text">Título</div>
                                <input type="text" class="form-control" required>
                            </div>
                        </div>

                        <br><div class="col-auto">
                            <label class="visually-hidden">Descripción</label>
                            <div class="input-group">
                                <div class="input-group-text">Descripción</div>
                                <textarea type="text" class="form-control" required></textarea>
                            </div>
                        </div>

                        <br><div class="col-auto">
                            <label class="visually-hidden">Fecha</label>
                            <div class="input-group">
                                <div class="input-group-text">Fecha</div>
                                <input type="date" class="form-control" required>
                            </div>
                        </div>
                        <!-- Botón de tipo submit que permite agregar una tarea -->
                        <br><button type="submit" class="btn btn-success"">Agregar tarea</button>
                        <br>
                    </div> <!-- Cierre de la clase card card-body -->
                </div> <!-- Cierre de la clase col-lg-4 col-md-4 -->

                <!-- Columna del lado derecho para la tabla de datos -->
                <div class="col-lg-8 col-md-8">
                    <div class="card card-body"> <!-- Tarjeta de trabajo -->
                        <table class="table table-dark table-striped"> <!-- Estilo de la tabla de datos -->
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
                                <tr>
                                    <!-- Muestra los datos ingresados en la tabla -->
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <!-- Iconos de acciones -->
                                    <td>
                                        <a href="#" class="btn btn-outline-success">
                                            <i class="fas fa-pencil-alt"></i> 
                                        </a>
                                        <a href="#" class="btn btn-outline-danger">
                                            <i class="fas fa-trash"></i> 
                                        </a>
                                    </td>          
                                </tr>
                            </tbody>
                        </table> <!-- Cierre de la etiqueta table-->
                    </div> <!-- Cierre de la clase card card-body -->
                    <br><div class="align text-center">
                        <a href="index.jsp" class="btn btn-outline-success"> Cerrar sesion</a>
                    </div>
                </div> <!-- Cierre de la clase col-lg-8 col-md-8 -->
            </div> <!-- Cierre de la clase row -->
        </div> <!-- Cierre de la clase container p-4 -->

        <!-- Inclución de la plantilla de footer -->
        <%@include file= "templates/footer.jsp" %>