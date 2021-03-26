package com.fookoo.template.server.craftsman.creator;

public interface Creator<T> {

    public T create(Object source);
}
