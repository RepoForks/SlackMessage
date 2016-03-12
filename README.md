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
compile 'com.devsh.suhanlee:slack-message:0.1.2'
```

- Get API-KEY refer to Slack Incoming Webhooks(https://api.slack.com/incoming-webhooks)

ex) T0DJ86CLB/B0Q8V7ET0/74M6oWZIbxPcyAwK3fhxjfLh
## Code
### 1. Make SlackMessage
```java
      List<SlackAttachment> attachmentList = new ArrayList<>();

        // Option - attachment
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

        // SlackMessage
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
        // when it no need response
        SlackReporter.create(apiKey).report(slackMessage);

        // when it need handle response
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
    public void testSlackMessage() throws IOException {
        SlackMessage slackMessage = SlackMessage.create()
                .username("Hi, Im suhanlee")
                .iconEmoji(":ghost:")
                .text("BOO!");

        String body = SlackReporter.create(apiKey).reportSync(slackMessage);
        assertEquals("ok", body);
    }

    @MediumTest
    public void testChannelOverride() throws IOException {
        SlackMessage slackMessage = SlackMessage.create()
                .username("Hi, Im suhanlee")
                .iconEmoji(":ghost:")
                .text("BOO!")
                .channel("#testchannel");

        String body = SlackReporter.create(apiKey).reportSync(slackMessage);
        assertEquals("ok", body);
    }

    @MediumTest
    public void testImageUrl() throws IOException {
        SlackMessage slackMessage = SlackMessage.create()
                .username("Hi, Im suhanlee")
                .text("BOO!")
                .channel("#testchannel")
                .iconUrl("https://avatars0.githubusercontent.com/u/2666166?v=3&s=460");

        String body = SlackReporter.create(apiKey).reportSync(slackMessage);
        assertEquals("ok", body);
    }
```
- Slack Attachments
```java
    @MediumTest
    public void testSlackAttachments() throws IOException {
        List<SlackAttachment> attachmentList = new ArrayList<>();

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

        String body = SlackReporter.create(apiKey).reportSync(slackMessage);
        assertEquals("ok", body);
    }

    @MediumTest
    public void testExampleGroove() throws IOException {
        List<SlackAttachment> attachmentList = new ArrayList<>();

        SlackAttachment attachment = SlackAttachment.create()
                .fallback("New ticket from Andrea Lee - Ticket #1943: Can't rest my password - https://groove.hq/path/to/ticket/194")
                .pretext("New ticket from Andrea Lee")
                .title("Ticket #1943: Can't reset my password")
                .titleLink("https://groove.hq/path/to/ticket/1943")
                .text("Help! I tried to reset my password but nothing happened!")
                .color("#7CD197");

        attachmentList.add(attachment);

        SlackMessage slackMessage = SlackMessage.create().attachements(attachmentList);

        String body = SlackReporter.create(apiKey).reportSync(slackMessage);
        assertEquals("ok", body);
    }

    @MediumTest
    public void testExampleHoneybadger() throws IOException {
        List<SlackAttachment> attachmentList = new ArrayList<>();

        SlackAttachment attachment = SlackAttachment.create()
                .fallback("ReferenceError - UI is not defined: https://honeybadger.io/path/to/event/")
                .text("<https://honeybadger.io/path/to/event/|ReferenceError> - UI is not defined")
                .color("#F35A00");

        attachmentList.add(attachment);

        SlackMessage slackMessage = SlackMessage.create().attachements(attachmentList);

        String body = SlackReporter.create(apiKey).reportSync(slackMessage);
        assertEquals("ok", body);

    }

    @MediumTest
    public void testExampleDatadog() throws IOException {
        List<SlackAttachment> attachmentList = new ArrayList<>();

        SlackAttachment attachment = SlackAttachment.create()
                .fallback("Network traffic (kb/s): How does this look? @slack-ops - Sent by Julie Dodd - https://datadog.com/path/to/event")
                .title("Network traffic (kb/s)")
                .titleLink("https://datadog.com/path/to/event")
                .text("How does this look? @slack-ops - Sent by Julie Dodd")
                .imageUrl("https://datadoghq.com/snapshot/path/to/snapshot.png")
                .color("#764FA5");

        attachmentList.add(attachment);

        SlackMessage slackMessage = SlackMessage.create().attachements(attachmentList);

        String body = SlackReporter.create(apiKey).reportSync(slackMessage);
        assertEquals("ok", body);
    }
```
