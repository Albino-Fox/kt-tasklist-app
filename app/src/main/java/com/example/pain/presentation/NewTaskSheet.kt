package com.example.pain.presentation

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.pain.R
import com.example.pain.TaskApp
import com.example.pain.databinding.FragmentNewTaskSheetBinding
import com.example.pain.data.Task
import com.example.pain.presentation.components.TaskViewModelFactory
import com.example.pain.presentation.components.TaskViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalDateTime

class NewTaskSheet(var task: Task?) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNewTaskSheetBinding
    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((requireActivity().application as TaskApp).repo)
    }
    private var dueDateTime: LocalDateTime? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (task != null) {
            binding.taskTitle.text = getString(R.string.editTask)

            val editable = Editable.Factory.getInstance()

            binding.name.text = editable.newEditable(task!!.name)
            binding.desc.text = editable.newEditable(task!!.desc)

            if (task!!.dueDateTime() != null) {
                dueDateTime = task!!.dueDateTime()!!
                updateTimeButtonText()
            }
        } else {
            binding.deleteButton.isVisible = false
            binding.taskTitle.text = getString(R.string.createNewTaskTitle)
        }

        binding.deleteButton.setOnClickListener{
            taskViewModel.deleteTask(task!!)
            dismiss()
        }

        binding.saveButton.setOnClickListener {
            if (task != null) {
                taskViewModel.updateTask(task!!, binding, dueDateTime)
            } else {
                taskViewModel.addTask(binding, dueDateTime)
            }
            dismiss()
        }
        binding.timePickerInput.setOnClickListener{
            if (dueDateTime == null)
                dueDateTime = LocalDateTime.now()
            openDateTimePicker()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        return dialog
    }

    private fun openDateTimePicker() {
        val datePickerDialog = DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
            val timePickerDialog = TimePickerDialog(requireContext(), { _, hour, minute ->
                dueDateTime = LocalDateTime.of(year, month + 1, dayOfMonth, hour, minute)
                updateTimeButtonText()
            }, dueDateTime!!.hour, dueDateTime!!.minute, true)
            timePickerDialog.setTitle("Установите время")
            timePickerDialog.show()
        }, dueDateTime!!.year, dueDateTime!!.monthValue - 1, dueDateTime!!.dayOfMonth)

        datePickerDialog.setTitle("Установите дату")
        datePickerDialog.show()
    }

    private fun updateTimeButtonText() {
        val dateTimeText = "${dueDateTime!!.toLocalDate()} ${String.format("%02d:%02d", dueDateTime!!.hour, dueDateTime!!.minute)}"
        binding.timePickerInput.setText(dateTimeText)
    }
}
