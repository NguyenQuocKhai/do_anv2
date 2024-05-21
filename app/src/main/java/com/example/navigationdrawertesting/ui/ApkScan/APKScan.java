package com.example.navigationdrawertesting.ui.ApkScan;


import static android.app.Activity.RESULT_OK;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.navigationdrawertesting.R;

public class APKScan extends Fragment {
    //variables (id)
    final int ACTIVITY_CHOOSE_FILE = 1;
    String selectedFilePath = null;
    Button fileSelectorButton;
    TextView selectedFileTextView;
    Button uploadButton;
    TextView scanCompleteTextView;
    Button downloadButton;
    TextView downloadedTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate the layout for this fragment( callout the content of the layout to this fragement)
        View view = inflater.inflate(R.layout.fragment_scan_apk, container, false);

        // Initialize views
        fileSelectorButton = view.findViewById(R.id.fileSelectorButton);
        selectedFileTextView = view.findViewById(R.id.selectedFileTextView);
        uploadButton = view.findViewById(R.id.uploadButton);
        scanCompleteTextView = view.findViewById(R.id.scanCompleteTextView);
        downloadButton = view.findViewById(R.id.downloadButton);
        downloadedTextView = view.findViewById(R.id.downloadedSizeTextView);

        // Setup click listeners and logic
        setupClickListeners();

        return view;
    }

    private void setupClickListeners() {
        fileSelectorButton.setOnClickListener(v -> {
            Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
            chooseFile.setType("*/*");
            startActivityForResult(Intent.createChooser(chooseFile, "Choose a file"), ACTIVITY_CHOOSE_FILE);
        });

        uploadButton.setOnClickListener(v -> {
            if (selectedFilePath != null) {
                // Show progress dialog in a Fragment context, you can use getActivity() to retrieve context
                Toast.makeText(getActivity(), "Start upload and scan process", Toast.LENGTH_SHORT).show();
                // Implement actual upload and scan logic here
            } else {
                Toast.makeText(getActivity(), "Please select a file to upload!!", Toast.LENGTH_LONG).show();
            }
        });

        downloadButton.setOnClickListener(v -> {
            // Simulate delay and download logic
            new Handler().postDelayed(() -> {
                // Placeholder for actual download logic
                Toast.makeText(getActivity(), "Simulate download", Toast.LENGTH_SHORT).show();
            }, 30000); // 30-second delay
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_CHOOSE_FILE && resultCode == getActivity().RESULT_OK && data != null) {
            Uri uri = data.getData();
            selectedFilePath = getPathFromUri(getActivity(), uri);
            selectedFileTextView.setText("Selected File: " + selectedFilePath);
        }
    }

    // Methods to handle URI and get path from URI, similar to the original activity
    public static String getPathFromUri(Context context, Uri uri) {
        // Implement getPathFromUri logic similar to the original activity, ensure it is using the context properly
        return null; // Placeholder
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = "_data";
        String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(columnIndex);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

}
