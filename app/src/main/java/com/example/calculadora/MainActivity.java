package com.example.calculadora;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    double primeiroNumero;
    String operacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btnSomar = findViewById(R.id.btnSomar);
        Button btnSubtrair = findViewById(R.id.btnSubtrair);
        Button btnMultiplicar = findViewById(R.id.btnMultiplicar);
        Button btnDividir = findViewById(R.id.btnDividir);
        Button btnIgual = findViewById(R.id.btnIgual);
        Button btnPonto = findViewById(R.id.btnPonto);
        Button btnOff = findViewById(R.id.btnOff);
        Button btnOn = findViewById(R.id.btnOn);
        Button btnAc = findViewById(R.id.btnAc);
        Button btnDeletar = findViewById(R.id.btnDeletar);

        TextView visor = findViewById(R.id.txtVisor);

        btnOff.setOnClickListener(view -> visor.setVisibility(View.INVISIBLE));
        btnOn.setOnClickListener(view -> {
                    visor.setVisibility(View.VISIBLE);
                    visor.setText("0");
                });

        btnAc.setOnClickListener(view -> {
            primeiroNumero = 0;
            visor.setText("0");
        });

        ArrayList<Button> nums = new ArrayList<>();
        nums.add(btn1);
        nums.add(btn2);
        nums.add(btn3);
        nums.add(btn4);
        nums.add(btn5);
        nums.add(btn6);
        nums.add(btn7);
        nums.add(btn8);
        nums.add(btn9);
        nums.add(btn0);

        for (Button b: nums){
            b.setOnClickListener(view -> {
                if (!visor.getText().toString().equals("0")){
                    visor.setText(visor.getText().toString() + b.getText().toString());
                }else{
                    visor.setText(b.getText().toString());
                }
            });
        }

        ArrayList<Button> operadores = new ArrayList<>();
        operadores.add(btnSomar);
        operadores.add(btnSubtrair);
        operadores.add(btnMultiplicar);
        operadores.add(btnDividir);

        for (Button b: operadores){
            b.setOnClickListener(view -> {
                primeiroNumero = Double.parseDouble(visor.getText().toString());
                operacao = b.getText().toString();

                visor.setText("0");

            });
        }

        btnDeletar.setOnClickListener(view ->{
            String num = visor.getText().toString();
            if (num.length() > 1){
                visor.setText(num.substring(0, num.length()-1));
            }else if(num.length() == 1 && !num.equals("0")){
                visor.setText("0");
            }
        });

        btnPonto.setOnClickListener(view -> {
            if (!visor.getText().toString().contains(".")){
                visor.setText(visor.getText().toString() + ".");
            }
        });

        btnIgual.setOnClickListener(view -> {
            double segundoNumero = Double.parseDouble(visor.getText().toString());
            double resultado;
            switch (operacao){
                case "/":
                    resultado = primeiroNumero / segundoNumero;
                    break;
                case "X":
                    resultado = primeiroNumero * segundoNumero;
                    break;
                case "+":
                    resultado = primeiroNumero+ segundoNumero;
                    break;
                case "-":
                    resultado = primeiroNumero - segundoNumero;
                    break;
                default:
                    resultado = primeiroNumero + segundoNumero;
            }
            visor.setText(String.valueOf(resultado));
            primeiroNumero = resultado;
        });
    }
}