package com.cazimir.skeletonsingleactivitymvvm

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.android.billingclient.api.*
import com.cazimir.skeletonsingleactivitymvvm.model.CustomPojoClassExample
import com.cazimir.skeletonsingleactivitymvvm.shared.SharedViewModel
import com.cazimir.skeletonsingleactivitymvvm.ui.about.AboutFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : FragmentActivity(), PurchasesUpdatedListener, IMainActivityCallback {

    companion object {
        // TODO add the in app purchases in the google play console. For example:
        //  For example : https://play.google.com/apps/publish/?account=6057318625214354870#ManagedProductsSetupPlace:p=com.cazimir.relaxoo&appid=4973803007102252000
        private const val REMOVE_ADS = "remove_ads"
        private const val BUY_PRO = "buy_pro"
        private val skuListAds = listOf(REMOVE_ADS)
        private val skuListPro = listOf(BUY_PRO)
    }

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var billingClient: BillingClient
    private var doubleBackToExitPressedOnce: Boolean = false
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    // TODO: Uncomment these
//    private lateinit var adView: AdView
//    private lateinit var adUnitId: String
    private var snackBar: Snackbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        EventBus.getDefault().register(this)
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        shouldHideSplash()
        subscribeObservers()

        // checkUserPurchases()
        // TODO: Uncomment this method call if permission requests are needed.
        // checkPermissions()

        // TODO: Example replacement of fragment with Starting Fragment
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_container, AboutFragment.newInstance()).commit()

        // TODO: Remove this and call hideSplash when loading is done and main layout should be showed
        Handler().postDelayed({
            hideSplash()
        }, 2000)

    }

    /*this mehtod checks if the splashscreen has already been shown and hides it if it has.
    Used because when rotating the splashscreen shows again because of the activity being recreated*/
    private fun shouldHideSplash() {
        if (sharedViewModel.splashScreenShown) {
            hideSplash()
        }
    }

    private fun subscribeObservers() {
        // TODO: Subscribe to all relevant observers here (adsBought for example to show or hide the ads view)
    }

    private fun checkUserPurchases() {
        billingClient =
                BillingClient.newBuilder(this).enablePendingPurchases().setListener(this).build()
        billingClient.startConnection(
                object : BillingClientStateListener {
                    override fun onBillingSetupFinished(billingResult: BillingResult) {
                        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                            val purchasesResult = billingClient.queryPurchases(BillingClient.SkuType.INAPP)
                            if (purchasesResult.purchasesList.size != 0) {
                                if (purchasesResult.purchasesList[0].sku == BUY_PRO) {
                                    sharedViewModel.updateBoughtPro()
                                } else if (purchasesResult.purchasesList[0].sku == REMOVE_ADS) {
                                    sharedViewModel.updateBoughtAds()
                                }
                            }
                        }
                    }

                    override fun onBillingServiceDisconnected() {
                        // Try to restart the connection on the next request to
                        // Google Play by calling the startConnection() method.
                    }
                })
    }

    // TODO: Call this when user selects the option to remove ads or buy pro
    private fun launchFlowToRemoveAds() {
        if (billingClient.isReady) {
            val skuDetailsParams = SkuDetailsParams.newBuilder()
                    .setSkusList(skuListAds)
                    .setType(BillingClient.SkuType.INAPP)
                    .build()
            billingClient.querySkuDetailsAsync(
                    skuDetailsParams
            ) { billingResult: BillingResult, skuDetailsList: List<SkuDetails> ->
                if ((billingResult.responseCode == BillingClient.BillingResponseCode.OK &&
                                !skuDetailsList.isEmpty())) {
                    for (skuDetail: SkuDetails in skuDetailsList) {
                        val flowParams: BillingFlowParams = BillingFlowParams.newBuilder().setSkuDetails(skuDetail).build()
                        billingClient.launchBillingFlow(this, flowParams)
                    }
                } else {
//                    Log.d(TAG, "loadAllSKUs() called with: skuDetailsList is empty")
                }
            }
        } else {
//            Log.d(TAG, "loadAllSKUs() called with: billingClient is not ready")
        }
    }

    // TODO: Create a frame layout in the main_activity template: <FrameLayout
    //            android:id="@+id/adMobView"
    //            tools:visibility="visible"
    //            android:layout_width="wrap_content"
    //            android:layout_height="wrap_content" />

    // TODO: Uncomment this

//    private fun loadAds() {
//        this.adView = AdView(this)
//        adView.adSize = AdSize.SMART_BANNER
//        adView.id = View.generateViewId()
//
//        if (BuildConfig.DEBUG) {
//            // TODO: Add test and production ad ids
//            adView.adUnitId = resources.getString(androidx.lifecycle.R.string.ad_test)
//        } else {
//            adView.adUnitId = resources.getString(androidx.lifecycle.R.string.ad_prod)
//        }
//
//        adMobView.addView(adView)
//        val adRequest = AdRequest.Builder().build()
//        adView.loadAd(adRequest)
//    }

    // TODO: Callback for purchase flow
    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: List<Purchase>?) {
        val responseCode = billingResult.responseCode

        if (responseCode == BillingClient.BillingResponseCode.OK || responseCode == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
            // pro includes also remove ads
            if (purchases?.get(0)?.sku == BUY_PRO) {
                sharedViewModel.updateBoughtPro()
            } else if (purchases?.get(0)?.sku == REMOVE_ADS) {
                sharedViewModel.updateBoughtAds()
            }

        }
    }

    // TODO: Uncomment this

