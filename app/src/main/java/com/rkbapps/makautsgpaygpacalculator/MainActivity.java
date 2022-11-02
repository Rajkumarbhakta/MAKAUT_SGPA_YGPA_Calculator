package com.rkbapps.makautsgpaygpacalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    EditText numberOfSubject, sgpaOrYgpa;
    Button calculate;
    TextView percentage, gotNumber;
    String totalSub, sgpa;
    CardView github, star, feedback;
    private ReviewManager reviewManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberOfSubject = findViewById(R.id.txtTotalNumberOFSub);
        sgpaOrYgpa = findViewById(R.id.txtSGPAorCGPA);
        calculate = findViewById(R.id.buttonCaculate);
        percentage = findViewById(R.id.txtPercentage);
        gotNumber = findViewById(R.id.txtGotNumber);
        github = findViewById(R.id.cardGithub);
        star = findViewById(R.id.cardRatting);
        feedback = findViewById(R.id.cardFeedback);


        calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                totalSub = numberOfSubject.getText().toString();
                sgpa = sgpaOrYgpa.getText().toString();
                if (totalSub.isEmpty() || sgpa.isEmpty()) {
                    // Toast.makeText(MainActivity.this, "Please Enter Proper Details", Toast.LENGTH_LONG).show();
                    Toasty.error(MainActivity.this, "Please Enter Proper Details", Toast.LENGTH_LONG).show();
                } else {
                    Double sgpaInDouble = Double.parseDouble(sgpa);
                    Double numOfSubjectInDOuble = Double.parseDouble(totalSub);
                    Double persentageDouble = Double.parseDouble(getSgpaToPercentage(sgpaInDouble));
                    if (persentageDouble >= 60.00) {
                        percentage.setTextColor(Color.GREEN);
                        percentage.setText("" + getSgpaToPercentage(sgpaInDouble) + "%");
                    } else {
                        percentage.setTextColor(Color.RED);
                        percentage.setText("" + getSgpaToPercentage(sgpaInDouble) + "%");
                    }
                    gotNumber.setText("" + getMarkToPercentage(numOfSubjectInDOuble, persentageDouble) + " Out Of " + numOfSubjectInDOuble * 100);
                }
            }
        });


        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://github.com/Rajkumarbhakta/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


//        star.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            //
//                Toasty.success(MainActivity.this,"Success",Toast.LENGTH_LONG).show();
//            }
//        });

        init();


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("mailto:askrkbapps@gmail.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }

    //Method to calculate percentage .

    public String getSgpaToPercentage(Double SGPA) {
        Double percentage;
        percentage = (SGPA - 0.75) * 10;
        String stringDecimal = String.format("%2f", percentage);
        return stringDecimal;
    }

    //Method to calculate marks
    public String getMarkToPercentage(Double totalSUb, Double Persentage) {
        Double obtainMark = 0.0;
        obtainMark = (Persentage * (totalSUb * 100)) / 100;
        String stringDecimal = String.format("%2f", obtainMark);
        return stringDecimal;
    }


    // Initializing method
    private void init() {
        reviewManager = ReviewManagerFactory.create(this);
        // Referencing the button
        findViewById(R.id.cardRatting).setOnClickListener(view -> showRateApp());
    }

    public void showRateApp() {
        Task<ReviewInfo> request = reviewManager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Getting the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();

                Task<Void> flow = reviewManager.launchReviewFlow(this, reviewInfo);
                flow.addOnCompleteListener(task1 -> {
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown.
                });
            }
        });
    }
}