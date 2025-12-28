package com.sise.agendaescolar.grupo04;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sise.agendaescolar.grupo04.adapter.CursoAdapter;
import com.sise.agendaescolar.grupo04.database.DatabaseHelper;
import com.sise.agendaescolar.grupo04.model.Curso;

import java.util.List;

public class CursoActivity extends AppCompatActivity {

    RecyclerView recyclerCursos;
    FloatingActionButton fabAgregar;
    CursoAdapter adapter;
    DatabaseHelper db;
    List<Curso> listaCursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);

        recyclerCursos = findViewById(R.id.recyclerCursos);
        fabAgregar = findViewById(R.id.fabAgregarCurso);

        recyclerCursos.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHelper(this);

        fabAgregar.setOnClickListener(v -> {
            Intent intent = new Intent(CursoActivity.this, AddCursoActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarCursos();
    }

    private void cargarCursos() {
        listaCursos = db.getAllCursos();

        adapter = new CursoAdapter(this, listaCursos, new CursoAdapter.OnCursoClickListener() {

            // 👉 EDITAR
            @Override
            public void onCursoClick(Curso curso) {
                Intent intent = new Intent(CursoActivity.this, EditCursoActivity.class);
                intent.putExtra("id", curso.getId());
                intent.putExtra("nombre", curso.getNombre());
                intent.putExtra("descripcion", curso.getDescripcion());
                intent.putExtra("docente", curso.getDocente());
                startActivity(intent);
            }

            // 👉 ELIMINAR (BOTÓN)
            @Override
            public void onCursoDelete(Curso curso) {
                mostrarDialogEliminar(curso);
            }
        });

        recyclerCursos.setAdapter(adapter);
    }

    private void mostrarDialogEliminar(Curso curso) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar curso")
                .setMessage("¿Deseas eliminar el curso \"" + curso.getNombre() + "\"?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    db.deleteCurso(curso.getId());
                    cargarCursos();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}
