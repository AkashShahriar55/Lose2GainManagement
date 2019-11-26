package com.example.lose2gainmanagement.ui.form;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lose2gainmanagement.MainActivity;
import com.example.lose2gainmanagement.R;
import com.example.lose2gainmanagement.ui.form.clientDatabase.ClientEntity;
import com.example.lose2gainmanagement.ui.form.clientDatabase.ClientViewModel;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import static com.yalantis.ucrop.UCropFragment.TAG;

public class ClientImage extends Fragment implements ActivityCompat.OnRequestPermissionsResultCallback {


    private Context context;
    private static final int REQUEST_IMAGE = 100;
    private ImageView imageView;
    private ClientEntity client;
    private Button add_client;
    private ClientViewModel viewModel;
    private int client_image_flag = 0;
    private Bitmap bitmap;
    private static final int PERMISSION_REQUEST_CODE = 1;

    public ClientImage() {
    }

    public ClientImage(Context context, ClientEntity client, ClientViewModel viewModel) {
        this.context = context;
        this.client = client;
        this.viewModel =viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.form_client_pic,container,false);

        CardView client_pic = rootView.findViewById(R.id.client_pic);
        imageView = rootView.findViewById(R.id.client_image);



        CardView edit_pic = rootView.findViewById(R.id.edit_pic);
        add_client = rootView.findViewById(R.id.add_client);

        client_pic.setOnClickListener(view -> selectImage());

        edit_pic.setOnClickListener(view -> selectImage());

        add_client.setOnClickListener(view -> {
            client.setPriority("1");
            if(client_image_flag == 0){
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){


                    if (checkPermission())
                    {

                        /*** If Storage Permission Is Given, Check External storage is available for read and write***/

                        if(isExternalStorageWritable()){


                            if (client.getSex().equals("Female")){
                                Bitmap myBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_avatar_female);
                                saveToInternalStorage(myBitmap);
                            }
                            else{
                                Bitmap myBitmap  = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_avatar_male);
                                saveToInternalStorage(myBitmap);
                            }
                        }
                        else{

                            Toast.makeText(context,"External Storage is Not Available For Write",Toast.LENGTH_SHORT).show();

                        }




                    } else {

                        requestPermission();
                    }

                }

                else{
                    Toast.makeText(context,"Permission Is Granted..",Toast.LENGTH_SHORT).show();

                }
            }
            viewModel.insert(client);
        });

        return rootView;
    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }


    private void requestPermission() {

        ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {




                /*** If Storage Permission Is Given, Check External storage is available for read and write***/

                if (isExternalStorageWritable()) {

                    if (client.getSex().equals("Female")){
                        Bitmap myBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_avatar_female);
                        saveToInternalStorage(myBitmap);
                    }
                    else{
                        Bitmap myBitmap  = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_avatar_male);
                        saveToInternalStorage(myBitmap);
                    }

                } else {

                    Toast.makeText(context, "External Storage is Not Available For Write", Toast.LENGTH_SHORT).show();

                }
            }

            else {

                Toast.makeText(context, "Permission Denied... \n You Should Allow External Storage Permission To Download Images.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void chageAvatar(){


        if(client.getSex().equals("Female")){
            imageView.setBackgroundResource(R.drawable.ic_avatar_female);
            //Log.d("Sex","Female");
        }
        else {
            imageView.setBackgroundResource(R.drawable.ic_avatar_male);
            //Log.d("Sex","male");
        }
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(context, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(context, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(context, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
                try {
                    // You can update this bitmap to your server
                    bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
                    client_image_flag = 1;
                    saveToInternalStorage(bitmap);

                    // loading profile image from local cache
                    loadProfile(uri.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }


    private void loadProfile(String url) {
        Log.d(TAG, "Image cache path: " + url);

        GlideApp.with(this).load(url)
                .into(imageView);
        //imageView.setColorFilter(ContextCompat.getColor(context, android.R.color.transparent));
    }

    private void selectImage() {
        Dexter.withActivity((Activity) context)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            showImagePickerOptions();
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }


    private void saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(context.getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("ClientImageDir", Context.MODE_PRIVATE);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        // Create imageDir
        String fileName = String.format(client.getName() + "_%d.jpg",System.currentTimeMillis());
        File mypath=new File(directory,fileName);
        client.setClient_image(fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //return directory.getAbsolutePath();

        client.setClient_image_directory(directory.getAbsolutePath());
        Log.d("directory",directory.getAbsolutePath());
        Log.d("image",fileName);
    }



}
