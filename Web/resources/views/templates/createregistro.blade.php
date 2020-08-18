@csrf

<div class="text-center">
    <span class="display-3">Crear Registro de {{ $tipoRegistro }}</span>
</div>

<hr>

<div class="form-group">
    <label for="patente"> Patente Vehiculo </label>
    <input class="form-control shadow-sm bg-light" name="patente" type="text">
</div>

<div class="form-group">
    <label for="rut"> Rut Conductor </label>
    <input class="form-control shadow-sm bg-light" name="rut" type="text">
</div>

<hr>

<div class="py-3">
    <button type="submit" class="btn btn-primary btn-lg btn-block rounded-pill"> Crear
        Registro {{ $tipoRegistro }}
    </button>
    <a class="btn btn-lg btn-block btn-outline-dark rounded-pill" href="{{route('home')}}">
        Cancelar
    </a>
</div>
