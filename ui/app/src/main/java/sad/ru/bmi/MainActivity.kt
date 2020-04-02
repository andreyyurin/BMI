package sad.ru.bmi

import android.os.Build
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.gms.ads.MobileAds
import ru.terrakok.cicerone.Navigator
import sad.ru.base.base.BaseActivity
import sad.ru.base.base.BaseFragment
import sad.ru.base.di.ProjectComponent
import sad.ru.base.navigation.GlobalScreenKeys
import sad.ru.calculate.CalcFragment

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

        initNavigator()
        initAdMob()

    }


    private fun initAdMob() {
        MobileAds.initialize(this, "ca-app-pub-8154277548860310~1403351202")
    }

    private fun initNavigator() {
        navigator = MainNavigator(
            this, R.id.main_fragment_container
        )
    }

    private fun initFragment(tag: String, newInstance: () -> BaseFragment): BaseFragment {
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        fragment ?: apply {
            fragment = newInstance()
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, fragment as BaseFragment, tag)
                .detach(fragment as BaseFragment)
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
