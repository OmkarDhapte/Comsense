package com.comsense.comsense;

import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity {

    private final String TAG = AboutUsActivity.class.getName();

    private TextView txtAboutUs,txtAboutUsTitle,txtOurCultureTitle,txtOurMissionTitle;
    private WebView webViewOurCulture, webViewContent;

    private String comsenseConsulting = "<b><font color=\"black\">Comsense Consulting</font></b><br><br>" +
            "We want every business to use technology to do a better marketing, because we believe technology and marketing can create magic. We think there are great technologists out there and there are highly creative marketing folks and that’s the community of people we are nurturing to work together to deliver a great customer experience in the digital world today. " +
            "<br><br>We are agile, easy to inspire and truly interested in success of our clients and Partners business. We are clear what we want to achieve and we expect that from our partners/ clients in every business relationship we undertake. Our Clients have been delivering great digital experiences for their customers. We have a highly enthused & creative team of artists, engineers, marketers and marketing technologists.";

    private String ourCulture = "<html><body><ul type=\"disc\">" +
            "<li>We believe in Failing fast.</li>" +
            "<li>Being truthful and transparent , no matter what it costs.</li>" +
            "<li>Taking responsibility of our actions.</li>" +
            "<li>To say what we mean and mean what we say.</li>" +
            "<li>Lifetime learning.</li>" +
            "<li>Conviction in what we do.</li>" +
            "<li>Growing together, don’t leave her behind.</li>" +
            "</ul></body></html>";

    private String content = "<html><body><br><b>To Our Customer:</b>" +
            "<br><br>To understand, create and sustain exceptional digital journeys for our clients’ customers." +
            "<br><br><b>To Our Employee:</b>" +
            "<ul>" +
            "<li>" +
            "Think about customer’s customer.</li>" +
            "<li>Learn , unlearn and relearn</li>" +
            "<li>Enthusiasm in everything.</li><ul></body></html>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        try {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            Log.e(TAG, "onCreate: ", e);
        }

        txtAboutUsTitle = (TextView) findViewById(R.id.txtAboutUsTitle);
        txtAboutUsTitle.setText("About Us:");
        txtAboutUsTitle.setPaintFlags(txtAboutUsTitle.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        txtOurCultureTitle= (TextView) findViewById(R.id.txtOurCultureTitle);
        txtOurCultureTitle.setText("Our Culture:");
        txtOurCultureTitle.setPaintFlags(txtOurCultureTitle.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        txtOurMissionTitle= (TextView) findViewById(R.id.txtOurMissionTitle);
        txtOurMissionTitle.setText("Our Mission:");
        txtOurMissionTitle.setPaintFlags(txtOurMissionTitle.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        txtAboutUs = (TextView) findViewById(R.id.txtAboutUs);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtAboutUs.setText(Html.fromHtml(comsenseConsulting, Html.FROM_HTML_MODE_COMPACT));
        } else {
            txtAboutUs.setText(Html.fromHtml(comsenseConsulting));
        }

        webViewOurCulture = (WebView) findViewById(R.id.webViewOurCulture);
        webViewOurCulture.loadDataWithBaseURL(null, ourCulture,"text/html","utf-8",null);

        webViewContent = (WebView) findViewById(R.id.webViewContent);
        webViewContent.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);

    }

}
