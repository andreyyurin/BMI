package upwork.test.base.di.data

import dagger.Module
import upwork.test.base.di.data.prefs.PreferencesModule
import upwork.test.base.di.data.rest.RestModule

@Module(includes = [RestModule::class, PreferencesModule::class])
internal class DataModule