package Errors

class NotBoolConditionError : ErrorNode {

    /* Error that occurs whenever a condition doesn't evaluate to a boolean type */

    override fun printError(): String {
        return "Condition expression must be evaluated to a boolean type."
    }

}
