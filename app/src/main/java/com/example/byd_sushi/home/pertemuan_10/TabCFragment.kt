package com.example.byd_sushi.home.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.byd_sushi.databinding.FragmentTabCBinding

class TabCFragment : Fragment() {

    private var _binding: FragmentTabCBinding? = null
    private val binding get() = _binding!!

    private val productList = listOf(
        // Lip
        ProductModel("Lip Cream Velvet", "Rp 90.000", "https://picsum.photos/seed/cosmetic1/400/300"),
        ProductModel("Lip Tint Matte", "Rp 65.000", "https://picsum.photos/seed/cosmetic2/400/300"),
        ProductModel("Lip Balm Strawberry", "Rp 35.000", "https://picsum.photos/seed/cosmetic3/400/300"),
        ProductModel("Lipstik Satin Nude", "Rp 85.000", "https://picsum.photos/seed/cosmetic4/400/300"),
        ProductModel("Lip Gloss Shimmer", "Rp 55.000", "https://picsum.photos/seed/cosmetic5/400/300"),
        ProductModel("Lip Liner Brown", "Rp 30.000", "https://picsum.photos/seed/cosmetic6/400/300"),
        ProductModel("Lip Mask Night Repair", "Rp 75.000", "https://picsum.photos/seed/cosmetic7/400/300"),
        ProductModel("Lip Oil Glow", "Rp 70.000", "https://picsum.photos/seed/cosmetic8/400/300"),
        ProductModel("Lip Scrub Sugar", "Rp 45.000", "https://picsum.photos/seed/cosmetic9/400/300"),
        ProductModel("Lipstik Crayon Rose", "Rp 60.000", "https://picsum.photos/seed/cosmetic10/400/300"),

        // Skincare
        ProductModel("Serum Vitamin C", "Rp 100.000", "https://picsum.photos/seed/cosmetic11/400/300"),
        ProductModel("Serum Niacinamide", "Rp 95.000", "https://picsum.photos/seed/cosmetic12/400/300"),
        ProductModel("Serum Hyaluronic Acid", "Rp 110.000", "https://picsum.photos/seed/cosmetic13/400/300"),
        ProductModel("Toner Aloe Vera", "Rp 70.000", "https://picsum.photos/seed/cosmetic14/400/300"),
        ProductModel("Toner Rice Water", "Rp 75.000", "https://picsum.photos/seed/cosmetic15/400/300"),
        ProductModel("Facial Wash Gentle", "Rp 55.000", "https://picsum.photos/seed/cosmetic16/400/300"),
        ProductModel("Micellar Water", "Rp 80.000", "https://picsum.photos/seed/cosmetic17/400/300"),
        ProductModel("Moisturizer Gel", "Rp 85.000", "https://picsum.photos/seed/cosmetic18/400/300"),
        ProductModel("Night Cream Repair", "Rp 120.000", "https://picsum.photos/seed/cosmetic19/400/300"),
        ProductModel("Eye Cream Anti Dark Circle", "Rp 105.000", "https://picsum.photos/seed/cosmetic20/400/300"),

        // Sunscreen & Body
        ProductModel("Sunscreen SPF 50", "Rp 85.000", "https://picsum.photos/seed/cosmetic21/400/300"),
        ProductModel("Sunscreen Stick SPF 35", "Rp 95.000", "https://picsum.photos/seed/cosmetic22/400/300"),
        ProductModel("Sun Gel Waterproof", "Rp 90.000", "https://picsum.photos/seed/cosmetic23/400/300"),
        ProductModel("Body Lotion Whitening", "Rp 65.000", "https://picsum.photos/seed/cosmetic24/400/300"),
        ProductModel("Body Scrub Coffee", "Rp 60.000", "https://picsum.photos/seed/cosmetic25/400/300"),
        ProductModel("Hand Cream Shea Butter", "Rp 40.000", "https://picsum.photos/seed/cosmetic26/400/300"),
        ProductModel("Body Mist Floral", "Rp 70.000", "https://picsum.photos/seed/cosmetic27/400/300"),
        ProductModel("Sabun Mandi Cair", "Rp 50.000", "https://picsum.photos/seed/cosmetic28/400/300"),
        ProductModel("Deodorant Roll On", "Rp 35.000", "https://picsum.photos/seed/cosmetic29/400/300"),
        ProductModel("Foot Cream Repair", "Rp 45.000", "https://picsum.photos/seed/cosmetic30/400/300"),

        // Face Makeup
        ProductModel("Cushion Foundation", "Rp 130.000", "https://picsum.photos/seed/cosmetic31/400/300"),
        ProductModel("Bedak Padat Matte", "Rp 75.000", "https://picsum.photos/seed/cosmetic32/400/300"),
        ProductModel("Loose Powder Translucent", "Rp 80.000", "https://picsum.photos/seed/cosmetic33/400/300"),
        ProductModel("Concealer Cover Up", "Rp 60.000", "https://picsum.photos/seed/cosmetic34/400/300"),
        ProductModel("Blush On Peach", "Rp 65.000", "https://picsum.photos/seed/cosmetic35/400/300"),
        ProductModel("Highlighter Glow", "Rp 70.000", "https://picsum.photos/seed/cosmetic36/400/300"),
        ProductModel("Setting Spray", "Rp 85.000", "https://picsum.photos/seed/cosmetic37/400/300"),
        ProductModel("Primer Pore Blur", "Rp 90.000", "https://picsum.photos/seed/cosmetic38/400/300"),
        ProductModel("Contour Stick", "Rp 68.000", "https://picsum.photos/seed/cosmetic39/400/300"),
        ProductModel("BB Cream SPF 30", "Rp 78.000", "https://picsum.photos/seed/cosmetic40/400/300"),

        // Eye, Brow & Tools
        ProductModel("Eyeshadow Palette Nude", "Rp 125.000", "https://picsum.photos/seed/cosmetic41/400/300"),
        ProductModel("Maskara Waterproof", "Rp 60.000", "https://picsum.photos/seed/cosmetic42/400/300"),
        ProductModel("Eyeliner Liquid Black", "Rp 45.000", "https://picsum.photos/seed/cosmetic43/400/300"),
        ProductModel("Pensil Alis Coklat", "Rp 30.000", "https://picsum.photos/seed/cosmetic44/400/300"),
        ProductModel("Brow Gel Tint", "Rp 55.000", "https://picsum.photos/seed/cosmetic45/400/300"),
        ProductModel("Sheet Mask Hydrating", "Rp 15.000", "https://picsum.photos/seed/cosmetic46/400/300"),
        ProductModel("Clay Mask Charcoal", "Rp 70.000", "https://picsum.photos/seed/cosmetic47/400/300"),
        ProductModel("Makeup Remover Balm", "Rp 88.000", "https://picsum.photos/seed/cosmetic48/400/300"),
        ProductModel("Set Kuas Makeup 10pcs", "Rp 110.000", "https://picsum.photos/seed/cosmetic49/400/300"),
        ProductModel("Paket Skincare Starter", "Rp 250.000", "https://picsum.photos/seed/cosmetic50/400/300")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productAdapter = ProductAdapter(productList) { selectedItem ->
            Toast.makeText(
                requireContext(),
                "Anda memilih ${selectedItem.name}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = productAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}