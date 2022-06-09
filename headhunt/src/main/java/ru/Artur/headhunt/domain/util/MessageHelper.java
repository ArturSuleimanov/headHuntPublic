package ru.Artur.headhunt.domain.util;

import ru.Artur.headhunt.domain.User;

public abstract class MessageHelper {
    public static String getAuthorName(User author) {
        return author != null ? author.getUsername() : "<none>";

    }
}
