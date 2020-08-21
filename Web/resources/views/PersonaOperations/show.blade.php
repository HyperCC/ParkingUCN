@extends('templates.body')

@section('title_head', 'Persona ' . $persona->rut )

@section('content_body')

    <div class="container">

        <div class="row my-4">

            <div class="col-12 col-lg-6 text-center my-3">

                <span class="display-3">Persona
                    <h1>Rut: {{ $persona->rut }} </h1></span>

                <img class="img-fluid my-3" src="{{ URL::to('/')}}/img/thepersonas.svg" alt="persona encontrada">
            </div>

            <div class="col-12 col-lg-6">
                <div class="card shadow-sm">

                    <h3 class="card-header text-center font-weight-bold">{{ $persona->nombre  }}</h3>

                    <div class="card-body">

                        <div class="card p-3">

                            <div class="input-group my-3">
                                <div class="input-group-prepend">
                                    <p class="input-group-text">Nombre Completo</p>
                                </div>
                                <p class="form-control" aria-describedby="basic-addon3"> {{ $persona->nombre }} </p>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <p class="input-group-text">Rut</p>
                                </div>
                                <p class="form-control" aria-describedby="basic-addon3"> {{ $persona->rut }} </p>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <p class="input-group-text">Sexo</p>
                                </div>
                                <p class="form-control" aria-describedby="basic-addon3"> {{ $persona->sexo }} </p>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <p class="input-group-text">Cargo</p>
                                </div>
                                <p class="form-control" aria-describedby="basic-addon3"> {{ $persona->cargo }} </p>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <p class="input-group-text">Unidad</p>
                                </div>
                                <p class="form-control" aria-describedby="basic-addon3"> {{ $persona->unidad }} </p>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <p class="input-group-text">E-Mail</p>
                                </div>
                                <p class="form-control" aria-describedby="basic-addon3"> {{ $persona->email }} </p>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <p class="input-group-text">Telefono</p>
                                </div>
                                <p class="form-control" aria-describedby="basic-addon3"> {{ $persona->telefono }} </p>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <p class="input-group-text">Oficina</p>
                                </div>
                                <p class="form-control" aria-describedby="basic-addon3"> {{ $persona->oficina }} </p>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <p class="input-group-text">Dirección de Trabajo</p>
                                </div>
                                <p class="form-control"
                                   aria-describedby="basic-addon3"> {{ $persona->direccionTrabajo }} </p>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <p class="input-group-text">Dirección Local</p>
                                </div>
                                <p class="form-control"
                                   aria-describedby="basic-addon3"> {{ $persona->direccionCasa }} </p>
                            </div>

                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <p class="input-group-text">Comuna</p>
                                </div>
                                <p class="form-control" aria-describedby="basic-addon3"> {{ $persona->comuna }} </p>
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
