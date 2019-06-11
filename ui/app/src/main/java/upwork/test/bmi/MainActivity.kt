package upwork.test.bmi

import android.os.Build
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.crashlytics.android.Crashlytics
import com.google.android.gms.ads.MobileAds
import io.fabric.sdk.android.Fabric
import ru.terrakok.cicerone.Navigator
import upwork.test.base.base.BaseActivity
import upwork.test.base.base.BaseFragment
import upwork.test.base.di.ProjectComponent
import upwork.test.base.navigation.GlobalScreenKeys
import upwork.test.calculate.CalcFragment

class MainActivity : BaseActivity(), MainView {

    @InjectPresenter
    internal lateinit var presenter: MainPresenter

    override lateinit var navigator: Navigator

    override fun layoutId(): Int = R.layout.activity_main

    override fun createComponent(projectComponent: ProjectComponent) {
        val component = DaggerMainComponent.builder()
            .projectComponent(projectComponent)
            .mainModule(MainModule())
            .build()
        component.inject(presenter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //openSplashScreen()

        initCrashlytics()
        initNavigator()
        initAdMob()

    }

    private fun initCrashlytics() {
        Fabric.with(this, Crashlytics())
    }

    private fun initAdMob() {
        MobileAds.initialize(this, "ca-app-pub-6854808716956873~1227491696")
    }

    private fun initNavigator() {
        navigator = MainNavigator(
            this, R.id.main_fragment_container,
            mapOf(
                Pair(GlobalScreenKeys.CALC_SCREEN, calcFragment)
            )
        )
    }

    private fun initFragment(tag: String, newInstance: () -> BaseFragment): BaseFragment {
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        fragment ?: apply {
            fragment = newInstance()
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, fragment, tag)
                .detach(fragment)
                .commitNow()
        }
        return fragment as BaseFragment
    }

    private val calcFragment: BaseFragment by lazy {
        initFragment(GlobalScreenKeys.CALC_SCREEN) { CalcFragment() }
    }

    private fun askPermission(): Boolean {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1)
    }
}
