package com.shawn.animationdrawableproject;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private boolean isPressed;
    private int cntWin = 0, cntLose = 0, cntDraw = 0, time = 0, computerScore = 0, humanScore = 0, computerResult = 0, playerResult = 0, aiBlood = 0;
    private TextView textlshow, textwshow, textdshow, showResult, countDown, content, showlevel;
    private Button btn_paper, btn_scissors, btn_rock, backupbutton, backupbutton2, next_btn;
    private ImageView main_characters_hand, tiny_imageview_animate, hulkangry;
    private ImageView blue_heart1, blue_heart2, blue_heart3, blue_heart4, blue_heart5;
    private ImageView red_heart1, red_heart2, red_heart3, red_heart4, red_heart5, cdnum;
    private AnimationDrawable forTinyAnimation, forMainAnimation, initial_faceMotion, forhulkangry;
    private CountDownTimer countDownTimer;
    private LinearLayout people, bigbackground;
    private int level, winrate = 0, winpercent = 0, radom2select1 = 0, rateplayerResult = 0;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialisation();
        initialFaceMotion();
        runningInSmallScreen();
        countDown();
        doAction();
        changeBackground();
    }

    @Override
    protected void onStart() {
        super.onStart();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void initialisation() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        backupbutton = (Button) findViewById(R.id.backupbutton);
        backupbutton2 = (Button) findViewById(R.id.backupbutton2);//addnew
        btn_rock = (Button) findViewById(R.id.btn_rock);
        btn_paper = (Button) findViewById(R.id.btn_paper);
        btn_scissors = (Button) findViewById(R.id.btn_scissors);
        showResult = (TextView) findViewById(R.id.showResult);
        textdshow = (TextView) findViewById(R.id.textdshow);
        textwshow = (TextView) findViewById(R.id.textwshow);
        textlshow = (TextView) findViewById(R.id.textlshow);
        cdnum = (ImageView) findViewById(R.id.cdnum);
        countDown = (TextView) findViewById(R.id.countDown);
        showlevel = (TextView) findViewById(R.id.showlevel);
        red_heart1 = (ImageView) findViewById(R.id.red_heart1);
        red_heart2 = (ImageView) findViewById(R.id.red_heart2);
        red_heart3 = (ImageView) findViewById(R.id.red_heart3);
        red_heart4 = (ImageView) findViewById(R.id.red_heart4);
        red_heart5 = (ImageView) findViewById(R.id.red_heart5);
        blue_heart1 = (ImageView) findViewById(R.id.blue_heart1);
        blue_heart2 = (ImageView) findViewById(R.id.blue_heart2);
        blue_heart3 = (ImageView) findViewById(R.id.blue_heart3);
        blue_heart4 = (ImageView) findViewById(R.id.blue_heart4);
        blue_heart5 = (ImageView) findViewById(R.id.blue_heart5);
        cdnum = (ImageView) findViewById(R.id.cdnum);
        tiny_imageview_animate = (ImageView) findViewById(R.id.smallanimation);
        main_characters_hand = (ImageView) findViewById(R.id.insertedmovinghandanimation);
        bigbackground = (LinearLayout) findViewById(R.id.bigbackground);
        people = (LinearLayout) findViewById(R.id.people);
        backupbutton.setOnClickListener(myListener);
        backupbutton2.setOnClickListener(myListener);//addnew
        btn_scissors.setOnClickListener(myListener);
        btn_rock.setOnClickListener(myListener);
        btn_paper.setOnClickListener(myListener);
        tiny_imageview_animate.setOnClickListener(myListener);

    }

    public void initialFaceMotion() {
        initial_faceMotion = (AnimationDrawable) ContextCompat.getDrawable(getBaseContext(),
                R.drawable.upanddownmovinghand);
        main_characters_hand.setImageDrawable(initial_faceMotion);
        initial_faceMotion.start();
    }

    public void runningInSmallScreen() {

        forTinyAnimation = (AnimationDrawable) ContextCompat.getDrawable(getBaseContext()
                , R.drawable.runningchoices);
        tiny_imageview_animate.setImageDrawable(forTinyAnimation);
        forTinyAnimation.start();

    }

    private View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            if (v.getId() == R.id.backupbutton2) {
                initial_faceMotion.stop();
                forMainAnimation = (AnimationDrawable) ContextCompat.getDrawable(getBaseContext(),
                        R.drawable.setofwhat);
                main_characters_hand.setImageDrawable(forMainAnimation);
                forMainAnimation.start();
            } else if (v.getId() == R.id.backupbutton) {
                if (level < 2) level++;
                else level = 0;
                switch (level) {
                    case 0:
                        people.setBackgroundResource(R.drawable.a_maincharacter);
                        bigbackground.setBackgroundResource(R.drawable.a_mainbackground);
                        //Toast.makeText(MainActivity.this, "Level1!!", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        people.setBackgroundResource(R.drawable.b_maincharacter);
                        bigbackground.setBackgroundResource(R.drawable.b_mainbackground);
                        //Toast.makeText(MainActivity.this, "Level2!!", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        people.setBackgroundResource(R.drawable.c_maincharacter);
                        bigbackground.setBackgroundResource(R.drawable.c_mainbackground);
                        //Toast.makeText(MainActivity.this, "Level3!!", Toast.LENGTH_SHORT).show();
                        break;
                }

            } else if (v.getId() == R.id.smallanimation) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MyThread myThread = new MyThread();
                        try {
                            myThread.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            myThread.interrupt();
                        }
                    }
                }).start();
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MyThread myThread = new MyThread(v);
                        try {
                            myThread.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            myThread.interrupt();
                        }
                    }
                }).start();
            }

        }
    };

    protected void changeBackground() {
        if (level != 3) {
            //Miao level up OK
            switch (level) {
                case 0:
                    people.setBackgroundResource(R.drawable.a_maincharacter);
                    bigbackground.setBackgroundResource(R.drawable.a_mainbackground);
                    break;
                case 1:
                    people.setBackgroundResource(R.drawable.b_maincharacter);
                    bigbackground.setBackgroundResource(R.drawable.b_mainbackground);
                    break;
                case 2:
                    people.setBackgroundResource(R.drawable.c_maincharacter);
                    bigbackground.setBackgroundResource(R.drawable.c_mainbackground);
                    break;
            }
        }
    }

    public void countDown() {
        if (showResult.getText().equals("You Passed This Level") || showResult.getText().equals("You're dead!")) {
            countDown.setText("10");
            countDownTimer.cancel();
        } else {
            countDownTimer = new CountDownTimer(10000, 1000 - 500) {//先設定倒數30sec的時間(每次減少一秒)
                public void onTick(long ms) { //使用long方法
                    if (Math.round((float) ms / 1000.0f) != time)//如果變數是long必須cast轉型成float除上1000成30秒　
                    {
                        time = Math.round((float) ms / 1000.0f);//不等於為true
                        int[] x = {R.drawable.no00, R.drawable.no01, R.drawable.no02, R.drawable.no03, R.drawable.no04, R.drawable.no05,
                                R.drawable.no06, R.drawable.no07, R.drawable.no08, R.drawable.no09, R.drawable.no10};
                        for (int i = 0; i <= 10; i++) {
                            if (time == 0) cdnum.setBackgroundResource(x[i]);
                        }
                        countDown.setText(String.valueOf(time));
                        countDown.setTextSize(20);
                    }
                    Log.i("test", "ms=" + ms + " till finished=" + time);//跳這行印出測試用
                }

                public void onFinish() {
                    countDown.setText("0");//最後停在0這個數字
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            MyThread autoThread = new MyThread();
                            try {
                                autoThread.start();
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                autoThread.interrupt();
                            }

                        }
                    }).start();
                }
            }.start();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234) {
            if (data.getExtras().getBoolean("restart"))
                cntWin = cntLose = cntDraw = 0;
        }
    }

    public void resetTimer() {
        final Thread cancelCountDownThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (MainActivity.this) {
                    countDownTimer.cancel();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (MainActivity.this) {
                            countDown();
                        }
                    }
                });
            }
        });
        try {
            cancelCountDownThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cancelCountDownThread.interrupt();
        }
        System.gc();
    }

    public void doAction() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        initial_faceMotion.stop();
                        forMainAnimation = (AnimationDrawable) ContextCompat.getDrawable(getBaseContext(),
                                R.drawable.setofscissors);
                        main_characters_hand.setImageDrawable(forMainAnimation);
                        forMainAnimation.start();
                        waitForAction();
                        break;
                    case 1:
                        initial_faceMotion.stop();
                        forMainAnimation = (AnimationDrawable) ContextCompat.getDrawable(getBaseContext(),
                                R.drawable.setofrocks);
                        main_characters_hand.setImageDrawable(forMainAnimation);
                        forMainAnimation.start();
                        waitForAction();
                        break;
                    case 2:
                        initial_faceMotion.stop();
                        forMainAnimation = (AnimationDrawable) ContextCompat.getDrawable(getBaseContext(),
                                R.drawable.setofpapers);
                        main_characters_hand.setImageDrawable(forMainAnimation);
                        forMainAnimation.start();
                        waitForAction();
                        break;
                    case 3:
                        initial_faceMotion.stop();
                        forTinyAnimation.stop();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            forhulkangry = (AnimationDrawable) ContextCompat.getDrawable(getBaseContext(),
                                                    R.drawable.hulkangry);
                                            hulkangry.setImageDrawable(forhulkangry);
                                            forhulkangry.start();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        } finally {
                                            //  Thread.currentThread().interrupt();
                                        }
                                    }
                                });

                            }
                        }).start();

                        break;
                }
            }
        };
    }

    public void waitForAction() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(0, 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        backToInitialFace();
                    }
                });
            }
        }).start();
    }

    public void ifItIsPressedOrNot() {
        isPressed = false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                synchronized (MainActivity.this) {
                    resetTimer();
                    runningInSmallScreen();
                }
            }
        }, 2000);
    }

    public void backToInitialFace() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                forMainAnimation.stop();
                initialFaceMotion();
            }
        }, 2000);
    }

    public void createDialogue() {
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.flyouts_dialogue);
        dialog.setCanceledOnTouchOutside(false);
        next_btn = (Button) dialog.findViewById(R.id.next_btn);
        hulkangry = (ImageView) dialog.findViewById(R.id.hulkangry);
        content = (TextView) dialog.findViewById(R.id.content);
        //Show Text
        if (cntLose >= 5) content.setText("You Lose!");
        else content.setText("You Win!");
        //Hulk Angry Animation
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 3;
                handler.sendMessage(message);
            }
        }).start();


    }

    // count down to 0 computer will play by itself
    private class MyThread extends Thread {
        View v;
        Message message = new Message();

        public MyThread(View v) {
            this.v = v;
        }

        public MyThread() {
        }

        @Override
        public void run() {
            super.run();
            try {
                synchronized (MainActivity.this) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //Miao控制勝率, 變數playerResult:User 0剪,1石,2布, winrate,winpercent
                            if (v != null) rateplayerResult = Integer.parseInt((String) v.getTag());
                            else rateplayerResult = (int) (Math.random() * 3);

                            winrate = (int) ((Math.random() * 10) + 1);
                            radom2select1 = (int) (Math.random() * 1);
                            switch (level) {
                                case 0:
                                    winpercent = 7;//第一關User勝率改這裡, 10表100%勝, 8表80%勝
                                    break;
                                case 1:
                                    winpercent = 5;//第二關勝率改這裡, 10表100%勝, 8表80%勝
                                    break;
                                case 2:
                                    winpercent = 3;//第三關User勝率改這裡, 10表100%勝, 8表80%勝
                                    break;
                            }

                            //Miao 判斷User出拳
                            switch (rateplayerResult) {
                                case 0:
                                    if (winrate <= winpercent) {
                                        computerResult = 2;
                                    } else {
                                        computerResult = radom2select1;
                                    }
                                    message.what = computerResult;
                                    handler.sendMessage(message);
                                    backToInitialFace();
                                    break;
                                case 1:
                                    if (winrate <= winpercent) {
                                        computerResult = 0;
                                    } else {
                                        computerResult = (int) (radom2select1 + 1);
                                    }
                                    message.what = computerResult;
                                    handler.sendMessage(message);
                                    backToInitialFace();
                                    break;
                                case 2:
                                    if (winrate <= winpercent) {
                                        computerResult = 1;
                                    } else {
                                        if (radom2select1 == 0) computerResult = 0;
                                        else computerResult = (int) (radom2select1 + 1);
                                    }
                                    message.what = computerResult;
                                    handler.sendMessage(message);
                                    backToInitialFace();
                                    break;
                            }

                        }
                    });
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (v != null) playerResult = Integer.parseInt((String) v.getTag());
                            else playerResult = (int) (Math.random() * 3);
                            int[][] winLoseTable = {{0, -1, 1}, {1, 0, -1}, {-1, 1, 0}};
                            switch (winLoseTable[playerResult][computerResult]) {
                                case -1:
                                    ++cntLose;
                                    computerScore++;
                                    textlshow.setText(String.valueOf(cntLose));
                                    showResult.setText(R.string.lose);
//                                    Toast.makeText(MainActivity.this, "I lose " + String.valueOf(cntLose) + "game", Toast.LENGTH_SHORT).show();
                                    if (cntLose <= 5) {
                                        switch (cntLose) {
                                            case 1:
                                                red_heart1.setImageDrawable(null);
                                                humanScore -= 2;
                                                break;
                                            case 2:
                                                red_heart2.setImageDrawable(null);
                                                humanScore -= 2;
                                                break;
                                            case 3:
                                                red_heart3.setImageDrawable(null);
                                                humanScore -= 2;
                                                break;
                                            case 4:
                                                red_heart4.setImageDrawable(null);
                                                humanScore -= 2;
                                                break;
                                            case 5:
                                                red_heart5.setImageResource(0);
                                                showResult.setText("You're dead!");
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        createDialogue();
                                                        dialog.setTitle("ha ha ha...YOU LOST...");
                                                        next_btn.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                finishAffinity();
                                                                startActivityForResult(new Intent(MainActivity.this, ScoreBoard.class)
                                                                        .putExtra("wincount", cntWin)
                                                                        .putExtra("losecount", cntLose)
                                                                        .putExtra("drawncount", cntDraw), 1234);
                                                            }
                                                        });
                                                        dialog.show();
                                                    }
                                                }, 1000);
                                                humanScore -= 2;
                                                countDownTimer.cancel();//addnew----------
                                                break;
                                            default:
                                        }
                                    } else break;
                                    break;
                                case 0:
                                    showResult.setText(R.string.draw);
                                    ++cntDraw;
                                    textdshow.setText(String.valueOf(cntDraw));
                                    computerScore = computerScore + 0;
                                    humanScore = humanScore + 0;
                                    break;
                                case 1:
                                    showResult.setText(R.string.win);
                                    ++cntWin;
                                    textwshow.setText(String.valueOf(cntWin));
