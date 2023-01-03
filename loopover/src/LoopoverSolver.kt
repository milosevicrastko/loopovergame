import java.util.*

const val SOLUTION_FOUND = "SOLUTION FOUND"


class LoopoverSolver(private val startPosition: Loopover) : Solver {

    private var solvingQueue: Queue<Loopover> = LinkedList()
    private var movesAlreadyMade: Set<Loopover> = mutableSetOf()
    private val solvedLoopover = startPosition.getSolvedLoopover()
    private var isLoopoverSolved: Boolean = false
    private var solvedTrail: Stack<Loopover> = Stack()

    override fun solve() {

        startPosition.areElementsUnique()

        if (startPosition == solvedLoopover) {
            throw Exception(LOOPOVER_ALREADY_SOLVED)
        }

        solvingQueue.add(startPosition)

        while (solvingQueue.isNotEmpty()) {
            if (isLoopoverSolved) break
            val currentPosition = solvingQueue.poll()
            makeAllNewMovesForRows(currentPosition)
            makeAllNewMovesForColumns(currentPosition)
        }

    }

    private fun makeAllNewMovesForRows(currentPosition: Loopover) {
        if (isLoopoverSolved) {
            return
        }

        for (i in 0 until currentPosition.row) {
            operateWithNewMove(currentPosition.moveRowLeft(i))
            operateWithNewMove(currentPosition.moveRowRight(i))
        }
    }

    private fun makeAllNewMovesForColumns(currentPosition: Loopover) {
        if (isLoopoverSolved) {
            return
        }

        for (i in 0 until currentPosition.column) {
            operateWithNewMove(currentPosition.moveColumnDown(i))
            operateWithNewMove(currentPosition.moveColumnUp(i))

        }
    }

    private fun operateWithNewMove(move: Loopover) {
        if (!movesAlreadyMade.contains(move)) {
            checkIfMoveIsSolution(move)
            addMovesToExisting(move)
        }
    }

    private fun checkIfMoveIsSolution(move: Loopover) {
        if (move == solvedLoopover) {

            isLoopoverSolved = true
            traceMovesToSolved(move)
            printSolvedTrail()
            print(move)
            throw Exception(SOLUTION_FOUND)
        }
    }

    private fun traceMovesToSolved(move: Loopover) {
        var stackMove = move
        while (stackMove.parent != null) {
            solvedTrail.push(stackMove)
            stackMove = stackMove.parent!!
        }
    }

    private fun printSolvedTrail() {
        while (solvedTrail.isNotEmpty()) {
            println(solvedTrail.pop().originMove)
        }
    }

    private fun addMovesToExisting(move: Loopover) {
        solvingQueue.add(move)
        movesAlreadyMade += move
    }

}