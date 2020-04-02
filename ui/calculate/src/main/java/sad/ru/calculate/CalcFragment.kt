package sad.ru.calculate

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.fragment_calc.*
import sad.ru.base.base.BaseFragment
import sad.ru.base.di.ProjectComponent
import sad.ru.domain.models.BMIData
import sad.ru.numberpicker.NumberPicker

class CalcFragment : BaseFragment(), CalcView {
    @InjectPresenter
    internal lateinit var presenter: CalcPresenter

    override fun layoutId(): Int = R.layout.fragment_calc

    private lateinit var weightPicker: NumberPicker
    private lateinit var heightPicker: NumberPicker
    private lateinit var genderPicker: NumberPicker

    private lateinit var mInterstitialAd: InterstitialAd
    private lateinit var adRequest: AdRequest

    override fun createComponent(projectComponent: ProjectComponent) {
        DaggerCalcComponent.builder()
            .projectComponent(projectComponent)
            .build()
            .inject(presenter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPickers(view)
        initPickerWeight(view)
        initPickerHeight(view)
        initPickerGender(view)

        initAdRequest()
        initInterstitialAd(view)
        initAdMob()

        initCalcButton()
        initBackButton()
    }

    private fun initAdRequest() {
        adRequest = AdRequest
            .Builder()
            .build()

    }

    private fun initInterstitialAd(view: View) {
        mInterstitialAd = InterstitialAd(view.context)
        mInterstitialAd.adUnitId = "ca-app-pub-8154277548860310/5993139385"
        mInterstitialAd.loadAd(adRequest)
    }

    private fun initAdMob() {
        val ad = AdRequest.Builder().addTestDevice("F278C5B8D97EACB4C2F05700F8EDB4A1").build()
        ad_view.loadAd(ad)

        ad_view.adListener = object : AdListener() {
            override fun onAdClicked() {
//                Toast.makeText(ad_view.context, "Thanks for click on ad", Toast.LENGTH_LONG)
            }
        }
    }

    private fun initBackButton() {
        go_back.setOnClickListener { presenter.goBack() }
    }

    private fun initCalcButton() {
        btn_calc.setOnClickListener {
            val result = getResult()
            if (mInterstitialAd.isLoaded) mInterstitialAd.show()
            presenter.openResultScreen(result)
        }
    }

    private fun getResult(): BMIData {

        val p1 = weightPicker.value.toFloat()
        val p2 = heightPicker.value.toFloat()
        val p3 = genderPicker.value
        val p4 = get_name.text.toString()
        val data = BMIData(
            (p1 / ((p2 / 100) * (p2 / 100))).toString(),
            p4,
            p3,
            (p1 / ((p2 / 100) * (p2 / 100) * (p2 / 100))).toString()
        )
        return data
    }

    private fun initPickers(view: View) {
        weightPicker = view.findViewById(R.id.weight_picker)
        heightPicker = view.findViewById(R.id.height_picker)
        genderPicker = view.findViewById(R.id.gender_picker)
    }

    private fun initPickerWeight(view: View) {
        weightPicker.minValue = 1
        weightPicker.maxValue = 600
    }

    private fun initPickerHeight(view: View) {
        heightPicker.minValue = 50
        heightPicker.maxValue = 260
    }

    private fun initPickerGender(view: View) {
        genderPicker.minValue = 0
        genderPicker.maxValue = 1
        genderPicker.displayedValues = arrayOf("Male", "Female")
    }

    override fun setVarData(varData: BMIData) {
        data = varData
    }

}