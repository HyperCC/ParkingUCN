@if($errors->any())

    <div class="alert alert-danger">
        <p>Se deben corregir estos errores antes de continuar:</p>
        <ul>

            @foreach($errors->all() as $error)
                <li>{{ $error }}</li>
            @endforeach

        </ul>
    </div>

@endif
