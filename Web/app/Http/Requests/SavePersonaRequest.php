<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class SavePersonaRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array
     */
    public function rules()
    {
        return [
            'nombre' => 'required|min:3',
            'rut' => 'required|min:8|max:12'
        ];
    }

    /**
     * @return array|string[]
     */
    public function messages()
    {
        return [
            'nombre.required' => 'Debe ingresar al menos un Nombre',
            'nombre.min' => 'Debe ingresar un Nombre de al menos 3 carácteres',
            'rut.required' => 'Debe ingresar el Rut en formato 12223334, 1222333-4 ó 1.222.333-4',
            'rut.min' => 'Debe ingresar el Rut de al menos 8 carácteres en formato 12223334',
            'rut.min' => 'Debe ingresar el Rut de a lo más 12 carácteres en formato 11.222.333-4'
        ];
    }

}
