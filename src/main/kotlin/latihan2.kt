import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.*

fun main(){
//runBlocking{
//    println("Perkiraan Cuaca")
//    launch{
//        cetakPerkiraan2()
//    }
//    launch{
//        cetakTempratur2()
//    }
    /*
    fungsi launch() dari library coroutine untuk meluncurkan coroutine baru. Untuk menjalankan tugas secara serentak,
     tambahkan beberapa fungsi launch() ke kode Anda agar beberapa coroutine dapat diproses secara bersamaan.
     */
//}


/*
output:
Perkiraan Cuaca
30\u00b0C
Pagi Hari

launch{cetakPerkiraan()} dapat ditampilkan sebelum semua tugas di cetakPerkiraan()
 */
//    -----------------------------
//    val waktu = measureTimeMillis{
//        runBlocking {
//            println("Perkiraan Cuaca")
//            launch{
//                cetakPerkiraan2()
//            }
//            launch{
//                cetakTempratur2()
//            }
//        }
//    }
//    println("waktu eksekusi: ${waktu/1000.0} detik")

/*
output:
Perkiraan Cuaca
30\u00b0C
Pagi Hari
waktu eksekusi: 3.091 detik
 */
//    ---------------------
//    runBlocking{
//        println("Perkiraan Cuaca")
//        launch{
//            cetakPerkiraan2()
//        }
//        launch{
//            cetakTempratur2()
//        }
//        println("Semoga Harimu Menyenangkan")
//    }
    /*
    output:
    Perkiraan Cuaca
Semoga Harimu Menyenangkan
30\u00b0C
Pagi Hari
     */
    /*
    Dari output ini, Anda dapat mengamati bahwa setelah dua coroutine baru diluncurkan untuk cetakPerkiraan2() dan cetakTempratur2(), Anda dapat melanjutkan dengan petunjuk berikutnya yang mencetak Semoga Harimu Menyenangkan. Hal ini menunjukkan sifat "aktifkan dan lupakan" launch(). Anda mengaktifkan coroutine baru dengan launch(), dan tidak perlu khawatir saat tugasnya selesai.
     */
//    --------------------------
//    runBlocking {
//        println("Perkiraan Cuaca")
//        val perkiraan: Deferred<String> = async{
//            dapatkanPerkiraan()
//        }
//        val tempratur: Deferred<String> = async {
//            dapatkanTempratur()
//        }
//        println("${perkiraan.await()} ${tempratur.await()}")
//        println("Semoga harimu menyenangkan")
//    }
    /*
    output:
    Perkiraan Cuaca
Pagi Hari 30°C
Semoga harimu menyenangkan

Keren! Anda membuat dua coroutine yang berjalan serentak untuk mendapatkan data perkiraan dan suhu. Setelah setiap proses selesai, nilai akan ditampilkan. Kemudian, Anda menggabungkan kedua nilai yang ditampilkan ke dalam satu pernyataan cetak:
Pagi Hari 30C
     */
//    -----------------
    runBlocking {
        println("Perkiraan Cuaca")
        println(dapatkanLaporanCuaca())
        println("Semoga harimu menyenangkan")
    }
/*
output:
Perkiraan Cuaca
Pagi Hari 30°C
Semoga harimu menyenangkan
 */
/*
Output-nya sama, tetapi ada beberapa poin penting yang perlu diperhatikan di sini. Seperti yang disebutkan sebelumnya, coroutineScope() hanya akan ditampilkan setelah semua tugasnya, termasuk coroutine yang diluncurkan, telah selesai. Dalam hal ini, coroutine dapatkanPerkiraan() dan dapatkanTempratur() perlu menyelesaikan dan menampilkan hasil masing-masing
  Kemudian, teks Sunny dan 30°C digabungkan dan ditampilkan dari cakupan. Laporan cuaca Pagi Hari 30°C ini akan dicetak ke output, dan pemanggil dapat melanjutkan ke pernyataan cetak terakhir Semoga harimu menyenangkan!.
 */
}

//suspend fun cetakPerkiraan2(){
//    delay(3000)
//    println("Pagi Hari")
//}
//
//suspend fun cetakTempratur2(){
//    delay(1000)
//    println("30\\u00b0C")
//}

////    ---------------------
suspend fun dapatkanPerkiraan():String{
    delay(1000)
    return "Pagi Hari"
}
suspend fun dapatkanTempratur(): String{
    delay(1000)
    return "30\u00b0C"
}
//---------
// Dekomposisi Paralel
suspend fun dapatkanLaporanCuaca()=coroutineScope{
    val perkiraan = async{dapatkanPerkiraan()}
    val tempratur = async{dapatkanTempratur()}

    "${perkiraan.await()} ${tempratur.await()}"
}