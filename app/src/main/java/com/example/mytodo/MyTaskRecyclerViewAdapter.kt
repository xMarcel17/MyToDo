import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.R
import com.example.mytodo.databinding.FragmentTaskItemBinding

// Adapter is responsible for managing the display of the list –binding data with the views
class MyTaskRecyclerViewAdapter(
    private val values: List<Task>
): RecyclerView.Adapter<MyTaskRecyclerViewAdapter.ViewHolder>()
{
    // The ViewHolder class is a container for the views in the recycler view item
    class ViewHolder(binding: FragmentTaskItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        val imgView: ImageView = binding.itemImg
        val contentView: TextView = binding.content
        val itemContainer: View = binding.root
        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    // onCreateViewHolder creates the ViewHolder objects
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyTaskRecyclerViewAdapter.ViewHolder {
        // create the view holders for the recycler view items
        // no data is bound to the views yet
        return ViewHolder(
            FragmentTaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyTaskRecyclerViewAdapter.ViewHolder, position: Int) {
        // The method binds (connects) the data to the views in the view holder visible to the user

        val task = values[position] // get the task at the current position

        // select the drawable resource for the image view based on the importance of the task
        val importanceImgae = when(task.importance){
            IMPORTANCE.LOW -> R.drawable.circle_drawable_green
            IMPORTANCE.NORMAL -> R.drawable.circle_drawable_orange
            IMPORTANCE.HIGH -> R.drawable.circle_drawable_red
        }

        // set the image view and text view with the task data
        holder.imgView.setImageResource(importanceImgae)
        holder.contentView.text = task.title
    }

    override fun getItemCount(): Int {
        return values.size
    }

}