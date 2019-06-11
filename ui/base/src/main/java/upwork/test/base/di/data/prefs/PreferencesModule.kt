package upwork.test.base.di.data.prefs

import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import upwork.test.base.app.ProjectApplication
import upwork.test.base.di.ProjectScope

@Module
internal class PreferencesModule {

    @ProjectScope
    @Provides
    fun providePreferences(application: ProjectApplication): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }
}