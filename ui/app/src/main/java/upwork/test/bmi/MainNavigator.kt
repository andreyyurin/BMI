package upwork.test.bmi

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import upwork.test.base.base.BaseFragment
import upwork.test.base.navigation.GlobalScreenKeys
import upwork.test.calculate.CalcFragment
import upwork.test.domain.models.BMIData
import upwork.test.result.ResultFragment

internal class MainNavigator(
    activity: MainActivity,
    containerId: Int,
    private val bottomFragmentsMap: Map<String, BaseFragment>
) : SupportAppNavigator(activity, containerId) {


    private val fragmentManager = activity.supportFragmentManager!!
    //private val transactionAnimations = TransitionAnimations.SLIDE

    override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? {
        // add all activities that can be launched from mainActivity or it's fragments here
        return null
    }

    override fun createFragment(screenKey: String?, data: Any?): Fragment? {
        return when (screenKey) {
            GlobalScreenKeys.CALC_SCREEN -> CalcFragment()
            GlobalScreenKeys.RESULT_SCREEN -> ResultFragment.newInstance(data as BMIData)
            else -> throw IllegalStateException("Main navigator - unknown screen key: $screenKey")
        }
    }

    override fun applyCommand(command: Command?) {
        if (command is Replace) {
            attachFirstFragmentDetachOthers(
                bottomFragmentsMap[(command).screenKey]!!,
                bottomFragmentsMap.filter { it.key != command.screenKey }.values
            )
        } else super.applyCommand(command)
    }

    private fun attachFirstFragmentDetachOthers(
        attachFragment: BaseFragment,
        detachFragments: Collection<BaseFragment>
    ) {
        val transaction = fragmentManager.beginTransaction()
        detachFragments.forEach { transaction.detach(it) }
        transaction.attach(attachFragment)
        transaction.commitNow()
    }

}