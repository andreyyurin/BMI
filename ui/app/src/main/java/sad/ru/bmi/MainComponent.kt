package sad.ru.bmi

import dagger.Component
import dagger.Module
import sad.ru.base.di.ProjectComponent
import javax.inject.Scope

@Scope
internal annotation class MainScope

@MainScope
@Component(dependencies = [ProjectComponent::class], modules = [MainModule::class])
internal interface MainComponent {
    fun inject(presenter: MainPresenter)
}

@Module
internal class MainModule