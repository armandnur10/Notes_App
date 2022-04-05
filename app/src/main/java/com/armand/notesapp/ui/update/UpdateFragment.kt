package com.armand.notesapp.ui.update

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.armand.notesapp.R
import com.armand.notesapp.data.entity.Notes
import com.armand.notesapp.databinding.FragmentUpdateBinding
import com.armand.notesapp.ui.NotesViewModel
import com.armand.notesapp.utils.ExtensionFunctions.setActionBar
import com.armand.notesapp.utils.HelperFunctions.parseToPriority
import com.armand.notesapp.utils.HelperFunctions.spinnerListener
import java.text.SimpleDateFormat
import java.util.*

class UpdateFragment : Fragment() {

    private var _binding : FragmentUpdateBinding? = null
    private val binding get() = _binding as FragmentUpdateBinding

    private val saveArgs: UpdateFragmentArgs by navArgs()

    private val UpdateViewModel by viewModels<NotesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.safeArgs = saveArgs

        setHasOptionsMenu(true)

        binding.apply {
            toolbarUpdate.setActionBar(requireActivity())
            spinnerPrioritiesUpdate.onItemSelectedListener = spinnerListener(context, binding.priorityIndicator)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_save, menu)
        val action = menu.findItem(R.id.menu_save)
        action.actionView.findViewById<AppCompatImageButton>(R.id.btn_save).setOnClickListener {
            findNavController().navigate(R.id.action_updateFragment_to_detailFragment)
            Toast.makeText(context, "Note has been Update", Toast.LENGTH_SHORT).show()
            updateNote()
        }
    }

    private fun updateNote() {
        binding.apply {
            val title = edtTitleUpdate.text.toString()
            val description = edtDescriptionUpdate.text.toString()
            val priority = spinnerPrioritiesUpdate.selectedItem.toString()

            val calender = Calendar.getInstance().time
            val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(calender)

            val note = Notes(
                saveArgs.currentItem.id,
                title,
                parseToPriority(priority, context),
                description,
                date
            )
            if (edtTitleUpdate.text.isEmpty() || edtDescriptionUpdate.text.isEmpty()){
                Toast.makeText(context, "Please fill fields", Toast.LENGTH_SHORT).show()
            }
            else{
                UpdateViewModel.insertData(note)
                val action = UpdateFragmentDirections.actionUpdateFragmentToDetailFragment(note)
                findNavController().navigate(action)
                Toast.makeText(context, "Succesfully Update Note", Toast.LENGTH_SHORT).show()
                Log.i("UpdateFragment", "updateNote: $note")
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


