/*
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
package com.devsh.libslackmessage;

public class SlackAttachment {
    String fallback;
    String color;
    String pretext;

    String author_name;
    String author_link;
    String author_icon;

    String title;
    String title_link;

    String text;

    String image_url;
    String thumb_url;

    public static SlackAttachment create() {
        return new SlackAttachment();
    }

    private SlackAttachment() {}

    public SlackAttachment fallback(String fallback) {
        this.fallback = fallback;
        return this;
    }

    public SlackAttachment color(String color) {
        this.color = color;
        return this;
    }

    public SlackAttachment pretext(String pretext) {
        this.pretext = pretext;
        return this;
    }

    public SlackAttachment authorName(String authorName) {
        this.author_name = authorName;
        return this;
    }

    public SlackAttachment authorLink(String authorLink) {
        this.author_link = authorLink;
        return this;
    }

    public SlackAttachment authorIcon(String authorIcon) {
        this.author_icon = authorIcon;
        return this;
    }

    public SlackAttachment title(String title) {
        this.title = title;
        return this;
    }

    public SlackAttachment titleLink(String titleLink) {
        this.title_link = titleLink;
        return this;
    }

    public SlackAttachment text(String text) {
        this.text = text;
        return this;
    }

    public SlackAttachment imageUrl(String imageUrl) {
        this.image_url = imageUrl;
        return this;
    }

    public SlackAttachment thumbUrl(String thumbUrl) {
        this.thumb_url = thumbUrl;
        return this;
    }

}
