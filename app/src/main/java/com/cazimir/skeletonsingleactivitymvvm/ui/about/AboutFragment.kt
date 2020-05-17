package com.cazimir.skeletonsingleactivitymvvm.ui.about

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cazimir.skeletonsingleactivitymvvm.IMainActivityCallback
import com.cazimir.skeletonsingleactivitymvvm.R
import com.cazimir.skeletonsingleactivitymvvm.adapter.AboutListAdapter
import com.cazimir.skeletonsingleactivitymvvm.model.AboutItem
import com.cazimir.skeletonsingleactivitymvvm.model.AboutItemType
import com.cazimir.skeletonsingleactivitymvvm.shared.SharedViewModel
import com.cazimir.skeletonsingleactivitymvvm.ui.privacy_policy.PrivacyPolicyActivity
import com.cazimir.utilitieslibrary.shareMyApp
import com.cazimir.utilitieslibrary.showMyListingInStoreForRating
import com.cazimir.utilitieslibrary.showMyOtherApplicationsInGooglePlay
import kotlinx.android.synthetic.main.about_fragment.view.*

class AboutFragment : Fragment() {

    lateinit var aboutRecyclerView: RecyclerView
    //initialize this in onAttach so you can communicate with the hosting activity
    private var activityCallback: IMainActivityCallback? = null
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var aboutItems: List<AboutItem>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.about_fragment, container, false)
        this.aboutRecyclerView = view.about_recycler_view
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel = ViewModelProvider(activity!!).get(SharedViewModel::class.java)
        aboutItems = populateAboutItems(sharedViewModel.adsBought.value)
        aboutRecyclerView.layoutManager = LinearLayoutManager(context)
        aboutRecyclerView.adapter = AboutListAdapter(
            context!!,
            aboutItems as ArrayList<AboutItem>,
            object : AboutListAdapter.Interactor {
                override fun onItemClick(item: AboutItem) {
                    when (item.name) {

                        is AboutItemType.SendFeedback -> startSendFeedbackAction(
                            listOf("cazimir.developer@gmail.com").toTypedArray(),
                            "Feedback for ${getString(R.string.app_name)}",
                            "Your feedback helps a lot." +
                                    "\n \n What can we do to make the product better for you?\n\nYour message here:\n"
                        )
                        is AboutItemType.RemoveAds -> startRemoveAdsAction()
                        is AboutItemType.Share -> startShareAction()
                        is AboutItemType.PrivacyPolicy -> startPrivacyPolicyActivity()
                        is AboutItemType.RateApp -> startRateAppAction()
                        is AboutItemType.MoreApps -> startMoreAppsActivity()
                    }
                }
            }
        )
    }

    private fun startSendFeedbackAction(
        addresses: Array<String>, subject: String, text: String
    ) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, text)
        }
        if (intent.resolveActivity(activity?.packageManager!!) != null) {
            startActivity(intent)
        }
    }

    private fun startRemoveAdsAction() {
        activityCallback?.removeAds()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityCallback = context as IMainActivityCallback
    }

    private fun startMoreAppsActivity() {
        showMyOtherApplicationsInGooglePlay(activity as Activity)
    }

    private fun startPrivacyPolicyActivity() {
        val intent = Intent(activity, PrivacyPolicyActivity::class.java)
        startActivity(putAdsBoughExtra(intent))
    }

    private fun putAdsBoughExtra(intent: Intent): Intent {
        intent.putExtra("ads_bought", sharedViewModel.adsBought.value)
        intent.putExtra("pro_bought", sharedViewModel.proBought.value)
        return intent
    }

    private fun startRateAppAction() {
        showMyListingInStoreForRating(activity as Context)
    }

    private fun startShareAction() {
        shareMyApp(activity as Context, resources.getString(R.string.share_text))
    }

    private fun populateAboutItems(adsBought: Boolean?): List<AboutItem> {
        val aboutItems = mutableListOf<AboutItem>()

        aboutItems.add(
            AboutItem(
                AboutItemType.SendFeedback(getString(R.string.send_feedback)),
                R.drawable.ic_feedback
            )
        )

        if (!adsBought!!) {
            aboutItems.add(
                AboutItem(
                    AboutItemType.RemoveAds(getString(R.string.remove_ads)),
                    R.drawable.ic_shop_white
                )
            )
        }
        aboutItems.add(
            AboutItem(
                AboutItemType.Share(getString(R.string.share_app)),
                R.drawable.ic_share_white
            )
        )
        aboutItems.add(
            AboutItem(
                AboutItemType.PrivacyPolicy(getString(R.string.privacy_policy)),
                R.drawable.ic_info_white
            )
        )
        aboutItems.add(
            AboutItem(
                AboutItemType.RateApp(getString(R.string.rate_app)),
                R.drawable.ic_star_white
            )
        )
        aboutItems.add(
            AboutItem(
                AboutItemType.MoreApps(getString(R.string.more_apps)),
                R.drawable.ic_more_white
            )
        )
        return aboutItems as ArrayList<AboutItem>
    }

    fun hideRemoveAdsButton() {
        val adapter = aboutRecyclerView.adapter as AboutListAdapter
        adapter.removeRemoveAds()
    }

    companion object {
        private const val TAG = "AboutFragment"

        fun newInstance(): AboutFragment {
            return AboutFragment()
        }
    }
}
