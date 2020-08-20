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
Route::get('/persona-Buscada/{buscada}', 'PersonaController@show')->name('persona.show');

Route::get('/crear-Vehiculo', 'VehiculoController@create')->name('vehiculo.create');
Route::post('/guardar_Vehiculo', 'VehiculoController@store')->name('vehiculo.store');
Route::get('/vehiculo-Buscado/{buscado}', 'VehiculoController@show')->name('vehiculo.show');

Route::get('/crear-Registro-Entrada', 'RegistroController@createEntrada')->name('registro.create.entrada');
Route::get('/crear-Registro-Salida', 'RegistroController@createSalida')->name('registro.create.salida');
Route::post('/guardar-Registro', 'RegistroController@store')->name('registro.store');

Route::get('/buscar-Rut-O-Patente', 'BusquedaController@search')->name('busqueda.search');
