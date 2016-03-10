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

- Get Slack WebHook API Key : https://api.slack.com

## 1. Make SlackMessage
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
## 2. Report it with Slack API Key

```java
    private String apiKey = "T0DJ86CLB/B0Q8V7ET0/74M6oWZIbxPcyAwK3fhxjfLh"; // example
...
...
        SlackReporter.create(apiKey).report(slackMessage);

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
