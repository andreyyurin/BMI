package sad.ru.calculate

import com.arellomobile.mvp.InjectViewState
import ru.terrakok.cicerone.Router
import sad.ru.base.base.BasePresenter
import sad.ru.base.navigation.GlobalScreenKeys
import sad.ru.domain.models.BMIData
import sad.ru.numberpicker.GlobalScreen
import javax.inject.Inject

@InjectViewState
internal class CalcPresenter : BasePresenter<CalcView>() {

    @Inject
    lateinit var router: Router

    fun openResultScreen(data: BMIData) {
        viewState.setVarData(data)
        router.navigateTo(GlobalScreen.ThisScreenData(GlobalScreenKeys.RESULT_SCREEN, CalcFragment(), data))
    }

    fun goBack() {
        router.exit()
    }
}