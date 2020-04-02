package sad.ru.numberpicker

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import sad.ru.domain.models.BMIData


class GlobalScreen {
    class ThisScreen(private val key: String, private val screen: Fragment?) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return screen
        }

        init {
            screenKey = key
        }
    }

    class ThisScreenData(private val key: String, private val screen: Fragment?, data: BMIData) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return screen
        }

        init {
            screenKey = key
        }
    }

}