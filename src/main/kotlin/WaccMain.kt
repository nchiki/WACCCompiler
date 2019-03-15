import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.ValueTable
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.FileInputStream
import kotlin.system.exitProcess


fun main(args: Array<String>) {

    var optimise = false

    if (args.size > 1) {
        if (args[0].equals("-o")) {
            optimise = true
        }
        System.setIn(FileInputStream(args[1]))
    }else{
        System.setIn(FileInputStream(args[0]))
    }

    val input = CharStreams.fromStream(java.lang.System.`in`)
    //Lexical analysis
    val lexer = BasicLexer(input)
    //Create a buffer of tokens
    val tokens = CommonTokenStream(lexer)
    //Syntactical analysis
    val parser = BasicParser(tokens)
    val tree = parser.prog()
    //Exit with code 100 if there are any syntax errors
    if (parser.numberOfSyntaxErrors > 0) {
        exitProcess(100)
    }
    val visitor = WaccVisitor()
    val errorLogger = ErrorLogger()
    val symbolTable = SymbolTable(null)
    val progNode = visitor.visit(tree)
    //Semantic Check
    progNode.semanticCheck(errorLogger, symbolTable)
    for (error in errorLogger.errorList) {
        println(error.printError())
    }
    if (errorLogger.errorList.count() > 0) {
        exitProcess(200)
    }

    if(optimise){
        val valueTable = ValueTable(null)
        progNode.optimise(valueTable)
    }

    val codeGen = CodeGenerator()
    codeGen.initRegs()
    codeGen.switchFunctions("main")
    progNode.generateCode(codeGen)

    codeGen.writeToFile(args[0].substring(args[0].lastIndexOf("/") + 1).replace(".wacc", ".s"))

}

