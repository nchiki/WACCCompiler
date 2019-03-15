import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.preprocess
import main.kotlin.ValueTable
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.FileInputStream
import kotlin.system.exitProcess


fun main(args: Array<String>) {
        var optimise = false
        var inputFile = args[0]

        if (args.size > 1) {
            if (args[0].equals("-o")) {
                    optimise = true
            }
                inputFile = args[1]
        }

        System.setIn(FileInputStream(inputFile))

        val input = CharStreams.fromStream(java.lang.System.`in`)
        //Lexical analysis
        val lexer = BasicLexer(input)
//        println(lexer.allTokens)
        val newInput = preprocess(input, lexer.allTokens)
//        println(newInput.toString())
        val newLexer = BasicLexer(newInput)

        //Create a buffer of tokens
        val tokens = CommonTokenStream(newLexer)
        //Syntactical analysis
        val parser = BasicParser(tokens)
        val tree = parser.prog()
//        println(tree.childCount)
//        println(tree.toStringTree())
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

        codeGen.writeToFile(inputFile.substring(inputFile.lastIndexOf("/") + 1).replace(".wacc", ".s"))

}
