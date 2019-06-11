package upwork.test.bmi.splash

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.koushikdutta.ion.Ion
import com.koushikdutta.ion.builder.AnimateGifMode
import kotlinx.android.synthetic.main.fragment_splash.*
import upwork.test.base.app.ProjectApplication
import upwork.test.bmi.MainActivity
import upwork.test.bmi.R

class SplashActivity : MvpAppCompatActivity(), SplashView {


    @InjectPresenter
    internal lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)
        DaggerSplashComponent.builder()
            .projectComponent((application as ProjectApplication).projectComponent)
            .build()
            .inject(presenter)
    }
    override fun openMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        val bundle = ActivityOptionsCompat.makeCustomAnimation(
            this,
            android.R.anim.fade_in, android.R.anim.fade_out
        ).toBundle()
        startActivity(intent, bundle)
        finish()
    }
}