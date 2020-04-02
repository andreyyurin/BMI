package sad.ru.base.di.data.prefs

import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import sad.ru.base.app.ProjectApplication
import sad.ru.base.di.ProjectScope

@Module
internal class PreferencesModule {

    @ProjectScope
    @Provides
    fun providePreferences(application: ProjectApplication): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }
}