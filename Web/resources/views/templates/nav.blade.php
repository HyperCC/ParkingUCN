@extends('templates.validateerrors')

<nav class="navbar navbar-light navbar-expand-lg bg-primary">

    <div class="container">

        <a class="navbar-brand font-weight-bold text-light my-1" href="{{ route('home') }}">
            ParkingUCN
        </a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">

            <ul class="navbar-nav mr-auto">

                <li class="nav-item">
                    <a class="nav-link text-light" href="{{ route('app') }}"> App </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link text-light" href="#"> Acerca </a>
                </li>

            </ul>

            <div class="row">
                <form class="form-inline my-lg-0 my-2" action="{{ route('busqueda.search') }}" method="get">

                    <div class="col-8">
                        <input class="form-control rounded-pill px-3" type="text"
                               placeholder="Buscar Rut o Patente.."
                               aria-label="Search"
                               name="buscado">
                    </div>

                    <div class="col-4">
                        <button class="btn btn-outline-light" type="submit">Buscar</button>
                    </div>

                </form>
            </div>

        </div>
    </div>
</nav>
