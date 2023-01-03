const val FILENAME = "example.txt"
fun main() {
    LoopoverSolver(Loopover(0,0, arrayOf(), null, null)
        .loopoverFromFile(FILENAME))
        .solve()
}