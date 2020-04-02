package sad.ru.base.di

import dagger.BindsInstance
import dagger.Component
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import sad.ru.base.app.ProjectApplication
import sad.ru.base.base.BaseActivity
import sad.ru.base.di.data.DataDependencies
import sad.ru.base.di.data.DataModule

@ProjectScope
@Component(modules = [DataModule::class])
interface ProjectComponent : ProjectDependencies, DataDependencies {
    fun inject(activity: BaseActivity)
    @Component.Builder
    interface Builder {
        fun build(): ProjectComponent

        @BindsInstance
        fun provideApplication(application: ProjectApplication): Builder

        @BindsInstance
        fun provideCiceroneRouter(router: Router): Builder

        @BindsInstance
        fun provideCiceroneNavigationHolder(navigationHolder: NavigatorHolder): Builder
    }
}

