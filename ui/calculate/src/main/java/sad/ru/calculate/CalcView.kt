package sad.ru.calculate

import sad.ru.base.base.BaseView
import sad.ru.domain.models.BMIData

internal interface CalcView : BaseView {
    fun setVarData(varData: BMIData)
}