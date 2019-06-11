package upwork.test.bmi

import dagger.Component
import dagger.Module
import dagger.Provides
import upwork.test.base.di.ProjectComponent
import upwork.test.domain.models.BMIData
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