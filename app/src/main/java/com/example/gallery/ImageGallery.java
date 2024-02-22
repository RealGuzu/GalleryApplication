package com.example.gallery;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ImageGallery {

    public static ArrayList<String> listOfImages(Context context) {
        ArrayList<String> listOfAllImages = new ArrayList<>();
        Uri uri;
        Cursor cursor = null;
        String absolutePathOfImage;

        // Check for permission to access external storage
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Handle permission not granted
            return listOfAllImages; // Return an empty list
        }

        try {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

            String[] projection = {MediaStore.MediaColumns.DATA,
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

            String orderBy = MediaStore.Video.Media.DATE_TAKEN + " DESC"; // Add space before DESC
            cursor = context.getContentResolver().query(uri, projection, null,
                    null, orderBy);

            if (cursor != null) {
                int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

                while (cursor.moveToNext()) {
                    absolutePathOfImage = cursor.getString(column_index_data);

                    listOfAllImages.add(absolutePathOfImage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close(); // Close the cursor to release resources
            }
        }

        return listOfAllImages;
    }
}
