package com.cynoteck.petofyOPHR.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.print.PDFPrint;
import android.print.PrintAttributes;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;


import com.cynoteck.petofyOPHR.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tejpratapsingh.pdfcreator.utils.FileManager;
import com.tejpratapsingh.pdfcreator.utils.PDFUtil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLDecoder;

public class PdfEditorActivity extends AppCompatActivity{
    private static final String TAG = "PdfEditorActivity";

    private WebView webView;
    File pdfFile;
    String encyptId ="";

    public static class MyWebViewClient extends WebViewClient {

        public interface OnSourceReceived {
            void success(String html);
        }

        private final OnSourceReceived onSourceReceived;

        public MyWebViewClient(OnSourceReceived onSourceReceived) {
            this.onSourceReceived = onSourceReceived;
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("source://")) {
                try {
                    String html = URLDecoder.decode(url, "UTF-8").substring(9);
                    onSourceReceived.success(html);
                } catch (UnsupportedEncodingException e) {
                    Log.e("example", "failed to decode source", e);
                }
                return true;
            }
            // For all other links, let the WebView do it's normal thing
            return false;
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_editor);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Pdf Editor");
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                    .getColor(R.color.colorTransparentBlack)));
        }

        Bundle extras = getIntent().getExtras();
        encyptId = extras.getString("encryptId");

        webView = (WebView) findViewById(R.id.webViewEditor);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(true);
        webView.setWebViewClient(new MyWebViewClient(new MyWebViewClient.OnSourceReceived() {
            @Override
            public void success(String html) {
                Log.d(TAG, "success: html: " + html);
                FileManager.getInstance().cleanTempFolder(getApplicationContext());
                // Create Temp File to save Pdf To
                pdfFile  = FileManager.getInstance().createTempFile(getApplicationContext(), "pdf", false);
                // Generate Pdf From Html
                PDFUtil.generatePDFFromWebView(pdfFile, webView, new PDFPrint.OnPDFPrintListener() {
                    @Override
                    public void onSuccess(File file) {
                        Uri pdfUri = Uri.fromFile(pdfFile);

                        Intent intentPdfViewer = new Intent(PdfEditorActivity.this, PdfViewerActivity.class);
                        intentPdfViewer.putExtra(PdfViewerActivity.PDF_FILE_URI, pdfUri);

                        startActivity(intentPdfViewer);
                    }

                    @Override
                    public void onError(Exception exception) {
                        exception.printStackTrace();
                    }
                });
            }
        }));
        Log.e("URL","https://testing.petofy.com/PetHealthRecord/DoctorsPrescription?encryptedId="+encyptId+"&&status=1");
        webView.loadUrl("https://testing.petofy.com/PetHealthRecord/DoctorsPrescription?encryptedId="+encyptId+"&&status=1");

//        Log.e("URL","https://www.petofy.com/PetHealthRecord/DoctorsPrescription?encryptedId="+encyptId+"&&status=1");
//        webView.loadUrl("https://www.petofy.com/PetHealthRecord/DoctorsPrescription?encryptedId="+encyptId+"&&status=1");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pdf_editor, menu);
        // return true so that the menu pop up is opened
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.menuPrintPdf) {
            webView.loadUrl("javascript:this.document.location.href = 'source://' + encodeURI(document.documentElement.outerHTML);");
        }
        return super.onOptionsItemSelected(item);
    }
}