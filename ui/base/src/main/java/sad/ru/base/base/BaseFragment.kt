package sad.ru.base.base

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import sad.ru.base.app.ProjectApplication
import sad.ru.base.di.ProjectComponent
import sad.ru.domain.models.BMIData

abstract class BaseFragment : MvpAppCompatFragment(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createComponent((context!!.applicationContext as ProjectApplication).projectComponent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    protected abstract fun createComponent(projectComponent: ProjectComponent)

    protected abstract fun layoutId(): Int

    open fun shouldAskPermission(): Boolean {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1)
    }

    open lateinit var data: BMIData
}