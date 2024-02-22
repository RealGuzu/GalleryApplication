package com.example.gallery;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ImageGallery {

        public static ArrayList<String> listOfImages(Context context) {
            Uri uri;
            Cursor cursor;
            int column_index_data, column_index_folder_name;
            ArrayList<String> listOfAllImages = new ArrayList<>();

            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

            String[] projection = {MediaStore.MediaColumns.DATA,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

            String orderBy = MediaStore.Video.Media.DATE_TAKEN;
            cursor = context.getContentResolver().query(uri, projection, null,
                    null, orderBy+ "DESC");

            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

            column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

            while (cursor.moveToNext()){
                ablosutePathOfImage = cursor.getString(column_index_data);

                listOfAllImages.add(ablosutePathOfImage);
            }



        }
}
