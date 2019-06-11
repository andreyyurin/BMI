package upwork.test.base.app

import android.app.Application
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import upwork.test.base.di.DaggerProjectComponent
import upwork.test.base.di.ProjectComponent

class ProjectApplication : Application() {

    lateinit var projectComponent: ProjectComponent

    override fun onCreate() {
        super.onCreate()
        val cicerone = Cicerone.create(Router())
        initDaggerGraph(cicerone)
    }

    private fun initDaggerGraph(cicerone: Cicerone<Router>) {
        projectComponent = DaggerProjectComponent.builder()
            .provideApplication(this)
            .provideCiceroneRouter(cicerone.router)
            .provideCiceroneNavigationHolder(cicerone.navigatorHolder)
            .build()
    }
}