package sad.ru.result

import com.arellomobile.mvp.InjectViewState
import ru.terrakok.cicerone.Router
import sad.ru.base.base.BasePresenter
import javax.inject.Inject

@InjectViewState
internal class ResultPresenter : BasePresenter<ResultView>() {

    @Inject
    lateinit var router: Router

    fun goBack() {
        router.exit()
    }

}