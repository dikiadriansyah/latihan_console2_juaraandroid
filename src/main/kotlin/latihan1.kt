import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
//    println("Hello World!")
//    runBlocking {
//        println("Weather forecast")
//        delay(1000)
//        menunda eksekusi sebesar 1000milidetik atau 1 detik
//        println("Sunny")
//    }
    /*
    runBlocking() menjalankan loop peristiwa, yang dapat menangani beberapa tugas sekaligus dengan melanjutkan setiap tugas dari posisi terakhir saat tugas siap dilanjutkan.
     */
//    --------------------
//    runBlocking{
//        println("Perkiraan Cuaca")
//        cetakPerkiraan()
//        cetakTempratur()
//    }
//---------------
//    Opsional) Jika ingin melihat waktu yang diperlukan untuk menjalankan program ini dengan penundaan, Anda dapat menggabungkan kode dalam panggilan ke measureTimeMillis()
    val waktu = measureTimeMillis{
        runBlocking{
            println("Perkiraan Cuaca")
            cetakPerkiraan()
            cetakTempratur()
        }
    }
println("Waktu Eksekusi: ${waktu/1000.0} detik")
}
suspend fun cetakPerkiraan(){
    delay(1000)
    println("Hujan Ringan")
}
/*
 pengubah suspend tepat sebelum kata kunci fun di deklarasi fungsi printForecast() untuk menjadikannya sebagai fungsi penangguhan.
 */

suspend fun cetakTempratur(){
    delay(1000)
    println("30\\u00b0C")
}