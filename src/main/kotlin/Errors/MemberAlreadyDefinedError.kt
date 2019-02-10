package Errors


class MemberAlreadyDefinedError(val id : String) : ErrorNode {

    override fun printError(): String {
        return "Member $id has already been defined."
    }
}