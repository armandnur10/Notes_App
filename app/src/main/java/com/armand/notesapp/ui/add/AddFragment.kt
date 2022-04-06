package com.armand.notesapp.ui.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.armand.notesapp.R
import com.armand.notesapp.data.entity.Notes
import com.armand.notesapp.data.entity.Priority
import com.armand.notesapp.databinding.FragmentAddBinding
import com.armand.notesapp.ui.NotesViewModel
import com.armand.notesapp.ui.ViewModelFactory
import com.armand.notesapp.utils.ExtensionFunctions.setActionBar
import com.armand.notesapp.utils.HelperFunctions
import com.armand.notesapp.utils.HelperFunctions.parseToPriority
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val  binding get() = _binding as FragmentAddBinding
    private var _addViewModel: NotesViewModel? = null
//    private val addViewModel by viewModels<NotesViewModel>()
    private val addViewModel get() = _addViewModel as NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        _addViewModel = activity?.let { obtainViewModel(it) }

        binding.toolbarAdd.setActionBar(requireActivity())

        binding.spinnerPriorities.onItemSelectedListener = HelperFunctions.spinnerListener(requireContext(), binding.priorityIndicator)
    }

    private fun obtainViewModel(activity: FragmentActivity): NotesViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[NotesViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_save, menu)
        val action = menu.findItem(R.id.menu_save)
        action.actionView.findViewById<AppCompatImageButton>(R.id.btn_save).setOnClickListener {
            insertNote()
        }
    }



    private fun insertNote(){
        binding.apply {
            val title = edtTitle.text.toString()
            val priority = spinnerPriorities.selectedItem.toString()
            val description = edtDescription.text.toString()
            val calender = Calendar.getInstance().time
            val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(calender)

            val note = Notes(
                0,
                title,
                parseToPriority(priority, context),
                description,
                date
            )
            if (edtTitle.text.isEmpty() || edtDescription.text.isEmpty()){
                Toast.makeText(context, "Please fill fields", Toast.LENGTH_SHORT).show()
            }
            else{
                addViewModel.insertData(note)
                findNavController().navigate(R.id.action_addFragment_to_homeFragment)
            }

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}