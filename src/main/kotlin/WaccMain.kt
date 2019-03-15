import main.kotlin.CodeGenerator
import main.kotlin.ErrorLogger
import main.kotlin.SymbolTable
import main.kotlin.preprocess
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.FileInputStream
import kotlin.system.exitProcess


fun main(args: Array<String>) {
        val path = if (args.isEmpty()) {
                "tests/valid/function/simple_functions/sameArgName2.wacc"
        } else {
                args[0]
        }
        System.setIn(FileInputStream(path))
        val input = CharStreams.fromStream(java.lang.System.`in`)

        //Lexical analysis
        val lexer = BasicLexer(input)
        val newInput = preprocess(input, lexer.allTokens)
        val newLexer = BasicLexer(newInput)

        //Create a buffer of tokens
        val tokens = CommonTokenStream(newLexer)
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

        val codeGen = CodeGenerator()
        codeGen.initRegs()
        codeGen.switchFunctions("main")
        progNode.generateCode(codeGen)

        codeGen.writeToFile(path.substring(path.lastIndexOf("/") + 1).replace(".wacc", ".s"))

}
