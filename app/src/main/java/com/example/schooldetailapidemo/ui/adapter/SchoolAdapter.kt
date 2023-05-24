package com.example.schooldetailapidemo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.schooldetailapidemo.R
import com.example.schooldetailapidemo.databinding.SchoolListBinding
import com.example.schooldetailapidemo.ui.model.RootSchool


class SchoolAdapter(val context: Context, val schoolList: ArrayList<RootSchool.Data>,var callBack:(Int,ArrayList<RootSchool.Data>)->Unit) :
    RecyclerView.Adapter<SchoolAdapter.ViewHolder>() {
    class ViewHolder(var binding: SchoolListBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SchoolListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int {
        return schoolList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = schoolList[position]
        holder.binding.tvName.text = pos.name
        holder.binding.tvEmail.text = pos.email
        Glide.with(context)
            .load("http://13.233.39.120/kodris/" + schoolList[position].profilePicture)
            .placeholder(R.drawable.profile)
            .into(holder.binding.schoolImage)
        holder.itemView.setOnClickListener {
            callBack.invoke(position, schoolList)
        }
    }
}


