package renonkarton.playarena_app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.InputStream;


public class TeamSquadActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamsquad);

        try {
            Bundle b = getIntent().getExtras();
            String url = "";
            String logoUrl = "";
            if(b != null)
            {
                url = b.getString("teamUrl");
                logoUrl = b.getString("logoUrl");
            }

            ImageView logoImage = (ImageView) findViewById(R.id.team_logo);
            String pathToFile = "http://playarena.pl" + logoUrl;
            logoImage.setImageBitmap((new ImageDownloader().execute(pathToFile)).get());

            Player[] players_array = PlayersExtractor.getPlayers(Team.IdCutter(url));
            Context baseContext = getApplicationContext();
            new TableDisplay().setlayout(baseContext, players_array);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //zeby dobrze te buttony konfigurowac ta klasa musi byc tu
    private class TableDisplay {

        private  void setlayout(Context mycontext, Player[] data)
        {
            TableLayout tableLayout = findViewById(R.id.main_table);
            for(int i = 0; i < 8; i++) {
               // tableLayout.setColumnShrinkable(i, true);
                tableLayout.setColumnStretchable(i, true);
            }
            TableRow tableRow = new TableRow(mycontext);

            TextView text = new Button(mycontext);
            text.setText("");
            tableRow.addView(text);

            TextView text2 = new Button(mycontext);
            text2.setText("Nr");
            tableRow.addView(text2);

            TextView text3 = new Button(mycontext);
            text3.setText("Imie i nazwisko");
            tableRow.addView(text3);

            TextView text4 = new Button(mycontext);
            text4.setText("Wiek");
            tableRow.addView(text4);

            TextView text5 = new Button(mycontext);
            text5.setText("M");
            tableRow.addView(text5);

            TextView text6 = new Button(mycontext);
            text6.setText("Pkt");
            tableRow.addView(text6);

            TextView text7 = new Button(mycontext);
            text7.setText("Gw");
            tableRow.addView(text7);

            TextView text8 = new Button(mycontext);
            text8.setText("MVP");
            tableRow.addView(text8);

            tableLayout.addView(tableRow);

            for (Player p:
                 data) {
                tableRow = new TableRow(mycontext);
                Button button = new Button(mycontext);

                button.setText(p.position);
                tableRow.addView(button);

                button = new Button(mycontext);
                button.setText(String.valueOf(p.number));
                tableRow.addView(button);

                button = new Button(mycontext);
                button.setText(p.name);
                tableRow.addView(button);

                button = new Button(mycontext);
                button.setText(String.valueOf(p.age));
                tableRow.addView(button);

                button = new Button(mycontext);
                button.setText(String.valueOf(p.matches));
                tableRow.addView(button);

                button = new Button(mycontext);
                button.setText(String.valueOf(p.points));
                tableRow.addView(button);

                button = new Button(mycontext);
                button.setText(String.valueOf(p.stars));
                tableRow.addView(button);

                button = new Button(mycontext);
                button.setText(String.valueOf(p.goals));
                tableRow.addView(button);

                tableLayout.addView(tableRow);
            }
        }
    }
}
