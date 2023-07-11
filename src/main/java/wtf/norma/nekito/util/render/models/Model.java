package wtf.norma.nekito.util.render.models;


// author: saph
// thanks for it

public abstract class Model {

    private String id;

    public Model() {
    }

    public abstract void render();

    public abstract void renderGroups(String var1);

    public void setID(String id) {
        this.id = id;
    }

    public String getID() {
        return this.id;
    }
}