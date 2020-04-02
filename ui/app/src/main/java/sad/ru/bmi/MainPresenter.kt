package sad.ru.bmi

import com.arellomobile.mvp.InjectViewState
import ru.terrakok.cicerone.Router
import sad.ru.base.base.BasePresenter
import sad.ru.base.navigation.GlobalScreenKeys
import sad.ru.calculate.CalcFragment
import sad.ru.numberpicker.GlobalScreen
import javax.inject.Inject

@InjectViewState
internal class MainPresenter : BasePresenter<MainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        openStartScreen()
    }

    fun openStartScreen(){
        router.replaceScreen(GlobalScreen.ThisScreen(GlobalScreenKeys.CALC_SCREEN, CalcFragment()))
    }
}