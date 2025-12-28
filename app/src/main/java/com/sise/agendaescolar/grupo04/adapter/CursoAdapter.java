package com.sise.agendaescolar.grupo04.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sise.agendaescolar.grupo04.R;
import com.sise.agendaescolar.grupo04.database.DatabaseHelper;
import com.sise.agendaescolar.grupo04.model.Curso;

import java.util.List;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.CursoViewHolder> {

    private Context context;
    private List<Curso> listaCursos;
    private OnCursoClickListener listener;

    // 🔹 Interface
    public interface OnCursoClickListener {
        void onCursoClick(Curso curso);
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

        // CLICK → editar
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCursoClick(curso);
            }
        });

        // LONG CLICK → eliminar
        holder.itemView.setOnLongClickListener(v -> {

            new AlertDialog.Builder(context)
                    .setTitle("Eliminar curso")
                    .setMessage("¿Deseas eliminar este curso?")
                    .setPositiveButton("Sí", (dialog, which) -> {

                        DatabaseHelper db = new DatabaseHelper(context);
                        db.deleteCurso(curso.getId());

                        listaCursos.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, listaCursos.size());
                    })
                    .setNegativeButton("No", null)
                    .show();

            return true;
        });
    }

    @Override
    public int getItemCount() {
        return listaCursos.size();
    }

    // 🔹 Actualizar lista
    public void actualizarLista(List<Curso> nuevaLista) {
        this.listaCursos = nuevaLista;
        notifyDataSetChanged();
    }

    // 🔹 ViewHolder
    static class CursoViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombreCurso, txtDescripcionCurso, txtDocenteCurso;

        public CursoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNombreCurso = itemView.findViewById(R.id.txtNombreCurso);
            txtDescripcionCurso = itemView.findViewById(R.id.txtDescripcionCurso);
            txtDocenteCurso = itemView.findViewById(R.id.txtDocenteCurso);
        }
    }
}
