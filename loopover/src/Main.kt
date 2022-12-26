fun main() {
    val loopover = Loopover(0,0, arrayOf())
    val loopoverFromFile = loopover.loopoverFromFile("example.txt")
    print(loopoverFromFile)
    println()
    val loopoverMovedLeft = loopoverFromFile.moveRowLeft(2)
    print(loopoverMovedLeft)
}