package sad.ru.base.di

import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import sad.ru.base.app.ProjectApplication

interface ProjectDependencies {

    fun provideProjectApplication(): ProjectApplication

    // Navigation
    fun provideRouter(): Router

    fun provideNavigatorHolder(): NavigatorHolder
}