package im.dnn.labs.mjbestsongs.helpers;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;

import im.dnn.labs.mjbestsongs.helpers.Http.Client;

public class Picloader {
    private Bitmap bmap;
    private ImageView image;
    private Drawable imgError;

    private Boolean hasError = false;

    public Picloader (ImageView image) {
        this.image = image;
    }

    public Picloader load (String path) {
        try {

            Client client = new Client (path);
            bmap = client.image ();

        } catch (InterruptedException e) {
            hasError = true;
            e.printStackTrace();
        } catch (ExecutionException e) {
            hasError = true;
            e.printStackTrace();
        }

        return this;
    }

    public Picloader render () {
        if (!hasError) {
            image.setImageBitmap (bmap);
        } else {
            image.setImageDrawable (imgError);
        }
        return this;
    }

    public Picloader placeholder (Drawable img) {
        image.setImageDrawable (img);
        return this;
    }

    public Picloader error (Drawable img) {
        imgError = img;
        return this;
    }

}
