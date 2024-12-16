package com.creators.dec13_vm_app_anton.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.creators.dec13_vm_app_anton.R
import com.creators.dec13_vm_app_anton.data.model.Room
import com.creators.dec13_vm_app_anton.data.model.RoomResponse

class RoomAdapter(private var roomList: ArrayList<Room>
): RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {
    class RoomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val isOccupiedTextView: TextView = itemView.findViewById(R.id.isOccupied)
        val maxOccupancyTextView: TextView = itemView.findViewById(R.id.maxOccupancy)
        val roomNumberView: TextView = itemView.findViewById(R.id.roomNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_room, parent, false)
        return RoomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = roomList[position]
        holder.isOccupiedTextView.text =
            if (room.isOccupied == true) "Occupied" else "Unoccupied"
        holder.maxOccupancyTextView.text = "Max Occupancy: ${room.maxOccupancy.toString()}"
        holder.roomNumberView.text = "Room ${room.id}"
    }

    fun updateRooms(newRooms: RoomResponse) {
        roomList = newRooms
        notifyDataSetChanged()
    }
}