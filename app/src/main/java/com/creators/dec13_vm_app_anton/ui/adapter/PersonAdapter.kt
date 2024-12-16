package com.creators.dec13_vm_app_anton.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.creators.dec13_vm_app_anton.R
import com.creators.dec13_vm_app_anton.data.model.Person
import com.creators.dec13_vm_app_anton.data.model.People

class PersonAdapter(private var personList: ArrayList<Person>
        ): RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {
    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstNameTextView: TextView = itemView.findViewById(R.id.firstNameText)
        val lastNameTextView: TextView = itemView.findViewById(R.id.lastNameText)
        val avatarView: ImageView = itemView.findViewById(R.id.avatarView)
        val viewDetailsButton: Button = itemView.findViewById(R.id.viewDetailBtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = personList[position]
        holder.firstNameTextView.text = person.firstName
        holder.lastNameTextView.text = person.lastName

        Glide.with(holder.avatarView.context)
            .load(person.avatar)
            .placeholder(R.drawable.person)
            .into(holder.avatarView)

        holder.viewDetailsButton.setOnClickListener{
            val bundle = bundleOf("personId" to person.id)
            val navController = Navigation.findNavController(holder.itemView)
            navController.navigate(R.id.action_peopleListFragment_to_personDetailFragment, bundle)
        }
    }

    fun updateData(newData: People) {
        personList = newData
        notifyDataSetChanged()
    }
}