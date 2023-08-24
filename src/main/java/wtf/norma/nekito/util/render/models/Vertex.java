package wtf.norma.nekito.util.render.models;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

// author: saph
// thanks for it


public class Vertex {

    private final Vector3f pos;
    private final Vector2f texCoords;
    private final Vector3f normal;
    private final Vector3f tangent;

    public Vertex(Vector3f pos, Vector2f texCoords, Vector3f normal, Vector3f tangent) {
        this.pos = pos;
        this.texCoords = texCoords;
        this.normal = normal;
        this.tangent = tangent;
    }

    public Vector3f pos() {
        return this.pos;
    }

    public Vector2f texCoords() {
        return this.texCoords;
    }

    public Vector3f normal() {
        return this.normal;
    }

    public Vector3f tangent() {
        return this.tangent;
    }
}

