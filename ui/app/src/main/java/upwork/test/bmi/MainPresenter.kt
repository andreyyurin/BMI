package upwork.test.bmi

import com.arellomobile.mvp.InjectViewState
import ru.terrakok.cicerone.Router
import upwork.test.base.base.BasePresenter
import upwork.test.base.navigation.GlobalScreenKeys
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
        router.replaceScreen(GlobalScreenKeys.CALC_SCREEN)
    }
}