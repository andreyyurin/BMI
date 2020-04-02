package sad.ru.result

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.fragment_result.*
import sad.ru.base.base.BaseFragment
import sad.ru.base.di.ProjectComponent
import sad.ru.domain.models.BMIData
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


class ResultFragment : BaseFragment(), ResultView {

    companion object {
        private const val ARGS_DATA = "ResultFragment.data"

        fun newInstance(data: BMIData): ResultFragment {
            val fragment = ResultFragment()

            val args = Bundle()
            args.putSerializable(ARGS_DATA, data)
            fragment.arguments = args

            return fragment
        }
    }

    @InjectPresenter
    internal lateinit var presenter: ResultPresenter

    override fun layoutId(): Int = R.layout.fragment_result

    private lateinit var mainData: BMIData
    private lateinit var adRequest: AdRequest

    override fun createComponent(projectComponent: ProjectComponent) {
        DaggerResultComponent.builder()
            .projectComponent(projectComponent)
            .resultModule(ResultModule(arguments!!.getSerializable(ARGS_DATA) as BMIData))
            .build()
            .inject(presenter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdRequest()
        initAdMob()
        initBackButton()
        initAndSetData(view)
        initShareButton(view)
        initRateButton(view)
    }

    private fun initAdRequest() {
        adRequest = AdRequest.Builder().build()
    }

    private fun initShareButton(view: View) {
        share_btn.setOnClickListener {
            if (shouldAskPermission()) {
                val perms = arrayOf("android.permission.WRITE_EXTERNAL_STORAGE")
                val permsRequestCode = 200

                requestPermissions(perms, permsRequestCode)
            } else {
                startShare(view)
            }

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            200 -> startShare(view!!)
        }
    }

    override fun shouldAskPermission(): Boolean {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1)
    }

    private fun initRateButton(view: View) {
        rate_btn.setOnClickListener { rateApp(view) }
    }

    private fun rateApp(view: View) {
        val uri =
            Uri.parse("http://play.google.com/store/apps/details?id=" + view.context.packageName);
        val openGP = Intent(Intent.ACTION_VIEW, uri)

        startActivity(openGP)
    }

    fun forceCrash() {
        throw RuntimeException("This is a crash")
    }


    private fun initAndSetData(view: View) {
        mainData = arguments!!.get(ARGS_DATA) as BMIData

        val firstResult = algthFirstResult(mainData.result)
        val secondResult = algthSecondResult(mainData.result)

        result1.text = firstResult
        result2.text = secondResult

        po_index.text = alghtPoIndexResult(mainData.poIndex)
        message.text = algthResultMessage(mainData.result, mainData.name)
    }

    private fun alghtPoIndexResult(data: String): String {
        return "Ponderal Index: " + String.format("%.02f", data.toFloat()) + "kg/m3"
    }

    private fun algthResultMessage(data: String, name: String): String {
        if (data.toFloat() < 18.5) {
            return "HELLO " + name.toUpperCase() + ", YOU ARE UNDERWEIGHT"
        } else if (data.toFloat() < 25) {
            return "HELLO " + name.toUpperCase() + ", YOU ARE NORMAL"
        } else if (data.toFloat() < 30) {
            return "HELLO " + name.toUpperCase() + ", YOU ARE OVERWEIGHT"
        } else {
            return "HELLO " + name.toUpperCase() + ", YOU ARE OBESE"
        }
    }

    private fun algthFirstResult(data: String): String {
        var final = ""
        for (i in data) {
            if (i == '.') {
                break
            } else {
                final += i
            }
        }
        return final
    }

    private fun algthSecondResult(data: String): String {
        var final = ""
        var flag = false
        for (i in data) {
            if (i == '.') {
                flag = true
                final += i
            } else if (flag && final.length < 3) {
                final += i
            }
        }
        return final
    }

    private fun initBackButton() {
        go_back.setOnClickListener { presenter.goBack() }
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

    private fun startShare(view: View) {
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        val bitmap: Bitmap = getBitmapFromView(main_result)
        val shareIntent = Intent(Intent.ACTION_SEND)
        val byteArrayOutputStream = ByteArrayOutputStream()
        shareIntent.type = "image/jpeg"
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val saveFile =
            File(Environment.getExternalStorageDirectory().absoluteFile.toString() + "/" + "ResultImage.jpg")
        saveFile.createNewFile()

        val fileOutputStream = FileOutputStream(saveFile)
        fileOutputStream.write(byteArrayOutputStream.toByteArray())

        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/ResultImage.jpg"))
        startActivity(Intent.createChooser(shareIntent, "Share your result"))
    }

    private fun getBitmapFromView(view: View): Bitmap {
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) {
            bgDrawable.draw(canvas)
        } else {
            canvas.drawColor(view.resources.getColor(R.color.background_material_light))
        }
        view.draw(canvas)
        return returnedBitmap
    }
}