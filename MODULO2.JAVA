
package org.example;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main implements GLEventListener {

    private static float posX = 0.0f;
    private static float posY = 0.0f;
    private static float angulo = 0.0f;

    public static void main(String[] args) {

        GLJPanel panel = new GLJPanel();
        panel.addGLEventListener(new Main());

        JFrame frame = new JFrame("Mi primera ventana OpenGL");

        frame.add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.setFocusable(true);

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_UP)
                    posY += 0.1f;

                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    posY -= 0.1f;

                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                    posX -= 0.1f;

                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                    posX += 0.1f;

                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                    angulo += 5.0f;

                panel.repaint();
            }
        });

        FPSAnimator animator = new FPSAnimator(panel, 60);
        animator.start();

        panel.requestFocusInWindow();
    }

    @Override
    public void init(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();

        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        gl.glLoadIdentity();

        // Traslación
        gl.glTranslatef(posX, posY, 0.0f);

        // Rotación
        gl.glRotatef(angulo, 0.0f, 0.0f, 1.0f);

        // Color negro
        gl.glColor3f(0.0f, 0.0f, 0.0f);

        // Dibujo del triángulo
        gl.glBegin(GL2.GL_TRIANGLES);

        gl.glVertex2f(0.0f, 0.3f);
        gl.glVertex2f(-0.5f, -0.4f);
        gl.glVertex2f(0.5f, -0.4f);

        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y,
                        int width, int height) {
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }
}
