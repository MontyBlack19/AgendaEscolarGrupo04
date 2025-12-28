package com.sise.agendaescolar.grupo04;

import android.content.Intent;
import android.os.Bundle;

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

    // 🔄 Se ejecuta cada vez que vuelves a esta pantalla
    @Override
    protected void onResume() {
        super.onResume();
        cargarCursos();
    }

    private void cargarCursos() {
        listaCursos = db.getAllCursos();

        adapter = new CursoAdapter(
                this,
                listaCursos,
                curso -> {
                    Intent intent = new Intent(CursoActivity.this, EditCursoActivity.class);
                    intent.putExtra("id", curso.getId());
                    intent.putExtra("nombre", curso.getNombre());
                    intent.putExtra("descripcion", curso.getDescripcion());
                    intent.putExtra("docente", curso.getDocente());
                    startActivity(intent);
                }
        );

        recyclerCursos.setAdapter(adapter);
    }
}
