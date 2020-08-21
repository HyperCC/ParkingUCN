@extends('templates.body')

@section('title_head', 'Persona ' . $vehiculo->modelo )

@section('content_body')

    <div class="container">

        <div class="row my-4">

            <div class="col-12 col-lg-6 text-center my-3">

                <span class="display-3">Vehiculo
                    <h1>Patente: {{ $vehiculo->patente }} </h1></span>

                <img class="img-fluid my-3" src="{{ URL::to('/')}}/img/carshow.svg" alt="vehiculo encontrado">
            </div>

            <div class="col-12 col-lg-6">
                <div class="card shadow-sm">

                    <h3 class="card-header text-center font-weight-bold">{{ $vehiculo->modelo }}</h3>

                    <div class="card-body">

                        <div class="card p-3">

                            <div class="input-group my-3">
                                <div class="input-group-prepend">
                                    <p class="input-group-text">Patente</p>
                                </div>
                                <p class="form-control" aria-describedby="basic-addon3"> {{ $vehiculo->patente }} </p>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <p class="input-group-text">Marca</p>
                                </div>
                                <p class="form-control" aria-describedby="basic-addon3"> {{ $vehiculo->marca }} </p>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <p class="input-group-text">Modelo</p>
                                </div>
                                <p class="form-control" aria-describedby="basic-addon3"> {{ $vehiculo->modelo }} </p>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <p class="input-group-text">Año</p>
                                </div>
                                <p class="form-control" aria-describedby="basic-addon3"> {{ $vehiculo->anio }} </p>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <p class="input-group-text">Observaciones</p>
                                </div>
                                <p class="form-control"
                                   aria-describedby="basic-addon3"> {{ $vehiculo->observacion }} </p>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <p class="input-group-text">Rut Propietario</p>
                                </div>
                                <p class="form-control"
                                   aria-describedby="basic-addon3"> {{ $vehiculo->responsable }} </p>
                            </div>

                            <div class="py-3">
                                <a class="btn btn-lg btn-outline-primary btn-block rounded-pill mb-2 text-dark"
                                   href="{{ route('home') }}"> Todo Ok, volver al inicio .. </a>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

@endsection
