package com.tianzl.vitamio.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;
import android.provider.MediaStore;

import com.tianzl.vitamio.been.VideoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianzl on 2017/8/21.
 */

public class Utils {
    public static final String INTENT_VIDEO="VIDEOINFO";

    public static List<VideoInfo> getVideoList(Context context) {
        List<VideoInfo> mData = new ArrayList<>();
        String[] attr = new String[]{
                MediaStore.MediaColumns.DATA,
                BaseColumns._ID,
                MediaStore.MediaColumns.TITLE,
                MediaStore.MediaColumns.MIME_TYPE,
                MediaStore.Video.VideoColumns.DURATION,
                MediaStore.MediaColumns.SIZE
        };
        Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, attr,
                null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                VideoInfo info = new VideoInfo();
                info.setFilePath(cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)));
                info.setMimeType(cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.MediaColumns.MIME_TYPE)));
                info.setTitle(cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.MediaColumns.TITLE)));
                info.setTime(CommTools.LongToHms(cursor.getInt(cursor
                        .getColumnIndexOrThrow(MediaStore.Video.VideoColumns.DURATION))));
                info.setSize(CommTools.LongToPoint(cursor
                        .getLong(cursor
                                .getColumnIndexOrThrow(MediaStore.MediaColumns.SIZE))));
                int id = cursor.getInt(cursor
                        .getColumnIndexOrThrow(BaseColumns._ID));
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inDither = false;
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                info.setB(MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(), id,
                        MediaStore.Images.Thumbnails.MICRO_KIND, options));
                mData.add(info);

            }
        }
        return mData;
    }
}
