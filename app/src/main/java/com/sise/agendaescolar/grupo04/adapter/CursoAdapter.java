package com.sise.agendaescolar.grupo04.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sise.agendaescolar.grupo04.R;
import com.sise.agendaescolar.grupo04.model.Curso;

import java.util.List;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.CursoViewHolder> {

    private Context context;
    private List<Curso> listaCursos;
    private OnCursoClickListener listener;

    // 🔹 Interface de comunicación con la Activity
    public interface OnCursoClickListener {
        void onCursoClick(Curso curso);     // editar
        void onCursoDelete(Curso curso);    // eliminar
    }

    // 🔹 Constructor
    public CursoAdapter(Context context, List<Curso> listaCursos, OnCursoClickListener listener) {
        this.context = context;
        this.listaCursos = listaCursos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_curso, parent, false);
        return new CursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoViewHolder holder, int position) {

        Curso curso = listaCursos.get(position);

        holder.txtNombreCurso.setText(curso.getNombre());
        holder.txtDescripcionCurso.setText(curso.getDescripcion());
        holder.txtDocenteCurso.setText("Docente: " + curso.getDocente());

        // CLICK EN CARD → EDITAR
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCursoClick(curso);
            }
        });

        // BOTÓN ELIMINAR → AVISA A LA ACTIVITY
        holder.btnEliminarCurso.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCursoDelete(curso);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaCursos.size();
    }

    // 🔹 Para refrescar la lista desde la Activity
    public void actualizarLista(List<Curso> nuevaLista) {
        this.listaCursos = nuevaLista;
        notifyDataSetChanged();
    }

    // 🔹 ViewHolder
    static class CursoViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombreCurso;
        TextView txtDescripcionCurso;
        TextView txtDocenteCurso;
        Button btnEliminarCurso;

        public CursoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNombreCurso = itemView.findViewById(R.id.txtNombreCurso);
            txtDescripcionCurso = itemView.findViewById(R.id.txtDescripcionCurso);
            txtDocenteCurso = itemView.findViewById(R.id.txtDocenteCurso);
            btnEliminarCurso = itemView.findViewById(R.id.btnEliminarCurso);
        }
    }
}
