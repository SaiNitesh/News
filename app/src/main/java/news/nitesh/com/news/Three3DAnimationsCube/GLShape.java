package news.nitesh.com.news.Three3DAnimationsCube;

import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Nitesh on 10/15/2017.
 */

public class GLShape {

    public M4 mTransform;
    public M4 mAnimateTransform;
    protected List<GLFace> mFaceList = new ArrayList();
    protected List<GLVertex> mVertexList = new ArrayList();
    protected ArrayList mIndexList = new ArrayList(); // make more efficient?
    protected GLWorld mWorld;

    public GLShape(GLWorld world) {
        mWorld = world;
    }

    public void addFace(GLFace face) {
        mFaceList.add(face);
    }

    public void setFaceColor(int face, GLColor color) {
        mFaceList.get(face).setColor(color);
    }

    public void putIndices(ShortBuffer buffer) {
        Iterator<GLFace> iter = mFaceList.iterator();
        while (iter.hasNext()) {
            GLFace face = iter.next();
            face.putIndices(buffer);
        }
    }

    public int getIndexCount() {
        int count = 0;
        Iterator iter = mFaceList.iterator();
        while (iter.hasNext()) {
            GLFace face = (GLFace) iter.next();
            count += face.getIndexCount();
        }
        return count;
    }

    public GLVertex addVertex(float x, float y, float z) {

// look for an existing GLVertex first
        Iterator<GLVertex> iter = mVertexList.iterator();
        while (iter.hasNext()) {
            GLVertex vertex = iter.next();
            if (vertex.x == x && vertex.y == y && vertex.z == z) {
                return vertex;
            }
        }

// doesn't exist, so create new vertex
        GLVertex vertex = mWorld.addVertex(x, y, z);
        mVertexList.add(vertex);
        return vertex;
    }

    public void animateTransform(M4 transform) {
        mAnimateTransform = transform;

        if (mTransform != null)
            transform = mTransform.multiply(transform);

        Iterator iter = mVertexList.iterator();
        while (iter.hasNext()) {
            GLVertex vertex = (GLVertex) iter.next();
            mWorld.transformVertex(vertex, transform);
        }
    }

    public void startAnimation() {
    }

    public void endAnimation() {
        if (mTransform == null) {
            mTransform = new M4(mAnimateTransform);
        } else {
            mTransform = mTransform.multiply(mAnimateTransform);
        }
    }


}
