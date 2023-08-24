package wtf.norma.nekito.util.render.models;

import org.lwjgl.util.vector.Vector3f;

public class Material {
// author: saph
// thanks for it

    private final String name;
    public Vector3f diffuseColor;
    public Vector3f ambientColor;
    public int ambientTexture;
    public int diffuseTexture;
    public float transparency = 1.0F;

    public Material(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

