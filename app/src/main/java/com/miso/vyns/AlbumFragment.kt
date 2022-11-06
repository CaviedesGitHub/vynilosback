package com.miso.vyns

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.miso.vyns.databinding.FragmentAlbumBinding
import kotlinx.coroutines.runBlocking


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ID_ALBUM = "id"

/**
 * A simple [Fragment] subclass.
 * Use the [AlbumFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlbumFragment : Fragment() {
    private lateinit var binding: FragmentAlbumBinding
    private val viewModel: VynsViewModel by activityViewModels() //by viewModels()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var idAlbum: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            idAlbum = it.getInt(ID_ALBUM,100)
        }
        //viewModel.getVynsAlbumDet(idAlbum!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_album, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Param", "Inicio Album Detalle: @{idAlbum} "+idAlbum.toString())
        binding.lifecycleOwner = viewLifecycleOwner
        //binding.vynsAlbumDetalle = viewModel.album.value
        binding.vynsAlbumDetalle = viewModel.album.value

        binding.btnAct.setOnClickListener {
            runBlocking {
                viewModel.getVynsAlbumDet(idAlbum!! + 1)
                val navController = findNavController()
            }
        }
        binding.btnRefres.setOnClickListener {
            val navController = findNavController()
            navController.run {
                popBackStack()
                navigate(R.id.albumFragment)
            }
        }
        viewModel.album.observe(viewLifecycleOwner,
            { newAlbum ->
                Log.d("OBSERVE","OBSERVADO: "+newAlbum.id.toString())
            })
    }

    fun refrescaVista(){
        val navController = findNavController()
        navController.run {
            popBackStack()
            navigate(R.id.albumFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        //viewModel.getVynsAlbumDet(idAlbum!!)
    }

    override fun onResume() {
        super.onResume()
        //viewModel.getVynsAlbumDet(idAlbum!!)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AlbumFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AlbumFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}