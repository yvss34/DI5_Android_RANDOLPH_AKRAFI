//
// Created by Lenovo on 03/12/2020.
//

#include <string.h>
#include <math.h>
#include <jni.h>

using namespace std;

// GRADIENT

/*
extern "C" {
JNIEXPORT void JNICALL Java_com_example_myapplicationopencv_MainActivity_ProcessFast(JNIEnv* env, jobject thiz, jint width,jint height, jbyteArray data, jbyteArray out)
    {
    jbyte* _data = env->GetByteArrayElements(data, 0);
    jbyte* _out = env->GetByteArrayElements(out, 0);
    //stuf todo here

    jbyte GradH;
    jbyte GradV;

    for(int x=1; x<width-1;x++){
        for(int y=1; y<height-1;y++){
            GradH = (_data[y*width+(x-1)]-_data[y*width+(x+1)]);
            if(GradH < 0)
            {
                GradH = (jbyte)0;
            }

            if(GradH > 255)
            {
                GradH = (jbyte)255;
            }

            GradV = (_data[(y-1)*width+x]-_data[(y+1)*width+x]);
            if(GradV < 0)
            {
                GradV = (jbyte)0;
            }

            if(GradV > 255)
            {
                GradV = (jbyte)255;
            }

            _out[y*width+x]= (GradH+GradV);
        }
    }



    env->ReleaseByteArrayElements(data, _data, 0);
    env->ReleaseByteArrayElements(out, _out, 0);
    }
}

*/

// SOBEL

extern "C" {
JNIEXPORT void JNICALL Java_com_example_myapplicationopencv_MainActivity_processFast(JNIEnv* env, jobject thiz, jint width,jint height, jbyteArray data, jbyteArray out)
{
jbyte* _data = env->GetByteArrayElements(data, 0);
jbyte* _out = env->GetByteArrayElements(out, 0);
//stuf todo here

int sobel_x[3][3] = {{-1,0,1},{-2,0,2},{-1,0,1}};
int sobel_y[3][3] = {{-1,-2,-1},{0,0,0},{1,2,1}};

int pixel_x;
int pixel_y;

for(int x=1;x<width-2;x++) {
for (int y = 1; y < height - 2; y++) {
pixel_x = (sobel_x[0][0] * _data[(y-1)*width+(x-1)]) + (sobel_x[0][1] * _data[(y-1)*width+(x)]) + (sobel_x[0][2] * _data[(y+1)*width+(x+1)]) +
          (sobel_x[1][0] * _data[(y)*width+(x-1)])   + (sobel_x[1][1] * _data[(y)*width+(x)])   + (sobel_x[1][2] * _data[(y)*width+(x+1)]) +
          (sobel_x[2][0] * _data[(y+1)*width+(x-1)]) + (sobel_x[2][1] * _data[(y+1)*width+(x)]) + (sobel_x[2][2] * _data[(y+1)*width+(x+1)]);
pixel_y = (sobel_y[0][0] * _data[(y-1)*width+(x-1)]) + (sobel_y[0][1] * _data[(y-1)*width+(x)]) + (sobel_y[0][2] * _data[(y+1)*width+(x+1)]) +
          (sobel_y[1][0] * _data[(y)*width+(x-1)])   + (sobel_y[1][1] * _data[(y)*width+(x)])   + (sobel_y[1][2] * _data[(y)*width+(x+1)]) +
          (sobel_y[2][0] * _data[(y+1)*width+(x-1)]) + (sobel_y[2][1] * _data[(y+1)*width+(x)]) + (sobel_x[2][2] * _data[(y+1)*width+(x+1)]);

int val = (int)sqrt((pixel_x * pixel_x) + (pixel_y * pixel_y));

if(val < 0)
{
val = 0;
}

if(val > 255)
{
val = 255;
}

_out[y*width+x] = (jbyte)val;
}

}

env->ReleaseByteArrayElements(data, _data, 0);
env->ReleaseByteArrayElements(out, _out, 0);

}
}