package upwork.test.domain.models

import java.io.Serializable

data class BMIData(
    val result: String,
    val name: String,
    val gender: Int,
    val poIndex: String
): Serializable