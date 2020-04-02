package sad.ru.result

import dagger.Component
import dagger.Module
import dagger.Provides
import sad.ru.base.di.ProjectComponent
import sad.ru.domain.models.BMIData
import javax.inject.Scope

@Scope
internal annotation class ResultScope

@ResultScope
@Component(dependencies = [ProjectComponent::class], modules = [ResultModule::class])
internal interface ResultComponent {
    fun inject(presenter: ResultPresenter)
}

@Module
internal class ResultModule(private val data: BMIData){
    @Provides
    @ResultScope
    fun provideData() = data
}