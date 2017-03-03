package myfriendisbamby.p0004_basicview;

import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextClock;


public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;

    private int mSetFormatTime = 0;

    private TextClock mTextClock;

    private TextView mQuestionTextView;

    private Question[] mQuestionsBank = new Question[]{
                    new Question(R.string.question_Size_Of_Clock, false),
                    new Question(R.string.question_Clock, true),
                    new Question(R.string.question_Name, true),

            };
    private int mCurrentIndex = 0;



    private void updateQuestion(){
        int question = mQuestionsBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionsBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if(userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this,messageResId, Toast.LENGTH_SHORT).show();
    }

    private void checkColor(boolean userPressedTrue){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int color = getResources().getColor(R.color.colorPrimaryDark);

        final TextClock mTextClock = (TextClock) findViewById(R.id.textClock);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
                    public void onClick(View v){
                //Toast.makeText(MainActivity.this,R.string.correct_toast,Toast.LENGTH_SHORT).show();
                //Пока нечего не делать, но скоро будеш

                checkAnswer(true);

                mTextClock.setFormat24Hour("yyyy-MM-dd 'T' HH:mm:ss");
                final int colorChild = MainActivity.this.getResources().getColor(R.color.colorAccent);
                mTextClock.setTextColor(colorChild);
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                //Toast.makeText(MainActivity.this,R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
                //Пока нечего не делать, но скоро будеш

                checkAnswer(false);

                mTextClock.setFormat24Hour("h:mm aa");
                mTextClock.setTextColor(color);
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionsBank.length;
                if(mCurrentIndex == 4) mCurrentIndex = 0;
                updateQuestion();
            }
        });
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionsBank.length;
                if(mCurrentIndex < 0) mCurrentIndex = 3;
                updateQuestion();
            }
        });
    updateQuestion();
    }
}
