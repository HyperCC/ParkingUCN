@extends('templates.body')

@section('title_head', 'Crear Registro Salida')

@section('content_body')

    <div class="container">
        <div class="row">
            <div class="col-12 col-sm-10 col-lg-6 mx-auto my-3">

                <form class="bg-white py-3 px-4 shadow rounded" method="POST" action="#">

                    @include('templates.createregistro', ['tipoRegistro'=>'Salida'])

                </form>

            </div>
        </div>
    </div>

@endsection
