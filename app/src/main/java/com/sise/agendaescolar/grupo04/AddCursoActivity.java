package com.sise.agendaescolar.grupo04;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sise.agendaescolar.grupo04.database.DatabaseHelper;
import com.sise.agendaescolar.grupo04.model.Curso;

public class AddCursoActivity extends AppCompatActivity {

    EditText etNombre, etDescripcion, etDocente;
    Button btnGuardar, btnCancelar;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_curso);

        etNombre = findViewById(R.id.etNombreCurso);
        etDescripcion = findViewById(R.id.etDescripcionCurso);
        etDocente = findViewById(R.id.etDocenteCurso);
        btnGuardar = findViewById(R.id.btnGuardarCurso);
        btnCancelar = findViewById(R.id.btnCancelarCurso);

        db = new DatabaseHelper(this);

        btnGuardar.setOnClickListener(v -> guardarCurso());
        btnCancelar.setOnClickListener(v -> finish());
    }

    private void guardarCurso() {
        String nombre = etNombre.getText().toString().trim();
        String descripcion = etDescripcion.getText().toString().trim();
        String docente = etDocente.getText().toString().trim();

        if (nombre.isEmpty()) {
            etNombre.setError("El nombre es obligatorio");
            return;
        }

        Curso curso = new Curso();
        curso.setNombre(nombre);
        curso.setDescripcion(descripcion);
        curso.setDocente(docente);

        long resultado = db.addCurso(curso);

        if (resultado > 0) {
            Toast.makeText(this, "Curso registrado", Toast.LENGTH_SHORT).show();
            finish(); // vuelve a CursoActivity
        } else {
            Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
        }
    }
}
