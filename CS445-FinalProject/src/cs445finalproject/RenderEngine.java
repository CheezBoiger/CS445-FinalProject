/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs445finalproject;


import java.util.ArrayList;
import java.util.List;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 * Rendering engine that handles all of the OpenGL calls.
 * @author alexa
 */
public class RenderEngine {
    public static final String PROJECT_TITLE = "CS445 Final Project Checkpoint 1";
    public static final String ENGINE_NAME = "Git Gud Engine";
    
    /**
     * Check if this engine is running.
     */
    private boolean running;
    
    /**
     * Keep track of the display mode. This holds width and height of the 
     * window.
     */
    private DisplayMode displayMode;
    
    /**
     * Main camera to grab our view and projection matrices from.
     */
    private Camera mainCamera;
    
    /**
     * The command list used to render onto the screen.
     */
    private List<Mesh> commandlist;
    
    RenderEngine(int winx, int winy) {
        displayMode = new DisplayMode(winx, winy);
        running = false;
        commandlist = new ArrayList<>();
    }
    
    RenderEngine() {
        this(1200, 800);
    }
    
    
    private void initWindow() {
        try {
            Display.setDisplayMode(displayMode);
            Display.setTitle(PROJECT_TITLE);
            
            Display.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void initGL() {
        glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(100.0f, 
                (float )displayMode.getWidth() / (float )displayMode.getHeight(), 
                0.1f, 300.0f);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        // For depth and 3D.
        glEnable(GL_DEPTH_TEST);
        
        // Cull mode to save 50% of performance.
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glFrontFace(GL_CW);
    }
    
    public void initialize() {
        initWindow();
        initGL();
    }
    
    /**
     * method: Start
     * purpose: Start this engine.
     */
    public void start() {
        running = true;
    }
    
    
    /**
     * Check if this engine is running.
     * @return 
     */
    public boolean isRunning() {
        return running && !Display.isCloseRequested();
    }
    
    public void render() {
        glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
        glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        
        for (int i = 0; i < commandlist.size(); ++i) {
            Mesh mesh = commandlist.get(i);
            if (mesh.renderable) {
                glPushMatrix();
                glTranslatef(mesh.position.x, mesh.position.y, mesh.position.z);
                glScalef(mesh.scale.x, mesh.scale.y, mesh.scale.z);
                mesh.draw();
                glPopMatrix();
            }
        }
        
        Display.update();
        Display.sync(60);
        
        commandlist.clear();
    }
    
    
    public void push(Mesh mesh) {
        commandlist.add(mesh);
    }
    
    
    public void setMainCamera(Camera c) {
        mainCamera = c;
    }
    
    public void update() {
        glLoadIdentity();
        // update the camera, if there is one.
        if (mainCamera != null) {
            moveCamera();
            if (Mouse.isInsideWindow()) {
                mainCamera.look((float )Mouse.getX(), (float )Mouse.getY(), true);
            }
            mainCamera.update();
        }
        
        checkCloseSignal();
    }
    
    /**
     * Clean up things.
     */
    public void cleanUp() {
        Display.destroy();
    }
    
    
    public void checkCloseSignal() {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            // signal that we are closing.
            running = false;
        }
    }
    
    private void moveCamera() {
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            mainCamera.move(Camera.Movement.LEFT);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            mainCamera.move(Camera.Movement.RIGHT);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            mainCamera.move(Camera.Movement.FORWARD);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            mainCamera.move(Camera.Movement.BACK);
        }
        // Check the main camera position.
        System.out.println("Camera pos: x:" + mainCamera.position.x + " y: " 
                + mainCamera.position.y + " z: " + mainCamera.position.z);
    }
}