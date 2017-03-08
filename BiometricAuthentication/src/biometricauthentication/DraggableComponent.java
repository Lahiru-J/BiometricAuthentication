/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricauthentication;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;

/**
 *
 * @author Lahiru
 */
public class DraggableComponent extends JComponent{
      /** If sets <b>TRUE</b> this component is dragable */
    private boolean draggable = true;
    /** 2D Point representing the coordinate where mouse is, relative parent container */
    protected Point anchorPoint;
    /** Default mouse cursor for dragging action */
    protected Cursor draggingCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    /** If sets <b>TRUE</b> when dragging component, 
    it will be painted over each other (z-Buffer change) */
    protected boolean overbearing = false;

    public DraggableComponent() {
        addDragListeners();
        setOpaque(true);
        setBackground(new Color(240,240,240));
    }
    
    /**
     * Add Mouse Motion Listener with drag function
     */
    private void addDragListeners() {
        /** This handle is a reference to THIS because in next Mouse Adapter 
	"this" is not allowed */
        final DraggableComponent handle = this;
        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                anchorPoint = e.getPoint();
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int anchorX = anchorPoint.x;
                int anchorY = anchorPoint.y;

                Point parentOnScreen = getParent().getLocationOnScreen();
                Point mouseOnScreen = e.getLocationOnScreen();
                Point position = new Point(mouseOnScreen.x - parentOnScreen.x - 
		anchorX, mouseOnScreen.y - parentOnScreen.y - anchorY);
                setLocation(position);

                //Change Z-Buffer if it is "overbearing"
                if (overbearing) {
                    getParent().setComponentZOrder(handle, 0);
                    repaint();
                }
            }
        });
    }
    
    @Override
   protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       if (isOpaque()) {
           g.setColor(getBackground());
           g.fillRect(0, 0, getWidth(), getHeight());
       }
   }

private void removeDragListeners() {
       for (MouseMotionListener listener : this.getMouseMotionListeners()) {
           removeMouseMotionListener(listener);
       }
       setCursor(Cursor.getDefaultCursor());
   }
}