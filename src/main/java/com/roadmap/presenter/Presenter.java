package com.roadmap.presenter;

import java.util.List;

public interface Presenter<T> {
    void showList(final List<T> items);
}
