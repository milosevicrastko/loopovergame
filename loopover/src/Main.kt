fun main() {
    val loopover = Loopover(0,0, arrayOf(), null)
    val loopoverFromFile = loopover.loopoverFromFile("example.txt")
    val loopoverSolver = LoopoverSolver(loopoverFromFile)
    loopoverSolver.solve()
}