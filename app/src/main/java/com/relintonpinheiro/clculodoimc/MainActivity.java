package com.relintonpinheiro.clculodoimc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editPeso = findViewById(R.id.editPeso);
        final EditText editAltura = findViewById(R.id.editAltura);
        Button btnCalcularImc = findViewById(R.id.btnCalcularImc);
        Button btnLimparTela = findViewById(R.id.btnLimparTela);
        final TextView textResp = findViewById(R.id.textResposta);

        // Botão para calcular
        btnCalcularImc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    double imc = calcIMC(Double.parseDouble(editPeso.getText().toString()), Double.parseDouble(editAltura.getText().toString()));
                    imcString(imc, textResp);
                }
                catch (Exception e) {
                    Log.d("Erro:",e.toString());
                    textResp.setText(getText(R.string.err));
                    Toast.makeText(MainActivity.this, "Preencha os campos e tente novamente.", Toast.LENGTH_LONG).show();
                }
                hideKeyBoard();
            }
        });
        // Botão limpar
        btnLimparTela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPeso.setText("");
                editAltura.setText("");
                textResp.setText("");
            }
        });
    }

    // Cálculo do IMC
    private double calcIMC(double peso, double altura){
        double resp = peso/(altura*altura);
        return resp;
    }
    // Obtem cálculo e formula resposta
    private void imcString(double calc,TextView tv){
        DecimalFormat df = new DecimalFormat("#.00");
        String resp = "IMC: "+df.format(calc)+"\n";
        if(calc < 17){
            resp+= getText(R.string.resp_1);
            tv.setTextColor(getResources().getColor(R.color.color1));
        }
        else if(calc >= 17 && calc < 18.5){
            resp+= getText(R.string.resp_2);
            tv.setTextColor(getResources().getColor(R.color.color2));
        }
        else if(calc >= 18.5 && calc < 25){
            resp+= getText(R.string.resp_3);
            tv.setTextColor(getResources().getColor(R.color.color3));
        }
        else if(calc >= 25 && calc < 30){
            resp+= getText(R.string.resp_4);
            tv.setTextColor(getResources().getColor(R.color.color4));
        }
        else if(calc >= 30 && calc < 35){
            resp+= getText(R.string.resp_5);
            tv.setTextColor(getResources().getColor(R.color.color5));
        }
        else if(calc >= 35 && calc < 40){
            resp+= getText(R.string.resp_6);
            tv.setTextColor(getResources().getColor(R.color.color6));
        }
        else {
            resp+= getText(R.string.resp_7);
            tv.setTextColor(getResources().getColor(R.color.color7));
        }
        tv.setText(resp);
    }
    // Esconde o teclado
    public void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if(view!= null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
