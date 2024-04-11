package com.example.mytodo

import Tasks
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mytodo.databinding.FragmentAddTaskBinding

class AddTaskFragment : Fragment() {
    val args: AddTaskFragmentArgs by navArgs()
    private lateinit var binding: FragmentAddTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize the binding variable
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        // Set the title and description EditText fields with the task to edit
        // (only if it's not null)
        binding.titleInput.setText(args.taskToEdit?.title)
        binding.descriptionInput.setText(args.taskToEdit?.description)
        // Set the importance radio button with the task to edit (only if it's not null)
        when(args.taskToEdit?.importance){
            IMPORTANCE.LOW -> binding.lowRadioButton.isChecked = true
            IMPORTANCE.NORMAL -> binding.normalRadioButton.isChecked = true
            IMPORTANCE.HIGH -> binding.highRadioButton.isChecked = true
            else -> binding.normalRadioButton.isChecked = true
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.setOnClickListener { saveTask() }
    }

    private fun saveTask() {
        // Get the values from data fields on the screen
        var title: String = binding.titleInput.text.toString()
        var description: String = binding.descriptionInput.text.toString()
        val importance = when(binding.importanceGroup.checkedRadioButtonId){
            R.id.low_radioButton -> IMPORTANCE.LOW
            R.id.normal_radioButton -> IMPORTANCE.NORMAL
            R.id.high_radioButton -> IMPORTANCE.HIGH
            else -> IMPORTANCE.NORMAL
        }
        // Handle missing EditText input
        if(title.isEmpty())
            title = getString(R.string.default_task_title) + "${Tasks.list.size + 1}"
        if(description.isEmpty())
            description = getString(R.string.no_desc_msg)
        // Create a new Task item based on input values
        val taskItem = Task(
            {title + description}.hashCode().toString(),
            title,
            description,
            importance
        )
        if(!args.edit) {
            // Add the new task to the list of tasks
            Tasks.addTask(taskItem)
        }else{
            // Update the task in the list of tasks
            Tasks.updateTask(oldTask = args.taskToEdit,newTask = taskItem)
        }

        // Hide the software keyboard with InputMethodManager
        val inputMethodManager: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken,0)

        // Navigate back to the TaskListFragment, the inclusive parameter is set to false
        findNavController().popBackStack(R.id.taskListFragment, false)

    }

}