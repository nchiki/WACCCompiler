import main.kotlin.Instructions.Instruction
import kotlin.Instructions.Instruction

class Assembly {

    init {
        labels = HashMap()
        labels.put("main", ArrayList())
    }

    val data = HashMap<String, String>()
    val labels: ArrayList<Pair<String, List<Instruction>>>

    fun newFunction(identifier : String) {

    }

    fun writeToFile() {
        // file.add(".data\n")
        // for string in data {
        //      addStringToFile(string)
        // }
        //
        // file.add(".text\n")
        // file.add(".global main")
        // for label in labels {
        //
        // }
    }

}