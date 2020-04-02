package sad.ru.base.base

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import sad.ru.base.app.ProjectApplication
import sad.ru.base.di.ProjectComponent
import javax.inject.Inject

abstract class BaseActivity : MvpAppCompatActivity(), BaseView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    protected abstract val navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        injectSelf()
    }

    private fun injectSelf() {
        val projectComponent = (application as ProjectApplication).projectComponent
        createComponent(projectComponent)
        projectComponent.inject(this)
    }

    override fun onStart() {
        super.onStart()
        // attach binders
    }

    override fun onStop() {
        // detach binders
        super.onStop()
    }



    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    protected

    abstract fun layoutId(): Int

    protected abstract fun createComponent(projectComponent: ProjectComponent)

}
