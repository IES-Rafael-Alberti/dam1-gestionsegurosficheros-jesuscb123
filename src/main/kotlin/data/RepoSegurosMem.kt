package prog2425.dam1.seguros.data


import prog2425.dam1.seguros.model.Seguro

class RepoSegurosMem : IRepoSeguros {
    val listaSeguros = mutableListOf<Seguro>()

    override fun agregar(seguro: Seguro): Boolean {
        val seguroExistente = buscar(seguro.numPoliza)
        if (seguroExistente == null){
            listaSeguros.add(seguro)
        }
        return false
    }

    override fun buscar(numPoliza: Int): Seguro? {
       return listaSeguros.find { it.numPoliza == numPoliza }
    }

    override fun eliminar(seguro: Seguro): Boolean {
        if (listaSeguros.remove(seguro)){
            return true
        }else{
            return false
        }
    }

    override fun eliminar(numPoliza: Int): Boolean {
        val seguroExistente = buscar(numPoliza)
        if (seguroExistente != null){
            eliminar(seguroExistente)
        }
        return false
    }

    override fun obtenerTodos(): List<Seguro> {
        return listaSeguros
    }

    override fun obtener(tipoSeguro: String): List<Seguro> {
        return listaSeguros.filter { it.tipoSeguro() == tipoSeguro }
    }


}