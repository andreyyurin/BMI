package sad.ru.base.di.data

import dagger.Module
import sad.ru.base.di.data.prefs.PreferencesModule
import sad.ru.base.di.data.rest.RestModule

@Module(includes = [RestModule::class, PreferencesModule::class])
internal class DataModule