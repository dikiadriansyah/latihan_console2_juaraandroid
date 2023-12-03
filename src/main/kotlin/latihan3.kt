import kotlinx.coroutines.*

fun main(){
//    val angkaOrang = 0
//    val angkaPiza = 20
//    println("Potongan perorang: ${angkaPiza/angkaOrang}")
//    output:
//    Exception in thread "main" java.lang
    /*
    Saat Anda menjalankan program, program akan mengalami error dengan pengecualian aritmetika karena Anda tidak dapat membagi angka dengan nol.
     */

//    -----------------
//    Pengecualian dengan coroutine
//    runBlocking{
//        println("Perkiraan Cuaca")
//        println(dapatkanLaporanCuaca2())
//        println("Semoga harimu menyenangkan")
//    }
    /*
    output:
    Perkiraan Cuaca
Pagi Hari 30°C
Semoga harimu menyenangkan

     */
//----------------
//    runBlocking{
//        println("Perkiraan Cuaca")
//        try{
//            println(dapatkanLaporanCuaca2())
//
//        }catch(e: AssertionError){
//            println("Caught exception dalam runBlocking(): $e")
//            println("Laporan tidak tersedia pada waktu ini")
////            Handle error
//        }
//        println("Semoga harimu menyenangkan")
//    }
    /*
    output:
    Perkiraan Cuaca
Caught exception dalam runBlocking(): java.lang.AssertionError: Tempratur tidak valid
Laporan tidak tersedia pada waktu ini
Semoga harimu menyenangkan
     */
    /*
    Dari output, Anda dapat mengamati bahwa dapatkanTempratur() menampilkan pengecualian. Dalam isi fungsi runBlocking(),
    Anda akan mengelilingi panggilan println(dapatkanLaporanCuaca2()) dalam blok try-catch. Anda akan menangkap jenis
    pengecualian yang diharapkan (AssertionError dalam kasus contoh ini). Kemudian, Anda akan mencetak pengecualian ke
     output sebagai "Caught exception", diikuti dengan string pesan error. Untuk menangani error tersebut, Anda akan
     memberi tahu pengguna bahwa laporan cuaca tidak tersedia dengan pernyataan println() tambahan:
     Laporan tidak tersedia pada waktu ini.
     */
    //---------------------------
//    runBlocking{
//        println("Perkiraan cuaca")
//        println(dapatkanLaporanCuaca2())
//        println("Semoga harimu menyenangkan")
//    }
    /*
    output:
    Perkiraan cuaca
Caught Exception java.lang.AssertionError: Tempratur adalah tidak valid
pagi hari {Tempratur tidak ditemukan}
Semoga harimu menyenangkan
     */
    /*
    Dari output, Anda dapat melihat bahwa memanggil dapatkanTempratur() gagal dengan pengecualian, tetapi kode dalam
    async() dapat menangkap pengecualian tersebut dan menanganinya dengan baik dengan menggunakan coroutine yang masih
    menampilkan String yang menyatakan suhu tidak ditemukan. Laporan cuaca masih dapat dicetak, dengan perkiraan
     keberhasilan sebesar Pagi Hari. Suhu di laporan cuaca tidak ada, tetapi muncul pesan yang menjelaskan bahwa suhu tidak
     ditemukan. Ini adalah pengalaman pengguna yang lebih baik daripada program mengalami error.
     */
    //---------------------------
//runBlocking{
//    println("Perkiraan Cuaca")
//    println(dapatkanLaporanCuaca2())
//    println("Semoga harimu menyenangkan")
//}
/*
output:
Perkiraan Cuaca
pagi hari
Semoga harimu menyenangkan
 */
    /*
    Yang Anda pelajari di sini adalah bahwa coroutine dapat dibatalkan, tetapi tidak akan memengaruhi coroutine lain dalam cakupan yang sama dan coroutine induk tidak akan dibatalkan
     */
//    -----------------------
//    runBlocking {
//        launch{
//            delay(1000)
//            println("ditemukan 10 hasil")
//        }
//        println("Loading...")
//    }
    /*
    output:
    Loading...
ditemukan 10 hasil
     */
//    ----------------
//    runBlocking {
//        launch{
//            withContext(Dispatchers.Default){
//                delay(1000)
//                println("10 hasil ditemukan")
//            }
//        }
//        println("Loading...")
//    }
    /*
    output:
    Loading...
10 hasil ditemukan
     */
//    ------------------------
    runBlocking {
        println("${Thread.currentThread().name} - runBlocking fungsi")
        launch{
            println("${Thread.currentThread().name} - launch fungsi")
            withContext(Dispatchers.Default){
                println("${Thread.currentThread().name} - withContext fungsi")
                delay(1000)
                println("10 hasil ditemukan")
            }
            println("${Thread.currentThread().name} - end of launch fungsi")
        }
        println("Loading...")
    }
    /*
    output:
    main - runBlocking fungsi
Loading...
main - launch fungsi
DefaultDispatcher-worker-1 - withContext fungsi
10 hasil ditemukan
main - end of launch fungsi
     */
    /*
    Dari output ini, Anda dapat mengamati bahwa sebagian besar kode dieksekusi di coroutine pada thread utama. Namun,
    untuk bagian kode Anda dalam blok withContext(Dispatchers.Default) dieksekusi dalam coroutine pada thread pekerja
    Dispatcher Default (yang bukan thread utama). Perhatikan bahwa setelah withContext() ditampilkan, coroutine akan
    kembali berjalan di thread utama (dibuktikan dengan pernyataan output: main @coroutine#2 - end of launch function).
    Contoh ini menunjukkan bahwa Anda dapat berganti dispatcher dengan mengubah konteks yang digunakan untuk coroutine.
     */

}

