@extends('templates.body')

@section('title_head', 'Crear Registro Entrada')

@section('content_body')

    <div class="container">
        <div class="row">
            <div class="col-12 col-sm-10 col-lg-6 mx-auto my-3">

                @extends('templates.validateerrors')

                <form class="bg-white py-3 px-4 shadow rounded" method="POST" action="{{ route('registro.store') }}">

                    @include('templates.createregistro', ['tipoRegistro'=>'Entrada', 'registro'=>$registro])

                </form>

            </div>
        </div>
    </div>

@endsection
