#version 120

uniform sampler2D textureIn, textureToCheck;
uniform vec2 texelSize, direction;
uniform vec3 color;
uniform bool avoidTexture;
uniform float exposure, radius,blurSize,blurMoment;
uniform float weights[256];

#define offset direction * texelSize

void main() {
    if (direction.y == 1 && avoidTexture) {
        if (texture2D(textureToCheck, gl_TexCoord[0].st).a != 0.0) discard;
    }

    float innerAlpha = texture2D(textureIn, gl_TexCoord[0].st).a * weights[0];
    //float innerAlpha = texture2D(textureIn, gl_TexCoord[0].st).a * weights[0] * 5;

    for (float r = 1.0; r <= radius; r ++) {
        innerAlpha += texture2D(textureIn, gl_TexCoord[0].st + offset * r).a * weights[int(r)];
        innerAlpha += texture2D(textureIn, gl_TexCoord[0].st - offset * r).a * weights[int(r)];
    }
    vec4 sum = vec4(color, mix(innerAlpha, 1.0 - exp(-innerAlpha * exposure), step(0.0, direction.y)));
    //thank you! http://www.gamerendering.com/2008/10/11/gaussian-blur-filter-shader/ for the
    //blur tutorial
    // blur in y (vertical)
    // take nine samples, with the distance blurSize between them
    vec2 texcoord = direction.xy/texelSize.xy;
    float intensity  = 1;
    sum += texture2D(textureIn, vec2(texcoord.x - 4.0*blurSize, texcoord.y)) * 0.05 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x - 3.0*blurSize, texcoord.y)) * 0.09 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x - 2.0*blurSize, texcoord.y)) * 0.12 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x - blurSize, texcoord.y)) * 0.15 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y)) * 0.16 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x + blurSize, texcoord.y)) * 0.15 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x + 2.0*blurSize, texcoord.y)) * 0.12 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x + 3.0*blurSize, texcoord.y)) * 0.09 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x + 4.0*blurSize, texcoord.y)) * 0.05 * blurMoment;

    // blur in y (vertical)
    // take nine samples, with the distance blurSize between them
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y - 4.0*blurSize)) * 0.05 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y - 3.0*blurSize)) * 0.09 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y - 2.0*blurSize)) * 0.12 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y - blurSize)) * 0.15 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y)) * 0.16 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y + blurSize)) * 0.15 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y + 2.0*blurSize)) * 0.12 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y + 3.0*blurSize)) * 0.09 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y + 4.0*blurSize)) * 0.05 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x - 4.0*blurSize, texcoord.y)) * 0.05 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x - 3.0*blurSize, texcoord.y)) * 0.09 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x - 2.0*blurSize, texcoord.y)) * 0.12 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x - blurSize, texcoord.y)) * 0.15 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y)) * 0.16 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x + blurSize, texcoord.y)) * 0.15 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x + 2.0*blurSize, texcoord.y)) * 0.12 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x + 3.0*blurSize, texcoord.y)) * 0.09 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x + 4.0*blurSize, texcoord.y)) * 0.05 * blurMoment;

    // blur in y (vertical)
    // take nine samples, with the distance blurSize between them
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y - 4.0*blurSize)) * 0.05 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y - 3.0*blurSize)) * 0.09 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y - 2.0*blurSize)) * 0.12 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y - blurSize)) * 0.15 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y)) * 0.16 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y + blurSize)) * 0.15 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y + 2.0*blurSize)) * 0.12 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y + 3.0*blurSize)) * 0.09 * blurMoment;
    sum += texture2D(textureIn, vec2(texcoord.x, texcoord.y + 4.0*blurSize)) * 0.05 * blurMoment;


    gl_FragColor = sum;
}
