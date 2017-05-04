/***************************************************************
* File: Cube.java
* Authors: Mario Garcia, [plz put yo names here]
* Class: CS 141 – Programming and Problem Solving
*
* assignment: program 1
* date last modified: 3/20/2112
*
* purpose: The cube mesh object.
*
****************************************************************/
package cs445finalproject;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author alexa
 */
public class Cube extends Mesh {
    private int vbo, colorVBO;
    
    Cube() {
        vbo = -1;
        colorVBO = -1;
    }

    @Override
    public void draw() {
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        GL11.glVertexPointer(3, GL_FLOAT, 0, 0l);
        
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorVBO);
        GL11.glColorPointer(3, GL_FLOAT, 0, 0l);
        
        GL11.glEnableClientState(GL_VERTEX_ARRAY);
        GL11.glEnableClientState(GL_COLOR_ARRAY);
        GL11.glDrawArrays(GL_TRIANGLES, 0, 36);
    }

    @Override
    public void initialize() {
        float[] vertices = new float[] {
            // Front
            -1.0f, -1.0f, 1.0f,
            -1.0f,  1.0f, 1.0f,
             1.0f,  1.0f, 1.0f,
             1.0f, 1.0f, 1.0f,
             1.0f, -1.0f, 1.0f,
             -1.0f, -1.0f, 1.0f,
            
             // right
             1.0f, -1.0f,  1.0f,
             1.0f,  1.0f,  1.0f,
             1.0f,  1.0f, -1.0f,
             1.0f,  1.0f, -1.0f,
             1.0f, -1.0f, -1.0f,
             1.0f, -1.0f,  1.0f,
             
             // back.
             1.0f, -1.0f, -1.0f,
             1.0f,  1.0f, -1.0f,
            -1.0f,  1.0f, -1.0f,
            -1.0f,  1.0f, -1.0f,
            -1.0f, -1.0f, -1.0f,
             1.0f, -1.0f, -1.0f,
             
            // left.
            -1.0f, -1.0f, -1.0f,
            -1.0f,  1.0f, -1.0f,
            -1.0f,  1.0f,  1.0f,
            -1.0f,  1.0f,  1.0f,
            -1.0f, -1.0f,  1.0f,
            -1.0f, -1.0f, -1.0f,
            
            // top.
            -1.0f, 1.0f, -1.0f,
             1.0f, 1.0f, -1.0f,
             1.0f, 1.0f,  1.0f,
             1.0f, 1.0f,  1.0f,
            -1.0f, 1.0f,  1.0f,
            -1.0f, 1.0f, -1.0f,
            
            // bottom
           -1.0f, -1.0f, -1.0f,
           -1.0f, -1.0f,  1.0f,
            1.0f, -1.0f,  1.0f,
            1.0f, -1.0f,  1.0f,
            1.0f, -1.0f, -1.0f,
           -1.0f, -1.0f, -1.0f
        };
        
        float[] colorVertices = new float[] {
            1.0f, 1.0f, 0.0f,
            1.0f, 1.0f, 0.0f,
            1.0f, 1.0f, 0.0f,
            
            1.0f, 1.0f, 0.0f,
            1.0f, 1.0f, 0.0f,
            1.0f, 1.0f, 0.0f,
            
            1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
            
            1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
            
            0.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 1.0f,
            
            0.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 1.0f,
            
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            
            1.0f, 0.0f, 0.0f, 
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
        };
        
        vbo = GL15.glGenBuffers();
        colorVBO = GL15.glGenBuffers();
        FloatBuffer flo = BufferUtils.createFloatBuffer(vertices.length);
        FloatBuffer color = BufferUtils.createFloatBuffer(colorVertices.length);
        
        //flo.put(new float[] {-1.0f, -1.0f, 1.0f,});
        //flo.put(new float[] {-1.0f,  1.0f, 1.0f,});
        //flo.put(new float[] { 1.0f,  1.0f, 1.0f,});
        flo.put(vertices);
        color.put(colorVertices);
        flo.flip();
        color.flip();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, flo, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorVBO);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, color, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }   
}