//suspend fun dapatkanLaporanCuaca2() = coroutineScope{
//    val perkiraan = async{ dapatkanPerkiraan2()}
//    val tempratur = async{ dapatkanTempratur2()}
//    "${perkiraan.await()} ${tempratur.await()}"
//}
//
//suspend fun dapatkanPerkiraan2(): String{
//    delay(1000)
//    return "Pagi Hari"
//}
//
//suspend fun dapatkanTempratur2(): String{
//    delay(500)
//    throw AssertionError("Tempratur tidak valid")
//    return "30\u00b0C"
//}
//----------------------------
//suspend fun dapatkanLaporanCuaca2() = coroutineScope{
//    val perkiraan = async{ dapatkanPerkiraan2() }
//    val tempratur = async{
//        try{
//            dapatkanTempratur2()
//        }catch(e: AssertionError){
//            println("Caught Exception $e")
//            "{Tempratur tidak ditemukan}"
//        }
//    }
//    "${perkiraan.await()} ${tempratur.await()}"
    /*
    Dalam hal ini, produsen (async()) dapat menangkap dan menangani pengecualian, tetapi tetap menampilkan hasil String
    dari "{ Tempratur tidak ditemukan }". Konsumen (await()) menerima hasil String ini dan bahkan tidak perlu mengetahui
    bahwa telah terjadi pengecualian. Ini adalah opsi lain untuk menangani dengan baik pengecualian yang Anda harapkan
    dapat terjadi dalam kode Anda.
     */
//}
//
//
//suspend fun dapatkanPerkiraan2(): String{
//    delay(1000)
//    return "pagi hari"
//}
//
//suspend fun dapatkanTempratur2(): String{
//    delay(500)
//    throw AssertionError("Tempratur adalah tidak valid")
//    return "30\u00b0C"
//}

//-------------
//Pembatalan
suspend fun dapatkanLaporanCuaca2()=coroutineScope{
    val perkiraan = async{dapatkanPerkiraan2()}
    val tempratur = async{dapatkanTempratur2()}
//    "${perkiraan.await()} ${tempratur.await()}"
delay(200)
    tempratur.cancel()
    "${perkiraan.await()}"
}
suspend fun dapatkanPerkiraan2(): String{
    delay(1000)
    return "pagi hari"
}

suspend fun dapatkanTempratur2(): String{
    delay(1000)
    return "30\u00b0C"
}