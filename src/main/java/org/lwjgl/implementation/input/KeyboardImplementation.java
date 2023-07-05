package org.lwjgl.implementation.input;


import java.nio.ByteBuffer;
public interface KeyboardImplementation {
    void createKeyboard();

    void destroyKeyboard();

    void pollKeyboard(ByteBuffer keyDownBuffer);

    void readKeyboard(ByteBuffer readBuffer);
}
