import java.util.*


class LoopoverSolver(private val startPosition: Loopover) : Solver {

    private var solvingQueue: Queue<Loopover> = LinkedList<Loopover>()
    private var movesAlreadyMade: Set<Loopover> = mutableSetOf()
    private val solvedLoopover = startPosition.getSolvedLoopover()
    private var isLoopoverSolved: Boolean = false

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
            var newMove = currentPosition.moveRowLeft(i)
            if (movesAlreadyMade.contains(newMove)) {
                continue
            }
            if (newMove == solvedLoopover) {
                isLoopoverSolved = true
                return
            }
            addMovesToExisting(newMove)

            newMove = currentPosition.moveRowRight(i)
            if (movesAlreadyMade.contains(newMove)) {
                continue
            }
            if (newMove == solvedLoopover) {
                isLoopoverSolved = true
                return
            }
            addMovesToExisting(newMove)
        }
    }

    private fun makeAllNewMovesForColumns(currentPosition: Loopover) {
        if (isLoopoverSolved) {
            return
        }

        for (i in 0 until currentPosition.column) {
            var newMove = currentPosition.moveColumnDown(i)
            if (movesAlreadyMade.contains(newMove)) {
                continue
            }
            if (newMove == solvedLoopover) {
                print(newMove)
                isLoopoverSolved = true
                return
            }
            addMovesToExisting(newMove)

            newMove = currentPosition.moveColumnUp(i)
            if (movesAlreadyMade.contains(newMove)) {
                continue
            }
            if (newMove == solvedLoopover) {
                print(newMove)
                isLoopoverSolved = true
                return
            }
            addMovesToExisting(newMove)
        }
    }

    private fun addMovesToExisting(move: Loopover) {
        solvingQueue.add(move)
        movesAlreadyMade += move
    }
}