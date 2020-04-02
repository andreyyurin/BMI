package sad.ru.bmi

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command
import sad.ru.base.navigation.GlobalScreenKeys
import sad.ru.calculate.CalcFragment
import sad.ru.result.ResultFragment

internal class MainNavigator(
    activity: MainActivity,
    containerId: Int
) : SupportAppNavigator(activity, containerId) {
    override fun applyCommands(commands: Array<Command>) {
        super.applyCommands(commands)
    }

    private val activityLocal = activity

    override fun createFragment(screen: SupportAppScreen?): Fragment {
        return when (screen?.screenKey) {
            GlobalScreenKeys.CALC_SCREEN -> {
                val finalScreen = CalcFragment()
                finalScreen
            }
            GlobalScreenKeys.RESULT_SCREEN -> {
                val finalScreen = ResultFragment.newInstance(
                    ((activityLocal.supportFragmentManager.findFragmentById(R.id.main_fragment_container)) as CalcFragment).data
                )
                finalScreen
            }

            else -> throw IllegalStateException("Main navigator - unknown screen key ${screen?.screenKey}")
        }
    }
}