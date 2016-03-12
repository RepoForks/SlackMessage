# SlackMessage
Slack Message Sender Library for Android and Java

# How to Use

## Download
```xml
<dependency>
  <groupId>com.devsh.suhanlee</groupId>
  <artifactId>slack-message</artifactId>
  <version>0.1.1</version>
  <type>pom</type>
</dependency>
```
or Gradle

```java
compile 'com.devsh.suhanlee:slack-message:0.1.1'
```

- Get API-KEY refer to Slack Incoming Webhooks(https://api.slack.com/incoming-webhooks)

ex) T0DJ86CLB/B0Q8V7ET0/74M6oWZIbxPcyAwK3fhxjfLh
## Code
### 1. Make SlackMessage
```java
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
```
### 2. Report it with Slack API Key

```java
    private String apiKey = "T0DJ86CLB/B0Q8V7ET0/74M6oWZIbxPcyAwK3fhxjfLh"; // example
...
...
        // if you do not need check response,
        SlackReporter.create(apiKey).report(slackMessage);
        // if you need check response,
        SlackReporter.create(apiKey).report(slackMessage, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i(TAG, response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
```
## Other Example
- Slack Message
```java
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
```
- Slack Attachments
```java
    @MediumTest
    public void testSlackAttachments() {
        List<SlackAttachment> attachmentList = new ArrayList<SlackAttachment>();

        SlackAttachment attachment = SlackAttachment.create()
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

        SlackMessage slackMessage = SlackMessage.create().attachements(attachmentList);

        try {
            String body = SlackReporter.create(apiKey).reportSync(slackMessage);
            assertEquals("ok", body);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @MediumTest
    public void testExampleGroove() {
        List<SlackAttachment> attachmentList = new ArrayList<SlackAttachment>();

        SlackAttachment attachment = SlackAttachment.create()
                .fallback("New ticket from Andrea Lee - Ticket #1943: Can't rest my password - https://groove.hq/path/to/ticket/194")
                .pretext("New ticket from Andrea Lee")
                .title("Ticket #1943: Can't reset my password")
                .titleLink("https://groove.hq/path/to/ticket/1943")
                .text("Help! I tried to reset my password but nothing happened!")
                .color("#7CD197");

        attachmentList.add(attachment);

        SlackMessage slackMessage = SlackMessage.create().attachements(attachmentList);

        try {
            String body = SlackReporter.create(apiKey).reportSync(slackMessage);
            assertEquals("ok", body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MediumTest
    public void testExampleHoneybadger() {
        List<SlackAttachment> attachmentList = new ArrayList<SlackAttachment>();

        SlackAttachment attachment = SlackAttachment.create()
                .fallback("ReferenceError - UI is not defined: https://honeybadger.io/path/to/event/")
                .text("<https://honeybadger.io/path/to/event/|ReferenceError> - UI is not defined")
                .color("#F35A00");

        attachmentList.add(attachment);

        SlackMessage slackMessage = SlackMessage.create().attachements(attachmentList);

        try {
            String body = SlackReporter.create(apiKey).reportSync(slackMessage);
            assertEquals("ok", body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MediumTest
    public void testExampleDatadog() {
        List<SlackAttachment> attachmentList = new ArrayList<SlackAttachment>();

        SlackAttachment attachment = SlackAttachment.create()
                .fallback("Network traffic (kb/s): How does this look? @slack-ops - Sent by Julie Dodd - https://datadog.com/path/to/event")
                .title("Network traffic (kb/s)")
                .titleLink("https://datadog.com/path/to/event")
                .text("How does this look? @slack-ops - Sent by Julie Dodd")
                .imageUrl("https://datadoghq.com/snapshot/path/to/snapshot.png")
                .color("#764FA5");

        attachmentList.add(attachment);

        SlackMessage slackMessage = SlackMessage.create().attachements(attachmentList);

        try {
            String body = SlackReporter.create(apiKey).reportSync(slackMessage);
            assertEquals("ok", body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```
