out vec4 fragColor;
in vec2 fragCoord;

void main(){
    // normailze and adjsut for ratio
    vec2 res = iResolution.xy,
    uv = (fragCoord*2.0-res ) / res.y;
    uv*=.7;
    //initilize colors
    vec4 color1 = vec4(.4,.6,.7,1.0);
    vec4 color2 = vec4(.9,.7,.6,1.0);

    // shade with 2 faux lights
    color1*=.8-distance(uv,vec2(-.1,-.1));
    color2*=.6-distance(uv,vec2(.25,.3));
    vec4 sphere = color1+color2 ;

    //limit edges to circle shape
    float d = distance(uv, vec2(0.0));
    // smooth edges
    float t =1.0- smoothstep(.6,.61, d);
    // apply shape to colors
    sphere*=t+sin(iTime)*.2*uv.y;

    //output final color, and brighten
    fragColor = sphere*1.6;

}