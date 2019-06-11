package upwork.test.base.di

import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import upwork.test.base.app.ProjectApplication

interface ProjectDependencies {

    fun provideProjectApplication(): ProjectApplication

    // Navigation
    fun provideRouter(): Router

    fun provideNavigatorHolder(): NavigatorHolder
}