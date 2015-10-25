package io.palaima.debugdrawer.drawer;

public interface BaseDebugDrawer {

    void open();

    void close();

    boolean isOpen();

    void onStart();

    void onStop();
}
