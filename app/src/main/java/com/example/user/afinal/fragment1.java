package com.example.user.afinal;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by tomoya on 4/5/17.
 */

public class fragment1 extends Fragment implements View.OnClickListener {

    private TextView mAScoreText, mBScoreText;
    private int mAScore, mBScore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment1, container, false);

        mAScoreText = (TextView) rootView.findViewById(R.id.aScoreText);
        mBScoreText = (TextView) rootView.findViewById(R.id.bScoreText);
        Button mAAddThreeBtn = (Button) rootView.findViewById(R.id.aAddThreeBtn);
        Button mAAddTwoBtn = (Button) rootView.findViewById(R.id.aAddTwoBtn);
        Button mAAddOneBtn = (Button) rootView.findViewById(R.id.aAddOneBtn);
        Button mBAddThreeBtn = (Button) rootView.findViewById(R.id.bAddThreeBtn);
        Button mBAddTwoBtn = (Button) rootView.findViewById(R.id.bAddTwoBtn);
        Button mBAddOneBtn = (Button) rootView.findViewById(R.id.bAddOneBtn);
        Button mResetBtn = (Button) rootView.findViewById(R.id.resetBtn);

        mAAddThreeBtn.setOnClickListener(this);
        mAAddTwoBtn.setOnClickListener(this);
        mAAddOneBtn.setOnClickListener(this);
        mBAddThreeBtn.setOnClickListener(this);
        mBAddTwoBtn.setOnClickListener(this);
        mBAddOneBtn.setOnClickListener(this);
        mResetBtn.setOnClickListener(this);




        displayAScore(mAScore);
        displayBScore(mBScore);

        return rootView;
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.aAddThreeBtn:
                aAddScore(3);
                break;
            case R.id.aAddTwoBtn:
                aAddScore(2);
                break;
            case R.id.aAddOneBtn:
                aAddScore(1);
                break;
            case R.id.bAddThreeBtn:
                bAddScore(3);
                break;
            case R.id.bAddTwoBtn:
                bAddScore(2);
                break;
            case R.id.bAddOneBtn:
                bAddScore(1);
                break;
            case R.id.resetBtn:
                showResetDialog();
                break;
        }
    }

    private void aAddScore(int score) {
        mAScore = mAScore + score;
        displayAScore(mAScore);
    }

    private void bAddScore(int score) {
        mBScore = mBScore + score;
        displayBScore(mBScore);
    }

    private void displayAScore(int score) {
        mAScoreText.setText(String.valueOf(score));
    }


    private void displayBScore(int score) {
        mBScoreText.setText(String.valueOf(score));
    }

    private void showResetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getView().getContext())
                .setTitle("提示")
                .setMessage("確認要清空兩隊得分嗎?")
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetScore();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // ignore
                    }
                });
        builder.show();
    }

    private void resetScore() {
        mAScore = 0;
        mBScore = 0;
        displayAScore(mAScore);
        displayBScore(mBScore);
    }
}
