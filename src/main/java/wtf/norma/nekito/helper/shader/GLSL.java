package wtf.norma.nekito.helper.shader;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.lwjgl.opengl.GL20.*;

public enum GLSL {
    MAIN_MENU("/assets/minecraft/shadersMenu/menu.fsh");

    private final int programId;
    private final int timeUniform;
    private final int mouseUniform;
    private final int resolutionUniform;

    GLSL(String fragmentShaderLocation) {
        try {
            int program = glCreateProgram();
            this.programId = program;

            //glAttachShader(program, createShader(GLSLSandboxShader.class.getResourceAsStream("/passthrough.vsh"), GL_VERTEX_SHADER));
            glAttachShader(program, createShader(GLSL.class.getResourceAsStream(fragmentShaderLocation), GL_FRAGMENT_SHADER));
            glLinkProgram(program);

            int linked = glGetProgrami(program, GL_LINK_STATUS);
            if (linked == 0) { // If linking failed
                System.err.println(glGetProgramInfoLog(program, glGetProgrami(program, GL_INFO_LOG_LENGTH)));
                throw new IllegalStateException("Shader failed to link");
            }


            // Setup uniforms
            glUseProgram(program);
            this.timeUniform = glGetUniformLocation(program, "time");
            this.mouseUniform = glGetUniformLocation(program, "mouse");
            this.resolutionUniform = glGetUniformLocation(program, "resolution");

            glUseProgram(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void useShader(int width, int height, float mouseX, float mouseY, float time) {
        glUseProgram(this.programId);

        glUniform2f(this.resolutionUniform, width, height);
        glUniform2f(this.mouseUniform, mouseX / width, 1.0f - mouseY / height);
        glUniform1f(this.timeUniform, time);
    }

    private int createShader(InputStream inputStream, int shaderType) throws IOException {
        int shader = glCreateShader(shaderType);

        glShaderSource(shader, IOUtils.toString(inputStream, StandardCharsets.UTF_8));
        glCompileShader(shader);

        int compiled = glGetShaderi(shader, GL_COMPILE_STATUS);
        if (compiled == 0) { // If compilation failed
            System.err.println(glGetShaderInfoLog(shader, glGetShaderi(shader, GL_INFO_LOG_LENGTH)));
            throw new IllegalStateException("Failed to compile shader");
        }

        return shader;
    }
}