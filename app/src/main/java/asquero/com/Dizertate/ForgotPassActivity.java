package asquero.com.Dizertate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class ForgotPassActivity extends AppCompatActivity {

    private TextInputLayout emailInput;

    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Reset Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        emailInput = findViewById(R.id.email);

        Button reset = findViewById(R.id.resetBtn);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = Objects.requireNonNull(emailInput.getEditText()).getText().toString();

                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("FORGOT_PASS", "Email sent.");
                                    Toast.makeText(ForgotPassActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                                    updateUI();
                                }
                            }
                        });

            }
        });
    }

    private void updateUI() {

        Intent intent = new Intent(ForgotPassActivity.this, SignInActivity.class);
        startActivity( intent );
        finish();

    }
}
