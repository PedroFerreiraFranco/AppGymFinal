package br.edu.ifsuldeminas.mch.championsgym;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class API_activity extends AppCompatActivity {

    private static final String PREFS_NAME = "SharedPreferences";
    private static final String TEXT_KEY = "sharedText";
    private String resultText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        getSupportActionBar().hide();
        TextView tv = findViewById(R.id.jsonExercicios);

        // URL do seu endpoint JSON
        String url = "http://10.0.2.2:3000/chests"; // Substitua pelo URL correto

        // Usando JsonArrayRequest para lidar com um array JSON
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                StringBuilder result = new StringBuilder();

                try {
                    // Percorrer o array JSON
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String title = jsonObject.getString("title");
                        String description = jsonObject.getString("description");
                        String series = jsonObject.getString("series");
                        String repetitions = jsonObject.getString("repetitions");

                        result.append("Exercício: ").append(title).append("\n")
                                .append("Descrição: ").append(description).append("\n")
                                .append("Séries: ").append(series).append("\n")
                                .append("Repetições: ").append(repetitions).append("\n\n");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                resultText = result.toString();
                tv.setText(resultText);

                // Salvar o texto no SharedPreferences
                SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(TEXT_KEY, resultText);
                editor.apply();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tv.setText("Error: " + error.getMessage());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                String textToShare = preferences.getString(TEXT_KEY, "");

                shareData(textToShare);
            }
        });
    }

    // Método para compartilhar os dados
    private void shareData(String data) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, data);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, "Compartilhar via");
        startActivity(shareIntent);
    }
}
