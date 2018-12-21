package net.danteh.handmade;

import android.animation.Animator;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.morphingbutton.MorphingButton;
import com.github.florent37.viewanimator.ViewAnimator;

import java.util.concurrent.TimeUnit;

import static android.media.MediaExtractor.MetricsConstants.FORMAT;

public class Login extends AppCompatActivity {
    Button send;
    EditText pn,code;
    ImageView logoView;
    MorphingButton btnMorph;
    TextView state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pn = findViewById(R.id.pn);
        code = findViewById(R.id.code);
        //send = findViewById(R.id.sendbtn);
        logoView = findViewById(R.id.logoView);
        btnMorph = findViewById(R.id.send);
        state = findViewById(R.id.state_txt);

        btnMorph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MorphingButton.Params circle = MorphingButton.Params.create()
                        .duration(1000)
                        .cornerRadius(100) // 56 dp
                        .width(200) // 56 dp
                        .height(200) // 56 dp
                        .color(Color.parseColor("#38a22a")) // normal state color
                        .colorPressed(Color.parseColor("#33852f")) // pressed state color
                        .icon(R.drawable.checked); // icon
                btnMorph.morph(circle);
                pn.setVisibility(View.GONE);
                code.setVisibility(View.VISIBLE);
                state.setVisibility(View.VISIBLE);

                new CountDownTimer(120000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        state.setText( "لطفاً تا دریافت پیامک فعال سازی صبرکنید \n"+(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)) + ":"
                                + (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))));
                        //state.setText("لطفا تا دریافت پیامک صبر کنید\n " +millisUntilFinished/60000 + ":"  + millisUntilFinished / 10000);
                        //here you can have your logic to set text to edittext
                    }

                    public void onFinish() {
                        unmorph(btnMorph);
                        state.setVisibility(View.INVISIBLE);
                        //state.setText("ارسال دوباره پیامک");
                    }

                }.start();
            }
        });



        pn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    logoView.setImageResource(R.drawable.logo_on);
                else
                    logoView.setImageResource(R.drawable.logo);
            }
        });

        //ViewAnimator.animate(logoView).scale(2,1).repeatCount(9999).repeatMode(2).duration(2000).start();
    }

    public void unmorph (MorphingButton btn){
        MorphingButton.Params square = MorphingButton.Params.create()
                .duration(1000)
                .cornerRadius(10) // 56 dp
                .width(600) // 56 dp
                .height(200) // 56 dp
                .color(Color.parseColor("#0ca2d0")) // normal state color
                .colorPressed(Color.parseColor("#0ca2d0")) // pressed state color
                .text("ارسال دوباره پیامک"); // icon
        btn.morph(square);
    }
}
