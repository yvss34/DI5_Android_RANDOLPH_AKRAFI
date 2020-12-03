//
// Created by Lenovo on 03/12/2020.
//

#include <string.h>
#include <math.h>
#include <jni.h>

using namespace std;
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