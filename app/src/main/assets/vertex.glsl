precision mediump float;

uniform mat4 uMVPMatrix;
uniform float uTime;

attribute vec4 aPosition;
attribute vec2 aTextureCoord;

varying vec4 vColor;
varying vec2 vTextureCoord;

varying vec3 vPos0;
varying vec3 vPos1;
varying vec3 vPos1n;

void main() {


	vec4 position;
	position.x = aPosition.x;
	position.y = aPosition.y;
	position.z = aPosition.z;
	position.w = 1.0;

	  vPos0 =  vec4(position).xyz;
      vPos1 = aPosition.xyz;
      vPos1n = aPosition.xyz+vec3(0.5);

	gl_Position = uMVPMatrix * position;
}

