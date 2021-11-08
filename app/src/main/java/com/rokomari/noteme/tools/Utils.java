package com.rokomari.noteme.tools;

import android.app.Activity;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class Utils {

    public static void openEmailApp(String emailAddress, Activity activity, Context context){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType(ClipDescription.MIMETYPE_TEXT_PLAIN);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
        intent.putExtra(android.content.Intent.EXTRA_TEXT, emailAddress);
        activity.startActivity(Intent.createChooser(intent,"Send Email"));
    }

    public static void openDialler(String phoneNumber,Activity activity){
        activity.startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    public static void openURLInBrowser(String url,Activity activity){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        activity.startActivity(intent);
    }

}
