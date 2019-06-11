package upwork.test.bmi.splash

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@InjectViewState
internal class SplashPresenter : MvpPresenter<SplashView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        openMainScreen()
    }

    fun openMainScreen(){
        GlobalScope.launch {
            Thread.sleep(2000)
            viewState.openMainScreen()
        }
    }

}
