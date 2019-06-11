package upwork.test.calculate

import com.arellomobile.mvp.InjectViewState
import ru.terrakok.cicerone.Router
import upwork.test.base.base.BasePresenter
import upwork.test.base.navigation.GlobalScreenKeys
import upwork.test.domain.models.BMIData
import javax.inject.Inject

@InjectViewState
internal class CalcPresenter : BasePresenter<CalcView>() {

    @Inject
    lateinit var router: Router

    fun openResultScreen(data: BMIData) {
        router.navigateTo(GlobalScreenKeys.RESULT_SCREEN, data)
    }

    fun goBack() {
        router.exit()
    }
}