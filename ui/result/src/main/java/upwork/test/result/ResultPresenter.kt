package upwork.test.result

import com.arellomobile.mvp.InjectViewState
import ru.terrakok.cicerone.Router
import upwork.test.base.base.BasePresenter
import javax.inject.Inject

@InjectViewState
internal class ResultPresenter : BasePresenter<ResultView>() {

    @Inject
    lateinit var router: Router

    fun goBack() {
        router.exit()
    }

}