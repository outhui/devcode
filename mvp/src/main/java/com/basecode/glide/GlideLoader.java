package com.basecode.glide;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.basecode.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public final class GlideLoader {

    private static void load(ImageView imageView, RequestOptions options, String imageUrl) {
        if (imageUrl == null) {
            Drawable errorPlaceholder = options.getErrorPlaceholder();
            if (errorPlaceholder != null) {
                imageView.setImageDrawable(errorPlaceholder);
            } else {
                int errorId = options.getErrorId();
                if (errorId != 0) {
                    imageView.setImageResource(errorId);
                }
            }
            return;
        }
        if (options != null) {
            Glide.with(imageView.getContext())
                    .load(imageUrl)
                    .apply(options)
                    .into(imageView);
        } else {
            Glide.with(imageView.getContext()).load(imageUrl)
                    .into(imageView);
        }

    }

    @SuppressLint("CheckResult")
    public static void load(ImageView imageView, Drawable drawable, String url, boolean circle, int radius) {
        if (drawable == null) {
            RequestOptions options;
            if (circle) {
                options = new RequestOptions().placeholder(R.drawable.default_circle)
                        .error(R.drawable.default_circle)
                        .dontAnimate()
                        .transform(new CircleCrop());
            } else if (radius != 0) {
                options = bitmapTransform(new RoundedCorners(radius))
                        .placeholder(R.drawable.default_round)
                        .error(R.drawable.default_round).dontAnimate();
            } else {
                options = new RequestOptions().placeholder(R.drawable.default_image)
                        .error(R.drawable.default_image)
                        .dontAnimate();
            }
            load(imageView, options, url);
        } else {
            RequestOptions options;
            if (circle) {
                options = new RequestOptions().placeholder(drawable)
                        .error(drawable)
                        .dontAnimate()
                        .transform(new CircleCrop());
            } else if (radius != 0) {
                options = bitmapTransform(new RoundedCorners(radius))
                        .placeholder(drawable)
                        .error(drawable).dontAnimate();
            } else {
                options = new RequestOptions().placeholder(drawable)
                        .error(drawable)
                        .dontAnimate();
            }
            load(imageView, options, url);
        }
    }
}
