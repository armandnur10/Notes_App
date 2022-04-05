package com.armand.notesapp.ui.update

import android.os.Bundle
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
        val title = binding.edtTitleUpdate.text.toString()
        val desc = binding.edtDescriptionUpdate.text.toString()
        val priority = binding.spinnerPrioritiesUpdate.selectedItem.toString()
        val date = Calendar.getInstance().time

        val formatedDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(date)

        findNavController().navigate(R.id.action_updateFragment_to_detailFragment)
        Toast.makeText(context, "Note has been Update", Toast.LENGTH_SHORT).show()

        if (title.isEmpty()){
            binding.edtTitleUpdate.error = "Please fill field"
        } else if(desc.isEmpty()){
            Toast.makeText(context, "Your note is still empty", Toast.LENGTH_LONG).show()
        } else {
            UpdateViewModel.updateNote(
                Notes(saveArgs.currentItem.id,
                title,
                parseToPriority(priority, context),
                desc,
                formatedDate
            ))
            Toast.makeText(context, "successfull add note", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_homeFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


