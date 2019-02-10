package Errors


class MemberAlreadyDefinedError(val id : String, val line : Int, val pos : Int) : ErrorNode {

    override fun printError(): String {
        return "Member $id has already been defined. Error found in line $line, at position $pos ."
    }
}