package com.devsh.libslackmessage;/*
 * Copyright (C) 2015 Suhan Lee
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * <a href="https://api.slack.com/incoming-webhooks">Slack Incoming WebHooks</a>
 */
import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.MediumTest;

import java.io.IOException;

public class SlackMessageTest extends ApplicationTestCase<Application> {
    private String apiKey = "T0DJ86CLB/B0Q8V7ET0/74M6oWZIbxPcyAwK3fhxjfLh";

    public SlackMessageTest() {
        super(Application.class);
    }

    @MediumTest
    public void testSlackMessage() {
        SlackMessage slackMessage = SlackMessage.create()
                .username("Hi, Im suhanlee")
                .iconEmoji(":ghost:")
                .text("BOO!");

        try {
            String body = SlackReporter.create(apiKey).reportSync(slackMessage);
            assertEquals("ok", body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MediumTest
    public void testChannelOverride() {
        SlackMessage slackMessage = SlackMessage.create()
                .username("Hi, Im suhanlee")
                .iconEmoji(":ghost:")
                .text("BOO!")
                .channel("#testchannel");

        try {
            String body = SlackReporter.create(apiKey).reportSync(slackMessage);
            assertEquals("ok", body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MediumTest
    public void testImageUrl() {
        SlackMessage slackMessage = SlackMessage.create()
                .username("Hi, Im suhanlee")
                .text("BOO!")
                .channel("#testchannel")
                .iconUrl("https://avatars0.githubusercontent.com/u/2666166?v=3&s=460");

        try {
            String body = SlackReporter.create(apiKey).reportSync(slackMessage);
            assertEquals("ok", body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
