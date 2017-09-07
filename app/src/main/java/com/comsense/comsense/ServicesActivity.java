package com.comsense.comsense;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class ServicesActivity extends AppCompatActivity {

    private final String TAG = ServicesActivity.class.getName();

    private final String text1 = "<b>Services:</b>" +
            "<br><br>" +
            "<b>What We Do</b><br>" +
            "<br>" +
            "We help client to marry technology and marketing to build exceptional customer experience across the buyers journey.<br>" +
            "<br>" +
            "<b><u>1.Marketing Analytics:</u></b>";

    private final String text2 = "<b>Our Marketing Analytics Solution provides</b><br>" +
            "<br>" +
            "A unique view into a website beyond what typical web analytics can provide.<br>" +
            "<br>" +
            "It captures all of a website’s traffic, no need to tag pages or decide ahead of time what data you want to capture.<br>" +
            "<br>" +
            "Data is captured and processed in real-time which reveal unknown issues as they are happening.<br>" +
            "<br>" +
            "Replay session to see what a customer or user actually did and not just they remember doing when a problem occurred.<br>" +
            "<br>" +
            "<b><u>2.Marketing Automation Expertise:</u></b>";

    private final String text3 = "<html><body>" +
            "We are known for our expertise and leadership in utilizing industry-leading, cloud-based marketing automation software to deliver results. Our team works with you to operate your marketing automation system to move leads down the sales funnel and pre-qualify them for your sales team. We do this by following a well-defined marketing automation process:" +
            "<br>" +
            "<ul><li>Content creation, design and optimization for lead nurturing campaigns</li>" +
            "<li>Integration of demand generation campaigns with lead nurturing campaigns</li>" +
            "<li>Creation of lead nurturing emails, landing pages and confirmation pages</li>" +
            "<li>Creation of personalized content, calls-to-action and smart forms</li>" +
            "<li>Placement of calls-to-action on web pages, blogs and social media</li>" +
            "<li>A/B testing of all lead nurturing and content personalization components</li>" +
            "<li>Setting criteria for lead scoring based on page and email interactions, form conversions and information provided via forms</li>" +
            "<li>Setting criteria for buy cycle (or lead lifecycle) status based on lead score and/or page interactions and form conversions</li>" +
            "<li>Creation of workflows that automatically adjust lead score and buy cycle status based on established criteria</li>" +
            "<li>Creation of workflows that automatically update segmented lists for lead nurturing communication based on buy cycle status and/or lead score</li></ul>" +
            "<b><u>3.Marketing as a Services:</u></b></html></body>";

    private final String text4 = "<html><body><b>Campaign Management Centre:</b><br>" +
            "<br>" +
            "A hub of marketing services, technology, and processes – is a cost-effective solution for creating predictable sales results for the new buying cycle. A marketing shared services (MSS) model offers the opportunity for improved service delivery, greater economies of scale, greater concentration and leverage of expertise, and more rapid and effective program/campaign execution.<br>" +
            "<br>" +
            "<b>on Marketing shared services model include:</b><br>" +
            "<br>" +
            "<ol><li><b>Campaign Management Centre Assessment:</b></li>" +
            "We work with your marketing team and start by conducting in-depth interviews with all key internal stakeholders to identify your top challenges .<br>" +
            "<br>" +
            "<li><b>Implementation Plan:</li></b>" +
            "Implementation plan is Based on your workshop, we deliver a the detailed implementation plan with the timelines and the assigned roles and responsibilities.<br>" +
            "<br>" +
            "<li><b>Campaign Execution:</li></b>" +
            "Once we build hub center, our team of experts will provide ongoing services, including campaign execution, lead scoring, lead management, Email marketing management.<br>" +
            "<br>" +
            "<li><b>Campaign Analysis:</li></b>" +
            "Comsense team of marketing analysts constantly looking at data and outcomes of every stage of campaign and present an analysis in order to optimize running campaigns.<br>" +
            "<br></ol>" +
            "<b><u>4.Visual & Design:</b></u></body><html/>";
    
    private final String text5 = "Every Visual communication is designed keeping the persona of the Receiver in mind.The most important thing to understand when designing an online visual experience is your audience. Understanding who they are, what they do for a living, how old they are, how they work, what they know about the Web, how they use it, on what devices, where and so on provides invaluable insight into their pain points that you are out to solve.<br>" +
            "Setting clear constraints on your design also helps. At Comsense, we believe understanding your audience builds on a communication design foundation by revealing your users’ sensitivities (physical or cultural, for example) to things like color and typography." +
            "<br>" +
            "<ul><li>Website:Style, Creative, Interactive.</li>" +
            "<li>Digital media:Social tiles, Emailer, Case Studies</li>" +
            "<li>Mobile:Mobile Apps, Responsive Designs</li>" +
            "<li>Offline media:Event branding, Sales Kit</ul><br>" +
            "<b><u>5.Marketing Automation:</b></u>";
    
    private final String text6 = "<html><body><b>You Can Personalize Your Marketing Like Never Before:</b><br>" +
            "<br>" +
            "Customers want relevant, personalized and consistent interactions. Catalysts such as social media, real-time access to information and mobile devices are redefining what they expect. These trends are fundamentally changing how marketing must work to improve business success.<br>" +
            "<br>" +
            "With <b>IBM marketing automation solution</b>, you can engage with your customers in highly relevant, interactive dialogues across digital, social, mobile and traditional channels. You can personalize and optimize cross-channel campaigns and digital marketing efforts to convert visitors into repeat customers and advocates.<br>" +
            "<br>" +
            "Marketing automation system is the core of your content marketing, demand generation and lead nurturing eco-system. Used effectively, you can manage and optimize nearly every step from initial lead capture to handing off qualified leads to your sales team.<br>" +
            "<br>" +
            "<b>The benefits of marketing automation can be dramatic:</b>" +
            "<br>" +
            "<ul><li>Increase the number and percentage of qualified leads passed to sales.</li>" +
            "<li>Increase conversion rates for demand generation and lead nurturing.</li>" +
            "<li>Reduce the number of leads lost and missed sales opportunities.</li>" +
            "<li>Reduce the sales cycle and churn, while increasing lifetime value.</li>" +
            "<li>Enable Revenue Performance Management to track revenue-based KPIs and ROI.</li></ul><br>" +
            "<b><u>6.Technology services:</b></u></body></html>";

    private final String text7 = "Every Visual communication is designed keeping the persona of the Receiver in mind.The most important thing to understand when designing an online visual experience is your audience. Understanding who they are, what they do for a living, how old they are, how they work, what they know about the Web, how they use it, on what devices, where and so on provides invaluable insight into their pain points that you are out to solve.<br>" +
            "<br>" +
            "Setting clear constraints on your design also helps. At Comsense, we believe understanding your audience builds on a communication design foundation by revealing your users’ sensitivities (physical or cultural, for example) to things like color and typography.";
    
    private TextView txtText1, txtText2, txtText3;
    private WebView webView1,webView2,webView3, webView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        try {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            Log.e(TAG, "onCreate: ", e);
        }

        txtText1 = (TextView) findViewById(R.id.text1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtText1.setText(Html.fromHtml(text1, Html.FROM_HTML_MODE_COMPACT));
        } else {
            txtText1.setText(Html.fromHtml(text1));
        }

        txtText2 = (TextView) findViewById(R.id.txtText2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtText2.setText(Html.fromHtml(text2, Html.FROM_HTML_MODE_COMPACT));
        } else {
            txtText2.setText(Html.fromHtml(text2));
        }

        webView1 = (WebView) findViewById(R.id.webView1);
        webView1.loadDataWithBaseURL(null, text3, "text/html", "utf-8", null);

        webView2 = (WebView) findViewById(R.id.webView2);
        webView2.loadDataWithBaseURL(null, text4, "text/html", "utf-8", null);

        webView3 = (WebView) findViewById(R.id.webView3);
        webView3.loadDataWithBaseURL(null, text5, "text/html", "utf-8", null);

        webView4 = (WebView) findViewById(R.id.webView4);
        webView4.loadDataWithBaseURL(null, text6, "text/html", "utf-8", null);

        txtText3 = (TextView) findViewById(R.id.txtText3);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtText3.setText(Html.fromHtml(text7, Html.FROM_HTML_MODE_COMPACT));
        } else {
            txtText3.setText(Html.fromHtml(text7));
        }
    }

}
