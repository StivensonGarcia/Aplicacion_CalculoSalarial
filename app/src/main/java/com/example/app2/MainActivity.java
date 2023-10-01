package com.example.app2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText salarioBaseEditText;
    private EditText horasExtrasEditText;
    private EditText bonificacionEditText;
    private TextView resultadoTextView;
    private Button calcularButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vinculamos las vistas con sus respectivos elementos en el código
        salarioBaseEditText = findViewById(R.id.salarioBaseEditText);
        horasExtrasEditText = findViewById(R.id.horasExtrasEditText);
        bonificacionEditText = findViewById(R.id.bonificacionEditText);
        resultadoTextView = findViewById(R.id.resultadoTextView);
        calcularButton = findViewById(R.id.calcularButton);

        // Configuramos el botón para que realice el cálculo al hacer clic
        calcularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularSalario();
            }
        });
    }

    private void calcularSalario() {
        // Obtenemos los valores ingresados por el usuario
        double salarioBase = Double.parseDouble(salarioBaseEditText.getText().toString());
        int horasExtras = Integer.parseInt(horasExtrasEditText.getText().toString());
        int bonificacion = Integer.parseInt(bonificacionEditText.getText().toString());

        // Calculamos el valor de una hora normal y el valor de las horas extras
        double valorHoraNormal = salarioBase / 192;
        double valorHoraExtra = valorHoraNormal * 1.25;
        double horasExtrasTotal = valorHoraExtra * horasExtras;
        double bonificacionTotal = bonificacion == 1 ? salarioBase * 0.05 : 0;

        // Calculamos el salario total antes de descuentos
        double salarioTotalAntesDescuentos = salarioBase + horasExtrasTotal + bonificacionTotal;

        // Aplicamos los descuentos
        double descuentoSalud = salarioTotalAntesDescuentos * 0.035;
        double descuentoPension = salarioTotalAntesDescuentos * 0.04;
        double descuentoCajaCompensacion = salarioTotalAntesDescuentos * 0.01;

        // Calculamos el salario final después de los descuentos
        double salarioFinal = salarioTotalAntesDescuentos - descuentoSalud - descuentoPension - descuentoCajaCompensacion;

        // Mostramos el resultado en la vista
        resultadoTextView.setText(String.format("%.1f", salarioFinal));
    }
}