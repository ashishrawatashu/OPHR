package com.cynoteck.petofyOPHR.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MediaUtils {

    Activity mActivity;
    Fragment mFragment;
    private final GetImg mGetImg;
    private final int REQ_CAMERA = 101;
    private final int REQ_GALLERY = 102;
    private Uri imageUri;
    private String imageFilePath="";


    public MediaUtils(Activity activity) {
        mActivity = activity;
        mGetImg = (GetImg) activity;
    }

    public MediaUtils(Fragment fragment) {
        mActivity = fragment.getActivity();
        mFragment = fragment;
        mGetImg = (GetImg) fragment;
    }

    public void openGallery() {
        Intent intent2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent2.setType("image/*");
        if(mFragment == null) {
            mActivity.startActivityForResult(intent2, REQ_GALLERY);
        }else{
            mFragment.startActivityForResult(intent2, REQ_GALLERY);
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void openCamera() {
            Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (pictureIntent.resolveActivity(mActivity.getPackageManager()) != null) {
                //Create a file to store the image
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(mActivity, "com.cynoteck.petofyOPHR.fileprovider", photoFile);
                    pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    if(mFragment == null) {
                        mActivity.startActivityForResult(pictureIntent, REQ_CAMERA);
                    }else{
                        mFragment.startActivityForResult(pictureIntent, REQ_CAMERA);
                    }
                }
            }
    }

    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = mActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQ_CAMERA) {
                //camera
//                String imgPath = getPath(mActivity, imageUri);
                Log.e("CAMERA","CAMERA");
                mGetImg.imgdata(imageFilePath);
            } else {
                //gallery
                imageUri = data.getData();
                String imgPath = getPath(mActivity, imageUri);
                mGetImg.imgdata(imgPath);
            }
        }
    }

    public String getPath(Context context, Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        int column_index = 0;
        if (cursor != null) {
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        } else
            return uri.getPath();
    }

    public interface GetImg {
        void imgdata(String imgPath);
    }

}
