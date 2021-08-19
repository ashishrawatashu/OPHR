package com.cynoteck.petofyOPHR.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.cynoteck.petofyOPHR.R;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Methods {
    private Context c;
    private Dialog progressBarDialog;
    /*public static SweetAlertDialog errorMsgDialog;
    private SweetAlertDialog informationDialog;*/
    ArrayList<String> cars = new ArrayList<>();
    JSONArray jsonArray;
    boolean flag = false;



    public String  addMonths(String dateAsString, int nbMonths) throws ParseException {
        String format = "dd/MM/yyyy" ;
        SimpleDateFormat sdf = new SimpleDateFormat(format) ;
        Date dateAsObj = sdf.parse(dateAsString) ;
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateAsObj);
        cal.add(Calendar.MONTH, nbMonths);
        Date dateAsObjAfterAMonth = cal.getTime() ;
        System.out.println(sdf.format(dateAsObjAfterAMonth));
        return sdf.format(dateAsObjAfterAMonth);
    }
    public Methods(Context c) {
        this.c = c;
    }

    public final boolean isInternetOn() {
        boolean flag = false;
        ConnectivityManager connec = (ConnectivityManager) c.getSystemService(c.CONNECTIVITY_SERVICE);
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            flag = true;


        } else if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            flag = false;
        }
        return flag;
    }

    public void DialogInternet() {

        AlertDialog.Builder ad = new AlertDialog.Builder(c);
        ad.setMessage(R.string.CheckInternet);
        ad.setCancelable(true);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        ad.show();
    }

    public void showCustomProgressBarDialog(Context context) {
        try {
            progressBarDialog = new Dialog(context);
            progressBarDialog.setContentView(R.layout.custom_spin_progress_dialog);
            progressBarDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progressBarDialog.setCancelable(false);
            progressBarDialog.setCanceledOnTouchOutside(false);
            progressBarDialog.show();
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = progressBarDialog.getWindow();
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

//--------------------------------------------------------------------------------------------------------------------
//    private  void showNoConnectionSnackBar(String message, boolean isConnected) {
//
//        if (isConnected){
//            flag = true;
//
//
//        }else{
//            Toast.makeText(c.getApplicationContext(), "Internet is creating Issue", Toast.LENGTH_SHORT).show();
//            DialogInternet();
//
//                }
//            }

//-------------------------------------------------------------------------------------------------------
    public void customProgressDismiss() {
        progressBarDialog.dismiss();
    }

    public String removeLastElement(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    public String pdfGenarator(String petName,String petAge,String petSex,String pet_parent,String strTemparature,String history,String Symptons, String strDiagnosis,String strRemark,String strNxtVisit,String regisNumber) {
        String care="Vet Care";
        String str = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
                "<script src=\"//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js\"></script>\n" +
                "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n" +
                "    \n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css\">\n" +
                "\n" +
                "    <title>Invioce</title>\n" +
                "</head>\n" +
                "<style type=\"text/css\">\n" +
                "    .invoice-title h2, .invoice-title h3 {\n" +
                "        display: inline-block;\n" +
                "    }\n" +
                "\n" +
                "    .table > tbody > tr > .no-line {\n" +
                "        border-top: none;\n" +
                "    }\n" +
                "\n" +
                "    .table > thead > tr > .no-line {\n" +
                "        border-bottom: none;\n" +
                "    }\n" +
                "\n" +
                "    .table > tbody > tr > .thick-line {\n" +
                "        border-top: 2px solid;\n" +
                "    }\n" +
                "    @page {\n" +
                "      size: A4;\n" +
                "      margin: 15px;\n" +
                "    }\n" +
                "</style>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <p><?=date('d/m/Y')?></p> \n" +
                "    <div class=\"row\">\n" +
                "        <div class=\"col-xs-12\">\n" +
                "            <div class=\"invoice-title \">\n" +
                "                <div class=\"row\">\n" +
                "                    <div class=\"col-lg-12 col-md-12 col-xs-12\" style=\"font-size: 25px;font-family: cizel;\">\n" +
                "                       <b>" + Config.user_Veterian_name + "</b> \n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-12 col-md-12 col-xs-12\" style=\"font-size: 20px; margin-bottom: 20px;\">\n" +
                "                        MBBS,MVS \n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-6 col-md-6 col-xs-6\" style=\"font-size: 20px; \" >\n" + care +
                "                       \n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-6 col-md-6 col-xs-6 text-right\" style=\"font-size: 20px;\">\n" +
                "                       <b> Mobile :" + Config.user_Veterian_phone + " </b>\n" +
                "                    </div>\n" +
                "                    \n" +
                "                    <div class=\"col-lg-6 col-md-6 col-xs-6\" style=\"font-size: 17px;\">\n" +
                "                       <b> Email: " + Config.user_Veterian_emial + " </b>\n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-6 col-md-6 col-xs-6 text-right\" style=\"font-size: 20px;\">\n" +
                "                       <b> " + Config.user_Veterian_phone + "</b>\n" +
                "                    </div>\n" +
                "                 \n" +
                "                    \n" +
                "                </div>\n" +
                "               \n" +
                "                \n" +
                "            </div>\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-md-12\" style=\"border: 1px solid black;\"></div>\n" +
                "            </div>\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-xs-3\" style=\"font-size: 20px;\">\n" +
                "                    <b>For a: " + petName + "</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-3\" style=\"font-size: 20px;\">\n" +
                "                    <b>Age: " + petAge + "</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-3\" style=\"font-size: 20px;\">\n" +
                "                    <b>Sex: " + petSex + "</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-3\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Date:"+Config.currentDate()+" <?=date('d/m/Y')?></b>\n" +
                "                </div>\n" +
                "\n" +
                "\n" +
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Pet Parant Name:" + pet_parent + "</b>\n" +
                "                </div>\n" +
                "               <script>\n"+
                "                var Temperature = "+strTemparature+";\n"+
                "                if (!Temperature.equals("+""+")) {\n"+
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 10px;\">\n" +
                "                    <b>Temperature(F): " + strTemparature + "\u2109</b>\n" +
                "                </div>\n" +
                "                   }\n"+
                "               </script>\n"+
                "                <div class=\"col-xs-12\" style=\"font-size: 30px; margin-bottom: 25px;\">\n" +
                "                    <b>Routine/Health Problem/</b>\n" +
                "                </div>\n" +
                "               <script>\n"+
                "                var history = "+history+";\n"+
                "                if (!history.equals("+""+")) {\n"+
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>History:</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-12\" style=\"margin-bottom: 10px;\">\n" +
                "                    <p>" + history + "</p>\n" +
                "                </div>\n" +
                "                   }\n"+
                "               </script>\n"+
                "               <script>\n"+
                "                var Symptoms = "+Symptons+";\n"+
                "                if (!Symptoms.equals("+""+")) {\n"+
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Symptoms:</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-12\" style=\"margin-bottom: 10px;\">\n" +
                "                    <p>" + Symptons + "</p>\n" +
                "                </div>\n" +
                "                   }\n"+
                "               </script>\n"+
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 10px;\">\n" +
                "                    <p>" + strDiagnosis + "</p>\n" +
                "                </div>\n" +
                "               <script>\n"+
                "                var strRemark = "+strRemark+";\n"+
                "                if (!strRemark.equals("+""+")) {\n"+
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Treatment Remarks:</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 10px;\">\n" +
                "                    <p>" + strRemark + "</p>\n" +
                "                </div>\n" +
                "                   }\n"+
                "               </script>\n"+
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Next Visit:</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 10px;\">\n" +
                "                    <p>" + strNxtVisit + "</p>\n" +
                "                </div>\n" +
                "\n" +
                "            </div><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>\n" +
                "            <div class=\"col-md-12\" style=\"border: 1px solid black;\"></div>\n" +
                "            <div class=\"col-md-12\" style=\"font-size: 25px; text-align: center;\">Address: " + Config.user_Veterian_address + ", Registration Number: " + regisNumber + "</div>\n" +
                "            \n" +
                "        </div>\n" +
                "\n" +
                "\n" +
                "    </div>\n" +
                "\n" +
                "</div>\n" +
                "<script type=\"text/javascript\">\n" +
                "    $(function(){\n" +
                "        window.print();\n" +
                "        window.close();\n" +
                "    });\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>";
        return str;
    }

    public String pdfGenaratorDeworming(String petName,String petAge,String petSex,String pet_parent,String strTemparature,String Symptons,String history, String dewormerName,String dewormerdose,String strRemark,String strNxtVisit,String regisNumber,String petWeight) {
        String care="Vet Care";
        String str = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
                "<script src=\"//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js\"></script>\n" +
                "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n" +
                "    \n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css\">\n" +
                "\n" +
                "    <title>Invioce</title>\n" +
                "</head>\n" +
                "<style type=\"text/css\">\n" +
                "    .invoice-title h2, .invoice-title h3 {\n" +
                "        display: inline-block;\n" +
                "    }\n" +
                "\n" +
                "    .table > tbody > tr > .no-line {\n" +
                "        border-top: none;\n" +
                "    }\n" +
                "\n" +
                "    .table > thead > tr > .no-line {\n" +
                "        border-bottom: none;\n" +
                "    }\n" +
                "\n" +
                "    .table > tbody > tr > .thick-line {\n" +
                "        border-top: 2px solid;\n" +
                "    }\n" +
                "    @page {\n" +
                "      size: A4;\n" +
                "      margin: 15px;\n" +
                "    }\n" +
                "</style>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <p><?=date('d/m/Y')?></p> \n" +
                "    <div class=\"row\">\n" +
                "        <div class=\"col-xs-12\">\n" +
                "            <div class=\"invoice-title \">\n" +
                "                <div class=\"row\">\n" +
                "                    <div class=\"col-lg-12 col-md-12 col-xs-12\" style=\"font-size: 25px;font-family: cizel;\">\n" +
                "                       <b>" + Config.user_Veterian_name + "</b> \n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-12 col-md-12 col-xs-12\" style=\"font-size: 20px; margin-bottom: 20px;\">\n" +
                "                        MBBS,MVS \n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-6 col-md-6 col-xs-6\" style=\"font-size: 20px; \" >\n" + care +
                "                       \n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-6 col-md-6 col-xs-6 text-right\" style=\"font-size: 20px;\">\n" +
                "                       <b> Mobile :" + Config.user_Veterian_phone + " </b>\n" +
                "                    </div>\n" +
                "                    \n" +
                "                    <div class=\"col-lg-6 col-md-6 col-xs-6\" style=\"font-size: 17px;\">\n" +
                "                       <b> Email: " + Config.user_Veterian_emial + " </b>\n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-6 col-md-6 col-xs-6 text-right\" style=\"font-size: 20px;\">\n" +
                "                       <b> " + Config.user_Veterian_phone + "</b>\n" +
                "                    </div>\n" +
                "                 \n" +
                "                    \n" +
                "                </div>\n" +
                "               \n" +
                "                \n" +
                "            </div>\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-md-12\" style=\"border: 1px solid black;\"></div>\n" +
                "            </div>\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-xs-3\" style=\"font-size: 20px;\">\n" +
                "                    <b>For a: " + petName + "</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-3\" style=\"font-size: 20px;\">\n" +
                "                    <b>" + petAge + "</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-3\" style=\"font-size: 20px;\">\n" +
                "                    <b>Sex: " + petSex + "</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-3\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Date:"+Config.currentDate()+" <?=date('d/m/Y')?></b>\n" +
                "                </div>\n" +
                "\n" +
                "\n" +
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Pet Parent Name:" + pet_parent + "</b>\n" +
                "                </div>\n" +
                "               <script>\n"+
                "                var temperature = "+""+";\n"+
                "                if (temperature === "+""+") {\n"+
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 10px;\">\n" +
                "                    <b>Temperature(F): " + strTemparature + "\u2109</b>\n" +
                "                </div>\n" +
                "                   }\n"+
                "               </script>\n"+
                "                <div class=\"col-xs-12\" style=\"font-size: 30px; margin-bottom: 25px;\">\n" +
                "                    <b>DEWORMING</b>\n" +
                "                </div>\n" +
                "               <script>\n"+
                "                var history = "+history+";\n"+
                "                if (!history.equals("+""+")) {\n"+
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>History:</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-12\" style=\"margin-bottom: 10px;\">\n" +
                "                    <p>" + history + "</p>\n" +
                "                </div>\n" +
                "                   }\n"+
                "               </script>\n"+
                "               <script>\n"+
                "                var Symptoms = "+Symptons+";\n"+
                "                if (!Symptoms.equals("+""+")) {\n"+
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Symptoms:</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-12\" style=\"margin-bottom: 10px;\">\n" +
                "                    <p>" + Symptons + "</p>\n" +
                "                </div>\n" +
                "                   }\n"+
                "               </script>\n"+

                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Dewormer Name:</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 10px;\">\n" +
                "                    <p>" + dewormerName + " "+dewormerdose+"</p>\n" +
                "                </div>\n" +
                "               <script>\n"+
                "                var strRemark = "+strRemark+";\n"+
                "                if (!strRemark.equals("+""+")) {\n"+
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Treatment Remarks:</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-12\" style=\"margin-bottom: 10px;\">\n" +
                "                    <p>" + strRemark + "</p>\n" +
                "                </div>\n" +
                "                   }\n"+
                "               </script>\n"+
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Next Visit:</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 10px;\">\n" +
                "                    <p>" + strNxtVisit + "</p>\n" +
                "                </div>\n" +
                "\n" +
                "            </div><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>\n" +
                "            <div class=\"col-md-12\" style=\"border: 1px solid black;\"></div>\n" +
                "            <div class=\"col-md-12\" style=\"font-size: 25px; text-align: center;\">Address: " + Config.user_Veterian_address + ", Registration Number: " + regisNumber + "</div>\n" +
                "            \n" +
                "        </div>\n" +
                "\n" +
                "\n" +
                "    </div>\n" +
                "\n" +
                "</div>\n" +
                "<script type=\"text/javascript\">\n" +
                "    $(function(){\n" +
                "        window.print();\n" +
                "        window.close();\n" +
                "    });\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>";
        return str;
    }

    public String immunizationPdfGenarator(String petName, String petAge, String petSex, String petParent , String regisNumber, JSONArray immunizationDate, JSONArray vaccineClass, JSONArray nextDueDate, JSONArray immunizationDatePending, JSONArray vaccineClassPending, JSONArray nextDueDatePending ) {

        String care="Vet Care";
        String str = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
                "<script src=\"//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js\"></script>\n" +
                "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n" +
                "    \n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css\">\n" +
                "\n" +
                "    <title>Invioce</title>\n" +
                "</head>\n" +
                "<style type=\"text/css\">\n" +
                "    .invoice-title h2, .invoice-title h3 {\n" +
                "        display: inline-block;\n" +
                "    }\n" +
                "\n" +
                "    .table > tbody > tr > .no-line {\n" +
                "        border-top: none;\n" +
                "    }\n" +
                "\n" +
                "    .table > thead > tr > .no-line {\n" +
                "        border-bottom: none;\n" +
                "    }\n" +
                "\n" +
                "    .table > tbody > tr > .thick-line {\n" +
                "        border-top: 2px solid;\n" +
                "    }\n" +
                "    @page {\n" +
                "      size: A4;\n" +
                "      margin: 15px;\n" +
                "    }\n" +
                "  table {\n" +
                "      width: 100%;\n" +
                "   }\n" +
                "           th {\n" +
                "      width: 25%;\n" +
                "     background: #ffff;\n" +
                "    color: #808080;\n" +
                "     padding: 10px;\n" +
                "     border: 1px #000000 solid;\n" +
                "    border-radius: 1px;\n" +
                "  }\n" +
                "  td {\n" +
                "     width: 25%;\n" +
                "     padding: 10px;\n" +
                "    border: 1px #000000 solid;\n" +
                "   border-radius: 1px;\n" +
                "  }\n" +
                "</style>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <p><?=date('d/m/Y')?></p> \n" +
                "    <div class=\"row\">\n" +
                "        <div class=\"col-xs-12\">\n" +
                "            <div class=\"invoice-title \">\n" +
                "                <div class=\"row\">\n" +
                "                    <div class=\"col-lg-12 col-md-12 col-xs-12\" style=\"font-size: 25px;font-family: cizel;\">\n" +
                "                       <b>" + Config.user_Veterian_name + "</b> \n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-12 col-md-12 col-xs-12\" style=\"font-size: 20px; margin-bottom: 20px;\">\n" +
                "                        MBBS,MVS \n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-6 col-md-6 col-xs-6\" style=\"font-size: 20px; \" >\n" + care +
                "                       \n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-6 col-md-6 col-xs-6 text-right\" style=\"font-size: 20px;\">\n" +
                "                       <b> Mobile :" + Config.user_Veterian_phone + " </b>\n" +
                "                    </div>\n" +
                "                    \n" +
                "                    <div class=\"col-lg-6 col-md-6 col-xs-6\" style=\"font-size: 17px;\">\n" +
                "                       <b> Email: " + Config.user_Veterian_emial + " </b>\n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-6 col-md-6 col-xs-6 text-right\" style=\"font-size: 20px;\">\n" +
                "                       <b> " + Config.user_Veterian_phone + "</b>\n" +
                "                    </div>\n" +
                "                 \n" +
                "                    \n" +
                "                </div>\n" +
                "               \n" +
                "                \n" +
                "            </div>\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-md-12\" style=\"border: 1px solid black;\"></div>\n" +
                "            </div>\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-xs-3\" style=\"font-size: 20px;\">\n" +
                "                    <b>For a: " + petName + "</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-3\" style=\"font-size: 20px;\">\n" +
                "                    <b>Age: " + petAge + "</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-3\" style=\"font-size: 20px;\">\n" +
                "                    <b>Sex: " + petSex + "</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-3\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Date:"+Config.currentDate()+" <?=date('d/m/Y')?></b>\n" +
                "                </div>\n" +
                "\n" +
                "\n" +
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Pet Parant Name:" + petParent + "</b>\n" +
                "                </div>\n" +
                "\n" +
                "\n" +
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b><h1>Pet Added Vaccination<h1></b>\n" +
                "                </div>\n" +
                "<div id=\"output1\">\n"+
                "</div>\n"+
                "\n" +
                "\n" +
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b><h1>Pending Vaccination<h1></b>\n" +
                "                </div>\n" +
                "<div id=\"output2\">\n"+
                "</div>\n"+
                "            </div><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>\n" +
                "            <div class=\"col-md-12\" style=\"border: 1px solid black;\"></div>\n" +
                "            <div class=\"col-md-12\" style=\"font-size: 25px; text-align: center;\">Address: " + Config.user_Veterian_address + ", Registration Number: " + regisNumber + "</div>\n" +
                "            \n" +
                "        </div>\n" +
                "\n" +
                "\n" +
                "    </div>\n" +
                "\n" +
                "</div>\n" +
                "<script type=\"text/javascript\">\n" +
                "    $(function(){\n" +
                "        window.print();\n" +
                "        window.close();\n" +
                "    });\n" +
                "</script>\n" +
                "<script>\n"+
                // Initializing arrays
                "var immuDate = "+immunizationDate+";\n"+
                "var vaccineName = "+vaccineClass+";\n"+
                "var nextDate = "+nextDueDate+";\n"+

                "var immuDatePending = "+immunizationDatePending+";\n"+
                "var vaccineNamePending = "+vaccineClassPending+";\n"+
                "var nextDatePending = "+nextDueDatePending+";\n"+
                // Getting output element
                "var output = document.getElementById('output1');\n"+
                "var outputNext = document.getElementById('output2');\n"+
                // Creating table tags
                "var table = \"<table><thead><tr><th>Vaccine Class</th><th>Vaccine Type</th><th>Next Due Date</th></tr></thead><tbody>\";\n"+
                "  for (var i = 0; i < immuDate.length; i++) {\n"+
                "table += \"<tr><td>\" + vaccineName[i] + \"</td><td>\" + immuDate[i]  + \"</td><td>\" + nextDate[i] + \"</td></tr>\";\n"+
                " }\n"+
                "  table += \"</tbody></table>\";\n"+

                "var table1 = \"<table><thead><tr><th>Vaccine Class</th><th>Vaccine Type</th><th>Next Due Date</th></tr></thead><tbody>\";\n"+
                "  for (var i = 0; i < immuDatePending.length; i++) {\n"+
                "table1 += \"<tr><td>\" + vaccineNamePending[i] + \"</td><td>\" + immuDatePending[i]  + \"</td><td>\" + nextDatePending[i] + \"</td></tr>\";\n"+
                " }\n"+
                "  table += \"</tbody></table1>\";\n"+
                // Binding output element with table var
                " output.innerHTML = table;\n"+
                " outputNext.innerHTML = table1;\n"+
                "</script>\n"+
                "</body>\n" +
                "</html>";
        return str;
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public String getRequestJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
    public float getDays(String DoB, String currentDate )
    {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        String dateBeforeString = DoB;
        String dateAfterString = currentDate;
        float daysBetween=0;
        try {
            Date dateBefore = myFormat.parse(dateBeforeString);
            Date dateAfter = myFormat.parse(dateAfterString);
            long difference = dateAfter.getTime() - dateBefore.getTime();
            daysBetween = (difference / (1000*60*60*24));
            /* You can also convert the milliseconds to days using this method
             * float daysBetween =
             *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
             */
            System.out.println("Number of Days between dates: "+daysBetween);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return daysBetween;
    }

    public boolean checktimings(String time, String endtime) {

        String pattern = "dd/MM/yyyy hh:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(endtime);

            if(date1.before(date2)) {
                return true;
            } else {

                return false;
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
        return false;
    }

}
