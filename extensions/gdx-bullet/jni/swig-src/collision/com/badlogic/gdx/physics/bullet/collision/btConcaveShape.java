/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.0
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet.collision;

import com.badlogic.gdx.physics.bullet.BulletBase;
import com.badlogic.gdx.physics.bullet.linearmath.*;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;

public class btConcaveShape extends btCollisionShape {
	private long swigCPtr;
	
	protected btConcaveShape(final String className, long cPtr, boolean cMemoryOwn) {
		super(className, CollisionJNI.btConcaveShape_SWIGUpcast(cPtr), cMemoryOwn);
		swigCPtr = cPtr;
	}
	
	/** Construct a new btConcaveShape, normally you should not need this constructor it's intended for low-level usage. */
	public btConcaveShape(long cPtr, boolean cMemoryOwn) {
		this("btConcaveShape", cPtr, cMemoryOwn);
		construct();
	}
	
	@Override
	protected void reset(long cPtr, boolean cMemoryOwn) {
		if (!destroyed)
			destroy();
		super.reset(CollisionJNI.btConcaveShape_SWIGUpcast(swigCPtr = cPtr), cMemoryOwn);
	}
	
	public static long getCPtr(btConcaveShape obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	@Override
	protected void finalize() throws Throwable {
		if (!destroyed)
			destroy();
		super.finalize();
	}

  @Override protected synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				CollisionJNI.delete_btConcaveShape(swigCPtr);
			}
			swigCPtr = 0;
		}
		super.delete();
	}

  public void processAllTriangles(btTriangleCallback callback, Vector3 aabbMin, Vector3 aabbMax) {
    CollisionJNI.btConcaveShape_processAllTriangles(swigCPtr, this, btTriangleCallback.getCPtr(callback), callback, aabbMin, aabbMax);
  }

}
