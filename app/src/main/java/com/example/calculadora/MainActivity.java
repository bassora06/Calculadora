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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button number1, number2, number3, number4, number5, number6, number7, number8, number9, number0, dividir, limpar,
            somar, subtrair, igual, apagar, virgula, multiplicar;

    private TextView txtResultado, txtEspressao;

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

        iniciarComponentes();

        number0.setOnClickListener(this);
        number1.setOnClickListener(this);
        number2.setOnClickListener(this);
        number3.setOnClickListener(this);
        number4.setOnClickListener(this);
        number5.setOnClickListener(this);
        number6.setOnClickListener(this);
        number7.setOnClickListener(this);
        number8.setOnClickListener(this);
        number9.setOnClickListener(this);
        somar.setOnClickListener(this);
        dividir.setOnClickListener(this);
        multiplicar.setOnClickListener(this);
        subtrair.setOnClickListener(this);
        virgula.setOnClickListener(this);

        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtEspressao.setText("");
                txtResultado.setText("");
            }
        });

        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView expressao = findViewById(R.id.txtEspressao);
                String string = expressao.getText().toString();

                if(!string.isEmpty()){
                    byte var0 = 0;
                    int var1 = string.length()-1;
                    String txtExpressao = string.substring(var0, var1);
                    expressao.setText(txtExpressao);
                }
                txtResultado.setText("");
            }
        });

        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Expression expressao = new ExpressionBuilder(txtEspressao.getText().toString()).build();
                double resultado = expressao.evaluate();
                long longResult = (long) resultado;

                if(resultado == (double)longResult){
                    txtResultado.setText((CharSequence) String.valueOf(longResult));
                }else{
                    txtResultado.setText((CharSequence) String.valueOf(resultado));
                }
            }
        });
    }

    private void iniciarComponentes(){
        number0 = findViewById(R.id.btn0);
        number1 = findViewById(R.id.btn1);
        number2 = findViewById(R.id.btn2);
        number3 = findViewById(R.id.btn3);
        number4 = findViewById(R.id.btn4);
        number5 = findViewById(R.id.btn5);
        number6 = findViewById(R.id.btn6);
        number7 = findViewById(R.id.btn7);
        number8 = findViewById(R.id.btn8);
        number9 = findViewById(R.id.btn9);
        somar = findViewById(R.id.btnSomar);
        dividir = findViewById(R.id.btnDividir);
        multiplicar = findViewById(R.id.btnMultiplicar);
        subtrair = findViewById(R.id.btnSubtrair);
        limpar = findViewById(R.id.btnC);
        apagar = findViewById(R.id.btnApagar);
        igual = findViewById(R.id.btnIgual);
        virgula = findViewById(R.id.btnVirgula);
        txtEspressao = findViewById(R.id.txtEspressao);
        txtResultado = findViewById(R.id.txtResultado);

    }

    public void acrescrentarUmaExpressao(String string,boolean limparDados){
        if(txtResultado.getText().equals("")){
            txtEspressao.setText(" ");
        }

        if(limparDados){
            txtResultado.setText(" ");
            txtEspressao.append(string);

        }else{
            txtEspressao.append(txtResultado.getText());
            txtEspressao.append(string);
            txtResultado.setText(" ");
        }
    }

    @Override
    public void onClick(View view) {
            if(view.getId() == R.id.btn0){
                acrescrentarUmaExpressao("0", true);
            }else if(view.getId() == R.id.btn1){
                acrescrentarUmaExpressao("1", true);
            }else if (view.getId() == R.id.btn2){
                acrescrentarUmaExpressao("2", true);
            }else if (view.getId() == R.id.btn3){
                acrescrentarUmaExpressao("3", true);
            }else if (view.getId() == R.id.btn4){
                acrescrentarUmaExpressao("4", true);
            }else if (view.getId() == R.id.btn5){
                acrescrentarUmaExpressao("5", true);
            }else if (view.getId() == R.id.btn6){
                acrescrentarUmaExpressao("6", true);
            }else if (view.getId() == R.id.btn7){
                acrescrentarUmaExpressao("7", true);
            }else if (view.getId() == R.id.btn8){
                acrescrentarUmaExpressao("8", true);
            }else if (view.getId() == R.id.btn9){
                acrescrentarUmaExpressao("9", true);
            }else if (view.getId() == R.id.btnVirgula){
                acrescrentarUmaExpressao(".", true);
            }else if (view.getId() == R.id.btnSomar){
                acrescrentarUmaExpressao("+", false);
            }else if (view.getId() == R.id.btnSubtrair){
                acrescrentarUmaExpressao("-", false);
            }else if (view.getId() == R.id.btnMultiplicar){
                acrescrentarUmaExpressao("*", false);
            }else if (view.getId() == R.id.btnDividir){
                acrescrentarUmaExpressao("/", false);
            }
    }
}