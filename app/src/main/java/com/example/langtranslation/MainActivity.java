package com.example.langtranslation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextInputEditText sourceText;
    TextView targetText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sourceText = findViewById(R.id.source_txt);
        targetText = findViewById(R.id.target_txt);

        final TranslateViewModel viewModel = ViewModelProviders.of(this).get(TranslateViewModel.class);
        viewModel.sourceLang.setValue(new Language("en"));
        viewModel.targetLang.setValue(new Language("fr"));

        sourceText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setProgressText(targetText);
                viewModel.sourceText.postValue(s.toString());

            }
        });

        viewModel.translatedText.observe(this, new Observer<ResultOrError>() {
            @Override
            public void onChanged(ResultOrError resultOrError) {
                if(resultOrError.error != null){
                    sourceText.setError(resultOrError.error.getLocalizedMessage());

                } else {
                    targetText.setText(resultOrError.result);

                }
            }
        });
    }

    private void setProgressText(TextView tv){
        tv.setText(this.getString(R.string.translate_progress));
    }
}