//    private fun checkPermissions() {
//        // TODO: Replace all 'xxx' and 'yyy' with relevant values
//        val xxxxxPermission =
//            ContextCompat.checkSelfPermission(this, Manifest.permission.XXXXXXXXXXXXX)
//        val yyyyyyPermission =
//            ContextCompat.checkSelfPermission(this, Manifest.permission.XXXXXXXXXXXXXX)
//        if (xxxxxPermission == PackageManager.PERMISSION_GRANTED && yyyyyyPermission == PackageManager.PERMISSION_GRANTED) {
//            // if both permission granted do something (update a live data object etc)
////            areWritePermissionsGranted.value = true
//        } else {
//            // TODO: show dialog explaining why we need these permission. Modify the string values that need to be shown
//            SimpleOkDialog(
//                object : ISimpleOkDialogCallback {
//                    override fun okClicked() {
//                        requestXXXXXPermissions()
//                    }
//                },
//                getString(R.string.permission_storage_denied_message),
//                getString(R.string.permission_storage_denied_title)
//            ).show(supportFragmentManager, "xxxxxPermission")
//        }
//
//    }

//    private fun requestXXXXXPermissions() {
//        // TODO: Replace 'xxx' with permissions to be requested. Also create a unique permissions request code
//        requestPermissionsGeneric(arrayOf(Manifest.permission.XXXXXXXXX, Manifest.permission.XXXXXXXXXXXX), XXXXXX_PERMISSIONS_REQ_CODE)
//    }
//
//    private fun requestPermissionsGeneric(permissions: Array<String>, requestCode: Int) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions(permissions, requestCode)
//        }
//    }

//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        // TODO: Handle all permission requests here based on the permission request code
//        when (requestCode) {
//            XXXXXX_PERMISSIONS_REQ_CODE -> {
//                handleXXXXXPermissionResult(permissions, grantResults)
//            }
//
//            YYYYYY_PERMISSIONS_REQ_CODE -> {
//                handleRYYYYYYYPermissionResult(permissions, grantResults)
//            }
//
//            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        }
//    }

//    private fun handleXXXXXPermissionResult(permissions: Array<out String>, grantResults: IntArray) {
//        // clear any left over dialog // the retainInstance thingie creates a new dialog on each rotation, therefore this hack is needed to dismiss all created dialogs
//        val dialogFragmentList: List<DialogFragment>? = supportFragmentManager.fragments.filter { it.tag == "xxxxxxxPermission" } as List<DialogFragment>
//        dialogFragmentList?.forEach {
//            it.dismiss()
//        }
//
//        val denied = grantResults.indices.filter { grantResults[it] != PackageManager.PERMISSION_GRANTED }
//
//        // all permission granted
//        if (denied.isEmpty()) {
//            // update viewModel or other data to continue with UI flow
////            areWritePermissionsGranted.value = true
//        } else {
//            permissions.forEach {
//                val showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, it)
//                // will it show again? logic for when the user clicks on do not ask again
//                if (!showRationale) {
//                    SimpleOkDialog(object : ISimpleOkDialogCallback {
//                        override fun okClicked() {
//                            // send user to settings to grant permissions there
//                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//                            val uri: Uri = Uri.fromParts("package", packageName, null)
//                            intent.data = uri
//                            startActivity(intent)
//                            finish()
//                        }
//                    }, getString(R.string.permission_denied_go_to_settings), getString(R.string.permission_xxxxx_denied_title)).show(supportFragmentManager, "xxxxxxxPermission")
//
//                } else {
//                    SimpleOkDialog(object : ISimpleOkDialogCallback {
//                        override fun okClicked() {
//                            // request permissions again
//                            requestXXXXXPermissions()
//                        }
//                    }, getString(R.string.permission_storage_denied_message), getString(R.string.permission_storage_denied_title)).show(supportFragmentManager, "xxxxxxxPermission")
//
//                }
//            }
//        }
//    }

    override fun onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true

        showMessageToUser(getString(R.string.back_exit), Snackbar.LENGTH_SHORT)

        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    // you can also set INDEFINITE on the snackbar to show the snackbar forever. this is why we save it to a variable so we can dismiss it later
    private fun showMessageToUser(message: String, length: Int) {
        snackBar = Snackbar.make(coordinator, message, length).setTextColor(Color.WHITE)
        snackBar!!.show()
    }

    override fun hideSplash() {
        splash.visibility = View.GONE
        main_layout.visibility = View.VISIBLE
        no_internet_text.visibility = View.GONE
        sharedViewModel.splashScreenShown = true
    }

    override fun removeAds() {
//        launchFlowToRemoveAds()
        TODO("Uncomment above method")
    }

    override fun logAnalyticsEvent(event: String, bundle: Bundle) {
        firebaseAnalytics.logEvent(event, bundle)
    }

    /* When calling EventBus.getDefault().post(CustomPojoClass()), it will be directed here to this method*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun exampleEventBusCallback(customPojoClass: CustomPojoClassExample) {
        // do your stuff here
    }
}