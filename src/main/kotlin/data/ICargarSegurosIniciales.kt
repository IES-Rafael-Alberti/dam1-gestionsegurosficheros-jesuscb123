package prog2425.dam1.seguros.data

import prog2425.dam1.seguros.model.Seguro


interface ICargarSegurosIniciales {
    fun cargarSeguros(mapa: Map<String, (List<String>) -> Seguro>): Boolean
}