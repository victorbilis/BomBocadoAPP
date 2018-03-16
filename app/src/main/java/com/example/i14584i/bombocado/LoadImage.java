package com.example.i14584i.bombocado;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;



import java.io.BufferedReader;
import java.io.InputStream;
import java.net.URL;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;
import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by Matheus on 20/11/2016.
 */
class LoadImage extends AsyncTask<String, String, Bitmap> {
    Bitmap bitmap;
    Context context;
    ImageView img;
    protected ProgressDialog progressDialog;
    AlertDialog alertDialog;

    public LoadImage(Context contexto, ImageView img2) {
        this.context = contexto;
        this.img = img2;
        img.setImageResource(R.drawable.cupcake_loading_dois);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    protected Bitmap doInBackground(String... args) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 7;

            bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent(), null, options);
            // scale it to fit the screen, x and y swapped because my image is wider than it is tall
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 300, 250, true);
            // create a matrix object
            Matrix matrix = new Matrix();
            Bitmap bmp = BitmapFactory.decodeStream(new URL(args[0]).openConnection().getInputStream(), null, options);

            if (bmp.getWidth() > bmp.getHeight()) {
                matrix.postRotate(90); // anti-clockwise by 90 degrees
            } else {

            }

            // create a new bitmap from the original using the matrix to transform the result
            bitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
            // display the rotated bitmap

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap image) {

        if (image != null) {
            img.setImageBitmap(image);


        } else {

            img.setImageBitmap(image);

        }

    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
