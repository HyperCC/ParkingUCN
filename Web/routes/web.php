<?php

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('home');
})->name('home');

Route::get('/app', function () {
    return view('appdownload');
})->name('app');

Route::get('/crear-Persona', 'PersonaController@create')->name('persona.create');
Route::post('/guardar-Persona', 'PersonaController@store')->name('persona.store');

Route::get('/crear-Vehiculo', 'VehiculoController@create')->name('vehiculo.create');

Route::get('/crear-Registro', 'RegistroController@create')->name('registro.create');

Route::get('/probar', 'PersonaController@getContratos');
