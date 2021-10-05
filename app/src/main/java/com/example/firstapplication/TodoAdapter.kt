package com.example.firstapplication

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*


class TodoAdapter(
    private val todos: MutableList<Todo>
    ): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>()
{
        class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
       return TodoViewHolder(
           LayoutInflater.from(parent.context).inflate(
               R.layout.item_todo,parent,false
           )
       )
    }
    fun addTodo(todo:Todo){
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }

    fun deleteDoneTodos(){
        todos.removeAll { todo: Todo ->
            todo.isChecked
        }
        notifyDataSetChanged()

    }

    fun toggleStrikedThrough(tvTodoItems:TextView,isChecked:Boolean){
        if(isChecked) {
            tvTodoItems.paintFlags = tvTodoItems.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else {
            tvTodoItems.paintFlags = tvTodoItems.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }

    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
       val curTodo = todos[position]
        holder.itemView.apply {
         tvTodoItems.text = curTodo.title
          checkBox.isChecked = curTodo.isChecked
            toggleStrikedThrough(tvTodoItems,curTodo.isChecked)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikedThrough(tvTodoItems,isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }

        }

    }

    override fun getItemCount(): Int {
        return todos.size
    }
}
