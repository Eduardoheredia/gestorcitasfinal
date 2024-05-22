package com.example.gestorcitas.ui

import android.os.Build
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.gestorcitas.R
import androidx.recyclerview.widget.RecyclerView
import com.example.gestorcitas.model.Appointment
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class AppointmentAdapter
    : RecyclerView.Adapter<AppointmentAdapter.ViewHolder>() {

    var appointments = ArrayList<Appointment>()

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvAppointmentId = itemView.findViewById<TextView>(R.id.tv_id)
        val tvDoctorName = itemView.findViewById<TextView>(R.id.tv_medico)
        val tvScheduledDate = itemView.findViewById<TextView>(R.id.tv_fecha)
        val tvScheduledTime = itemView.findViewById<TextView>(R.id.tv_hora)

        val tvSpecialty = itemView.findViewById<TextView>(R.id.tv_especialidad)
        val tvDescription = itemView.findViewById<TextView>(R.id.tv_descripcion)
        val tvStatus = itemView.findViewById<TextView>(R.id.tv_status)
        val tvType = itemView.findViewById<TextView>(R.id.tv_tipo)
        val tvCreateAt = itemView.findViewById<TextView>(R.id.tv_creado_en)

        val ibExpand = itemView.findViewById<ImageButton>(R.id.id_expand)
        val linearLayoutDetails = itemView.findViewById<LinearLayout>(R.id.linear_layout_detalles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_appointment,parent,false)
        )

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment = appointments[position]

        holder.tvAppointmentId.text = "Cita Taller N#${appointment.id}"
        holder.tvDoctorName.text = appointment.doctor.name
        holder.tvScheduledDate.text = "Atencion el dia ${appointment.scheduledDate}"
        holder.tvScheduledTime.text = "A las ${appointment.scheduledTime}"

        holder.tvSpecialty.text = appointment.specialty.name
        holder.tvDescription.text = appointment.description
        holder.tvStatus.text = appointment.status
        holder.tvType.text = appointment.type

        val fecha = appointment.createdAt
        val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
        val dateString = OffsetDateTime.parse(fecha)
        val formatFecha: String = dateString.format(dateFormatter)

        holder.tvCreateAt.text = "La cita se registro el dia ${formatFecha} con las siguientes fallas:"

        holder.ibExpand.setOnClickListener {
            TransitionManager.beginDelayedTransition(holder.linearLayoutDetails as ViewGroup, AutoTransition())
            if (holder.linearLayoutDetails.visibility == View.VISIBLE){
                holder.linearLayoutDetails.visibility == View.GONE
                holder.ibExpand.setImageResource(R.drawable.ic_expand_more_)
            }else{
                holder.linearLayoutDetails.visibility = View.VISIBLE
                holder.ibExpand.setImageResource(R.drawable.ic_expand_less)
            }
        }
    }

    override fun getItemCount() = appointments.size
    }


