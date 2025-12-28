package com.sise.agendaescolar.grupo04;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sise.agendaescolar.grupo04.database.DatabaseHelper;
import com.sise.agendaescolar.grupo04.model.Curso;

public class EditCursoActivity extends AppCompatActivity {

    EditText etNombre, etDescripcion, etDocente;
    Button btnGuardar, btnCancelar;
    DatabaseHelper db;
    int cursoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_curso);

        // UI
        etNombre = findViewById(R.id.etNombreCurso);
        etDescripcion = findViewById(R.id.etDescripcionCurso);
        etDocente = findViewById(R.id.etDocenteCurso);
        btnGuardar = findViewById(R.id.btnGuardarCurso);
        btnCancelar = findViewById(R.id.btnCancelarCurso);

        // DB
        db = new DatabaseHelper(this);

        // Cambiar texto
        btnGuardar.setText("Actualizar curso");

        // Recibir datos
        cursoId = getIntent().getIntExtra("id", -1);
        etNombre.setText(getIntent().getStringExtra("nombre"));
        etDescripcion.setText(getIntent().getStringExtra("descripcion"));
        etDocente.setText(getIntent().getStringExtra("docente"));

        // Guardar cambios
        btnGuardar.setOnClickListener(v -> actualizarCurso());
        btnCancelar.setOnClickListener(v -> finish());
    }

    private void actualizarCurso() {
        String nombre = etNombre.getText().toString().trim();
        String descripcion = etDescripcion.getText().toString().trim();
        String docente = etDocente.getText().toString().trim();

        if (nombre.isEmpty()) {
            etNombre.setError("El nombre es obligatorio");
            return;
        }

        Curso curso = new Curso(
                cursoId,
                nombre,
                descripcion,
                docente
        );

        int filas = db.updateCurso(curso);

        if (filas > 0) {
            Toast.makeText(this, "Curso actualizado", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show();
        }
    }
}
