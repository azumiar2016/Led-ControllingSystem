package led.myledtest;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    // Progress Dialog


    EditText inputred;
    EditText inputblue;
    EditText inputgreen;
    EditText ipaddress;
    // url to create new product


    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    String red,blue,green;
    String ip = "192.168.11.7";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Edit Text
        inputred = (EditText) findViewById(R.id.inputRed);
        inputblue = (EditText) findViewById(R.id.inputBlue);
        inputgreen = (EditText) findViewById(R.id.inputGreen);
        ipaddress = (EditText)findViewById(R.id.ipaddress);
        ipaddress.setText(ip);
        // Create button
        Button btnCreateNewLed = (Button) findViewById(R.id.btnCreateLed);
        Button ColorPickerBtn = (Button) findViewById(R.id.btnColorPicker);


        // button click event
        btnCreateNewLed.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                 red = inputred.getText().toString();
                 blue = inputblue.getText().toString();
                 green = inputgreen.getText().toString();
                 ip = ipaddress.getText().toString();

                // Building Parameters


               String url = "http://" +ip+  "/db_test.php";
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("Response", response);
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Log.d("Error.Response", "error");
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String,String> params = new HashMap<String,String>();
                        params.put("red", red);
                        params.put("blue", blue);
                        params.put("green", green);

                        return params;
                    }
                };
                queue.add(postRequest);



            }

        });

        ColorPickerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ColorPickerDialogBuilder
                        .with(v.getContext())
                        .setTitle("Choose color")
                        .initialColor(Color.RED)
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(10)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {

                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                changeBackgroundColor(selectedColor);
                            }


                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();

            }


        });


    }

    private void changeBackgroundColor(int selectedColor) {
       int r = Color.red(selectedColor);
       int b = Color.blue(selectedColor);
       int g = Color.green(selectedColor);
        inputred.setText(String.valueOf(r));
        inputblue.setText(String.valueOf(b));
        inputgreen.setText(String.valueOf(g));
    }


    }





