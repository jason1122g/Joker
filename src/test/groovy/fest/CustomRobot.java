package fest;

import org.fest.swing.core.*;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.hierarchy.ComponentHierarchy;

import javax.swing.*;
import java.awt.*;

public class CustomRobot implements Robot{

    private FrameFixture window;
    private Robot robot;

    public CustomRobot(FrameFixture window){
        this.window = window;
        this.robot  = window.robot;
    }

    @Override
    public ComponentHierarchy hierarchy() {
        return robot.hierarchy();
    }

    @Override
    public ComponentFinder finder() {
        return robot.finder();
    }

    @Override
    public ComponentPrinter printer() {
        return robot.printer();
    }

    @Override
    public void showWindow(Window w) {
        robot.showWindow(w);
    }

    @Override
    public void showWindow(Window w, Dimension size) {
        robot.showWindow(w,size);
    }

    @Override
    public void showWindow(Window w, Dimension size, boolean pack) {
        robot.showWindow(w,size,pack);
    }

    @Override
    public void close(Window w) {
        robot.close(w);
    }

    @Override
    public void focus(Component c) {
        robot.focus(c);
    }

    @Override
    public void focusAndWaitForFocusGain(Component c) {
        robot.focusAndWaitForFocusGain(c);
    }

    @Override
    public void cleanUp() {
        robot.cleanUp();
    }

    @Override
    public void cleanUpWithoutDisposingWindows() {
        robot.cleanUpWithoutDisposingWindows();
    }

    @Override
    public void click(Component c) {
        robot.click(c);
    }

    @Override
    public void rightClick(Component c) {
        robot.rightClick(c);
    }

    @Override
    public void click(Component c, MouseButton button) {
        robot.click(c,button);
    }

    @Override
    public void doubleClick(Component c) {
        robot.doubleClick(c);
    }

    @Override
    public void click(Component c, MouseButton button, int times) {
        robot.click(c,button,times);
    }

    @Override
    public void click(Component c, Point where) {
        robot.click(c,transformToScreen(where));
    }

    @Override
    public void click(Component c, Point where, MouseButton button, int times) {
        robot.click(c,transformToScreen(where),button,times);
    }

    @Override
    public void click(Point where, MouseButton button, int times) {
        robot.click(transformToScreen(where),button,times);
    }

    @Override
    public void pressMouse(MouseButton button) {
        robot.pressMouse(button);
    }

    @Override
    public void pressMouse(Component c, Point where) {
        robot.pressMouse(c,transformToScreen(where));
    }

    @Override
    public void pressMouse(Component c, Point where, MouseButton button) {
        robot.pressMouse(c,transformToScreen(where),button);
    }

    @Override
    public void pressMouse(Point where, MouseButton button) {
        robot.pressMouse(transformToScreen(where),button);
    }

    @Override
    public void moveMouse(Component c) {
        robot.moveMouse(c);
    }

    @Override
    public void moveMouse(Component c, Point p) {
        robot.moveMouse(c,transformToScreen(p));
    }

    @Override
    public void moveMouse(Component c, int x, int y) {
        robot.moveMouse(c,new Point(x,y));
    }

    @Override
    public void moveMouse(Point p) {
        robot.moveMouse(transformToScreen(p));
    }

    @Override
    public void moveMouse(int x, int y) {
        robot.moveMouse(new Point(x,y));
    }

    @Override
    public void releaseMouse(MouseButton button) {
        robot.releaseMouse(button);
    }

    @Override
    public void releaseMouseButtons() {
        robot.releaseMouseButtons();
    }

    @Override
    public void rotateMouseWheel(Component c, int amount) {
        robot.rotateMouseWheel(c,amount);
    }

    @Override
    public void rotateMouseWheel(int amount) {
        robot.rotateMouseWheel(amount);
    }

    @Override
    public void jitter(Component c) {
        robot.jitter(c);
    }

    @Override
    public void jitter(Component c, Point where) {
        robot.jitter(c,transformToScreen(where));
    }

    @Override
    public void enterText(String text) {
        robot.enterText(text);
    }

    @Override
    public void type(char character) {
        robot.type(character);
    }

    @Override
    public void pressAndReleaseKey(int keyCode, int... modifiers) {
        robot.pressAndReleaseKey(keyCode,modifiers);
    }

    @Override
    public void pressAndReleaseKeys(int... keyCodes) {
        robot.pressAndReleaseKeys(keyCodes);
    }

    @Override
    public void pressKey(int keyCode) {
        robot.pressKey(keyCode);
    }

    @Override
    public void releaseKey(int keyCode) {
        robot.releaseKey(keyCode);
    }

    @Override
    public void pressModifiers(int modifierMask) {
        robot.pressModifiers(modifierMask);
    }

    @Override
    public void releaseModifiers(int modifierMask) {
        robot.releaseModifiers(modifierMask);
    }

    @Override
    public void waitForIdle() {
        robot.waitForIdle();
    }

    @Override
    public boolean isDragging() {
        return robot.isDragging();
    }

    @Override
    public boolean isReadyForInput(Component c) {
        return robot.isReadyForInput(c);
    }

    @Override
    public JPopupMenu showPopupMenu(Component invoker) {
        return robot.showPopupMenu(invoker);
    }

    @Override
    public JPopupMenu showPopupMenu(Component invoker, Point location) {
        return robot.showPopupMenu(invoker,transformToScreen(location));
    }

    @Override
    public JPopupMenu findActivePopupMenu() {
        return robot.findActivePopupMenu();
    }

    @Override
    public void requireNoJOptionPaneIsShowing() {
        robot.requireNoJOptionPaneIsShowing();
    }

    @Override
    public Settings settings() {
        return robot.settings();
    }

    @Override
    public boolean isActive() {
        return robot.isActive();
    }

    public Component findComponentAt(int x, int y){
        return findComponentAt(new Point(x,y));
    }

    public Component findComponentAt(Point p){
        return window.component().findComponentAt(transformInWindow(p));
    }

    public Component getComponentAt(int x,int y){
        return getComponentAt(new Point(x, y));
    }

    public Component getComponentAt(Point p){
        return window.component().getComponentAt(transformInWindow(p));
    }

    private Point transformToScreen(int x, int y){
        Insets insets = window.component().getInsets();
        Point  screenPoint = window.component().getLocationOnScreen();
        return new Point(x+insets.left+screenPoint.x,y+insets.top+screenPoint.y);
    }

    private Point transformToScreen(Point point){
        return transformToScreen(point.x,point.y);
    }

    private Point transformInWindow(int x, int y){
        Insets insets = window.component().getInsets();
        return new Point(x+insets.left,y+insets.top);
    }

    private Point transformInWindow(Point point){
        return transformInWindow(point.x,point.y);
    }

}
