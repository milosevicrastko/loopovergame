import java.io.File

object FileReader {
    fun readFileAsLinesUsingUseLines(fileName: String): List<String>
            = File(fileName).useLines { it.toList() }
}