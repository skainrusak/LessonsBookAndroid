package com.example.alex.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.example.alex.myapplication.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.example.android.myapplication.answer_shown";
    private static final String KEY_CHEAT = "cheat";
    private boolean mAnswerIsTrue ;
    private TextView mAnswerTextView ;
    private Button mShowAnswer ;
    public static Intent newIntent(Context packageContext , boolean answerIsTrue){
        Intent intent = new Intent(packageContext , CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE , answerIsTrue);
        return intent ;
    }
    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN , false);
    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE , false);
        mAnswerTextView = (TextView)findViewById(R.id.answer_text_view);
         mShowAnswer = (Button)findViewById(R.id.show_answer_button);
         mShowAnswer.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (mAnswerIsTrue) {
                     mAnswerTextView.setText(R.string.true_button);
                 } else {
                     mAnswerTextView.setText(R.string.false_button);
                 }
                 setAnswerShownResult(true);
                 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                     int cx = mShowAnswer.getWidth() / 2;
                     int cy = mShowAnswer.getHeight() / 2;
                     float radius = mShowAnswer.getWidth();
                     Animator anim = ViewAnimationUtils.createCircularReveal(mShowAnswer, cx, cy, radius, 0);
                     anim.addListener(new AnimatorListenerAdapter() {
                         @Override
                         public void onAnimationEnd(Animator animation) {
                             super.onAnimationEnd(animation);
                             mAnswerTextView.setVisibility(View.VISIBLE);
                             mShowAnswer.setVisibility(View.INVISIBLE);
                         }
                     });
                     anim.start();
                 } else {
                     mAnswerTextView.setVisibility(View.VISIBLE);
                     mShowAnswer.setVisibility(View.INVISIBLE);
                 }
             }
         });
    }
    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN , isAnswerShown);
        setResult(RESULT_OK , data);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_CHEAT , mAnswerIsTrue);
        setAnswerShownResult(true);
    }
}
