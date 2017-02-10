package com.example.adriamartinez.finalproject;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.support.v7.appcompat.R.styleable.Toolbar;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class CalculatorFragment extends android.support.v4.app.Fragment implements View.OnClickListener{
    SharedPreferences sp;

    public CalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        //listeners
        View rootview = inflater.inflate(R.layout.calculadora,container,false);
        setHasOptionsMenu(true);


        Button b0 = (Button) rootview.findViewById(R.id.b0);
        Button b1 = (Button) rootview.findViewById(R.id.b1);
        Button b2 = (Button) rootview.findViewById(R.id.b2);
        Button b3 = (Button) rootview.findViewById(R.id.b3);
        Button b4 = (Button) rootview.findViewById(R.id.b4);
        Button b5 = (Button) rootview.findViewById(R.id.b5);
        Button b6 = (Button) rootview.findViewById(R.id.b6);
        Button b7 = (Button) rootview.findViewById(R.id.b7);
        Button b8 = (Button) rootview.findViewById(R.id.b8);
        Button b9 = (Button) rootview.findViewById(R.id.b9);
        Button equal = (Button) rootview.findViewById(R.id.equal);
        ImageButton erase = (ImageButton) rootview.findViewById(R.id.erase);
        Button add = (Button) rootview.findViewById(R.id.add);
        Button multiply = (Button) rootview.findViewById(R.id.multiply);
        Button substract = (Button) rootview.findViewById(R.id.substract);
        Button AC = (Button) rootview.findViewById(R.id.AC);
        Button decimal = (Button) rootview.findViewById(R.id.decimalpoint);
        Button divide = (Button) rootview.findViewById(R.id.divide);
        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        equal.setOnClickListener(this);
        erase.setOnClickListener(this);
        add.setOnClickListener(this);
        multiply.setOnClickListener(this);
        substract.setOnClickListener(this);
        AC.setOnClickListener(this);
        decimal.setOnClickListener(this);
        divide.setOnClickListener(this);

        /*
        Edit the shared preferences
        If the two variables of SP already existed then they are mantained, if not, float is set to zero
        and operation to "finished
         */
        sp = getActivity().getSharedPreferences("calculator", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String s1 = sp.getString("s1","0");
        String s2 = sp.getString("s2",null);
        String s3 = sp.getString("s3","0");
        Boolean bol1 = sp.getBoolean("b1",true);
        Boolean bol2 = sp.getBoolean("b2",false);
        Boolean bol3 = sp.getBoolean("b3",false);

        editor.putString("s1",s1);
        editor.putString("s2",s2);
        editor.putString("s3",s3);
        editor.putBoolean("b1",bol1);
        editor.putBoolean("b2",bol2);
        editor.putBoolean("b3",bol3);
        editor.apply();

        TextView editText = (TextView) rootview.findViewById(R.id.editCalc);
        editText.setText(s1);

        return rootview;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    private void inicialitza_calc(){
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("s1","0");
        editor.putString("s2",null);
        editor.putString("s3",null);
        editor.putBoolean("b1",true);
        editor.putBoolean("b2",false);
        editor.putBoolean("b3",false);
        editor.apply();
        TextView et = (TextView) getActivity().findViewById(R.id.editCalc);
        et.setText("0");

    }
    private String encadena(String s1, String s2){
        String s = s1 + s2;
        if(s.charAt(0) == '0' && s.charAt(1) != '.') return s2;
        else return s;
    }
    private float calcula(String s1, String s2, String s3){
        Float f1 = Float.valueOf(s1);
        Float f2 = Float.valueOf(s3);
        if(s2.equals("*")) return f1*f2;
        if(s2.equals("+")) return f1+f2;
        if(s2.equals("-")) return f1-f2;
        if(s2.equals("/")) return f1/f2;
        if(s2.equals("+")) return f1+f2;
        return f1;
    }
    private void update_calc(char c) {
        Log.v("Al entrar", sp.getBoolean("b1",false)+" "+sp.getBoolean("b2",false)+" "+sp.getBoolean("b3",false));
        TextView et = (TextView) getActivity().findViewById(R.id.editCalc);
        SharedPreferences.Editor editor = sp.edit();
        if ((c >= '0' && c <= '9') || c == '.' ) {
            //estic introduint numeros, en aquest cas sempre l'únic que voldré fer és editar la pantalla
            //i en cas que el b2 sigui true posar a true el b3 perquè ja ha introduit un segon numero
            //també s'ha de comprovar si el b1 es false en aquest cas borrarem la pantalla
            if (!sp.getBoolean("b1", false)) {
                String s = String.valueOf(c);
                et.setText(s);
                editor.putBoolean("b1", true);
                editor.putString("s1",s);
                editor.apply();
            } else {
                if (!sp.getBoolean("b2", false)) {
                    String tmp = encadena(sp.getString("s1",""), String.valueOf(c));
                    editor.putString("s1", tmp);
                    editor.apply();
                    et.setText(sp.getString("s1", "3"));
                } else {
                    editor.putBoolean("b3",true);
                    editor.putString("s3", encadena(sp.getString("s3", "0"), String.valueOf(c)));
                    editor.apply();
                    et.setText(sp.getString("s3", ""));
                }

            }
        } else {
           /*
           en cas contrari estic posant una operació. Hi ha dues possibilitats
                1. ja tenia a true els 3 valors per tant calculo la operació pendent i em preparo per la seguent
                2. el tercer valor es fals per tant estic triant l'operació
            S'ha de posar sempre el valor b1 a true
            */
            if (!sp.getBoolean("b2", false)) {
                if (c != '=') {
                    editor.putString("s2", String.valueOf(c));
                    editor.putBoolean("b1", true);
                    editor.putBoolean("b2", true);
                    editor.apply();
                    et.setText(sp.getString("s2", ""));
                }
            }else{
                if(c!= '='){
                    if(!sp.getBoolean("b3",false)){
                        editor.putString("s2", String.valueOf(c));
                        editor.apply();
                        et.setText(sp.getString("s2", ""));
                    }else{
                        String s1,s2,s3;
                        s1 = sp.getString("s1",null);
                        s2 = sp.getString("s2",null);
                        s3 = sp.getString("s3",null);
                        Float f = calcula(s1,s2,s3);
                        editor.putBoolean("b1",true);
                        editor.putBoolean("b2",true);
                        editor.putBoolean("b3",false);
                        editor.putString("s1",f.toString());
                        editor.putString("s3","0");
                        editor.putString("s2",String.valueOf(c));
                        editor.apply();
                        et.setText(sp.getString("s1",null));
                    }
                }else{
                    if(!sp.getBoolean("b3",false)) {
                        editor.putString("s3",sp.getString("s1"," "));
                        editor.putBoolean("b1",true);
                        editor.putBoolean("b2",true);
                        editor.putBoolean("b3",true);
                        editor.apply();
                        String s1,s2,s3;
                        s1 = sp.getString("s1",null);
                        s2 = sp.getString("s2",null);
                        s3 = sp.getString("s3",null);
                        Float f = calcula(s1,s2,s3);
                        editor.putBoolean("b1",true);
                        editor.putBoolean("b2",true);
                        editor.putBoolean("b3",false);
                        editor.putString("s1",f.toString());
                        editor.putString("s3","0");
                        editor.putString("s2",String.valueOf(c));
                        editor.apply();
                        et.setText(sp.getString("s1",null));
                    }else{
                        String s1,s2,s3;
                        s1 = sp.getString("s1",null);
                        s2 = sp.getString("s2",null);
                        s3 = sp.getString("s3",null);
                        Float f = calcula(s1,s2,s3);
                        editor.putBoolean("b1",false);
                        editor.putBoolean("b2",false);
                        editor.putBoolean("b3",false);
                        editor.putString("s1",f.toString());
                        editor.putString("s3","0");
                        editor.putString("s2",String.valueOf(c));
                        editor.apply();
                        et.setText(sp.getString("s1",null));
                    }
                }
            }
        }
        Log.v("Al sortir", sp.getBoolean("b1",false)+" "+sp.getBoolean("b2",false)+" "+sp.getBoolean("b3",false));
    }

    @Override
    public void onClick(View view) {
        char c;
            switch (view.getId()) {
                case R.id.b0:
                    c = '0';
                    update_calc(c);
                    break;
                case R.id.b1:
                    c = '1';
                    update_calc(c);
                    break;
                case R.id.b2:
                    c = '2';
                    update_calc(c);
                    break;
                case R.id.b3:
                    c = '3';
                    update_calc(c);
                    break;
                case R.id.b4:
                    c = '4';
                    update_calc(c);
                    break;
                case R.id.b5:
                    c = '5';
                    update_calc(c);
                    break;
                case R.id.b6:
                    c = '6';
                    update_calc(c);
                    break;
                case R.id.b7:
                    c = '7';
                    update_calc(c);
                    break;
                case R.id.b8:
                    c = '8';
                    update_calc(c);
                    break;
                case R.id.b9:
                    c = '9';
                    update_calc(c);
                    break;
                case R.id.multiply:
                    c = '*';
                    update_calc(c);
                    break;
                case R.id.divide:
                    c = '/';
                    update_calc(c);
                    break;
                case R.id.add:
                    c = '+';
                    update_calc(c);
                    break;
                case R.id.substract:
                    c = '-';
                    update_calc(c);
                    break;
                case R.id.equal:
                    c = '=';
                    update_calc(c);
                    break;
                case R.id.AC:
                    inicialitza_calc();
                    break;
                case R.id.decimalpoint:
                    update_calc('.');
                    break;
            }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_calculadora, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
