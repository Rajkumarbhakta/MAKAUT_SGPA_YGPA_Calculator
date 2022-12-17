package com.rkbapps.makautsgpaygpacalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SgpaToPercentageActivity extends AppCompatActivity {
    /*
    Variable declaration
     */
    EditText firstSemTotalSUb, secondSemTotalSub, firstSemSgpa, secondSemSgpa;
    Button calculation;
    TextView firstSemPercentage, secondSemPercentage, firstSemNumber, secondSemNumber;
    TextView ygpa, ygpaPercentage, ygpaNumber;
    String firstSemSubject, secondSemSubject, firstSemGpa, secondSemGpa;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgpa_to_percentage);
        //finding the id from xml
        firstSemTotalSUb = findViewById(R.id.edit_first_semester_subject);
        firstSemSgpa = findViewById(R.id.edit_first_semester_sgpa);
        secondSemTotalSub = findViewById(R.id.edit_second_semester_subject);
        secondSemSgpa = findViewById(R.id.edit_second_semester_sgpa);
        /*
            the calculate button
         */
        calculation = findViewById(R.id.button_calculate);
        /*
            for result section
         */
        firstSemPercentage = findViewById(R.id.txt_first_semester_percentage);
        firstSemNumber = findViewById(R.id.txt_first_semester_number);
        secondSemPercentage = findViewById(R.id.txt_second_semester_percentage);
        secondSemNumber = findViewById(R.id.txt_second_semester_number);
        /*
            For ygpa section
         */
        ygpa = findViewById(R.id.txt_ygpa);
        ygpaPercentage = findViewById(R.id.txt_ygpa_percentage);
        ygpaNumber = findViewById(R.id.txt_ygpa_number);

        calculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstSemSubject = firstSemTotalSUb.getText().toString();
                secondSemSubject = secondSemTotalSub.getText().toString();
                firstSemGpa = firstSemSgpa.getText().toString();
                secondSemGpa = secondSemSgpa.getText().toString();
                if (firstSemSubject.isEmpty() && firstSemGpa.isEmpty() || secondSemSubject.isEmpty() && secondSemGpa.isEmpty()) {
                    /*
                    if
                     */


                } else {
                /*
                convert sgpa in ygpa
                 */
                    Double doubleFirstSemGpa = Double.parseDouble(firstSemGpa);
                    Double doubleSecondSemGpa = Double.parseDouble(secondSemGpa);
                    Double doubleYgpa = (doubleFirstSemGpa + doubleSecondSemGpa) / 2.0;
                /*
                convert string subject number into double
                 */
                    Double doubleFirstSemSub = Double.parseDouble(firstSemSubject);
                    Double doubleSecondSemSub = Double.parseDouble(secondSemSubject);
                    Double doubleYgpaSub = doubleFirstSemSub + doubleSecondSemSub;

                    firstSemPercentage.setText("" + getSgpaToPercentage(doubleFirstSemGpa) + "%");
                    firstSemNumber.setText("" + getMarkToPercentage(doubleFirstSemSub, Double.parseDouble(getSgpaToPercentage(doubleFirstSemGpa))) + " Out Of " + doubleFirstSemSub * 100);

                    secondSemPercentage.setText("" + getSgpaToPercentage(doubleSecondSemGpa) + "%");
                    secondSemNumber.setText("" + getMarkToPercentage(doubleSecondSemSub, Double.parseDouble(getSgpaToPercentage(doubleSecondSemGpa))) + " Out Of " + doubleSecondSemSub * 100);

                    ygpa.setText("" + doubleYgpa);
                    ygpaPercentage.setText("" + getSgpaToPercentage(doubleYgpa) + "%");
                    ygpaNumber.setText("" + getMarkToPercentage(doubleYgpaSub, Double.parseDouble(getSgpaToPercentage(doubleYgpa))) + " Out Of " + doubleYgpaSub * 100);

                }


            }
        });

    }

    //Method to calculate percentage .

    public String getSgpaToPercentage(Double GPA) {
        Double percentage;
        percentage = (GPA - 0.75) * 10;
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
}