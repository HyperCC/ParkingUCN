<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class SaveBusquedaRequest extends FormRequest
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
            'buscado' => 'required|min:6|max:12',
        ];
    }

    /**
     * @return array|string[]
     */
    public function messages()
    {
        return [
            'buscado.required' => 'Debe ingresar una Patente en formato XXYYZZ o un Rut en formatos 11222333-4',
            'buscado.min' => 'Debe ingresar una Patente de al menos 6 caracteres en formato XXYYZZ',
            'buscado.max' => 'Debe ingresar una Rut de a lo mas 12 caracteres en formato 11.222.333-4'
        ];
    }
}
