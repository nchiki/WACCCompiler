package main.kotlin.Utils

enum class Register(s: String) {
    r0(""), //used for passing and returning arguments (r0 - r3)
    r1(""),
    r2(""),
    r3(""),
    r4(""),
    r5(""),
    r6(""),
    r7(""),
    r8(""),
    r9(""),
    r10(""),
    r11(""),
    r12(""),
    r13(""),
    lr("lr"),
    pc("PC"),
    r16("CPSR"),
    sp("sp")
}