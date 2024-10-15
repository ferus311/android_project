package com.ferus.birthday

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var textResult: TextView
    lateinit var subText: TextView

    var state: Int = 1
    var op: Int = 0
    var op1: String = ""
    var op2: String = ""
    var isDecimal: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.text_result)
        subText = findViewById(R.id.sub_text)

        val buttonIds = listOf(
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,
            R.id.btn_add, R.id.btn_subtract, R.id.btn_multiply, R.id.btn_divide,
            R.id.btn_equal, R.id.btn_clear, R.id.btn_sign, R.id.btn_backspace, R.id.btn_clear_entry, R.id.btn_dot
        )

        for (id in buttonIds) {
            findViewById<Button>(id).setOnClickListener(this)
        }
    }

    override fun onClick(p0: View?) {
        val id = p0?.id
        when (id) {
            R.id.btn_0 -> addDigit("0")
            R.id.btn_1 -> addDigit("1")
            R.id.btn_2 -> addDigit("2")
            R.id.btn_3 -> addDigit("3")
            R.id.btn_4 -> addDigit("4")
            R.id.btn_5 -> addDigit("5")
            R.id.btn_6 -> addDigit("6")
            R.id.btn_7 -> addDigit("7")
            R.id.btn_8 -> addDigit("8")
            R.id.btn_9 -> addDigit("9")
            R.id.btn_dot -> addDot()
            R.id.btn_add -> setOperator(1)
            R.id.btn_subtract -> setOperator(2)
            R.id.btn_multiply -> setOperator(3)
            R.id.btn_divide -> setOperator(4)
            R.id.btn_equal -> calculate()
            R.id.btn_clear -> clear()
            R.id.btn_sign -> toggleSign()
            R.id.btn_backspace -> backspace()
            R.id.btn_clear_entry -> clearEntry()
        }
    }

    private fun addDigit(digit: String) {
        if (state == 1) {
            op1 += digit
            textResult.text = formatNumber(op1)
        } else {
            op2 += digit
            textResult.text = formatNumber(op2)
        }
    }

    private fun addDot() {
        if (state == 1) {
            if (!op1.contains(".")) {
                op1 += "."
                textResult.text = op1
            }
        } else {
            if (!op2.contains(".")) {
                op2 += "."
                textResult.text = op2
            }
        }
    }

    private fun setOperator(operator: Int) {
        op = operator
        state = 2
        isDecimal = false
        subText.text = "$op1 ${getOperatorSymbol(op)}"
    }

    private fun calculate() {
        var result = 0.0
        val num1 = op1.toDoubleOrNull() ?: 0.0
        val num2 = op2.toDoubleOrNull() ?: 0.0
        when (op) {
            1 -> result = num1 + num2
            2 -> result = num1 - num2
            3 -> result = num1 * num2
            4 -> result = if (num2 != 0.0) num1 / num2 else 0.0
        }
        textResult.text = formatNumber(result.toString())
        subText.text = "$op1 ${getOperatorSymbol(op)} $op2 ="
        state = 1
        op1 = result.toString()
        op2 = ""
        op = 0
        isDecimal = false
    }

    private fun clear() {
        state = 1
        op = 0
        op1 = ""
        op2 = ""
        textResult.text = "0"
        subText.text = ""
        isDecimal = false
    }

    private fun clearEntry() {
        if (state == 1) {
            op1 = ""
            textResult.text = "0"
        } else {
            op2 = ""
            textResult.text = "0"
        }
    }

    private fun toggleSign() {
        if (state == 1) {
            if (op1.startsWith("-")) {
                op1 = op1.substring(1)
            } else {
                op1 = "-$op1"
            }
            textResult.text = op1
        } else {
            if (op2.startsWith("-")) {
                op2 = op2.substring(1)
            } else {
                op2 = "-$op2"
            }
            textResult.text = op2
        }
    }

    private fun backspace() {
        if (state == 1) {
            if (op1.isNotEmpty()) {
                op1 = op1.dropLast(1)
                textResult.text = if (op1.isEmpty()) "0" else op1
            }
        } else {
            if (op2.isNotEmpty()) {
                op2 = op2.dropLast(1)
                textResult.text = if (op2.isEmpty()) "0" else op2
            }
        }
    }

    private fun formatNumber(number: String): String {
        val parts = number.split(".")
        val integerPart = parts[0]
        if (integerPart.length > 7) {
            return "Error"
        }
        if (parts.size > 1 && parts[1] == "0") {
            return integerPart
        }
        return if (parts.size > 1) {
            val decimalPart = parts[1]
            if (decimalPart.length > 6) {
                val formattedNumber = BigDecimal(number).setScale(6, RoundingMode.HALF_UP)
                formattedNumber.toPlainString()
            } else {
                number
            }
        } else {
            number
        }
    }

    private fun getOperatorSymbol(operator: Int): String {
        return when (operator) {
            1 -> "+"
            2 -> "-"
            3 -> "*"
            4 -> "/"
            else -> ""
        }
    }
}