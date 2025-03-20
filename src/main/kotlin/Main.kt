import dominio.SeguroAuto
import dominio.SeguroHogar
import dominio.TipoAuto

fun main(){
    println(SeguroHogar("7754445",20.0,80,15000.0,"cALLE BRUJULA").serializar())

    println(SeguroAuto("5354646", 20.0, "bmw m4", "gasolina",TipoAuto.CAMION, "Hibrido", true, 1).serializar())
}