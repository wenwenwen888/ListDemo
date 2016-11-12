package com.wyt.list.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wyt.list.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Won on 2016/10/17.
 */

public class TimerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView info;
    private Button pause, reset;

    //时间
    private Timer timer = null;
    //秒分时
    private int second = 0, minute = 0, hour = 0;
    private String time_str;
    //停止计数
    private boolean isPause = false;
    //标识
    private final int TIMING = 0x00;//计时中
    private final int TIMIEOUT = 0x01;//计时完成

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TIMING:
                    info.setText(time_str);
                    break;
                case TIMIEOUT:
                    info.setText("时间到!");
                    isPause = true;
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 时间线程
     */
    private TimerTask task = new TimerTask() {
        public void run() {
            if (!isPause) {//没停止才+1s
                Message message = new Message();
                second++;
                if (second >= 60) {
                    minute++;
                    second = 0;
                }
                if (minute >= 60) {
                    hour++;
                    minute = 0;
                }
                if (hour >= 3) {
                    message.what = TIMIEOUT;
                } else {
                    message.what = TIMING;
                    time_str = getTimeStr(hour) + "时" + getTimeStr(minute) + "分" + getTimeStr(second) + "秒";
                }
                handler.sendMessage(message);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        setTitle(getIntent().getStringExtra("title"));

        info = (TextView) findViewById(R.id.tv_timer_info);
        pause = (Button) findViewById(R.id.bt_timer_pause);
        reset = (Button) findViewById(R.id.bt_timer_reset);

        info.setOnClickListener(this);
        pause.setOnClickListener(this);
        reset.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_timer_info:
                if (info.getText().equals("点我开始") || info.getText().equals("点我重新开始")) {
                    startTimer();
                }
                break;
            case R.id.bt_timer_pause:
                if (pause.getText().equals("暂停")) {
                    pauseTimer();
                } else {
                    startTimer();
                }
                break;
            case R.id.bt_timer_reset:
                resetTimer();
                break;
            default:
                break;
        }
    }

    /**
     * 开始计时
     */
    private void startTimer() {
        if (timer == null) {
            isPause = false;
            timer = new Timer(true);
            timer.schedule(task, 1000, 1000);//延时1000ms后执行，1000ms执行一次
        } else {
            isPause = false;
            pause.setText("暂停");
        }
    }

    /**
     * 暂停计时
     */
    private void pauseTimer() {
        isPause = true;
        pause.setText("继续");
    }

    /**
     * 重置计时
     */
    private void resetTimer() {
        isPause = true;
        second = 0;
        minute = 0;
        hour = 0;
        info.setText("点我重新开始");
    }

    /**
     * 设置时间格式
     */
    private String getTimeStr(int time) {
        String time_str;
        if (time < 10) {
            time_str = "0" + time;
        } else {
            time_str = "" + time;
        }
        return time_str;
    }
}
