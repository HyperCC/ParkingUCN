@extends('templates.body')

@section('title_head', 'Home')

@section('content_body')

    <div class="container">

        <div class="row my-3">

            <div class="col-12 col-lg-6">
                <img class="img-fluid m-lg-3 m-sm-3" src="img/parkinghome.png" alt="parking ucn">
            </div>

            <div class="col-12 col-lg-6">

                <div class="card-deck my-3 text-center">

                    <!-- TITULO Y BIENVENIDA AL USUARIO -->

                    <div class="card my-4 box-shadow">
                        <div class="card-header bg-dark py-4">
                            <h3 class="my-0 font-weight-normal text-light"> Bienvenido Señor Guardia </h3>
                        </div>
                        <div class="card-body">
                            <h4 class="card-title pircing-card-title py-4"> ¡Desde este panel de administración podrás
                                gestionar todas tus operaciones para el parking en la UCN!
                            </h4>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div class="row">

            <div class="card-deck my-3 text-center">

                <div class="col-lg-3 col-md-6 col-sm-12">
                    <div class="card mb-3 box-shadow">
                        <div class="card-header bg-warning">
                            <h4 class="my-0 font-weight-normal text-dark"> Registrar un Ingreso </h4>
                        </div>
                        <div class="card-body">
                            <p class="text-muted"> Crear nuevo registro de ingreso vehicular a la universidad</p>
                        </div>
                        <a href="#" class="btn mx-3 mb-3 btn-outline-warning rounded-pill text-dark">
                            ¡A Registrar!
                        </a>
                    </div>
                </div>

                <div class="col-lg-3 col-md-6 col-sm-12">
                    <div class="card mb-3 box-shadow">
                        <div class="card-header bg-warning">
                            <h4 class="my-0 font-weight-normal text-dark"> Registrar una Salida </h4>
                        </div>
                        <div class="card-body">
                            <p class="text-muted"> Crear nuevo registro sobre la salida de un vehiculo del recinto </p>
                        </div>
                        <a href="#" class="btn mx-3 mb-3 btn-outline-warning rounded-pill text-dark">
                            ¡A Registrar!
                        </a>
                    </div>
                </div>

                <div class="col-lg-3 col-md-6 col-sm-12">
                    <div class="card mb-3 box-shadow">
                        <div class="card-header bg-success">
                            <h4 class="my-0 font-weight-normal text-light"> Guardar nuevo Usuario </h4>
                        </div>
                        <div class="card-body">
                            <p class="text-muted"> Crear un usuario y registrarlo junto a los demas usuarios
                                autorizados</p>
                        </div>
                        <a href="{{ route('create.persona') }}" class="btn mx-3 mb-3 btn-outline-success rounded-pill">
                            ¡A Inscribir!
                        </a>
                    </div>
                </div>

                <div class="col-lg-3 col-md-6 col-sm-12">
                    <div class="card mb-3 box-shadow">
                        <div class="card-header bg-success">
                            <h4 class="my-0 font-weight-normal text-light"> Guardar nuevo Vehiculo </h4>
                        </div>
                        <div class="card-body">
                            <p class="text-muted"> Crear un vehiculo validado el usuario como autorizado o
                                registrado</p>
                        </div>
                        <a href="#" class="btn mx-3 mb-3 btn-outline-success rounded-pill">
                            ¡A Inscribir!
                        </a>
                    </div>
                </div>

            </div>

        </div>
    </div>

@endsection
