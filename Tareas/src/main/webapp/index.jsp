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
                            <form action="#" class="signin-form">
                                <div class="form-group">
                                    <input type="text" class="form-control space-narrow" placeholder="Número de cédula" required>
                                </div>
                                <div class="form-group">
                                    <input id="password-field" type="password" class="form-control space-narrow" placeholder="Contraseña" required>
                                    <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password "></span>
                                </div>
                                <br><div class="form-group text-center">
                                    <button type="submit" class="btn btn-primary px-3 space-btn-narrow">Ingresar</button>
                                </div>
                            </form>
                            <br><p class="w-100 text-center">&mdash; No tienes una cuenta? &mdash;</p>
                            <div class="social d-flex justify-content-center">
                                <a href="#" class="py-2 ml-md-1 rounded btn-narrow">Registrate aquí</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </section>

    <!-- Inclución de la plantilla de footer -->
    <%@include file= "templates/footer.jsp" %>