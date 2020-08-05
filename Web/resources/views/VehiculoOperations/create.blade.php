@extends('templates.body')

@section('title_head', 'Crear Vehiculo')

@section('content_body')

    <div class="container">

        <div class="row">

            <div class="col-12 col-sm-10 col-lg-6 mx-auto my-3">

                <form class="bg-white py-3 px-4 shadow rounded" method="POST" action="#">

                    @csrf

                    <div class="text-center">
                        <span class="display-3">Crear nuevo Vehiculo</span>
                    </div>

                    <hr>

                    <div class="form-group">
                        <label for="patente"> Patente </label>
                        <input class="form-control shadow-sm bg-light" name="patente" type="text">
                    </div>

                    <div class="form-group">
                        <label for="marca"> Marca </label>
                        <input class="form-control shadow-sm bg-light" name="marca" type="text">
                    </div>

                    <div class="form-group">
                        <label for="anio"> Año Manufactura </label>
                        <input class="form-control shadow-sm bg-light" name="anio" type="text">
                    </div>

                    <div class="form-group">
                        <label for="observacion"> Observación </label>
                        <input class="form-control shadow-sm bg-light" name="observacion" type="text">
                    </div>

                    <div class="form-group">
                        <label for="propietario"> Nombre del Propietario </label>
                        <input class="form-control shadow-sm bg-light" name="propietario" type="text">
                    </div>

                    <hr>

                    <div class="py-3">
                        <button type="submit" class="btn btn-primary btn-lg btn-block rounded-pill"> Crear Persona
                        </button>
                        <a class="btn btn-lg btn-block btn-outline-dark rounded-pill" href="{{route('home')}}">
                            Cancelar
                        </a>
                    </div>

                </form>

            </div>
        </div>
    </div>

@endsection
