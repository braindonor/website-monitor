import java.io.File
import java.io.FileWriter
import java.util.*

class Logger() {

    fun log(string: String) {
        val file = File("website-monitor.log")
        val message = Calendar.getInstance().time.toString() + " : " + string
                FileWriter(file, true).use { it.write(message + System.lineSeparator()) }
        println(message)
    }
}