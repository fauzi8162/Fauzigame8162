/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yourcompany.quizgame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.electricpunch.vgquiz.R;

public class MenuActivity extends Activity {

    TextView game_name;
    Button play_button, exit_button, continue_button;
    final int text_size = (int) Helper.getScreenSize().y;
    final int button_width = (int) Helper.getScreenSize().x / 2;
    SharedPreferences save;
    SharedPreferences.Editor editor;
    
    
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.menu);
        //setting background image
        getWindow().getDecorView().setBackgroundResource(R.drawable.background_in_menu);
        //init sound effects
        Helper.InitSounds(this, new String[]{"click"});
        //set game name size and font
        game_name = (TextView) findViewById(R.id.game_name);
        game_name.setText("Quiz Game");
        game_name.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/headfont.ttf"));
        //continue last ended game
        save = getSharedPreferences("SAVE_GAME", 0);
        editor = save.edit();
        continue_button = (Button) findViewById(R.id.continue_button);
        continue_button.setBackgroundResource(R.drawable.menu_button);
        continue_button.setText("Continue");
        continue_button.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/normalfont.ttf"));
        if (save.contains("gameSaved") && save.getBoolean("gameSaved", false)) {
            continue_button.setVisibility(View.VISIBLE);
        } else {
            continue_button.setVisibility(View.GONE);
        }
        continue_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Helper.playSound(getApplicationContext(), "click");
                editor.putBoolean("continue", true);
                editor.commit();
                Intent game_intent = new Intent(MenuActivity.this, GameActivity.class);
                startActivity(game_intent);
                finish();
            }
        });
        //start the new game
        play_button = (Button) findViewById(R.id.play_button);
        play_button.setBackgroundResource(R.drawable.menu_button);
        play_button.setText("New Game");
        play_button.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/normalfont.ttf"));
        play_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Helper.playSound(getApplicationContext(), "click");
                editor.putBoolean("continue", false);
                editor.commit();
                Intent game_intent = new Intent(MenuActivity.this, GameActivity.class);
                startActivity(game_intent);
                finish();
            }
        });
        //exit from game
        exit_button = (Button) findViewById(R.id.exit_button);
        exit_button.setBackgroundResource(R.drawable.menu_button);
        exit_button.setText("Exit");
        exit_button.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/normalfont.ttf"));
        exit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Helper.playSound(getApplicationContext(), "click");
                finish();
            }
        });
    }
}
