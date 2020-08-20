<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="utf-8"/>
    <title>Parking UCN - @yield('title_head')</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="{{ asset(mix('css/app.css')) }}">
    <link rel="stylesheet" href="{{ asset('css/paletas.css') }}">
    <script src="{{ asset(mix('js/app.js')) }}" defer></script>

    <link rel="shortcut icon" href="{{{ asset('img/favicon.png') }}}">

</head>
<body>

    <div class="d-flex flex-column h-screen justify-content-between" id="app">
        <header>
            @include('templates.nav')
        </header>

        <main>
            @yield('content_body')
        </main>

        <footer class="bg-dark text-center text-light py-3 shadow">
            {{ config('app.name') }} | Copyright @ {{ date('Y') }}
        </footer>

    </div>

</body>
</html>
