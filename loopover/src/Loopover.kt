const val INVALID_COLUMN_INDEX = "Column index for function not valid"
const val INVALID_ROW_INDEX = "Row index for function not valid"
const val LOOPOVER_ALREADY_SOLVED = "Loopover already solved"
const val INVALID_LOOPOVER_SETUP = "Invalid loopover setup"
const val MOVED_COLUMN_UP = "Moved column up"
const val MOVED_COLUMN_DOWN = "Moved column down"
const val MOVED_ROW_LEFT = "Moved row left"
const val MOVED_ROW_RIGHT = "Moved row right"

class Loopover(val row: Int, val column: Int, val array: Array<CharArray>, val parent: Loopover?, val originMove: String?) {

    fun loopoverFromFile(fileName: String): Loopover {
        val file = FileReader.readFileAsLinesUsingUseLines(fileName)
        val rowCount = file.size
        val columnCount = file[0].length

        val newArray = Array(rowCount) { CharArray(columnCount) }
        for (i in 0 until rowCount) {
            for (j in 0 until columnCount) {
                newArray[i][j] = file[i][j]
            }
        }

        return Loopover(rowCount, columnCount, newArray, null, null)

    }

    private fun copyArray(): Array<CharArray> {
        val newArray = Array(row) { CharArray(column) }
        for (i in 0 until row) {
            for (j in 0 until column) {
                newArray[i][j] = array[i][j]
            }
        }
        return newArray
    }

    fun areElementsUnique() {
        val setOfElements = mutableSetOf<Char>()
        for (i in 0 until row) {
            for (j in 0 until column) {
                if (setOfElements.contains(array[i][j])) {
                    throw Exception(INVALID_LOOPOVER_SETUP)
                }
                setOfElements.add((array[i][j]))
            }
        }
    }

    fun getSolvedLoopover(): Loopover {
        val newArray = Array(row) { CharArray(column) }
        var letter = 'a'
        for (i in 0 until row) {
            for (j in 0 until column) {
                newArray[i][j] = letter++
            }
        }
        return Loopover(row, column, newArray, null, null)
    }

    fun moveColumnUp(columnIndex: Int): Loopover {
        if (columnIndex < 0 || columnIndex >= column) {
            throw Exception(INVALID_COLUMN_INDEX)
        }
        val newArray = copyArray()
        val swap = newArray[0][columnIndex]
        for (i in 0 until row - 1) {
            newArray[i][columnIndex] = newArray[i + 1][columnIndex]
        }
        newArray[row - 1][columnIndex] = swap
        return Loopover(row, column, newArray, this, String.format("%s %s", MOVED_COLUMN_UP, columnIndex))
    }

    fun moveColumnDown(columnIndex: Int): Loopover {
        if (columnIndex < 0 || columnIndex >= column) {
            throw Exception(INVALID_COLUMN_INDEX)
        }
        val newArray = copyArray()
        val swap = newArray[row - 1][columnIndex]
        for (i in row - 1 downTo 1) {
            newArray[i][columnIndex] = newArray[i - 1][columnIndex]
        }
        newArray[0][columnIndex] = swap
        return Loopover(row, column, newArray, this, String.format("%s %s", MOVED_COLUMN_DOWN, columnIndex))
    }

    fun moveRowLeft(rowIndex: Int): Loopover {
        if (rowIndex < 0 || rowIndex >= row) {
            throw Exception(INVALID_ROW_INDEX)
        }
        val newArray = copyArray()
        val swap = newArray[rowIndex][0]
        for (i in 0 until column - 1) {
            newArray[rowIndex][i] = newArray[rowIndex][i + 1]
        }
        newArray[rowIndex][column - 1] = swap
        return Loopover(row, column, newArray, this, String.format("%s %s", MOVED_ROW_LEFT, rowIndex))


    }

    fun moveRowRight(rowIndex: Int): Loopover {
        if (rowIndex < 0 || rowIndex >= row) {
            throw Exception(INVALID_ROW_INDEX)
        }
        val newArray = copyArray()
        val swap = newArray[rowIndex][column - 1]
        for (i in column - 1 downTo 1) {
            newArray[rowIndex][i] = newArray[rowIndex][i - 1]
        }
        newArray[rowIndex][0] = swap
        return Loopover(row, column, newArray, this, String.format("%s %s", MOVED_ROW_RIGHT, rowIndex))
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        for (i in 0 until row) {
            for (j in 0 until column) {
                stringBuilder.append(array[i][j])
            }
            stringBuilder.appendLine()
        }
        return stringBuilder.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Loopover) return false

        if (row != other.row) return false
        if (column != other.column) return false
        if (!array.contentDeepEquals(other.array)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = row
        result = 31 * result + column
        result = 31 * result + array.contentDeepHashCode()
        return result
    }


}