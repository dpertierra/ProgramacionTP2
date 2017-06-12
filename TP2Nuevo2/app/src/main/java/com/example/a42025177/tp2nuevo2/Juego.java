package com.example.a42025177.tp2nuevo2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Juego extends AppCompatActivity {
    int movs = 0;
    int Record;
    boolean responder;
    baseTP3SQLiteHelper accesoabase;
    SQLiteDatabase basededatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        AsignarImagenesalAzar();
        Bundle DatosRecibidos;
        DatosRecibidos = this.getIntent().getExtras();
        String Nombre = DatosRecibidos.getString("Nombreingresado");
        String Saludo = DatosRecibidos.getString("Saludo");
        Record = DatosRecibidos.getInt("Record");
        TextView nombrer = (TextView)findViewById(R.id.txtNombrer);
        nombrer.setText(Saludo + " " + Nombre);
        Toast.makeText(this, "Su record es " + Record, Toast.LENGTH_LONG).show();
    }
    public void AsignarImagenesalAzar()//asigno las imagenes a los botones
    {
        int sumatotalbotones = 0;
        boolean volveragenerar = true;
        while (volveragenerar==true)
        {
            for (int i = 0; i<=8; i++)
            {
                switch (i){
                    case 0:
                        sumatotalbotones+= AsignarImagenaBoton(R.id.Botonfoto1);
                        break;
                    case 1:
                        sumatotalbotones+= AsignarImagenaBoton(R.id.Botonfoto2);
                        break;
                    case 2:
                        sumatotalbotones+= AsignarImagenaBoton(R.id.Botonfoto3);
                        break;
                    case 3:
                        sumatotalbotones+= AsignarImagenaBoton(R.id.Botonfoto4);
                        break;
                    case 4:
                        sumatotalbotones+= AsignarImagenaBoton(R.id.Botonfoto5);
                        break;
                    case 5:
                        sumatotalbotones+= AsignarImagenaBoton(R.id.Botonfoto6);
                        break;
                    case 6:
                        sumatotalbotones+= AsignarImagenaBoton(R.id.Botonfoto7);
                        break;
                    case 7:
                        sumatotalbotones+= AsignarImagenaBoton(R.id.Botonfoto8);
                        break;
                    case 8:
                        sumatotalbotones+= AsignarImagenaBoton(R.id.Botonfoto9);
                        break;

                }
                if (sumatotalbotones != 0 && sumatotalbotones !=9)
                {
                    volveragenerar= false;
                }
            }
        }
    }
    public void gane()
    {
        Drawable.ConstantState codigoticverde;
        codigoticverde= ContextCompat.getDrawable(this, R.drawable.ticverde).getConstantState();
        Drawable.ConstantState cruzroja;
        cruzroja= ContextCompat.getDrawable(this, R.drawable.cruzroja).getConstantState();
        ImageButton btnimg1;
        btnimg1 = (ImageButton) findViewById(R.id.Botonfoto1);
        ImageButton btnimg2;
        btnimg2 = (ImageButton) findViewById(R.id.Botonfoto2);
        ImageButton btnimg3;
        btnimg3 = (ImageButton) findViewById(R.id.Botonfoto3);
        ImageButton btnimg4;
        btnimg4 = (ImageButton) findViewById(R.id.Botonfoto4);
        ImageButton btnimg5;
        btnimg5 = (ImageButton) findViewById(R.id.Botonfoto5);
        ImageButton btnimg6;
        btnimg6 = (ImageButton) findViewById(R.id.Botonfoto6);
        ImageButton btnimg7;
        btnimg7 = (ImageButton) findViewById(R.id.Botonfoto7);
        ImageButton btnimg8;
        btnimg8 = (ImageButton) findViewById(R.id.Botonfoto8);
        ImageButton btnimg9;
        btnimg9 = (ImageButton) findViewById(R.id.Botonfoto9);
       if (btnimg1.getDrawable().getConstantState()==codigoticverde && btnimg2.getDrawable().getConstantState()== codigoticverde &&
               btnimg3.getDrawable().getConstantState()==codigoticverde && btnimg4.getDrawable().getConstantState()==codigoticverde &&
               btnimg5.getDrawable().getConstantState()==codigoticverde && btnimg6.getDrawable().getConstantState()==codigoticverde &&
               btnimg7.getDrawable().getConstantState()==codigoticverde && btnimg8.getDrawable().getConstantState()==codigoticverde &&
               btnimg9.getDrawable().getConstantState()==codigoticverde)
        {
            Button btnvolverajugar = (Button)findViewById(R.id.btnvolverajugar);
            Bundle DatosRecibidos;
            DatosRecibidos = this.getIntent().getExtras();
            String Nombre = DatosRecibidos.getString("Nombreingresado");
            TextView ganaste = (TextView)findViewById(R.id.txtganaste);
            ganaste.setText("¡Felicitaciones " + Nombre + " Ganaste!" );
            btnimg1.setEnabled(false);
            btnimg2.setEnabled(false);
            btnimg3.setEnabled(false);
            btnimg4.setEnabled(false);
            btnimg5.setEnabled(false);
            btnimg6.setEnabled(false);
            btnimg7.setEnabled(false);
            btnimg8.setEnabled(false);
            btnimg9.setEnabled(false);
            if (Record > 0 && Record > movs)
            {
                inicializarbase();
                if (responder == true)
                {
                    String strSQL = "UPDATE jugadores SET Record ='" + movs + "'WHERE Nombre='"+ Nombre;
                    basededatos.execSQL(strSQL);
                }
                basededatos.close();
            }
            btnvolverajugar.setVisibility(View.VISIBLE);
        }

        else if (btnimg1.getDrawable().getConstantState()==cruzroja && btnimg2.getDrawable().getConstantState()== cruzroja &&
               btnimg3.getDrawable().getConstantState()==cruzroja && btnimg4.getDrawable().getConstantState()==cruzroja &&
               btnimg5.getDrawable().getConstantState()==cruzroja && btnimg6.getDrawable().getConstantState()==cruzroja &&
               btnimg7.getDrawable().getConstantState()==cruzroja && btnimg8.getDrawable().getConstantState()==cruzroja &&
               btnimg9.getDrawable().getConstantState()==cruzroja)
        {
            Button btnvolverajugar = (Button)findViewById(R.id.btnvolverajugar);
            Bundle DatosRecibidos;
            DatosRecibidos = this.getIntent().getExtras();
            String Nombre = DatosRecibidos.getString("Nombreingresado");
            TextView ganaste = (TextView)findViewById(R.id.txtganaste);
            ganaste.setText("¡Felicitaciones " + Nombre + " Ganaste!" );
            btnimg1.setEnabled(false);
            btnimg2.setEnabled(false);
            btnimg3.setEnabled(false);
            btnimg4.setEnabled(false);
            btnimg5.setEnabled(false);
            btnimg6.setEnabled(false);
            btnimg7.setEnabled(false);
            btnimg8.setEnabled(false);
            btnimg9.setEnabled(false);
            if (Record > 0 && movs < Record)
            {
                inicializarbase();
                if (responder == true)
                {
                    String strSQL = "UPDATE jugadores SET Record ='" + movs + "'WHERE Nombre='"+ Nombre;
                    basededatos.execSQL(strSQL);
                }
                basededatos.close();
            }
            btnvolverajugar.setVisibility(View.VISIBLE);
        }
    }
    public boolean inicializarbase()
    {
        basededatos = accesoabase.getWritableDatabase();
        if (basededatos!=null)
        {
            responder = true;
        }
        else {
            responder = false;
        }
        return responder;
    }
    public void Volverajugar(View vista)
    {
        Intent Ir;
        Ir = new Intent(this, MainActivity.class);
        startActivity(Ir);
    }

    int AsignarImagenaBoton(int iddelBoton)
    {
        Random alazar = new Random();
        int numeroimagen;
        numeroimagen = alazar.nextInt(2);
        ImageButton BotonaCambiar;
        BotonaCambiar = (ImageButton)findViewById(iddelBoton);
        if (numeroimagen==0)
        {
            BotonaCambiar.setImageResource(R.drawable.ticverde);
        }
        else
        {
            BotonaCambiar.setImageResource(R.drawable.cruzroja);
        }
        return numeroimagen;
    }
    public void PresionaBoton(View VistaPresionada)// detecto que boton se presiono
    {
        ImageButton BotonPresionado;
        BotonPresionado = (ImageButton) VistaPresionada;
        String tagBotonPresionado;
        tagBotonPresionado = BotonPresionado.getTag().toString();
        int NumeroBoton;
        NumeroBoton = Integer.parseInt(tagBotonPresionado);
        movs++;
        TextView Movimientos;
        Movimientos = (TextView) findViewById(R.id.txtmov);
        Movimientos.setText("Movimientos: " + movs);


        //Declaracion botones
        ImageButton btnimg1;
        btnimg1 = (ImageButton) findViewById(R.id.Botonfoto1);
        ImageButton btnimg2;
        btnimg2 = (ImageButton) findViewById(R.id.Botonfoto2);
        ImageButton btnimg3;
        btnimg3 = (ImageButton) findViewById(R.id.Botonfoto3);
        ImageButton btnimg4;
        btnimg4 = (ImageButton) findViewById(R.id.Botonfoto4);
        ImageButton btnimg5;
        btnimg5 = (ImageButton) findViewById(R.id.Botonfoto5);
        ImageButton btnimg6;
        btnimg6 = (ImageButton) findViewById(R.id.Botonfoto6);
        ImageButton btnimg7;
        btnimg7 = (ImageButton) findViewById(R.id.Botonfoto7);
        ImageButton btnimg8;
        btnimg8 = (ImageButton) findViewById(R.id.Botonfoto8);
        ImageButton btnimg9;
        btnimg9 = (ImageButton) findViewById(R.id.Botonfoto9);


        switch (NumeroBoton) //cambio las imagenes de los botones correspondientes
        {
            case (0):
                InvertirImagen(btnimg1);
                InvertirImagen(btnimg2);
                InvertirImagen(btnimg4);
                InvertirImagen(btnimg5);
                gane();
                break;

            case (1):
                InvertirImagen(btnimg1);
                InvertirImagen(btnimg2);
                InvertirImagen(btnimg3);
                InvertirImagen(btnimg5);
                gane();
                break;

            case (2):
                InvertirImagen(btnimg2);
                InvertirImagen(btnimg3);
                InvertirImagen(btnimg5);
                InvertirImagen(btnimg6);
                gane();
                break;

            case (3):
                InvertirImagen(btnimg1);
                InvertirImagen(btnimg4);
                InvertirImagen(btnimg5);
                InvertirImagen(btnimg7);
                gane();
                break;

            case (4):
                InvertirImagen(btnimg2);
                InvertirImagen(btnimg4);
                InvertirImagen(btnimg5);
                InvertirImagen(btnimg6);
                InvertirImagen(btnimg8);
                gane();
                break;

            case (5):
                InvertirImagen(btnimg3);
                InvertirImagen(btnimg5);
                InvertirImagen(btnimg6);
                InvertirImagen(btnimg9);
                gane();
                break;

            case (6):
                InvertirImagen(btnimg4);
                InvertirImagen(btnimg5);
                InvertirImagen(btnimg7);
                InvertirImagen(btnimg8);
                break;

            case (7):
                InvertirImagen(btnimg5);
                InvertirImagen(btnimg7);
                InvertirImagen(btnimg8);
                InvertirImagen(btnimg9);
                gane();
                break;

            case (8):
                InvertirImagen(btnimg5);
                InvertirImagen(btnimg6);
                InvertirImagen(btnimg8);
                InvertirImagen(btnimg9);
                gane();
                break;

        }

    }
    private void InvertirImagen (ImageButton imagen)
        {
            Drawable.ConstantState codigoimagenboton, codigoticverde;
            codigoticverde= ContextCompat.getDrawable(this, R.drawable.ticverde).getConstantState();
            codigoimagenboton = imagen.getDrawable().getConstantState();

            if (codigoimagenboton.equals(codigoticverde))
            {
                imagen.setImageResource(R.drawable.cruzroja);
            }
            else
            {
                imagen.setImageResource(R.drawable.ticverde);
            }
        }

}
