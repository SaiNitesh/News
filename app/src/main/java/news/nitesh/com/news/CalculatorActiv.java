package news.nitesh.com.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by nitesh on 12/1/2016.
 */
public class CalculatorActiv extends AppCompatActivity{

    TextView display=null;
    TextView smallDisplay=null;

    int kDigit;
    long dNumber,lastNum, result ;

    String lastop="=";

    boolean justNumPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activ_calculator);

        display = (TextView) findViewById(R.id.display);
        smallDisplay = (TextView) findViewById(R.id.smallDisplay);
    }


    public void numberPressed(View v){
        Button b = (Button) v;
        Log.i("customTag",b.getText().toString());

        kDigit = Integer.parseInt(b.getText().toString());
        dNumber =Long.parseLong(display.getText().toString());

        dNumber= dNumber * 10 + kDigit;

        justNumPressed = true;

        display.setText(String.valueOf(dNumber));

    }

    public void opearatorPressed(View v){

        Button b= (Button)v;

        String op = b.getText().toString();
        lastNum = Long.parseLong(display.getText().toString());

        clear(null);


        result = calculate(result, lastNum, lastop);
        // result=1 lastNum=1 lastop=
        //result=1 lastNum=2 lastop=
       // result=1 lastNum=2 lastop+
        // result=3 lastNum=0 lastop=

        smallDisplay.setText(String.valueOf(result) + op);

        if (op.equals("=")){
            display.setText(String.valueOf(result));
            lastNum=0;
            result=0;
        }

        lastop =op;

        justNumPressed = false;
    }

    public long calculate(long result, long lastNum, String op){
        if(op.equals("+")){
            return result + lastNum;
        }else if (op.equals("-")){
            return result - lastNum;
        }else if (op.equals("x")){
            if (justNumPressed){
                // first pressed 1 then if you press x and then your mind changed and pressed +,
                // Now it imeedaitely  returns result*lastNum and then it adds +, instead of changing the operator directly from x to +
                // thats why we add "justNumPressed" logic
                return result * lastNum;
            }
            return  result;

        }else if (op.equals("/")){
            if (justNumPressed){
                return result / lastNum;
            }
            return  result;
        }
        return lastNum;

    }

    public void clear(View v){
        display.setText("0");
        smallDisplay.setText("0");
    }

}
