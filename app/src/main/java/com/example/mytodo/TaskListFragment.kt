package com.example.mytodo
import MyTaskRecyclerViewAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytodo.databinding.FragmentTaskListBinding

class TaskListFragment : Fragment(){
    // connect the fragment_task_list.xml with TaskListFragment class
    private lateinit var binding: FragmentTaskListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTaskListBinding.inflate(inflater, container, false)
        // Set the adapter and layout manager for the RecyclerView
        // "with" is a Kotlin extension function that allows you to call
        // the methods of an object without explicitly calling the object itself
        with(binding.list) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyTaskRecyclerViewAdapter(
                Tasks.list
            ) // adapter is responsible for displaying the data
        }
        return binding.root
    }
}