package com.example.schooldetailapidemo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.TeacherListBinding
import com.example.schooldetailapidemo.ui.model.TeacherRoot

class TeacherAdapter(val context: Context, val teacherList: ArrayList<TeacherRoot.Data>,var callBack:(Int,ArrayList<TeacherRoot.Data>)->Unit) :
    RecyclerView.Adapter<TeacherAdapter.ViewHolder>() {
    class ViewHolder(var binding: TeacherListBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            TeacherListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return teacherList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = teacherList[position]
        holder.binding.tvName.text = pos.name
        holder.binding.tvEmail.text = pos.email

        Glide.with(context)
            .load("http://13.233.39.120/kodris/"+teacherList[position].profilePicture)
            .placeholder(R.drawable.profile)
            .into(holder.binding.teacherImage)
        holder.itemView.setOnClickListener {
            callBack.invoke(position,teacherList)
        }
    }
}