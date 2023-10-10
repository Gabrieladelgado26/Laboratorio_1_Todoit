	
<!-- Inclución de la plantilla de header -->
<%@include file= "templates/header.jsp" %>

<body class="img js-fullheight" style="background-image: url(images/fondo.jpeg);">
    <section class="ftco-section d-flex align-items-center"> <!-- Centra verticalmente -->
        <div class="container">
            <div class="row justify-content-center">
                <!-- Contenido centralizado -->
                <div class="col-md-9 col-lg-7">
                    <div class="login-wrap p-4" style="height: 400px;"> 
                        <div class="content-box text-center">
                            <div class="text-center">
                                <h2 class="heading-section">Sistema de Gestión</h2>
                                <h2 class="heading-section">de Tareas</h2>
                            </div><br>
                            <form action="SvLogin" method="POST" class="signin-form">
                                <div class="form-group">
                                    <input id="cedula" name="cedula" type="text" class="form-control space-narrow" placeholder="Número de cédula" required>
                                </div>
                                <div class="form-group">
                                    <input id="contrasenia" name="contrasenia" type="password" class="form-control space-narrow" placeholder="Contraseña" required>
                                </div>
                                <br><div class="form-group text-center">
                                    <button type="submit" class="btn btn-primary px-3 space-btn-narrow">Ingresar</button>
                                </div>
                                <br><p class="w-100 text-center">&mdash; ¿Aún no tienes una cuenta? &mdash;</p>
                                <div class="social d-flex justify-content-center">
                                    <a href="#" class="py-2 ml-md-1 rounded btn-narrow" data-bs-toggle="modal" data-bs-target="#registrarUsuario">Registrate aquí</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!---------------------------------------- Verificación de usuario -------------------------------------------->
    <%
        // Llama al atributo existente
        String existente = (String) request.getAttribute("existente");

        // Evalua si la variable existente es igual a "verdadero"
        if (existente != null && existente.equals("verdadero")) {
    %>
    <script>
        <!-- Si la condición se cumple llama a la función usuarioVerificado() para mostrar el modal correspondiente -->
        $(document).ready(function () {
            usuarioVerificado();
        });
    </script>
    <%    }
        if (existente != null && existente.equals("falso")) {
    %>
    <script>
        <!-- Si la condición se cumple llama a la función cedulaExistente() para mostrar el modal correspondiente -->
        $(document).ready(function () {
            cedulaExistente();
        });
    </script>
    <%
        }
        String noExistente = request.getParameter("noExistente");

        // Evalua si la variable noExistente es igual a "false"
        if (noExistente != null && noExistente.equals("false")) {
    %>
    <script>
        <!-- Si la condición se cumple llama a la función usuarioSinVerificar() para mostrar el modal correspondiente -->
        $(document).ready(function () {
            usuarioSinVerificar();
        });
    </script>
    <%
        }
        // Limpiar los atributos de la solicitud
        request.removeAttribute("existente");
        request.removeAttribute("noExistente");
    %>

    <!-------------------------------------------- VENTANAS MODALES ------------------------------------------------->

    <!-- Modal que se muestra si el usario desea registrarse -->

    <div class="modal fade" id="registrarUsuario" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <!-- form que conecta con el servlet llamado SvUsuario por el metodo POST -->
        <form action="SvUsuario" method="POST">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="registrarUsuarioLabel">Regístrate aquí</h1>
                    </div>
                    <div class="modal-body" style="z-index: 1050;">

                        <div class="form-outline mb-4">
                            <input classtype="text" id="cedula" name="cedula" class="form-control" style="background-color: rgba(128, 128, 128, 0.3); color: black;" />
                            <label class="form-label" for="cedula">Número de cédula</label>
                        </div>

                        <div class="form-outline mb-4">
                            <input type="text" id="nombre" name="nombre" class="form-control" style="background-color: rgba(128, 128, 128, 0.3);" />
                            <label class="form-label" for="nombre">Nombre</label>
                        </div>

                        <div class="form-outline mb-4";">
                            <input type="password" id="contrasenia" name="contrasenia" class="form-control" style="background-color: rgba(128, 128, 128, 0.3);"/>
                            <label class="form-label" for="contrasenia">Contraseña</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        <button type="submit" class="btn btn-primary">Registrar Usuario</button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <!-- Modal para informar al usuario que los datos se registraron correctamente-->
    <div class="modal fade" id="usuarioRegistrado" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="registradoLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body text-center align-middle">
                    <h4 align="center">Usuario registrado correctamente</h4>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal que se muestra en caso de que los datos para iniciar sesión no sean validos -->
    <div class="modal fade" id="usuarioNoRegistrado" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="usuarioNoRegistradoLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body text-center align-middle">
                    <h2>Los datos ingresados no son correctos</h2>
                    <p>Por favor verifique la información</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Modal que se muestra en caso de que la cédula que el usuario intente ingresar ya este en el sistema -->
    <div class="modal fade" id="mensajeAlerta" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="mensajeAlertaLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body text-center align-middle">
                    <h2>Datos no validos</h2>
                    <p>La cédula que intenta ingresar ya esta registrada, por favor verifique la información</p>
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

    <!-- Inclución de la plantilla de footer -->
    <%@include file= "templates/footer.jsp" %>