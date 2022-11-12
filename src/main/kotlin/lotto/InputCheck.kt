package lotto

class InputCheck {

    fun checkInputLotteryPurchase(input: String): Boolean {
        val inputTrim = input.trim()
        if (!checkInputOneInteger(inputTrim)) return false
        if (!isOneThousandUnit(inputTrim)) return false
        return true
    }

    fun checkInputWinningLottery(input: String): Boolean {
        val inputTrim = input.trim()
        if (!isConsistAllCommaAndNumber(inputTrim)) return false
        if (!isAlternatingCommaAndNumber(inputTrim)) return false
        val numbers = convertSeparateComma(inputTrim)
        numbers.forEach { if (!isLotteryRange(it)) return false }
        if (numbers.size != 6) return false
        if (numbers.toSet().size != 6) return false
        return true
    }

    fun checkInputBonusInteger(input: String, winningLottery: List<Int>): Boolean {
        val inputTrim = input.trim()
        if (!checkInputOneInteger(inputTrim)) return false
        if (!isLotteryRange(inputTrim.toInt())) return false
        if (winningLottery.contains(inputTrim.toInt())) return false
        return true
    }

    private fun checkInputOneInteger(input: String): Boolean {
        input.forEach { if (!isNumber(it)) return false }
        return true
    }

    private fun isNumber(char: Char): Boolean = char.code in 48..57
    private fun isOneThousandUnit(input: String): Boolean = input.toInt() % 1000 == 0

    private fun isConsistAllCommaAndNumber(input: String): Boolean {
        input.forEach {
            if (!(isNumber(it) || isComma(it) || it == ' ')) return false
        }
        return true
    }

    private fun isAlternatingCommaAndNumber(input: String): Boolean {
        var nowComma = false
        for (i in input.indices) {
            if (input[i] == ',') {
                if (nowComma) return false // 콤마가 나왓는데 또 연속으로 나온 경우
                nowComma = true
            } else if (isNumber(input[i])) {
                nowComma = false
            }
        }
        return !nowComma //마지막에 콤마가 오면 안됨
    }

    private fun convertSeparateComma(input: String): List<Int> {
        val numbers = input.split(",")
        return numbers.map { it.toInt() }
    }

    private fun isLotteryRange(num: Int) = num in 1..45

    private fun isComma(char: Char): Boolean = char.code == 44
}