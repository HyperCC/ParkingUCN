<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class SaveVehiculoRequest extends FormRequest
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
            'patente' => 'required|min:6|max:8',
            'responsable' => 'required|min:8|max:12'
        ];
    }

    /**
     * @return array|string[]
     */
    public function messages()
    {
        return [
            'patente.required' => 'Debe ingresar una Patente en formato XX-YY-ZZ ó XXYYZZ',
            'patente.min' => 'Debe ingresar una Patente de al menos 6 caracteres en formato XXYYZZ',
            'patente.max' => 'Debe ingresar una Patente de a lo mas 8 caracteres en formato XX-YY-ZZ',
            'responsable.required' => 'Debe ingresar el Rut del Propietario en formato 12223334, 1222333-4 ó 1.222.333-4',
            'responsable.min' => 'Debe ingresar el Rut del Propietario de al menos 8 caracteres en formato 12223334',
            'responsable.min' => 'Debe ingresar el Rut del Propietario de a lo mas 12 caracteres en formato 11.222.333-4'
        ];
    }
}
