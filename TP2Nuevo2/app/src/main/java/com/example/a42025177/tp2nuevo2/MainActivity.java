package com.example.a42025177.tp2nuevo2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.Channels;
import java.util.Random;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    Random generator = new Random();
    int num = generator.nextInt(10);
    int num2 = generator.nextInt(10);
    String simbolos = "+-*/"; // para usar en el random de simbolos
    char sim = simbolos.charAt(generator.nextInt(simbolos.length())); //consigo uno de los simbolos del string
    String numstr = Integer.toString(num);
    String num2str = Integer.toString(num2);
    baseTP3SQLiteHelper accesoabase;
    SQLiteDatabase basededatos;
    boolean responder;
    boolean mul = false; //lo uso para saber si el captcha es una multiplicacion
    boolean sum = false; //lo uso para saber si el captcha es una suma
    boolean res = false; //lo uso para saber si el captcha es una resta
    boolean div = false; //lo uso para saber si el captcha es una division
    String Nombre;
    boolean Existe;
    int result = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView cuenta = (TextView)findViewById(R.id.cuenta);
        if (sim == '+') //pregunto si el simbolo obtenido es +
            {
            cuenta.setText(numstr + " + " + num2str);
            sum = true;
        }
        if (sim == '-') //pregunto si el simbolo obtenido es -
        {
            cuenta.setText(numstr + " - " + num2str);
            res= true;
        }
        if (sim == '*') //pregunto si el simbolo obtenido es *
        {
            cuenta.setText(numstr + " * " + num2str);
            mul = true;
        }
        if (sim == '/') //pregunto si el simbolo obtenido es /
        {
            if (num%num2 == 0) //pregunto si el resto de la division entre e numero 1 y 2 es 0
            {
            cuenta.setText(numstr + " / " + num2str);
            div = true;
            }
            else //sino lo es no uso division para el captcha ya que seria y dificil para el usuario
                {
                    cuenta.setText(numstr + " + " + num2str);
                    sum = true; //en lugar de division uso suma
                }
        }

    }

    public void PresionaBoton(View Vista)
    {
        EditText txtnombre = (EditText)findViewById(R.id.txtNombre);
        EditText resultado = (EditText)findViewById(R.id.txtresultado);
        String resultingre = resultado.getText().toString();

        if (sum)  //si el bool de suma es true guardo en una variable resultado la suma del numero 1 y 2
        {
            result = num + num2;
        }
        if (res) //si el bool de resta es true guardo en una variable resultado la resta del numero 1 y 2
        {
            result = num - num2;
        }
        if (mul) //si el bool de multiplicacion es true guardo en una variable resultado la multiplicacion del numero 1 y 2
        {
            result = num * num2;
        }
        if (div) //si el bool de division es true guardo en una variable resultado la division del numero 1 y 2
        {
            result = num / num2;
        }
        int resulting = Integer.parseInt(resultingre); //convierto el resultado ingresado a int

        Nombre = txtnombre.getText().toString();
        if (Nombre.length() >0 && resultingre.length()>0 && resulting == result) //pregunto si ingreso nombre, resultado del captcha y si este es correcto
        {
            accesoabase = new baseTP3SQLiteHelper(this, "baseTP3", null, 1);
            inicializarbase(); //abro la conexion con la base de datos
            ValidarNombre(); //llamo a la funcion validar nombre
            if (Existe == true) //me fijo si el usuario ya jugo para cambiar el saludo
            {
            Bundle PaquetedeDatos = new Bundle();
            PaquetedeDatos.putString("Nombreingresado", Nombre);
            PaquetedeDatos.putString("Saludo", "Hola de nuevo:");
            Intent Ir;
            Ir = new Intent(MainActivity.this, Juego.class);
            Ir.putExtras(PaquetedeDatos);
            startActivity(Ir);
        }
        else //si no jugo le doy la bienvenida al juego
            {
                Bundle PaquetedeDatos = new Bundle();
                PaquetedeDatos.putString("Nombreingresado", Nombre);
                PaquetedeDatos.putString("Saludo", "Bienvenido al juego:");
                Intent Ir;
                Ir = new Intent(MainActivity.this, Juego.class);
                Ir.putExtras(PaquetedeDatos);
                startActivity(Ir);
            }
        }
        else //Muestro los errores
        {
            if (Nombre.length()<=0)
            {
                txtnombre.setError("Debe ingresar su nombre");
            }
            if (resultingre.length()<=0)
            {
                resultado.setError("Debe ingresar el resultado a la suma");
            }
            else if (resulting != result)
            {
                resultado.setError("El resultado de la suma es incorrecto");
            }
            TextView errores= (TextView)findViewById(R.id.txterror);
            errores.setText("El nombre o el resultado de la cuenta es incorrecto");
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
    public void ValidarNombre()
    {
        if (responder ==true)
        {
            Cursor ConjuntoRegistros;
            ConjuntoRegistros = basededatos.rawQuery("select Nombre from jugadores", null);
            if(ConjuntoRegistros.moveToFirst() == true)
            {

                do
                {

                    String NombreSQL = ConjuntoRegistros.getString(0);

                    if(NombreSQL.equals(Nombre))
                    {
                        Existe = true;

                    }
                }while (ConjuntoRegistros.moveToNext()==true);

                if(Existe==false)
                {
                    ContentValues nuevoregistro;

                    nuevoregistro= new ContentValues();
                    nuevoregistro.put("Nombre", Nombre);
                    basededatos.insert("jugadores", null, nuevoregistro);

                }
            }
            else
            {
                ContentValues nuevoregistro;

                nuevoregistro= new ContentValues();
                nuevoregistro.put("Nombre", Nombre);
                basededatos.insert("jugadores", null, nuevoregistro);
            }

        }
        basededatos.close();
    }

}
