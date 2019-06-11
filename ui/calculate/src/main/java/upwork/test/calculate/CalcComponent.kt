package upwork.test.calculate

import dagger.Component
import dagger.Module
import upwork.test.base.di.ProjectComponent
import javax.inject.Scope

@Scope
internal annotation class CalcScope

@CalcScope
@Component(dependencies = [ProjectComponent::class], modules = [CalcModule::class])
internal interface CalcComponent {
    fun inject(presenter: CalcPresenter)
}

@Module
internal class CalcModule