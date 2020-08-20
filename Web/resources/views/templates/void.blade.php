@extends('templates.body')

@section('title_head', 'Sin resultados' )

@section('content_body')

    <div class="container">

        <div class="row my-4">

            <div class="col-12 col-lg-6">
                <img class="img-fluid my-3" src="{{ URL::to('/')}}/img/voidresult.svg" alt="busqueda sin resultados">
            </div>

            <div class="col-12 col-lg-6">
                <div class="card shadow-sm">

                    <h3 class="card-header text-center font-weight-bold"> Sin resultados :(</h3>

                    <div class="card-body">

                        <div class="card p-3">

                            <div class="my-3 text-center">

                                <p class="text-capitalize" aria-describedby="basic-addon3"> La palabra ingresada en la
                                    busqueda <b>{{ $buscado }}</b> no coincide con ningun resultado en nuestra base de datos.
                                    Verifica la sintaxis y vuelve a intentarlo. </p>
                            </div>

                            <div class="py-3">
                                <a class="btn btn-lg btn-outline-primary btn-block rounded-pill mb-2 text-dark"
                                   href="{{ route('home') }}"> Volver al inicio .. </a>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


@endsection
