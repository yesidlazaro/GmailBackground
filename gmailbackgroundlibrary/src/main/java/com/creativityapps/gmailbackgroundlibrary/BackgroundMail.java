package com.creativityapps.gmailbackgroundlibrary;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.creativityapps.gmailbackgroundlibrary.util.GmailSender;
import com.creativityapps.gmailbackgroundlibrary.util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BackgroundMail {
    String TAG = "BackgroundMail";
    private String username;
    private String password;
    private String mailto;
    private String subject;
    private String body;
    private String sendingMessage;
    private String sendingMessageSuccess;
    private String sendingMessageError;
    private boolean processVisibility = true;
    private ArrayList<String> attachments = new ArrayList<>();
    private Context mContext;

    public BackgroundMail(Fragment fragment) {
        this(fragment.getActivity().getApplicationContext());
    }

    public BackgroundMail(Context context) {
        this.mContext = context;
        this.sendingMessage = context.getString(R.string.msg_sending_email);
        this.sendingMessageSuccess = context.getString(R.string.msg_email_sent_successfully);
        this.sendingMessageError=context.getString(R.string.msg_error_sending_email);
    }

    public void setGmailUserName(@NonNull String string) {
        this.username = string;
    }

    public void setGmailPassword(@NonNull String string) {
        this.password = string;
    }

    public void showVisibleProgress(boolean state) {
        this.processVisibility = state;
    }

    public void setMailTo(@NonNull String string) {
        this.mailto = string;
    }

    public void setFormSubject(@NonNull String string) {
        this.subject = string;
    }

    public void setFormBody(@NonNull String string) {
        this.body = string;
    }

    public void setSendingMessage(String string) {
        this.sendingMessage = string;
    }

    public void setSendingMessageSuccess(String string) {
        this.sendingMessageSuccess = string;
    }

    public void addAttachment(@NonNull String attachment) {
        this.attachments.add(attachment);
    }

    public void addAttachments(@NonNull List<String> attachments) {
        this.attachments.addAll(attachments);
    }

    public void addAttachments(String...attachments) {
        this.attachments.addAll(Arrays.asList(attachments));
    }

    public void send() {

        if (TextUtils.isEmpty(username)) {
            throw new IllegalArgumentException("You didn't set a Gmail username");
        }
        if (TextUtils.isEmpty(password)) {
            throw new IllegalArgumentException("You didn't set a Gmail password");
        }
        if (TextUtils.isEmpty(mailto)) {
            throw new IllegalArgumentException("You didn't set a Gmail recipient");
        }
        if (TextUtils.isEmpty(body)) {
            throw new IllegalArgumentException("You didn't set a body");
        }
        if (TextUtils.isEmpty(subject)) {
            throw new IllegalArgumentException("You didn't set a subject");
        }
        if (!Utils.isNetworkAvailable(mContext)) {
            Log.d(TAG, "you need internet connection to send the email");
        }
        new SendEmailTask().execute();
    }

    public class SendEmailTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (processVisibility) {
                progressDialog = new ProgressDialog(mContext);
                progressDialog.setMessage(sendingMessage);
                progressDialog.setCancelable(false);
                progressDialog.show();
            }
        }

        @Override
        protected Boolean doInBackground(String... arg0) {
            try {
                GmailSender sender = new GmailSender(username, password);
                if (!attachments.isEmpty()) {
                    for (int i = 0; i < attachments.size(); i++) {
                        if (!attachments.get(i).isEmpty()) {
                            sender.addAttachment(attachments.get(i));
                        }
                    }
                }
                sender.sendMail(subject, body, username, mailto);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (processVisibility) {
                progressDialog.dismiss();
                if (result) {
                    Toast.makeText(mContext, sendingMessageSuccess,
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext, sendingMessageError, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
