@extends('templates.body')

@section('title_head', 'Home')

@section('content_body')

    <div class="container">

        <div class="row">


            <div class="card-deck my-3 text-center">

                <div class="col-lg-3 col-md-6 col-sm-12">
                    <div class="card mb-3 box-shadow">
                        <div class="card-header bg-warning">
                            <h4 class="my-0 font-weight-normal text-light"> Registrar un Ingreso </h4>
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
                            <h4 class="my-0 font-weight-normal text-light"> Registrar una Salida </h4>
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
                            <p class="text-muted"> Crear un usuario y registrarlo junto a los demas usuarios autorizados</p>
                        </div>
                        <a href="#" class="btn mx-3 mb-3 btn-outline-success rounded-pill">
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
                            <p class="text-muted"> Crear un vehiculo validado el usuario como autorizado o registrado</p>
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
