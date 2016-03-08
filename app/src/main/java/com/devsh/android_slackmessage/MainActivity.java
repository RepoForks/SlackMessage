package com.devsh.android_slackmessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.devsh.libslackmessage.SlackAttachment;
import com.devsh.libslackmessage.SlackMessage;
import com.devsh.libslackmessage.SlackReporter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String apiKey = "T0DJ86CLB/B0Q8V7ET0/74M6oWZIbxPcyAwK3fhxjfLh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<SlackAttachment> attachmentList = new ArrayList<SlackAttachment>();

        SlackAttachment attachment = new SlackAttachment()
                .fallback("Required plain-text summary of the attachment.")
                .color("#36a64f")
                .pretext("Optional text that appears above the attachment block")
                .authorName("Bobby Tables")
                .authorLink("http://flickr.com/bobby/")
                .authorIcon("http://flickr.com/icons/bobby.jpg")
                .title("Slack API Documentation")
                .titleLink("https://api.slack.com/")
                .text("Optional text that appears within the attachment")
                .imageUrl("https://avatars0.githubusercontent.com/u/2666166?v=3&s=460")
                .thumbUrl("https://avatars2.githubusercontent.com/u/2666166?v=3&u=e9222752a65412ab2b4a0459fcb2932ae7c7620c&s=140");

        attachmentList.add(attachment);

        SlackMessage slackMessage = SlackMessage.create()
                .username("Hi, Im suhanlee")
                .iconEmoji(":ghost")
                .attachements(attachmentList);

        SlackReporter.create(apiKey).report(slackMessage);
    }
}