//                                    Toast.makeText(MainActivity.this, "I won " + String.valueOf(cntWin) + "game", Toast.LENGTH_SHORT).show();
                                    aiBlood = cntWin % 5;//add new
                                    if (aiBlood <= 5) {
                                        switch (aiBlood) {
                                            case 1:
                                                blue_heart5.setImageDrawable(null);
                                                blue_heart5.setBackgroundResource(0);
                                                humanScore += 3;
                                                break;
                                            case 2:
                                                blue_heart4.setImageDrawable(null);
                                                blue_heart4.setBackgroundResource(0);
                                                humanScore += 3;
                                                break;
                                            case 3:
                                                blue_heart3.setImageDrawable(null);
                                                blue_heart3.setBackgroundResource(0);
                                                humanScore += 3;
                                                break;
                                            case 4:
                                                blue_heart2.setImageDrawable(null);
                                                blue_heart2.setBackgroundResource(0);
                                                humanScore += 3;
                                                break;
                                            case 0:
                                                textdshow.setText(String.valueOf(cntDraw));
                                                blue_heart1.setImageResource(0);
                                                blue_heart1.setBackgroundResource(0);
                                                showResult.setText("You Passed This Level");
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        createDialogue();
                                                        dialog.setTitle("ha ha ha...YOU LOST...");
                                                        next_btn.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                level++;//add new
                                                                switch (level) {
                                                                    case 1:
                                                                        dialog.dismiss();
                                                                        changeBackground();
                                                                        aiBlood = 5;
                                                                        blue_heart1.setBackgroundResource(R.drawable.blue_heart);
                                                                        blue_heart2.setBackgroundResource(R.drawable.blue_heart);
                                                                        blue_heart3.setBackgroundResource(R.drawable.blue_heart);
                                                                        blue_heart4.setBackgroundResource(R.drawable.blue_heart);
                                                                        blue_heart5.setBackgroundResource(R.drawable.blue_heart);
                                                                        break;
                                                                    case 2:
                                                                        dialog.dismiss();
                                                                        changeBackground();
                                                                        aiBlood = 5;
                                                                        blue_heart1.setBackgroundResource(R.drawable.blue_heart);
                                                                        blue_heart2.setBackgroundResource(R.drawable.blue_heart);
                                                                        blue_heart3.setBackgroundResource(R.drawable.blue_heart);
                                                                        blue_heart4.setBackgroundResource(R.drawable.blue_heart);
                                                                        blue_heart5.setBackgroundResource(R.drawable.blue_heart);
                                                                        break;

                                                                    case 3:
                                                                        startActivityForResult(new Intent(MainActivity.this, ScoreBoard.class)
                                                                                .putExtra("wincount", humanScore)
                                                                                .putExtra("losecount", cntLose)
                                                                                .putExtra("drawncount", cntDraw), 1234);
                                                                        break;
                                                                }

                                                            }
                                                        });
                                                        dialog.show();
                                                    }
                                                }, 1000);
                                                humanScore += 3;
                                                break;
                                            default:
                                        }
                                    } else break;
                                    break;
                            }
                        }
                    });
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (v != null) playerResult = Integer.parseInt((String) v.getTag());
                            else playerResult = (int) (Math.random() * 3);
                            switch (String.valueOf(playerResult)) {
                                case "0":
                                    isPressed = true;
                                    if (isPressed == true) {
                                        btn_scissors.setEnabled(false);
                                        tiny_imageview_animate.setImageResource(R.drawable.btn_scissors);
                                        ifItIsPressedOrNot();
                                    }
                                    btn_scissors.setEnabled(true);
                                    break;
                                case "1":
                                    isPressed = true;
                                    if (isPressed == true) {
                                        btn_rock.setEnabled(false);
                                        tiny_imageview_animate.setImageResource(R.drawable.btn_rock);
                                        ifItIsPressedOrNot();
                                    }
                                    btn_rock.setEnabled(true);
                                    break;
                                case "2":
                                    isPressed = true;
                                    if (isPressed == true) {
                                        btn_paper.setEnabled(false);
                                        tiny_imageview_animate.setImageResource(R.drawable.btn_paper);
                                        ifItIsPressedOrNot();
                                    }
                                    btn_paper.setEnabled(true);
                                    break;
                            }
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Thread.currentThread().isAlive()) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Thread.currentThread().isAlive()) {
            Thread.currentThread().interrupt();
        }
    }
}
