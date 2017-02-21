package com.wyt.list.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vincent.filepicker.Constant;
import com.vincent.filepicker.activity.AudioPickActivity;
import com.vincent.filepicker.activity.ImagePickActivity;
import com.vincent.filepicker.activity.NormalFilePickActivity;
import com.vincent.filepicker.activity.VideoPickActivity;
import com.vincent.filepicker.filter.entity.AudioFile;
import com.vincent.filepicker.filter.entity.ImageFile;
import com.vincent.filepicker.filter.entity.NormalFile;
import com.vincent.filepicker.filter.entity.VideoFile;
import com.wyt.list.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilePickerActivity extends AppCompatActivity {

    @BindView(R.id.btn_pick_image)
    Button btnPickImage;
    @BindView(R.id.btn_pick_video)
    Button btnPickVideo;
    @BindView(R.id.btn_pick_audio)
    Button btnPickAudio;
    @BindView(R.id.btn_pick_file)
    Button btnPickFile;
    @BindView(R.id.tv_pick_info)
    TextView tvPickInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_picker);
        ButterKnife.bind(this);
        setTitle(getIntent().getStringExtra("title"));
    }

    @OnClick({R.id.btn_pick_image, R.id.btn_pick_video, R.id.btn_pick_audio, R.id.btn_pick_file})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pick_image:
                Intent intent1 = new Intent(this, ImagePickActivity.class);
                intent1.putExtra(ImagePickActivity.IS_NEED_CAMERA, true);
                intent1.putExtra(Constant.MAX_NUMBER, 9);
                startActivityForResult(intent1, Constant.REQUEST_CODE_PICK_IMAGE);
                break;
            case R.id.btn_pick_video:
                Intent intent2 = new Intent(this, VideoPickActivity.class);
                intent2.putExtra(VideoPickActivity.IS_NEED_CAMERA, true);
                intent2.putExtra(Constant.MAX_NUMBER, 9);
                startActivityForResult(intent2, Constant.REQUEST_CODE_PICK_VIDEO);
                break;
            case R.id.btn_pick_audio:
                Intent intent3 = new Intent(this, AudioPickActivity.class);
                intent3.putExtra(AudioPickActivity.IS_NEED_RECORDER, true);
                intent3.putExtra(Constant.MAX_NUMBER, 9);
                startActivityForResult(intent3, Constant.REQUEST_CODE_PICK_AUDIO);
                break;
            case R.id.btn_pick_file:
                Intent intent4 = new Intent(this, NormalFilePickActivity.class);
                intent4.putExtra(Constant.MAX_NUMBER, 9);
                intent4.putExtra(NormalFilePickActivity.SUFFIX, new String[]{"xlsx", "xls", "doc", "docx", "ppt", "pptx", "pdf"});
                startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constant.REQUEST_CODE_PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    ArrayList<ImageFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_IMAGE);
                    tvPickInfo.setText("");
                    for (ImageFile imageFile : list) {
                        tvPickInfo.append(imageFile.getName() + " : " + imageFile.getPath() + "\n");
                    }
                }
                break;
            case Constant.REQUEST_CODE_PICK_VIDEO:
                if (resultCode == RESULT_OK) {
                    ArrayList<VideoFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_VIDEO);
                    tvPickInfo.setText("");
                    for (VideoFile videoFile : list) {
                        tvPickInfo.append(videoFile.getName() + " : " + videoFile.getPath() + "\n");
                    }
                }
                break;
            case Constant.REQUEST_CODE_PICK_AUDIO:
                if (resultCode == RESULT_OK) {
                    ArrayList<AudioFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_AUDIO);
                    tvPickInfo.setText("");
                    for (AudioFile audioFile : list) {
                        tvPickInfo.append(audioFile.getName() + " : " + audioFile.getPath() + "\n");
                    }
                }
                break;
            case Constant.REQUEST_CODE_PICK_FILE:
                if (resultCode == RESULT_OK) {
                    ArrayList<NormalFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_FILE);
                    tvPickInfo.setText("");
                    for (NormalFile normalFile : list) {
                        tvPickInfo.append(normalFile.getName() + " : " + normalFile.getPath() + "\n");
                    }
                }
                break;
        }
    }
}
