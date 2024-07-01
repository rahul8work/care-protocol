package com.onchain.careprotocol.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.onchain.careprotocol.HomeActivity
import com.onchain.careprotocol.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.textDashboard.setOnClickListener {
            showDetailsDialog()
        }




        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showDetailsDialog(){
        val dialog = AlertDialog.Builder(this.requireActivity())
            .setTitle("Details on donation method")
            .setMessage("Your donation will be received as a crypto token which will be transferred to Care Protocol wallet. " +
                    "You will receive a nft of appreciation on Coinbase smart wallet. \n" +
                    "If you already have coinbase smart walllet it will be automatically used. otherwise new wallet will be created.")
            .setPositiveButton("OK") { dialog,  id ->
                (this.requireActivity() as HomeActivity).presentPaymentSheet()
            }
            .setNegativeButton("Cancel") {dialog, id ->
                dialog.dismiss()
            }
            .show()
    }



}