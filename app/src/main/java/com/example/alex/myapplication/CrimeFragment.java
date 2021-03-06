package com.example.alex.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;

public class CrimeFragment extends Fragment {
    private static final String ARG_CRIME_ID = "crime_id";
    private Crime mCrime ;
    private EditText mTitleField;
    private Button mDateButton ;
    private CheckBox mSolvedCheckBox ;
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE =0;

    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID ,crimeId);           //CrimeFragment метод newInstance(UUID), который получает UUID,
                                                               //создает пакет аргументов, создает экземпляр фрагмента, а затем присоединяет аргументы к фрагменту
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);                                                                  // Когда фрагменту требуется получить доступ к его аргументам, он вызывает метод
                                                                                                         //getArguments() класса Fragment, а затем один из get-методов Bundle для конкретного типа.
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }
    @Override
    public View onCreateView(LayoutInflater inflater , final ViewGroup container , Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_crime , container , false);
        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence c, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence c, int start, int before, int count) {
mCrime.setTitle(c.toString());
            }

            @Override
            public void afterTextChanged(Editable c) {

            }
        });

        mDateButton = (Button)v.findViewById(R.id.crime_date);
        String data = DateFormat.format("EEEE , MMM d , yyyy" ,mCrime.getDate()).toString();
        mDateButton.setText(data);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager =getFragmentManager();
                DataPickerFragment dialog = DataPickerFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this ,REQUEST_DATE);
                dialog.show(manager , DIALOG_DATE);
            }
        });

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);          //Чек бокс для проверки раскрыто ли преступление
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        return v;
    }
    @Override
    public void onActivityResult(int requestCode ,int resultCode, Intent data){
        if (requestCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_DATE){
            Date date = (Date)data.getSerializableExtra(DataPickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            mDateButton.setText(mCrime.getDate().toString());
        }
    }

}
