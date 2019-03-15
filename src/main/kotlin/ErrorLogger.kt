package main.kotlin

import main.kotlin.Errors.ErrorNode

class ErrorLogger {
    var errorList: ArrayList<ErrorNode> = arrayListOf()


    fun addError(error: ErrorNode): Boolean {
        return errorList.add(error)
    }

    fun printErrors() {
        for (error in errorList) {
            error.printError()
        }
    }
}