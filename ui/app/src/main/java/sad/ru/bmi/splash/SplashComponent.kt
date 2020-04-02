package sad.ru.bmi.splash

import dagger.Component
import dagger.Module
import sad.ru.base.di.ProjectComponent
import javax.inject.Scope

@Scope
internal annotation class SplashScope

@SplashScope
@Component(dependencies = [ProjectComponent::class], modules = [SplashModule::class])
internal interface SplashComponent {
    fun inject(presenter: SplashPresenter)
}

@Module
class SplashModule