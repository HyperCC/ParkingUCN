@extends('templates.body')

@section('title_head', 'Crear Persona')

@section('content_body')

    <div class="container">

        <div class="row">

            <div class="col-12 col-sm-10 col-lg-6 mx-auto my-3">

                @extends('templates.validateerrors')

                <form class="bg-white py-3 px-4 shadow rounded" method="POST" action="{{ route('persona.store') }}">

                    @csrf

                    <div class="text-center">
                        <span class="display-3">Crear nueva Persona</span>
                    </div>

                    <hr>

                    <div class="form-group">
                        <label for="name"> Nombre </label>
                        <input class="form-control shadow-sm bg-light" name="name" type="text">
                    </div>

                    <div class="form-group">
                        <label for="rut"> Rut </label>
                        <input class="form-control shadow-sm bg-light" name="rut" type="text">
                    </div>

                    <div class="form-group">
                        <label for="sexo"> Sexo </label>
                        <select class="form-control shadow-sm custom-select" name="sexo">
                            <option value="VAR">Masculino</option>
                            <option value="MUJ">Femenino</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="cargo"> Cargo </label>
                        <input class="form-control shadow-sm bg-light" name="cargo" type="text">
                    </div>

                    <div class="form-group">
                        <label for="unidad"> Unidad </label>
                        <input class="form-control shadow-sm bg-light" name="unidad" type="text">
                    </div>

                    <div class="form-group">
                        <label for="email"> E-mail </label>
                        <input class="form-control shadow-sm bg-light" name="email" type="email">
                    </div>

                    <div class="form-group">
                        <label for="telefono"> Telefono </label>
                        <input class="form-control shadow-sm bg-light" name="telefono" type="text">
                    </div>

                    <div class="form-group">
                        <label for="oficina"> Oficina </label>
                        <input class="form-control shadow-sm bg-light" name="oficina" type="text">
                    </div>

                    <div class="form-group">
                        <label for="direccion_trabajo"> Direccion de Trabajo </label>
                        <input class="form-control shadow-sm bg-light" name="direccion_trabajo" type="text">
                    </div>

                    <div class="form-group">
                        <label for="direccion_casa"> Direccion de Casa </label>
                        <input class="form-control shadow-sm bg-light" name="direccion_casa" type="text">
                    </div>

                    <div class="form-group">
                        <label for="comuna"> Comuna </label>
                        <input class="form-control shadow-sm bg-light" name="comuna" type="text">
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
